package tehnut.buttons.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.config.SaveCacheHandler;

@SideOnly(Side.CLIENT)
public class GuiButtonDeleteSave extends GuiButtonExt {

    private GuiButtonSave saveButton;

    public GuiButtonDeleteSave(int xPos, int yPos, GuiButtonSave saveButton) {
        super(0, xPos, yPos, "X");

        this.saveButton = saveButton;
        setWidth(20);
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

        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        saveButton.setSavedInventory(null);
        SaveCacheHandler.setSaveSlot(saveButton.getButtonNumber(), null);
        visible = false;
    }

    public GuiButton setId(int id) {
        this.id = id;
        return this;
    }
}
