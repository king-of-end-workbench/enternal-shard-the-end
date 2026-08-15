package net.mcreator.end_elemetn.block;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.EndElemetnMod;

import java.util.List;

/**
 * Sits at the bottom of a Greenstone Springs lake. Whenever there's water directly above it, it
 * has a random chance each check to erupt: for GEYSER_DURATION_TICKS it shoots water splash and
 * campfire smoke up through the water column, and gently keeps bouncing any living entity
 * standing in that column, like riding a bubble-column fountain.
 *
 * Registered here (not through EndElemetnModBlocks/Items/Tabs) since those files are regenerated
 * by MCreator on every build and would silently drop a hand-added entry.
 */
public class GreenstoneGaserBlock extends Block {
	public static final IntegerProperty ACTIVE_TICKS = IntegerProperty.create("active_ticks", 0, 160);

	private static final int GEYSER_DURATION_TICKS = 160;
	private static final int ERUPTION_CHANCE = 150;
	private static final double BOUNCE_VELOCITY = 0.32;
	private static final double COLUMN_RADIUS = 0.4;
	private static final double COLUMN_HEIGHT = 3.0;

	public static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EndElemetnMod.MODID);
	public static final RegistryObject<Block> GREENSTONE_GASER = BLOCK_REGISTRY.register("greenstone_gaser", GreenstoneGaserBlock::new);

	public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EndElemetnMod.MODID);
	public static final RegistryObject<Item> GREENSTONE_GASER_ITEM = ITEM_REGISTRY.register("greenstone_gaser",
			() -> new BlockItem(GREENSTONE_GASER.get(), new Item.Properties()));

	public GreenstoneGaserBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops());
		registerDefaultState(this.stateDefinition.any().setValue(ACTIVE_TICKS, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ACTIVE_TICKS);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(ACTIVE_TICKS) == 0;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (state.getValue(ACTIVE_TICKS) > 0)
			return;
		if (!level.getFluidState(pos.above()).is(FluidTags.WATER))
			return;
		if (random.nextInt(ERUPTION_CHANCE) != 0)
			return;
		level.setBlock(pos, state.setValue(ACTIVE_TICKS, GEYSER_DURATION_TICKS), 3);
		level.scheduleTick(pos, this, 1);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int remaining = state.getValue(ACTIVE_TICKS);
		if (remaining <= 0)
			return;
		erupt(level, pos);
		remaining--;
		level.setBlock(pos, state.setValue(ACTIVE_TICKS, remaining), remaining == 0 ? 3 : 2);
		if (remaining > 0) {
			level.scheduleTick(pos, this, 1);
		}
	}

	private void erupt(ServerLevel level, BlockPos pos) {
		double x = pos.getX() + 0.5;
		double y = pos.getY() + 1.0;
		double z = pos.getZ() + 0.5;
		level.sendParticles(ParticleTypes.SPLASH, x, y, z, 6, 0.3, 0.2, 0.3, 0.15);
		level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 2, 0.2, 0.3, 0.2, 0.02);

		AABB column = new AABB(pos).inflate(COLUMN_RADIUS, 0, COLUMN_RADIUS).expandTowards(0, COLUMN_HEIGHT, 0);
		List<LivingEntity> riders = level.getEntitiesOfClass(LivingEntity.class, column, LivingEntity::isAlive);
		for (LivingEntity rider : riders) {
			if (rider.getDeltaMovement().y < BOUNCE_VELOCITY) {
				rider.setDeltaMovement(rider.getDeltaMovement().x, BOUNCE_VELOCITY, rider.getDeltaMovement().z);
				rider.hurtMarked = true;
				rider.fallDistance = 0f;
			}
		}
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
