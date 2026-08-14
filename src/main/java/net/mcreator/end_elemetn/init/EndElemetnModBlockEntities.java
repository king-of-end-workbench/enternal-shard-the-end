/*
*    MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.end_elemetn.block.entity.UnstableHoleInRealityBlockEntity;
import net.mcreator.end_elemetn.block.entity.TitaniumPedestalBlockEntity;
import net.mcreator.end_elemetn.block.entity.HoleInRealityBlockEntity;
import net.mcreator.end_elemetn.block.entity.EndSpawnerBlockEntity;
import net.mcreator.end_elemetn.block.entity.EndCityVaultBlockEntity;
import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EndElemetnMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> END_SPAWNER = register("end_spawner", EndElemetnModBlocks.END_SPAWNER, EndSpawnerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> TITANIUM_PEDESTAL = register("titanium_pedestal", EndElemetnModBlocks.TITANIUM_PEDESTAL, TitaniumPedestalBlockEntity::new);
	// Start of user code block custom block entities
	public static final RegistryObject<BlockEntityType<?>> HOLE_IN_REALITY = register("hole_in_reality", EndElemetnModBlocks.HOLE_IN_REALITY, HoleInRealityBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> UNSTABLE_HOLE_IN_REALITY = register("unstable_hole_in_reality", EndElemetnModBlocks.UNSTABLE_HOLE_IN_REALITY,
			UnstableHoleInRealityBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> END_CITY_VAULT = register("end_city_vault", EndElemetnModBlocks.END_CITY_VAULT, EndCityVaultBlockEntity::new);

	// End of user code block custom block entities
	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}