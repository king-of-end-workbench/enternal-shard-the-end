package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class AnimationProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof LivingEntity _livEnt0 && _livEnt0.swinging;
	}
}