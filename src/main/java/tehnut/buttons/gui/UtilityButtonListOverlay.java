package tehnut.buttons.gui;

import com.google.common.base.Stopwatch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.Buttons;
import tehnut.buttons.api.button.utility.Button;
import tehnut.buttons.api.button.utility.IButtonListOverlay;
import tehnut.buttons.config.ConfigHandler;
import tehnut.buttons.gui.button.GuiButtonUtility;
import tehnut.buttons.impl.WidgetRegistry;
import tehnut.buttons.util.Utils;

import java.util.List;

@SideOnly(Side.CLIENT)
public class UtilityButtonListOverlay implements IButtonListOverlay {

    private static final int BUTTON_WIDTH = 22;

    private int columns;
    private boolean open;

    public void init(GuiScreen screen) {
        if (screen instanceof GuiContainer) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            GuiContainer container = (GuiContainer) screen;

            if (container.guiLeft < 24) {
                close();
                return;
            }

            this.open = true;
            this.columns = (int) Math.floor((double) container.guiLeft / 24);

            drawScreen(screen, screen.buttonList);
            Buttons.debug("Initialized button list in {}.", stopwatch.stop());
        } else {
            close();
        }
    }

    public void drawScreen(GuiScreen screen, List<GuiButton> buttons) {
        int xOffset = 0;
        int yOffset = 0;
        int columnIndex = 0;

        for (Button button : WidgetRegistry.INSTANCE.getUtilityButtons().values()) {
            if (ConfigHandler.shouldHideUnusableButtons() && button.requireElevatedPermissions() && !Utils.hasPermission(Minecraft.getMinecraft().thePlayer))
                continue;

            GuiButtonUtility guiButton = new GuiButtonUtility(2 + xOffset, 2 + yOffset, button);
            buttons.add(guiButton);
            guiButton.setId(buttons.indexOf(guiButton) + 5);
            xOffset += BUTTON_WIDTH;
            columnIndex++;

            if (columnIndex >= getColumns()) {
                columnIndex = 0;
                yOffset += 22;
                xOffset = 0;
            }
        }
    }

    public void drawScreenPost(GuiScreen screen, List<GuiButton> buttons, int mouseX, int mouseY) {
        for (GuiButton button : buttons) {
            if (button instanceof GuiButtonUtility && button.isMouseOver()) {
                int modMouseY = mouseY;
                if (modMouseY < 16)
                    modMouseY += 16 - modMouseY;
                ((GuiButtonUtility) button).drawButtonTooltips(mouseX, modMouseY);
            }
        }
    }

    public void close() {
        this.open = false;
        this.columns = 0;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public boolean isOpen() {
        return open;
    }
}
