package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.minecraftforge.network.PacketDistributor;

import net.mcreator.end_elemetn.EndElemetnMod;

import net.mcreator.end_elemetn.procedures.EnternslScytheProcedure;
import net.mcreator.end_elemetn.network.ScytheSpinMessage;

import java.util.List;

/**
 * Shared behavior for every scythe tier: normal attacks splash to nearby enemies (bonus damage the
 * more targets are caught), tilling auto-plants from inventory, and a held right-click unleashes a
 * spinning AoE strike.
 */
public abstract class AbstractScytheItem extends HoeItem {
	// Holding the spin without releasing chains multiple rotations: each successive rotation is
	// ROTATION_ACCEL_TICKS faster (down to MIN_ROTATION_TICKS), and the damage/cooldown of the
	// eventual hit scale with how many full rotations were actually completed.
	private static final int BASE_ROTATION_TICKS = 24;
	private static final int ROTATION_ACCEL_TICKS = 3;
	private static final int MIN_ROTATION_TICKS = 12;
	private static final int MIN_HOLD_FOR_SPIN_TICKS = 12;
	private static final int SUPER_HIT_SPIN_THRESHOLD = 5;
	private static final float SUPER_HIT_DAMAGE_MULTIPLIER = 1.5f;
	private static final float SUPER_HIT_RADIUS_MULTIPLIER = 1.3f;
	private static final float CROWD_DAMAGE_PER_TARGET = 0.25f;
	private static final float CROWD_DAMAGE_CAP = 3f;

	private final double meleeSplashRadius;
	private final float meleeSplashDamage;
	private final double spinRadius;
	private final float spinDamage;
	private final int spinCooldownTicks;
	private final int maxSpinCount;

	// HoeItem (via DiggerItem) already builds its own attack damage/speed attribute modifiers from
	// these two args - no need to duplicate that here like AbstractHammerItem (a plain TieredItem)
	// has to.
	protected AbstractScytheItem(Tier tier, float attackDamage, float attackSpeed, double meleeSplashRadius, float meleeSplashDamage, double spinRadius,
			float spinDamage, int spinCooldownTicks, int maxSpinCount) {
		super(tier, (int) attackDamage, attackSpeed, new Item.Properties());
		this.meleeSplashRadius = meleeSplashRadius;
		this.meleeSplashDamage = meleeSplashDamage;
		this.spinRadius = spinRadius;
		this.spinDamage = spinDamage;
		this.spinCooldownTicks = spinCooldownTicks;
		this.maxSpinCount = maxSpinCount;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return InteractionResultHolder.consume(entity.getItemInHand(hand));
	}

	private int rotationTicks(int rotationIndex) {
		return Math.max(MIN_ROTATION_TICKS, BASE_ROTATION_TICKS - rotationIndex * ROTATION_ACCEL_TICKS);
	}

	private int totalSpinTicks() {
		int total = 0;
		for (int i = 0; i < maxSpinCount; i++)
			total += rotationTicks(i);
		return total;
	}

	private int rotationIndexAt(int ticksElapsed) {
		int cumulative = 0;
		for (int i = 0; i < maxSpinCount; i++) {
			cumulative += rotationTicks(i);
			if (ticksElapsed < cumulative)
				return i;
		}
		return maxSpinCount - 1;
	}

