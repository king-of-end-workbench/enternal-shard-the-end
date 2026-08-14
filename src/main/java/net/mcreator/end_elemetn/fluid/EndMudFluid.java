package net.mcreator.end_elemetn.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.end_elemetn.init.EndElemetnModItems;
import net.mcreator.end_elemetn.init.EndElemetnModFluids;
import net.mcreator.end_elemetn.init.EndElemetnModFluidTypes;
import net.mcreator.end_elemetn.init.EndElemetnModBlocks;

public abstract class EndMudFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> EndElemetnModFluidTypes.END_MUD_TYPE.get(), () -> EndElemetnModFluids.END_MUD.get(), () -> EndElemetnModFluids.FLOWING_END_MUD.get())
			.explosionResistance(100f).bucket(() -> EndElemetnModItems.END_MUD_BUCKET.get()).block(() -> (LiquidBlock) EndElemetnModBlocks.END_MUD.get());

	private EndMudFluid() {
		super(PROPERTIES);
	}

	public static class Source extends EndMudFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends EndMudFluid {
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