package net.mcreator.end_elemetn.custommixin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import net.mcreator.end_elemetn.custom.EndIslandGenerationState;

/**
 * The density function can only ever be tuned so far without breaking biome classification (see
 * EndIslandDensityFunctionMixin's extensive notes on why it must never return less than "capped").
 * This is a decisive, unconditional backstop that runs strictly AFTER shaping and biome selection
 * have already happened, so it cannot touch biome correctness at all - it just deletes blocks.
 *
 * Rule: if a column is solid all the way from below the ceiling up through it (a real "mountain"
 * fused to the ground), everything above the ceiling gets chopped off down to the first natural air
 * gap. If the ground doesn't reach the ceiling at all (air right below it), that's a genuinely
 * separate floating island sitting up there - left alone.
 */
@Mixin(NoiseBasedChunkGenerator.class)
public abstract class MountainCarverMixin {
	private static final int CEILING_Y = 70;

	// A flat y=100 plane for every column made the cut look like a razor-straight tabletop. Waving
	// the ceiling height itself (two octaves - one slow rolling wave, one finer ripple) makes the
	// trim line read as an uneven, eroded ridge instead of an obviously artificial flat cut. Since
	// it varies smoothly across neighboring columns there's no vertical cliff at the boundary either.
	private static final SimplexNoise EDGE_NOISE = new SimplexNoise(RandomSource.create(918273645L));

	private static int end_elemetn$ceilingAt(int wx, int wz) {
		double coarse = EDGE_NOISE.getValue(wx * 0.015, wz * 0.015) * 10.0;
		// Dialed way down from 4.0 - at that amplitude, neighboring columns routinely landed on
		// different carved heights, turning the cut into a staircase. That broke tree placement's
		// "is there solid ground next to me" platform check almost everywhere the carver had run.
		double fine = EDGE_NOISE.getValue(wx * 0.06 + 4000.0, wz * 0.06 + 4000.0) * 1.0;
		return Mth.clamp(CEILING_Y + (int) Math.round(coarse + fine), CEILING_Y - 14, CEILING_Y + 14);
	}

	@Inject(method = "fillFromNoise", at = @At("RETURN"), cancellable = true)
	private void end_elemetn$carveMountains(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess, CallbackInfoReturnable<CompletableFuture<ChunkAccess>> cir) {
		// Chaotic mode deliberately wants tall mountains/spikes fused straight to the ground - this
		// carver would otherwise chop every single one of them right back off. Only run in calm mode.
		if (EndIslandGenerationState.CHAOTIC_ENABLED)
			return;

		ChunkGenerator self = (ChunkGenerator) (Object) this;
		if (!(self.getBiomeSource() instanceof TheEndBiomeSource))
			return;

		CompletableFuture<ChunkAccess> original = cir.getReturnValue();
		cir.setReturnValue(original.thenApply(chunk -> {
			end_elemetn$carve(chunk);
			return chunk;
		}));
	}

	private static void end_elemetn$carve(ChunkAccess chunk) {
		int maxY = chunk.getMaxBuildHeight() - 1;
		if (CEILING_Y >= maxY)
			return;

		ChunkPos chunkPos = chunk.getPos();
		int originX = chunkPos.getMinBlockX();
		int originZ = chunkPos.getMinBlockZ();
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		for (int lx = 0; lx < 16; lx++) {
			for (int lz = 0; lz < 16; lz++) {
				int wx = originX + lx;
				int wz = originZ + lz;
				int ceiling = Math.min(end_elemetn$ceilingAt(wx, wz), maxY - 1);

				pos.set(wx, ceiling, wz);
				if (chunk.getBlockState(pos).isAir())
					continue; // nothing solid at the ceiling here - no mountain to trim

				pos.set(wx, ceiling - 1, wz);
				if (chunk.getBlockState(pos).isAir())
					continue; // disconnected from the ground right below the ceiling - a real floating island, leave it

				// Bridge over internal air pockets (ordinary 3D noise / the void-cutoff carving
				// punching holes partway up the mountain - stopping there left orphaned leftover
				// scraps floating above the pocket, which also broke tree placement's platform
				// check on and around them), but stop for good on a genuinely large gap, since that
				// means there's a real, separate floating island further up this same column (our
				// floating tiers start at least ~30 blocks above the carve ceiling) - blindly
				// carving through would eat chunks out of that legitimate island instead. 25 was
				// picked to comfortably clear ordinary internal pockets while staying under that
				// ~30-block margin.
				int airRun = 0;
				for (int y = ceiling + 1; y <= maxY; y++) {
					pos.set(wx, y, wz);
					if (chunk.getBlockState(pos).isAir()) {
						if (++airRun > 25)
							break;
					} else {
						airRun = 0;
						chunk.setBlockState(pos, Blocks.AIR.defaultBlockState(), false);
					}
				}
			}
		}
	}
}
