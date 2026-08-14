package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.end_elemetn.init.EndElemetnModMenus;
import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class OutputTredelingGUIProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof EndElemetnModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == EndElemetnModItems.ENDSTONE_LOOTBOX.get()) {
			if (entity instanceof Player _player && _player.containerMenu instanceof EndElemetnModMenus.MenuAccessor _menu) {
				_menu.getSlots().get(0).remove(16);
				_player.containerMenu.broadcastChanges();
			}
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof EndElemetnModMenus.MenuAccessor _menu3 ? _menu3.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == EndElemetnModItems.TITANIUM_LOOTBOX
				.get()) {
			if (entity instanceof Player _player && _player.containerMenu instanceof EndElemetnModMenus.MenuAccessor _menu) {
				_menu.getSlots().get(0).remove(32);
				_player.containerMenu.broadcastChanges();
			}
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof EndElemetnModMenus.MenuAccessor _menu6 ? _menu6.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == EndElemetnModItems.ENDERITE_LOOTBOX
				.get()) {
			if (entity instanceof Player _player && _player.containerMenu instanceof EndElemetnModMenus.MenuAccessor _menu) {
				_menu.getSlots().get(0).remove(64);
				_player.containerMenu.broadcastChanges();
			}
		}
	}
}