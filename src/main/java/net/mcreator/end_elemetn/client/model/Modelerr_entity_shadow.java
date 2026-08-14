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
public class Modelerr_entity_shadow<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("end_elemetn", "modelerr_entity_shadow"), "main");
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart bone2;
	public final ModelPart pupil2;
	public final ModelPart bone4;
	public final ModelPart pupil3;
	public final ModelPart bone;
	public final ModelPart pupil;
	public final ModelPart bone3;
	public final ModelPart rightArm;
	public final ModelPart leftArm;

	public Modelerr_entity_shadow(ModelPart root) {
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.bone2 = this.head.getChild("bone2");
		this.pupil2 = this.head.getChild("pupil2");
		this.bone4 = this.pupil2.getChild("bone4");
		this.pupil3 = this.head.getChild("pupil3");
		this.bone = this.pupil3.getChild("bone");
		this.pupil = this.head.getChild("pupil");
		this.bone3 = this.pupil.getChild("bone3");
		this.rightArm = this.body.getChild("rightArm");
		this.leftArm = this.body.getChild("leftArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(111, 5).mirror().addBox(-2.0F, 2.0F, -2.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-2.5F, 4.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(111, 5).addBox(-2.0F, 2.0F, -2.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 4.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(68, 21).addBox(-5.5F, -13.0F, -3.0F, 9.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(66, 0).addBox(-8.5F, -7.0F, -4.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -15.0F, 0.0F));
		PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-3.0F, -4.75F, -5.0F));
		PartDefinition pupil2 = head.addOrReplaceChild("pupil2", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, -4.0F));
		PartDefinition bone4 = pupil2.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(27, 15).addBox(-3.5F, -37.0F, -3.52F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 37.0F, 3.0F));
		PartDefinition pupil3 = head.addOrReplaceChild("pupil3", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, -1.0F, -0.51F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -3.0F, -4.0F));
		PartDefinition bone = pupil3.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(27, 15).addBox(-3.5F, -37.0F, -3.52F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 37.0F, 3.0F));
		PartDefinition pupil = head.addOrReplaceChild("pupil", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F, 3.0F, -0.51F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -5.0F, -4.0F));
		PartDefinition bone3 = pupil.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(27, 15).addBox(-3.5F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 2.5F, -0.02F));
		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(123, 3).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 22.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -6.0F, -0.5F));
		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(123, 3).addBox(-2.5F, 0.0F, -1.0F, 3.0F, 23.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -13.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 164, 86);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}