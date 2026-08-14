// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelCelestian_Guard<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "celestian_guard"), "main");
	private final ModelPart bone2;
	private final ModelPart group;
	private final ModelPart bone;
	private final ModelPart bone5;
	private final ModelPart group2;
	private final ModelPart group3;
	private final ModelPart group5;

	public ModelCelestian_Guard(ModelPart root) {
		this.bone2 = root.getChild("bone2");
		this.group = this.bone2.getChild("group");
		this.bone = this.bone2.getChild("bone");
		this.bone5 = this.bone.getChild("bone5");
		this.group2 = this.bone.getChild("group2");
		this.group3 = this.bone.getChild("group3");
		this.group5 = this.bone.getChild("group5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition group = bone2.addOrReplaceChild("group",
				CubeListBuilder.create().texOffs(0, 56)
						.addBox(-13.0F, -14.0F, 1.0F, 14.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(48, 32)
						.addBox(-11.0F, -14.0F, -1.0F, 10.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(48, 67)
						.addBox(-11.0F, -3.0F, 1.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(48, 56)
						.addBox(-11.0F, -16.0F, 1.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 32)
						.addBox(-12.0F, -15.0F, 0.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(6.0F, 0.0F, -6.0F));

		PartDefinition bone = bone2.addOrReplaceChild("bone", CubeListBuilder.create(),
				PartPose.offset(0.0F, -7.0511F, 0.3882F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 1.5F, -16.0F, 32.0F, 0.0F, 32.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.4489F, -0.3882F, 0.2618F, 0.0F, 0.0F));

		PartDefinition bone5 = bone.addOrReplaceChild("bone5", CubeListBuilder.create(),
				PartPose.offset(0.0F, -1.4489F, -0.3882F));

		PartDefinition cube_r2 = bone5
				.addOrReplaceChild("cube_r2",
						CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 1.5F, -16.0F, 32.0F, 0.0F, 32.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition group2 = bone.addOrReplaceChild("group2",
				CubeListBuilder.create().texOffs(48, 78)
						.addBox(-4.5F, -2.0F, 0.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 76)
						.addBox(-2.5F, -2.0F, -2.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(72, 78)
						.addBox(-2.5F, 3.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(72, 83)
						.addBox(-2.5F, -4.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(24, 76)
						.addBox(-3.5F, -3.0F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-15.5F, -0.9489F, -2.3882F, 0.2618F, 0.0F, 0.0F));

		PartDefinition group3 = bone.addOrReplaceChild("group3",
				CubeListBuilder.create().texOffs(48, 78)
						.addBox(-4.5F, -2.0F, 0.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 76)
						.addBox(-2.5F, -2.0F, -2.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(72, 78)
						.addBox(-2.5F, 3.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(72, 83)
						.addBox(-2.5F, -4.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(24, 76)
						.addBox(-3.5F, -3.0F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 3.0511F, -15.3882F, 0.2618F, 0.0F, 0.0F));

		PartDefinition group5 = bone.addOrReplaceChild("group5",
				CubeListBuilder.create().texOffs(48, 78)
						.addBox(-4.5F, -2.0F, 0.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 76)
						.addBox(-2.5F, -2.0F, -2.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(72, 78)
						.addBox(-2.5F, 3.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(72, 83)
						.addBox(-2.5F, -4.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(24, 76)
						.addBox(-3.5F, -3.0F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.5F, -3.9489F, 13.6118F, 0.2618F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}