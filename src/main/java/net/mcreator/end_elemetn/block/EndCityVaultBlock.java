package net.mcreator.end_elemetn.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.procedures.EndCityVaultPriShchielchkiePKMPoBlokuProcedure;
import net.mcreator.end_elemetn.block.entity.EndCityVaultBlockEntity;
import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

import javax.annotation.Nullable;

public class EndCityVaultBlock extends Block implements EntityBlock {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 2);
	// 0 = Endstone (plainest loot), 1 = End City (better loot/variety), 2 = Soulful (best loot). Only
	// ever changed by hand in creative mode - vaults don't get "fought" like spawners do, so there's
	// no natural in-world trigger to progress this the way Bad Omen does for EndSpawnerBlock.
	public static final IntegerProperty TIER = IntegerProperty.create("tier", 0, 2);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final int REWARD_TIER_COUNT = 6;
	private static final String[] TIER_NAMES = { "Endstone", "End City", "Soulful" };
	private static final VoxelShape SHAPE_NORTH = box(0, 0, 0, 16, 16, 16);
	private static final VoxelShape SHAPE_SOUTH = box(0, 0, 0, 16, 16, 16);
	private static final VoxelShape SHAPE_EAST = box(0, 0, 0, 16, 16, 16);
	private static final VoxelShape SHAPE_WEST = box(0, 0, 0, 16, 16, 16);

	public EndCityVaultBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.VAULT).strength(1f, 10f).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				// Was always returning 0 no matter the state - lights up once a player is detected
				// (blockstate 1) or the vault is actively ejecting its reward (blockstate 2).
				if (s.getValue(BLOCKSTATE) == 1)
					return 7;
				if (s.getValue(BLOCKSTATE) == 2)
					return 15;
				return 0;
			}
		}.getLightLevel())).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TIER, 0));
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
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return (switch (state.getValue(FACING)) {
			case NORTH -> SHAPE_NORTH;
			case SOUTH -> SHAPE_SOUTH;
			case EAST -> SHAPE_EAST;
			case WEST -> SHAPE_WEST;
			default -> SHAPE_NORTH;
		});
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, BLOCKSTATE, TIER);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new EndCityVaultBlockEntity(pos, state);
	}

	// Matches vanilla's own VaultBlock: a real BlockEntityTicker instead of a hand-rolled
	// scheduleTick loop, with the client and server halves kept separate exactly like vanilla does
	// (server cycles the reward tier and detects players; client only advances the visual spin).
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (type != EndElemetnModBlockEntities.END_CITY_VAULT.get())
			return null;
		return level.isClientSide ? (lvl, pos, st, be) -> {
			if (be instanceof EndCityVaultBlockEntity vaultEntity)
				EndCityVaultBlockEntity.clientTick(lvl, pos, st, vaultEntity);
		} : (lvl, pos, st, be) -> {
			if (be instanceof EndCityVaultBlockEntity vaultEntity)
				EndCityVaultBlockEntity.serverTick(lvl, pos, st, vaultEntity);
		};
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		// Creative-only way to pick which of the 3 loot stages this vault is: sneak + empty-hand
		// right-click cycles it. There's no natural in-world trigger for a vault the way Bad Omen
		// drives the spawner's tier, so this has to be a deliberate creative-mode action.
		if (entity.isCreative() && entity.isShiftKeyDown()) {
			if (!world.isClientSide()) {
				int nextTier = (blockstate.getValue(TIER) + 1) % 3;
				world.setBlock(pos, blockstate.setValue(TIER, nextTier), 3);
				entity.displayClientMessage(Component.literal("Хранилище: " + TIER_NAMES[nextTier]), true);
			}
			return InteractionResult.SUCCESS;
		}
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		EndCityVaultPriShchielchkiePKMPoBlokuProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}
}