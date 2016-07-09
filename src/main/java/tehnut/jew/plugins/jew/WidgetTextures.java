package tehnut.jew.plugins.jew;

import net.minecraft.util.ResourceLocation;
import tehnut.jew.api.WidgetTexture;

public class WidgetTextures {

	public static final WidgetTexture BUTTON_EXIT = new WidgetTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			0,
			20,
			20,
			20
			);

	public static final WidgetTexture BUTTON_DAY = new WidgetTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			20,
			20,
			20,
			20
	);

	public static final WidgetTexture BUTTON_NIGHT = new WidgetTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			40,
			20,
			20,
			20
	);

	public static final WidgetTexture BLANK = new WidgetTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			0,
			0,
			0,
			0
	);
}
