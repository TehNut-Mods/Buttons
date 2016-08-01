package tehnut.buttons.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;

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
            NBTTagList invItems = message.getInvTag().getTagList("inv", 10);
            PlayerInvWrapper playerInv = new PlayerInvWrapper(ctx.getServerHandler().playerEntity.inventory);
            for (int i = 0; i < invItems.tagCount(); i++) {
                NBTTagCompound tag = invItems.getCompoundTagAt(i);
                int slot = tag.getInteger("Slot");
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("id")));
                int count = tag.getInteger("Count");
                int meta = tag.getInteger("Damage");
                NBTTagCompound itemTag = tag.getCompoundTag("tag");

                ItemStack built = new ItemStack(item, count, meta);
                if (!itemTag.hasNoTags())
                    built.setTagCompound(itemTag);

                playerInv.setStackInSlot(slot, built);
            }

            return null;
        }
    }
}
