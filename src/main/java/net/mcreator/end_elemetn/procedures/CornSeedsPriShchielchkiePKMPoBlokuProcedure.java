package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public class CornSeedsPriShchielchkiePKMPoBlokuProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == EndElemetnModBlocks.CHORUS_GRASS.get()) {
			world.setBlock(BlockPos.containing(x, y + 1, z), EndElemetnModBlocks.CORN_STAGE_0.get().defaultBlockState(), 3);
		}
	}
}