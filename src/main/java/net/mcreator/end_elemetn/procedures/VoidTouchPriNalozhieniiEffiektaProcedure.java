package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.entity.VengefulHeartOfEnderEntity;
import net.mcreator.end_elemetn.entity.CelestianGuardEntity;

public class VoidTouchPriNalozhieniiEffiektaProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof CelestianGuardEntity) || !(entity instanceof VengefulHeartOfEnderEntity)) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
		}
	}
}