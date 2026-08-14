package net.mcreator.end_elemetn.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.end_elemetn.procedures.HypnotisedKazhdyiTikVoVriemiaEffiektaProcedure;

public class HypnotisedMobEffect extends MobEffect {
	public HypnotisedMobEffect() {
		super(MobEffectCategory.NEUTRAL, -44137);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		HypnotisedKazhdyiTikVoVriemiaEffiektaProcedure.execute(entity);
		return super.applyEffectTick(entity, amplifier);
	}
}