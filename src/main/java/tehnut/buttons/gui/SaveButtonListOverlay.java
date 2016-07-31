package tehnut.buttons.gui;

import com.google.common.base.Stopwatch;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.Buttons;
import tehnut.buttons.api.button.utility.IButtonListOverlay;
import tehnut.buttons.config.ConfigHandler;

import java.util.List;

@SideOnly(Side.CLIENT)
public class SaveButtonListOverlay implements IButtonListOverlay {

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

            drawScreen(screen, screen.buttonList);
            Buttons.debug("Initialized button list in {}.", stopwatch.stop());
        } else {
            close();
        }
    }

    public void drawScreen(GuiScreen screen, List<GuiButton> buttons) {
        int yOffset = 70;

        for (int i = 0; i < 5; i++) {
            GuiContainer container = (GuiContainer) screen;
            GuiButtonSave saveButton = new GuiButtonSave(2, 2 + yOffset, i, container);
            buttons.add(saveButton);
            saveButton.setId(buttons.indexOf(saveButton) + 5);
//            NBTTagCompound invTag = ConfigHandler.getSaveSlot(i);
//            if (invTag != null)
//                saveButton.setSavedInventory(invTag);
            GuiButtonDeleteSave deleteButton = new GuiButtonDeleteSave(container.guiLeft - 37, 2 + yOffset, saveButton);
            buttons.add(deleteButton);
            deleteButton.setId(buttons.indexOf(deleteButton) + 5);
            saveButton.setDeleteButton(deleteButton);
            if (saveButton.getSavedInventory() == null)
                deleteButton.visible = false;
            yOffset += 22;
        }
    }

    public void close() {
        this.open = false;
    }

    @Override
    public int getColumns() {
        return 1;
    }

    @Override
    public boolean isOpen() {
        return open;
    }
}
