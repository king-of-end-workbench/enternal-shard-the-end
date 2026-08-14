package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public class DryChorusPlantPriIzmienieniiSosiednieghoBlokaProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get() && (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get()) {
		} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get() && (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get()) {
		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get() || (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get()
				|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get() || (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get()) {
		} else {
		}
	}
}