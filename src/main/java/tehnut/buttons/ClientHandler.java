package tehnut.buttons;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.gui.SaveButtonListOverlay;
import tehnut.buttons.gui.UtilityButtonListOverlay;

@SideOnly(Side.CLIENT)
public class ClientHandler {

    private final UtilityButtonListOverlay utilityButtonListOverlay = new UtilityButtonListOverlay();
    private final SaveButtonListOverlay saveButtonListOverlay= new SaveButtonListOverlay();

    @SubscribeEvent
    public void onGuiInitPost(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof GuiContainer) {
            utilityButtonListOverlay.init(event.getGui());
            saveButtonListOverlay.init(event.getGui());
        }
    }

    @SubscribeEvent
    public void onGuiDrawPost(GuiScreenEvent.DrawScreenEvent.Post event) {
        utilityButtonListOverlay.drawScreenPost(event.getGui(), event.getGui().buttonList, event.getMouseX(), event.getMouseY());
    }
}
