package net.mcreator.end_elemetn.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.end_elemetn.procedures.EnternalLootboxPriShchielchkiePKMProcedure;

public class EnderiteLootboxItem extends Item {
	public EnderiteLootboxItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		EnternalLootboxPriShchielchkiePKMProcedure.execute(entity, ar.getObject());
		return ar;
	}
}