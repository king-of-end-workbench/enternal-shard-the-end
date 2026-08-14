// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelenderom<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "enderom"), "main");
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart bone2;
	private final ModelPart pupil2;
	private final ModelPart pupil;
	private final ModelPart bone;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart bone3;

	public Modelenderom(ModelPart root) {
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.bone2 = this.head.getChild("bone2");
		this.pupil2 = this.head.getChild("pupil2");
		this.pupil = this.head.getChild("pupil");
		this.bone = this.head.getChild("bone");
		this.rightArm = this.body.getChild("rightArm");
		this.leftArm = this.body.getChild("leftArm");
		this.bone3 = root.getChild("bone3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg",
				CubeListBuilder.create().texOffs(109, 4).mirror()
						.addBox(-1.0F, 2.0F, -2.0F, 3.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-2.5F, 4.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(109, 5)
				.addBox(-2.0F, 2.0F, -2.0F, 3.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.5F, 4.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(68, 21)
				.addBox(-5.5F, -13.0F, -3.0F, 11.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(68, 2).addBox(-4.5F,
				-6.0F, -3.5F, 9.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

		PartDefinition bone2 = head.addOrReplaceChild("bone2",
				CubeListBuilder.create().texOffs(1, 1)
						.addBox(0.0F, 0.75F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(1, 1)
						.addBox(0.0F, -3.25F, -1.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-3.0F, -4.75F, -5.0F));

		PartDefinition pupil2 = head.addOrReplaceChild("pupil2", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F,
				-1.0F, -0.51F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -2.0F, -3.0F));

		PartDefinition pupil = head.addOrReplaceChild("pupil", CubeListBuilder.create().texOffs(23, 15).addBox(-0.5F,
				2.0F, -0.51F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, -3.0F));

		PartDefinition bone = head.addOrReplaceChild("bone",
				CubeListBuilder.create().texOffs(1, 1)
						.addBox(3.0F, -39.0F, -5.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(1, 1)
						.addBox(3.0F, -43.0F, -6.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 35.0F, 0.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(121, 2)
				.addBox(-3.5F, 0.0F, -2.0F, 4.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-6.0F, -13.0F, 0.0F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(121, 2).addBox(
				-0.5F, 0.0F, -2.0F, 4.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -13.0F, 0.0F));

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 69).addBox(
				-5.0F, -3.0F, -1.0F, 10.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 164, 86);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}