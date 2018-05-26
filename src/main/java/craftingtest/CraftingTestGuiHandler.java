package craftingtest;

import contrivitive.gui.ContrivitiveContainer;
import contrivitive.gui.ContrivitiveGuiContainer;
import contrivitive.gui.GuiContainerBlueprint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class CraftingTestGuiHandler implements IGuiHandler {

	@Nullable
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		GuiContainerBlueprint blueprint = CTInventory.getCTInventoryBlueprint();
		if (id == 0) {
			return new ContrivitiveContainer(blueprint, player);
		}
		return null;
	}

	@Nullable
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		GuiContainerBlueprint blueprint = CTInventory.getCTInventoryBlueprint();
		if (id == 0) {
			return new ContrivitiveGuiContainer(new ContrivitiveContainer(blueprint, player));
		}
		return null;
	}
}
