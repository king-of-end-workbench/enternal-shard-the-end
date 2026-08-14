// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelvengeful_heart<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "vengeful_heart"), "main");
	private final ModelPart Ven;
	private final ModelPart leftarm;
	private final ModelPart rightarm;
	private final ModelPart body;
	private final ModelPart bone12;
	private final ModelPart bone;
	private final ModelPart bone9;
	private final ModelPart bone11;
	private final ModelPart Box;
	private final ModelPart Box2;
	private final ModelPart Box3;
	private final ModelPart Box4;
	private final ModelPart head;
	private final ModelPart bone10;
	private final ModelPart Jaw;
	private final ModelPart bone8;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart bone4;
	private final ModelPart bone5;
	private final ModelPart Upperjaw;
	private final ModelPart Face;
	private final ModelPart light;
	private final ModelPart fire2;
	private final ModelPart fire;
	private final ModelPart fire3;
	private final ModelPart fire4;
	private final ModelPart body_anim_one;
	private final ModelPart bone6;
	private final ModelPart Box5;
	private final ModelPart Box6;
	private final ModelPart bone7;
	private final ModelPart body_anim;
	private final ModelPart Box7;
	private final ModelPart stick_r2;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart stick_r1;

	public Modelvengeful_heart(ModelPart root) {
		this.Ven = root.getChild("Ven");
		this.leftarm = this.Ven.getChild("leftarm");
		this.rightarm = this.Ven.getChild("rightarm");
		this.body = this.Ven.getChild("body");
		this.bone12 = this.body.getChild("bone12");
		this.bone = this.bone12.getChild("bone");
		this.bone9 = this.bone.getChild("bone9");
		this.bone11 = this.bone.getChild("bone11");
		this.Box = this.bone.getChild("Box");
		this.Box2 = this.bone.getChild("Box2");
		this.Box3 = this.bone.getChild("Box3");
		this.Box4 = this.bone.getChild("Box4");
		this.head = this.bone12.getChild("head");
		this.bone10 = this.head.getChild("bone10");
		this.Jaw = this.head.getChild("Jaw");
		this.bone8 = this.Jaw.getChild("bone8");
		this.bone2 = this.Jaw.getChild("bone2");
		this.bone3 = this.Jaw.getChild("bone3");
		this.bone4 = this.Jaw.getChild("bone4");
		this.bone5 = this.Jaw.getChild("bone5");
		this.Upperjaw = this.head.getChild("Upperjaw");
		this.Face = this.Upperjaw.getChild("Face");
		this.light = this.Face.getChild("light");
		this.fire2 = this.head.getChild("fire2");
		this.fire = this.head.getChild("fire");
		this.fire3 = this.head.getChild("fire3");
		this.fire4 = this.head.getChild("fire4");
		this.body_anim_one = this.body.getChild("body_anim_one");
		this.bone6 = this.body_anim_one.getChild("bone6");
		this.Box5 = this.bone6.getChild("Box5");
		this.Box6 = this.Box5.getChild("Box6");
		this.bone7 = this.bone6.getChild("bone7");
		this.body_anim = this.bone7.getChild("body_anim");
		this.Box7 = this.body_anim.getChild("Box7");
		this.stick_r2 = this.body_anim.getChild("stick_r2");
		this.leftleg = this.bone7.getChild("leftleg");
		this.rightleg = this.bone7.getChild("rightleg");
		this.stick_r1 = this.bone6.getChild("stick_r1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Ven = partdefinition.addOrReplaceChild("Ven", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leftarm = Ven.addOrReplaceChild("leftarm",
				CubeListBuilder.create().texOffs(129, 242).addBox(-8.0F, -3.0F, -2.0F, 8.0F, 17.0F, 8.0F,
						new CubeDeformation(3.0F)),
				PartPose.offsetAndRotation(-12.0F, -14.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition rightarm = Ven.addOrReplaceChild("rightarm",
				CubeListBuilder.create().texOffs(129, 242).addBox(1.0F, -1.0F, -2.0F, 8.0F, 17.0F, 8.0F,
						new CubeDeformation(3.0F)),
				PartPose.offsetAndRotation(11.0F, -16.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition body = Ven.addOrReplaceChild("body", CubeListBuilder.create(),
				PartPose.offset(-1.0F, -15.0F, 0.0F));

		PartDefinition bone12 = body.addOrReplaceChild("bone12", CubeListBuilder.create(),
				PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition bone = bone12.addOrReplaceChild("bone",
				CubeListBuilder.create().texOffs(132, 195)
						.addBox(-7.0F, 3.0F, -6.0F, 17.0F, 14.0F, 15.0F, new CubeDeformation(0.0F)).texOffs(90, 213)
						.addBox(-4.0F, -14.0F, -3.0F, 9.0F, 20.0F, 10.0F, new CubeDeformation(1.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition bone9 = bone.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(197, 195).addBox(-5.0F,
				-60.0F, -3.0F, 9.0F, 42.0F, 10.0F, new CubeDeformation(2.0F)), PartPose.offset(1.0F, -3.0F, 0.0F));

		PartDefinition bone11 = bone.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(241, 152)
				.addBox(-5.0F, -4.0F, -3.0F, 9.0F, 9.0F, 10.0F, new CubeDeformation(2.0F)),
				PartPose.offset(1.0F, -71.0F, 0.0F));

		PartDefinition Box = bone.addOrReplaceChild("Box", CubeListBuilder.create().texOffs(0, 49).addBox(-12.0F, -9.0F,
				-11.0F, 25.0F, 16.0F, 26.0F, new CubeDeformation(1.0F)), PartPose.offset(1.0F, -10.0F, 1.0F));

		PartDefinition Box2 = bone.addOrReplaceChild("Box2",
				CubeListBuilder.create().texOffs(103, 73).addBox(-12.0F, -7.0F, -12.0F, 23.0F, 13.0F, 23.0F,
						new CubeDeformation(1.0F)),
				PartPose.offsetAndRotation(1.0F, -31.0F, 3.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition Box3 = bone.addOrReplaceChild("Box3",
				CubeListBuilder.create().texOffs(0, 92).addBox(-14.0F, -8.0F, -12.0F, 27.0F, 14.0F, 24.0F,
						new CubeDeformation(1.0F)),
				PartPose.offsetAndRotation(1.0F, -74.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Box4 = bone.addOrReplaceChild("Box4",
				CubeListBuilder.create().texOffs(112, 0)
						.addBox(-12.0F, -6.0F, -11.0F, 22.0F, 15.0F, 23.0F, new CubeDeformation(1.0F)).texOffs(196, 73)
						.addBox(12.0F, 1.0F, -11.0F, 6.0F, 8.0F, 23.0F, new CubeDeformation(1.0F)),
				PartPose.offsetAndRotation(1.0F, -54.0F, 3.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition head = bone12.addOrReplaceChild("head", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, -80.0F, -4.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition bone10 = head.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(202, 105)
				.addBox(-6.42F, -6.21F, -6.42F, 14.98F, 7.49F, 16.05F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.07F, -1.28F, -3.21F));

		PartDefinition Jaw = head.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(103, 110)
				.addBox(-14.91F, -1.44F, -11.49F, 29.96F, 8.0F, 20.33F, new CubeDeformation(0.0F)).texOffs(162, 174)
				.addBox(-12.77F, 4.93F, -11.49F, 25.68F, 0.0F, 20.33F, new CubeDeformation(0.0F)),
				PartPose.offset(-0.07F, -14.56F, -0.28F));

		PartDefinition bone8 = Jaw.addOrReplaceChild("bone8", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone8_r1 = bone8.addOrReplaceChild("bone8_r1",
				CubeListBuilder.create().texOffs(236, 195).addBox(0.0F, -15.91F, -6.955F, 0.0F, 19.12F, 15.91F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(9.7F, 2.79F, -1.325F, 0.0F, 0.0F, -0.0436F));

		PartDefinition bone2 = Jaw.addOrReplaceChild("bone2", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-11.63F, -3.21F, -1.605F, 0.0F, 0.0F, -0.2182F));

		PartDefinition bone2_r1 = bone2.addOrReplaceChild("bone2_r1",
				CubeListBuilder.create().texOffs(236, 230).addBox(-32.332F, -129.4151F, -5.35F, 0.0F, 18.19F, 14.91F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.63F, 120.49F, -1.325F, 0.0F, 0.0F, 0.2182F));

		PartDefinition bone3 = Jaw.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(241, 129).addBox(-7.28F,
				-16.47F, 5.63F, 18.98F, 22.47F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.14F, 0.0F, 2.14F));

		PartDefinition bone4 = Jaw.addOrReplaceChild("bone4", CubeListBuilder.create(),
				PartPose.offsetAndRotation(5.21F, 0.0F, -14.98F, 0.0F, 0.0F, 0.1745F));

		PartDefinition bone4_r1 = bone4.addOrReplaceChild("bone4_r1",
				CubeListBuilder.create().texOffs(162, 242).addBox(0.0F, -18.19F, 5.35F, 9.63F, 17.12F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.07F, 7.07F, 0.28F, -0.0105F, -0.0195F, -0.1531F));

		PartDefinition bone5 = Jaw.addOrReplaceChild("bone5", CubeListBuilder.create(),
				PartPose.offset(-13.84F, 0.0F, -14.98F));

		PartDefinition bone5_r1 = bone5.addOrReplaceChild("bone5_r1",
				CubeListBuilder.create().texOffs(162, 242).addBox(-6.0677F, -14.5974F, 5.35F, 9.63F, 17.12F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.42F, 3.86F, 0.28F, 0.0051F, -0.0251F, 0.0073F));

		PartDefinition Upperjaw = head.addOrReplaceChild("Upperjaw", CubeListBuilder.create().texOffs(71, 178)
				.addBox(-8.49F, -10.05F, -7.21F, 16.12F, 20.33F, 14.98F, new CubeDeformation(0.0F)),
				PartPose.offset(-0.07F, -18.84F, -1.35F));

		PartDefinition Face = Upperjaw.addOrReplaceChild("Face", CubeListBuilder.create().texOffs(102, 48)
				.addBox(-54.5F, -157.71F, -8.387F, 54.57F, 23.54F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(27.82F, 139.1F, 1.07F));

		PartDefinition light = Face.addOrReplaceChild("light", CubeListBuilder.create().texOffs(0, 0).addBox(-26.68F,
				-23.17F, 2.543F, 54.57F, 47.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-28.82F, -136.54F, -2.93F));

		PartDefinition fire2 = head.addOrReplaceChild("fire2", CubeListBuilder.create().texOffs(132, 178).addBox(-5.48F,
				-17.08F, -0.35F, 9.63F, 14.98F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-24.48F, -34.28F, -8.21F));

		PartDefinition fire = head.addOrReplaceChild("fire", CubeListBuilder.create().texOffs(132, 178).addBox(-5.48F,
				-15.08F, -0.35F, 9.63F, 14.98F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-24.48F, -36.28F, -8.21F));

		PartDefinition fire3 = head.addOrReplaceChild("fire3", CubeListBuilder.create().texOffs(132, 178).addBox(-4.19F,
				-13.08F, -0.35F, 9.63F, 14.98F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(24.52F, -38.28F, -8.21F));

		PartDefinition fire4 = head.addOrReplaceChild("fire4",
				CubeListBuilder.create().texOffs(132, 178).addBox(-4.19F, -14.08F, -0.35F, 9.63F, 14.98F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(24.52F, -37.28F, -8.21F, 0.1309F, 0.0F, 0.0F));

		PartDefinition body_anim_one = body.addOrReplaceChild("body_anim_one", CubeListBuilder.create().texOffs(0, 207)
				.addBox(-8.0F, -8.0F, 1.0F, 18.0F, 19.0F, 7.0F, new CubeDeformation(1.0F)),
				PartPose.offset(0.0F, 3.0F, 12.0F));

		PartDefinition bone6 = body_anim_one.addOrReplaceChild("bone6", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 12.0F));

		PartDefinition Box5 = bone6.addOrReplaceChild("Box5",
				CubeListBuilder.create().texOffs(81, 138)
						.addBox(-12.0F, -11.0F, -8.0F, 24.0F, 23.0F, 16.0F, new CubeDeformation(1.0F)).texOffs(129, 225)
						.addBox(-7.0F, -18.0F, -8.0F, 14.0F, 5.0F, 11.0F, new CubeDeformation(1.0F)),
				PartPose.offset(1.0F, 0.0F, 8.0F));

		PartDefinition Box6 = Box5.addOrReplaceChild("Box6", CubeListBuilder.create().texOffs(162, 138).addBox(-12.0F,
				-10.0F, -7.0F, 24.0F, 20.0F, 15.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 1.0F, 19.0F));

		PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create(),
				PartPose.offset(0.0F, 2.0F, 40.0F));

		PartDefinition body_anim = bone7.addOrReplaceChild("body_anim",
				CubeListBuilder.create().texOffs(0, 172)
						.addBox(-8.0F, -10.0F, 26.0F, 18.0F, 17.0F, 17.0F, new CubeDeformation(1.0F)).texOffs(90, 244)
						.addBox(-15.0F, -12.0F, 35.0F, 8.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(243, 27)
						.addBox(-7.0F, -18.0F, 35.0F, 9.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(225, 48)
						.addBox(-6.08F, -16.27F, 0.63F, 14.16F, 7.09F, 11.13F, new CubeDeformation(1.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Box7 = body_anim.addOrReplaceChild("Box7", CubeListBuilder.create().texOffs(0, 131)
				.addBox(-11.0F, -9.0F, -8.0F, 21.0F, 21.0F, 19.0F, new CubeDeformation(1.0F)),
				PartPose.offset(1.0F, -3.0F, 8.0F));

		PartDefinition stick_r2 = body_anim.addOrReplaceChild("stick_r2",
				CubeListBuilder.create().texOffs(51, 213).addBox(-4.0F, -17.0F, -5.0F, 9.0F, 23.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, 15.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition leftleg = bone7.addOrReplaceChild("leftleg",
				CubeListBuilder.create().texOffs(243, 0).addBox(-12.5742F, -3.5036F, -4.0F, 9.0F, 18.0F, 8.0F,
						new CubeDeformation(3.0F)),
				PartPose.offsetAndRotation(-10.0F, -8.0F, 24.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition rightleg = bone7.addOrReplaceChild("rightleg",
				CubeListBuilder.create().texOffs(243, 0).addBox(1.1997F, -1.7903F, -6.0F, 9.0F, 18.0F, 8.0F,
						new CubeDeformation(3.0F)),
				PartPose.offsetAndRotation(13.0F, -10.0F, 26.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition stick_r1 = bone6.addOrReplaceChild("stick_r1",
				CubeListBuilder.create().texOffs(204, 0)
						.addBox(-5.0F, -23.0F, -6.0F, 9.0F, 37.0F, 10.0F, new CubeDeformation(1.0F)).texOffs(0, 234)
						.addBox(-5.0F, -43.0F, -6.0F, 9.0F, 18.0F, 10.0F, new CubeDeformation(1.0F)),
				PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Ven.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}