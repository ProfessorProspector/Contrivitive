package craftingtest.network.packet;

import craftingtest.CraftingTest;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketInventoryOpen implements IMessage {
	public PacketInventoryOpen() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler implements IMessageHandler<PacketInventoryOpen, IMessage> {
		@Override
		public IMessage onMessage(PacketInventoryOpen message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}

		private void handle(PacketInventoryOpen message, MessageContext context) {
			EntityPlayer player = context.getServerHandler().player;
			player.openGui(CraftingTest.instance, 0, Minecraft.getMinecraft().world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());

		}
	}
}
