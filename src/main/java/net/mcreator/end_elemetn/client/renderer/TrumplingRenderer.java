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

import net.mcreator.end_elemetn.entity.TrumplingEntity;
import net.mcreator.end_elemetn.client.model.animations.thumplingAnimation;
import net.mcreator.end_elemetn.client.model.Modelthumpling;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class TrumplingRenderer extends MobRenderer<TrumplingEntity, Modelthumpling<TrumplingEntity>> {
	public TrumplingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelthumpling.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<TrumplingEntity, Modelthumpling<TrumplingEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("end_elemetn:textures/entities/thumpling_eyes.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, TrumplingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				AnimatedModel model = new AnimatedModel(Minecraft.getInstance().getEntityModels().bakeLayer(Modelthumpling.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
			}
		});
	}

	@Override
	protected void scale(TrumplingEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(entity.getAgeScale(), entity.getAgeScale(), entity.getAgeScale());
	}

	@Override
	public ResourceLocation getTextureLocation(TrumplingEntity entity) {
		return ResourceLocation.parse("end_elemetn:textures/entities/" + entity.getTexture() + ".png");
	}

	private static final class AnimatedModel extends Modelthumpling<TrumplingEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<TrumplingEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TrumplingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, thumplingAnimation.thumpling_idle, ageInTicks, 1f);
				this.animateWalk(thumplingAnimation.thumpling_walk, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, thumplingAnimation.thumpling_warning, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(TrumplingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}