package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.init.EndElemetnModEntities;
import net.mcreator.end_elemetn.entity.SnarelingGlobEntity;

public class SnarelingblobPriShchielchkiePKMProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		{
			Entity _shootFrom = entity;
			Level projectileLevel = _shootFrom.level();
			if (!projectileLevel.isClientSide()) {
				Projectile _entityToSpawn = initArrowProjectile(new SnarelingGlobEntity(EndElemetnModEntities.SNARELING_GLOB.get(), 0, 0, 0, projectileLevel, createArrowWeaponItemStack(projectileLevel, 1, (byte) 0)), null, 5, true, false, false,
						AbstractArrow.Pickup.DISALLOWED);
				_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
				_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 1, 0);
				projectileLevel.addFreshEntity(_entityToSpawn);
			}
		}
		if (world instanceof ServerLevel _level) {
			(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(3, _level, null, _stkprov -> {
			});
		}
		if (entity instanceof Player _player)
			_player.getCooldowns().addCooldown((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), 60);
	}

	private static AbstractArrow initArrowProjectile(AbstractArrow entityToSpawn, Entity shooter, float damage, boolean silent, boolean fire, boolean particles, AbstractArrow.Pickup pickup) {
		entityToSpawn.setOwner(shooter);
		entityToSpawn.setBaseDamage(damage);
		if (silent)
			entityToSpawn.setSilent(true);
		if (fire)
			entityToSpawn.igniteForSeconds(100);
		if (particles)
			entityToSpawn.setCritArrow(true);
		entityToSpawn.pickup = pickup;
		return entityToSpawn;
	}

	private static ItemStack createArrowWeaponItemStack(Level level, int knockback, byte piercing) {
		ItemStack weapon = new ItemStack(Items.ARROW);
		if (knockback > 0)
			weapon.enchant(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), knockback);
		if (piercing > 0)
			weapon.enchant(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.PIERCING), piercing);
		return weapon;
	}
}