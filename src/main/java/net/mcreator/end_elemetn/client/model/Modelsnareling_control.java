package net.mcreator.end_elemetn.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelsnareling_control<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("end_elemetn", "modelsnareling_control"), "main");
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart leftArm;
	public final ModelPart leftWindmill;
	public final ModelPart rightArm;
	public final ModelPart rightWindmill;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;

	public Modelsnareling_control(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.leftArm = this.body.getChild("leftArm");
		this.leftWindmill = this.leftArm.getChild("leftWindmill");
		this.rightArm = this.body.getChild("rightArm");
		this.rightWindmill = this.rightArm.getChild("rightWindmill");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(71, 24).addBox(-5.5F, -13.0F, -3.5F, 11.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(109, 36)
				.addBox(-4.0F, -11.0F, -2.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)).texOffs(120, 58).addBox(-4.0F, -11.0F, -2.25F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(69, 1).addBox(-4.5F, -8.0F, -2.5F, 9.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(10, 5).addBox(-4.5F, -9.0F, -3.0F, 9.0F, 9.0F, 5.0F, new CubeDeformation(0.75F)),
				PartPose.offset(0.0F, -13.0F, 0.0F));
		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(71, 48).mirror().addBox(-0.5F, -1.0F, -1.0F, 2.0F, 36.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, -11.0F, 0.0F));
		PartDefinition leftWindmill = leftArm.addOrReplaceChild("leftWindmill", CubeListBuilder.create().texOffs(84, 54).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 34.0F, 1.0F));
		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(71, 48).addBox(-1.5F, -1.0F, -1.0F, 2.0F, 36.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -11.0F, 0.0F));
		PartDefinition rightWindmill = rightArm.addOrReplaceChild("rightWindmill", CubeListBuilder.create().texOffs(84, 54).mirror().addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-0.5F, 34.0F, 1.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(58, 47).mirror().addBox(-1.5F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-2.0F, 0.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(58, 47).addBox(-0.5F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 164, 86);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}