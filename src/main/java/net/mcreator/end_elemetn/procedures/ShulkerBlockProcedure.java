package net.mcreator.end_elemetn.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.end_elemetn.item.ShulkerItem;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber
public class ShulkerBlockProcedure {
	private static final int DISABLE_TICKS = 100;
	private static final ResourceLocation BLOCK_SLOW_ID = ResourceLocation.fromNamespaceAndPath("end_elemetn", "shulker_block_slow");
	private static final Map<UUID, Long> BLOCKED_AT_TICK = new ConcurrentHashMap<>();

	private static boolean isBlocking(Player player) {
		if (!player.isShiftKeyDown())
			return false;
		ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
		if (!(helmet.getItem() instanceof ShulkerItem.Helmet))
			return false;
		return !player.getCooldowns().isOnCooldown(helmet.getItem());
	}

	@SubscribeEvent
	public static void onIncomingDamage(LivingIncomingDamageEvent event) {
		if (!(event.getEntity() instanceof Player player))
			return;
		if (player.level().isClientSide())
			return;
		if (!isBlocking(player))
			return;

		DamageSource source = event.getSource();
		if (source.is(DamageTypeTags.BYPASSES_SHIELD) || source.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
			return;

		event.setAmount(0.0F);
		BLOCKED_AT_TICK.put(player.getUUID(), player.level().getGameTime());

		ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
		Entity attackerEntity = source.getEntity();
		if (attackerEntity instanceof LivingEntity attacker && attacker.getMainHandItem().getItem() instanceof AxeItem) {
			player.getCooldowns().addCooldown(helmet.getItem(), DISABLE_TICKS);
			player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SHIELD_BREAK, SoundSource.PLAYERS, 1.0F, 0.8F);
		} else {
			player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0F, 1.0F);
		}
	}

	@SubscribeEvent
	public static void onKnockBack(LivingKnockBackEvent event) {
		if (!(event.getEntity() instanceof Player player))
			return;
		Long blockedTick = BLOCKED_AT_TICK.get(player.getUUID());
		if (blockedTick != null && blockedTick == player.level().getGameTime()) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onAttack(AttackEntityEvent event) {
		if (isBlocking(event.getEntity())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Pre event) {
		Player player = event.getEntity();
		if (player.level().isClientSide())
			return;
		AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);
		if (speed == null)
			return;
		boolean blocking = isBlocking(player);
		boolean hasModifier = speed.hasModifier(BLOCK_SLOW_ID);
		if (blocking && !hasModifier) {
			speed.addTransientModifier(new AttributeModifier(BLOCK_SLOW_ID, -0.99, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
		} else if (!blocking && hasModifier) {
			speed.removeModifier(BLOCK_SLOW_ID);
		}
	}
}
