package contrivitive.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContrivitiveSlot extends SlotItemHandler {
	public SlotFilter filter = (slot, stack) -> true;

	public ContrivitiveSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	public ContrivitiveSlot setFilter(SlotFilter filter) {
		this.filter = filter;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public ContrivitiveSlot setSprite(ResourceLocation backgroundLocation) {
		this.setBackgroundLocation(backgroundLocation);
		return this;
	}

	@Override
	public boolean isItemValid(
		@Nonnull
			ItemStack stack) {
		return !stack.isEmpty() && filter.isItemValid(this, stack) && super.isItemValid(stack);
	}

	public interface SlotFilter {
		public boolean isItemValid(ContrivitiveSlot slot, ItemStack stack);
	}
}
