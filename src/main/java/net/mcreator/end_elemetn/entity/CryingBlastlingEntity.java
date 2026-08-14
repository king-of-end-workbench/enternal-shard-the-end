package net.mcreator.end_elemetn.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.end_elemetn.procedures.BlastlingPlusPriObnovlieniiTikaSushchnostiProcedure;
import net.mcreator.end_elemetn.procedures.AnimationProcedure;
import net.mcreator.end_elemetn.client.model.animations.blastling_aniAnimation;
import net.mcreator.end_elemetn.EndElemetnMod;

public class CryingBlastlingEntity extends EnderMan implements RangedAttackMob {

	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(CryingBlastlingEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> ANIM = SynchedEntityData.defineId(CryingBlastlingEntity.class, EntityDataSerializers.INT);
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationState2 = new AnimationState();

	public CryingBlastlingEntity(EntityType<CryingBlastlingEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
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
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TEXTURE, "blastling_controling1");
		builder.define(ANIM, 0);
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
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0, 60, 10.0f));
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
	public void performRangedAttack(LivingEntity target, float velocity) {
		if (this.level().isClientSide())
			return;
		this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 25, false, false));
		SoulBlastlingBulletEntity.shoot(this, target);
		EndElemetnMod.queueServerWork(5, () -> {
			if (this.isAlive() && target.isAlive())
				SoulBlastlingBulletEntity.shoot(this, target);
		});
		EndElemetnMod.queueServerWork(10, () -> {
			if (this.isAlive() && target.isAlive())
				SoulBlastlingBulletEntity.shoot(this, target);
		});
		this.swing(InteractionHand.MAIN_HAND, true);
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
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
				if (elapsedSeconds >= blastling_aniAnimation.blastling_idle.lengthInSeconds()) {
					if (!blastling_aniAnimation.blastling_idle.looping())
						this.animationState0.stop();
					else
						this.animationState0.start(this.tickCount);
				}
			}
			this.animationState2.animateWhen(AnimationProcedure.execute(this), this.tickCount);
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
		BlastlingPlusPriObnovlieniiTikaSushchnostiProcedure.execute(this.level(), this);
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 40);
		builder = builder.add(Attributes.ARMOR, 4);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 6);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		return builder;
	}

	@Override
	protected void onBelowWorld() {
		// End mobs ignore the void - falling below the world should not deal damage
	}
}
