package tehnut.jew.plugins.jew;

import net.minecraft.util.ResourceLocation;
import tehnut.jew.api.button.Button;
import tehnut.jew.api.WidgetTexture;

public class ButtonBase extends Button {

	private final String id;

	public ButtonBase(WidgetTexture widgetTexture, String id) {
		super(widgetTexture);

		this.id = id;
	}

	@Override
	public ResourceLocation getButtonId() {
		return new ResourceLocation("justenoughwidgets", id);
	}
}
