package net.mcreator.end_elemetn.fluid;

import net.minecraftforge.fluids.ForgeFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.end_elemetn.init.EndElemetnModItems;
import net.mcreator.end_elemetn.init.EndElemetnModFluids;
import net.mcreator.end_elemetn.init.EndElemetnModFluidTypes;
import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public abstract class VoidLiquidFluid extends ForgeFlowingFluid {
	public static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(() -> EndElemetnModFluidTypes.VOID_LIQUID_TYPE.get(), () -> EndElemetnModFluids.VOID_LIQUID.get(), () -> EndElemetnModFluids.FLOWING_VOID_LIQUID.get())
			.explosionResistance(100f).tickRate(30).bucket(() -> EndElemetnModItems.VOID_LIQUID_BUCKET.get()).block(() -> (LiquidBlock) EndElemetnModBlocks.VOID_LIQUID.get());

	private VoidLiquidFluid() {
		super(PROPERTIES);
	}

	public static class Source extends VoidLiquidFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends VoidLiquidFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}