package net.mcreator.end_elemetn.init;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.endbiomes.TheEndBiomes;

@EventBusSubscriber
public class EndElemetnModEndBiomes {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("end_elemetn:end_wilds")), 0.4375d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("end_elemetn:chorus_forest")), 0.4375d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("end_elemetn:inverted_forest")), 0.25d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("end_elemetn:void_plains")), 2.5d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, ResourceLocation.parse("end_elemetn:dracora_oasis")), 2.2d);
		});
	}
}