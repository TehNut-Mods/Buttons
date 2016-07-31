package tehnut.buttons.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.Buttons;
import tehnut.buttons.api.ClientHelper;
import tehnut.buttons.api.ButtonsAPI;
import tehnut.buttons.api.button.utility.Button;
import tehnut.buttons.api.WidgetTexture;
import tehnut.buttons.network.MessageButtonClicked;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiButtonUtility extends GuiButton {

    private final Button button;

    public GuiButtonUtility(int x, int y, Button button) {
        super(0, x, y, 20, 20, "");

        this.button = button;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (visible) {
            this.hovered = ClientHelper.isMouseBetween(mouseX, mouseY, xPosition, yPosition, 19, 19);

            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            WidgetTexture background = ButtonsAPI.BUTTON_BACKGROUND_DEFAULT;
            if (hovered)
                background = ButtonsAPI.BUTTON_BACKGROUND_ACTIVE;
            mc.renderEngine.bindTexture(background.getTextureLocation());
            ClientHelper.drawTexture(0, xPosition, yPosition, background);
            button.drawButton(xPosition, yPosition);
        }
    }

    public void drawButtonTooltips(int mouseX, int mouseY) {
        if (visible && getButton().getTooltip() != null) {
            ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            List<String> tooltips = new ArrayList<String>();
            for (ITextComponent textComponent : getButton().getTooltip())
                tooltips.add(textComponent.getFormattedText());
            GuiUtils.drawHoveringText(tooltips, mouseX, mouseY, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), -1, Minecraft.getMinecraft().fontRendererObj);
        }
    }

    @Override
    public void playPressSound(SoundHandler soundHandlerIn) {
        // No-op
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        super.mouseReleased(mouseX, mouseY);

        if (!isMouseOver())
            return;

        EnumActionResult result = button.onClientClick(mouseX, mouseY);
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));

        if (result == EnumActionResult.SUCCESS && button.isServerRequired())
            Buttons.NETWORK_INSTANCE.sendToServer(new MessageButtonClicked(button));
    }

    public void setId(int id) {
        this.id = id;
    }

    public Button getButton() {
        return button;
    }
}
