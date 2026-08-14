package net.mcreator.end_elemetn.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;
import net.mcreator.end_elemetn.entity.WatchlingEntity;
import net.mcreator.end_elemetn.entity.SnarelingEntity;
import net.mcreator.end_elemetn.entity.LurelingEntity;
import net.mcreator.end_elemetn.entity.BlastlingEntity;

import javax.annotation.Nullable;

@EventBusSubscriber
public class MobskilledProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof WatchlingEntity || entity instanceof SnarelingEntity || entity instanceof BlastlingEntity || entity instanceof LurelingEntity) {
			BlockPos foundPos = null;
			search:
			for (int dx = -3; dx <= 2; dx++) {
				for (int dy = -3; dy <= 2; dy++) {
					for (int dz = -3; dz <= 2; dz++) {
						BlockPos candidate = BlockPos.containing(x + dx, y + dy, z + dz);
						if (world.getBlockState(candidate).getBlock() == EndElemetnModBlocks.END_SPAWNER.get()) {
							foundPos = candidate;
							break search;
						}
					}
				}
			}
			if (foundPos != null) {
				if (!world.isClientSide()) {
					BlockEntity _blockEntity = world.getBlockEntity(foundPos);
					BlockState _bs = world.getBlockState(foundPos);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("killed_mobs", (getBlockNBTNumber(world, foundPos, "killed_mobs") + 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(foundPos, _bs, _bs, 3);
				}
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}