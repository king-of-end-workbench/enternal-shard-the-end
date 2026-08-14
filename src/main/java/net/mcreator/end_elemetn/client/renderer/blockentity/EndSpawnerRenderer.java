package net.mcreator.end_elemetn.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import net.mcreator.end_elemetn.block.entity.EndSpawnerBlockEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Mirrors vanilla's spawner cage preview: the designated mob is never actually spawned into the
 * world here, just created once (EntityType#create, no side effects) and cached per type so it can
 * be rendered spinning inside the block, matching the vault's own render-only approach.
 */
public class EndSpawnerRenderer implements BlockEntityRenderer<EndSpawnerBlockEntity> {
	private final EntityRenderDispatcher entityRenderer;
	private final Map<EntityType<?>, Entity> displayEntities = new HashMap<>();

	public EndSpawnerRenderer(BlockEntityRendererProvider.Context context) {
		this.entityRenderer = context.getEntityRenderer();
	}

	@Override
	public void render(EndSpawnerBlockEntity be, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		EntityType<?> type = be.getDesignatedMob();
		if (type == null || be.getLevel() == null)
			return;

		Entity display = displayEntities.computeIfAbsent(type, t -> t.create(be.getLevel()));
		if (display == null)
			return;

		float size = Math.max(display.getBbWidth(), display.getBbHeight());
		float scale = size > 1.0F ? 1.0F / size : 1.0F;

		poseStack.pushPose();
		poseStack.translate(0.5F, 0.4F, 0.5F);
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.rotLerp(partialTicks, be.getPreviousSpin(), be.getSpin())));
		poseStack.scale(scale, scale, scale);
		display.setYRot(0.0F);
		display.setYHeadRot(0.0F);
		display.tickCount++;
		this.entityRenderer.render(display, 0.0, 0.0, 0.0, 0.0F, partialTicks, poseStack, bufferSource, packedLight);
		poseStack.popPose();
	}
}
