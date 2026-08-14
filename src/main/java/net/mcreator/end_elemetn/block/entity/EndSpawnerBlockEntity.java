package net.mcreator.end_elemetn.block.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.end_elemetn.utils.ExtendedFluidTank;
import net.mcreator.end_elemetn.custom.SpawnerTierData;
import net.mcreator.end_elemetn.block.EndSpawnerBlock;
import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

import javax.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * A miniature Trial Spawner: idle -> (player detected, off cooldown) -> spawns 3 waves of 3-6 mobs
 * one wave at a time -> once every wave is cleared, ejects a reward sequence (food / defense item /
 * keys) -> 20 minute cooldown -> idle again. The mob pool comes from the block's TIER (Endstone/End
 * City/Soulful), which normally only ever goes up via a nearby player's Bad Omen level, unless a
 * creative player has manually locked it. A spawn egg used on the block overrides which single mob
 * type it always spawns; otherwise one is rolled once from the tier pool and remembered.
 */
public class EndSpawnerBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private NonNullList<ItemStack> stacks = NonNullList.withSize(9, ItemStack.EMPTY);

	private static final int TOTAL_WAVES = 3;
	private static final int COOLDOWN_TICKS = 24000; // ~20 minutes
	private static final int DETECTION_RANGE = 12;
	private static final int WAVE_PAUSE_TICKS = 20;

	private int currentWave = 0;
	private int waveDelay = 0;
	private int cooldown = 0;
	private boolean tierLocked = false;
	@Nullable
	private EntityType<?> designatedMob = null;
	private final List<UUID> aliveMobs = new ArrayList<>();
	private final Deque<ItemStack> ejectQueue = new ArrayDeque<>();
	private int ejectCooldown = 0;

	private float spin;
	private float previousSpin;

	public EndSpawnerBlockEntity(BlockPos position, BlockState state) {
		super(EndElemetnModBlockEntities.END_SPAWNER.get(), position, state);
	}

	@Nullable
	public EntityType<?> getDesignatedMob() {
		return designatedMob;
	}

	public float getSpin() {
		return spin;
	}

	public float getPreviousSpin() {
		return previousSpin;
	}

	/**
	 * Called when a player uses a spawn egg on the block - overrides whatever mob was previously
	 * designated (structure-rolled or from an earlier egg), effective on the next trial.
	 */
	public void setDesignatedMob(EntityType<?> type) {
		this.designatedMob = type;
		this.setChanged();
	}

	public void setTierLocked(boolean locked) {
		this.tierLocked = locked;
		this.setChanged();
	}

	/**
	 * Forces the next trial to re-roll its mob from whatever tier the block is set to. Without this,
	 * a creative player forcing the tier to Soulful had no visible effect until a full wave cycle
	 * happened to complete on its own, since the designated mob is otherwise only cleared then.
	 */
	public void resetDesignatedMob() {
		this.designatedMob = null;
		this.setChanged();
	}

	public boolean isTierLocked() {
		return tierLocked;
	}

	private static BlockPos findSpawnPosition(Level level, BlockPos center, RandomSource random) {
		for (int attempt = 0; attempt < 16; attempt++) {
			int dx = random.nextInt(5) - 2;
			int dz = random.nextInt(5) - 2;
			int dy = random.nextInt(3) - 1;
			BlockPos candidate = center.offset(dx, dy, dz);
			if (level.getBlockState(candidate).getCollisionShape(level, candidate).isEmpty()
					&& level.getBlockState(candidate.above()).getCollisionShape(level, candidate.above()).isEmpty()
					&& !level.getBlockState(candidate.below()).getCollisionShape(level, candidate.below()).isEmpty()) {
				return candidate;
			}
		}
		return null;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState originalState, EndSpawnerBlockEntity be) {
		// Tracks the state actually set this tick - state.setValue(...) on the ORIGINAL parameter
		// always derives from the value it had when the tick started, so chaining two setBlock calls
		// off that same stale parameter (e.g. bump TIER, then separately bump BLOCKSTATE) made the
		// second call silently overwrite the first back to its old value. This is exactly the bug
		// that made a Bad Omen tier upgrade never actually stick - the very next setBlock call in the
		// same tick (switching to blockstate 1) reset TIER back to whatever it was at tick start.
		BlockState state = originalState;
		int blockstate = state.getValue(EndSpawnerBlock.BLOCKSTATE);
		int tier = state.getValue(EndSpawnerBlock.TIER);

		if (!be.ejectQueue.isEmpty()) {
			if (be.ejectCooldown <= 0) {
				ItemStack next = be.ejectQueue.poll();
				if (level instanceof ServerLevel serverLevel) {
					ItemEntity itemEntity = new ItemEntity(serverLevel, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, next);
					itemEntity.setPickUpDelay(10);
					serverLevel.addFreshEntity(itemEntity);
					serverLevel.sendParticles(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5, 4, 0.2, 0.2, 0.2, 0.01);
				}
				be.ejectCooldown = 10;
			} else {
				be.ejectCooldown--;
			}
			return;
		} else if (blockstate == 2) {
			// Whole reward has finished popping out - go on cooldown and reset for the next trial.
			be.cooldown = COOLDOWN_TICKS;
			be.currentWave = 0;
			be.designatedMob = null;
			state = state.setValue(EndSpawnerBlock.BLOCKSTATE, 0);
			level.setBlock(pos, state, 3);
			blockstate = 0;
		}

		if (blockstate == 0) {
			if (be.cooldown > 0) {
				be.cooldown--;
				return;
			}
			// Only a survival player nearby can kick off a trial - creative/spectator/adventure
			// players just flying or looking around shouldn't wake the spawner up.
			List<Player> nearby = level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(DETECTION_RANGE)).stream()
					.filter(player -> player instanceof ServerPlayer serverPlayer && serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL).toList();
			if (nearby.isEmpty())
				return;

			if (!be.tierLocked) {
				int bestAmplifier = -1;
				for (Player player : nearby) {
					MobEffectInstance effect = player.getEffect(MobEffects.BAD_OMEN);
					if (effect != null)
						bestAmplifier = Math.max(bestAmplifier, effect.getAmplifier());
				}
				int upgradedTier = tier;
				if (bestAmplifier >= 3)
					upgradedTier = 2;
				else if (bestAmplifier >= 0)
					upgradedTier = Math.max(tier, 1);
				if (upgradedTier != tier) {
					tier = upgradedTier;
					state = state.setValue(EndSpawnerBlock.TIER, tier);
					level.setBlock(pos, state, 3);
				}
			}

			if (be.designatedMob == null) {
				be.designatedMob = SpawnerTierData.rollMob(SpawnerTierData.mobsForTier(tier), level.getRandom());
				be.setChanged();
			}
			state = state.setValue(EndSpawnerBlock.BLOCKSTATE, 1);
			level.setBlock(pos, state, 3);
			return;
		}

		// blockstate == 1: a trial is in progress
		if (level instanceof ServerLevel serverLevel) {
			be.aliveMobs.removeIf(uuid -> !(serverLevel.getEntity(uuid) instanceof LivingEntity living) || !living.isAlive());
		}
		if (!be.aliveMobs.isEmpty())
			return;

		if (be.waveDelay > 0) {
			be.waveDelay--;
			return;
		}

		if (be.currentWave >= TOTAL_WAVES) {
			be.ejectQueue.addAll(SpawnerTierData.rewardsForTier(tier, level.getRandom()));
			state = state.setValue(EndSpawnerBlock.BLOCKSTATE, 2);
			level.setBlock(pos, state, 3);
			return;
		}

		be.currentWave++;
		int waveSize = 3 + level.getRandom().nextInt(4);
		EntityType<?> mobType = be.designatedMob != null ? be.designatedMob : SpawnerTierData.rollMob(SpawnerTierData.mobsForTier(tier), level.getRandom());
		if (level instanceof ServerLevel serverLevel) {
			for (int i = 0; i < waveSize; i++) {
				BlockPos spawnPos = findSpawnPosition(level, pos, level.getRandom());
				if (spawnPos == null)
					continue;
				Entity spawned = mobType.spawn(serverLevel, spawnPos, MobSpawnType.TRIAL_SPAWNER);
				if (spawned == null)
					continue;
				spawned.setYRot(level.getRandom().nextFloat() * 360F);
				// Marks this specific mob as spawner-born so its species' otherwise-neutral AI
				// proactively hunts the player - the same species spawned any other way stays
				// neutral. Persisted NBT, not a runtime-only flag, so it survives a chunk reload.
				spawned.getPersistentData().putBoolean("end_elemetn_from_spawner", true);
				be.aliveMobs.add(spawned.getUUID());
				if (tier == 2 && spawned instanceof LivingEntity living) {
					living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 0));
					living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 0));
				}
				// Force immediate aggro on whoever triggered the trial instead of waiting for the
				// mob's own AI to eventually notice - a spawner-summoned mob should always come
				// straight at the player, unlike the same species found wandering the world.
				if (spawned instanceof Mob mobEntity) {
					Player nearestPlayer = serverLevel.getNearestPlayer(spawned, DETECTION_RANGE);
					if (nearestPlayer != null)
						mobEntity.setTarget(nearestPlayer);
				}
				serverLevel.sendParticles(ParticleTypes.END_ROD, spawned.getX(), spawned.getY() + 0.5, spawned.getZ(), 12, 0.3, 0.5, 0.3, 0.02);
			}
		}
		be.waveDelay = WAVE_PAUSE_TICKS;
		be.setChanged();
	}

	public static void clientTick(Level level, BlockPos pos, BlockState state, EndSpawnerBlockEntity be) {
		be.previousSpin = be.spin;
		be.spin = Mth.wrapDegrees(be.spin + 4.0F);
		if (level.getRandom().nextInt(4) == 0) {
			level.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.3 + level.getRandom().nextDouble() * 0.4, pos.getY() + 0.3 + level.getRandom().nextDouble() * 0.4,
					pos.getZ() + 0.3 + level.getRandom().nextDouble() * 0.4, 0.0, 0.02, 0.0);
		}
	}

	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.loadAdditional(compound, lookupProvider);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks, lookupProvider);

		currentWave = compound.getInt("CurrentWave");
		waveDelay = compound.getInt("WaveDelay");
		cooldown = compound.getInt("Cooldown");
		tierLocked = compound.getBoolean("TierLocked");
		designatedMob = compound.contains("DesignatedMob") ? BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse(compound.getString("DesignatedMob"))) : null;
		aliveMobs.clear();
		if (compound.contains("AliveMobs", 9)) {
			ListTag list = compound.getList("AliveMobs", 11);
			for (int i = 0; i < list.size(); i++)
				aliveMobs.add(NbtUtils.loadUUID(list.get(i)));
		}
	}

	@Override
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
		super.saveAdditional(compound, lookupProvider);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks, lookupProvider);
		}
		compound.putInt("CurrentWave", currentWave);
		compound.putInt("WaveDelay", waveDelay);
		compound.putInt("Cooldown", cooldown);
		compound.putBoolean("TierLocked", tierLocked);
		if (designatedMob != null)
			compound.putString("DesignatedMob", BuiltInRegistries.ENTITY_TYPE.getKey(designatedMob).toString());
		ListTag list = new ListTag();
		for (UUID uuid : aliveMobs)
			list.add(NbtUtils.createUUID(uuid));
		compound.put("AliveMobs", list);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
		return this.saveWithFullMetadata(lookupProvider);
	}

	@Override
	public int getContainerSize() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Component getDefaultName() {
		return Component.literal("end_spawner");
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return ChestMenu.threeRows(id, inventory);
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("End Spawner");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.stacks;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemstack, @Nullable Direction direction) {
		return this.canPlaceItem(index, itemstack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack itemstack, Direction direction) {
		return true;
	}

	// FtaO: Holds all fluid tanks + extra with individual type setting
	public final ExtendedFluidTank[] fluidTanks = {};
}
