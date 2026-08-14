package net.mcreator.end_elemetn.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.block.entity.HoleInRealityBlockEntity;

public class HoleInRealityBlock extends Block implements EntityBlock, Portal {
	public HoleInRealityBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GRAVEL).strength(-1, 3600000).noCollission());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HoleInRealityBlockEntity(pos, state);
	}

	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		super.onPlace(state, world, pos, oldState, movedByPiston);
		if (!oldState.is(state.getBlock()))
			world.playSound(null, pos, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 1.0F, 1.0F);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
		if (random.nextInt(100) == 0)
			world.playLocalSound((double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);

		for (int i = 0; i < 4; i++) {
			double d0 = (double) pos.getX() + random.nextDouble();
			double d1 = (double) pos.getY() + random.nextDouble();
			double d2 = (double) pos.getZ() + random.nextDouble();
			double d3 = (random.nextFloat() - 0.5) * 0.5;
			double d4 = (random.nextFloat() - 0.5) * 0.5;
			double d5 = (random.nextFloat() - 0.5) * 0.5;
			world.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity.canUsePortal(false) && Shapes.joinIsNotEmpty(
				Shapes.create(entity.getBoundingBox().move((double) (-pos.getX()), (double) (-pos.getY()), (double) (-pos.getZ()))),
				state.getShape(world, pos), BooleanOp.AND)) {
			if (!world.isClientSide)
				world.scheduleTick(pos, this, 4);
			entity.setAsInsidePortal(this, pos);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		if (state.is(this)) {
			world.playSound(null, pos, SoundEvents.PORTAL_TRAVEL, SoundSource.BLOCKS, 1.0F, 1.0F);
			world.removeBlock(pos, false);
		}
	}

	@Override
	public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
		ResourceKey<Level> destKey = level.dimension() == Level.END ? Level.OVERWORLD : Level.END;
		ServerLevel destLevel = level.getServer().getLevel(destKey);
		if (destLevel == null)
			return null;

		ensureSafeLanding(destLevel, pos);
		Vec3 vec3 = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

		return new DimensionTransition(destLevel, vec3, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(),
				DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET));
	}

	protected void ensureSafeLanding(ServerLevel level, BlockPos pos) {
		BlockPos below = pos.below();
		if (level.getBlockState(below).isAir())
			level.setBlockAndUpdate(below, Blocks.OBSIDIAN.defaultBlockState());
	}
}