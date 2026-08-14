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
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;

/**
 * A vanilla-correct bow (real ammo lookup/consumption, Infinity/Power/Punch/Flame all actually
 * work, guaranteed crit on full draw) that also rewards a full draw with a "void shot": a brief
 * Levitation proc (and the matching particle trail tipped arrows get for free) plus bonus damage.
 */
public class EnternalBowItem extends Item {
	private static final float FULL_DRAW_BONUS_DAMAGE = 2.0f;

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

		Arrow projectile = new Arrow(world, entity);
		projectile.setEffectsFromItem(ammoForArrow);
		projectile.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0, pullingPower * 3.15f, 1.0F);

		boolean fullDraw = pullingPower >= 1.0f;
		if (fullDraw) {
			projectile.setCritArrow(true);
			projectile.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 0));
			projectile.setBaseDamage(projectile.getBaseDamage() + FULL_DRAW_BONUS_DAMAGE);
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