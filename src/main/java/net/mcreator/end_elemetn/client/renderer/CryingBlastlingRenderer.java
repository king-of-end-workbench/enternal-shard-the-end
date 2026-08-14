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

import net.mcreator.end_elemetn.entity.CryingBlastlingEntity;
import net.mcreator.end_elemetn.client.model.animations.blastling_aniAnimation;
import net.mcreator.end_elemetn.client.model.Modelblastling_control;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class CryingBlastlingRenderer extends MobRenderer<CryingBlastlingEntity, Modelblastling_control<CryingBlastlingEntity>> {
	public CryingBlastlingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelblastling_control.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<CryingBlastlingEntity, Modelblastling_control<CryingBlastlingEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("end_elemetn:textures/entities/blastling_cry_eyes.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, CryingBlastlingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				AnimatedModel model = new AnimatedModel(Minecraft.getInstance().getEntityModels().bakeLayer(Modelblastling_control.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(CryingBlastlingEntity entity) {
		return ResourceLocation.parse("end_elemetn:textures/entities/" + entity.getTexture() + ".png");
	}

	private static final class AnimatedModel extends Modelblastling_control<CryingBlastlingEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<CryingBlastlingEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(CryingBlastlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, blastling_aniAnimation.blastling_idle, ageInTicks, 1f);
				this.animateWalk(blastling_aniAnimation.blastling_walk, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, blastling_aniAnimation.blastling_shoot, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(CryingBlastlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			// Isolated to the head bone specifically (not the whole body) - applied last so it
			// always wins over whatever the idle/walk/attack animations above left the head at.
			this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
			this.head.xRot = headPitch * ((float) Math.PI / 180F);
		}
	}
}