package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModItems;
import net.mcreator.end_elemetn.block.entity.TitaniumPedestalBlockEntity;

public class TitaniumPedestalPriShchielchkiePKMPoBlokuProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockPos pos = BlockPos.containing(x, y, z);
		ItemStack heldItem = entity instanceof LivingEntity livingEntity ? livingEntity.getMainHandItem() : ItemStack.EMPTY;

		if (heldItem.getItem() == EndElemetnModItems.ENDERITE.get()) {
			if (!world.isClientSide() && world.getBlockEntity(pos) instanceof TitaniumPedestalBlockEntity pedestal) {
				pedestal.addCrystalCharge();
			}
		} else if (heldItem.getItem() == EndElemetnModItems.STAR.get()) {
			if (!world.isClientSide() && world.getBlockEntity(pos) instanceof TitaniumPedestalBlockEntity pedestal) {
				pedestal.setStarCharge();
			}
		} else if (entity.level().dimension() == Level.END) {
			if (entity instanceof ServerPlayer serverPlayer)
				serverPlayer.setRespawnPosition(serverPlayer.level().dimension(), pos, serverPlayer.getYRot(), false, false);
		} else {
			if (world instanceof Level level && !level.isClientSide())
				level.explode(null, x, y, z, 9, Level.ExplosionInteraction.BLOCK);
		}
	}
}
