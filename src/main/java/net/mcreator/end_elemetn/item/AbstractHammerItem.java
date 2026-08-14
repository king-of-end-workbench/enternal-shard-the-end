package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import java.util.List;

/**
 * Shared behavior for every hammer tier: mines pickaxe+axe+shovel blocks alike (a "hammer" isn't
 * just an axe or just a pickaxe), breaks a 3x3 area on the plane perpendicular to the player's
 * look direction, and unleashes a ground-slam AoE when the charged right-click completes.
 */
public abstract class AbstractHammerItem extends TieredItem {
	// Holding the slam without releasing charges it up in stages, each stage a little quicker to
	// reach than the last; the eventual slam's damage/radius/knockback and cooldown scale with how
	// many charge stages were actually completed.
	private static final int BASE_STAGE_TICKS = 20;
	private static final int STAGE_ACCEL_TICKS = 2;
	private static final int MIN_STAGE_TICKS = 10;
	private static final int MIN_HOLD_FOR_SLAM_TICKS = 10;
	private static final int SUPER_SLAM_CHARGE_THRESHOLD = 5;
	private static final float SUPER_SLAM_DAMAGE_MULTIPLIER = 1.5f;
	private static final float SUPER_SLAM_RADIUS_MULTIPLIER = 1.3f;
	private static final double SUPER_SLAM_KNOCKBACK_MULTIPLIER = 1.5;

	private final float mineSpeed;
	private final double slamRadius;
	private final float slamDamage;
	private final double slamKnockback;
	private final int slamCooldownTicks;
	private final int maxChargeCount;
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	// forge-1.20.1 has no Item.Properties().attributes(...) data component (later addition) - the
	// attack damage/speed attribute modifiers are built by hand here, mirroring what DiggerItem's own
	// constructor does internally.
	protected AbstractHammerItem(Tier tier, float attackDamage, float attackSpeed, float mineSpeed, double slamRadius, float slamDamage, double slamKnockback,
			int slamCooldownTicks, int maxChargeCount) {
		super(tier, new Item.Properties());
		this.mineSpeed = mineSpeed;
		this.slamRadius = slamRadius;
		this.slamDamage = slamDamage;
		this.slamKnockback = slamKnockback;
		this.slamCooldownTicks = slamCooldownTicks;
		this.maxChargeCount = maxChargeCount;
		float attackDamageBaseline = attackDamage + tier.getAttackDamageBonus();
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", attackDamageBaseline, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
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
		stack.hurtAndBreak(2, attacker, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
		return true;
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

	private int stageTicks(int stageIndex) {
		return Math.max(MIN_STAGE_TICKS, BASE_STAGE_TICKS - stageIndex * STAGE_ACCEL_TICKS);
	}

	private int totalChargeTicks() {
		int total = 0;
		for (int i = 0; i < maxChargeCount; i++)
			total += stageTicks(i);
		return total;
	}

	private int chargesCompletedAt(int ticksElapsed) {
		int cumulative = 0;
		int completed = 0;
		for (int i = 0; i < maxChargeCount; i++) {
			cumulative += stageTicks(i);
			if (ticksElapsed < cumulative)
				break;
			completed++;
		}
		return completed;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return totalChargeTicks();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
		int ticksUsed = totalChargeTicks() - timeLeft;
		int chargesReached = chargesCompletedAt(ticksUsed);
		if (chargesReached == 0 && ticksUsed >= MIN_HOLD_FOR_SLAM_TICKS) {
			chargesReached = 1;
		}
		if (chargesReached > 0) {
			groundSlam(world, entity, stack, chargesReached);
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		groundSlam(world, entity, stack, maxChargeCount);
		return stack;
	}

	private void groundSlam(Level world, LivingEntity entity, ItemStack stack, int chargesReached) {
		if (!(world instanceof ServerLevel level))
			return;
		if (entity instanceof Player player && player.getCooldowns().isOnCooldown(stack.getItem()))
			return;
		boolean superSlam = chargesReached >= SUPER_SLAM_CHARGE_THRESHOLD;
		double radius = slamRadius;
		float damage = slamDamage * chargesReached;
		double knockback = slamKnockback * chargesReached;
		if (superSlam) {
			radius *= SUPER_SLAM_RADIUS_MULTIPLIER;
			damage *= SUPER_SLAM_DAMAGE_MULTIPLIER;
			knockback *= SUPER_SLAM_KNOCKBACK_MULTIPLIER;
		}
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		DamageSource source = entity instanceof Player playerAttacker ? entity.damageSources().playerAttack(playerAttacker) : entity.damageSources().mobAttack(entity);
		List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(radius), e -> e != entity);
		for (LivingEntity target : nearby) {
			target.hurt(source, damage);
			target.knockback(knockback, target.getX() - x, target.getZ() - z);
		}
		level.playSound(null, x, y, z, SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.5f, 0.7f);
		level.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 1, 0, 0, 0, 0);
		level.sendParticles(ParticleTypes.CRIT, x, y + 0.2, z, 20, radius / 2, 0.1, radius / 2, 0.3);
		if (superSlam) {
			level.sendParticles(ParticleTypes.SONIC_BOOM, x, y + 0.5, z, 1, 0, 0, 0, 0);
			level.playSound(null, x, y, z, SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 1f, 0.8f);
		}
		if (entity instanceof Player player) {
			player.getCooldowns().addCooldown(stack.getItem(), slamCooldownTicks * chargesReached);
		}
	}
}
