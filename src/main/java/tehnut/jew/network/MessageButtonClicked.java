package tehnut.jew.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tehnut.jew.JustEnoughWidgets;
import tehnut.jew.api.button.Button;
import tehnut.jew.impl.WidgetRegistry;

public class MessageButtonClicked implements IMessage {

	private Button button;

	public MessageButtonClicked() {

	}

	public MessageButtonClicked(Button button) {
		this.button = button;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.button = WidgetRegistry.INSTANCE.getButtons().get(new ResourceLocation(ByteBufUtils.readUTF8String(buf)));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, button.getButtonId().toString());
	}

	public Button getButton() {
		return button;
	}

	public static class Handler implements IMessageHandler<MessageButtonClicked, IMessage> {

		@Override
		public IMessage onMessage(final MessageButtonClicked message, final MessageContext ctx) {
			FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					boolean canUse = true;
					if (message.getButton().requireElevatedPermissions())
						canUse = ctx.getServerHandler().playerEntity.canCommandSenderUseCommand(2, "");

					if (canUse) {
						message.getButton().onServerClick(ctx.getServerHandler().playerEntity);
						JustEnoughWidgets.LOGGER.info(message.getButton().getUseNotification(ctx.getServerHandler().playerEntity).getFormattedText());
					} else {
						ctx.getServerHandler().playerEntity.addChatComponentMessage(new TextComponentTranslation("chat.jew.permission.fail").setStyle(new Style().setColor(TextFormatting.RED)));
					}
				}
			});

			return null;
		}
	}
}
