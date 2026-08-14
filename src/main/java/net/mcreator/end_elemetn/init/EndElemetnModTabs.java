/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.EndElemetnMod;

@EventBusSubscriber
public class EndElemetnModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndElemetnMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(EndElemetnModItems.SHULKER_HELMET.get());
			tabData.accept(EndElemetnModItems.SHULKER_CHESTPLATE.get());
			tabData.accept(EndElemetnModItems.SHULKER_LEGGINGS.get());
			tabData.accept(EndElemetnModItems.SHULKER_BOOTS.get());
			tabData.accept(EndElemetnModItems.ENDERITE_HELMET.get());
			tabData.accept(EndElemetnModItems.ENDERITE_CHESTPLATE.get());
			tabData.accept(EndElemetnModItems.ENDERITE_LEGGINGS.get());
			tabData.accept(EndElemetnModItems.ENDERITE_BOOTS.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_SWORD.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_AXE.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_SCYTHE.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_BOW.get());
			tabData.accept(EndElemetnModItems.BLASTLING_SHOOT.get());
			tabData.accept(EndElemetnModItems.FUSELING.get());
			tabData.accept(EndElemetnModItems.CORRUPTED_BEACON.get());
			tabData.accept(EndElemetnModItems.WOODEN_HAMMER.get());
			tabData.accept(EndElemetnModItems.STONE_HAMMER.get());
			tabData.accept(EndElemetnModItems.IRON_HAMMER.get());
			tabData.accept(EndElemetnModItems.GOLDEN_HAMMER.get());
			tabData.accept(EndElemetnModItems.DIAMOND_HAMMER.get());
			tabData.accept(EndElemetnModItems.NETHERITE_HAMMER.get());
			tabData.accept(EndElemetnModItems.WOODEN_SCYTH.get());
			tabData.accept(EndElemetnModItems.STONE_SCYTH.get());
			tabData.accept(EndElemetnModItems.IRON_SCYTH.get());
			tabData.accept(EndElemetnModItems.GOLDEN_SCYTH.get());
			tabData.accept(EndElemetnModItems.DIAMOND_SCYTH.get());
			tabData.accept(EndElemetnModItems.NETHERITE_SCYTH.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(EndElemetnModBlocks.END_COBBELSTONE.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_MESHFADES_STONE.get().asItem());
			tabData.accept(EndElemetnModBlocks.GREEN_END_STONE.get().asItem());
			tabData.accept(EndElemetnModBlocks.GREEN_END_COBBLESTONE.get().asItem());
			tabData.accept(EndElemetnModBlocks.DIRTY_END_COBBLESTONE.get().asItem());
			tabData.accept(EndElemetnModBlocks.CRACKED_GREEN_ENDCOBBLESTONE.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDWILD_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_LEAVES.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_FERN.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_TALL_GRESS.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_WILD_CORAL.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDSTONE_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDSTONE_BLOSSOM_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_VINES.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_MUD_BLOCK.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_WILD_TREE_SAPLING.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRY_CHORUS_FLOWER.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRY_CHORUS_PLANT.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_MESH_PICK.get().asItem());
			tabData.accept(EndElemetnModBlocks.MUDDY_ENDCOBBLESTONE.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDSTONE_DUST.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_WILD_FLOWER.get().asItem());
			tabData.accept(EndElemetnModBlocks.VOID_BLOCK.get().asItem());
			tabData.accept(EndElemetnModBlocks.VOID_FLAME.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_WARPED_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_LEAVES.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_FLOOR_ROOTS.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_LEAVES.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_VINES.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_BLOSSOM_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_PLANT.get().asItem());
			tabData.accept(EndElemetnModBlocks.TALL_CHORUS_PLANT.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_SHROOM.get().asItem());
			tabData.accept(EndElemetnModBlocks.TALL_CHORUS_BLOSSOM_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.METEOR_DEBRIS.get().asItem());
			tabData.accept(EndElemetnModBlocks.SHADOW_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.PILLARS_OF_SHADOW_WOOD.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_LEAVES.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(EndElemetnModBlocks.ENDER_WOOD.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_LOG.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_PLANKS.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_STAIRS.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_SLAB.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_FENCE.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_FENCE_GATE.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_PRESSURE_PLATE.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_BUTTON.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_WOOD_STAIRS.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_DOOR.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDER_TRAPDOOR.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_LOG.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_WOOD.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_PLANKS.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_STAIRS.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_SLAB.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_FENCE.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_FENCE_GATE.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_PRESSURE_PLATE.get().asItem());
			tabData.accept(EndElemetnModBlocks.WARPED_BUTTON.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_LOG.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_WOOD.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_PLANKS.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_STAIRS.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_SLAB.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_FENCE.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_FENCE_GATE.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_PRESSURE_PLATE.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHORUS_BUTTON.get().asItem());
			tabData.accept(EndElemetnModBlocks.CRACKED_ENDSTONE_BRICKS.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHISELED_ENDSTONE_BRICKS.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDSTONE_TILES.get().asItem());
			tabData.accept(EndElemetnModBlocks.POLISHED_ENDSTONE.get().asItem());
			tabData.accept(EndElemetnModBlocks.ENDSTONE_PILLAR.get().asItem());
			tabData.accept(EndElemetnModBlocks.ARCH_ENDSTONE_PILLAR.get().asItem());
			tabData.accept(EndElemetnModBlocks.SMOOTH_PURPUR.get().asItem());
			tabData.accept(EndElemetnModBlocks.CRACKED_PURPUR.get().asItem());
			tabData.accept(EndElemetnModBlocks.PURPUR_TILES.get().asItem());
			tabData.accept(EndElemetnModBlocks.CUBET_PURPUR_TILES.get().asItem());
			tabData.accept(EndElemetnModBlocks.CHISELED_PURPUR.get().asItem());
			tabData.accept(EndElemetnModBlocks.PURPUR_WALL.get().asItem());
			tabData.accept(EndElemetnModBlocks.TITANIUM_BLOCK.get().asItem());
			tabData.accept(EndElemetnModBlocks.UNKNOWN_BLOCK.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_LOG.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_WOOD.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_PLANKS.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_STAIRS.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_SLAB.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_FENCE.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_FENCE_GATE.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_PRESSURE_PLATE.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_PALM_BUTTON.get().asItem());
			tabData.accept(EndElemetnModBlocks.DRACORA_GRASS.get().asItem());
			tabData.accept(EndElemetnModBlocks.MOSSY_ENDSTONE_BRICKS.get().asItem());
			tabData.accept(EndElemetnModBlocks.CRACKED_CHORUS_PLANKS.get().asItem());
			tabData.accept(EndElemetnModBlocks.DIRTY_CHORUS_PLANKS.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(EndElemetnModItems.END_MUD_BUCKET.get());
			tabData.accept(EndElemetnModItems.SNARELINGBLOB.get());
			tabData.accept(EndElemetnModItems.VOID_LIQUID_BUCKET.get());
			tabData.accept(EndElemetnModItems.ENDSTONE_LOOTBOX.get());
			tabData.accept(EndElemetnModItems.TITANIUM_LOOTBOX.get());
			tabData.accept(EndElemetnModItems.ENDERITE_LOOTBOX.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_SHOVEL.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_PICKAXE.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_AXE.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_HOE.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_SCYTHE.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_HAMMER.get());
			tabData.accept(EndElemetnModItems.THUMPET.get());
			tabData.accept(EndElemetnModItems.DIMENSIONAL_CUTTER.get());
			tabData.accept(EndElemetnModItems.FULL_END_BOTTLE.get());
			tabData.accept(EndElemetnModItems.EYE_OF_TELEPORTATION.get());
			tabData.accept(EndElemetnModItems.WOODEN_HAMMER.get());
			tabData.accept(EndElemetnModItems.STONE_HAMMER.get());
			tabData.accept(EndElemetnModItems.IRON_HAMMER.get());
			tabData.accept(EndElemetnModItems.GOLDEN_HAMMER.get());
			tabData.accept(EndElemetnModItems.DIAMOND_HAMMER.get());
			tabData.accept(EndElemetnModItems.NETHERITE_HAMMER.get());
			tabData.accept(EndElemetnModItems.WOODEN_SCYTH.get());
			tabData.accept(EndElemetnModItems.STONE_SCYTH.get());
			tabData.accept(EndElemetnModItems.IRON_SCYTH.get());
			tabData.accept(EndElemetnModItems.GOLDEN_SCYTH.get());
			tabData.accept(EndElemetnModItems.DIAMOND_SCYTH.get());
			tabData.accept(EndElemetnModItems.NETHERITE_SCYTH.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(EndElemetnModBlocks.SNARELINGWEB.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_CITY_VAULT.get().asItem());
			tabData.accept(EndElemetnModBlocks.END_SPAWNER.get().asItem());
			tabData.accept(EndElemetnModBlocks.TITANIUM_PEDESTAL.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(EndElemetnModItems.WATCHLING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.SNARELING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.BLASTLING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.LURELING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.TRUMPLING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.ENDERSENT_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.EYED_ENDERSENT_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.TRADLING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.ENDERBABY_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.CELESTIAN_GUARD_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.SHADOWLING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.NECROSENT_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.CRYING_WATCHLING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.CRYING_SNARELING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.CRYING_BLASTLING_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.ENDER_JELLYFISH_SPAWN_EGG.get());
			tabData.accept(EndElemetnModItems.VENGEFUL_HEART_OF_ENDER_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(EndElemetnModItems.ENDERITE.get());
			tabData.accept(EndElemetnModItems.RAW_TITANIUM.get());
			tabData.accept(EndElemetnModItems.TITANIUM_INGOT.get());
			tabData.accept(EndElemetnModItems.TITANIUM_NUGGET.get());
			tabData.accept(EndElemetnModItems.TITANIUM_COIN.get());
			tabData.accept(EndElemetnModItems.CRYSTALLIZED_PURPUR.get());
			tabData.accept(EndElemetnModItems.WEAPON_SMITHING_TEMPLATE.get());
			tabData.accept(EndElemetnModItems.SHULKER_UPGRADER_SMITHING_TEMPLATE.get());
			tabData.accept(EndElemetnModItems.ENTERNAL_UPGRADER_SMITHING_TEMPLATE.get());
			tabData.accept(EndElemetnModItems.END_CITY_KEY.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_PICKAXE_SHARD.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_AXE_SHARD.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_HOE_SHARD.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_SWORD_SHARD.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_SHOWEL_SHARD.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_BOW_SHARD.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_SCYTHE_SHARD.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_HAMMER_SHARD.get());
			tabData.accept(EndElemetnModItems.METEOR_SCRAP.get());
			tabData.accept(EndElemetnModItems.UPGRADED_ENDERITHE_INGOT.get());
			tabData.accept(EndElemetnModItems.ENDERITHE_INGOT.get());
			tabData.accept(EndElemetnModItems.STAR.get());
			tabData.accept(EndElemetnModItems.ENTERNAL_RAW_ORE.get());
			tabData.accept(EndElemetnModItems.THE_END_SHARD.get());
			tabData.accept(EndElemetnModItems.LURE.get());
			tabData.accept(EndElemetnModItems.WATCHLING_EYE.get());
			tabData.accept(EndElemetnModItems.SNARE_STRING.get());
			tabData.accept(EndElemetnModItems.CORN_SEEDS.get());
			tabData.accept(EndElemetnModItems.SHARD_OF_EYE_OF_TELEPORTATION.get());
			tabData.accept(EndElemetnModItems.END_BOTTLE.get());
			tabData.accept(EndElemetnModItems.ENDSTONE_KEY.get());
			tabData.accept(EndElemetnModItems.END_SOULFUL_KEY.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			tabData.accept(EndElemetnModItems.DRAGON_FRUIT.get());
			tabData.accept(EndElemetnModItems.CORN.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
			if (tabData.hasPermissions()) {
				tabData.accept(EndElemetnModBlocks.HOLE_IN_REALITY.get().asItem());
				tabData.accept(EndElemetnModBlocks.UNSTABLE_HOLE_IN_REALITY.get().asItem());
			}
		}
	}
}