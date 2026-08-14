package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class CornItem extends Item {
	public CornItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6f).build()));
	}
}