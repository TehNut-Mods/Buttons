package tehnut.buttons.plugins.buttons.button;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.gui.config.GuiConfigButtons;
import tehnut.buttons.plugins.buttons.ButtonBase;
import tehnut.buttons.plugins.buttons.WidgetTextures;

public class ButtonConfig extends ButtonBase {

    public ButtonConfig() {
        super(WidgetTextures.GLASSES, "button_config");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumActionResult onClientClick(int mouseX, int mouseY) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiConfigButtons(Minecraft.getMinecraft().currentScreen));
        return EnumActionResult.SUCCESS;
    }
}
