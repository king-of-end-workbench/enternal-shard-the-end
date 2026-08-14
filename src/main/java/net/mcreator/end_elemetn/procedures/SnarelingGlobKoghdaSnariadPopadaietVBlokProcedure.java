package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public class SnarelingGlobKoghdaSnariadPopadaietVBlokProcedure {
	public static void execute(LevelAccessor world, BlockPos hitPos, Direction hitFace) {
		BlockPos targetPos = hitPos.relative(hitFace);
		BlockState targetState = world.getBlockState(targetPos);
		if (targetState.canBeReplaced()) {
			world.setBlock(targetPos, EndElemetnModBlocks.SNARELINGWEB.get().defaultBlockState(), 3);
		}
	}
}
