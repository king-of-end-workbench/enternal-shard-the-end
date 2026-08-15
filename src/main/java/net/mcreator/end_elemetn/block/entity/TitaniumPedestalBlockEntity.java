package net.mcreator.end_elemetn.block.entity;

import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.end_elemetn.init.EndElemetnModBlockEntities;

import javax.annotation.Nullable;

import java.util.stream.IntStream;

public class TitaniumPedestalBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private NonNullList<ItemStack> stacks = NonNullList.withSize(9, ItemStack.EMPTY);
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

	public static final int MAX_CRYSTAL_CHARGES = 5;
	public static final int STAR_CHARGES = 6;

	private int charges = 0;
	private boolean viaStar = false;

	public TitaniumPedestalBlockEntity(BlockPos position, BlockState state) {
		super(EndElemetnModBlockEntities.TITANIUM_PEDESTAL.get(), position, state);
	}

	public int getCharges() {
		return charges;
	}

	public boolean isViaStar() {
		return viaStar;
	}

	/** 0=inactive, 1=active, 2..5=one..four (crystal count), 6=star. */
	public int getDisplayIndex() {
		if (charges <= 0)
			return 0;
		return viaStar ? 6 : charges;
	}

	public void addCrystalCharge() {
		if (!viaStar && charges < MAX_CRYSTAL_CHARGES)
			charges++;
		syncToClient();
	}

	public void setStarCharge() {
		charges = STAR_CHARGES;
		viaStar = true;
		syncToClient();
	}

	/** Called when a respawn actually consumes one charge of this pedestal. Returns true if a charge was spent. */
	public boolean consumeCharge() {
		if (charges <= 0)
			return false;
		charges--;
		if (charges <= 0)
			viaStar = false;
		syncToClient();
		return true;
	}

	private void syncToClient() {
		setChanged();
		if (level != null) {
			BlockState state = getBlockState();
			if (state.getBlock() instanceof net.mcreator.end_elemetn.block.TitaniumPedestalBlock) {
				int index = getDisplayIndex();
				if (state.getValue(net.mcreator.end_elemetn.block.TitaniumPedestalBlock.BLOCKSTATE) != index)
					level.setBlock(getBlockPos(), state.setValue(net.mcreator.end_elemetn.block.TitaniumPedestalBlock.BLOCKSTATE, index), 3);
			}
		}
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks);
		this.charges = compound.getInt("Charges");
		this.viaStar = compound.getBoolean("ViaStar");
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks);
		}
		compound.putInt("Charges", this.charges);
		compound.putBoolean("ViaStar", this.viaStar);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithFullMetadata();
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
		return Component.literal("titanium_pedestal");
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return ChestMenu.threeRows(id, inventory);
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Titanium Pedestal");
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

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
			return handlers[facing.ordinal()].cast();
		return super.getCapability(capability, facing);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers)
			handler.invalidate();
	}
}