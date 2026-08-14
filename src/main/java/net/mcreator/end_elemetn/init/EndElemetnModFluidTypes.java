/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fluids.FluidType;

import net.mcreator.end_elemetn.fluid.types.VoidLiquidFluidType;
import net.mcreator.end_elemetn.fluid.types.EndMudFluidType;
import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, EndElemetnMod.MODID);
	public static final RegistryObject<FluidType> END_MUD_TYPE = REGISTRY.register("end_mud", () -> new EndMudFluidType());
	public static final RegistryObject<FluidType> VOID_LIQUID_TYPE = REGISTRY.register("void_liquid", () -> new VoidLiquidFluidType());
}