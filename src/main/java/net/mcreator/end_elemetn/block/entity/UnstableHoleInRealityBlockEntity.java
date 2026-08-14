package net.mcreator.end_elemetn.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

public class UnstableHoleInRealityBlockEntity extends TheEndPortalBlockEntity {
	public UnstableHoleInRealityBlockEntity(BlockPos pos, BlockState state) {
		super(EndElemetnModBlockEntities.UNSTABLE_HOLE_IN_REALITY.get(), pos, state);
	}

	@Override
	public boolean shouldRenderFace(Direction direction) {
		return Block.shouldRenderFace(this.getBlockState(), this.level, this.getBlockPos(), direction, this.getBlockPos().relative(direction));
	}
}
