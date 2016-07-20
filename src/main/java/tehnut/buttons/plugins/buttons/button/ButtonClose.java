package tehnut.buttons.plugins.buttons.button;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.plugins.buttons.ButtonBase;
import tehnut.buttons.plugins.buttons.WidgetTextures;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

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
	public List<? extends ITextComponent> getTooltip() {
		return Collections.singletonList(new TextComponentTranslation("button.jew.exit.title"));
	}
}
