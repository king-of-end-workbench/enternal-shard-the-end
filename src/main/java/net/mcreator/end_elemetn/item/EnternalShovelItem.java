package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class EnternalShovelItem extends ShovelItem {
	public EnternalShovelItem() {
		super(new Tier() {
			public int getUses() {
				return 100;
			}

			public float getSpeed() {
				return 14.5f;
			}

			public float getAttackDamageBonus() {
				return 10f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(EndElemetnModItems.ENDERITE.get()));
			}
		}, 1, -3f, new Item.Properties());
	}
}