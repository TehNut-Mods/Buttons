package tehnut.jew.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.fml.client.config.GuiUtils;
import tehnut.jew.JustEnoughWidgets;
import tehnut.jew.api.ClientHelper;
import tehnut.jew.api.JustEnoughWidgetsAPI;
import tehnut.jew.api.button.Button;
import tehnut.jew.api.button.ButtonTexture;
import tehnut.jew.network.MessageButtonClicked;

import java.util.Collections;

public class GuiButtonWidget extends GuiButton {

	private final Button button;

	public GuiButtonWidget(int x, int y, Button button) {
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
			ButtonTexture background = JustEnoughWidgetsAPI.BUTTON_BACKGROUND_DEFAULT;
			if (hovered)
				background = JustEnoughWidgetsAPI.BUTTON_BACKGROUND_ACTIVE;
			mc.renderEngine.bindTexture(background.getTextureLocation());
			ClientHelper.drawTexture(0, xPosition, yPosition, background);
			button.drawButton(xPosition, yPosition);
		}
	}

	public void drawButtonTooltips(int mouseX, int mouseY) {
		if (visible && button.getTitle() != null) {
			ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
			GuiUtils.drawHoveringText(Collections.singletonList(button.getTitle().getFormattedText()), mouseX, mouseY, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), -1, Minecraft.getMinecraft().fontRendererObj);
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		super.mouseReleased(mouseX, mouseY);

		button.onClientClick(mouseX, mouseY);

		if (button.isServerRequired())
			JustEnoughWidgets.NETWORK_INSTANCE.sendToServer(new MessageButtonClicked(button));
	}

	public void setId(int id) {
		this.id = id;
	}
}
