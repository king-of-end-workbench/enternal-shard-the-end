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
public class Modelnecrosent<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("end_elemetn", "modelnecrosent"), "main");
	public final ModelPart everything;
	public final ModelPart body;
	public final ModelPart snarelingGlob;
	public final ModelPart rightRib3;
	public final ModelPart rightRib2;
	public final ModelPart rightRib1;
	public final ModelPart leftRib3;
	public final ModelPart leftRib2;
	public final ModelPart leftRib1;
	public final ModelPart watchlingHead;
	public final ModelPart watchlingHeadSkull;
	public final ModelPart head;
	public final ModelPart skull;
	public final ModelPart blastlingRightArmUpper;
	public final ModelPart blastlingRightArmLower;
	public final ModelPart rightArmUpper;
	public final ModelPart rightArmLower;
	public final ModelPart leftArmUpper;
	public final ModelPart leftArmLower;
	public final ModelPart leftHand;
	public final ModelPart snarelingHand;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;

	public Modelnecrosent(ModelPart root) {
		this.everything = root.getChild("everything");
		this.body = this.everything.getChild("body");
		this.snarelingGlob = this.body.getChild("snarelingGlob");
		this.rightRib3 = this.body.getChild("rightRib3");
		this.rightRib2 = this.body.getChild("rightRib2");
		this.rightRib1 = this.body.getChild("rightRib1");
		this.leftRib3 = this.body.getChild("leftRib3");
		this.leftRib2 = this.body.getChild("leftRib2");
		this.leftRib1 = this.body.getChild("leftRib1");
		this.watchlingHead = this.body.getChild("watchlingHead");
		this.watchlingHeadSkull = this.watchlingHead.getChild("watchlingHeadSkull");
		this.head = this.body.getChild("head");
		this.skull = this.head.getChild("skull");
		this.blastlingRightArmUpper = this.body.getChild("blastlingRightArmUpper");
		this.blastlingRightArmLower = this.blastlingRightArmUpper.getChild("blastlingRightArmLower");
		this.rightArmUpper = this.body.getChild("rightArmUpper");
		this.rightArmLower = this.rightArmUpper.getChild("rightArmLower");
		this.leftArmUpper = this.body.getChild("leftArmUpper");
		this.leftArmLower = this.leftArmUpper.getChild("leftArmLower");
		this.leftHand = this.leftArmLower.getChild("leftHand");
		this.snarelingHand = this.leftArmLower.getChild("snarelingHand");
		this.rightLeg = this.everything.getChild("rightLeg");
		this.leftLeg = this.everything.getChild("leftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition everything = partdefinition.addOrReplaceChild("everything", CubeListBuilder.create(), PartPose.offset(0.0F, -32.0F, 0.0F));
		PartDefinition body = everything.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(41, 27).addBox(-9.0F, -16.0F, -4.0F, 18.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(55, 42).addBox(-9.0F, -27.0F, -7.0F, 18.0F, 11.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -1.0F, 0.0F));
		PartDefinition snarelingGlob = body.addOrReplaceChild("snarelingGlob",
				CubeListBuilder.create().texOffs(195, 35).addBox(-8.0F, -8.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)).texOffs(206, 57).addBox(-8.0F, -8.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(2.0F)),
				PartPose.offset(4.0F, -12.0F, -1.0F));
		PartDefinition rightRib3 = body.addOrReplaceChild("rightRib3", CubeListBuilder.create().texOffs(32, 64).mirror().addBox(0.0F, -1.5F, -3.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -4.5F, -5.0F));
		PartDefinition rightRib2 = body.addOrReplaceChild("rightRib2", CubeListBuilder.create().texOffs(32, 64).mirror().addBox(0.0F, -1.5F, -3.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -9.5F, -5.0F));
		PartDefinition rightRib1 = body.addOrReplaceChild("rightRib1", CubeListBuilder.create().texOffs(32, 64).mirror().addBox(0.0F, -1.5F, -3.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -14.5F, -5.0F));
		PartDefinition leftRib3 = body.addOrReplaceChild("leftRib3", CubeListBuilder.create().texOffs(32, 64).addBox(-7.0F, -1.5F, -3.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -4.5F, -5.0F));
		PartDefinition leftRib2 = body.addOrReplaceChild("leftRib2", CubeListBuilder.create().texOffs(32, 64).addBox(-7.0F, -1.5F, -3.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -9.5F, -5.0F));
		PartDefinition leftRib1 = body.addOrReplaceChild("leftRib1", CubeListBuilder.create().texOffs(32, 64).addBox(-7.0F, -1.5F, -3.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -14.5F, -5.0F));
		PartDefinition watchlingHead = body.addOrReplaceChild("watchlingHead", CubeListBuilder.create().texOffs(167, 90).addBox(-4.5F, -13.0F, -6.0F, 9.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, -6.0F));
		PartDefinition watchlingHeadSkull = watchlingHead.addOrReplaceChild("watchlingHeadSkull", CubeListBuilder.create().texOffs(211, 84).addBox(-4.5F, -16.0F, -4.0F, 9.0F, 15.0F, 7.0F, new CubeDeformation(0.75F)),
				PartPose.offset(0.0F, 3.0F, -2.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(43, 4).addBox(-4.5F, -13.0F, -6.0F, 9.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, -6.0F));
		PartDefinition skull = head.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(131, 64).addBox(-4.5F, -16.0F, -4.0F, 9.0F, 15.0F, 7.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 3.0F, -2.0F));
		PartDefinition blastlingRightArmUpper = body.addOrReplaceChild("blastlingRightArmUpper", CubeListBuilder.create().texOffs(81, 73).addBox(-7.0F, -4.0F, -4.0F, 7.0F, 24.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-9.0F, -24.0F, -1.0F));
		PartDefinition blastlingRightArmLower = blastlingRightArmUpper.addOrReplaceChild("blastlingRightArmLower", CubeListBuilder.create().texOffs(81, 108).addBox(-4.0F, 0.0F, -4.0F, 7.0F, 40.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-3.0F, 20.0F, 0.0F));
		PartDefinition rightArmUpper = body.addOrReplaceChild("rightArmUpper", CubeListBuilder.create().texOffs(17, 0).mirror().addBox(-4.0F, -4.0F, -2.0F, 4.0F, 30.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-9.0F, -24.0F, -2.0F));
		PartDefinition rightArmLower = rightArmUpper.addOrReplaceChild("rightArmLower", CubeListBuilder.create().texOffs(17, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 41.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-2.0F, 26.0F, 0.0F));
		PartDefinition rightArmLower_r1 = rightArmLower.addOrReplaceChild("rightArmLower_r1", CubeListBuilder.create().texOffs(122, 8).mirror().addBox(17.0F, -4.0F, -3.0F, 11.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-1.0F, 24.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));
		PartDefinition leftArmUpper = body.addOrReplaceChild("leftArmUpper", CubeListBuilder.create().texOffs(17, 0).addBox(0.0F, -4.0F, -2.0F, 4.0F, 30.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -24.0F, -2.0F));
		PartDefinition leftArmLower = leftArmUpper.addOrReplaceChild("leftArmLower", CubeListBuilder.create().texOffs(17, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 41.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 26.0F, 0.0F));
		PartDefinition leftHand = leftArmLower.addOrReplaceChild("leftHand", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftHand_r1 = leftHand.addOrReplaceChild("leftHand_r1", CubeListBuilder.create().texOffs(122, 8).addBox(-28.0F, -4.0F, -5.0F, 11.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 24.0F, 2.0F, 1.5708F, 0.0F, -1.5708F));
		PartDefinition snarelingHand = leftArmLower.addOrReplaceChild("snarelingHand", CubeListBuilder.create().texOffs(17, 0).addBox(-1.99F, 0.0F, -2.0F, 4.0F, 41.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 39.0F, 1.0F, 1.1781F, 0.0F, 0.0F));
		PartDefinition rightLeg = everything.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(1, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 57.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -1.0F, 0.0F));
		PartDefinition leftLeg = everything.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(1, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 57.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 328, 172);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		everything.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}