package tehnut.buttons.gui.button;

import com.google.common.base.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tehnut.buttons.Buttons;
import tehnut.buttons.config.ConfigHandler;
import tehnut.buttons.config.SaveCacheHandler;
import tehnut.buttons.network.MessageLoadInventory;

@SideOnly(Side.CLIENT)
public class GuiButtonSave extends GuiButtonExt {

    private NBTTagCompound savedInventory = null;
    private int buttonNumber;
    private GuiButtonDeleteSave deleteButton;

    public GuiButtonSave(int x, int y, int buttonNumber, GuiContainer container) {
        super(0, x, y, "");

        this.buttonNumber = buttonNumber;

        setWidth(container.guiLeft - 40);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + i * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);

            int stringColor = 14737632;

            if (packedFGColour != 0)
                stringColor = packedFGColour;
            else if (!this.enabled)
                stringColor = 10526880;
            else if (this.hovered)
                stringColor = 16777120;

            String toDraw;

            if (savedInventory == null)
                toDraw = I18n.format("save.butt.save", buttonNumber + 1);
            else
                toDraw = SaveCacheHandler.getSlotName(buttonNumber);

            drawCenteredString(fontrenderer, toDraw, xPosition + width / 2, yPosition + (height - 8) / 2, stringColor);
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

        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (getSavedInventory() == null) {
            NBTTagCompound invTag = new NBTTagCompound();
            invTag.setTag("inv", player.inventory.writeToNBT(new NBTTagList()));
            setSavedInventory(invTag);
            SaveCacheHandler.setSaveSlot(getButtonNumber(), invTag);
            SaveCacheHandler.setSlotName(getButtonNumber(), I18n.format("save.butt.load", getButtonNumber() + 1));
            deleteButton.visible = true;
        } else {
            Buttons.NETWORK_INSTANCE.sendToServer(new MessageLoadInventory(getSavedInventory()));
        }
    }

    public GuiButton setId(int id) {
        this.id = id;
        return this;
    }

    public GuiButton setDeleteButton(GuiButtonDeleteSave deleteButton) {
        this.deleteButton = deleteButton;
        return this;
    }

    public NBTTagCompound getSavedInventory() {
        return savedInventory;
    }

    public void setSavedInventory(NBTTagCompound savedInventory) {
        this.savedInventory = savedInventory;
    }

    public int getButtonNumber() {
        return buttonNumber;
    }
}
