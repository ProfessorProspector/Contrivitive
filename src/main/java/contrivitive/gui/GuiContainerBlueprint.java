package contrivitive.gui;

import contrivitive.gui.element.slot.PlayerSlotElement;
import contrivitive.gui.element.slot.SlotElement;
import contrivitive.gui.element.slot.SlotType;
import contrivitive.util.ContrivitiveSlot;
import contrivitive.util.Pair;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class GuiContainerBlueprint extends GuiBlueprint {

	public int playerInvX = -1;
	public int playerInvY = -1;
	public List<SlotElement> slots = new ArrayList<>();
	public List<PlayerSlotElement> playerSlots = new ArrayList<>();

	public GuiContainerBlueprint(int width, int height) {
		super(width, height);
	}

	public GuiContainerBlueprint addPlayerSlot(String page, int x, int y, int index) {
		PlayerSlotElement element = new PlayerSlotElement(x, y, index);
		at(x, y, element);
		playerSlots.add(element);
		return this;
	}

	public GuiContainerBlueprint addPlayerSlot(int x, int y, int index) {
		return addPlayerSlot("main", x, y, index);
	}

	public GuiContainerBlueprint addSlot(String page, SlotElement slot) {
		slot.setSlotId(slots.size());
		this.slots.add(slot);
		at(page, slot.getSlotX(), slot.getSlotY(), slot);
		return this;
	}

	public GuiContainerBlueprint addSlot(String page, ItemStackHandler inventory, SlotType type, int x, int y) {
		addSlot(page, new SlotElement(inventory, x + type.getSlotOffsetX(), y + type.getSlotOffsetY(), type, (slot, stack) -> true));
		return this;
	}

	public GuiContainerBlueprint addSlot(String page, ItemStackHandler inventory, int x, int y) {
		return addSlot(page, inventory, SlotType.NORMAL, x, y);
	}

	public GuiContainerBlueprint addSlot(SlotElement slot) {
		return addSlot("main", slot);
	}

	public GuiContainerBlueprint addSlot(ItemStackHandler inventory, SlotType type, int x, int y) {
		addSlot(inventory, type, x, y, (slot, stack) -> true);
		return this;
	}

	public GuiContainerBlueprint addSlot(ItemStackHandler inventory, int x, int y) {
		return addSlot(inventory, SlotType.NORMAL, x, y);
	}

	public GuiBlueprint addSlot(ItemStackHandler inventory, SlotType type, int x, int y, ContrivitiveSlot.SlotFilter filter) {
		addSlot(new SlotElement(inventory, x + type.getSlotOffsetX(), y + type.getSlotOffsetY(), type, filter));
		return this;
	}

	public GuiBlueprint addSlot(ItemStackHandler inventory, int x, int y, ContrivitiveSlot.SlotFilter filter) {
		return addSlot(inventory, SlotType.NORMAL, x, y, filter);
	}

	public GuiBlueprint syncIntegerValue(final IntSupplier supplier, final IntConsumer setter) {
		this.intSyncables.add(new Pair<>(supplier, setter));
		return this;
	}

	public GuiBlueprint syncShortValue(final IntSupplier supplier, final IntConsumer setter) {
		this.shortSyncables.add(new Pair<>(supplier, setter));
		return this;
	}

	public GuiBlueprint addPlayerInventory(String page, int x, int y) {
		this.playerInvX = x;
		this.playerInvY = y;
		if (x > -1 && y > -1) {
			for (int row = 0; row < 3; ++row) {
				for (int column = 0; column < 9; ++column) {
					int index = column + row * 9 + 9;
					int xpos = x + (column * 18);
					int ypos = y + (row * 18);
					addPlayerSlot(page, xpos, ypos, index);
				}
			}
			for (int column = 0; column < 9; ++column) {
				int xpos = x + (column * 18);
				int ypos = y + 58;
				addPlayerSlot(page, xpos, ypos, column);
			}
		}
		return this;
	}

	public GuiBlueprint addPlayerInventory(int x, int y) {
		return addPlayerInventory("main", x, y);
	}
}