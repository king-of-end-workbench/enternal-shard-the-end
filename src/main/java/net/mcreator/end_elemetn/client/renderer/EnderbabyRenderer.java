package net.mcreator.end_elemetn.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.end_elemetn.entity.EnderbabyEntity;
import net.mcreator.end_elemetn.client.model.animations.babyenderAnimation;
import net.mcreator.end_elemetn.client.model.Modelbabyender;

public class EnderbabyRenderer extends MobRenderer<EnderbabyEntity, Modelbabyender<EnderbabyEntity>> {
	public EnderbabyRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelbabyender.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(EnderbabyEntity entity) {
		return new ResourceLocation("end_elemetn:textures/entities/flower_fields_enderman.png");
	}

	private static final class AnimatedModel extends Modelbabyender<EnderbabyEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<EnderbabyEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(EnderbabyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, babyenderAnimation.idle, ageInTicks, 1f);
				this.animateWalk(babyenderAnimation.walk, limbSwing, limbSwingAmount, 1f, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(EnderbabyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}