package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.end_elemetn.init.EndElemetnModMobEffects;

public class LurePriZaviershieniiIspolzovaniiaProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(EndElemetnModMobEffects.HYPNOTISED, 200, 1));
	}
}