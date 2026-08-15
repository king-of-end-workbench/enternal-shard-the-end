package net.mcreator.end_elemetn.custommixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import net.mcreator.end_elemetn.custom.EndIslandGenerationState;

@Mixin(targets = "net.minecraft.world.level.levelgen.DensityFunctions$EndIslandDensityFunction")
public abstract class EndIslandDensityFunctionMixin {
	@Shadow
	@Final
	private SimplexNoise islandNoise;

	// Its own signal only stays strong within roughly 90-100 blocks of the origin (f = 100 -
	// distance, clamped), so this is already a generous safety margin covering the whole visible
	// main-island structure.
	private static final double MAIN_ISLAND_RADIUS = 250.0;

	/**
	 * How large "sloped_cheese" needs to be at height {@code y} for the End density formula to
	 * actually produce solid terrain there. Derived by hand from the formula in
	 * data/minecraft/worldgen/noise_settings/end.json, using the deliberately stretched
	 * (to_y=2400 instead of vanilla's 312) second gradient - this is what makes tall/dramatic
	 * terrain reliably reachable and is core to the chaotic mode's effect. It does mean a natural,
	 * un-boosted island's own body reaches a bit higher than pure vanilla proportions (~85 blocks
	 * instead of ~65) - that trade-off stays for now so chaotic mode keeps its full punch.
	 */
	private static double requiredThreshold(double y) {
		double g1 = Mth.clamp((y - 4.0) / 28.0, 0.0, 1.0);
		double g2 = Mth.clamp(1.0 - (y - 56.0) / 2344.0, 0.0, 1.0);
		if (g1 <= 1.0E-4 || g2 <= 1.0E-4)
			return 100000.0;
		return (23.203125 + 0.234375 / g1) / g2 - 23.4375;
	}

	/**
	 * Same idea as a hard {@code Math.min(v, 0.3)} cap, but smooth: below 0.24 it's the identity
	 * (untouched), above that it curves gently up towards an 0.3 asymptote instead of hard-clipping.
	 * A hard clip makes every strong column return the EXACT same 0.3 - a flat plateau - which then
	 * gets chewed up by the ordinary 3D terrain noise (added later in sloped_cheese) into a cluster
	 * of separate little bumps at the plateau's edge instead of one clean dome. Smoothly varying
	 * values near the cap avoids that flat-top artifact.
	 */
	private static double capIslandStrength(double original) {
		if (original <= 0.0)
			return original;

		// Under the stretched height gradient, even a barely-positive value solidifies into a thin
		// sheet near y~56 - vanilla itself never had this problem (its gradient made weak signal
		// simply unreachable), but ours does, and it was quietly gluing separate islands together
		// into one near-continuous floor with almost no void between them. Push that marginal band
		// down into real void instead. This is comfortably below where anything would ever classify
		// as highlands (0.25), so genuine island cores and their custom biomes are untouched - only
		// the near-nothing ambient fringe gets removed. Kept close to the 0.002 "is this a real
		// island at all" dispatch threshold to avoid also swallowing small_end_islands' own
		// deliberately faint signal. Dialed back from 0.12 - that value cut the transition zone so
		// short that every island's edge became a near-vertical cliff (a flat-topped mesa with
		// sheer walls once the mountain carver trimmed the top). A gentler cutoff leaves more room
		// for a natural slope.
		double voidCutoff = 0.05;
		if (original < voidCutoff) {
			double t = original / voidCutoff;
			return -0.3 + t * (voidCutoff + 0.3);
		}

		// Beyond the void cutoff, let magnitude vary naturally for a while (avoids the old hard
		// plateau) but saturate again fairly soon (asymptote ~0.4, reachable up to only ~y95) -
		// widening this all the way to ~0.8 (~y120+) made the carver strip so much material off
		// strong cores that the whole footprint ended up flattened at the carve ceiling, reading as
		// one big wide plateau/mountain instead of a smaller, naturally sloped island.
		double v = original;
		double threshold = 0.3;
		if (v <= threshold)
			return v;
		double range = 0.1;
		return threshold + range * (1.0 - Math.exp(-(v - threshold) / range));
	}

