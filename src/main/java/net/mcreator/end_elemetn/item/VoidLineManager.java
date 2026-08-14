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
import net.minecraft.core.particles.DustParticleOptions;

import org.joml.Vector3f;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Backs the Enderite Bow's full-draw "void line" perk: every tick the fired arrow is in flight,
 * the segment it just traveled is recorded as part of a trailing line that lingers for
 * TRAIL_LIFETIME_TICKS after being laid down, rendered as a dense run of small white dust
 * particles (a continuous thin line, not scattered dots). Any non-player entity that touches an
 * unexpired segment takes damage once per shot (tracked per-shot, not per-segment, so standing in
 * an old part of the line doesn't repeat-hit).
 */
@Mod.EventBusSubscriber
public class VoidLineManager {
	private static final double TRAIL_HIT_RADIUS = 0.6;
	private static final int TRAIL_LIFETIME_TICKS = 100;
	private static final float VOID_LINE_DAMAGE = 6.0f;
	private static final double LINE_PARTICLE_SPACING = 0.2;
	private static final float LINE_PARTICLE_SCALE = 0.35f;
	private static final Vector3f LINE_COLOR = new Vector3f(1f, 1f, 1f);

	private static final List<TrackedShot> trackedShots = new CopyOnWriteArrayList<>();
	private static final List<TrailSegment> trailSegments = new CopyOnWriteArrayList<>();

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
			Vec3 currentPos = shot.arrow.position();
			if (currentPos.distanceToSqr(shot.lastPos) > 1.0E-4) {
				long expireAt = serverLevel.getGameTime() + TRAIL_LIFETIME_TICKS;
				trailSegments.add(new TrailSegment(serverLevel, shot.lastPos, currentPos, expireAt, shot.hitEntityIds));
				shot.lastPos = currentPos;
			}
		}

		for (TrailSegment segment : trailSegments) {
			if (segment.level.getGameTime() >= segment.expireAtGameTime) {
				trailSegments.remove(segment);
				continue;
			}
			spawnLine(segment.level, segment.from, segment.to);
			AABB box = new AABB(segment.from, segment.to).inflate(TRAIL_HIT_RADIUS);
			List<LivingEntity> touching = segment.level.getEntitiesOfClass(LivingEntity.class, box,
					e -> !(e instanceof Player) && e.isAlive() && !segment.hitEntityIds.contains(e.getId()));
			for (LivingEntity target : touching) {
				segment.hitEntityIds.add(target.getId());
				target.hurt(target.damageSources().magic(), VOID_LINE_DAMAGE);
			}
		}
	}

	private static void spawnLine(ServerLevel level, Vec3 from, Vec3 to) {
		double distance = from.distanceTo(to);
		int steps = Math.max(1, (int) Math.ceil(distance / LINE_PARTICLE_SPACING));
		DustParticleOptions dust = new DustParticleOptions(LINE_COLOR, LINE_PARTICLE_SCALE);
		for (int i = 0; i <= steps; i++) {
			Vec3 p = from.lerp(to, (double) i / steps);
			level.sendParticles(dust, p.x, p.y, p.z, 1, 0.0, 0.0, 0.0, 0.0);
		}
	}

	private static final class TrackedShot {
		final Arrow arrow;
		final Set<Integer> hitEntityIds = new HashSet<>();
		Vec3 lastPos;

		TrackedShot(Arrow arrow) {
			this.arrow = arrow;
			this.lastPos = arrow.position();
		}
	}

	private static final class TrailSegment {
		final ServerLevel level;
		final Vec3 from;
		final Vec3 to;
		final long expireAtGameTime;
		final Set<Integer> hitEntityIds;

		TrailSegment(ServerLevel level, Vec3 from, Vec3 to, long expireAtGameTime, Set<Integer> hitEntityIds) {
			this.level = level;
			this.from = from;
			this.to = to;
			this.expireAtGameTime = expireAtGameTime;
			this.hitEntityIds = hitEntityIds;
		}
	}
}
