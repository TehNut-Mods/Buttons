package tehnut.buttons.plugins.buttons.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import tehnut.buttons.Buttons;
import tehnut.buttons.network.MessageDeleteItem;
import tehnut.buttons.plugins.buttons.ButtonBase;
import tehnut.buttons.plugins.buttons.WidgetTextures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class ButtonDelete extends ButtonBase {

    public ButtonDelete() {
        super(WidgetTextures.TRASH, "button_delete");

        setServerRequired();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumActionResult onClientClick(int mouseX, int mouseY) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        ItemStack mouseStack = player.inventory.getItemStack();

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && mouseStack == null)
            return EnumActionResult.SUCCESS;

        if (mouseStack != null) {
            player.inventory.setItemStack(null);
            Buttons.NETWORK_INSTANCE.sendToServer(new MessageDeleteItem(mouseStack, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)));
        }
        return EnumActionResult.PASS;
    }

    @Override
    public void onServerClick(EntityPlayerMP player) {
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
            player.inventory.mainInventory[i] = null;
        for (int i = 0; i < player.inventory.offHandInventory.length; i++)
            player.inventory.offHandInventory[i] = null;
        for (int i = 0; i < player.inventory.armorInventory.length; i++)
            player.inventory.armorInventory[i] = null;
    }

    @Nonnull
    @Override
    public ITextComponent getUseNotification(EntityPlayerMP player) {
        return new TextComponentString(String.format("%s used the %s button to delete their inventory.", player.getDisplayNameString(), getButtonId()));
    }

    @Nullable
    @Override
    public List<? extends ITextComponent> getTooltip() {
        return Collections.singletonList(new TextComponentTranslation("button.butt.delete.title"));
    }
}
