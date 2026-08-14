package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * Shared behavior for every hammer tier: mines pickaxe+axe+shovel blocks alike (a "hammer" isn't
 * just an axe or just a pickaxe), breaks a 3x3 area on the plane perpendicular to the player's
 * look direction, and unleashes a ground-slam AoE when the charged right-click completes.
 */
public abstract class AbstractHammerItem extends TieredItem {
	private static final int SLAM_CHARGE_TICKS = 20;
	private static final int MIN_HOLD_FOR_SLAM_TICKS = 10;

	private final float mineSpeed;
	private final double slamRadius;
	private final float slamDamage;
	private final double slamKnockback;
	private final int slamCooldownTicks;

	protected AbstractHammerItem(Tier tier, float attackDamage, float attackSpeed, float mineSpeed, double slamRadius, float slamDamage, double slamKnockback,
			int slamCooldownTicks) {
		super(tier, new Item.Properties().attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed)));
		this.mineSpeed = mineSpeed;
		this.slamRadius = slamRadius;
		this.slamDamage = slamDamage;
		this.slamKnockback = slamKnockback;
		this.slamCooldownTicks = slamCooldownTicks;
	}

	// forge-1.20.1 has no data-driven Tool component (later addition) - a hammer that mines
	// pickaxe+axe+shovel blocks alike is done the old way, by overriding isCorrectToolForDrops/
	// getDestroySpeed directly instead of attaching a Tool.Rule list.
	@Override
	public boolean isCorrectToolForDrops(BlockState state) {
		int level = this.getTier().getLevel();
		if (level < 3 && state.is(BlockTags.NEEDS_DIAMOND_TOOL))
			return false;
		if (level < 2 && state.is(BlockTags.NEEDS_IRON_TOOL))
			return false;
		if (level < 1 && state.is(BlockTags.NEEDS_STONE_TOOL))
			return false;
		return state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
			return this.mineSpeed;
		}
		return super.getDestroySpeed(stack, state);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return true;
	}

	@Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
		boolean result = super.mineBlock(stack, level, state, pos, miner);
		if (!level.isClientSide && miner instanceof Player) {
			Vec3 look = miner.getLookAngle();
			Direction facing = Direction.getNearest(look.x, look.y, look.z);
			Direction.Axis axis = facing.getAxis();
			for (int a = -1; a <= 1; a++) {
				for (int b = -1; b <= 1; b++) {
					if (a == 0 && b == 0)
						continue;
					BlockPos extra = switch (axis) {
						case X -> pos.offset(0, a, b);
						case Y -> pos.offset(a, 0, b);
						case Z -> pos.offset(a, b, 0);
					};
					breakExtra(level, extra, miner, stack);
				}
			}
		}
		return result;
	}

	private void breakExtra(Level level, BlockPos pos, LivingEntity miner, ItemStack stack) {
		BlockState state = level.getBlockState(pos);
		if (state.isAir())
			return;
		if (state.getDestroySpeed(level, pos) < 0)
			return;
		if (state.requiresCorrectToolForDrops() && !stack.isCorrectToolForDrops(state))
			return;
		level.levelEvent(2001, pos, Block.getId(state));
		Block.dropResources(state, level, pos, level.getBlockEntity(pos), miner, stack);
		level.removeBlock(pos, false);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return InteractionResultHolder.consume(entity.getItemInHand(hand));
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return SLAM_CHARGE_TICKS;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
		int ticksUsed = SLAM_CHARGE_TICKS - timeLeft;
		if (ticksUsed >= MIN_HOLD_FOR_SLAM_TICKS) {
			groundSlam(world, entity, stack);
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		groundSlam(world, entity, stack);
		return stack;
	}

	private void groundSlam(Level world, LivingEntity entity, ItemStack stack) {
		if (!(world instanceof ServerLevel level))
			return;
		if (entity instanceof Player player && player.getCooldowns().isOnCooldown(stack.getItem()))
			return;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		DamageSource source = entity instanceof Player playerAttacker ? entity.damageSources().playerAttack(playerAttacker) : entity.damageSources().mobAttack(entity);
		List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(slamRadius), e -> e != entity);
		for (LivingEntity target : nearby) {
			target.hurt(source, slamDamage);
			target.knockback(slamKnockback, target.getX() - x, target.getZ() - z);
		}
		level.playSound(null, x, y, z, SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.5f, 0.7f);
		level.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 1, 0, 0, 0, 0);
		level.sendParticles(ParticleTypes.CRIT, x, y + 0.2, z, 20, slamRadius / 2, 0.1, slamRadius / 2, 0.3);
		if (entity instanceof Player player) {
			player.getCooldowns().addCooldown(stack.getItem(), slamCooldownTicks);
		}
	}
}
