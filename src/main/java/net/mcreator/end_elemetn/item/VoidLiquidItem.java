package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.mcreator.end_elemetn.init.EndElemetnModFluids;

public class VoidLiquidItem extends BucketItem {
	public VoidLiquidItem() {
		super(EndElemetnModFluids.VOID_LIQUID.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)

		);
	}
}