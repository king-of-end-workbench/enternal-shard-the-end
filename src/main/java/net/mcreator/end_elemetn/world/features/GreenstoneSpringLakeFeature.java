package net.mcreator.end_elemetn.world.features;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.block.GreenstoneGaserBlock;
import net.mcreator.end_elemetn.init.EndElemetnModBlocks;
import net.mcreator.end_elemetn.EndElemetnMod;

import com.mojang.serialization.Codec;

/**
 * A small round spring: a shallow pool of water lined with green end cobblestone, with a
 * Greenstone Geyser sitting at the very center under the water so it always has water directly
 * above it (the condition it needs to erupt).
 */
public class GreenstoneSpringLakeFeature extends Feature<NoneFeatureConfiguration> {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EndElemetnMod.MODID);
	public static final RegistryObject<Feature<?>> GREENSTONE_SPRING_LAKE = REGISTRY.register("greenstone_spring_lake", () -> new GreenstoneSpringLakeFeature(NoneFeatureConfiguration.CODEC));

	private static final int MIN_RADIUS = 3;
	private static final int MAX_RADIUS = 5;

	public GreenstoneSpringLakeFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos origin = context.origin();
		RandomSource random = context.random();
		int radius = MIN_RADIUS + random.nextInt(MAX_RADIUS - MIN_RADIUS + 1);

		BlockState waterState = Blocks.WATER.defaultBlockState();
		BlockState rimState = EndElemetnModBlocks.GREEN_END_COBBLESTONE.get().defaultBlockState();
		BlockState geyserState = GreenstoneGaserBlock.GREENSTONE_GASER.get().defaultBlockState();

		// Never read/write outside the chunk currently being decorated - reaching into a
		// neighboring chunk here (e.g. via getHeight) can throw "Requested chunk unavailable
		// during world generation" if that chunk isn't generated far enough yet.
		int chunkMinX = Math.floorDiv(origin.getX(), 16) * 16;
		int chunkMinZ = Math.floorDiv(origin.getZ(), 16) * 16;
		int chunkMaxX = chunkMinX + 15;
		int chunkMaxZ = chunkMinZ + 15;

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		boolean placedAny = false;

		for (int dx = -radius - 1; dx <= radius + 1; dx++) {
			for (int dz = -radius - 1; dz <= radius + 1; dz++) {
				double distance = Math.sqrt(dx * dx + dz * dz);
				if (distance > radius + 1)
					continue;
				int x = origin.getX() + dx;
				int z = origin.getZ() + dz;
				if (x < chunkMinX || x > chunkMaxX || z < chunkMinZ || z > chunkMaxZ)
					continue;
				int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z) - 1;
				if (surfaceY <= level.getMinBuildHeight())
					continue;

				if (distance <= radius) {
					boolean isCenter = dx == 0 && dz == 0;
					pos.set(x, surfaceY, z);
					level.setBlock(pos, isCenter ? geyserState : rimState, 2);
					pos.set(x, surfaceY + 1, z);
					level.setBlock(pos, waterState, 2);
					placedAny = true;
				} else {
					pos.set(x, surfaceY, z);
					level.setBlock(pos, rimState, 2);
				}
			}
		}

		return placedAny;
	}
}
