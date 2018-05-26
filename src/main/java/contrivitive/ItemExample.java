package contrivitive;

import contrivitive.lib.ContrivitiveConstants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemExample extends Item {
	public ItemExample() {
		setRegistryName(ContrivitiveConstants.MOD_ID, "example");
		setUnlocalizedName(ContrivitiveConstants.MOD_ID + ".example");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.openGui(Contrivitive.instance, 0, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
