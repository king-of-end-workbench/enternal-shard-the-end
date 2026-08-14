package net.mcreator.end_elemetn.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class EndstoneDustBlock extends FallingBlock {
	public EndstoneDustBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SAND).strength(1f, 10f).instrument(NoteBlockInstrument.SNARE));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}