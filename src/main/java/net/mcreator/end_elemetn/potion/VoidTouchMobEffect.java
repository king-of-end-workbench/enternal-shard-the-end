package net.mcreator.end_elemetn.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.end_elemetn.procedures.VoidTouchPriNalozhieniiEffiektaProcedure;

public class VoidTouchMobEffect extends MobEffect {
	public VoidTouchMobEffect() {
		super(MobEffectCategory.HARMFUL, -15138786);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		VoidTouchPriNalozhieniiEffiektaProcedure.execute(entity.level(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}