package net.mcreator.end_elemetn.block;

import net.minecraftforge.common.util.ForgeSoundType;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.end_elemetn.procedures.EndFirePriStolknovieniiSushchnostiSBlokomProcedure;

import javax.annotation.Nullable;

public class EndFireBlock extends Block {
	public static final BooleanProperty NORTH = FireBlock.NORTH;
	public static final BooleanProperty EAST = FireBlock.EAST;
	public static final BooleanProperty SOUTH = FireBlock.SOUTH;
	public static final BooleanProperty WEST = FireBlock.WEST;
	public static final BooleanProperty UP = FireBlock.UP;

	private static final VoxelShape UP_AABB = Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
	private static final VoxelShape WEST_AABB = Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
	private static final VoxelShape EAST_AABB = Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);
	private static final VoxelShape NORTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
	private static final VoxelShape SOUTH_AABB = Block.box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);
	private static final VoxelShape DOWN_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

	public EndFireBlock() {
		super(BlockBehaviour.Properties.of()
				.sound(new ForgeSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.fire.ambient")), () -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.fire.ambient")),
						() -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.flintandsteel.use")), () -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.fire.extinguish")),
						() -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.fire.ambient"))))
				.strength(1f, 10f).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).replaceable());
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP);
	}

	@Nullable
	private static BooleanProperty propertyFor(Direction direction) {
		return switch (direction) {
			case NORTH -> NORTH;
			case SOUTH -> SOUTH;
			case EAST -> EAST;
			case WEST -> WEST;
			case UP -> UP;
			default -> null;
		};
	}

	private static BlockState computeConnections(BlockGetter level, BlockPos pos, BlockState state) {
		for (Direction direction : Direction.values()) {
			BooleanProperty property = propertyFor(direction);
			if (property != null) {
				BlockPos neighborPos = pos.relative(direction);
				BlockState neighborState = level.getBlockState(neighborPos);
				state = state.setValue(property, neighborState.isFaceSturdy(level, neighborPos, direction.getOpposite()));
			}
		}
		return state;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return computeConnections(context.getLevel(), context.getClickedPos(), this.defaultBlockState());
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		BooleanProperty property = propertyFor(direction);
		if (property == null)
			return state;
		return state.setValue(property, neighborState.isFaceSturdy(level, neighborPos, direction.getOpposite()));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		VoxelShape shape = Shapes.empty();
		if (state.getValue(UP))
			shape = UP_AABB;
		if (state.getValue(NORTH))
			shape = Shapes.or(shape, NORTH_AABB);
		if (state.getValue(SOUTH))
			shape = Shapes.or(shape, SOUTH_AABB);
		if (state.getValue(EAST))
			shape = Shapes.or(shape, EAST_AABB);
		if (state.getValue(WEST))
			shape = Shapes.or(shape, WEST_AABB);
		return shape.isEmpty() ? DOWN_AABB : shape;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(Blocks.AIR);
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		EndFirePriStolknovieniiSushchnostiSBlokomProcedure.execute(world, entity);
	}
}
