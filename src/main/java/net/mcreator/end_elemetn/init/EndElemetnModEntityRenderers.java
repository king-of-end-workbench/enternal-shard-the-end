/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.end_elemetn.client.renderer.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EndElemetnModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EndElemetnModEntities.SNARELING_GLOB.get(), SnarelingGlobRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.BLASTLING_BULLET.get(), BlastlingBulletRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.WATCHLING.get(), WatchlingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.SNARELING.get(), SnarelingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.BLASTLING.get(), BlastlingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.LURELING.get(), LurelingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.TRUMPLING.get(), TrumplingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.ENDERSENT.get(), EndersentRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.EYED_ENDERSENT.get(), EyedEndersentRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.TRADLING.get(), TradlingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.ENDERBABY.get(), EnderbabyRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.CELESTIAN_GUARD.get(), CelestianGuardRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.SHADOWLING.get(), ShadowlingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.NECROSENT.get(), NecrosentRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.SOUL_BULLET.get(), SoulBulletRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.CRYING_WATCHLING.get(), CryingWatchlingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.CRYING_SNARELING.get(), CryingSnarelingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.CRYING_BLASTLING.get(), CryingBlastlingRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.SOUL_GLOB.get(), SoulGlobRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.SOUL_BLASTLING_BULLET.get(), SoulBlastlingBulletRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.LURE_BLASTLING_BULLET.get(), LureBlastlingBulletRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.LASER_BEAM.get(), LaserBeamRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.ENDER_JELLYFISH.get(), EnderJellyfishRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.VENGERFUL_BULLET.get(), VengerfulBulletRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.BULLETENTITY.get(), BulletentityRenderer::new);
		event.registerEntityRenderer(EndElemetnModEntities.VENGEFUL_HEART_OF_ENDER.get(), VengefulHeartOfEnderRenderer::new);
	}
}