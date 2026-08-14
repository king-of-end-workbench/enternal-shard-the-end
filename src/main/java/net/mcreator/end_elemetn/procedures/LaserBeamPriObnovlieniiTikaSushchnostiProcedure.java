package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.end_elemetn.entity.LaserBeamEntity;

public class LaserBeamPriObnovlieniiTikaSushchnostiProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (!(entity instanceof LaserBeamEntity laserBeam))
			return;
		if (!laserBeam.getShouldSynchroRot())
			return;
		laserBeam.setYRot(laserBeam.getLaserYaw());
		laserBeam.setXRot(laserBeam.getLaserPitch());
	}
}
