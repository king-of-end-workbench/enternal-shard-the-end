package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.end_elemetn.init.EndElemetnModMobEffects;

public class TradlingUsloviieProighryvaniiaProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(EndElemetnModMobEffects.TRADE_ACCEPT.get());
	}
}