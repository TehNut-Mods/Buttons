package tehnut.buttons.api;

import net.minecraft.util.ResourceLocation;

public class ButtonsAPI {

	public static final WidgetTexture BUTTON_BACKGROUND_DEFAULT = new WidgetTexture(
			new ResourceLocation("buttons", "textures/gui_widgets.png"),
			0,
			0,
			20,
			20
	);

	public static final WidgetTexture BUTTON_BACKGROUND_ACTIVE = new WidgetTexture(
			new ResourceLocation("buttons", "textures/gui_widgets.png"),
			20,
			0,
			20,
			20
	);

	public static final WidgetTexture BUTTON_BACKGROUND_INACTIVE = new WidgetTexture(
			new ResourceLocation("buttons", "textures/gui_widgets.png"),
			40,
			0,
			20,
			20
	);
}
