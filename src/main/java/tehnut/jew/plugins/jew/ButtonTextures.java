package tehnut.jew.plugins.jew;

import net.minecraft.util.ResourceLocation;
import tehnut.jew.api.button.ButtonTexture;

public class ButtonTextures {

	public static final ButtonTexture BUTTON_EXIT = new ButtonTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			0,
			20,
			20,
			20
			);

	public static final ButtonTexture BUTTON_DAY = new ButtonTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			20,
			20,
			20,
			20
	);

	public static final ButtonTexture BUTTON_NIGHT = new ButtonTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			40,
			20,
			20,
			20
	);

	public static final ButtonTexture BLANK = new ButtonTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			0,
			0,
			0,
			0
	);
}
