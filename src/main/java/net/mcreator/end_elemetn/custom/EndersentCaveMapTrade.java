package net.mcreator.end_elemetn.custom;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

/**
 * Reimplements vanilla's VillagerTrades.TreasureMapForEmeralds, but sets the resulting map's
 * name as a literal Component instead of a translation key, since this mod's generated lang
 * files do not carry hand-added translation keys across rebuilds.
 *
 * forge-1.20.1 has no custom-registrable map decoration type (that's a later addition), so this
 * uses the closest built-in MapDecoration.Type instead of a mod-specific one.
 */
public class EndersentCaveMapTrade implements VillagerTrades.ItemListing {
	private final int emeraldCost;
	private final TagKey<Structure> destination;
	private final String displayName;
	private final MapDecoration.Type destinationType;
	private final int maxUses;
	private final int villagerXp;

	public EndersentCaveMapTrade(int emeraldCost, TagKey<Structure> destination, String displayName, MapDecoration.Type destinationType, int maxUses, int villagerXp) {
		this.emeraldCost = emeraldCost;
		this.destination = destination;
		this.displayName = displayName;
		this.destinationType = destinationType;
		this.maxUses = maxUses;
		this.villagerXp = villagerXp;
	}

	@Nullable
	@Override
	public MerchantOffer getOffer(Entity trader, RandomSource random) {
		if (!(trader.level() instanceof ServerLevel serverLevel))
			return null;
		BlockPos blockpos = serverLevel.findNearestMapStructure(this.destination, trader.blockPosition(), 100, true);
		if (blockpos == null)
			return null;
		ItemStack itemstack = MapItem.create(serverLevel, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
		MapItem.renderBiomePreviewMap(serverLevel, itemstack);
		MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
		itemstack.setHoverName(Component.literal(this.displayName));
		return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
	}
}
