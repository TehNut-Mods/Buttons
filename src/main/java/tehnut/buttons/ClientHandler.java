package tehnut.buttons;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.config.ConfigHandler;
import tehnut.buttons.gui.SaveButtonListOverlay;
import tehnut.buttons.gui.UtilityButtonListOverlay;

@SideOnly(Side.CLIENT)
public class ClientHandler {

    private final UtilityButtonListOverlay utilityButtonListOverlay = new UtilityButtonListOverlay();
    private final SaveButtonListOverlay saveButtonListOverlay= new SaveButtonListOverlay();

    @SubscribeEvent
    public void onGuiInitPost(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof GuiContainer) {
            if (ConfigHandler.enableUtilityButtons())
                utilityButtonListOverlay.init(event.getGui());
            if (ConfigHandler.enableSaveButtons())
                saveButtonListOverlay.init(event.getGui());
        }
    }

    @SubscribeEvent
    public void onGuiDrawPost(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (ConfigHandler.enableUtilityButtons())
            utilityButtonListOverlay.drawScreenPost(event.getGui(), event.getGui().buttonList, event.getMouseX(), event.getMouseY());
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent event) {
        if (event.getModID().equals(Buttons.MODID))
            ConfigHandler.syncConfig();
    }
}
