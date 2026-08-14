package net.mcreator.end_elemetn.client.renderer.blockentity;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.mcreator.end_elemetn.block.entity.HoleInRealityBlockEntity;
import net.mcreator.end_elemetn.block.entity.UnstableHoleInRealityBlockEntity;
import net.mcreator.end_elemetn.block.entity.EndCityVaultBlockEntity;
import net.mcreator.end_elemetn.block.entity.EndSpawnerBlockEntity;
import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HoleInRealityClientRenderers {
	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer((BlockEntityType<HoleInRealityBlockEntity>) EndElemetnModBlockEntities.HOLE_IN_REALITY.get(), HoleInRealityRenderer::new);
		event.registerBlockEntityRenderer((BlockEntityType<UnstableHoleInRealityBlockEntity>) EndElemetnModBlockEntities.UNSTABLE_HOLE_IN_REALITY.get(), UnstableHoleInRealityRenderer::new);
		event.registerBlockEntityRenderer((BlockEntityType<EndCityVaultBlockEntity>) EndElemetnModBlockEntities.END_CITY_VAULT.get(), EndCityVaultRenderer::new);
		event.registerBlockEntityRenderer((BlockEntityType<EndSpawnerBlockEntity>) EndElemetnModBlockEntities.END_SPAWNER.get(), EndSpawnerRenderer::new);
	}
}
