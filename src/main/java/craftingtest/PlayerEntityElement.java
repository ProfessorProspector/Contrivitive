package craftingtest;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.util.RenderUtils;
import net.minecraft.client.Minecraft;

public class PlayerEntityElement extends Element {
	int scale;
	private float oldMouseX;
	private float oldMouseY;

	public PlayerEntityElement(int scale) {
		this.scale = scale;
	}

	@Override
	public void draw(IContrivitiveGui gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		RenderUtils.drawEntityFacingMouse(gui, x, y, scale, mouseX, mouseY, Minecraft.getMinecraft().player);
	}
}
