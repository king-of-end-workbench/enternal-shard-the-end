package net.mcreator.end_elemetn.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.end_elemetn.entity.EnderJellyfishEntity;
import net.mcreator.end_elemetn.client.model.animations.ENDJAnimation;
import net.mcreator.end_elemetn.client.model.animations.JELLYAnimation;
import net.mcreator.end_elemetn.client.model.ModelENDJ;
import net.mcreator.end_elemetn.client.model.ModelJELLY;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class EnderJellyfishRenderer extends MobRenderer<EnderJellyfishEntity, ModelENDJ<EnderJellyfishEntity>> {
	public EnderJellyfishRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelENDJ.LAYER_LOCATION), context.bakeLayer(ModelJELLY.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(EnderJellyfishEntity entity, PoseStack poseStack, float f) {
		// The baby uses its own dedicated model (already properly proportioned as a baby), not a
		// shrunk-down copy of the adult, so the usual age-scale shrink is skipped for it.
		if (!entity.isBaby()) {
			poseStack.scale(entity.getAgeScale(), entity.getAgeScale(), entity.getAgeScale());
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EnderJellyfishEntity entity) {
		if (entity.isBaby()) {
			return ResourceLocation.parse("end_elemetn:textures/entities/vvv.png");
		}
		return ResourceLocation.parse("end_elemetn:textures/entities/" + entity.getTexture() + ".png");
	}

	private static final class AnimatedModel extends ModelENDJ<EnderJellyfishEntity> {
		private final ModelPart root;
		private final ModelPart babyRoot;
		private final ModelJELLY<EnderJellyfishEntity> babyModel;
		private boolean baby;

		private final HierarchicalModel animator = new HierarchicalModel<EnderJellyfishEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(EnderJellyfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animateWalk(ENDJAnimation.swim, limbSwing, limbSwingAmount, 1f, 1f);
			}
		};

		private final HierarchicalModel babyAnimator = new HierarchicalModel<EnderJellyfishEntity>() {
			@Override
			public ModelPart root() {
				return babyRoot;
			}

			@Override
			public void setupAnim(EnderJellyfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animateWalk(JELLYAnimation.swim, limbSwing, limbSwingAmount, 1f, 1f);
			}
		};

		public AnimatedModel(ModelPart root, ModelPart babyRoot) {
			super(root);
			this.root = root;
			this.babyRoot = babyRoot;
			this.babyModel = new ModelJELLY<>(babyRoot);
		}

		@Override
		public void setupAnim(EnderJellyfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			this.baby = entity.isBaby();
			if (this.baby) {
				babyAnimator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			} else {
				animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			}
		}

		@Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
			if (this.baby) {
				babyModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
			} else {
				super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
			}
		}
	}
}