	@Inject(method = "compute", at = @At("RETURN"), cancellable = true)
	private void end_elemetn$rebuildIslandHeights(DensityFunction.FunctionContext context, CallbackInfoReturnable<Double> cir) {
		double original = cir.getReturnValue();
		int x = context.blockX();
		int y = context.blockY();
		int z = context.blockZ();

		// 1. Leave the main (spawn) island's SHAPE untouched (never shredded/floated) - but it still
		// needs the same 0.3 magnitude cap as every other island. Its raw signal near the exact
		// center can reach ~0.72, and under the stretched gradient (needed for floating/chaotic
		// peaks elsewhere) that raw magnitude alone was ballooning it into a ~y126 mountain instead
		// of the normal ~y85 mound every other grounded island gets.
		double distFromOrigin = Math.sqrt((double) x * x + (double) z * z);
		if (distFromOrigin < MAIN_ISLAND_RADIUS) {
			cir.setReturnValue(capIslandStrength(original));
			return;
		}

		// 2. Cap how big a single island's core signal can get, so far-out islands (whose raw
		// noise value naturally grows with distance) can't balloon into one giant landmass.
		double capped = capIslandStrength(original);

		boolean chaotic = EndIslandGenerationState.CHAOTIC_ENABLED;

		// 3. Anywhere that isn't a real island core stays exactly vanilla (capped only) - we only
		// ever ADD extra mass on top of what vanilla would place, never subtract/carve at random,
		// so ordinary random-location islands never get wiped out. The one exception is a rare,
		// deliberate debris chunk scattered far from any real island. Threshold kept very low so
		// weak signals (like small_end_islands, which are deliberately faint in vanilla) still
		// count as "a real island" and get scattered too, instead of being skipped entirely.
		if (capped < 0.002) {
			// Void Plains islands: a deliberately common (unlike the rare debris chunks below),
			// mode-independent landmass clustered near the bottom of the world, in columns that
			// otherwise have no natural island signal at all. TheEndBiomeData forces any "highlands"
			// column being resolved down here to pick end_elemetn:void_plains regardless of the
			// normal horizontal replacement pool, so these islands are always that biome.
			double voidPlainsPick = this.islandNoise.getValue(x * 0.02 + 12000, z * 0.02 + 12000);
			if (voidPlainsPick > 0.55) {
				double voidPlainsTargetY = 15.0 + (this.islandNoise.getValue(x * 0.006 + 12500, z * 0.006 + 12500) + 1.0) * 0.5 * 20.0; // ~15..35
				double voidPlainsDist = Math.abs(y - voidPlainsTargetY);
				double voidPlainsNeed = requiredThreshold(y) + 1.0 - capped;
				if (voidPlainsNeed > 0.0) {
					double voidPlainsEnvelope = Math.max(0.0, 1.0 - voidPlainsDist / 12.0);
					cir.setReturnValue(capped + voidPlainsNeed * voidPlainsEnvelope);
					return;
				}
			}

			double debrisThreshold = chaotic ? 0.76 : 0.97;
			double debrisSpread = chaotic ? 225.0 : 60.0;
			// Calm-mode debris uses a much finer pick frequency (0.015 vs chaotic's 0.0025) so a
			// "picked" region is small and isolated instead of a giant slowly-varying blob that
			// stretched debris into towering walls tens of blocks wide and over a hundred tall.
			double pickScale = chaotic ? 0.0025 : 0.015;
			double debrisPick = this.islandNoise.getValue(x * pickScale + 9000, z * pickScale + 9000);
			if (debrisPick > debrisThreshold) {
				double debrisY = 20.0 + (this.islandNoise.getValue(x * 0.006 + 9500, z * 0.006 + 9500) + 1.0) * 0.5 * debrisSpread;
				double debrisDist = Math.abs(y - debrisY);
				double fine = this.islandNoise.getValue(x * 0.05, y / 16.0, z * 0.05);
				double lobe = Math.max(0.0, fine - 0.55);
				double debrisRadius = chaotic ? 40.0 : 18.0;
				double envelope = Math.max(0.0, 1.0 - debrisDist / debrisRadius);
				double need = requiredThreshold(y) + 1.0 - capped;
				double debrisBonus = need > 0.0 ? need * envelope * Math.min(1.0, lobe * 4.0) : 0.0;
				cir.setReturnValue(capped + debrisBonus);
				return;
			}
			cir.setReturnValue(capped);
			return;
		}

		if (chaotic) {
			end_elemetn$chaoticIsland(cir, x, y, z, capped);
		} else {
			end_elemetn$calmIsland(cir, x, y, z, capped);
		}
	}

	/**
	 * Dramatic mode: some islands get a spike (or several shredded slices) of extra mass hurled
	 * to a wildly different, mostly-random height, on top of whatever the island naturally already
	 * has. Real mountains, real gaps, jagged silhouettes.
	 */
	private void end_elemetn$chaoticIsland(CallbackInfoReturnable<Double> cir, int x, int y, int z, double capped) {
		double pick = this.islandNoise.getValue(x * 0.006 + 3000, z * 0.006 + 3000);
		if (pick < 0.0) {
			cir.setReturnValue(capped);
			return;
		}

		double targetPick = this.islandNoise.getValue(x * 0.006 + 4000, z * 0.006 + 4000);
		double targetY = 20.0 + (targetPick + 1.0) * 0.5 * 225.0; // ~20..245
		double dist = Math.abs(y - targetY);

		double need = requiredThreshold(y) + 1.0 - capped;
		if (need <= 0.0) {
			cir.setReturnValue(capped);
			return;
		}

		double roughness = 0.65 + 0.35 * this.islandNoise.getValue(x * 0.09, y * 0.09, z * 0.09);

		double strength;
		double shredPick = this.islandNoise.getValue(x * 0.006 + 5000, z * 0.006 + 5000);
		if (shredPick > -0.1) {
			double envelope = Math.max(0.0, 1.0 - dist / 95.0);
			double fine = this.islandNoise.getValue(x * 0.032, y / 14.0, z * 0.032);
			double lobe = Math.max(0.0, fine - 0.3);
			strength = envelope * Math.min(1.0, lobe * 3.0) * roughness;
		} else {
			strength = Math.max(0.0, 1.0 - dist / 30.0) * roughness;
		}

		double bonus = need * Mth.clamp(strength, 0.0, 1.0);
		cir.setReturnValue(capped + bonus);
	}

