package net.mcreator.end_elemetn.entity;

import net.neoforged.neoforge.items.wrapper.EntityHandsInvWrapper;
import net.neoforged.neoforge.items.wrapper.EntityArmorInvWrapper;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.EventHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.world.inventory.JellyGuiMenu;
import net.mcreator.end_elemetn.procedures.EnderJellyfishPriShchielchkiePKMPoSushchnostiProcedure;
import net.mcreator.end_elemetn.init.EndElemetnModItems;
import net.mcreator.end_elemetn.init.EndElemetnModEntities;
import net.mcreator.end_elemetn.custommixin.LivingEntityJumpingAccessor;

import io.netty.buffer.Unpooled;

public class EnderJellyfishEntity extends TamableAnimal {

	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(EnderJellyfishEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> ANIM = SynchedEntityData.defineId(EnderJellyfishEntity.class, EntityDataSerializers.INT);

	public EnderJellyfishEntity(EntityType<EnderJellyfishEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
		if (ANIM.equals(data)) {
			switch (this.entityData.get(ANIM)) {
			}
		}
		super.onSyncedDataUpdated(data);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TEXTURE, "drifter");
		builder.define(ANIM, 0);
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.8, 20) {
			@Override
			protected Vec3 getPosition() {
				RandomSource random = EnderJellyfishEntity.this.getRandom();
				double dir_x = EnderJellyfishEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = EnderJellyfishEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = EnderJellyfishEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
				return new Vec3(dir_x, dir_y, dir_z);
			}
		});
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new FloatGoal(this));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
	}

	@Override
	public boolean causeFallDamage(float l, float d, DamageSource source) {
		return false;
	}

	private final ItemStackHandler inventory = new ItemStackHandler(2) {
		@Override
		public int getSlotLimit(int slot) {
			return 1;
		}
	};
	private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));

	public CombinedInvWrapper getCombinedInventory() {
		return combined;
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		for (int i = 0; i < inventory.getSlots(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (!itemstack.isEmpty() && !EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)) {
				this.spawnAtLocation(itemstack);
			}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
		compound.put("InventoryCustom", inventory.serializeNBT(this.registryAccess()));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
		if (compound.get("InventoryCustom") instanceof CompoundTag inventoryTag)
			inventory.deserializeNBT(this.registryAccess(), inventoryTag);
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());
		if (sourceentity.isSecondaryUseActive()) {
			if (sourceentity instanceof ServerPlayer serverPlayer) {
				serverPlayer.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("Ender Jellyfish");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
						packetBuffer.writeBlockPos(sourceentity.blockPosition());
						packetBuffer.writeByte(0);
						packetBuffer.writeVarInt(EnderJellyfishEntity.this.getId());
						return new JellyGuiMenu(id, inventory, packetBuffer);
					}
				}, buf -> {
					buf.writeBlockPos(sourceentity.blockPosition());
					buf.writeByte(0);
					buf.writeVarInt(this.getId());
				});
			}
			return InteractionResult.sidedSuccess(this.level().isClientSide());
		}
		Item item = itemstack.getItem();
		if (itemstack.getItem() instanceof SpawnEggItem) {
			retval = super.mobInteract(sourceentity, hand);
		} else if (this.level().isClientSide()) {
			retval = (this.isTame() && this.isOwnedBy(sourceentity) || this.isFood(itemstack)) ? InteractionResult.sidedSuccess(this.level().isClientSide()) : InteractionResult.PASS;
		} else {
			if (this.isTame()) {
				if (this.isOwnedBy(sourceentity)) {
					if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
						this.usePlayerItem(sourceentity, hand, itemstack);
						FoodProperties foodproperties = itemstack.getFoodProperties(this);
						float nutrition = foodproperties != null ? (float) foodproperties.nutrition() : 1;
						this.heal(nutrition);
						retval = InteractionResult.sidedSuccess(this.level().isClientSide());
					} else if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
						this.usePlayerItem(sourceentity, hand, itemstack);
						this.heal(4);
						retval = InteractionResult.sidedSuccess(this.level().isClientSide());
					} else {
						retval = super.mobInteract(sourceentity, hand);
					}
				}
			} else if (this.isFood(itemstack)) {
				this.usePlayerItem(sourceentity, hand, itemstack);
				if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, sourceentity)) {
					this.tame(sourceentity);
					this.level().broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level().broadcastEntityEvent(this, (byte) 6);
				}
				this.setPersistenceRequired();
				retval = InteractionResult.sidedSuccess(this.level().isClientSide());
			} else {
				retval = super.mobInteract(sourceentity, hand);
				if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME)
					this.setPersistenceRequired();
			}
		}
		sourceentity.startRiding(this);
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level();

		EnderJellyfishPriShchielchkiePKMPoSushchnostiProcedure.execute(entity, sourceentity);
		return retval;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		EnderJellyfishEntity retval = EndElemetnModEntities.ENDER_JELLYFISH.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null);
		return retval;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return Ingredient.of(new ItemStack(EndElemetnModItems.DRAGON_FRUIT.get())).test(stack);
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	@Override
	public void setNoGravity(boolean ignored) {
		super.setNoGravity(true);
	}

	public void aiStep() {
		super.aiStep();
		this.setNoGravity(true);
		// A gentle up-down pulse layered on top of normal movement, like a real jellyfish, instead
		// of gliding in dead-flat straight lines - kept active while ridden too, so flying it still
		// feels alive instead of a perfectly flat mount.
		double bob = Math.sin((this.tickCount + this.getId()) * 0.1) * 0.02;
		this.setDeltaMovement(this.getDeltaMovement().add(0.0, bob, 0.0));
	}

	@Override
	public LivingEntity getControllingPassenger() {
		Entity passenger = this.getFirstPassenger();
		return passenger instanceof Player player ? player : super.getControllingPassenger();
	}

	@Override
	protected void tickRidden(Player player, Vec3 travelVector) {
		super.tickRidden(player, travelVector);
		this.setRot(player.getYRot(), player.getXRot() * 0.5F);
		this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();

		// Jet-propulsion burst on sprint - a real jellyfish shoves itself forward by pulsing, so
		// sprinting punches out a burst of particles behind it and a wingbeat-like sound, instead
		// of just quietly moving faster like any other flying mount.
		if (player.isSprinting()) {
			if (this.level().isClientSide) {
				Vec3 look = this.getLookAngle();
				RandomSource random = this.getRandom();
				for (int i = 0; i < 3; i++) {
					double px = this.getX() - look.x * 1.2 + (random.nextDouble() - 0.5) * 0.4;
					double py = this.getY() + this.getBbHeight() * 0.5 + (random.nextDouble() - 0.5) * 0.4;
					double pz = this.getZ() - look.z * 1.2 + (random.nextDouble() - 0.5) * 0.4;
					this.level().addParticle(ParticleTypes.REVERSE_PORTAL, px, py, pz, -look.x * 0.3, -look.y * 0.3, -look.z * 0.3);
				}
			} else if (this.tickCount % 8 == 0) {
				this.level().playSound(null, this.blockPosition(), SoundEvents.PHANTOM_FLAP, SoundSource.NEUTRAL, 0.6F, 1.6F);
			}
		}
	}

	@Override
	protected Vec3 getRiddenInput(Player player, Vec3 input) {
		double vertical = 0.0;
		if (player instanceof LivingEntityJumpingAccessor accessor && accessor.end_elemetn$isJumping()) {
			vertical += 1.0;
		} else if (player.isShiftKeyDown()) {
			vertical -= 1.0;
		}
		// Blend in a climb/dive from the camera's pitch while moving forward, on top of the
		// explicit up/down keys - otherwise altitude is entirely decoupled from where you're
		// looking and flight feels like a flat plane with an elevator button instead of actually
		// piloting through open air.
		double forward = player.zza;
		if (forward > 0.0) {
			vertical -= Math.sin(Math.toRadians(player.getXRot())) * forward;
		}
		return new Vec3(player.xxa * 0.5F, vertical, forward);
	}

	@Override
	protected float getRiddenSpeed(Player player) {
		float base = (float) this.getAttributeValue(Attributes.FLYING_SPEED);
		return player.isSprinting() ? base * 2.2F : base;
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(EndElemetnModEntities.ENDER_JELLYFISH.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		builder = builder.add(Attributes.FLYING_SPEED, 0.3);
		return builder;
	}

	@Override
	protected void onBelowWorld() {
		// End mobs ignore the void - falling below the world should not deal damage
	}
}
