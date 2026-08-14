package net.mcreator.end_elemetn.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.Minecraft;

import net.mcreator.end_elemetn.entity.VengefulHeartOfEnderEntity;
import net.mcreator.end_elemetn.client.model.animations.vengeful_heartAnimation;
import net.mcreator.end_elemetn.client.model.animations.vengeful_heart21Animation;
import net.mcreator.end_elemetn.client.model.Modelvengeful_heart;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class VengefulHeartOfEnderRenderer extends MobRenderer<VengefulHeartOfEnderEntity, Modelvengeful_heart<VengefulHeartOfEnderEntity>> {
	public VengefulHeartOfEnderRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelvengeful_heart.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<VengefulHeartOfEnderEntity, Modelvengeful_heart<VengefulHeartOfEnderEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("end_elemetn:textures/entities/venegra_eyes.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, VengefulHeartOfEnderEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				AnimatedModel model = new AnimatedModel(Minecraft.getInstance().getEntityModels().bakeLayer(Modelvengeful_heart.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1.0F, 1.0F, 1.0F, 1.0F);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(VengefulHeartOfEnderEntity entity) {
		return new ResourceLocation("end_elemetn:textures/entities/venegra.png");
	}

	private static final class AnimatedModel extends Modelvengeful_heart<VengefulHeartOfEnderEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<VengefulHeartOfEnderEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(VengefulHeartOfEnderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, vengeful_heart21Animation.idle, ageInTicks, 1f);
				this.animateWalk(vengeful_heart21Animation.walk, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, vengeful_heart21Animation.shoot, ageInTicks, 1f);
				this.animate(entity.animationState3, vengeful_heartAnimation.endermite, ageInTicks, 1f);
				this.animate(entity.animationState4, vengeful_heartAnimation.shoot3, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(VengefulHeartOfEnderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
			this.head.xRot = headPitch * ((float) Math.PI / 180F);
		}
	}
}