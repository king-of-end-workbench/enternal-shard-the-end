/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EndElemetnMod.MODID);
	public static final RegistryObject<SimpleParticleType> VOID_SKULLS = REGISTRY.register("void_skulls", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> BEAMMMM = REGISTRY.register("beammmm", () -> new SimpleParticleType(false));
}