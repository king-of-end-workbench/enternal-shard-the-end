package net.mcreator.end_elemetn.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import net.mcreator.end_elemetn.block.entity.UnstableHoleInRealityBlockEntity;

public class UnstableHoleInRealityRenderer extends TheEndPortalRenderer<UnstableHoleInRealityBlockEntity> {
	private static final ResourceLocation BEAM_LOCATION = new ResourceLocation("textures/entity/end_gateway_beam.png");

	public UnstableHoleInRealityRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(UnstableHoleInRealityBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		long gameTime = blockEntity.getLevel().getGameTime();
		BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_LOCATION, partialTick, 1.0F, gameTime, -128, 128, DyeColor.MAGENTA.getTextureDiffuseColors(), 0.15F, 0.175F);
		super.render(blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
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
