package net.mcreator.end_elemetn.custom;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.server.ServerStartingEvent;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber
public class EndIslandGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> CHAOTIC_END_ISLANDS = GameRules.register("chaoticEndIslands", GameRules.Category.MISC,
			GameRules.BooleanValue.create(false, (server, rule) -> EndIslandGenerationState.CHAOTIC_ENABLED = rule.get()));

	@SubscribeEvent
	public static void onServerStarting(ServerStartingEvent event) {
		EndIslandGenerationState.CHAOTIC_ENABLED = event.getServer().getGameRules().getBoolean(CHAOTIC_END_ISLANDS);
	}
}
