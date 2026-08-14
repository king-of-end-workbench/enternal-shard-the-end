// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 4.12.3 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class babyenderAnimation {
	public static final AnimationDefinition idle = AnimationDefinition.Builder.withLength(1.75F).looping()
			.addAnimation("bone",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone2",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone4",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 15.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(0.5F).looping()
			.addAnimation("bone",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.1667F, KeyframeAnimations.degreeVec(32.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone2",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.1667F, KeyframeAnimations.degreeVec(32.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3",
					new AnimationChannel(AnimationChannel.Targets.POSITION,
							new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone4",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone5",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.1667F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone6",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.build();
}