	private int spinsCompletedAt(int ticksElapsed) {
		int cumulative = 0;
		int completed = 0;
		for (int i = 0; i < maxSpinCount; i++) {
			cumulative += rotationTicks(i);
			if (ticksElapsed < cumulative)
				break;
			completed++;
		}
		return completed;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return totalSpinTicks();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public void onUseTick(Level world, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
		if (entity instanceof ServerPlayer serverPlayer) {
			int ticksElapsed = totalSpinTicks() - remainingUseDuration;
			float yawDelta = 360f / rotationTicks(rotationIndexAt(ticksElapsed));
			EndElemetnMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new ScytheSpinMessage(yawDelta));
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
		int ticksUsed = totalSpinTicks() - timeLeft;
		int spinsPerformed = spinsCompletedAt(ticksUsed);
		if (spinsPerformed == 0 && ticksUsed >= MIN_HOLD_FOR_SPIN_TICKS) {
			spinsPerformed = 1;
		}
		if (spinsPerformed > 0) {
			spin(stack, world, entity, spinsPerformed);
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		spin(stack, world, entity, maxSpinCount);
		return stack;
	}

	private void spin(ItemStack stack, Level world, LivingEntity entity, int spinsPerformed) {
		if (entity instanceof Player player && player.getCooldowns().isOnCooldown(stack.getItem())) {
			return;
		}
		boolean superHit = spinsPerformed >= SUPER_HIT_SPIN_THRESHOLD;
		float damage = spinDamage * spinsPerformed;
		double radius = spinRadius;
		if (superHit) {
			damage *= SUPER_HIT_DAMAGE_MULTIPLIER;
			radius *= SUPER_HIT_RADIUS_MULTIPLIER;
		}
		EnternslScytheProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, stack, radius, damage, superHit);
		if (entity instanceof Player player) {
			player.getCooldowns().addCooldown(stack.getItem(), spinCooldownTicks * spinsPerformed);
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (attacker.level() instanceof ServerLevel level) {
			DamageSource source = attacker instanceof Player playerAttacker ? attacker.damageSources().playerAttack(playerAttacker) : attacker.damageSources().mobAttack(attacker);
			List<LivingEntity> nearbyMobs = level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(meleeSplashRadius),
					e -> e != target && e != attacker);
			float crowdMultiplier = Math.min(1f + CROWD_DAMAGE_PER_TARGET * nearbyMobs.size(), CROWD_DAMAGE_CAP);
			for (LivingEntity nearby : nearbyMobs) {
				nearby.hurt(source, meleeSplashDamage * crowdMultiplier);
			}
			level.playSound(null, target.getX(), target.getY(), target.getZ(), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.player.attack.sweep")), SoundSource.PLAYERS, 1f, 1f);
			level.sendParticles(ParticleTypes.SWEEP_ATTACK, target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(), 5, 1, 1, 1, 1);
			harvestNearbyCrops(level, target, attacker, stack);
		}
		return true;
	}

	private void harvestNearbyCrops(ServerLevel level, LivingEntity target, LivingEntity attacker, ItemStack stack) {
		BlockPos center = BlockPos.containing(target.getX(), target.getY(), target.getZ());
		int radius = (int) Math.ceil(meleeSplashRadius);
		for (BlockPos pos : BlockPos.betweenClosed(center.offset(-radius, -1, -radius), center.offset(radius, 1, radius))) {
			BlockState cropState = level.getBlockState(pos);
			if (cropState.getBlock() instanceof CropBlock crop && crop.isMaxAge(cropState)) {
				Block.dropResources(cropState, level, pos, null, attacker, stack);
				level.setBlock(pos, crop.getStateForAge(0), 2);
			}
		}
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		InteractionResult result = super.useOn(context);
		if (result.consumesAction() && context.getLevel() instanceof ServerLevel level) {
			BlockPos tilledPos = context.getClickedPos();
			BlockPos plantPos = tilledPos.above();
			if (level.getBlockState(tilledPos).is(Blocks.FARMLAND) && level.getBlockState(plantPos).isAir()) {
				Player player = context.getPlayer();
				if (player != null) {
					plantFromInventory(level, plantPos, player);
				}
			}
		}
		return result;
	}

	private void plantFromInventory(ServerLevel level, BlockPos plantPos, Player player) {
		for (ItemStack stack : player.getInventory().items) {
			Block crop = seedToCrop(stack);
			if (crop != null) {
				level.setBlockAndUpdate(plantPos, crop.defaultBlockState());
				stack.shrink(1);
				break;
			}
		}
	}

	private Block seedToCrop(ItemStack stack) {
		if (stack.is(Items.WHEAT_SEEDS))
			return Blocks.WHEAT;
		if (stack.is(Items.CARROT))
			return Blocks.CARROTS;
		if (stack.is(Items.POTATO))
			return Blocks.POTATOES;
		if (stack.is(Items.BEETROOT_SEEDS))
			return Blocks.BEETROOTS;
		return null;
	}
}
