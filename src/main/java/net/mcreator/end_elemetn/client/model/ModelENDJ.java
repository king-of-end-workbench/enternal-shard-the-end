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
public class ModelENDJ<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("end_elemetn", "model_endj"), "main");
	public final ModelPart all;
	public final ModelPart headd;
	public final ModelPart head;
	public final ModelPart bone;
	public final ModelPart mouth;
	public final ModelPart bite1;
	public final ModelPart bite2;
	public final ModelPart tentaclel;
	public final ModelPart tent4;
	public final ModelPart tent1;
	public final ModelPart tent3;
	public final ModelPart tent2;

	public ModelENDJ(ModelPart root) {
		this.all = root.getChild("all");
		this.headd = this.all.getChild("headd");
		this.head = this.headd.getChild("head");
		this.bone = this.head.getChild("bone");
		this.mouth = this.headd.getChild("mouth");
		this.bite1 = this.mouth.getChild("bite1");
		this.bite2 = this.mouth.getChild("bite2");
		this.tentaclel = this.all.getChild("tentaclel");
		this.tent4 = this.tentaclel.getChild("tent4");
		this.tent1 = this.tentaclel.getChild("tent1");
		this.tent3 = this.tentaclel.getChild("tent3");
		this.tent2 = this.tentaclel.getChild("tent2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition headd = all.addOrReplaceChild("headd", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = headd.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(96, 13).addBox(0.0F, -33.0F, -5.0F, 0.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-4.0F, -23.0F, -6.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -49.0F, -18.0F, 32.0F, 16.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition mouth = headd.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bite1 = mouth.addOrReplaceChild("bite1", CubeListBuilder.create().texOffs(96, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -4.0F));
		PartDefinition bite2 = mouth.addOrReplaceChild("bite2", CubeListBuilder.create().texOffs(96, 10).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));
		PartDefinition tentaclel = all.addOrReplaceChild("tentaclel", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition tent4 = tentaclel.addOrReplaceChild("tent4", CubeListBuilder.create(), PartPose.offset(-16.0F, -33.0F, -2.0F));
		PartDefinition tent4_r1 = tent4.addOrReplaceChild("tent4_r1", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, -16.0F, 0.0F, 22.0F, 32.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));
		PartDefinition tent1 = tentaclel.addOrReplaceChild("tent1", CubeListBuilder.create(), PartPose.offset(16.0F, -33.0F, -2.0F));
		PartDefinition tent1_r1 = tent1.addOrReplaceChild("tent1_r1", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, -16.0F, 0.0F, 22.0F, 32.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));
		PartDefinition tent3 = tentaclel.addOrReplaceChild("tent3", CubeListBuilder.create(), PartPose.offset(-1.0F, -33.0F, -17.0F));
		PartDefinition tent3_r1 = tent3.addOrReplaceChild("tent3_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-16.0F, 0.0F, 0.0F, 32.0F, 22.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, -0.4363F, 0.0F, 0.0F));
		PartDefinition tent2 = tentaclel.addOrReplaceChild("tent2", CubeListBuilder.create(), PartPose.offset(0.0F, -33.0306F, 13.6488F));
		PartDefinition tent2_r1 = tent2.addOrReplaceChild("tent2_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-16.0F, 0.0F, 0.0F, 32.0F, 22.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0306F, 0.3512F, 0.4363F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 80);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}