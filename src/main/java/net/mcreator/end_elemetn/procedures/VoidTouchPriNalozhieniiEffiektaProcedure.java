package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

import net.mcreator.end_elemetn.entity.VengefulHeartOfEnderEntity;
import net.mcreator.end_elemetn.entity.CelestianGuardEntity;
import net.mcreator.end_elemetn.entity.BlastlingEntity;
import net.mcreator.end_elemetn.entity.BulletentityEntity;
import net.mcreator.end_elemetn.entity.CryingBlastlingEntity;
import net.mcreator.end_elemetn.entity.CryingSnarelingEntity;
import net.mcreator.end_elemetn.entity.CryingWatchlingEntity;
import net.mcreator.end_elemetn.entity.EnderJellyfishEntity;
import net.mcreator.end_elemetn.entity.EnderbabyEntity;
import net.mcreator.end_elemetn.entity.EndersentEntity;
import net.mcreator.end_elemetn.entity.EyedEndersentEntity;
import net.mcreator.end_elemetn.entity.LurelingEntity;
import net.mcreator.end_elemetn.entity.NecrosentEntity;
import net.mcreator.end_elemetn.entity.ShadowlingEntity;
import net.mcreator.end_elemetn.entity.SnarelingEntity;
import net.mcreator.end_elemetn.entity.TradlingEntity;
import net.mcreator.end_elemetn.entity.TrumplingEntity;
import net.mcreator.end_elemetn.entity.WatchlingEntity;

public class VoidTouchPriNalozhieniiEffiektaProcedure {
	private static boolean isEndMob(Entity entity) {
		return entity instanceof CelestianGuardEntity || entity instanceof VengefulHeartOfEnderEntity || entity instanceof BlastlingEntity
				|| entity instanceof BulletentityEntity || entity instanceof CryingBlastlingEntity
				|| entity instanceof CryingSnarelingEntity || entity instanceof CryingWatchlingEntity || entity instanceof EnderJellyfishEntity
				|| entity instanceof EnderbabyEntity || entity instanceof EndersentEntity || entity instanceof EyedEndersentEntity
				|| entity instanceof LurelingEntity || entity instanceof NecrosentEntity || entity instanceof ShadowlingEntity
				|| entity instanceof SnarelingEntity || entity instanceof TradlingEntity || entity instanceof TrumplingEntity
				|| entity instanceof WatchlingEntity;
	}

	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!isEndMob(entity)) {
			entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.GENERIC)), 1);
		}
	}
}