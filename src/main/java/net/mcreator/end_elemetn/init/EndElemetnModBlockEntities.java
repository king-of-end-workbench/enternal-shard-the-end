/*
*    MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.end_elemetn.block.entity.UnstableHoleInRealityBlockEntity;
import net.mcreator.end_elemetn.block.entity.TitaniumPedestalBlockEntity;
import net.mcreator.end_elemetn.block.entity.HoleInRealityBlockEntity;
import net.mcreator.end_elemetn.block.entity.EndSpawnerBlockEntity;
import net.mcreator.end_elemetn.block.entity.EndCityVaultBlockEntity;
import net.mcreator.end_elemetn.EndElemetnMod;

@EventBusSubscriber
public class EndElemetnModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EndElemetnMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EndSpawnerBlockEntity>> END_SPAWNER = register("end_spawner", EndElemetnModBlocks.END_SPAWNER, EndSpawnerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TitaniumPedestalBlockEntity>> TITANIUM_PEDESTAL = register("titanium_pedestal", EndElemetnModBlocks.TITANIUM_PEDESTAL, TitaniumPedestalBlockEntity::new);
	// Start of user code block custom block entities
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HoleInRealityBlockEntity>> HOLE_IN_REALITY = register("hole_in_reality", EndElemetnModBlocks.HOLE_IN_REALITY, HoleInRealityBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UnstableHoleInRealityBlockEntity>> UNSTABLE_HOLE_IN_REALITY = register("unstable_hole_in_reality", EndElemetnModBlocks.UNSTABLE_HOLE_IN_REALITY,
			UnstableHoleInRealityBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EndCityVaultBlockEntity>> END_CITY_VAULT = register("end_city_vault", EndElemetnModBlocks.END_CITY_VAULT, EndCityVaultBlockEntity::new);

	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, END_SPAWNER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, TITANIUM_PEDESTAL.get(), SidedInvWrapper::new);
	}
}