package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.end_elemetn.init.EndElemetnModMenus;
import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class Loot3Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof EndElemetnModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == EndElemetnModItems.TITANIUM_COIN.get()
				&& getAmountInGUISlot(entity, 0) >= 64) {
			if (entity instanceof Player _player && _player.containerMenu instanceof EndElemetnModMenus.MenuAccessor _menu) {
				ItemStack _setstack3 = new ItemStack(EndElemetnModItems.ENDERITE_LOOTBOX.get()).copy();
				_setstack3.setCount(1);
				_menu.getSlots().get(1).set(_setstack3);
				_player.containerMenu.broadcastChanges();
			}
		}
	}

	private static int getAmountInGUISlot(Entity entity, int sltid) {
		if (entity instanceof Player player && player.containerMenu instanceof EndElemetnModMenus.MenuAccessor menuAccessor) {
			ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
			if (stack != null)
				return stack.getCount();
		}
		return 0;
	}
}