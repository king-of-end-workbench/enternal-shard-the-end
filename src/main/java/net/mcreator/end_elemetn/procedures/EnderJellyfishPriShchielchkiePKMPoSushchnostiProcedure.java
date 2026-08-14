package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class EnderJellyfishPriShchielchkiePKMPoSushchnostiProcedure {
	public static void execute(Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (hasEntityInInventory(entity, new ItemStack(Items.SADDLE)) && entity.isVehicle() && (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null) == sourceentity) {
			entity.setDeltaMovement(new Vec3((sourceentity.getLookAngle().x), (sourceentity.getLookAngle().y), (sourceentity.getZ())));
			if (entity instanceof LivingEntity _livingEntity8 && _livingEntity8.getAttributes().hasAttribute(Attributes.FLYING_SPEED))
				_livingEntity8.getAttribute(Attributes.FLYING_SPEED).setBaseValue(5);
		} else {
			if (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(Attributes.FLYING_SPEED))
				_livingEntity9.getAttribute(Attributes.FLYING_SPEED).setBaseValue(1);
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}