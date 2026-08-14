package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.ParticleTypes;

public class MugGaserBlockObnovlieniieTikaProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < 0.4) {
			world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 0, 1, 0);
		}
	}
}