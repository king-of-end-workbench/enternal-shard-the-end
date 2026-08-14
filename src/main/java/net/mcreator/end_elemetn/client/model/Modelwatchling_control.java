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
public class Modelwatchling_control<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("end_elemetn", "modelwatchling_control"), "main");
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;
	public final ModelPart body;
	public final ModelPart eyeLid14;
	public final ModelPart eyeLid13;
	public final ModelPart eyeLid12;
	public final ModelPart eyeLid11;
	public final ModelPart eyeLid8;
	public final ModelPart eyeLid7;
	public final ModelPart pupil14;
	public final ModelPart pupil13;
	public final ModelPart pupil12;
	public final ModelPart pupil11;
	public final ModelPart pupil10;
	public final ModelPart pupil9;
	public final ModelPart head;
	public final ModelPart pupil8;
	public final ModelPart pupil7;
	public final ModelPart pupil6;
	public final ModelPart pupil5;
	public final ModelPart pupil4;
	public final ModelPart pupil3;
	public final ModelPart pupil2;
	public final ModelPart pupil;
	public final ModelPart eyeLid6;
	public final ModelPart eyeLid5;
	public final ModelPart eyeLid4;
	public final ModelPart eyeLid3;
	public final ModelPart eyeLid2;
	public final ModelPart eyeLid10;
	public final ModelPart eyeLid9;
	public final ModelPart eyeLid;
	public final ModelPart rightArm;
	public final ModelPart leftArm;
	public final ModelPart bone;

	public Modelwatchling_control(ModelPart root) {
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.body = root.getChild("body");
		this.eyeLid14 = this.body.getChild("eyeLid14");
		this.eyeLid13 = this.body.getChild("eyeLid13");
		this.eyeLid12 = this.body.getChild("eyeLid12");
		this.eyeLid11 = this.body.getChild("eyeLid11");
		this.eyeLid8 = this.body.getChild("eyeLid8");
		this.eyeLid7 = this.body.getChild("eyeLid7");
		this.pupil14 = this.body.getChild("pupil14");
		this.pupil13 = this.body.getChild("pupil13");
		this.pupil12 = this.body.getChild("pupil12");
		this.pupil11 = this.body.getChild("pupil11");
		this.pupil10 = this.body.getChild("pupil10");
		this.pupil9 = this.body.getChild("pupil9");
		this.head = this.body.getChild("head");
		this.pupil8 = this.head.getChild("pupil8");
		this.pupil7 = this.head.getChild("pupil7");
		this.pupil6 = this.head.getChild("pupil6");
		this.pupil5 = this.head.getChild("pupil5");
		this.pupil4 = this.head.getChild("pupil4");
		this.pupil3 = this.head.getChild("pupil3");
		this.pupil2 = this.head.getChild("pupil2");
		this.pupil = this.head.getChild("pupil");
		this.eyeLid6 = this.head.getChild("eyeLid6");
		this.eyeLid5 = this.head.getChild("eyeLid5");
		this.eyeLid4 = this.head.getChild("eyeLid4");
		this.eyeLid3 = this.head.getChild("eyeLid3");
		this.eyeLid2 = this.head.getChild("eyeLid2");
		this.eyeLid10 = this.head.getChild("eyeLid10");
		this.eyeLid9 = this.head.getChild("eyeLid9");
		this.eyeLid = this.head.getChild("eyeLid");
		this.rightArm = this.body.getChild("rightArm");
		this.leftArm = this.body.getChild("leftArm");
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(112, 5).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-2.5F, 4.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(112, 5).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 4.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(68, 21).addBox(-5.5F, -15.0F, -3.0F, 11.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
		PartDefinition eyeLid14 = body.addOrReplaceChild("eyeLid14", CubeListBuilder.create().texOffs(58, 18).addBox(-1.5F, 0.0F, -0.12F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -12.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition eyeLid13 = body.addOrReplaceChild("eyeLid13", CubeListBuilder.create().texOffs(58, 16).addBox(-1.5F, 0.0F, -0.12F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -3.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition eyeLid12 = body.addOrReplaceChild("eyeLid12", CubeListBuilder.create().texOffs(39, 46).addBox(-1.5F, 0.0F, -0.12F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -7.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition eyeLid11 = body.addOrReplaceChild("eyeLid11", CubeListBuilder.create().texOffs(49, 40).addBox(-1.5F, 0.0F, -0.12F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, -3.0F));
		PartDefinition eyeLid8 = body.addOrReplaceChild("eyeLid8", CubeListBuilder.create().texOffs(35, 56).addBox(-1.5F, 0.0F, -0.12F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -12.0F, -3.0F));
		PartDefinition eyeLid7 = body.addOrReplaceChild("eyeLid7", CubeListBuilder.create().texOffs(63, 56).addBox(-1.5F, 0.0F, -0.12F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -10.0F, -3.0F));
		PartDefinition pupil14 = body.addOrReplaceChild("pupil14", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -11.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition pupil13 = body.addOrReplaceChild("pupil13", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -2.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition pupil12 = body.addOrReplaceChild("pupil12", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition pupil11 = body.addOrReplaceChild("pupil11", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, -3.0F));
		PartDefinition pupil10 = body.addOrReplaceChild("pupil10", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -9.0F, -3.0F));
		PartDefinition pupil9 = body.addOrReplaceChild("pupil9", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -11.0F, -3.0F));
		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(68, 2).addBox(-4.5F, -8.0F, -3.5F, 9.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(13, 23).addBox(-4.5F, -8.9F, -4.0F, 9.0F, 12.0F, 7.0F, new CubeDeformation(0.75F)),
				PartPose.offset(0.0F, -15.0F, 0.0F));
		PartDefinition pupil8 = head.addOrReplaceChild("pupil8", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition pupil7 = head.addOrReplaceChild("pupil7", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition pupil6 = head.addOrReplaceChild("pupil6", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -2.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition pupil5 = head.addOrReplaceChild("pupil5", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -6.0F, 2.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition pupil4 = head.addOrReplaceChild("pupil4", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -6.0F, 1.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition pupil3 = head.addOrReplaceChild("pupil3", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, 2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition pupil2 = head.addOrReplaceChild("pupil2", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -2.0F, -3.0F));
		PartDefinition pupil = head.addOrReplaceChild("pupil", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, -3.0F));
		PartDefinition eyeLid6 = head.addOrReplaceChild("eyeLid6", CubeListBuilder.create().texOffs(44, 31).addBox(-1.5F, 0.0F, -0.52F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -1.0F, -2.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition eyeLid5 = head.addOrReplaceChild("eyeLid5", CubeListBuilder.create().texOffs(57, 23).addBox(-1.5F, 0.0F, -0.52F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -7.0F, 2.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition eyeLid4 = head.addOrReplaceChild("eyeLid4", CubeListBuilder.create().texOffs(57, 21).addBox(-1.5F, 0.0F, -0.52F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -7.0F, 1.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition eyeLid3 = head.addOrReplaceChild("eyeLid3", CubeListBuilder.create().texOffs(51, 19).addBox(-1.5F, 0.0F, -0.52F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.0F, 2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition eyeLid2 = head.addOrReplaceChild("eyeLid2", CubeListBuilder.create().texOffs(51, 17).addBox(-1.5F, 0.0F, -0.52F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, -3.0F));
		PartDefinition eyeLid10 = head.addOrReplaceChild("eyeLid10", CubeListBuilder.create().texOffs(51, 15).addBox(-1.5F, 0.0F, -0.52F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -3.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition eyeLid9 = head.addOrReplaceChild("eyeLid9", CubeListBuilder.create().texOffs(67, 48).addBox(-1.5F, 0.0F, -0.52F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -6.0F, 3.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition eyeLid = head.addOrReplaceChild("eyeLid", CubeListBuilder.create().texOffs(49, 52).addBox(-1.5F, 0.0F, -0.52F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -6.0F, -3.0F));
		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(121, 2).addBox(-3.5F, -2.0F, -2.0F, 4.0F, 29.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -13.0F, 0.0F));
		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(121, 2).addBox(-0.5F, -2.0F, -2.0F, 4.0F, 29.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -13.0F, 0.0F));
		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-98.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 164, 86);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}