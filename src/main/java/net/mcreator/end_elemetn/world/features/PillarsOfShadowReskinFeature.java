package net.mcreator.end_elemetn.world.features;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;
import net.mcreator.end_elemetn.EndElemetnMod;

import com.mojang.serialization.Codec;

/**
 * Same fix as GreenEndStoneReskinFeature, for Void Plains: the biome's surface rule only reskins
 * the topmost surface layer, so every island here was still plain end_stone underneath its
 * pillars_of_shadow_wood skin. Walks every column of the chunk once and swaps every end_stone
 * block for pillars_of_shadow_wood.
 *
 * Checks the biome per-column rather than trusting the placed_feature's chunk-wide "biome" filter
 * alone - that filter only samples a single point per chunk, which misses thin/sparse islands
 * where void_plains only covers a small corner of the chunk (this biome runs small more often than
 * greenstone_springs, so the gap was much more visible here).
 */
public class PillarsOfShadowReskinFeature extends Feature<NoneFeatureConfiguration> {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EndElemetnMod.MODID);
	public static final RegistryObject<Feature<?>> PILLARS_OF_SHADOW_RESKIN = REGISTRY.register("pillars_of_shadow_reskin", () -> new PillarsOfShadowReskinFeature(NoneFeatureConfiguration.CODEC));

	private static final ResourceKey<Biome> TARGET_BIOME = ResourceKey.create(Registries.BIOME, new ResourceLocation("end_elemetn:void_plains"));

	public PillarsOfShadowReskinFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos origin = context.origin();
		int chunkMinX = Math.floorDiv(origin.getX(), 16) * 16;
		int chunkMinZ = Math.floorDiv(origin.getZ(), 16) * 16;
		int top = level.getMaxBuildHeight() - 1;
		int bottom = level.getMinBuildHeight();
		BlockState pillarsOfShadow = EndElemetnModBlocks.PILLARS_OF_SHADOW_WOOD.get().defaultBlockState();
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		boolean replacedAny = false;

		for (int dx = 0; dx < 16; dx++) {
			for (int dz = 0; dz < 16; dz++) {
				int x = chunkMinX + dx;
				int z = chunkMinZ + dz;
				pos.set(x, bottom, z);
				if (!level.getBiome(pos).is(TARGET_BIOME))
					continue;

				for (int y = top; y >= bottom; y--) {
					pos.set(x, y, z);
					if (level.getBlockState(pos).is(Blocks.END_STONE)) {
						level.setBlock(pos, pillarsOfShadow, 2);
						replacedAny = true;
					}
				}
			}
		}

		return replacedAny;
	}
}
