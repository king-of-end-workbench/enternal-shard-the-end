package net.mcreator.end_elemetn.entity;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.mcreator.end_elemetn.procedures.SnarelingGlobKoghdaSnariadPopadaietVBlokProcedure;
import net.mcreator.end_elemetn.init.EndElemetnModItems;
import net.mcreator.end_elemetn.init.EndElemetnModEntities;

import javax.annotation.Nullable;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class SnarelingGlobEntity extends AbstractArrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(EndElemetnModItems.SNARELINGBLOB.get());
	private int knockback = 0;

	public SnarelingGlobEntity(EntityType<? extends SnarelingGlobEntity> type, Level world) {
		super(type, world);
	}

	public SnarelingGlobEntity(EntityType<? extends SnarelingGlobEntity> type, double x, double y, double z, Level world, @Nullable ItemStack firedFromWeapon) {
		super(type, x, y, z, world, PROJECTILE_ITEM, firedFromWeapon);
		if (firedFromWeapon != null)
			setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
	}

	public SnarelingGlobEntity(EntityType<? extends SnarelingGlobEntity> type, LivingEntity entity, Level world, @Nullable ItemStack firedFromWeapon) {
		super(type, entity, world, PROJECTILE_ITEM, firedFromWeapon);
		if (firedFromWeapon != null)
			setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(EndElemetnModItems.SNARELINGBLOB.get());
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	public void setKnockback(int knockback) {
		this.knockback = knockback;
	}

	@Override
	protected void doKnockback(LivingEntity livingEntity, DamageSource damageSource) {
		if (knockback > 0.0) {
			double d1 = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
			Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(knockback * 0.6 * d1);
			if (vec3.lengthSqr() > 0.0) {
				livingEntity.push(vec3.x, 0.1, vec3.z);
			}
		} else { // knockback might be set by firedFromWeapon passed into constructor
			super.doKnockback(livingEntity, damageSource);
		}
	}

	@Override
	public void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		SnarelingGlobKoghdaSnariadPopadaietVBlokProcedure.execute(this.level(), blockHitResult.getBlockPos(), blockHitResult.getDirection());
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		if (!this.level().isClientSide()) {
			Entity hitEntity = entityHitResult.getEntity();
			BlockPos feetPos = BlockPos.containing(hitEntity.getX(), hitEntity.getY(), hitEntity.getZ());
			SnarelingGlobKoghdaSnariadPopadaietVBlokProcedure.execute(this.level(), feetPos.below(), Direction.UP);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.inGround)
			this.discard();
	}

	public static SnarelingGlobEntity shoot(Level world, LivingEntity entity, RandomSource source) {
		return shoot(world, entity, source, 0f, 5, 0);
	}

	public static SnarelingGlobEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 0f, 5, 0);
	}

	public static SnarelingGlobEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		SnarelingGlobEntity entityarrow = new SnarelingGlobEntity(EndElemetnModEntities.SNARELING_GLOB.get(), entity, world, null);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static SnarelingGlobEntity shoot(LivingEntity entity, LivingEntity target) {
		SnarelingGlobEntity entityarrow = new SnarelingGlobEntity(EndElemetnModEntities.SNARELING_GLOB.get(), entity, entity.level(), null);
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(5);
		entityarrow.setKnockback(0);
		entityarrow.setCritArrow(false);
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}