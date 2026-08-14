package net.mcreator.end_elemetn.custommixin;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.entity.TrialSpawnerBlockEntity;
import net.minecraft.world.level.block.entity.trialspawner.PlayerDetector;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerConfig;
import net.minecraft.world.level.storage.loot.LootTable;

import net.mcreator.end_elemetn.custom.NextSpawnDataResettable;

/**
 * data/end_elemetn/trial_spawner/*.json and the trial_spawner/hostile tag do NOT do anything -
 * vanilla trial spawners don't read spawn pools from datapack JSON at all; each spawner's mob list
 * is baked straight into its block entity NBT by the Trial Chamber structure template. The only way
 * to actually add our mobs to vanilla trial spawners is to patch that NBT-derived config after it
 * loads, which is what this does - merging our 4 mobs into whatever pool the spawner already has
 * (never replacing vanilla's own choices), for both the normal and ominous configs.
 */
@Mixin(TrialSpawnerBlockEntity.class)
public abstract class TrialSpawnerMobMixin {
	@Shadow
	private TrialSpawner trialSpawner;

	private static final String[] EXTRA_MOBS = { "end_elemetn:watchling", "end_elemetn:snareling", "end_elemetn:blastling", "end_elemetn:lureling" };
	// No crying counterpart exists for lureling, so it stays as the regular version even here.
	private static final String[] EXTRA_MOBS_OMINOUS = { "end_elemetn:crying_watchling", "end_elemetn:crying_snareling", "end_elemetn:crying_blastling", "end_elemetn:lureling" };
	private static final ResourceKey<LootTable> EXTRA_LOOT = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("end_elemetn:trial_rewards/my_mob_loot"));

	@Inject(method = "loadAdditional", at = @At("TAIL"))
	private void end_elemetn$addOurMobs(CompoundTag tag, HolderLookup.Provider provider, CallbackInfo ci) {
		TrialSpawner current = this.trialSpawner;
		TrialSpawnerConfig normal = current.getNormalConfig();
		TrialSpawnerConfig ominous = current.getOminousConfig();
		TrialSpawnerConfig mergedNormal = end_elemetn$merge(normal, EXTRA_MOBS);
		TrialSpawnerConfig mergedOminous = end_elemetn$merge(ominous, EXTRA_MOBS_OMINOUS);
		if (mergedNormal == normal && mergedOminous == ominous)
			return;

		this.trialSpawner = new TrialSpawner(mergedNormal, mergedOminous, current.getData(), current.getTargetCooldownLength(), current.getRequiredPlayerRange(),
				(TrialSpawnerBlockEntity) (Object) this, PlayerDetector.NO_CREATIVE_PLAYERS, PlayerDetector.EntitySelector.SELECT_FROM_LEVEL);

		// Force a fresh roll of "what spawns next" - that pick is cached and saved in NBT, so a
		// spawner that was already running before our mobs got merged in would otherwise just keep
		// re-serving its old cached choice and never actually reach ours.
		if (current.getData() instanceof NextSpawnDataResettable resettable) {
			resettable.end_elemetn$clearNextSpawnData();
		}
	}

	private static TrialSpawnerConfig end_elemetn$merge(TrialSpawnerConfig config, String[] extraMobs) {
		List<WeightedEntry.Wrapper<SpawnData>> existingSpawns = config.spawnPotentialsDefinition().unwrap();
		Set<String> existingIds = new HashSet<>();
		for (WeightedEntry.Wrapper<SpawnData> entry : existingSpawns) {
			existingIds.add(entry.data().getEntityToSpawn().getString("id"));
		}

		boolean changedSpawns = false;
		SimpleWeightedRandomList.Builder<SpawnData> spawnBuilder = SimpleWeightedRandomList.builder();
		for (WeightedEntry.Wrapper<SpawnData> entry : existingSpawns) {
			spawnBuilder.add(entry.data(), entry.weight().asInt());
		}
		for (String id : extraMobs) {
			if (existingIds.contains(id))
				continue;
			changedSpawns = true;
			CompoundTag entityTag = new CompoundTag();
			entityTag.putString("id", id);
			spawnBuilder.add(new SpawnData(entityTag, Optional.empty(), Optional.empty()), 1);
		}

		List<WeightedEntry.Wrapper<ResourceKey<LootTable>>> existingLoot = config.lootTablesToEject().unwrap();
		boolean hasOurLoot = existingLoot.stream().anyMatch(entry -> entry.data().equals(EXTRA_LOOT));
		SimpleWeightedRandomList<ResourceKey<LootTable>> mergedLoot = config.lootTablesToEject();
		if (!hasOurLoot) {
			SimpleWeightedRandomList.Builder<ResourceKey<LootTable>> lootBuilder = SimpleWeightedRandomList.builder();
			for (WeightedEntry.Wrapper<ResourceKey<LootTable>> entry : existingLoot) {
				lootBuilder.add(entry.data(), entry.weight().asInt());
			}
			lootBuilder.add(EXTRA_LOOT, 1);
			mergedLoot = lootBuilder.build();
		}

		if (!changedSpawns && hasOurLoot)
			return config;

		return new TrialSpawnerConfig(config.spawnRange(), config.totalMobs(), config.simultaneousMobs(), config.totalMobsAddedPerPlayer(), config.simultaneousMobsAddedPerPlayer(),
				config.ticksBetweenSpawn(), spawnBuilder.build(), mergedLoot, config.itemsToDropWhenOminous());
	}
}
