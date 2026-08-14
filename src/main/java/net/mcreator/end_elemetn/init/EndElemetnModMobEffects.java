/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.end_elemetn.potion.VoidTouchMobEffect;
import net.mcreator.end_elemetn.potion.TradeAcceptMobEffect;
import net.mcreator.end_elemetn.potion.HypnotisedMobEffect;
import net.mcreator.end_elemetn.potion.HauntedMobEffect;
import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EndElemetnMod.MODID);
	public static final RegistryObject<MobEffect> TRADE_ACCEPT = REGISTRY.register("trade_accept", () -> new TradeAcceptMobEffect());
	public static final RegistryObject<MobEffect> VOID_TOUCH = REGISTRY.register("void_touch", () -> new VoidTouchMobEffect());
	public static final RegistryObject<MobEffect> HAUNTED = REGISTRY.register("haunted", () -> new HauntedMobEffect());
	public static final RegistryObject<MobEffect> HYPNOTISED = REGISTRY.register("hypnotised", () -> new HypnotisedMobEffect());
}