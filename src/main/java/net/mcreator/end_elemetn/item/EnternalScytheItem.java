package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;

import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class EnternalScytheItem extends AbstractScytheItem {
	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 1000;
		}

		@Override
		public float getSpeed() {
			return 8f;
		}

		@Override
		public float getAttackDamageBonus() {
			return 0;
		}

		@Override
		public int getLevel() {
			return 4;
		}

		@Override
		public int getEnchantmentValue() {
			return 6;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.of(new ItemStack(EndElemetnModItems.ENDERITE.get()));
		}
	};

	public EnternalScytheItem() {
		super(TOOL_TIER, 19f, -2.8f, 3.0, 8f, 7.0, 22f, 100);
	}
}
