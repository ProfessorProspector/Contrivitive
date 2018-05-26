package contrivitive.gui.element.slot;

import contrivitive.gui.GuiBlueprint;
import contrivitive.gui.element.Element;
import contrivitive.util.ContrivitiveSlot;
import net.minecraftforge.items.ItemStackHandler;

public class SlotElement<B extends GuiBlueprint> extends Element {
	public ContrivitiveSlot.SlotFilter filter;
	protected ItemStackHandler slotInventory;
	protected SlotType type;
	int slotId = -1, slotX, slotY;

	public SlotElement(ItemStackHandler slotInventory, int slotX, int slotY, SlotType type, ContrivitiveSlot.SlotFilter filter) {
		super(type.getSprite().width, type.getSprite().height);
		sprite(type.getSprite());
		this.type = type;
		this.slotInventory = slotInventory;
		this.slotX = slotX;
		this.slotY = slotY;
		this.filter = filter;
	}

	public SlotType getType() {
		return type;
	}

	public ItemStackHandler getSlotInventory() {
		return slotInventory;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public int getSlotX() {
		return slotX;
	}

	public int getSlotY() {
		return slotY;
	}
}
