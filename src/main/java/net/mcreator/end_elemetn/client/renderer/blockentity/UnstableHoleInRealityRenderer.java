package net.mcreator.end_elemetn.client.renderer.blockentity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;

import net.mcreator.end_elemetn.block.entity.UnstableHoleInRealityBlockEntity;

public class UnstableHoleInRealityRenderer extends TheEndPortalRenderer<UnstableHoleInRealityBlockEntity> {
	public UnstableHoleInRealityRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected float getOffsetUp() {
		return 1.0F;
	}

	@Override
	protected float getOffsetDown() {
		return 0.0F;
	}
}
