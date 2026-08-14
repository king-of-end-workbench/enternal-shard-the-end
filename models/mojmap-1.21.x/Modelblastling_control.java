// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelblastling_control<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "blastling_control"), "main");
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightEye;
	private final ModelPart leftEye;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public Modelblastling_control(ModelPart root) {
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.rightEye = this.head.getChild("rightEye");
		this.leftEye = this.head.getChild("leftEye");
		this.rightArm = this.body.getChild("rightArm");
		this.leftArm = this.body.getChild("leftArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg",
				CubeListBuilder.create().texOffs(51, 36).mirror()
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 21.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(2.5F, 3.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(51, 36)
				.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 21.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.5F, 3.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(124, 34)
				.addBox(-6.0F, -16.0F, -4.0F, 12.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(8, 33)
						.addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(71, 60)
						.addBox(-4.0F, -11.0F, -6.0F, 8.0F, 11.0F, 8.0F, new CubeDeformation(0.5F)).texOffs(12, 66)
						.addBox(-4.5F, -10.0F, -6.0F, 9.0F, 12.0F, 8.0F, new CubeDeformation(0.75F)),
				PartPose.offset(0.0F, -12.0F, -1.0F));

		PartDefinition rightEye = head.addOrReplaceChild("rightEye", CubeListBuilder.create().texOffs(26, 53)
				.addBox(-1.0F, -1.0F, -0.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, -3.0F, -6.0F));

		PartDefinition leftEye = head.addOrReplaceChild("leftEye", CubeListBuilder.create().texOffs(26, 53)
				.addBox(-1.0F, -1.0F, -0.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, -3.0F, -6.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm",
				CubeListBuilder.create().texOffs(79, 17).mirror()
						.addBox(-6.0F, -4.0F, -3.5F, 7.0F, 35.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-7.0F, -11.0F, 0.0F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(79, 17)
				.addBox(-1.0F, -4.0F, -3.5F, 7.0F, 35.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(7.0F, -11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 164, 86);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}