package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModEntities;
import net.mcreator.end_elemetn.entity.BlastlingBulletEntity;

public class VengefulHeartOfEnderPriObnovlieniiTikaSushchnostiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (Math.random() < 0.01 && entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive()) {
			for (int index0 = 0; index0 < 25; index0++) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = EntityType.ENDERMITE.spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
			}
		}
		if (Math.random() < 0.05 && entity instanceof Mob _mobEnt2 && _mobEnt2.isAggressive()) {
			if (world instanceof ServerLevel projectileLevel) {
				Projectile _entityToSpawn = initArrowProjectile(createArrowWeaponItemStack(new BlastlingBulletEntity(EndElemetnModEntities.BLASTLING_BULLET.get(), 0, 0, 0, projectileLevel), 1, (byte) 0), entity, 5, true, false, false,
						AbstractArrow.Pickup.DISALLOWED);
				_entityToSpawn.setPos(x, (y + 5.0625), z);
				_entityToSpawn.shoot((entity.getLookAngle().x), (entity.getLookAngle().y), (entity.getLookAngle().z), 1, 0);
				projectileLevel.addFreshEntity(_entityToSpawn);
			}
		}
	}

	private static AbstractArrow initArrowProjectile(AbstractArrow entityToSpawn, Entity shooter, float damage, boolean silent, boolean fire, boolean particles, AbstractArrow.Pickup pickup) {
		entityToSpawn.setOwner(shooter);
		entityToSpawn.setBaseDamage(damage);
		if (silent)
			entityToSpawn.setSilent(true);
		if (fire)
			entityToSpawn.setSecondsOnFire(100);
		if (particles)
			entityToSpawn.setCritArrow(true);
		entityToSpawn.pickup = pickup;
		return entityToSpawn;
	}

	private static AbstractArrow createArrowWeaponItemStack(AbstractArrow entityToSpawn, int knockback, byte piercing) {
		if (knockback > 0)
			entityToSpawn.setKnockback(knockback);
		if (piercing > 0)
			entityToSpawn.setPierceLevel(piercing);
		return entityToSpawn;
	}
}