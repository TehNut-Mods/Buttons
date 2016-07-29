package tehnut.buttons.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tehnut.buttons.Buttons;

public class MessageDeleteItem implements IMessage {

    private ItemStack stack;
    private boolean findAll;

    public MessageDeleteItem() {

    }

    public MessageDeleteItem(ItemStack stack, boolean findAll) {
        this.stack = stack;
        this.findAll = findAll;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        stack = ByteBufUtils.readItemStack(buf);
        findAll = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, stack);
        buf.writeBoolean(findAll);
    }

    public ItemStack getStack() {
        return stack;
    }

    public boolean isFindAll() {
        return findAll;
    }

    public static class Handler implements IMessageHandler<MessageDeleteItem, IMessage> {

        @Override
        public IMessage onMessage(MessageDeleteItem message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            ItemStack playerItem = player.inventory.getItemStack();
            if (ItemStack.areItemStacksEqual(playerItem, message.getStack()))
                player.inventory.setItemStack(null);

            if (message.isFindAll()) {
                for (int i = 0; i < player.inventory.mainInventory.length; i++)
                    if (areStacksEqual(player.inventory.mainInventory[i], message.getStack()))
                        player.inventory.mainInventory[i] = null;
                for (int i = 0; i < player.inventory.offHandInventory.length; i++)
                    if (areStacksEqual(player.inventory.offHandInventory[i], message.getStack()))
                        player.inventory.offHandInventory[i] = null;
                for (int i = 0; i < player.inventory.armorInventory.length; i++)
                    if (areStacksEqual(player.inventory.armorInventory[i], message.getStack()))
                        player.inventory.armorInventory[i] = null;

                Buttons.LOGGER.info("{} used the {} button to delete all items matching {}", player.getDisplayNameString(), "justenoughwidgets:button_delete", message.getStack().getDisplayName());
            } else {
                Buttons.LOGGER.info("{} used the {} button to delete {}", player.getDisplayNameString(), "justenoughwidgets:button_delete", message.getStack().getDisplayName());
            }

            return null;
        }
    }

    private static boolean areStacksEqual(ItemStack stack1, ItemStack stack2) {
        return ItemStack.areItemsEqual(stack1, stack2) && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }
}
