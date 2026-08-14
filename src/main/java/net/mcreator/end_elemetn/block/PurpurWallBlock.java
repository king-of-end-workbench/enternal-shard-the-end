package net.mcreator.end_elemetn.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class PurpurWallBlock extends WallBlock {
	public PurpurWallBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).forceSolidOn());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}