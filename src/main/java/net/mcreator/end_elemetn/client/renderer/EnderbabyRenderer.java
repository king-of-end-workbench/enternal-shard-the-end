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

import net.mcreator.end_elemetn.entity.EnderbabyEntity;
import net.mcreator.end_elemetn.client.model.animations.babyenderAnimation;
import net.mcreator.end_elemetn.client.model.Modelbabyender;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class EnderbabyRenderer extends MobRenderer<EnderbabyEntity, Modelbabyender<EnderbabyEntity>> {
	public EnderbabyRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelbabyender.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<EnderbabyEntity, Modelbabyender<EnderbabyEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("end_elemetn:textures/entities/bebooo.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, EnderbabyEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				AnimatedModel model = new AnimatedModel(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbabyender.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1.0F, 1.0F, 1.0F, 1.0F);
			}
		});
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