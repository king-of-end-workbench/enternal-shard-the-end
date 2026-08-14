/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.end_elemetn.client.particle.VoidSkullsParticle;
import net.mcreator.end_elemetn.client.particle.BeammmmParticle;

@EventBusSubscriber(Dist.CLIENT)
public class EndElemetnModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(EndElemetnModParticleTypes.VOID_SKULLS.get(), VoidSkullsParticle::provider);
		event.registerSpriteSet(EndElemetnModParticleTypes.BEAMMMM.get(), BeammmmParticle::provider);
	}
}