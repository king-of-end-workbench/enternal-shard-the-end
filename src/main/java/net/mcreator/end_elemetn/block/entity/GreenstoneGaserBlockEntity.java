package net.mcreator.end_elemetn.block.entity;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;

import net.mcreator.end_elemetn.block.GreenstoneGaserBlock;
import net.mcreator.end_elemetn.EndElemetnMod;

import java.util.List;

/**
 * Holds the Greenstone Geyser's per-tile timing state. This used to live directly in the block's
 * BlockState as IntegerProperty(0-160)/IntegerProperty(0-300), but that makes StateDefinition
 * build ~193000 permutations (161 * 301 * 4) and hangs the client for minutes populating the
 * state graph during mod loading. Keeping it in the block entity keeps the state count at 1.
 */
public class GreenstoneGaserBlockEntity extends BlockEntity {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EndElemetnMod.MODID);
	public static final RegistryObject<BlockEntityType<GreenstoneGaserBlockEntity>> GREENSTONE_GASER_ENTITY = REGISTRY.register("greenstone_gaser",
			() -> BlockEntityType.Builder.of(GreenstoneGaserBlockEntity::new, GreenstoneGaserBlock.GREENSTONE_GASER.get()).build(null));

	private static final int GEYSER_DURATION_TICKS = 160;
	private static final int COOLDOWN_TICKS_TOTAL = 300;
	private static final int MIN_COLUMN_HEIGHT = 5;
	private static final int MAX_COLUMN_HEIGHT = 8;
	private static final double BOUNCE_VELOCITY = 0.32;
	private static final double COLUMN_RADIUS = 0.4;

	private int activeTicks = 0;
	private int cooldownTicks = 0;
	private int columnHeight = MIN_COLUMN_HEIGHT;

	public GreenstoneGaserBlockEntity(BlockPos pos, BlockState state) {
		super(GREENSTONE_GASER_ENTITY.get(), pos, state);
	}

	public void tryStartEruption(ServerLevel level, BlockPos pos, RandomSource random) {
		if (activeTicks > 0 || cooldownTicks > 0)
			return;
		if (!level.getFluidState(pos.above()).is(FluidTags.WATER))
			return;
		columnHeight = MIN_COLUMN_HEIGHT + random.nextInt(MAX_COLUMN_HEIGHT - MIN_COLUMN_HEIGHT + 1);
		activeTicks = GEYSER_DURATION_TICKS;
		setChanged();
	}

	public void tick(Level level, BlockPos pos, BlockState state) {
		if (!(level instanceof ServerLevel serverLevel))
			return;

		if (activeTicks > 0) {
			erupt(serverLevel, pos, columnHeight);
			activeTicks--;
			if (activeTicks == 0) {
				cooldownTicks = COOLDOWN_TICKS_TOTAL;
			}
			setChanged();
			return;
		}

		if (cooldownTicks > 0) {
			cooldownTicks--;
			if (cooldownTicks == 0 && serverLevel.getFluidState(pos.above()).is(FluidTags.WATER)) {
				columnHeight = MIN_COLUMN_HEIGHT + serverLevel.getRandom().nextInt(MAX_COLUMN_HEIGHT - MIN_COLUMN_HEIGHT + 1);
				activeTicks = GEYSER_DURATION_TICKS;
			}
			setChanged();
		}
	}

	private void erupt(ServerLevel level, BlockPos pos, int columnHeight) {
		double x = pos.getX() + 0.5;
		double z = pos.getZ() + 0.5;
		for (int i = 1; i <= columnHeight; i++) {
			double y = pos.getY() + i;
			level.sendParticles(ParticleTypes.SPLASH, x, y, z, 3, 0.15, 0.1, 0.15, 0.1);
			if (i == columnHeight) {
				level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 2, 0.2, 0.2, 0.2, 0.02);
			}
		}

		AABB column = new AABB(pos).inflate(COLUMN_RADIUS, 0, COLUMN_RADIUS).expandTowards(0, columnHeight, 0);
		List<LivingEntity> riders = level.getEntitiesOfClass(LivingEntity.class, column, LivingEntity::isAlive);
		for (LivingEntity rider : riders) {
			if (rider.getDeltaMovement().y < BOUNCE_VELOCITY) {
				rider.setDeltaMovement(rider.getDeltaMovement().x, BOUNCE_VELOCITY, rider.getDeltaMovement().z);
				rider.hurtMarked = true;
				rider.fallDistance = 0f;
			}
		}
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		activeTicks = tag.getInt("ActiveTicks");
		cooldownTicks = tag.getInt("CooldownTicks");
		columnHeight = tag.contains("ColumnHeight") ? tag.getInt("ColumnHeight") : MIN_COLUMN_HEIGHT;
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("ActiveTicks", activeTicks);
		tag.putInt("CooldownTicks", cooldownTicks);
		tag.putInt("ColumnHeight", columnHeight);
	}
}
