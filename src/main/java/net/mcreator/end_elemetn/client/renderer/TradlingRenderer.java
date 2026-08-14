package net.mcreator.end_elemetn.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.end_elemetn.entity.TradlingEntity;
import net.mcreator.end_elemetn.client.model.animations.enderomAnimation;
import net.mcreator.end_elemetn.client.model.Modelenderom;

public class TradlingRenderer extends MobRenderer<TradlingEntity, Modelenderom<TradlingEntity>> {
	public TradlingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelenderom.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(TradlingEntity entity) {
		return new ResourceLocation("end_elemetn:textures/entities/enderom_8hp.png");
	}

	private static final class AnimatedModel extends Modelenderom<TradlingEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<TradlingEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TradlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, enderomAnimation.idle, ageInTicks, 1f);
				this.animate(entity.animationState1, enderomAnimation.WALK, ageInTicks, 1f);
				this.animate(entity.animationState2, enderomAnimation.TR, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(TradlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}