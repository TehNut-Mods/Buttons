package tehnut.jew.api;

import net.minecraft.util.ResourceLocation;
import tehnut.jew.api.button.ButtonTexture;
import tehnut.jew.api.button.IButtonListOverlay;

import javax.annotation.Nullable;

public class JustEnoughWidgetsAPI {

	/**
	 * The currently constructed {@link IButtonListOverlay}.
	 *
	 * Will be null if no container is open.
	 */
	@Nullable
	public static IButtonListOverlay buttonListOverlay;

	public static final ButtonTexture BUTTON_BACKGROUND_DEFAULT = new ButtonTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			0,
			0,
			20,
			20
	);

	public static final ButtonTexture BUTTON_BACKGROUND_ACTIVE = new ButtonTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			20,
			0,
			20,
			20
	);

	public static final ButtonTexture BUTTON_BACKGROUND_INACTIVE = new ButtonTexture(
			new ResourceLocation("justenoughwidgets", "textures/gui_widgets.png"),
			40,
			0,
			20,
			20
	);
}
