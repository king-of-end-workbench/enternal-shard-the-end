package net.mcreator.end_elemetn.init;

import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.mixin.NoiseGeneratorSettingsAccess;

@Mod.EventBusSubscriber
public class EndElemetnModSurfaceRules {
	@SubscribeEvent
	public static void init(ServerAboutToStartEvent event) {
		LevelStem levelStem = event.getServer().registryAccess().registryOrThrow(Registries.LEVEL_STEM).get(LevelStem.END);
		ChunkGenerator chunkGenerator = levelStem.generator();
		boolean hasEndBiomes = chunkGenerator.getBiomeSource().possibleBiomes().stream().anyMatch(biomeHolder -> biomeHolder.unwrapKey().orElseThrow().location().getNamespace().equals("end_elemetn"));
		if (hasEndBiomes) {
			if (chunkGenerator instanceof NoiseBasedChunkGenerator generator) {
				NoiseGeneratorSettings noiseGeneratorSettings = generator.settings.value();
				registerSurfaceRules(new ResourceLocation("end_elemetn:end_wilds"), noiseGeneratorSettings, EndElemetnModBlocks.ENDWILD_GRASS.get().defaultBlockState(), EndElemetnModBlocks.END_MESHFADES_STONE.get().defaultBlockState());
				registerSurfaceRules(new ResourceLocation("end_elemetn:chorus_forest"), noiseGeneratorSettings, EndElemetnModBlocks.CHORUS_GRASS.get().defaultBlockState(), Blocks.END_STONE.defaultBlockState());
				registerSurfaceRules(new ResourceLocation("end_elemetn:inverted_forest"), noiseGeneratorSettings, Blocks.END_STONE.defaultBlockState(), Blocks.END_STONE.defaultBlockState());
				registerSurfaceRules(new ResourceLocation("end_elemetn:void_plains"), noiseGeneratorSettings, EndElemetnModBlocks.SHADOW_GRASS.get().defaultBlockState(), EndElemetnModBlocks.PILLARS_OF_SHADOW_WOOD.get().defaultBlockState());
				registerSurfaceRules(new ResourceLocation("end_elemetn:dracora_oasis"), noiseGeneratorSettings, EndElemetnModBlocks.DRACORA_GRASS.get().defaultBlockState(), Blocks.END_STONE.defaultBlockState());
			}
		}
	}

	public static void registerSurfaceRules(ResourceLocation biome, NoiseGeneratorSettings noiseGeneratorSettings, BlockState groundBlock, BlockState undergroundBlock) {
		((NoiseGeneratorSettingsAccess) (Object) noiseGeneratorSettings).addSurfaceRule(SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ResourceKey.create(Registries.BIOME, biome)),
				SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(groundBlock)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(undergroundBlock)))), noiseGeneratorSettings.surfaceRule()));
	}
}
