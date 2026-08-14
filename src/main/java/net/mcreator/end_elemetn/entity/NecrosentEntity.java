package net.mcreator.end_elemetn.entity;

import net.minecraftforge.network.PlayMessages;

import net.mcreator.end_elemetn.init.EndElemetnModEntities;


import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.end_elemetn.procedures.NecrosentUsloviieProighryvaniiaProcedure;
import net.mcreator.end_elemetn.procedures.NecrosentPriRanieniiSushchnostiProcedure;
import net.mcreator.end_elemetn.procedures.NecrosentPriObnovleniiTikaProcedure;
import net.mcreator.end_elemetn.procedures.AnimationProcedure;
import net.mcreator.end_elemetn.client.model.animations.necrosentAnimation;

public class NecrosentEntity extends EnderMan {

	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(NecrosentEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> ANIM = SynchedEntityData.defineId(NecrosentEntity.class, EntityDataSerializers.INT);
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationState2 = new AnimationState();
	public final AnimationState animationState3 = new AnimationState();

	public NecrosentEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(EndElemetnModEntities.NECROSENT.get(), world);
	}

	public NecrosentEntity(EntityType<NecrosentEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		this.setMaxUpStep(0.6f);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
		if (ANIM.equals(data)) {
			switch (this.entityData.get(ANIM)) {
				case -1 :
					this.animationState0.stop();
					break;
				case -3 :
					this.animationState2.stop();
					break;
				case -4 :
					this.animationState3.stop();
					break;
				case 0 :
					this.animationState0.start(this.tickCount);
					break;
				case 2 :
					this.animationState2.start(this.tickCount);
					break;
				case 3 :
					this.animationState3.start(this.tickCount);
					break;
			}
		}
		super.onSyncedDataUpdated(data);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(TEXTURE, "necrosent_8hp");
		this.entityData.define(ANIM, 0);
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected void checkAndPerformAttack(LivingEntity entity, double distToEnemySqr) {
				if (this.isTimeToAttack() && distToEnemySqr < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity)) {
					this.resetAttackCooldown();
					this.mob.swing(InteractionHand.MAIN_HAND);
					this.mob.doHurtTarget(entity);
				}
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				// Only mobs actually summoned by EndSpawnerBlockEntity's trial waves proactively hunt
				// the player - the same species found naturally in the world stays neutral and only
				// fights back if attacked (see the HurtByTargetGoal below).
				return this.mob.getPersistentData().getBoolean("end_elemetn_from_spawner") && super.canUse();
			}
		});
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.death"));
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level();
		Entity entity = this;
		Entity sourceentity = damagesource.getEntity();
		Entity immediatesourceentity = damagesource.getDirectEntity();

		NecrosentPriRanieniiSushchnostiProcedure.execute(world, x, y, z);
		return super.hurt(damagesource, amount);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			if (this.animationState0.isStarted()) {
				float elapsedSeconds = this.animationState0.getAccumulatedTime() / 1000.0F;
				if (elapsedSeconds >= necrosentAnimation.necrosent_idle.lengthInSeconds()) {
					if (!necrosentAnimation.necrosent_idle.looping())
						this.animationState0.stop();
					else
						this.animationState0.start(this.tickCount);
				}
			}
			this.animationState2.animateWhen(AnimationProcedure.execute(this), this.tickCount);
			this.animationState3.animateWhen(NecrosentUsloviieProighryvaniiaProcedure.execute(this), this.tickCount);
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
		NecrosentPriObnovleniiTikaProcedure.execute(this.level(), this);
		if (!this.level().isClientSide() && this.isAggressive() && Math.random() < 0.01) {
			NecrosentPriRanieniiSushchnostiProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
		}
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 65);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 30);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}

	@Override
	protected void onBelowWorld() {
		// End mobs ignore the void - falling below the world should not deal damage
	}
}
