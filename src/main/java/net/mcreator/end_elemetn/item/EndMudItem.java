package net.mcreator.end_elemetn.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.mcreator.end_elemetn.init.EndElemetnModFluids;

public class EndMudItem extends BucketItem {
	public EndMudItem() {
		super(EndElemetnModFluids.END_MUD.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)

		);
	}
}