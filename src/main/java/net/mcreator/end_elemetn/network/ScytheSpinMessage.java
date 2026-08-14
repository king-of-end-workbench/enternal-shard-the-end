package net.mcreator.end_elemetn.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;

import net.mcreator.end_elemetn.EndElemetnMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ScytheSpinMessage {
	private final float yawDelta;

	public ScytheSpinMessage(float yawDelta) {
		this.yawDelta = yawDelta;
	}

	public ScytheSpinMessage(FriendlyByteBuf buffer) {
		this.yawDelta = buffer.readFloat();
	}

	public static void buffer(ScytheSpinMessage message, FriendlyByteBuf buffer) {
		buffer.writeFloat(message.yawDelta);
	}

	public static void handler(ScytheSpinMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isClient() && Minecraft.getInstance().player != null) {
				// Reuses the same turn() the vanilla boat-steering view nudge uses, so it composes
				// with the player's own mouse input instead of fighting/overwriting it.
				Minecraft.getInstance().player.turn(message.yawDelta / 0.15, 0);
			}
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EndElemetnMod.addNetworkMessage(ScytheSpinMessage.class, ScytheSpinMessage::buffer, ScytheSpinMessage::new, ScytheSpinMessage::handler);
	}
}
