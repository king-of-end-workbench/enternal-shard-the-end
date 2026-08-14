package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityAnchorArgument;

public class HypnotisedKazhdyiTikVoVriemiaEffiektaProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entity.getX()), (entity.getY() - 1), (entity.getZ())));
	}
}