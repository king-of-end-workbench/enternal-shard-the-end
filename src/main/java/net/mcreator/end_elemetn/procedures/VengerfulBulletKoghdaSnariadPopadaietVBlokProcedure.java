package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModEntities;

public class VengerfulBulletKoghdaSnariadPopadaietVBlokProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level) {
			Entity entityToSpawn = EndElemetnModEntities.BULLETENTITY.get().spawn(_level, BlockPos.containing(x, y + 1, z), MobSpawnType.MOB_SUMMONED);
			if (entityToSpawn != null) {
				entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
			}
		}
	}
}