	/**
	 * Calm mode - rebuilt from everything learned this round:
	 *
	 * - Must stay additive-only (never return less than capped), because "erosion" (which decides
	 * highlands/midlands/islands/barrens, which gates whether our custom biomes get picked at all)
	 * is cache_2d(end_islands) - it assumes this function never depends on Y and caches one sample
	 * per column. Returning anything less than capped anywhere risks that cached sample reading as
	 * "not highlands" and silently breaking every custom biome's grass/trees/etc.
	 * - Because chaotic mode needs the stretched (to_y=2400) gradient for its full effect, and the
	 * gradient can't differ between the two modes (it's static data, not something a Java mixin
	 * can toggle), a real island core's natural, un-boosted body unavoidably reaches roughly y~85
	 * here too - taller than pure vanilla, but a normal-looking chunky highlands island, not a
	 * disconnected floating piece. That's accepted as the "grounded" look.
	 * - Floating islands are placed on well-separated tiers that start comfortably ABOVE that ~85
	 * natural ceiling (not overlapping it), so they read as genuinely separate flying islands
	 * rather than a bump squeezed right on top of the ground.
	 * - Tiers spaced 90 blocks apart (world height raised to 384 to make room) - with each island
	 * being a ~45-block-thick slab, a 60-block spacing left only ~15 blocks of real clearance,
	 * which noise/interpolation blurred into visible merging between tiers 1 and 2. 90 gives a
	 * genuine ~45-block clear gap.
	 */
	private static final double[] CALM_FLOATING_TIERS = { 140.0, 230.0, 320.0 };

	private void end_elemetn$calmIsland(CallbackInfoReturnable<Double> cir, int x, int y, int z, double capped) {
		// Weak/small islands (vanilla's small_end_islands land here) all naturally solidify at
		// roughly the same y~56-60 no matter their exact tiny magnitude - the gradient's shape near
		// sea level barely cares how strong the signal is, only whether it's positive at all. Give
		// them their own small, independent height pick instead, so they scatter across a modest
		// range like real small floating islands instead of all sitting at the same altitude.
		if (capped < 0.08) {
			double smallPick = this.islandNoise.getValue(x * 0.02 + 11000, z * 0.02 + 11000);
			double smallTargetY = 32.0 + (smallPick + 1.0) * 0.5 * 70.0; // ~32..102
			double smallDist = Math.abs(y - smallTargetY);
			double smallNeed = requiredThreshold(y) + 1.0 - capped;
			if (smallNeed > 0.0) {
				double smallEnvelope = Math.max(0.0, 1.0 - smallDist / 10.0);
				cir.setReturnValue(capped + smallNeed * smallEnvelope);
				return;
			}
			cir.setReturnValue(capped);
			return;
		}

		double raisePick = this.islandNoise.getValue(x * 0.006 + 7000, z * 0.006 + 7000);
		if (raisePick < -0.2) {
			// Most islands still just stay grounded, at their normal (if now somewhat taller than
			// vanilla) natural height - only a majority-but-not-all subset float.
			cir.setReturnValue(capped);
			return;
		}

		// Discrete, hard-coded tiers with big fixed gaps (60 blocks) so two different floating
		// islands can never coincidentally land close enough to fuse together, and each tier sits
		// well clear of the natural ~85 block ceiling below.
		int tierIndex = Mth.clamp((int) ((raisePick + 0.2) / 1.2 * CALM_FLOATING_TIERS.length), 0, CALM_FLOATING_TIERS.length - 1);
		double targetPick = this.islandNoise.getValue(x * 0.02 + 8000, z * 0.02 + 8000);
		double targetY = CALM_FLOATING_TIERS[tierIndex] + targetPick * 4.0;
		double dist = Math.abs(y - targetY);

		double need = requiredThreshold(y) + 1.0 - capped;
		if (need <= 0.0) {
			cir.setReturnValue(capped);
			return;
		}

		// Flattened band (a thin slab, not a thick mountain) - purely additive, so it only ever
		// makes this column MORE likely to be solid than vanilla, never less. Radius 13.5 gives a
		// ~45-block-thick slab (up from the earlier ~20-block-thick radius-6 version).
		double envelope = Math.max(0.0, 1.0 - dist / 13.5);
		double bonus = need * envelope;
		cir.setReturnValue(capped + bonus);
	}
}
