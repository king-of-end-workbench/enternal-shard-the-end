package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import net.mcreator.end_elemetn.procedures.CornSeedsPriShchielchkiePKMPoBlokuProcedure;

public class CornSeedsItem extends Item {
	public CornSeedsItem() {
		super(new Item.Properties());
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		CornSeedsPriShchielchkiePKMPoBlokuProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ());
		return InteractionResult.SUCCESS;
	}
}