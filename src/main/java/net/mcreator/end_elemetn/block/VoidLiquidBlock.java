package net.mcreator.end_elemetn.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.procedures.VoidBlockPriStolknovieniiSushchnostiSBlokomProcedure;
import net.mcreator.end_elemetn.init.EndElemetnModFluids;

public class VoidLiquidBlock extends LiquidBlock {
	public VoidLiquidBlock() {
		super(EndElemetnModFluids.VOID_LIQUID.get(), BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).strength(100f).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		VoidBlockPriStolknovieniiSushchnostiSBlokomProcedure.execute(entity);
	}
}