package net.mcreator.end_elemetn.world.features;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;
import net.mcreator.end_elemetn.EndElemetnMod;

import com.mojang.serialization.Codec;

/**
 * Unlike environment_scan (which only samples a handful of random columns per chunk from a random
 * starting height, capped at 32 search steps by vanilla's own codec), this walks every column of
 * the chunk exactly once from the top of the world down to the bottom, so every floating island's
 * underside gets its end_stone converted to end_warped_grass regardless of how high or low that
 * particular island happens to generate.
 */
public class EndWarpedGrassCeilingFeature extends Feature<NoneFeatureConfiguration> {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EndElemetnMod.MODID);
	public static final RegistryObject<Feature<?>> END_WARPED_GRASS_CEILING = REGISTRY.register("end_warped_grass_ceiling", () -> new EndWarpedGrassCeilingFeature(NoneFeatureConfiguration.CODEC));

	public EndWarpedGrassCeilingFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos origin = context.origin();
		int chunkMinX = Math.floorDiv(origin.getX(), 16) * 16;
		int chunkMinZ = Math.floorDiv(origin.getZ(), 16) * 16;
		int top = level.getMaxBuildHeight() - 1;
		int bottom = level.getMinBuildHeight();
		BlockState grass = EndElemetnModBlocks.END_WARPED_GRASS.get().defaultBlockState();
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		boolean placedAny = false;

		for (int dx = 0; dx < 16; dx++) {
			for (int dz = 0; dz < 16; dz++) {
				int x = chunkMinX + dx;
				int z = chunkMinZ + dz;
				boolean previousWasAir = false;
				for (int y = top; y >= bottom; y--) {
					pos.set(x, y, z);
					BlockState state = level.getBlockState(pos);
					if (previousWasAir && state.is(Blocks.END_STONE)) {
						level.setBlock(pos, grass, 2);
						placedAny = true;
					}
					previousWasAir = state.isAir();
				}
			}
		}

		return placedAny;
	}
}
