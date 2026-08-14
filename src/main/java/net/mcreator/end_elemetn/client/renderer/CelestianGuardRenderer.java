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

import net.mcreator.end_elemetn.entity.CelestianGuardEntity;
import net.mcreator.end_elemetn.client.model.animations.Celestian_GuardAnimation;
import net.mcreator.end_elemetn.client.model.ModelCelestian_Guard;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class CelestianGuardRenderer extends MobRenderer<CelestianGuardEntity, ModelCelestian_Guard<CelestianGuardEntity>> {
	public CelestianGuardRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelCelestian_Guard.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<CelestianGuardEntity, ModelCelestian_Guard<CelestianGuardEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("end_elemetn:textures/entities/celestial_eyes.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, CelestianGuardEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				AnimatedModel model = new AnimatedModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelCelestian_Guard.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1.0F, 1.0F, 1.0F, 1.0F);
			}
		});
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