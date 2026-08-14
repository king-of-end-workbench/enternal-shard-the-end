// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 4.12.3 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class enderomAnimation {
	public static final AnimationDefinition idle = AnimationDefinition.Builder.withLength(2.5F).looping()
			.addAnimation("rightLeg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 22.5F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg",
					new AnimationChannel(AnimationChannel.Targets.POSITION,
							new Keyframe(0.0F, KeyframeAnimations.posVec(-1.0F, 0.0F, 2.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("body",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 25.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 5.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 9.9811F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 5.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(-0.0568F, 0.4319F, -9.9812F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(-0.0189F, 0.4352F, -4.9811F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone2",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.33F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -8.33F),
									AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1.0833F).looping()
			.addAnimation("rightLeg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0833F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition TR = AnimationDefinition.Builder.withLength(0.9583F)
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(14.8774F, -1.936F, 7.2472F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(-61.9332F, -11.0686F, -5.8449F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(-69.0F, -21.5F, 18.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-62.0F, -12.0F, -3.5F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -21.5F, 18.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9583F, KeyframeAnimations.degreeVec(0.0F, -12.0F, 3.5F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3",
					new AnimationChannel(AnimationChannel.Targets.POSITION,
							new Keyframe(0.0F, KeyframeAnimations.posVec(-2.0F, 2.0F, -21.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.posVec(-1.0F, 2.0F, -21.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9583F, KeyframeAnimations.posVec(-2.0F, 2.0F, -21.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3",
					new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.0F,
							KeyframeAnimations.scaleVec(0.7F, 0.7F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
}