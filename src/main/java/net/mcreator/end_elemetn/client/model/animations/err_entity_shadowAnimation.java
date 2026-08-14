package net.mcreator.end_elemetn.client.model.animations;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

// Save this class in your mod and generate all required imports
/**
 * Made with Blockbench 4.12.3 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class err_entity_shadowAnimation {
	public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(1.4583F)
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.125F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.75F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.875F, KeyframeAnimations.degreeVec(11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0833F, KeyframeAnimations.degreeVec(11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.125F, KeyframeAnimations.degreeVec(11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.3333F, KeyframeAnimations.degreeVec(11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.125F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.75F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.875F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.125F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.75F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.875F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.125F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-11.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
	public static final AnimationDefinition idle = AnimationDefinition.Builder.withLength(2.5833F).looping()
			.addAnimation("rightLeg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.7879F, 17.4828F, 2.6212F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.7879F, 17.4828F, 2.6212F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(13.2746F, -19.5064F, -4.5043F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(13.2746F, -19.5064F, -4.5043F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(13.2746F, -19.5064F, -4.5043F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.1667F, KeyframeAnimations.degreeVec(13.2746F, -19.5064F, -4.5043F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("body",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.1667F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -21.25F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.7917F, KeyframeAnimations.degreeVec(13.8655F, 28.9401F, -37.6885F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.875F, KeyframeAnimations.degreeVec(-84.4751F, -5.6603F, -62.9372F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.9583F, KeyframeAnimations.degreeVec(183.262F, -10.082F, -63.2801F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0417F, KeyframeAnimations.degreeVec(-144.9011F, 13.5746F, -78.122F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0833F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.125F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("pupil",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(5.2187F, 4.9952F, 10.0095F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(10.8246F, 4.2725F, 17.4992F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 7.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.1667F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 7.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -32.5F, -5.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(18.7002F, -30.2253F, -7.4941F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(9.2603F, -31.0671F, -11.6367F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -32.5F, -5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.1667F, KeyframeAnimations.degreeVec(0.0F, -32.5F, -5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.7917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.9167F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0417F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
	public static final AnimationDefinition idle2 = AnimationDefinition.Builder.withLength(0.7917F).looping()
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.7879F, 17.4828F, 2.6212F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.7746F, -19.5064F, -4.5043F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("body",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 30.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("pupil",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(-221.5734F, -51.8864F, 138.4266F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(-56.3946F, -0.5288F, 43.0598F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
}