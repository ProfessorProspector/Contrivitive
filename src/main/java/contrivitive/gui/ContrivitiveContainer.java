package contrivitive.gui;

import contrivitive.gui.element.slot.PlayerSlotElement;
import contrivitive.gui.element.slot.SlotElement;
import contrivitive.util.ContrivitiveSlot;
import contrivitive.util.Pair;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.MutableTriple;

import java.util.ArrayList;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class ContrivitiveContainer extends Container {
	public final GuiContainerBlueprint blueprint;
	public final ArrayList<MutableTriple<IntSupplier, IntConsumer, Integer>> integerValues = new ArrayList<>();
	public final ArrayList<MutableTriple<IntSupplier, IntConsumer, Short>> shortValues = new ArrayList<>();
	private Integer[] integerParts;

	public ContrivitiveContainer(GuiContainerBlueprint blueprint, EntityPlayer player) {
		this.blueprint = blueprint;

		for (Pair<IntSupplier, IntConsumer> syncable : blueprint.shortSyncables)
			this.shortValues.add(MutableTriple.of(syncable.getA(), syncable.getB(), (short) 0));
		this.shortValues.trimToSize();

		for (Pair<IntSupplier, IntConsumer> syncable : blueprint.intSyncables) {
			this.integerValues.add(MutableTriple.of(syncable.getA(), syncable.getB(), 0));
		}
		this.integerValues.trimToSize();

		this.integerParts = new Integer[this.integerValues.size()];
		addSlots();
		addPlayerSlots(player);
	}

	private void addSlots() {
		for (SlotElement slot : blueprint.slots) {
			if (slot.getSlotId() >= 0) {
				addSlotToContainer(new ContrivitiveSlot(slot.getSlotInventory(), slot.getSlotId(), slot.getSlotX(), slot.getSlotY()).setFilter(slot.filter));
			}
		}
	}

	private void addPlayerSlots(EntityPlayer player) {
		for (PlayerSlotElement slot : blueprint.playerSlots) {
			addSlotToContainer(new Slot(player.inventory, slot.getIndex(), slot.getX(), slot.getY()));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < blueprint.slots.size()) {
				if (!this.mergeItemStack(itemstack1, blueprint.slots.size(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, blueprint.slots.size(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
		//		return provider.canInteractWith(playerIn);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (final IContainerListener listener : this.listeners) {

			int i = 0;
			if (!this.shortValues.isEmpty())
				for (final MutableTriple<IntSupplier, IntConsumer, Short> value : this.shortValues) {
					final short supplied = (short) value.getLeft().getAsInt();
					if (supplied != value.getRight()) {

						listener.sendWindowProperty(this, i, supplied);
						value.setRight(supplied);
					}
					i++;
				}

			if (!this.integerValues.isEmpty())
				for (final MutableTriple<IntSupplier, IntConsumer, Integer> value : this.integerValues) {
					final int supplied = value.getLeft().getAsInt();
					if (supplied != value.getRight()) {

						listener.sendWindowProperty(this, i, supplied >> 16);
						listener.sendWindowProperty(this, i + 1, (short) (supplied & 0xFFFF));
						value.setRight(supplied);
					}
					i += 2;
				}
		}
	}

	@Override
	public void addListener(final IContainerListener listener) {
		super.addListener(listener);

		int i = 0;
		if (!this.shortValues.isEmpty())
			for (final MutableTriple<IntSupplier, IntConsumer, Short> value : this.shortValues) {
				final short supplied = (short) value.getLeft().getAsInt();

				listener.sendWindowProperty(this, i, supplied);
				value.setRight(supplied);
				i++;
			}

		if (!this.integerValues.isEmpty())
			for (final MutableTriple<IntSupplier, IntConsumer, Integer> value : this.integerValues) {
				final int supplied = value.getLeft().getAsInt();

				listener.sendWindowProperty(this, i, supplied >> 16);
				listener.sendWindowProperty(this, i + 1, (short) (supplied & 0xFFFF));
				value.setRight(supplied);
				i += 2;
			}

	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(final int id, final int value) {

		if (id < this.shortValues.size()) {
			this.shortValues.get(id).getMiddle().accept((short) value);
			this.shortValues.get(id).setRight((short) value);
		} else if (id - this.shortValues.size() < this.integerValues.size() * 2) {

			if ((id - this.shortValues.size()) % 2 == 0)
				this.integerParts[(id - this.shortValues.size()) / 2] = value;
			else {
				this.integerValues.get((id - this.shortValues.size()) / 2).getMiddle().accept(
					(this.integerParts[(id - this.shortValues.size()) / 2] & 0xFFFF) << 16 | value & 0xFFFF);
			}
		}
	}
}
