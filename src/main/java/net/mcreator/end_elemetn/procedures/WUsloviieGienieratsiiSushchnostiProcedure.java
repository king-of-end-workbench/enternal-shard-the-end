package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public class WUsloviieGienieratsiiSushchnostiProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == EndElemetnModBlocks.END_MESHFADES_STONE.get() || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.END_STONE)
				&& (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.AIR) {
			return true;
		}
		return false;
	}
}