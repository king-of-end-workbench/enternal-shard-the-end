package net.mcreator.end_elemetn;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.end_elemetn.world.features.StructureFeature;
import net.mcreator.end_elemetn.world.features.EndWarpedGrassCeilingFeature;
import net.mcreator.end_elemetn.world.features.GreenstoneSpringLakeFeature;
import net.mcreator.end_elemetn.block.GreenstoneGaserBlock;
import net.mcreator.end_elemetn.init.*;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;

@Mod("end_elemetn")
public class EndElemetnMod {
	public static final Logger LOGGER = LogManager.getLogger(EndElemetnMod.class);
	public static final String MODID = "end_elemetn";

	public EndElemetnMod(FMLJavaModLoadingContext context) {
		// Start of user code block mod constructor
		// forge-1.20.1 has no custom-registrable map decoration type registry (later addition) -
		// EndersentCaveMapTrade (currently unused) falls back to a built-in MapDecoration.Type instead.
		// End of user code block mod constructor
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = context.getModEventBus();
		EndElemetnModBlocks.REGISTRY.register(bus);
		EndElemetnModBlockEntities.REGISTRY.register(bus);
		EndElemetnModItems.REGISTRY.register(bus);
		EndElemetnModEntities.REGISTRY.register(bus);
		EndElemetnModTabs.REGISTRY.register(bus);
		StructureFeature.REGISTRY.register(bus);
		EndElemetnModPotions.REGISTRY.register(bus);
		EndElemetnModMobEffects.REGISTRY.register(bus);
		EndElemetnModEnchantments.REGISTRY.register(bus);
		EndElemetnModMenus.REGISTRY.register(bus);
		EndElemetnModParticleTypes.REGISTRY.register(bus);
		EndElemetnModFluids.REGISTRY.register(bus);
		EndElemetnModFluidTypes.REGISTRY.register(bus);
		// Start of user code block mod init
		EndWarpedGrassCeilingFeature.REGISTRY.register(bus);
		GreenstoneSpringLakeFeature.REGISTRY.register(bus);
		GreenstoneGaserBlock.BLOCK_REGISTRY.register(bus);
		GreenstoneGaserBlock.ITEM_REGISTRY.register(bus);
		// End of user code block mod init
	}

	// Start of user code block mod methods
	// End of user code block mod methods
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}