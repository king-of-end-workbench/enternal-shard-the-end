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
public class Modelbabyender<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("end_elemetn", "modelbabyender"), "main");
	public final ModelPart bone7;
	public final ModelPart bone3;
	public final ModelPart bone2;
	public final ModelPart bone;
	public final ModelPart bone4;
	public final ModelPart bone5;
	public final ModelPart bone6;

	public Modelbabyender(ModelPart root) {
		this.bone7 = root.getChild("bone7");
		this.bone3 = this.bone7.getChild("bone3");
		this.bone2 = this.bone3.getChild("bone2");
		this.bone = this.bone3.getChild("bone");
		this.bone4 = this.bone3.getChild("bone4");
		this.bone5 = this.bone7.getChild("bone5");
		this.bone6 = this.bone7.getChild("bone6");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bone7 = partdefinition.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition bone3 = bone7.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(24, 23).addBox(-5.5F, -4.0114F, -3.4766F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));
		PartDefinition bone2 = bone3.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 27).addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0114F, -1.4766F));
		PartDefinition bone = bone3.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(8, 27).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -3.0114F, -1.4766F));
		PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 13).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -7.5114F, -1.9766F));
		PartDefinition bone5 = bone7.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, -2.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -2.0F));
		PartDefinition bone6 = bone7.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(16, 27).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -9.5F, -2.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}