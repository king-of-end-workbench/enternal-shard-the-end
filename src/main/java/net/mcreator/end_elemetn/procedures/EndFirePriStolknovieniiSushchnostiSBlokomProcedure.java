package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

public class EndFirePriStolknovieniiSushchnostiSBlokomProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		entity.igniteForSeconds(16);
		entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.IN_FIRE)), 3);
	}
}