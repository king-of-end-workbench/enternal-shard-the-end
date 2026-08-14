/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import net.mcreator.end_elemetn.world.inventory.TradelingGUIMenu;
import net.mcreator.end_elemetn.world.inventory.TradeMenu;
import net.mcreator.end_elemetn.world.inventory.JellyGuiMenu;
import net.mcreator.end_elemetn.network.MenuStateUpdateMessage;
import net.mcreator.end_elemetn.EndElemetnMod;

import java.util.Map;

public class EndElemetnModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EndElemetnMod.MODID);
	public static final RegistryObject<MenuType<TradeMenu>> TRADE = REGISTRY.register("trade", () -> IForgeMenuType.create(TradeMenu::new));
	public static final RegistryObject<MenuType<JellyGuiMenu>> JELLY_GUI = REGISTRY.register("jelly_gui", () -> IForgeMenuType.create(JellyGuiMenu::new));
	public static final RegistryObject<MenuType<TradelingGUIMenu>> TRADELING_GUI = REGISTRY.register("tradeling_gui", () -> IForgeMenuType.create(TradelingGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				EndElemetnMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof EndElemetnModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				EndElemetnMod.PACKET_HANDLER.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
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