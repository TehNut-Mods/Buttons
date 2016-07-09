package tehnut.jew.plugins.jew.button;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.plugins.jew.ButtonBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nullable;

public class ButtonClose extends ButtonBase {

	public ButtonClose() {
		super(WidgetTextures.BUTTON_EXIT, "button_exit");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumActionResult onClientClick(int mouseX, int mouseY) {
		Minecraft.getMinecraft().displayGuiScreen(null);
		return EnumActionResult.SUCCESS;
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return new TextComponentTranslation("button.jew.exit.title");
	}
}
