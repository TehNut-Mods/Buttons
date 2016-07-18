package tehnut.jew.api;

import net.minecraft.util.ResourceLocation;
import tehnut.jew.api.button.IButtonListOverlay;

import javax.annotation.Nullable;

public class JustEnoughWidgetsAPI {

	public static final WidgetTexture BUTTON_BACKGROUND_DEFAULT = new WidgetTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			0,
			0,
			20,
			20
	);

	public static final WidgetTexture BUTTON_BACKGROUND_ACTIVE = new WidgetTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			20,
			0,
			20,
			20
	);

	public static final WidgetTexture BUTTON_BACKGROUND_INACTIVE = new WidgetTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			40,
			0,
			20,
			20
	);
}
