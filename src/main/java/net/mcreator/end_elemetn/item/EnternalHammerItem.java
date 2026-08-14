package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class EnternalHammerItem extends TieredItem {
	private static final int SLAM_CHARGE_TICKS = 20;
	private static final int MIN_HOLD_FOR_SLAM_TICKS = 10;

	private static final double SLAM_RADIUS = 5.0;
	private static final float SLAM_DAMAGE = 18f;
	private static final double SLAM_KNOCKBACK = 1.2;
	private static final int SLAM_COOLDOWN_TICKS = 90;

	// Deliberately slower than netherite (9) per swing - the hammer's payoff is breaking a 3x3 area at once, not raw speed.
	private static final float MINE_SPEED = 7f;

	private static final Tier TOOL_TIER = new Tier() {
		@Override
		public int getUses() {
			return 1000;
		}

		@Override
		public float getSpeed() {
			return MINE_SPEED;
		}

		@Override
		public float getAttackDamageBonus() {
			return 0;
		}

		@Override
		public TagKey<Block> getIncorrectBlocksForDrops() {
			return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
		}

		@Override
		public int getEnchantmentValue() {
			return 6;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.of(new ItemStack(EndElemetnModItems.ENDERITE.get()));
		}
	};

	private static Tool buildTool() {
		return new Tool(List.of(Tool.Rule.deniesDrops(BlockTags.INCORRECT_FOR_NETHERITE_TOOL), Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_PICKAXE, MINE_SPEED),
				Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_AXE, MINE_SPEED), Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_SHOVEL, MINE_SPEED)), 1.0f, 1);
	}

	public EnternalHammerItem() {
		super(TOOL_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(TOOL_TIER, 26f, -4f)).component(DataComponents.TOOL, buildTool()));
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return true;
	}

	@Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
		boolean result = super.mineBlock(stack, level, state, pos, miner);
		if (!level.isClientSide && miner instanceof Player) {
			Vec3 look = miner.getLookAngle();
			Direction facing = Direction.getNearest(look.x, look.y, look.z);
			Direction.Axis axis = facing.getAxis();
			for (int a = -1; a <= 1; a++) {
				for (int b = -1; b <= 1; b++) {
					if (a == 0 && b == 0)
						continue;
					BlockPos extra = switch (axis) {
						case X -> pos.offset(0, a, b);
						case Y -> pos.offset(a, 0, b);
						case Z -> pos.offset(a, b, 0);
					};
					breakExtra(level, extra, miner, stack);
				}
			}
		}
		return result;
	}

	private void breakExtra(Level level, BlockPos pos, LivingEntity miner, ItemStack stack) {
		BlockState state = level.getBlockState(pos);
		if (state.isAir())
			return;
		if (state.getDestroySpeed(level, pos) < 0)
			return;
		if (state.requiresCorrectToolForDrops() && !stack.isCorrectToolForDrops(state))
			return;
		level.levelEvent(2001, pos, Block.getId(state));
		Block.dropResources(state, level, pos, level.getBlockEntity(pos), miner, stack);
		level.removeBlock(pos, false);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return InteractionResultHolder.consume(entity.getItemInHand(hand));
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return SLAM_CHARGE_TICKS;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
		int ticksUsed = SLAM_CHARGE_TICKS - timeLeft;
		if (ticksUsed >= MIN_HOLD_FOR_SLAM_TICKS) {
			groundSlam(world, entity, stack);
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		groundSlam(world, entity, stack);
		return stack;
	}

	private void groundSlam(Level world, LivingEntity entity, ItemStack stack) {
		if (!(world instanceof ServerLevel level))
			return;
		if (entity instanceof Player player && player.getCooldowns().isOnCooldown(stack.getItem()))
			return;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		DamageSource source = entity instanceof Player playerAttacker ? entity.damageSources().playerAttack(playerAttacker) : entity.damageSources().mobAttack(entity);
		List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(SLAM_RADIUS), e -> e != entity);
		for (LivingEntity target : nearby) {
			target.hurt(source, SLAM_DAMAGE);
			target.knockback(SLAM_KNOCKBACK, target.getX() - x, target.getZ() - z);
		}
		level.playSound(null, x, y, z, SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.5f, 0.7f);
		level.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 1, 0, 0, 0, 0);
		level.sendParticles(ParticleTypes.CRIT, x, y + 0.2, z, 24, SLAM_RADIUS / 2, 0.1, SLAM_RADIUS / 2, 0.3);
		if (entity instanceof Player player) {
			player.getCooldowns().addCooldown(stack.getItem(), SLAM_COOLDOWN_TICKS);
		}
	}
}
