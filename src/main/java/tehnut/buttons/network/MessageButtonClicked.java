package tehnut.buttons.network;

import com.google.common.base.Stopwatch;
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
import tehnut.buttons.Buttons;
import tehnut.buttons.api.button.utility.Button;
import tehnut.buttons.api.button.utility.ButtonMode;
import tehnut.buttons.impl.WidgetRegistry;

public class MessageButtonClicked implements IMessage {

	private Button button;
	private Integer mode;

	public MessageButtonClicked() {

	}

	public MessageButtonClicked(Button button) {
		this.button = button;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.button = WidgetRegistry.INSTANCE.getButtons().get(new ResourceLocation(ByteBufUtils.readUTF8String(buf)));
		this.mode = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, button.getButtonId().toString());
		buf.writeInt(button instanceof ButtonMode ? ((ButtonMode) button).getMode().ordinal() : 0);
	}

	public Button getButton() {
		return button;
	}

	public Integer getMode() {
		return mode;
	}

	public static class Handler implements IMessageHandler<MessageButtonClicked, IMessage> {

		@Override
		public IMessage onMessage(final MessageButtonClicked message, final MessageContext ctx) {
			FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					Stopwatch stopwatch = Stopwatch.createStarted();
					boolean canUse = true;
					if (message.getButton() instanceof ButtonMode)
						//noinspection unchecked
						((ButtonMode) message.getButton()).setMode(((ButtonMode) message.getButton()).getModes()[message.getMode()]);

					if (message.getButton().requireElevatedPermissions())
						canUse = ctx.getServerHandler().playerEntity.canCommandSenderUseCommand(2, "");

					if (canUse) {
						message.getButton().onServerClick(ctx.getServerHandler().playerEntity);
						Buttons.LOGGER.info(message.getButton().getUseNotification(ctx.getServerHandler().playerEntity).getFormattedText());
					} else {
						ctx.getServerHandler().playerEntity.addChatComponentMessage(new TextComponentTranslation("chat.jew.permission.fail").setStyle(new Style().setColor(TextFormatting.RED)));
					}
					Buttons.debug("Handled server functionality of {} in {}.", message.getButton().getButtonId(), stopwatch.stop());
				}
			});

			return null;
		}
	}
}
