package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.end_elemetn.entity.EnderJellyfishEntity;

public class ThumpetPriShchielchkiePKMProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getVehicle()) instanceof EnderJellyfishEntity) {
			if ((entity.getVehicle()) instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 5, false, false));
		}
	}
}