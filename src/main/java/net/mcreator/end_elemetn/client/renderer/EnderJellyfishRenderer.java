package net.mcreator.end_elemetn.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.end_elemetn.entity.EnderJellyfishEntity;
import net.mcreator.end_elemetn.client.model.animations.ENDJAnimation;
import net.mcreator.end_elemetn.client.model.ModelENDJ;

import com.mojang.blaze3d.vertex.PoseStack;

public class EnderJellyfishRenderer extends MobRenderer<EnderJellyfishEntity, ModelENDJ<EnderJellyfishEntity>> {
	public EnderJellyfishRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelENDJ.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(EnderJellyfishEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(entity.getScale(), entity.getScale(), entity.getScale());
	}

	@Override
	public ResourceLocation getTextureLocation(EnderJellyfishEntity entity) {
		return new ResourceLocation("end_elemetn:textures/entities/drifter.png");
	}

	private static final class AnimatedModel extends ModelENDJ<EnderJellyfishEntity> {
		private final ModelPart root;
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

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(EnderJellyfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}