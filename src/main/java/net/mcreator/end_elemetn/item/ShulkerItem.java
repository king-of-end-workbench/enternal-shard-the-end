package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.Util;

import net.mcreator.end_elemetn.procedures.ShulkerKazhdyiTikDliaShliemaProcedure;

import java.util.Map;
import java.util.EnumMap;

import com.google.common.collect.Iterables;

public abstract class ShulkerItem extends ArmorItem {
	// forge-1.20.1's ArmorMaterial is a plain interface (no registry) - the 1.21 version's
	// registry-based ArmorMaterial record + RegisterEvent doesn't exist here, so this is just
	// instantiated directly instead of registered.
	public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial() {
		private final Map<ArmorItem.Type, Integer> DEFENSE = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.BOOTS, 2);
			map.put(ArmorItem.Type.LEGGINGS, 5);
			map.put(ArmorItem.Type.CHESTPLATE, 6);
			map.put(ArmorItem.Type.HELMET, 2);
		});

		@Override
		public int getDurabilityForType(ArmorItem.Type type) {
			return durabilityFor(type);
		}

		@Override
		public int getDefenseForType(ArmorItem.Type type) {
			return DEFENSE.getOrDefault(type, 0);
		}

		@Override
		public int getEnchantmentValue() {
			return 9;
		}

		@Override
		public SoundEvent getEquipSound() {
			return SoundEvents.EMPTY;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.of(Items.SHULKER_SHELL);
		}

		@Override
		public String getName() {
			return "end_elemetn:shulker";
		}

		@Override
		public float getToughness() {
			return 0f;
		}

		@Override
		public float getKnockbackResistance() {
			return 0f;
		}
	};

	// forge-1.20.1's ArmorItem.Type has no getDurability(int) multiplier method (later addition) -
	// these are vanilla's own per-slot base durabilities (see ArmorMaterials) times a x25 multiplier,
	// matching what the 1.21 API's type.getDurability(25) used to compute.
	private static int durabilityFor(ArmorItem.Type type) {
		return switch (type) {
			case HELMET -> 11 * 25;
			case CHESTPLATE -> 16 * 25;
			case LEGGINGS -> 15 * 25;
			case BOOTS -> 13 * 25;
		};
	}

	public ShulkerItem(ArmorItem.Type type, Item.Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

	public static class Helmet extends ShulkerItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().durability(durabilityFor(ArmorItem.Type.HELMET)));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				ShulkerKazhdyiTikDliaShliemaProcedure.execute(entity);
			}
		}
	}

	public static class Chestplate extends ShulkerItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(durabilityFor(ArmorItem.Type.CHESTPLATE)));
		}
	}

	public static class Leggings extends ShulkerItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(durabilityFor(ArmorItem.Type.LEGGINGS)));
		}
	}

	public static class Boots extends ShulkerItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().durability(durabilityFor(ArmorItem.Type.BOOTS)));
		}
	}
}
