package net.mcreator.end_elemetn.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.end_elemetn.entity.VengefulHeartOfEnderEntity;
import net.mcreator.end_elemetn.client.model.animations.vengeful_heartAnimation;
import net.mcreator.end_elemetn.client.model.animations.vengeful_heart21Animation;
import net.mcreator.end_elemetn.client.model.Modelvengeful_heart;

public class VengefulHeartOfEnderRenderer extends MobRenderer<VengefulHeartOfEnderEntity, Modelvengeful_heart<VengefulHeartOfEnderEntity>> {
	public VengefulHeartOfEnderRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelvengeful_heart.LAYER_LOCATION)), 0.5f);
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
		}
	}
}