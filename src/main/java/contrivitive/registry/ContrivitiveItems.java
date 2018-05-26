package contrivitive.registry;

import contrivitive.Contrivitive;
import contrivitive.ItemExample;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class ContrivitiveItems {
	protected static final ArrayList<Item> ITEMS = new ArrayList<>();

	public static final Item EXAMPLE = register(new ItemExample());

	public static Item register(Item item) {
		item.setCreativeTab(Contrivitive.TAB);
		ITEMS.add(item);
		return item;
	}

	public static ArrayList<Item> getItems() {
		return ITEMS;
	}
}