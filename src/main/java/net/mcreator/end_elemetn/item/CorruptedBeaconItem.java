package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.end_elemetn.procedures.CorruptedBeaconPriShchielchkiePKMProcedure;

public class CorruptedBeaconItem extends Item {
	public CorruptedBeaconItem() {
		super(new Item.Properties().rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return InteractionResultHolder.consume(entity.getItemInHand(hand));
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public void onUseTick(Level world, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
		if (entity instanceof Player player)
			CorruptedBeaconPriShchielchkiePKMProcedure.tick(world, player);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
		if (entity instanceof Player player)
			CorruptedBeaconPriShchielchkiePKMProcedure.stop(world, player);
	}
}
