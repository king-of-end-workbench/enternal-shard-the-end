package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public class CornStage1UsloviieUspieshnoghoPrimienieniiaKostnoiMukiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < 0.7) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(x, y, z), EndElemetnModBlocks.CORN_STAGE_2.get().defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(x, y + 1, z), (blockStateWithEnum(EndElemetnModBlocks.CORN_STAGE_2.get().defaultBlockState(), "half", "upper")), 3);
		} else {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(x, y, z), EndElemetnModBlocks.CORN_STAGE_3.get().defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(x, y + 1, z), (blockStateWithEnum(EndElemetnModBlocks.CORN_STAGE_3.get().defaultBlockState(), "half", "upper")), 3);
		}
	}

	private static BlockState blockStateWithEnum(BlockState blockState, String property, String newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof EnumProperty ep && ep.getValue(newValue).isPresent() ? blockState.setValue(ep, (Enum) ep.getValue(newValue).get()) : blockState;
	}
}