/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, EndElemetnMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> VOID_SKULLS = REGISTRY.register("void_skulls", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEAMMMM = REGISTRY.register("beammmm", () -> new SimpleParticleType(false));
}