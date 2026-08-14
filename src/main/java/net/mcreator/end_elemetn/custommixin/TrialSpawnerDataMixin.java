package net.mcreator.end_elemetn.custommixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerData;

import net.mcreator.end_elemetn.custom.NextSpawnDataResettable;

/**
 * "next spawn data" (which mob is about to spawn) is cached and persisted in NBT, and only re-rolls
 * once it's consumed - so a spawner that was already ticking before our mobs were merged into its
 * pool can keep re-serving its old cached pick indefinitely. This lets TrialSpawnerMobMixin force a
 * fresh roll right after merging, so our mobs actually get a chance immediately.
 */
@Mixin(TrialSpawnerData.class)
public abstract class TrialSpawnerDataMixin implements NextSpawnDataResettable {
	@Shadow
	@Mutable
	protected Optional<SpawnData> nextSpawnData;

	@Override
	public void end_elemetn$clearNextSpawnData() {
		this.nextSpawnData = Optional.empty();
	}
}
