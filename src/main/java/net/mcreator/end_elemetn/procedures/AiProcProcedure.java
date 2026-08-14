package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public class AiProcProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		return !((world.getBlockState(BlockPos.containing((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX(), (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY(),
				(entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ()))).getBlock() == EndElemetnModBlocks.SNARELINGWEB.get());
	}
}