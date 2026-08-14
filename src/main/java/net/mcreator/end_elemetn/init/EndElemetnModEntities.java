/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.end_elemetn.entity.*;
import net.mcreator.end_elemetn.EndElemetnMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EndElemetnModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EndElemetnMod.MODID);
	public static final RegistryObject<EntityType<SnarelingGlobEntity>> SNARELING_GLOB = register("snareling_glob",
			EntityType.Builder.<SnarelingGlobEntity>of(SnarelingGlobEntity::new, MobCategory.MISC).setCustomClientFactory(SnarelingGlobEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<BlastlingBulletEntity>> BLASTLING_BULLET = register("blastling_bullet", EntityType.Builder.<BlastlingBulletEntity>of(BlastlingBulletEntity::new, MobCategory.MISC)
			.setCustomClientFactory(BlastlingBulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<WatchlingEntity>> WATCHLING = register("watchling",
			EntityType.Builder.<WatchlingEntity>of(WatchlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.5f));
	public static final RegistryObject<EntityType<SnarelingEntity>> SNARELING = register("snareling",
			EntityType.Builder.<SnarelingEntity>of(SnarelingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.5f));
	public static final RegistryObject<EntityType<BlastlingEntity>> BLASTLING = register("blastling",
			EntityType.Builder.<BlastlingEntity>of(BlastlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.4f));
	public static final RegistryObject<EntityType<LurelingEntity>> LURELING = register("lureling",
			EntityType.Builder.<LurelingEntity>of(LurelingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.7f));
	public static final RegistryObject<EntityType<TrumplingEntity>> TRUMPLING = register("trumpling",
			EntityType.Builder.<TrumplingEntity>of(TrumplingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2.3f));
	public static final RegistryObject<EntityType<EndersentEntity>> ENDERSENT = register("endersent",
			EntityType.Builder.<EndersentEntity>of(EndersentEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.4f, 6f));
	public static final RegistryObject<EntityType<EyedEndersentEntity>> EYED_ENDERSENT = register("eyed_endersent",
			EntityType.Builder.<EyedEndersentEntity>of(EyedEndersentEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.3f, 6f));
	public static final RegistryObject<EntityType<TradlingEntity>> TRADLING = register("tradling",
			EntityType.Builder.<TradlingEntity>of(TradlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TradlingEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<EnderbabyEntity>> ENDERBABY = register("enderbaby",
			EntityType.Builder.<EnderbabyEntity>of(EnderbabyEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(EnderbabyEntity::new)

					.sized(0.6f, 1.4f));
	public static final RegistryObject<EntityType<CelestianGuardEntity>> CELESTIAN_GUARD = register("celestian_guard",
			EntityType.Builder.<CelestianGuardEntity>of(CelestianGuardEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CelestianGuardEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<ShadowlingEntity>> SHADOWLING = register("shadowling",
			EntityType.Builder.<ShadowlingEntity>of(ShadowlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<NecrosentEntity>> NECROSENT = register("necrosent",
			EntityType.Builder.<NecrosentEntity>of(NecrosentEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.4f, 6f));
	public static final RegistryObject<EntityType<SoulBulletEntity>> SOUL_BULLET = register("soul_bullet",
			EntityType.Builder.<SoulBulletEntity>of(SoulBulletEntity::new, MobCategory.MISC).setCustomClientFactory(SoulBulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<CryingWatchlingEntity>> CRYING_WATCHLING = register("crying_watchling",
			EntityType.Builder.<CryingWatchlingEntity>of(CryingWatchlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<CryingSnarelingEntity>> CRYING_SNARELING = register("crying_snareling",
			EntityType.Builder.<CryingSnarelingEntity>of(CryingSnarelingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<CryingBlastlingEntity>> CRYING_BLASTLING = register("crying_blastling",
			EntityType.Builder.<CryingBlastlingEntity>of(CryingBlastlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<SoulGlobEntity>> SOUL_GLOB = register("soul_glob",
			EntityType.Builder.<SoulGlobEntity>of(SoulGlobEntity::new, MobCategory.MISC).setCustomClientFactory(SoulGlobEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<SoulBlastlingBulletEntity>> SOUL_BLASTLING_BULLET = register("soul_blastling_bullet", EntityType.Builder.<SoulBlastlingBulletEntity>of(SoulBlastlingBulletEntity::new, MobCategory.MISC)
			.setCustomClientFactory(SoulBlastlingBulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<LureBlastlingBulletEntity>> LURE_BLASTLING_BULLET = register("lure_blastling_bullet", EntityType.Builder.<LureBlastlingBulletEntity>of(LureBlastlingBulletEntity::new, MobCategory.MISC)
			.setCustomClientFactory(LureBlastlingBulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<LaserBeamEntity>> LASER_BEAM = register("laser_beam",
			EntityType.Builder.<LaserBeamEntity>of(LaserBeamEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<EnderJellyfishEntity>> ENDER_JELLYFISH = register("ender_jellyfish",
			EntityType.Builder.<EnderJellyfishEntity>of(EnderJellyfishEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(EnderJellyfishEntity::new)

					.sized(0.6f, 3f));
	public static final RegistryObject<EntityType<VengerfulBulletEntity>> VENGERFUL_BULLET = register("vengerful_bullet", EntityType.Builder.<VengerfulBulletEntity>of(VengerfulBulletEntity::new, MobCategory.MISC)
			.setCustomClientFactory(VengerfulBulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<BulletentityEntity>> BULLETENTITY = register("bulletentity",
			EntityType.Builder.<BulletentityEntity>of(BulletentityEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BulletentityEntity::new)

					.sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<VengefulHeartOfEnderEntity>> VENGEFUL_HEART_OF_ENDER = register("vengeful_heart_of_ender",
			EntityType.Builder.<VengefulHeartOfEnderEntity>of(VengefulHeartOfEnderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(VengefulHeartOfEnderEntity::new)

					.sized(2f, 7.0625f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			WatchlingEntity.init();
			SnarelingEntity.init();
			BlastlingEntity.init();
			LurelingEntity.init();
			TrumplingEntity.init();
			EndersentEntity.init();
			EyedEndersentEntity.init();
			TradlingEntity.init();
			EnderbabyEntity.init();
			CelestianGuardEntity.init();
			ShadowlingEntity.init();
			NecrosentEntity.init();
			CryingWatchlingEntity.init();
			CryingSnarelingEntity.init();
			CryingBlastlingEntity.init();
			LaserBeamEntity.init();
			EnderJellyfishEntity.init();
			BulletentityEntity.init();
			VengefulHeartOfEnderEntity.init();
		});
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