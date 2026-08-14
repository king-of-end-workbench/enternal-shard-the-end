package net.mcreator.end_elemetn.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

import net.minecraftforge.common.util.ITeleporter;

import net.mcreator.end_elemetn.block.entity.HoleInRealityBlockEntity;

import java.util.function.Function;

public class HoleInRealityBlock extends Block implements EntityBlock {
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
		if (world.isClientSide || !(world instanceof ServerLevel serverLevel) || !entity.canChangeDimensions())
			return;
		if (!Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox().move(-pos.getX(), -pos.getY(), -pos.getZ())), Shapes.block(), BooleanOp.AND))
			return;

		ResourceKey<Level> destKey = world.dimension() == Level.END ? Level.OVERWORLD : Level.END;
		ServerLevel destLevel = serverLevel.getServer().getLevel(destKey);
		if (destLevel == null)
			return;

		ensureSafeLanding(destLevel, pos);
		Vec3 dest = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		ITeleporter teleporter = new ITeleporter() {
			@Override
			public PortalInfo getPortalInfo(Entity teleportingEntity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
				return new PortalInfo(dest, teleportingEntity.getDeltaMovement(), teleportingEntity.getYRot(), teleportingEntity.getXRot());
			}
		};

		if (entity instanceof ServerPlayer player) {
			player.changeDimension(destLevel, teleporter);
		} else {
			entity.changeDimension(destLevel, teleporter);
		}
		world.playSound(null, pos, SoundEvents.PORTAL_TRAVEL, SoundSource.BLOCKS, 1.0F, 1.0F);
		world.removeBlock(pos, false);
	}

	protected void ensureSafeLanding(ServerLevel level, BlockPos pos) {
		BlockPos below = pos.below();
		if (level.getBlockState(below).isAir())
			level.setBlockAndUpdate(below, Blocks.OBSIDIAN.defaultBlockState());
	}
}
