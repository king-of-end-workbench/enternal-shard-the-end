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

import net.mcreator.end_elemetn.entity.CryingSnarelingEntity;
import net.mcreator.end_elemetn.client.model.animations.snarelingAnimation;
import net.mcreator.end_elemetn.client.model.Modelsnareling_control;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class CryingSnarelingRenderer extends MobRenderer<CryingSnarelingEntity, Modelsnareling_control<CryingSnarelingEntity>> {
	public CryingSnarelingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelsnareling_control.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<CryingSnarelingEntity, Modelsnareling_control<CryingSnarelingEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("end_elemetn:textures/entities/crying_snareling_eyes.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, CryingSnarelingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				AnimatedModel model = new AnimatedModel(Minecraft.getInstance().getEntityModels().bakeLayer(Modelsnareling_control.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1.0F, 1.0F, 1.0F, 1.0F);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(CryingSnarelingEntity entity) {
		return new ResourceLocation("end_elemetn:textures/entities/" + entity.getTexture() + ".png");
	}

	private static final class AnimatedModel extends Modelsnareling_control<CryingSnarelingEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<CryingSnarelingEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(CryingSnarelingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, snarelingAnimation.snareling_idle, ageInTicks, 1f);
				this.animateWalk(snarelingAnimation.snareling_walk, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, snarelingAnimation.snareling_attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(CryingSnarelingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			// Isolated to the head bone specifically (not the whole body) - applied last so it
			// wins over the idle/walk animations above, but only when the attack animation isn't
			// running, since that animation has its own deliberate head keyframes that this
			// look-tracking override would otherwise blot out entirely.
			if (!entity.animationState2.isStarted()) {
				this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
				this.head.xRot = headPitch * ((float) Math.PI / 180F);
			}
		}
	}
}