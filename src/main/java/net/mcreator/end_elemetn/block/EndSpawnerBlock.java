package net.mcreator.end_elemetn.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.block.entity.EndSpawnerBlockEntity;
import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

public class EndSpawnerBlock extends Block implements EntityBlock {
	// 0 = Endstone (ordinary mobs, plainest loot), 1 = End City (stronger, more variety, better
	// loot), 2 = Soulful (crying mobs, super loot, potion-buffed). Normally only ever goes UP, driven
	// by a nearby player's Bad Omen level (I-III -> End City, IV-V -> Soulful) - unless a creative
	// player manually locks it via sneak + empty-hand right click.
	public static final IntegerProperty TIER = IntegerProperty.create("tier", 0, 2);
	// 0 = idle/waiting out its cooldown, 1 = a trial (waves) is in progress, 2 = ejecting its reward.
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 2);
	private static final String[] TIER_NAMES = { "Endstone", "End City", "Soulful" };

	public EndSpawnerBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.TRIAL_SPAWNER).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(TIER, 0).setValue(BLOCKSTATE, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(TIER, BLOCKSTATE);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new EndSpawnerBlockEntity(pos, state);
	}

	// Real Trial Spawner logic instead of the old hand-rolled scheduleTick loop: waves, cooldown and
	// reward ejection all live in EndSpawnerBlockEntity.serverTick, with a lightweight clientTick just
	// for the idle particle drift and the spinning mob preview.
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (type != EndElemetnModBlockEntities.END_SPAWNER.get())
			return null;
		return level.isClientSide ? (lvl, pos, st, be) -> {
			if (be instanceof EndSpawnerBlockEntity spawner)
				EndSpawnerBlockEntity.clientTick(lvl, pos, st, spawner);
		} : (lvl, pos, st, be) -> {
			if (be instanceof EndSpawnerBlockEntity spawner)
				EndSpawnerBlockEntity.serverTick(lvl, pos, st, spawner);
		};
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (stack.getItem() instanceof SpawnEggItem egg) {
			if (!world.isClientSide()) {
				BlockEntity be = world.getBlockEntity(pos);
				if (be instanceof EndSpawnerBlockEntity spawner) {
					EntityType<?> type = egg.getType(stack);
					spawner.setDesignatedMob(type);
					stack.shrink(1);
					player.displayClientMessage(Component.literal("Спавнер запомнил: ").append(type.getDescription()), true);
				}
			}
			return ItemInteractionResult.SUCCESS;
		}
		return super.useItemOn(stack, state, world, pos, player, hand, hit);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
		super.useWithoutItem(state, world, pos, player, hit);
		// Creative-only way to fix the tier by hand (sneak + empty-hand right click) - this also
		// locks it so a later Bad Omen visitor doesn't silently change it again.
		if (player.isCreative() && player.isShiftKeyDown()) {
			if (!world.isClientSide()) {
				int nextTier = (state.getValue(TIER) + 1) % 3;
				world.setBlock(pos, state.setValue(TIER, nextTier), 3);
				BlockEntity be = world.getBlockEntity(pos);
				if (be instanceof EndSpawnerBlockEntity spawner) {
					spawner.setTierLocked(true);
					// Otherwise the previously-designated mob (rolled under the old tier) would keep
					// spawning until a full wave cycle happened to finish on its own.
					spawner.resetDesignatedMob();
				}
				player.displayClientMessage(Component.literal("Спавнер: " + TIER_NAMES[nextTier] + " (закреплено)"), true);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof EndSpawnerBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof EndSpawnerBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}
