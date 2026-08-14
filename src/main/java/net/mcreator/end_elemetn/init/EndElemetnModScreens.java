/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.end_elemetn.client.gui.TradelingGUIScreen;
import net.mcreator.end_elemetn.client.gui.TradeScreen;
import net.mcreator.end_elemetn.client.gui.JellyGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EndElemetnModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(EndElemetnModMenus.TRADE.get(), TradeScreen::new);
			MenuScreens.register(EndElemetnModMenus.JELLY_GUI.get(), JellyGuiScreen::new);
			MenuScreens.register(EndElemetnModMenus.TRADELING_GUI.get(), TradelingGUIScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}