package net.mcreator.end_elemetn.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

public class HauntedMobEffect extends MobEffect {
	public HauntedMobEffect() {
		super(MobEffectCategory.HARMFUL, -6750208);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("ambient.basalt_deltas.mood")));
	}
}