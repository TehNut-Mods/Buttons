package tehnut.jew.plugins.jew;

import net.minecraft.util.ResourceLocation;
import tehnut.jew.JustEnoughWidgets;
import tehnut.jew.api.button.IMode;
import tehnut.jew.api.button.ButtonMode;

public class ButtonModeBase<T extends Enum<T> & IMode> extends ButtonMode<T> {

	private final String name;

	public ButtonModeBase(Class<T> enumClass, String name, boolean serverRequired) {
		super(WidgetTextures.BLANK, enumClass);
		this.name = name;

		if (serverRequired)
			setServerRequired();
	}

	public ButtonModeBase(Class<T> enumClass, String name) {
		this(enumClass, name, false);
	}

	@Override
	public boolean requireElevatedPermissions() {
		return isServerRequired();
	}

	@Override
	public ResourceLocation getButtonId() {
		return new ResourceLocation(JustEnoughWidgets.MODID, name);
	}
}
