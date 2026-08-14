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
public class necrosent2Animation {
	public static final AnimationDefinition necrosent_attack = AnimationDefinition.Builder.withLength(2.0F).looping()
			.addAnimation("body",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArmUpper",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(-145.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(-72.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(-72.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArmLower", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(-20.0F, 12.5F, -12.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(-20.0F, 12.5F, -12.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArmUpper",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(10.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArmLower",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 15.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(-50.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(-50.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("blastlingRightArmUpper",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(-23.33F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.75F, KeyframeAnimations.degreeVec(-9.16F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("blastlingRightArmLower",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(-13.33F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.75F, KeyframeAnimations.degreeVec(-4.16F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
}