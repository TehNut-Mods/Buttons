package tehnut.jew.plugins.jew.button;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.plugins.jew.ButtonBase;
import tehnut.jew.plugins.jew.ButtonTextures;

import javax.annotation.Nullable;

public class ButtonClose extends ButtonBase {

	public ButtonClose() {
		super(ButtonTextures.BUTTON_EXIT, "button_exit");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onClientClick(int mouseX, int mouseY) {
		Minecraft.getMinecraft().displayGuiScreen(null);
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return new TextComponentTranslation("button.jew.exit.title");
	}
}
