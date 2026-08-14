package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.end_elemetn.EndElemetnMod;

public class EndElemetnModMapDecorationTypes {
	public static final DeferredRegister<MapDecorationType> REGISTRY = DeferredRegister.create(Registries.MAP_DECORATION_TYPE, EndElemetnMod.MODID);
	public static final DeferredHolder<MapDecorationType, MapDecorationType> MARK_CAVE = REGISTRY.register("mark_cave",
			() -> new MapDecorationType(ResourceLocation.fromNamespaceAndPath(EndElemetnMod.MODID, "mark_cave"), true, -1, true, false));
}
