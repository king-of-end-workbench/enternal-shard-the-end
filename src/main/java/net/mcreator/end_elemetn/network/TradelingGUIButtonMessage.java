package net.mcreator.end_elemetn.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.procedures.Loot3Procedure;
import net.mcreator.end_elemetn.procedures.Loot2Procedure;
import net.mcreator.end_elemetn.procedures.Loot1Procedure;
import net.mcreator.end_elemetn.EndElemetnMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record TradelingGUIButtonMessage(int buttonID, int x, int y, int z) {
	public TradelingGUIButtonMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	public static void buffer(TradelingGUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(TradelingGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> handleButtonAction(context.getSender(), message.buttonID, message.x, message.y, message.z));
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			Loot1Procedure.execute(entity);
		}
		if (buttonID == 1) {

			Loot2Procedure.execute(entity);
		}
		if (buttonID == 2) {

			Loot3Procedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EndElemetnMod.addNetworkMessage(TradelingGUIButtonMessage.class, TradelingGUIButtonMessage::buffer, TradelingGUIButtonMessage::new, TradelingGUIButtonMessage::handler);
	}
}