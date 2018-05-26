package craftingtest.recipes;

import com.google.common.collect.Lists;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.ArrayList;

public class CraftingHandler {
	public static ArrayList<IRecipe> recipes = Lists.newArrayList();

	public static void removeRecipes() {
		ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES;
		ArrayList<IRecipe> recipes = Lists.newArrayList(recipeRegistry.getValues());
		CraftingHandler.recipes = recipes;
		for (IRecipe r : recipes) {
			recipeRegistry.remove(r.getRegistryName());
			recipeRegistry.register(DummyRecipe.from(r));
		}
	}
}
