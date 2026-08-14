package net.mcreator.end_elemetn.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

import net.minecraftforge.common.util.ITeleporter;

import net.mcreator.end_elemetn.block.entity.UnstableHoleInRealityBlockEntity;
import net.mcreator.end_elemetn.init.EndElemetnModItems;

import java.util.function.Function;

public class UnstableHoleInRealityBlock extends Block implements EntityBlock {
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
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		ItemStack stack = player.getItemInHand(hand);
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
			return InteractionResult.sidedSuccess(world.isClientSide);
		}
		return super.use(state, world, pos, player, hand, hitResult);
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

		RandomSource random = world.getRandom();
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
		Vec3 dest = new Vec3(destPos.getX() + 0.5, destPos.getY(), destPos.getZ() + 0.5);
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
