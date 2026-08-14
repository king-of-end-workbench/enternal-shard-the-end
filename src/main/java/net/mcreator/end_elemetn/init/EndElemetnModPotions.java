/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, EndElemetnMod.MODID);
	public static final RegistryObject<Potion> HYPNOTIZED_POTION = REGISTRY.register("hypnotized_potion", () -> new Potion(new MobEffectInstance(EndElemetnModMobEffects.HYPNOTISED.get(), 3600, 1, false, true)));
	public static final RegistryObject<Potion> SHUGARED_WATER = REGISTRY.register("shugared_water", () -> new Potion());
}