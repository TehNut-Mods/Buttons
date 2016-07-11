package tehnut.jew.plugins.jew.button;

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
import tehnut.jew.JustEnoughWidgets;
import tehnut.jew.network.MessageDeleteItem;
import tehnut.jew.plugins.jew.ButtonBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ButtonDelete extends ButtonBase {

	public ButtonDelete() {
		super(WidgetTextures.GLASSES, "button_delete");

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
			JustEnoughWidgets.NETWORK_INSTANCE.sendToServer(new MessageDeleteItem(mouseStack, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)));
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
	public ITextComponent getTitle() {
		return new TextComponentTranslation("button.jew.delete.title");
	}
}
