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
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.block.entity.UnstableHoleInRealityBlockEntity;
import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class UnstableHoleInRealityBlock extends Block implements EntityBlock, Portal {
	public UnstableHoleInRealityBlock() {
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
		return new UnstableHoleInRealityBlockEntity(pos, state);
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

		for (int i = 0; i < 12; i++) {
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
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.is(EndElemetnModItems.EYE_OF_TELEPORTATION.get())) {
			if (!world.isClientSide && world instanceof ServerLevel serverLevel) {
				serverLevel.sendParticles(ParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 30, 0.5, 0.5, 0.5, 0.3);
				serverLevel.playSound(null, pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
				world.removeBlock(pos, false);
				if (world.getRandom().nextFloat() < 0.1F)
					world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(EndElemetnModItems.SHARD_OF_EYE_OF_TELEPORTATION.get())));
				if (!player.getAbilities().instabuild)
					stack.shrink(1);
			}
			return ItemInteractionResult.sidedSuccess(world.isClientSide);
		}
		return super.useItemOn(stack, state, world, pos, player, hand, hitResult);
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

		RandomSource random = level.getRandom();
		WorldBorder border = destLevel.getWorldBorder();
		int x, z;
		if (destKey == Level.OVERWORLD && random.nextInt(10) == 0) {
			int beyond = 500 + random.nextInt(2000);
			x = (int) (random.nextBoolean() ? border.getMaxX() + beyond : border.getMinX() - beyond);
			z = (int) (random.nextBoolean() ? border.getMaxZ() + beyond : border.getMinZ() - beyond);
		} else {
			int margin = 16;
			int minX = (int) Math.max(-100000, Math.floor(border.getMinX()) + margin);
			int maxX = (int) Math.min(100000, Math.ceil(border.getMaxX()) - margin);
			int minZ = (int) Math.max(-100000, Math.floor(border.getMinZ()) + margin);
			int maxZ = (int) Math.min(100000, Math.ceil(border.getMaxZ()) - margin);
			x = minX + random.nextInt(Math.max(1, maxX - minX));
			z = minZ + random.nextInt(Math.max(1, maxZ - minZ));
		}
		BlockPos destPos = new BlockPos(x, 40 + random.nextInt(80), z);
		ensureSafeLanding(destLevel, destPos);
		Vec3 vec3 = new Vec3(destPos.getX() + 0.5, destPos.getY(), destPos.getZ() + 0.5);

		return new DimensionTransition(destLevel, vec3, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(),
				DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET));
	}

	protected void ensureSafeLanding(ServerLevel level, BlockPos pos) {
		BlockPos below = pos.below();
		if (level.getBlockState(below).isAir())
			level.setBlockAndUpdate(below, Blocks.OBSIDIAN.defaultBlockState());
	}
}
