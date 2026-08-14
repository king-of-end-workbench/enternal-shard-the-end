package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.end_elemetn.entity.SoulGlobEntity;
import net.mcreator.end_elemetn.entity.SoulBlastlingBulletEntity;
import net.mcreator.end_elemetn.EndElemetnMod;

public class NecrosentPriObnovleniiTikaProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (Math.random() < 0.04 && entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive() && _mobEnt0.getTarget() instanceof LivingEntity target) {
			LivingEntity shooter = _mobEnt0;
			Level projectileLevel = entity.level();
			if (!projectileLevel.isClientSide()) {
				if (Math.random() < 0.5) {
					SoulGlobEntity.shoot(shooter, target);
				} else {
					SoulBlastlingBulletEntity.shoot(shooter, target);
					EndElemetnMod.queueServerWork(5, () -> {
						if (shooter.isAlive() && target.isAlive())
							SoulBlastlingBulletEntity.shoot(shooter, target);
					});
					EndElemetnMod.queueServerWork(10, () -> {
						if (shooter.isAlive() && target.isAlive())
							SoulBlastlingBulletEntity.shoot(shooter, target);
					});
				}
				entity.setShiftKeyDown(true);
				EndElemetnMod.queueServerWork(10, () -> {
					entity.setShiftKeyDown(false);
				});
			}
		}
	}
}
