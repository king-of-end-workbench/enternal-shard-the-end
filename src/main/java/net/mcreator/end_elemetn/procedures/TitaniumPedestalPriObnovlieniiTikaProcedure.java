package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class TitaniumPedestalPriObnovlieniiTikaProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "points") == 0) {
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "points") > 4) {
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "points") == 4) {
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "points") == 5) {
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}