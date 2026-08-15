package net.mcreator.end_elemetn.world.features;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.block.GreenstoneGaserBlock;
import net.mcreator.end_elemetn.init.EndElemetnModBlocks;
import net.mcreator.end_elemetn.EndElemetnMod;

import com.mojang.serialization.Codec;

/**
 * Placed many times per chunk, each attempt landing on a column of water. If the block directly
 * below that water is Green End Cobblestone (the Greenstone Spring lake's barrier block, i.e.
 * this is genuinely part of a lake floor, not just any water), there's a small chance to replace
 * it with a Greenstone Geyser - so a lake ends up with a scattering of geysers on its floor
 * instead of exactly one guaranteed geyser in the same spot every time.
 */
public class GreenstoneGeyserSeedFeature extends Feature<NoneFeatureConfiguration> {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EndElemetnMod.MODID);
	public static final RegistryObject<Feature<?>> GREENSTONE_GEYSER_SEED = REGISTRY.register("greenstone_geyser_seed", () -> new GreenstoneGeyserSeedFeature(NoneFeatureConfiguration.CODEC));

	private static final int GEYSER_CHANCE_PERCENT = 5;

	public GreenstoneGeyserSeedFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos water = context.origin();
		BlockPos floorPos = water.below();
		BlockState floorState = level.getBlockState(floorPos);
		if (!floorState.is(EndElemetnModBlocks.GREEN_END_COBBLESTONE.get()))
			return false;

		RandomSource random = context.random();
		if (random.nextInt(100) >= GEYSER_CHANCE_PERCENT)
			return false;

		level.setBlock(floorPos, GreenstoneGaserBlock.GREENSTONE_GASER.get().defaultBlockState(), 2);
		return true;
	}
}
