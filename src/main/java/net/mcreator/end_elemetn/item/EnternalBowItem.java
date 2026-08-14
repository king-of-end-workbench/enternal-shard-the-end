package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

/**
 * A vanilla-correct bow (real ammo lookup/consumption, Infinity/Power/Punch/Flame all actually
 * work, guaranteed crit on full draw) that also rewards a full draw with a "void line" perk: the
 * arrow flies at greatly boosted speed and leaves a lingering line of void energy along its
 * flight path (tracked by VoidLineManager) that damages any non-player entity touching it for a
 * few seconds after the shot.
 */
public class EnternalBowItem extends Item {
	private static final float NORMAL_VELOCITY_MULTIPLIER = 3.15f;
	private static final float VOID_SHOT_VELOCITY_MULTIPLIER = 6.0f;

	public EnternalBowItem() {
		super(new Item.Properties().durability(1000));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BOW;
	}

	@Override
	public int getEnchantmentValue() {
		return 6;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 72000;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = InteractionResultHolder.fail(entity.getItemInHand(hand));
		if (entity.getAbilities().instabuild || !findAmmo(entity).isEmpty()) {
			ar = InteractionResultHolder.success(entity.getItemInHand(hand));
			entity.startUsingItem(hand);
		}
		return ar;
	}

	@Override
	public void onUseTick(Level world, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
		if (!(world instanceof ServerLevel serverLevel))
			return;
		int ticksUsed = this.getUseDuration(stack) - remainingUseDuration;
		if (ticksUsed % 2 != 0)
			return;
		Vec3 origin = entity.getEyePosition().add(entity.getLookAngle().scale(0.6));
		serverLevel.sendParticles(ParticleTypes.REVERSE_PORTAL, origin.x, origin.y, origin.z, 3, 0.15, 0.15, 0.15, 0.02);
	}

	@Override
	public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
		if (world.isClientSide() || !(entity instanceof ServerPlayer player))
			return;
		float pullingPower = BowItem.getPowerForTime(this.getUseDuration(itemstack) - time);
		if (pullingPower < 0.1)
			return;
		ItemStack stack = findAmmo(player);
		if (!player.getAbilities().instabuild && stack.isEmpty())
			return;
		boolean infinite = player.getAbilities().instabuild
				|| (stack.getItem() instanceof ArrowItem arrowItem && arrowItem.isInfinite(stack, itemstack, player));
		ItemStack ammoForArrow = stack.isEmpty() ? new ItemStack(Items.ARROW) : stack;
		boolean fullDraw = pullingPower >= 1.0f;
		float velocityMultiplier = fullDraw ? VOID_SHOT_VELOCITY_MULTIPLIER : NORMAL_VELOCITY_MULTIPLIER;

		Arrow projectile = new Arrow(world, entity);
		projectile.setEffectsFromItem(ammoForArrow);
		projectile.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0, pullingPower * velocityMultiplier, 1.0F);

		if (fullDraw) {
			projectile.setCritArrow(true);
			VoidLineManager.track(projectile);
		}

		int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, itemstack);
		if (powerLevel > 0) {
			projectile.setBaseDamage(projectile.getBaseDamage() + powerLevel * 0.5 + 0.5);
		}
		int punchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, itemstack);
		if (punchLevel > 0) {
			projectile.setKnockback(punchLevel);
		}
		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemstack) > 0) {
			projectile.setSecondsOnFire(100);
		}

		world.addFreshEntity(projectile);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1f,
				1f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + pullingPower * 0.5f);
		itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));

		if (player.getAbilities().instabuild) {
			projectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
		} else if (!infinite) {
			stack.shrink(1);
			if (stack.isEmpty())
				player.getInventory().removeItem(stack);
		}
		player.awardStat(Stats.ITEM_USED.get(this));
	}

	private ItemStack findAmmo(Player player) {
		if (isAmmo(player.getItemInHand(InteractionHand.OFF_HAND)))
			return player.getItemInHand(InteractionHand.OFF_HAND);
		if (isAmmo(player.getItemInHand(InteractionHand.MAIN_HAND)))
			return player.getItemInHand(InteractionHand.MAIN_HAND);
		for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
			ItemStack candidate = player.getInventory().getItem(i);
			if (isAmmo(candidate))
				return candidate;
		}
		return ItemStack.EMPTY;
	}

	private boolean isAmmo(ItemStack stack) {
		return stack.getItem() instanceof ArrowItem;
	}
}