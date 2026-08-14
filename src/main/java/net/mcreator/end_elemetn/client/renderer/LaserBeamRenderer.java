package net.mcreator.end_elemetn.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.end_elemetn.entity.LaserBeamEntity;
import net.mcreator.end_elemetn.client.model.Modellaser_beam;

public class LaserBeamRenderer extends MobRenderer<LaserBeamEntity, Modellaser_beam<LaserBeamEntity>> {
	public LaserBeamRenderer(EntityRendererProvider.Context context) {
		super(context, new Modellaser_beam<LaserBeamEntity>(context.bakeLayer(Modellaser_beam.LAYER_LOCATION)), 0f);
	}

	@Override
	public ResourceLocation getTextureLocation(LaserBeamEntity entity) {
		return ResourceLocation.parse("end_elemetn:textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	protected boolean shouldShowName(LaserBeamEntity entity) {
		return false;
	}

	@Override
	protected void scale(LaserBeamEntity entity, PoseStack poseStack, float partialTickTime) {
		poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));
		float length = Math.max(1, entity.getLaserLength());
		poseStack.scale(0.6F, 0.6F, length);
		// anchor the box's near edge (originally at local Z ~= +0.0625 blocks) on the entity
		// so the stretched far edge (originally at Z ~= -0.875) extends forward, not behind it
		poseStack.translate(0, 0, -0.0625F);
	}
}