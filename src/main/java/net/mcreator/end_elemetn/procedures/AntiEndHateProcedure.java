package net.mcreator.end_elemetn.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

@EventBusSubscriber
public class AntiEndHateProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity www = null;
		Entity etrg = null;
		if (entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive()) {
			if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("neoforge:end_entity"))) && (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) instanceof Player) {
				www = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
				if (((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("end_elemetn:ender_vision")))) != 0) {
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "data merge entity @s {AngerTime:0, AngryAt:[]}");
						}
					}
					if (entity instanceof Mob _entity)
						_entity.setTarget(null);
					entity.getPersistentData().putDouble("AngerTime", 0);
					if (entity instanceof Mob _entity)
						_entity.getNavigation().stop();
					if (world instanceof ServerLevel _level) {
						(www instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
						});
					}
				} else if (((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getDisplayName().getString()).equals("King_of end__")) {
					entity.getPersistentData().putDouble("AngerTime", 0);
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "data merge entity @s {AngerTime:0, AngryAt:[]}");
						}
					}
					if (entity instanceof Mob _entity)
						_entity.setTarget(null);
					if (entity instanceof Mob _entity)
						_entity.getNavigation().stop();
				}
			}
		}
	}
}