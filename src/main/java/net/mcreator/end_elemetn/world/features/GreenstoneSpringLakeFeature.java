package net.mcreator.end_elemetn.world.features;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.block.GreenstoneGaserBlock;
import net.mcreator.end_elemetn.EndElemetnMod;

import com.mojang.serialization.Codec;

/**
 * Same safe (no getBiome-during-worldgen) lake carving as EndSafeLakeFeature, but afterwards
 * seeds Greenstone Geysers directly into the floor it just carved: for each (x, z) column of the
 * lake, the "filled" blob array already tells us exactly where the deepest water cell is, so the
 * cell right below it is the true lakebed - no need to re-discover the lake afterwards via a
 * separate placed_feature random-height scan (that approach missed almost every lake, since a
 * uniform random height across the whole column rarely lands within the ~8-block band the lake
 * actually occupies before running out of scan steps).
 */
public class GreenstoneSpringLakeFeature extends Feature<LakeFeature.Configuration> {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EndElemetnMod.MODID);
	public static final RegistryObject<Feature<?>> GREENSTONE_GEYSER_LAKE = REGISTRY.register("greenstone_geyser_lake",
			() -> new GreenstoneSpringLakeFeature(LakeFeature.Configuration.CODEC));

	private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();
	private static final int GEYSER_CHANCE_PERCENT = 5;

	public GreenstoneSpringLakeFeature(Codec<LakeFeature.Configuration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<LakeFeature.Configuration> context) {
		BlockPos origin = context.origin();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		LakeFeature.Configuration config = context.config();
		if (origin.getY() <= level.getMinBuildHeight() + 4) {
			return false;
		}

		BlockPos blockpos = origin.below(4);
		boolean[] filled = new boolean[2048];
		int blobs = random.nextInt(4) + 4;

		for (int j = 0; j < blobs; j++) {
			double d0 = random.nextDouble() * 6.0 + 3.0;
			double d1 = random.nextDouble() * 4.0 + 2.0;
			double d2 = random.nextDouble() * 6.0 + 3.0;
			double d3 = random.nextDouble() * (16.0 - d0 - 2.0) + 1.0 + d0 / 2.0;
			double d4 = random.nextDouble() * (8.0 - d1 - 4.0) + 2.0 + d1 / 2.0;
			double d5 = random.nextDouble() * (16.0 - d2 - 2.0) + 1.0 + d2 / 2.0;

			for (int l = 1; l < 15; l++) {
				for (int i1 = 1; i1 < 15; i1++) {
					for (int j1 = 1; j1 < 7; j1++) {
						double d6 = ((double) l - d3) / (d0 / 2.0);
						double d7 = ((double) j1 - d4) / (d1 / 2.0);
						double d8 = ((double) i1 - d5) / (d2 / 2.0);
						double d9 = d6 * d6 + d7 * d7 + d8 * d8;
						if (d9 < 1.0) {
							filled[(l * 16 + i1) * 8 + j1] = true;
						}
					}
				}
			}
		}

		BlockState fluidState = config.fluid().getState(random, blockpos);

		for (int k1 = 0; k1 < 16; k1++) {
			for (int k = 0; k < 16; k++) {
				for (int l2 = 0; l2 < 8; l2++) {
					boolean edge = !filled[(k1 * 16 + k) * 8 + l2]
							&& (k1 < 15 && filled[((k1 + 1) * 16 + k) * 8 + l2]
									|| k1 > 0 && filled[((k1 - 1) * 16 + k) * 8 + l2]
									|| k < 15 && filled[(k1 * 16 + k + 1) * 8 + l2]
									|| k > 0 && filled[(k1 * 16 + (k - 1)) * 8 + l2]
									|| l2 < 7 && filled[(k1 * 16 + k) * 8 + l2 + 1]
									|| l2 > 0 && filled[(k1 * 16 + k) * 8 + (l2 - 1)]);
					if (edge) {
						BlockState existing = level.getBlockState(blockpos.offset(k1, l2, k));
						if (l2 >= 4 && existing.liquid()) {
							return false;
						}

						if (l2 < 4 && !existing.isSolid() && level.getBlockState(blockpos.offset(k1, l2, k)) != fluidState) {
							return false;
						}
					}
				}
			}
		}

		for (int l1 = 0; l1 < 16; l1++) {
			for (int i2 = 0; i2 < 16; i2++) {
				for (int i3 = 0; i3 < 8; i3++) {
					if (filled[(l1 * 16 + i2) * 8 + i3]) {
						BlockPos target = blockpos.offset(l1, i3, i2);
						if (canReplaceBlock(level.getBlockState(target))) {
							boolean isAirLayer = i3 >= 4;
							level.setBlock(target, isAirLayer ? AIR : fluidState, 2);
							if (isAirLayer) {
								level.scheduleTick(target, AIR.getBlock(), 0);
								this.markAboveForPostProcessing(level, target);
							}
						}
					}
				}
			}
		}

		BlockState barrierState = config.barrier().getState(random, blockpos);
		if (!barrierState.isAir()) {
			for (int j2 = 0; j2 < 16; j2++) {
				for (int j3 = 0; j3 < 16; j3++) {
					for (int l3 = 0; l3 < 8; l3++) {
						boolean edge = !filled[(j2 * 16 + j3) * 8 + l3]
								&& (j2 < 15 && filled[((j2 + 1) * 16 + j3) * 8 + l3]
										|| j2 > 0 && filled[((j2 - 1) * 16 + j3) * 8 + l3]
										|| j3 < 15 && filled[(j2 * 16 + j3 + 1) * 8 + l3]
										|| j3 > 0 && filled[(j2 * 16 + (j3 - 1)) * 8 + l3]
										|| l3 < 7 && filled[(j2 * 16 + j3) * 8 + l3 + 1]
										|| l3 > 0 && filled[(j2 * 16 + j3) * 8 + (l3 - 1)]);
						if (edge && (l3 < 4 || random.nextInt(2) != 0)) {
							BlockState existing = level.getBlockState(blockpos.offset(j2, l3, j3));
							if (existing.isSolid() && !existing.is(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
								BlockPos target = blockpos.offset(j2, l3, j3);
								level.setBlock(target, barrierState, 2);
								this.markAboveForPostProcessing(level, target);
							}
						}
					}
				}
			}
		}

		seedGeysers(level, blockpos, filled, random);

		return true;
	}

	private void seedGeysers(WorldGenLevel level, BlockPos blockpos, boolean[] filled, RandomSource random) {
		for (int l1 = 0; l1 < 16; l1++) {
			for (int i2 = 0; i2 < 16; i2++) {
				int deepestFilled = -1;
				for (int i3 = 1; i3 < 7; i3++) {
					if (filled[(l1 * 16 + i2) * 8 + i3]) {
						deepestFilled = i3;
						break;
					}
				}
				if (deepestFilled < 0 || random.nextInt(100) >= GEYSER_CHANCE_PERCENT)
					continue;

				BlockPos floorPos = blockpos.offset(l1, deepestFilled - 1, i2);
				if (level.getBlockState(floorPos).isSolid()) {
					level.setBlock(floorPos, GreenstoneGaserBlock.GREENSTONE_GASER.get().defaultBlockState(), 2);
				}
			}
		}
	}

	private boolean canReplaceBlock(BlockState state) {
		return !state.is(BlockTags.FEATURES_CANNOT_REPLACE);
	}
}
