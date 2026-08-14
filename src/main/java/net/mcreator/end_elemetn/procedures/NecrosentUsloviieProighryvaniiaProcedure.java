package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.entity.Entity;

public class NecrosentUsloviieProighryvaniiaProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.isShiftKeyDown();
	}
}