package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;
import net.mcreator.end_elemetn.init.EndElemetnModItems;

public class DimensionalCutterItem extends Item {
	private static final String CHARGE_TAG = "dimensional_charge";

	public DimensionalCutterItem() {
		super(new Item.Properties().durability(1000).attributes(ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
	}

	@Override
	public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
		return 1;
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
		itemstack.hurtAndBreak(1, entity, LivingEntity.getSlotForHand(entity.getUsedItemHand()));
		return true;
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		itemstack.hurtAndBreak(2, entity, LivingEntity.getSlotForHand(entity.getUsedItemHand()));
		return true;
	}

	@Override
	public int getEnchantmentValue() {
		return 2;
	}

	private static int getCharge(ItemStack stack) {
		return stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt(CHARGE_TAG);
	}

	private static void setCharge(ItemStack stack, int charge) {
		CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> tag.putInt(CHARGE_TAG, charge));
	}

	private static ItemStack findFullEndBottle(Player player) {
		for (ItemStack stack : player.getInventory().items) {
			if (stack.is(EndElemetnModItems.FULL_END_BOTTLE.get()))
				return stack;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (getCharge(stack) < 2 && !findFullEndBottle(player).isEmpty()) {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(stack);
		}
		return InteractionResultHolder.fail(stack);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {
		return 20;
	}

	@Override
	public void onUseTick(Level world, LivingEntity entity, ItemStack itemstack, int remainingUseDuration) {
		if (world.isClientSide) {
			RandomSource random = world.random;
			for (int i = 0; i < 3; i++) {
				double angle = random.nextDouble() * Math.PI * 2;
				double radius = 0.5 + random.nextDouble();
				double x = entity.getX() + Math.cos(angle) * radius;
				double y = entity.getY() + entity.getBbHeight() * random.nextDouble();
				double z = entity.getZ() + Math.sin(angle) * radius;
				double dx = (entity.getX() - x) * 0.1;
				double dy = (entity.getY() + entity.getBbHeight() * 0.5 - y) * 0.1;
				double dz = (entity.getZ() - z) * 0.1;
				world.addParticle(ParticleTypes.PORTAL, x, y, z, dx, dy, dz);
			}
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		if (!world.isClientSide && entity instanceof Player player) {
			ItemStack bottle = findFullEndBottle(player);
			if (!bottle.isEmpty()) {
				bottle.shrink(1);
				setCharge(itemstack, 2);
			}
		}
		return itemstack;
	}

	@Override
	public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity, InteractionHand hand) {
		boolean retval = super.onEntitySwing(itemstack, entity, hand);
		int charge = getCharge(itemstack);
		if (charge > 0 && entity.level() instanceof ServerLevel serverLevel) {
			setCharge(itemstack, charge - 1);
			Vec3 look = entity.getLookAngle();
			BlockPos base = BlockPos.containing(entity.getX() + look.x * 2, entity.getY(), entity.getZ() + look.z * 2);
			BlockState holeState = EndElemetnModBlocks.HOLE_IN_REALITY.get().defaultBlockState();
			serverLevel.setBlockAndUpdate(base, holeState);
			serverLevel.setBlockAndUpdate(base.above(), holeState);
		}
		return retval;
	}
}