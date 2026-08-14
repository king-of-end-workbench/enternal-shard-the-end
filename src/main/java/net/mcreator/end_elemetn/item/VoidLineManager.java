package net.mcreator.end_elemetn.item;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Backs the Enderite Bow's full-draw "void line" perk: every tick the fired arrow is in flight,
 * its current position is recorded as a point on a trailing line that lingers for
 * TRAIL_LIFETIME_TICKS after being laid down. Any non-player entity that touches an unexpired
 * point takes damage once per shot (tracked per-shot, not per-point, so standing in an old part
 * of the line doesn't repeat-hit).
 */
@Mod.EventBusSubscriber
public class VoidLineManager {
	private static final double TRAIL_POINT_RADIUS = 1.0;
	private static final int TRAIL_LIFETIME_TICKS = 100;
	private static final float VOID_LINE_DAMAGE = 6.0f;

	private static final List<TrackedShot> trackedShots = new CopyOnWriteArrayList<>();
	private static final List<TrailPoint> trailPoints = new CopyOnWriteArrayList<>();

	public static void track(Arrow arrow) {
		trackedShots.add(new TrackedShot(arrow));
	}

	@SubscribeEvent
	public static void onServerTick(TickEvent.ServerTickEvent event) {
		if (event.phase != TickEvent.Phase.END)
			return;

		for (TrackedShot shot : trackedShots) {
			if (shot.arrow.isRemoved() || !(shot.arrow.level() instanceof ServerLevel serverLevel)) {
				trackedShots.remove(shot);
				continue;
			}
			long expireAt = serverLevel.getGameTime() + TRAIL_LIFETIME_TICKS;
			trailPoints.add(new TrailPoint(serverLevel, shot.arrow.position(), expireAt, shot.hitEntityIds));
		}

		for (TrailPoint point : trailPoints) {
			if (point.level.getGameTime() >= point.expireAtGameTime) {
				trailPoints.remove(point);
				continue;
			}
			point.level.sendParticles(ParticleTypes.END_ROD, point.pos.x, point.pos.y, point.pos.z, 1, 0.02, 0.02, 0.02, 0.0);
			AABB box = new AABB(point.pos, point.pos).inflate(TRAIL_POINT_RADIUS);
			List<LivingEntity> touching = point.level.getEntitiesOfClass(LivingEntity.class, box,
					e -> !(e instanceof Player) && e.isAlive() && !point.hitEntityIds.contains(e.getId()));
			for (LivingEntity target : touching) {
				point.hitEntityIds.add(target.getId());
				target.hurt(target.damageSources().magic(), VOID_LINE_DAMAGE);
			}
		}
	}

	private static final class TrackedShot {
		final Arrow arrow;
		final Set<Integer> hitEntityIds = new HashSet<>();

		TrackedShot(Arrow arrow) {
			this.arrow = arrow;
		}
	}

	private static final class TrailPoint {
		final ServerLevel level;
		final Vec3 pos;
		final long expireAtGameTime;
		final Set<Integer> hitEntityIds;

		TrailPoint(ServerLevel level, Vec3 pos, long expireAtGameTime, Set<Integer> hitEntityIds) {
			this.level = level;
			this.pos = pos;
			this.expireAtGameTime = expireAtGameTime;
			this.hitEntityIds = hitEntityIds;
		}
	}
}
