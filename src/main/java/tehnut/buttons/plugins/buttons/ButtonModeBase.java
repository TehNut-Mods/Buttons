package tehnut.buttons.plugins.buttons;

import net.minecraft.util.ResourceLocation;
import tehnut.buttons.Buttons;
import tehnut.buttons.api.button.utility.IMode;
import tehnut.buttons.api.button.utility.ButtonMode;

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
        return new ResourceLocation(Buttons.MODID, name);
    }
}
