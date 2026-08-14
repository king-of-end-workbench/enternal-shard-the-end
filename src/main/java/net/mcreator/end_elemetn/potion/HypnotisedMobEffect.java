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
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		HypnotisedKazhdyiTikVoVriemiaEffiektaProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}