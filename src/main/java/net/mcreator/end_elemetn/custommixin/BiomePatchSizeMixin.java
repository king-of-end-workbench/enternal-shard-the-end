package net.mcreator.end_elemetn.custommixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

import net.mcreator.end_elemetn.endbiomes.WeightedPicker;

/**
 * TheEndBiomeData.java (which calls WeightedPicker.pickFromNoise with x/64, z/64) is a vendored
 * MCreator utility class that gets reset to its stock template on every build/regeneration, so
 * editing that divisor directly doesn't stick. Redirecting the actual noise sample here instead -
 * this class isn't part of MCreator's element/template system at all, so it's never touched.
 *
 * Rescaling by 64/220 turns the effective divisor from 64 into 220: bigger, more coherent custom
 * biome regions with far fewer transitions instead of a fine patchwork.
 */
@Mixin(WeightedPicker.class)
public abstract class BiomePatchSizeMixin {
	private static final double RESCALE = 64.0 / 220.0;

	@Redirect(method = "pickFromNoise", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/synth/ImprovedNoise;noise(DDD)D"))
	private double end_elemetn$biggerPatches(ImprovedNoise sampler, double x, double y, double z) {
		return sampler.noise(x * RESCALE, y * RESCALE, z * RESCALE);
	}
}
