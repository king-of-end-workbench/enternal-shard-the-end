package net.mcreator.end_elemetn.block;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.block.entity.TitaniumPedestalBlockEntity;

/**
 * A Titanium Pedestal only spends one of its stored charges when it's actually used to respawn a
 * dead player, not when the spawnpoint is (re)designated by right-clicking it - so this hooks the
 * respawn event itself rather than the click procedure. player.getRespawnPosition()/Dimension()
 * are still set to whatever setRespawnPosition last stored (vanilla's PlayerList.respawn() copies
 * them onto the fresh ServerPlayer before this event fires), so they reliably point back at the
 * exact pedestal that was used.
 */
@Mod.EventBusSubscriber
public class TitaniumPedestalRespawnHandler {
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player))
			return;
		if (player.getRespawnDimension() != Level.END)
			return;
		BlockPos pos = player.getRespawnPosition();
		if (pos == null)
			return;
		if (!(player.level() instanceof ServerLevel level))
			return;
		if (level.dimension() != Level.END)
			return;
		if (level.getBlockEntity(pos) instanceof TitaniumPedestalBlockEntity pedestal) {
			pedestal.consumeCharge();
		}
	}
}
