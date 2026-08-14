/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.potion.VoidTouchMobEffect;
import net.mcreator.end_elemetn.potion.TradeAcceptMobEffect;
import net.mcreator.end_elemetn.potion.HypnotisedMobEffect;
import net.mcreator.end_elemetn.potion.HauntedMobEffect;
import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, EndElemetnMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> TRADE_ACCEPT = REGISTRY.register("trade_accept", () -> new TradeAcceptMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> VOID_TOUCH = REGISTRY.register("void_touch", () -> new VoidTouchMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> HAUNTED = REGISTRY.register("haunted", () -> new HauntedMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> HYPNOTISED = REGISTRY.register("hypnotised", () -> new HypnotisedMobEffect());
}