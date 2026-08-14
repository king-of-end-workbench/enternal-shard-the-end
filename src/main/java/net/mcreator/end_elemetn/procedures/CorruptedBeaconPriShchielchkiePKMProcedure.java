package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModParticleTypes;
import net.mcreator.end_elemetn.init.EndElemetnModEntities;
import net.mcreator.end_elemetn.entity.LaserBeamEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CorruptedBeaconPriShchielchkiePKMProcedure {
	private static final double MAX_RANGE = 50.0;
	private static final double BEAM_RADIUS = 0.75;
	private static final float DAMAGE_PER_TICK = 6.0f;
	private static final int XP_DRAIN_INTERVAL_TICKS = 4;
	private static final int MIN_XP_TO_CONTINUE = 5;
	private static final String NBT_KEY = "ActiveLaserBeamId";

	public static void tick(Level world, Player player) {
		if (world.isClientSide() || !(world instanceof ServerLevel serverLevel))
			return;

		if (!player.getAbilities().instabuild && player.totalExperience < MIN_XP_TO_CONTINUE) {
			player.stopUsingItem();
			stop(world, player);
			return;
		}

		if (!player.getAbilities().instabuild && player.tickCount % XP_DRAIN_INTERVAL_TICKS == 0) {
			player.giveExperiencePoints(-1);
		}

		Vec3 viewDir = player.getViewVector(1.0F);
		Vec3 start = player.getEyePosition(1.0F).subtract(0, 0.5, 0).add(viewDir.scale(0.3));
		Vec3 maxEnd = start.add(viewDir.scale(MAX_RANGE));

		HitResult hitResult = world.clip(new ClipContext(start, maxEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
		Vec3 beamEnd = hitResult.getType() == HitResult.Type.MISS ? maxEnd : hitResult.getLocation();
		double beamLength = start.distanceTo(beamEnd);
		if (beamLength < 0.5)
			beamLength = 0.5;

		DamageSource voidDamage = new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("end_elemetn:void_touched"))));

		Set<Entity> alreadyHit = new HashSet<>();
		int steps = (int) Math.ceil(beamLength);
		for (int i = 1; i <= steps; i++) {
			double travelled = Math.min(i, beamLength);
			Vec3 point = start.add(viewDir.scale(travelled));

			for (Entity candidate : world.getEntitiesOfClass(LivingEntity.class, new AABB(point, point).inflate(BEAM_RADIUS), e -> e != player && !(e instanceof LaserBeamEntity))) {
				if (alreadyHit.add(candidate)) {
					candidate.hurt(voidDamage, DAMAGE_PER_TICK);
				}
			}

			if (player.tickCount % 2 == 0)
				world.addParticle((SimpleParticleType) EndElemetnModParticleTypes.BEAMMMM.get(), point.x, point.y, point.z, 0, 0, 0);
		}

		LaserBeamEntity beam = findBeam(serverLevel, player);
		if (beam == null) {
			Entity spawned = EndElemetnModEntities.LASER_BEAM.get().spawn(serverLevel, BlockPos.containing(start.x, start.y, start.z), MobSpawnType.MOB_SUMMONED);
			if (spawned instanceof LaserBeamEntity newBeam) {
				beam = newBeam;
				player.getPersistentData().putString(NBT_KEY, beam.getUUID().toString());
				world.playSound(null, player.getX(), player.getY(), player.getZ(),
						BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ender_dragon.shoot")), SoundSource.NEUTRAL, 1, 1);
			}
		}
		if (beam != null) {
			beam.moveTo(start.x, start.y, start.z, player.getYRot(), player.getXRot());
			beam.setYBodyRot(player.getYRot());
			beam.setYHeadRot(player.getYRot());
			beam.setLaserYaw(Math.round(player.getYRot()));
			beam.setLaserPitch(Math.round(player.getXRot()));
			beam.setLaserLength((int) Math.ceil(beamLength));
			beam.setShouldSynchroRot(true);
			beam.keepAlive();
		}
	}

	public static void stop(Level world, Player player) {
		if (!(world instanceof ServerLevel serverLevel))
			return;
		LaserBeamEntity beam = findBeam(serverLevel, player);
		if (beam != null)
			beam.discard();
		player.getPersistentData().remove(NBT_KEY);
	}

	private static LaserBeamEntity findBeam(ServerLevel serverLevel, Player player) {
		if (!player.getPersistentData().contains(NBT_KEY))
			return null;
		try {
			UUID id = UUID.fromString(player.getPersistentData().getString(NBT_KEY));
			Entity found = serverLevel.getEntity(id);
			return found instanceof LaserBeamEntity beam ? beam : null;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
