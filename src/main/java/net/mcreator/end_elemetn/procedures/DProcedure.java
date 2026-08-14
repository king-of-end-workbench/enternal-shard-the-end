package net.mcreator.end_elemetn.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;

import net.mcreator.end_elemetn.EndElemetnMod;

import javax.annotation.Nullable;

@EventBusSubscriber
public class DProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource) {
		execute(null, world, damagesource);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource) {
		if (damagesource == null)
			return;
		if (damagesource.getEntity() != null) {
			if ((damagesource.getEntity()) instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			EndElemetnMod.queueServerWork(5, () -> {
				if ((damagesource.getEntity()) instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
			});
		}
	}
}