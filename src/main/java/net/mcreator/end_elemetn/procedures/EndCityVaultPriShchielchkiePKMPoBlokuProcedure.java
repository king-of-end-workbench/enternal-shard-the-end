package net.mcreator.end_elemetn.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModItems;
import net.mcreator.end_elemetn.block.entity.EndCityVaultBlockEntity;
import net.mcreator.end_elemetn.block.EndCityVaultBlock;
import net.mcreator.end_elemetn.custom.SpawnerTierData;

public class EndCityVaultPriShchielchkiePKMPoBlokuProcedure {
	private static void enchant(ItemStack stack, ServerLevel level, ResourceKey<Enchantment> key, int lvl) {
		stack.enchant(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(key), lvl);
	}

	private static void enchantCustom(ItemStack stack, ServerLevel level, String id, int lvl) {
		enchant(stack, level, ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse(id)), lvl);
	}

	private record MaterialEntry(Item item, int maxCount) {
	}

	// Ore / ingot / gem pools the vault draws its random filler materials from - picked from the
	// same tier the special reward came from, so a common vault can't roll netherite scrap.
	private static final MaterialEntry[] LOW_MATERIALS = { new MaterialEntry(Items.IRON_INGOT, 8), new MaterialEntry(Items.GOLD_INGOT, 6), new MaterialEntry(Items.COPPER_INGOT, 8),
			new MaterialEntry(Items.REDSTONE, 8), new MaterialEntry(Items.LAPIS_LAZULI, 8), new MaterialEntry(Items.RAW_IRON, 4), new MaterialEntry(Items.RAW_GOLD, 4),
			new MaterialEntry(Items.RAW_COPPER, 4) };
	private static final MaterialEntry[] MID_MATERIALS = { new MaterialEntry(Items.DIAMOND, 3), new MaterialEntry(Items.EMERALD, 4), new MaterialEntry(Items.AMETHYST_SHARD, 6),
			new MaterialEntry(Items.GOLD_INGOT, 8), new MaterialEntry(Items.IRON_INGOT, 8) };
	private static final MaterialEntry[] HIGH_MATERIALS = { new MaterialEntry(Items.DIAMOND, 4), new MaterialEntry(Items.EMERALD, 6), new MaterialEntry(Items.NETHERITE_SCRAP, 2),
			new MaterialEntry(Items.AMETHYST_SHARD, 8) };

	// Random 2-6 stacks of ore/ingots/gems in random amounts, queued to eject one at a time alongside
	// whatever unique reward the tier gives.
	private static void queueRandomMaterials(EndCityVaultBlockEntity vaultEntity, ServerLevel level, MaterialEntry[] pool) {
		int count = 2 + level.getRandom().nextInt(5);
		for (int i = 0; i < count; i++) {
			MaterialEntry entry = pool[level.getRandom().nextInt(pool.length)];
			int amount = 1 + level.getRandom().nextInt(entry.maxCount());
			vaultEntity.queueEject(new ItemStack(entry.item(), amount));
		}
	}

	private static ItemStack pickRandom(ServerLevel level, ItemStack... options) {
		return options[level.getRandom().nextInt(options.length)].copy();
	}

	private static final Item[] DIAMOND_ARMOR_PIECES = { Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS };

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		// Server-authoritative only. This used to also run on the client, where the block entity's
		// ClaimedPlayers set is never networked (only CurrentTier is) - so the client always saw an
		// empty set and thought every visit was a first visit, running the whole reward branch (and
		// its own local item-count changes) out of step with what the server actually decided.
		if (!(world instanceof ServerLevel level))
			return;

		ItemStack heldItem = entity instanceof LivingEntity livingEntity ? livingEntity.getMainHandItem() : ItemStack.EMPTY;
		Item heldItemType = heldItem.getItem();
		boolean isAnyVaultKey = heldItemType == EndElemetnModItems.ENDSTONE_KEY.get() || heldItemType == EndElemetnModItems.END_CITY_KEY.get()
				|| heldItemType == EndElemetnModItems.END_SOULFUL_KEY.get();
		if (!isAnyVaultKey)
			return;

		BlockPos vaultPos = BlockPos.containing(x, y, z);
		// Each vault stage only opens with its own matching key - an Endstone Key won't open a
		// Soulful vault and vice versa.
		int stage = world.getBlockState(vaultPos).getValue(EndCityVaultBlock.TIER);
		Item requiredKey = SpawnerTierData.keyForTier(stage);
		if (heldItemType != requiredKey) {
			if (entity instanceof Player wrongKeyPlayer)
				wrongKeyPlayer.displayClientMessage(Component.literal("Нужен подходящий ключ для этого хранилища"), true);
			return;
		}

		BlockEntity be = world.getBlockEntity(vaultPos);
		if (!(be instanceof EndCityVaultBlockEntity vaultEntity))
			return;

		// One reward per player per vault - each vault remembers who already claimed it, so the
		// same player can't just keep re-opening it, while a different player still gets their own
		// shot at it.
		Player player = entity instanceof Player p ? p : null;
		if (player != null && vaultEntity.hasClaimed(player.getUUID())) {
			player.displayClientMessage(Component.literal("Вы уже забрали награду из этого хранилища"), true);
			return;
		}

		heldItem.shrink(1);
		{
			int _value = 2;
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
				world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
		}
		// Whatever tier the floating display (rendered straight off this same block entity by
		// EndCityVaultRenderer) happens to be showing at this exact moment is what you get - both
		// read the identical stored tier, so there's no separate hidden roll. Every reward is
		// pushed into the block entity's eject queue instead of dropped instantly, so it pops out
		// as a little one-by-one sequence (0.5s apart) with a random 1-4 batch of ore/ingots/gems
		// on top of the tier's unique item(s).
		int tier = vaultEntity.getCurrentTier();
		if (tier == 0) {
			vaultEntity.queueEject(pickRandom(level, new ItemStack(Items.IRON_SWORD), new ItemStack(Items.IRON_AXE), new ItemStack(Items.IRON_PICKAXE), new ItemStack(Items.SHIELD)));
			queueRandomMaterials(vaultEntity, level, LOW_MATERIALS);
		} else if (tier == 1) {
			vaultEntity
					.queueEject(pickRandom(level, new ItemStack(Items.GOLDEN_APPLE), new ItemStack(Items.BOW), new ItemStack(Items.CROSSBOW), new ItemStack(Items.SHIELD)));
			queueRandomMaterials(vaultEntity, level, LOW_MATERIALS);
		} else if (tier == 2) {
			vaultEntity.queueEject(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
			vaultEntity.queueEject(pickRandom(level, new ItemStack(Items.BREEZE_ROD), new ItemStack(Items.RECOVERY_COMPASS), new ItemStack(Items.ENDER_PEARL, 4)));
			queueRandomMaterials(vaultEntity, level, MID_MATERIALS);
		} else if (tier == 3) {
			ItemStack stack = new ItemStack(Items.ELYTRA);
			enchant(stack, level, Enchantments.UNBREAKING, 3);
			vaultEntity.queueEject(stack);
			vaultEntity.queueEject(pickRandom(level, new ItemStack(Items.FIREWORK_ROCKET, 8), new ItemStack(Items.PHANTOM_MEMBRANE, 4)));
			queueRandomMaterials(vaultEntity, level, MID_MATERIALS);
		} else if (tier == 4) {
			Item piece = DIAMOND_ARMOR_PIECES[level.getRandom().nextInt(DIAMOND_ARMOR_PIECES.length)];
			vaultEntity.queueEject(new ItemStack(piece));
			vaultEntity.queueEject(pickRandom(level, new ItemStack(Items.DIAMOND_SWORD), new ItemStack(Items.DIAMOND_AXE), new ItemStack(Items.TRIDENT)));
			ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
			enchant(book, level, Enchantments.MENDING, 1);
			vaultEntity.queueEject(book);
			queueRandomMaterials(vaultEntity, level, HIGH_MATERIALS);
		} else {
			vaultEntity.queueEject(new ItemStack(EndElemetnModItems.ENDERITE_LOOTBOX.get()));
			vaultEntity.queueEject(new ItemStack(Items.NETHERITE_INGOT));
			ItemStack visionBook = new ItemStack(Items.ENCHANTED_BOOK);
			enchantCustom(visionBook, level, "end_elemetn:ender_vision", 1);
			vaultEntity.queueEject(visionBook);
			ItemStack walkerBook = new ItemStack(Items.ENCHANTED_BOOK);
			enchantCustom(walkerBook, level, "end_elemetn:void_walker", 4);
			vaultEntity.queueEject(walkerBook);
			vaultEntity.queueEject(pickRandom(level, new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), new ItemStack(Items.TOTEM_OF_UNDYING)));
			queueRandomMaterials(vaultEntity, level, HIGH_MATERIALS);
		}
		// Only marked as claimed now that the reward has actually been queued - marking it right
		// after the key check (as before) meant that if anything above ever failed, the key was
		// already gone and the vault would refuse the player forever with nothing ever given.
		if (player != null) {
			vaultEntity.markClaimed(player.getUUID());
		}
		// Closing the vault back up is handled by EndCityVaultBlockEntity.serverTick once the whole
		// reward sequence has actually finished ejecting, instead of a fixed timer here.
	}
}