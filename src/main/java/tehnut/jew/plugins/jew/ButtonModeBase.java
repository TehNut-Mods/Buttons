package tehnut.jew.plugins.jew;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.ResourceLocation;
import tehnut.jew.api.button.ButtonMode;

import java.util.List;

public class ButtonModeBase extends ButtonMode {

	private final String id;
	private List<Mode> modes;

	public ButtonModeBase(String id) {
		super(ButtonTextures.BLANK);

		this.id = id;
	}

	@Override
	public boolean requireElevatedPermissions() {
		return isServerRequired();
	}

	@Override
	public List<Mode> getModes() {
		return ImmutableList.copyOf(modes);
	}

	public ButtonModeBase setModes(List<Mode> modes) {
		this.modes = modes;
		return this;
	}

	@Override
	public ResourceLocation getButtonId() {
		return new ResourceLocation("justenoughwidgets", id);
	}
}
