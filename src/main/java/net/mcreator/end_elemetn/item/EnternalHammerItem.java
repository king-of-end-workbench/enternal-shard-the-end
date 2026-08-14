package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;

import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class EnternalHammerItem extends AbstractHammerItem {
	private static final float MINE_SPEED = 7f;

	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 1000;
		}

		@Override
		public float getSpeed() {
			return MINE_SPEED;
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

	public EnternalHammerItem() {
		super(TOOL_TIER, 26f, -4f, MINE_SPEED, 5.0, 18f, 1.2, 90);
	}
}
