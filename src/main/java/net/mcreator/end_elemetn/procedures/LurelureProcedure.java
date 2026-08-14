package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import java.util.Comparator;

public class LurelureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive() && !world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(15 / 2d), e -> true).isEmpty()) {
			Entity foundPlayer = findEntityInWorldRange(world, Player.class, x, y, z, 15);
			if (foundPlayer == null)
				return;
			foundPlayer.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(x, y, z));
			Vec3 pull = new Vec3(x - foundPlayer.getX(), 0, z - foundPlayer.getZ());
			if (pull.lengthSqr() > 1.0E-4) {
				pull = pull.normalize().scale(0.06);
				foundPlayer.push(pull.x, 0, pull.z);
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}