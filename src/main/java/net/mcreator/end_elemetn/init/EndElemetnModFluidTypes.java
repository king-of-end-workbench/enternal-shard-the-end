/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import net.mcreator.end_elemetn.fluid.types.VoidLiquidFluidType;
import net.mcreator.end_elemetn.fluid.types.EndMudFluidType;
import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, EndElemetnMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> END_MUD_TYPE = REGISTRY.register("end_mud", () -> new EndMudFluidType());
	public static final DeferredHolder<FluidType, FluidType> VOID_LIQUID_TYPE = REGISTRY.register("void_liquid", () -> new VoidLiquidFluidType());
}