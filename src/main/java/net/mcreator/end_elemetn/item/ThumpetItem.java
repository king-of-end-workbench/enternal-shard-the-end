package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.end_elemetn.procedures.ThumpetPriShchielchkiePKMProcedure;

public class ThumpetItem extends Item {
	public ThumpetItem() {
		super(new Item.Properties());
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ThumpetPriShchielchkiePKMProcedure.execute(entity);
		return ar;
	}
}