package contrivitive.gui.element.slot;

import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.Sprites;

public class PlayerSlotElement extends Element {
	int x, y, index;

	public PlayerSlotElement(int x, int y, int index) {
		this.x = x;
		this.y = y;
		this.index = index;
		sprite(Sprites.SLOT_NORMAL);
	}

	public int getX() {
		return x + 1;
	}

	public int getY() {
		return y + 1;
	}

	public int getIndex() {
		return index;
	}
}
