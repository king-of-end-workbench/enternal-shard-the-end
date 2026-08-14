package net.mcreator.end_elemetn.entity;


import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.end_elemetn.procedures.ShadowlingPriObnovlieniiTikaSushchnostiProcedure;
import net.mcreator.end_elemetn.procedures.AnimationProcedure;
import net.mcreator.end_elemetn.init.EndElemetnModEntities;
import net.mcreator.end_elemetn.client.model.animations.err_entity_shadowAnimation;

public class ShadowlingEntity extends EnderMan {

	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(ShadowlingEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> ANIM = SynchedEntityData.defineId(ShadowlingEntity.class, EntityDataSerializers.INT);
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationState2 = new AnimationState();
	private boolean watchedByPlayer = false;

	public ShadowlingEntity(EntityType<ShadowlingEntity> type, Level world) {
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
				case 0 :
					this.animationState0.start(this.tickCount);
					break;
				case 2 :
					this.animationState2.start(this.tickCount);
					break;
			}
		}
		super.onSyncedDataUpdated(data);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(TEXTURE, "err_shadow_entity_8hp");
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
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, (float) 6));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected void checkAndPerformAttack(LivingEntity entity, double distToEnemySqr) {
				if (this.isTimeToAttack() && distToEnemySqr < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity)) {
					this.resetAttackCooldown();
					this.mob.swing(InteractionHand.MAIN_HAND);
					this.mob.doHurtTarget(entity);
				}
			}
		});
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				// Only mobs actually summoned by EndSpawnerBlockEntity's trial waves proactively hunt
				// the player - the same species found naturally in the world stays neutral and only
				// fights back if attacked (see the HurtByTargetGoal below).
				return this.mob.getPersistentData().getBoolean("end_elemetn_from_spawner") && super.canUse();
			}
		});
		this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(6, new FloatGoal(this));
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
			this.animationState0.animateWhen(AnimationProcedure.execute(this), this.tickCount);
			if (this.animationState2.isStarted()) {
				float elapsedSeconds = this.animationState2.getAccumulatedTime() / 1000.0F;
				if (elapsedSeconds >= err_entity_shadowAnimation.idle.lengthInSeconds()) {
					if (!err_entity_shadowAnimation.idle.looping())
						this.animationState2.stop();
					else
						this.animationState2.start(this.tickCount);
				}
			}
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
		ShadowlingPriObnovlieniiTikaSushchnostiProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		if (!this.level().isClientSide()) {
			boolean nowWatched = this.isWatchedByAnyPlayer();
			if (this.watchedByPlayer && !nowWatched) {
				this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15, 4, false, false));
			}
			this.watchedByPlayer = nowWatched;
		}
	}

	private boolean isWatchedByAnyPlayer() {
		double range = 24.0;
		for (Player player : this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(range), p -> true)) {
			if (player.isSpectator() || !player.isAlive())
				continue;
			Vec3 toThis = this.getEyePosition().subtract(player.getEyePosition());
			double distSqr = toThis.lengthSqr();
			if (distSqr > range * range)
				continue;
			if (distSqr < 1.0E-4)
				return true;
			Vec3 dir = toThis.normalize();
			double dot = player.getViewVector(1.0F).dot(dir);
			if (dot >= 0.5D && player.hasLineOfSight(this))
				return true;
		}
		return false;
	}

	@Override
	public void travel(Vec3 travelVector) {
		if (this.watchedByPlayer) {
			Vec3 currentMotion = this.getDeltaMovement();
			this.setDeltaMovement(0.0, currentMotion.y, 0.0);
			super.travel(Vec3.ZERO);
		} else {
			super.travel(travelVector);
		}
	}

	public static void init() {
		SpawnPlacements.register(EndElemetnModEntities.SHADOWLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 25);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 9);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}

	@Override
	protected void onBelowWorld() {
		// End mobs ignore the void - falling below the world should not deal damage
	}
}
