package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class FullEndBottleItem extends Item {
	public FullEndBottleItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
	}
}