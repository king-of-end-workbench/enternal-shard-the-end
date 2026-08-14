package net.mcreator.end_elemetn.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.procedures.LaserBeamPriObnovlieniiTikaSushchnostiProcedure;
import net.mcreator.end_elemetn.procedures.LaserBeamPriNachalnomPrizyvieSushchnostiProcedure;

import javax.annotation.Nullable;

public class LaserBeamEntity extends Monster {

	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(LaserBeamEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> ANIM = SynchedEntityData.defineId(LaserBeamEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_laser_lenght = SynchedEntityData.defineId(LaserBeamEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_yaw = SynchedEntityData.defineId(LaserBeamEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_pitch = SynchedEntityData.defineId(LaserBeamEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_shouldSynchroRot = SynchedEntityData.defineId(LaserBeamEntity.class, EntityDataSerializers.BOOLEAN);

	public LaserBeamEntity(EntityType<LaserBeamEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(true);
		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TEXTURE, "beam");
		builder.define(ANIM, 0);
		builder.define(DATA_laser_lenght, 0);
		builder.define(DATA_yaw, 0);
		builder.define(DATA_pitch, 0);
		builder.define(DATA_shouldSynchroRot, false);
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	public void setLaserYaw(int yaw) {
		this.entityData.set(DATA_yaw, yaw);
	}

	public int getLaserYaw() {
		return this.entityData.get(DATA_yaw);
	}

	public void setLaserPitch(int pitch) {
		this.entityData.set(DATA_pitch, pitch);
	}

	public int getLaserPitch() {
		return this.entityData.get(DATA_pitch);
	}

	public void setLaserLength(int length) {
		this.entityData.set(DATA_laser_lenght, length);
	}

	public int getLaserLength() {
		return this.entityData.get(DATA_laser_lenght);
	}

	public void setShouldSynchroRot(boolean value) {
		this.entityData.set(DATA_shouldSynchroRot, value);
	}

	public boolean getShouldSynchroRot() {
		return this.entityData.get(DATA_shouldSynchroRot);
	}

	private int ticksSinceRefresh = 0;

	public void keepAlive() {
		this.ticksSinceRefresh = 0;
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
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

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		return false;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata);
		LaserBeamPriNachalnomPrizyvieSushchnostiProcedure.execute(world, this);
		return retval;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
		compound.putInt("Datalaser_lenght", this.entityData.get(DATA_laser_lenght));
		compound.putInt("Datayaw", this.entityData.get(DATA_yaw));
		compound.putInt("Datapitch", this.entityData.get(DATA_pitch));
		compound.putBoolean("DatashouldSynchroRot", this.entityData.get(DATA_shouldSynchroRot));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
		if (compound.contains("Datalaser_lenght"))
			this.entityData.set(DATA_laser_lenght, compound.getInt("Datalaser_lenght"));
		if (compound.contains("Datayaw"))
			this.entityData.set(DATA_yaw, compound.getInt("Datayaw"));
		if (compound.contains("Datapitch"))
			this.entityData.set(DATA_pitch, compound.getInt("Datapitch"));
		if (compound.contains("DatashouldSynchroRot"))
			this.entityData.set(DATA_shouldSynchroRot, compound.getBoolean("DatashouldSynchroRot"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		LaserBeamPriObnovlieniiTikaSushchnostiProcedure.execute(this.level(), this);
		if (!this.level().isClientSide()) {
			this.ticksSinceRefresh++;
			if (this.ticksSinceRefresh > 4) {
				this.discard();
			}
		}
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void doPush(Entity entityIn) {
	}

	@Override
	protected void pushEntities() {
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
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
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
}