package net.mcreator.end_elemetn.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleOptions;

import net.mcreator.end_elemetn.procedures.VoidTouchPriNalozhieniiEffiektaProcedure;
import net.mcreator.end_elemetn.init.EndElemetnModParticleTypes;

public class VoidTouchMobEffect extends MobEffect {
	public VoidTouchMobEffect() {
		super(MobEffectCategory.HARMFUL, -15138786);
	}

	@Override
	public ParticleOptions createParticleOptions(MobEffectInstance mobEffectInstance) {
		return (SimpleParticleType) (EndElemetnModParticleTypes.VOID_SKULLS.get());
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		VoidTouchPriNalozhieniiEffiektaProcedure.execute(entity.level(), entity);
		return super.applyEffectTick(entity, amplifier);
	}
}