package contrivitive.registry;

import contrivitive.Contrivitive;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;

public class ContrivitiveBlocks {

	public static Block register(Block block) {
		return register(block, true);
	}

	public static Block register(Block block, boolean itemBlock) {
		block.setCreativeTab(Contrivitive.TAB);
		ContrivitiveRegistry.BLOCKS.add(block);
		if (itemBlock) {
			ItemBlock item = new ItemBlock(block);
			item.setRegistryName(block.getRegistryName());
			item.setUnlocalizedName(block.getUnlocalizedName());
			item.setCreativeTab(Contrivitive.TAB);
			ContrivitiveItems.getItems().add(item);
		}
		return block;
	}

	public static Block register(Block block, Class<? extends TileEntity> blockEntity, String entityName) {
		return register(block, true, blockEntity, entityName);
	}

	public static Block register(Block block, boolean itemBlock, Class<? extends TileEntity> blockEntity, String entityName) {
		block.setCreativeTab(Contrivitive.TAB);
		ContrivitiveRegistry.BLOCKS.add(block);
		if (itemBlock) {
			ItemBlock item = new ItemBlock(block);
			item.setRegistryName(block.getRegistryName());
			item.setUnlocalizedName(block.getUnlocalizedName());
			item.setCreativeTab(Contrivitive.TAB);
			ContrivitiveItems.getItems().add(item);
		}
		ContrivitiveRegistry.BLOCK_ENTITIES.put(entityName, blockEntity);
		return block;
	}

	public static ArrayList<Block> getBlocks() {
		return ContrivitiveRegistry.BLOCKS;
	}
}
