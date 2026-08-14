package net.mcreator.end_elemetn.custom;

/**
 * Duck interface implemented by TrialSpawnerDataMixin, letting TrialSpawnerMobMixin force a
 * fresh re-roll of the spawner's cached "next mob to spawn" after adding our mobs to its pool.
 */
public interface NextSpawnDataResettable {
	void end_elemetn$clearNextSpawnData();
}
