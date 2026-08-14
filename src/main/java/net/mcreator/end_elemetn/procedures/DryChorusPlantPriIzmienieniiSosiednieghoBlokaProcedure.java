package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public class DryChorusPlantPriIzmienieniiSosiednieghoBlokaProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get() && (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get()) {
			{
				int _value = 2;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get() && (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get()) {
			{
				int _value = 2;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get() || (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get()
				|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get() || (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == EndElemetnModBlocks.DRY_CHORUS_PLANT.get()) {
			{
				int _value = 1;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else {
			{
				int _value = 0;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		}
	}
}