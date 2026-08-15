package net.mcreator.end_elemetn.init;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.endbiomes.TheEndBiomes;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EndElemetnModEndBiomes {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, new ResourceLocation("end_elemetn:end_wilds")), 0.4375d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, new ResourceLocation("end_elemetn:chorus_forest")), 0.4375d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, new ResourceLocation("end_elemetn:inverted_forest")), 0.25d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, new ResourceLocation("end_elemetn:void_plains")), 2.5d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, new ResourceLocation("end_elemetn:dracora_oasis")), 4.5d);
			TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, new ResourceLocation("end_elemetn:greenstone_springs")), 2.5d);
		});
	}
}
