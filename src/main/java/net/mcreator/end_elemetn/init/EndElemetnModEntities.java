/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import net.mcreator.end_elemetn.entity.*;
import net.mcreator.end_elemetn.EndElemetnMod;

@EventBusSubscriber
public class EndElemetnModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, EndElemetnMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<SnarelingGlobEntity>> SNARELING_GLOB = register("snareling_glob",
			EntityType.Builder.<SnarelingGlobEntity>of(SnarelingGlobEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlastlingBulletEntity>> BLASTLING_BULLET = register("blastling_bullet",
			EntityType.Builder.<BlastlingBulletEntity>of(BlastlingBulletEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<WatchlingEntity>> WATCHLING = register("watchling",
			EntityType.Builder.<WatchlingEntity>of(WatchlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<SnarelingEntity>> SNARELING = register("snareling",
			EntityType.Builder.<SnarelingEntity>of(SnarelingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlastlingEntity>> BLASTLING = register("blastling",
			EntityType.Builder.<BlastlingEntity>of(BlastlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.4f));
	public static final DeferredHolder<EntityType<?>, EntityType<LurelingEntity>> LURELING = register("lureling",
			EntityType.Builder.<LurelingEntity>of(LurelingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.7f));
	public static final DeferredHolder<EntityType<?>, EntityType<TrumplingEntity>> TRUMPLING = register("trumpling",
			EntityType.Builder.<TrumplingEntity>of(TrumplingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.3f));
	public static final DeferredHolder<EntityType<?>, EntityType<EndersentEntity>> ENDERSENT = register("endersent",
			EntityType.Builder.<EndersentEntity>of(EndersentEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.4f, 6f));
	public static final DeferredHolder<EntityType<?>, EntityType<EyedEndersentEntity>> EYED_ENDERSENT = register("eyed_endersent",
			EntityType.Builder.<EyedEndersentEntity>of(EyedEndersentEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.3f, 6f));
	public static final DeferredHolder<EntityType<?>, EntityType<TradlingEntity>> TRADLING = register("tradling",
			EntityType.Builder.<TradlingEntity>of(TradlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<EnderbabyEntity>> ENDERBABY = register("enderbaby",
			EntityType.Builder.<EnderbabyEntity>of(EnderbabyEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.4f));
	public static final DeferredHolder<EntityType<?>, EntityType<CelestianGuardEntity>> CELESTIAN_GUARD = register("celestian_guard",
			EntityType.Builder.<CelestianGuardEntity>of(CelestianGuardEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<ShadowlingEntity>> SHADOWLING = register("shadowling",
			EntityType.Builder.<ShadowlingEntity>of(ShadowlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<NecrosentEntity>> NECROSENT = register("necrosent",
			EntityType.Builder.<NecrosentEntity>of(NecrosentEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.4f, 6f));
	public static final DeferredHolder<EntityType<?>, EntityType<SoulBulletEntity>> SOUL_BULLET = register("soul_bullet",
			EntityType.Builder.<SoulBulletEntity>of(SoulBulletEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<CryingWatchlingEntity>> CRYING_WATCHLING = register("crying_watchling",
			EntityType.Builder.<CryingWatchlingEntity>of(CryingWatchlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CryingSnarelingEntity>> CRYING_SNARELING = register("crying_snareling",
			EntityType.Builder.<CryingSnarelingEntity>of(CryingSnarelingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CryingBlastlingEntity>> CRYING_BLASTLING = register("crying_blastling",
			EntityType.Builder.<CryingBlastlingEntity>of(CryingBlastlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<SoulGlobEntity>> SOUL_GLOB = register("soul_glob",
			EntityType.Builder.<SoulGlobEntity>of(SoulGlobEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<SoulBlastlingBulletEntity>> SOUL_BLASTLING_BULLET = register("soul_blastling_bullet",
			EntityType.Builder.<SoulBlastlingBulletEntity>of(SoulBlastlingBulletEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<LureBlastlingBulletEntity>> LURE_BLASTLING_BULLET = register("lure_blastling_bullet",
			EntityType.Builder.<LureBlastlingBulletEntity>of(LureBlastlingBulletEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<LaserBeamEntity>> LASER_BEAM = register("laser_beam",
			EntityType.Builder.<LaserBeamEntity>of(LaserBeamEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<EnderJellyfishEntity>> ENDER_JELLYFISH = register("ender_jellyfish",
			EntityType.Builder.<EnderJellyfishEntity>of(EnderJellyfishEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 3f));
	public static final DeferredHolder<EntityType<?>, EntityType<VengerfulBulletEntity>> VENGERFUL_BULLET = register("vengerful_bullet",
			EntityType.Builder.<VengerfulBulletEntity>of(VengerfulBulletEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<BulletentityEntity>> BULLETENTITY = register("bulletentity",
			EntityType.Builder.<BulletentityEntity>of(BulletentityEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<VengefulHeartOfEnderEntity>> VENGEFUL_HEART_OF_ENDER = register("vengeful_heart_of_ender",
			EntityType.Builder.<VengefulHeartOfEnderEntity>of(VengefulHeartOfEnderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(2f, 7.0625f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerEntity(Capabilities.ItemHandler.ENTITY, ENDER_JELLYFISH.get(), (living, context) -> living.getCombinedInventory());
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		WatchlingEntity.init(event);
		SnarelingEntity.init(event);
		BlastlingEntity.init(event);
		LurelingEntity.init(event);
		TrumplingEntity.init(event);
		EndersentEntity.init(event);
		EyedEndersentEntity.init(event);
		TradlingEntity.init(event);
		EnderbabyEntity.init(event);
		CelestianGuardEntity.init(event);
		ShadowlingEntity.init(event);
		NecrosentEntity.init(event);
		CryingWatchlingEntity.init(event);
		CryingSnarelingEntity.init(event);
		CryingBlastlingEntity.init(event);
		LaserBeamEntity.init(event);
		EnderJellyfishEntity.init(event);
		BulletentityEntity.init(event);
		VengefulHeartOfEnderEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(WATCHLING.get(), WatchlingEntity.createAttributes().build());
		event.put(SNARELING.get(), SnarelingEntity.createAttributes().build());
		event.put(BLASTLING.get(), BlastlingEntity.createAttributes().build());
		event.put(LURELING.get(), LurelingEntity.createAttributes().build());
		event.put(TRUMPLING.get(), TrumplingEntity.createAttributes().build());
		event.put(ENDERSENT.get(), EndersentEntity.createAttributes().build());
		event.put(EYED_ENDERSENT.get(), EyedEndersentEntity.createAttributes().build());
		event.put(TRADLING.get(), TradlingEntity.createAttributes().build());
		event.put(ENDERBABY.get(), EnderbabyEntity.createAttributes().build());
		event.put(CELESTIAN_GUARD.get(), CelestianGuardEntity.createAttributes().build());
		event.put(SHADOWLING.get(), ShadowlingEntity.createAttributes().build());
		event.put(NECROSENT.get(), NecrosentEntity.createAttributes().build());
		event.put(CRYING_WATCHLING.get(), CryingWatchlingEntity.createAttributes().build());
		event.put(CRYING_SNARELING.get(), CryingSnarelingEntity.createAttributes().build());
		event.put(CRYING_BLASTLING.get(), CryingBlastlingEntity.createAttributes().build());
		event.put(LASER_BEAM.get(), LaserBeamEntity.createAttributes().build());
		event.put(ENDER_JELLYFISH.get(), EnderJellyfishEntity.createAttributes().build());
		event.put(BULLETENTITY.get(), BulletentityEntity.createAttributes().build());
		event.put(VENGEFUL_HEART_OF_ENDER.get(), VengefulHeartOfEnderEntity.createAttributes().build());
	}
}