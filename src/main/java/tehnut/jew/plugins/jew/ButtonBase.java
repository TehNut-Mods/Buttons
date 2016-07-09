package tehnut.jew.plugins.jew;

import net.minecraft.util.ResourceLocation;
import tehnut.jew.api.button.Button;
import tehnut.jew.api.button.ButtonTexture;

public class ButtonBase extends Button {

	private final String id;

	public ButtonBase(ButtonTexture buttonTexture, String id) {
		super(buttonTexture);

		this.id = id;
	}

	@Override
	public boolean requireElevatedPermissions() {
		return isServerRequired();
	}

	@Override
	public ResourceLocation getButtonId() {
		return new ResourceLocation("justenoughwidgets", id);
	}
}
