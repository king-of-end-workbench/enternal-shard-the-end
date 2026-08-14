package net.mcreator.end_elemetn.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class Proc2Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (new ItemStack(EndElemetnModItems.TITANIUM_COIN.get()).getCount() >= 32) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(EndElemetnModItems.TITANIUM_COIN.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 32, _player.inventoryMenu.getCraftSlots());
			}
			if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandler) {
				ItemStack _setstack = new ItemStack(EndElemetnModItems.TITANIUM_LOOTBOX.get()).copy();
				_setstack.setCount(1);
				_modHandler.setStackInSlot(1, _setstack);
			}
		}
	}
}