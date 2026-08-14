package net.mcreator.end_elemetn.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class Proc3Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (new ItemStack(EndElemetnModItems.TITANIUM_COIN.get()).getCount() == 64) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(EndElemetnModItems.TITANIUM_COIN.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 64, _player.inventoryMenu.getCraftSlots());
			}
			{
				final int _slotid = 1;
				final ItemStack _setstack = new ItemStack(EndElemetnModItems.ENDERITE_LOOTBOX.get()).copy();
				_setstack.setCount(1);
				entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
						_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
				});
			}
		}
	}
}