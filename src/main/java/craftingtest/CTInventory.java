package craftingtest;

import contrivitive.gui.GuiContainerBlueprint;

public class CTInventory {
	public static GuiContainerBlueprint getCTInventoryBlueprint() {
		GuiContainerBlueprint blueprint = new GuiContainerBlueprint(176, 176);
		blueprint.addPlayerInventory((blueprint.width - (18 * 9)) / 2, blueprint.height - (18 * 4 + 4) - 7);
		blueprint.addPlayerSlot(-22, blueprint.height - 18 - 7, 40); // Offhand slot
		blueprint.at(blueprint.width / 2, 70, new PlayerEntityElement(25));
		return blueprint;
	}
}
