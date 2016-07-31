package tehnut.buttons.gui;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import tehnut.buttons.config.ConfigHandler;

@SideOnly(Side.CLIENT)
public class GuiButtonSave extends GuiButton {

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
                toDraw = I18n.format("save.butt.load", buttonNumber + 1);

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
            IItemHandler itemHandler = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            NBTTagCompound invTag = new NBTTagCompound();
            invTag.setTag("inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(itemHandler, null));
            setSavedInventory(invTag);
//            ConfigHandler.setSaveSlot(getButtonNumber(), invTag);
            deleteButton.visible = true;
        } else {
            ItemStackHandler itemHandler = new ItemStackHandler(0);

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
