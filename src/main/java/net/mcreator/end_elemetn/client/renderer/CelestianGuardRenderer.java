package net.mcreator.end_elemetn.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.end_elemetn.entity.CelestianGuardEntity;
import net.mcreator.end_elemetn.client.model.animations.Celestian_GuardAnimation;
import net.mcreator.end_elemetn.client.model.ModelCelestian_Guard;

public class CelestianGuardRenderer extends MobRenderer<CelestianGuardEntity, ModelCelestian_Guard<CelestianGuardEntity>> {
	public CelestianGuardRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelCelestian_Guard.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(CelestianGuardEntity entity) {
		return new ResourceLocation("end_elemetn:textures/entities/celestial_guard.png");
	}

	private static final class AnimatedModel extends ModelCelestian_Guard<CelestianGuardEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<CelestianGuardEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(CelestianGuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, Celestian_GuardAnimation.idle, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(CelestianGuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}