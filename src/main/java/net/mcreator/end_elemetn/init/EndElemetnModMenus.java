/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.end_elemetn.world.inventory.TradelingGUIMenu;
import net.mcreator.end_elemetn.world.inventory.TradeMenu;
import net.mcreator.end_elemetn.world.inventory.JellyGuiMenu;
import net.mcreator.end_elemetn.network.MenuStateUpdateMessage;
import net.mcreator.end_elemetn.EndElemetnMod;

import java.util.Map;

public class EndElemetnModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, EndElemetnMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<TradeMenu>> TRADE = REGISTRY.register("trade", () -> IMenuTypeExtension.create(TradeMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<JellyGuiMenu>> JELLY_GUI = REGISTRY.register("jelly_gui", () -> IMenuTypeExtension.create(JellyGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<TradelingGUIMenu>> TRADELING_GUI = REGISTRY.register("tradeling_gui", () -> IMenuTypeExtension.create(TradelingGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof EndElemetnModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				PacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}