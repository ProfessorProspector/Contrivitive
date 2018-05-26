package contrivitive.registry;

import contrivitive.lib.ContrivitiveConstants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ContrivitiveConstants.MOD_ID)
public class ContrivitiveRegistry {
	protected static final ArrayList<Block> BLOCKS = new ArrayList<>();
	protected static final Map<String, Class<? extends TileEntity>> BLOCK_ENTITIES = new HashMap<>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Item item : ContrivitiveItems.getItems()) {
			event.getRegistry().register(item);
		}
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Block block : BLOCKS) {
			event.getRegistry().register(block);
		}
	}

	public static void registerBlockEntities() {
		for (String entityName : BLOCK_ENTITIES.keySet()) {
			GameRegistry.registerTileEntity(BLOCK_ENTITIES.get(entityName), entityName);
		}
	}

	public static void registerRecipes() {
	}
}
