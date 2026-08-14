/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.end_elemetn.enchantment.VoidWalkerEnchantment;
import net.mcreator.end_elemetn.enchantment.EnderVisionEnchantment;
import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EndElemetnMod.MODID);
	public static final RegistryObject<Enchantment> VOID_WALKER = REGISTRY.register("void_walker", () -> new VoidWalkerEnchantment());
	public static final RegistryObject<Enchantment> ENDER_VISION = REGISTRY.register("ender_vision", () -> new EnderVisionEnchantment());
}