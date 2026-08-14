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

import net.mcreator.end_elemetn.entity.CryingWatchlingEntity;
import net.mcreator.end_elemetn.client.model.animations.watchlingAnimation;
import net.mcreator.end_elemetn.client.model.Modelwatchling_control;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class CryingWatchlingRenderer extends MobRenderer<CryingWatchlingEntity, Modelwatchling_control<CryingWatchlingEntity>> {
	public CryingWatchlingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelwatchling_control.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<CryingWatchlingEntity, Modelwatchling_control<CryingWatchlingEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("end_elemetn:textures/entities/crying_watchling_eyes.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, CryingWatchlingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				AnimatedModel model = new AnimatedModel(Minecraft.getInstance().getEntityModels().bakeLayer(Modelwatchling_control.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(CryingWatchlingEntity entity) {
		return ResourceLocation.parse("end_elemetn:textures/entities/" + entity.getTexture() + ".png");
	}

	private static final class AnimatedModel extends Modelwatchling_control<CryingWatchlingEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<CryingWatchlingEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(CryingWatchlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, watchlingAnimation.watchling_idle, ageInTicks, 1f);
				this.animateWalk(watchlingAnimation.watchling_walk, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, watchlingAnimation.watchling_attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(CryingWatchlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			// Isolated to the head bone specifically (not the whole body) - applied last so it
			// always wins over whatever the idle/walk/attack animations above left the head at.
			this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
			this.head.xRot = headPitch * ((float) Math.PI / 180F);
		}
	}
}