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
 * The biome's surface rule only reskins the topmost couple of blocks (vanilla surface rules never
 * reach the deep interior of an island), so every floating island in this biome was still solid
 * vanilla end_stone underneath its green_end_stone skin. This walks every column of the chunk from
 * top to bottom exactly once and swaps every end_stone block for green_end_stone, so the whole
 * island is green_end_stone through and through.
 */
public class GreenEndStoneReskinFeature extends Feature<NoneFeatureConfiguration> {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EndElemetnMod.MODID);
	public static final RegistryObject<Feature<?>> GREEN_END_STONE_RESKIN = REGISTRY.register("green_end_stone_reskin", () -> new GreenEndStoneReskinFeature(NoneFeatureConfiguration.CODEC));

	public GreenEndStoneReskinFeature(Codec<NoneFeatureConfiguration> codec) {
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
		BlockState greenEndStone = EndElemetnModBlocks.GREEN_END_STONE.get().defaultBlockState();
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		boolean replacedAny = false;

		for (int dx = 0; dx < 16; dx++) {
			for (int dz = 0; dz < 16; dz++) {
				int x = chunkMinX + dx;
				int z = chunkMinZ + dz;
				for (int y = top; y >= bottom; y--) {
					pos.set(x, y, z);
					if (level.getBlockState(pos).is(Blocks.END_STONE)) {
						level.setBlock(pos, greenEndStone, 2);
						replacedAny = true;
					}
				}
			}
		}

		return replacedAny;
	}
}
