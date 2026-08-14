package net.mcreator.end_elemetn.client.renderer.blockentity;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

@EventBusSubscriber(Dist.CLIENT)
public class HoleInRealityClientRenderers {
	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(EndElemetnModBlockEntities.HOLE_IN_REALITY.get(), HoleInRealityRenderer::new);
		event.registerBlockEntityRenderer(EndElemetnModBlockEntities.UNSTABLE_HOLE_IN_REALITY.get(), UnstableHoleInRealityRenderer::new);
		event.registerBlockEntityRenderer(EndElemetnModBlockEntities.END_CITY_VAULT.get(), EndCityVaultRenderer::new);
		event.registerBlockEntityRenderer(EndElemetnModBlockEntities.END_SPAWNER.get(), EndSpawnerRenderer::new);
	}
}
