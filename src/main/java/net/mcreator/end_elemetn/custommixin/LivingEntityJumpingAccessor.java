package net.mcreator.end_elemetn.custommixin;

import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public interface LivingEntityJumpingAccessor {
	@Accessor("jumping")
	boolean end_elemetn$isJumping();
}
