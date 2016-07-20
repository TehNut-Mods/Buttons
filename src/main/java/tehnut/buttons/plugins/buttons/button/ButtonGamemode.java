package tehnut.buttons.plugins.buttons.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import tehnut.buttons.plugins.buttons.ButtonModeBase;
import tehnut.buttons.plugins.buttons.button.mode.GameModes;

import java.awt.*;

public class ButtonGamemode extends ButtonModeBase<GameModes> {

	public ButtonGamemode() {
		super(GameModes.class, "button_gamemode", true);

		setServerRequired();
	}

	@Override
	public void drawButton(int x, int y) {
		switch (getMode()) {
			case SURVIVAL: drawScaledText("S", x, y); break;
			case CREATIVE: drawScaledText("C", x, y); break;
			case ADVENTURE: drawScaledText("A", x, y); break;
			case SPECTATOR: super.drawButton(x, y); break;
		}

	}

	private void drawScaledText(String text, int x, int y) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(1.5, 1.5, 1.5);
		GlStateManager.translate(-4, 2, 0);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, x, y, Color.WHITE.getRGB());
		GlStateManager.popMatrix();
	}
}
