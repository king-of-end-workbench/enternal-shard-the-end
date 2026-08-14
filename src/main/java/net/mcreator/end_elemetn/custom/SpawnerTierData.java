package net.mcreator.end_elemetn.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.init.EndElemetnModEntities;
import net.mcreator.end_elemetn.init.EndElemetnModItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Mob and reward pools for EndSpawnerBlockEntity's 3 tiers: 0 = Endstone (plainest, ordinary mobs),
 * 1 = End City (stronger, more variety), 2 = Soulful (crying mobs, best loot).
 */
public final class SpawnerTierData {
	private SpawnerTierData() {
	}

	public record WeightedMob(EntityType<?> type, int weight) {
	}

	public static final List<WeightedMob> ENDSTONE_MOBS = List.of(new WeightedMob(EndElemetnModEntities.WATCHLING.get(), 35),
			new WeightedMob(EndElemetnModEntities.SNARELING.get(), 30), new WeightedMob(EndElemetnModEntities.BLASTLING.get(), 25),
			new WeightedMob(EndElemetnModEntities.LURELING.get(), 10));

	public static final List<WeightedMob> ENDCITY_MOBS = List.of(new WeightedMob(EndElemetnModEntities.WATCHLING.get(), 20),
			new WeightedMob(EndElemetnModEntities.SNARELING.get(), 18), new WeightedMob(EndElemetnModEntities.BLASTLING.get(), 18),
			new WeightedMob(EndElemetnModEntities.LURELING.get(), 8), new WeightedMob(EndElemetnModEntities.NECROSENT.get(), 14),
			new WeightedMob(EndElemetnModEntities.CELESTIAN_GUARD.get(), 12), new WeightedMob(EndElemetnModEntities.SHADOWLING.get(), 10));

	public static final List<WeightedMob> SOULFUL_MOBS = List.of(new WeightedMob(EndElemetnModEntities.CRYING_WATCHLING.get(), 28),
			new WeightedMob(EndElemetnModEntities.CRYING_SNARELING.get(), 26), new WeightedMob(EndElemetnModEntities.CRYING_BLASTLING.get(), 26),
			new WeightedMob(EndElemetnModEntities.LURELING.get(), 10), new WeightedMob(EndElemetnModEntities.VENGEFUL_HEART_OF_ENDER.get(), 10));

	public static List<WeightedMob> mobsForTier(int tier) {
		return switch (tier) {
			case 2 -> SOULFUL_MOBS;
			case 1 -> ENDCITY_MOBS;
			default -> ENDSTONE_MOBS;
		};
	}

	public static EntityType<?> rollMob(List<WeightedMob> pool, RandomSource random) {
		int total = pool.stream().mapToInt(WeightedMob::weight).sum();
		int roll = random.nextInt(total);
		int accumulated = 0;
		for (WeightedMob entry : pool) {
			accumulated += entry.weight();
			if (roll < accumulated)
				return entry.type();
		}
		return pool.get(pool.size() - 1).type();
	}

	// Matches the vault's own 3 stages: an Endstone spawner drops Endstone Keys (opens an Endstone
	// vault), an End City spawner drops End City Keys, a Soulful spawner drops Soulful Keys.
	public static Item keyForTier(int tier) {
		return switch (tier) {
			case 2 -> EndElemetnModItems.END_SOULFUL_KEY.get();
			case 1 -> EndElemetnModItems.END_CITY_KEY.get();
			default -> EndElemetnModItems.ENDSTONE_KEY.get();
		};
	}

	// Food + a defense item + this tier's matching keys, popped out one at a time once every wave is
	// cleared. A generous RANDOM pick from a wider pool per tier, not a fixed list, so two spawners
	// of the same tier don't always drop the exact same things.
	private static final List<ItemStack> ENDSTONE_EXTRAS = List.of(new ItemStack(Items.BOW), new ItemStack(Items.ARROW, 16), new ItemStack(Items.SHIELD),
			new ItemStack(Items.COOKED_BEEF, 4), new ItemStack(Items.IRON_SWORD));
	private static final List<ItemStack> ENDCITY_EXTRAS = List.of(new ItemStack(Items.SHIELD), new ItemStack(Items.CROSSBOW), new ItemStack(Items.GOLDEN_CARROT, 4),
			PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_HEALING), PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.FIRE_RESISTANCE));
	private static final List<ItemStack> SOULFUL_EXTRAS = List.of(new ItemStack(Items.TOTEM_OF_UNDYING), PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.HARMING),
			PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_STRENGTH), new ItemStack(Items.NETHERITE_SCRAP));

	public static List<ItemStack> rewardsForTier(int tier, RandomSource random) {
		List<ItemStack> rewards = new ArrayList<>();
		List<ItemStack> extras = switch (tier) {
			case 2 -> SOULFUL_EXTRAS;
			case 1 -> ENDCITY_EXTRAS;
			default -> ENDSTONE_EXTRAS;
		};
		switch (tier) {
			case 2 -> rewards.add(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
			case 1 -> rewards.add(new ItemStack(Items.GOLDEN_APPLE));
			default -> rewards.add(new ItemStack(Items.GOLDEN_CARROT, 2));
		}
		int extraCount = 1 + random.nextInt(2);
		for (int i = 0; i < extraCount; i++)
			rewards.add(extras.get(random.nextInt(extras.size())).copy());
		int keyCount = tier == 2 ? 2 + random.nextInt(2) : tier == 1 ? 1 + random.nextInt(2) : 1;
		rewards.add(new ItemStack(keyForTier(tier), keyCount));
		return rewards;
	}
}
