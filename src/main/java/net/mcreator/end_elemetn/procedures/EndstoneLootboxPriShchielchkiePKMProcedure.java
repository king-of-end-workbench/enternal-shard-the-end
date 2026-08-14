package net.mcreator.end_elemetn.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class EndstoneLootboxPriShchielchkiePKMProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (Math.random() < 0.6) {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Blocks.END_STONE).copy();
				_setstack.setCount(3);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (Math.random() < 0.5) {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Blocks.CHORUS_FLOWER).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (Math.random() < 0.45) {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(EndElemetnModItems.RAW_TITANIUM.get()).copy();
				_setstack.setCount(Mth.nextInt(RandomSource.create(), 1, 3));
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (Math.random() < 0.2) {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(EndElemetnModItems.ENDERITE.get()).copy();
				_setstack.setCount(Mth.nextInt(RandomSource.create(), 1, 3));
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (Math.random() < 0.15) {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Items.SHULKER_SHELL).copy();
				_setstack.setCount(Mth.nextInt(RandomSource.create(), 1, 3));
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (Math.random() < 0.01) {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(EndElemetnModItems.WEAPON_SMITHING_TEMPLATE.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Items.AMETHYST_SHARD).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}