/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(Registries.POTION, EndElemetnMod.MODID);
	public static final DeferredHolder<Potion, Potion> HYPNOTIZED_POTION = REGISTRY.register("hypnotized_potion", () -> new Potion(new MobEffectInstance(EndElemetnModMobEffects.HYPNOTISED, 3600, 1, false, true)));
	public static final DeferredHolder<Potion, Potion> SHUGARED_WATER = REGISTRY.register("shugared_water", () -> new Potion());
}