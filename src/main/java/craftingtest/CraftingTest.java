package craftingtest;

import craftingtest.network.CraftingTestPacketManager;
import craftingtest.network.packet.PacketInventoryOpen;
import craftingtest.recipes.CraftingHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "craftingtest", name = "Crafting Test", version = "1.0.0")
@Mod.EventBusSubscriber(modid = "craftingtest")
public class CraftingTest {

	@Mod.Instance
	public static CraftingTest instance;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onInventoryOpen(GuiOpenEvent event) {
		if (event.getGui() instanceof GuiInventory && !Minecraft.getMinecraft().player.isSneaking() /*&& !Minecraft.getMinecraft().player.isCreative()*/) {
			event.setCanceled(true);
			PacketInventoryOpen packet = new PacketInventoryOpen();
			CraftingTestPacketManager.networkWrapper.sendToServer(packet);
		}
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CraftingTestPacketManager.registerMessages("craftingtest");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new CraftingTestGuiHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		CraftingHandler.removeRecipes();
	}

}
