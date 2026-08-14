/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.mcreator.end_elemetn.fluid.VoidLiquidFluid;
import net.mcreator.end_elemetn.fluid.EndMudFluid;
import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, EndElemetnMod.MODID);
	public static final RegistryObject<FlowingFluid> END_MUD = REGISTRY.register("end_mud", () -> new EndMudFluid.Source());
	public static final RegistryObject<FlowingFluid> FLOWING_END_MUD = REGISTRY.register("flowing_end_mud", () -> new EndMudFluid.Flowing());
	public static final RegistryObject<FlowingFluid> VOID_LIQUID = REGISTRY.register("void_liquid", () -> new VoidLiquidFluid.Source());
	public static final RegistryObject<FlowingFluid> FLOWING_VOID_LIQUID = REGISTRY.register("flowing_void_liquid", () -> new VoidLiquidFluid.Flowing());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class FluidsClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(END_MUD.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_END_MUD.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(VOID_LIQUID.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_VOID_LIQUID.get(), RenderType.translucent());
		}
	}
}