package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;

public class TrumplingUsloviieProighryvaniiaProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive();
	}
}