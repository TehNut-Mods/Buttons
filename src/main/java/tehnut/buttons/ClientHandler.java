package tehnut.buttons;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tehnut.buttons.gui.ButtonListOverlay;

public class ClientHandler {

	private final ButtonListOverlay buttonListOverlay = new ButtonListOverlay();

	@SubscribeEvent
	public void onGuiInitPost(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.getGui() instanceof GuiContainer)
			buttonListOverlay.init(event.getGui());
	}

	@SubscribeEvent
	public void onGuiDrawPost(GuiScreenEvent.DrawScreenEvent.Post event) {
		buttonListOverlay.drawScreenPost(event.getGui(), event.getGui().buttonList, event.getMouseX(), event.getMouseY());
	}

	@SubscribeEvent
	public void onPotionShiftEvent(GuiScreenEvent.PotionShiftEvent event) {
		event.setCanceled(true);
	}
}
