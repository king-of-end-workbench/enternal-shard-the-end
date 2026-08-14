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
public class Modelendersent<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("end_elemetn", "modelendersent"), "main");
	public final ModelPart everything;
	public final ModelPart body;
	public final ModelPart eye;
	public final ModelPart head;
	public final ModelPart rightArmUpper;
	public final ModelPart rightArmLower;
	public final ModelPart leftArmUpper;
	public final ModelPart leftArmLower;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;

	public Modelendersent(ModelPart root) {
		this.everything = root.getChild("everything");
		this.body = this.everything.getChild("body");
		this.eye = this.body.getChild("eye");
		this.head = this.body.getChild("head");
		this.rightArmUpper = this.body.getChild("rightArmUpper");
		this.rightArmLower = this.rightArmUpper.getChild("rightArmLower");
		this.leftArmUpper = this.body.getChild("leftArmUpper");
		this.leftArmLower = this.leftArmUpper.getChild("leftArmLower");
		this.rightLeg = this.everything.getChild("rightLeg");
		this.leftLeg = this.everything.getChild("leftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition everything = partdefinition.addOrReplaceChild("everything", CubeListBuilder.create(), PartPose.offset(0.0F, -32.0F, 0.0F));
		PartDefinition body = everything.addOrReplaceChild("body", CubeListBuilder.create().texOffs(41, 27).addBox(-9.0F, -28.0F, -4.0F, 18.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
		PartDefinition eye = body.addOrReplaceChild("eye",
				CubeListBuilder.create().texOffs(132, 44).addBox(-6.5F, -2.0F, -1.0F, 13.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(133, 62).addBox(-5.5F, -4.0F, -1.0F, 11.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(103, 68)
						.addBox(-4.5F, -5.0F, -1.0F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(72, 67).addBox(-3.5F, -6.0F, -1.0F, 7.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 68)
						.addBox(-1.5F, -7.0F, -1.0F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -15.0F, -4.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(43, 4).addBox(-4.5F, -13.0F, -6.0F, 9.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -3.0F));
		PartDefinition rightArmUpper = body.addOrReplaceChild("rightArmUpper", CubeListBuilder.create().texOffs(17, 0).mirror().addBox(-4.0F, -4.0F, -2.0F, 4.0F, 30.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-9.0F, -24.0F, 0.0F));
		PartDefinition rightArmLower = rightArmUpper.addOrReplaceChild("rightArmLower", CubeListBuilder.create().texOffs(17, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 41.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-2.0F, 26.0F, 0.0F));
		PartDefinition rightArmLower_r1 = rightArmLower.addOrReplaceChild("rightArmLower_r1", CubeListBuilder.create().texOffs(122, 8).mirror().addBox(17.0F, -4.0F, -3.0F, 11.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-1.0F, 24.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));
		PartDefinition leftArmUpper = body.addOrReplaceChild("leftArmUpper", CubeListBuilder.create().texOffs(17, 0).addBox(0.0F, -4.0F, -2.0F, 4.0F, 30.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -24.0F, 0.0F));
		PartDefinition leftArmLower = leftArmUpper.addOrReplaceChild("leftArmLower", CubeListBuilder.create().texOffs(17, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 41.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 26.0F, 0.0F));
		PartDefinition leftArmLower_r1 = leftArmLower.addOrReplaceChild("leftArmLower_r1", CubeListBuilder.create().texOffs(122, 8).addBox(-28.0F, -4.0F, -5.0F, 11.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 24.0F, 2.0F, 1.5708F, 0.0F, -1.5708F));
		PartDefinition rightLeg = everything.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(1, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 57.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -1.0F, 0.0F));
		PartDefinition leftLeg = everything.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(1, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 57.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 164, 86);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		everything.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}