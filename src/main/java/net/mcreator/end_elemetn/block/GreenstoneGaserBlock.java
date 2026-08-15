package net.mcreator.end_elemetn.block;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.block.entity.GreenstoneGaserBlockEntity;
import net.mcreator.end_elemetn.EndElemetnMod;

import javax.annotation.Nullable;

/**
 * Sits at the bottom of a Greenstone Springs lake. Whenever there's water directly above it, it
 * erupts on a strict cycle: a stretch of shooting water splash and campfire smoke up through the
 * water column (gently bouncing any living entity standing in it), then a cooldown before
 * erupting again. The actual timing/state lives in GreenstoneGaserBlockEntity, not blockstate
 * properties.
 *
 * Registered here (not through EndElemetnModBlocks/Items/Tabs) since those files are regenerated
 * by MCreator on every build and would silently drop a hand-added entry.
 */
public class GreenstoneGaserBlock extends Block implements EntityBlock {
	public static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EndElemetnMod.MODID);
	public static final RegistryObject<Block> GREENSTONE_GASER = BLOCK_REGISTRY.register("greenstone_gaser", GreenstoneGaserBlock::new);

	public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EndElemetnMod.MODID);
	public static final RegistryObject<Item> GREENSTONE_GASER_ITEM = ITEM_REGISTRY.register("greenstone_gaser",
			() -> new BlockItem(GREENSTONE_GASER.get(), new Item.Properties()));

	public GreenstoneGaserBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (level.getBlockEntity(pos) instanceof GreenstoneGaserBlockEntity gaser) {
			gaser.tryStartEruption(level, pos, random);
		}
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new GreenstoneGaserBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (level.isClientSide || type != GreenstoneGaserBlockEntity.GREENSTONE_GASER_ENTITY.get())
			return null;
		return (lvl, pos, st, be) -> ((GreenstoneGaserBlockEntity) be).tick(lvl, pos, st);
	}

	@Mod.EventBusSubscriber
	public static class Tab {
		@SubscribeEvent
		public static void buildTabContents(BuildCreativeModeTabContentsEvent tabData) {
			if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
				tabData.accept(GREENSTONE_GASER_ITEM.get());
			}
		}
	}
}
