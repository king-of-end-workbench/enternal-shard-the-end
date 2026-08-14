package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.EndElemetnMod;

public class CommprestVoidPriObnovlieniiTikaProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		EndElemetnMod.queueServerWork(5, () -> {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		});
	}
}