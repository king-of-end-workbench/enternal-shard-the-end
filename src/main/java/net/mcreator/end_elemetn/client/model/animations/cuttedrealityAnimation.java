package net.mcreator.end_elemetn.client.model.animations;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

// Save this class in your mod and generate all required imports
/**
 * Made with Blockbench 5.1.6 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class cuttedrealityAnimation {
	public static final AnimationDefinition open = AnimationDefinition.Builder.withLength(0.0F).build();
	public static final AnimationDefinition opening = AnimationDefinition.Builder.withLength(0.1667F).addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.0833F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR))).build();
}