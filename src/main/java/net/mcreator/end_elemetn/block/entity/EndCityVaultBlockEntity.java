package net.mcreator.end_elemetn.block.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.block.EndCityVaultBlock;
import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

/**
 * Rebuilt after vanilla's own VaultBlockEntity/VaultRenderer: the floating preview item is rendered
 * directly by a BlockEntityRenderer (EndCityVaultRenderer), NOT by spawning a real entity into the
 * world - that spawned-entity approach was the source of every earlier bug here (transparency
 * fighting with the entity's own render layer, position drifting from the block, and needing manual
 * cleanup on block break). The renderer just stops drawing the moment this block entity is gone.
 */
public class EndCityVaultBlockEntity extends BlockEntity {
	private final Set<UUID> claimedPlayers = new HashSet<>();
	private final Deque<ItemStack> ejectQueue = new ArrayDeque<>();
	private int ejectCooldown = 0;
	private int currentTier = 0;
	private float spin;
	private float previousSpin;

	/**
	 * Rewards are ejected one stack at a time (0.5s apart) instead of all at once, so opening the
	 * vault reads as a little sequence rather than a single instant dump.
	 */
	public void queueEject(ItemStack stack) {
		ejectQueue.add(stack);
	}

	public EndCityVaultBlockEntity(BlockPos pos, BlockState state) {
		super(EndElemetnModBlockEntities.END_CITY_VAULT.get(), pos, state);
	}

	public boolean hasClaimed(UUID player) {
		return claimedPlayers.contains(player);
	}

	public void markClaimed(UUID player) {
		claimedPlayers.add(player);
		this.setChanged();
	}

	public int getCurrentTier() {
		return currentTier;
	}

	public float getSpin() {
		return spin;
	}

	public float getPreviousSpin() {
		return previousSpin;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, EndCityVaultBlockEntity be) {
		// Tracks the blockstate value across this whole tick call - state.getValue(...) on the
		// original parameter would still read the OLD value right after we call setBlock below, since
		// that parameter isn't updated in place. Without this, closing back to 0 and then immediately
		// re-checking "is a player nearby" in the same tick both worked off a stale "still 2" read,
		// which left the vault stuck lit up (blockstate 1) forever once a player had ever stood near
		// it, since nothing ever set it back down to 0.
		int blockstate = state.getValue(EndCityVaultBlock.BLOCKSTATE);

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
		} else if (be.ejectCooldown <= 0 && blockstate == 2) {
			// Whole reward sequence has finished popping out - only now close the vault back up,
			// instead of a fixed 1s timer that could close it mid-sequence.
			level.setBlock(pos, state.setValue(EndCityVaultBlock.BLOCKSTATE, 0), 3);
			blockstate = 0;
		}

		long time = level.getGameTime();
		if (time % 40L == 0L) {
			// The vault's TIER blockstate (endstone/end city/soulful) picks which HALF of the 6-entry
			// reward ladder this vault can ever land on - endstone only ever shows/gives rewards 0-1,
			// end city 2-3, soulful 4-5 - so the display cycles within just those 2 slots.
			int stage = state.getValue(EndCityVaultBlock.TIER);
			int rewardsPerStage = EndCityVaultBlock.REWARD_TIER_COUNT / 3;
			int nextTier = stage * rewardsPerStage + (int) ((time / 40L) % rewardsPerStage);
			if (nextTier != be.currentTier) {
				be.currentTier = nextTier;
				be.setChanged();
				if (level instanceof ServerLevel serverLevel) {
					serverLevel.sendParticles(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 0.55, pos.getZ() + 0.5, 6, 0.25, 0.25, 0.25, 0.02);
				}
				level.sendBlockUpdated(pos, state, state, 3);
			}
		}

		boolean playerNearby = !level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(3.0)).isEmpty();
		if (playerNearby && blockstate == 0) {
			level.setBlock(pos, state.setValue(EndCityVaultBlock.BLOCKSTATE, 1), 3);
		} else if (!playerNearby && blockstate == 1) {
			// Was missing entirely before - once a player triggered the "lit up" state there was no
			// code path that ever cleared it back to 0, so the vault stayed lit forever afterwards.
			level.setBlock(pos, state.setValue(EndCityVaultBlock.BLOCKSTATE, 0), 3);
		}
	}

	public static void clientTick(Level level, BlockPos pos, BlockState state, EndCityVaultBlockEntity be) {
		be.previousSpin = be.spin;
		be.spin = Mth.wrapDegrees(be.spin + 18.0F);
	}

	@Nullable
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();
		tag.putInt("CurrentTier", currentTier);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
		if (tag.contains("CurrentTier")) {
			currentTier = tag.getInt("CurrentTier");
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putInt("CurrentTier", currentTier);
		ListTag list = new ListTag();
		for (UUID player : claimedPlayers) {
			list.add(NbtUtils.createUUID(player));
		}
		tag.put("ClaimedPlayers", list);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		if (tag.contains("CurrentTier")) {
			currentTier = tag.getInt("CurrentTier");
		}
		claimedPlayers.clear();
		if (tag.contains("ClaimedPlayers", 9)) {
			ListTag list = tag.getList("ClaimedPlayers", 11);
			for (int i = 0; i < list.size(); i++) {
				claimedPlayers.add(NbtUtils.loadUUID(list.get(i)));
			}
		}
	}
}
