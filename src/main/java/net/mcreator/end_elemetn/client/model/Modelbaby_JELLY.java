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
public class Modelbaby_JELLY<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("end_elemetn", "modelbaby_jelly"), "main");
	public final ModelPart bb_main;
	public final ModelPart bone;
	public final ModelPart bone2;
	public final ModelPart bone3;
	public final ModelPart bone4;
	public final ModelPart bone5;
	public final ModelPart bone6;
	public final ModelPart bone7;

	public Modelbaby_JELLY(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
		this.bone = root.getChild("bone");
		this.bone2 = root.getChild("bone2");
		this.bone3 = root.getChild("bone3");
		this.bone4 = root.getChild("bone4");
		this.bone5 = root.getChild("bone5");
		this.bone6 = root.getChild("bone6");
		this.bone7 = root.getChild("bone7");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -22.0F, 2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(32, 24).addBox(-4.0F, -16.0F, 0.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(1, 11).addBox(1.0F, 0.0F, -7.0F, 0.0F, 11.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 5.0F, 4.0F));
		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(1, 11).addBox(0.0F, 0.0F, -3.0F, 0.0F, 11.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 5.0F, 0.0F));
		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(1, 24).addBox(-6.0F, 0.0F, 0.0F, 13.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, -4.0F));
		PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(1, 24).addBox(-7.0F, -1.0F, 1.0F, 13.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 11.0F));
		PartDefinition bone5 = partdefinition.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -27.0F, -4.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(48, 0).addBox(-3.0F, -9.0F, 3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition bone7 = partdefinition.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(48, 7).addBox(0.0F, -9.0F, 3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 48);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bone5.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bone7.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}