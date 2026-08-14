package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

public class EnternslScytheProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack, double radius, float damage, int cooldownTicks) {
		if (entity == null)
			return;
		if (entity instanceof Player _plrCldRem1 && _plrCldRem1.getCooldowns().isOnCooldown(itemstack.getItem()))
			return;
		final Vec3 _center = new Vec3(x, y, z);
		for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(radius), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
			if (!(entityiterator == entity)) {
				entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.PLAYER_ATTACK)), damage);
			}
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.attack.sweep")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.attack.sweep")), SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.SWEEP_ATTACK, x, y, z, (int) (radius * 2), radius / 2, 0.5, radius / 2, 1);
		if (entity instanceof Player _plrCldRem1) {
			_plrCldRem1.getCooldowns().addCooldown(itemstack.getItem(), cooldownTicks);
		}
	}
}
