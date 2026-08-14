package net.mcreator.end_elemetn.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.client.Minecraft;

import net.mcreator.end_elemetn.EndElemetnMod;

@EventBusSubscriber
public record ScytheSpinMessage(float yawDelta) implements CustomPacketPayload {

	public static final Type<ScytheSpinMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(EndElemetnMod.MODID, "scythe_spin"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ScytheSpinMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, ScytheSpinMessage message) -> {
		buffer.writeFloat(message.yawDelta);
	}, (RegistryFriendlyByteBuf buffer) -> new ScytheSpinMessage(buffer.readFloat()));

	@Override
	public Type<ScytheSpinMessage> type() {
		return TYPE;
	}

	public static void handleData(final ScytheSpinMessage message, final IPayloadContext context) {
		context.enqueueWork(() -> {
			if (context.flow() == PacketFlow.CLIENTBOUND && Minecraft.getInstance().player != null) {
				// Reuses the same turn() the vanilla boat-steering view nudge uses, so it composes
				// with the player's own mouse input instead of fighting/overwriting it.
				Minecraft.getInstance().player.turn(message.yawDelta / 0.15, 0);
			}
		}).exceptionally(e -> {
			context.connection().disconnect(Component.literal(e.getMessage()));
			return null;
		});
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EndElemetnMod.addNetworkMessage(ScytheSpinMessage.TYPE, ScytheSpinMessage.STREAM_CODEC, ScytheSpinMessage::handleData);
	}
}
