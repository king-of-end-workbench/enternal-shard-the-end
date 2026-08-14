package net.mcreator.end_elemetn.client.renderer.blockentity;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HoleInRealityClientRenderers {
	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(EndElemetnModBlockEntities.HOLE_IN_REALITY.get(), HoleInRealityRenderer::new);
		event.registerBlockEntityRenderer(EndElemetnModBlockEntities.UNSTABLE_HOLE_IN_REALITY.get(), UnstableHoleInRealityRenderer::new);
		event.registerBlockEntityRenderer(EndElemetnModBlockEntities.END_CITY_VAULT.get(), EndCityVaultRenderer::new);
		event.registerBlockEntityRenderer(EndElemetnModBlockEntities.END_SPAWNER.get(), EndSpawnerRenderer::new);
	}
}
