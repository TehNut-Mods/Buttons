package tehnut.buttons.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLoadInventory implements IMessage {

    private NBTTagCompound invTag;

    public MessageLoadInventory() {
    }

    public MessageLoadInventory(NBTTagCompound invTag) {
        this.invTag = invTag;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        invTag = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, invTag);
    }

    public NBTTagCompound getInvTag() {
        return invTag;
    }

    public static class Handler implements IMessageHandler<MessageLoadInventory, IMessage> {

        @Override
        public IMessage onMessage(MessageLoadInventory message, MessageContext ctx) {
            ctx.getServerHandler().playerEntity.inventory.readFromNBT(message.getInvTag().getTagList("inv", 10));
            return null;
        }
    }
}
