package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import net.mcreator.end_elemetn.procedures.EndBottlePriShchielchkiePKMPoBlokuProcedure;

public class EndBottleItem extends Item {
	public EndBottleItem() {
		super(new Item.Properties());
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		EndBottlePriShchielchkiePKMPoBlokuProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getPlayer());
		return InteractionResult.SUCCESS;
	}
}