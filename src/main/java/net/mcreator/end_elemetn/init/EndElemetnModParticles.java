/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.end_elemetn.client.particle.VoidSkullsParticle;
import net.mcreator.end_elemetn.client.particle.BeammmmParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EndElemetnModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(EndElemetnModParticleTypes.VOID_SKULLS.get(), VoidSkullsParticle::provider);
		event.registerSpriteSet(EndElemetnModParticleTypes.BEAMMMM.get(), BeammmmParticle::provider);
	}
}