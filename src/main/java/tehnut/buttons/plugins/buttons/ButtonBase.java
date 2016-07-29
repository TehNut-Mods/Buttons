package tehnut.buttons.plugins.buttons;

import net.minecraft.util.ResourceLocation;
import tehnut.buttons.api.button.utility.Button;
import tehnut.buttons.api.WidgetTexture;

public class ButtonBase extends Button {

    private final String id;

    public ButtonBase(WidgetTexture widgetTexture, String id) {
        super(widgetTexture);

        this.id = id;
    }

    @Override
    public ResourceLocation getButtonId() {
        return new ResourceLocation("buttons", id);
    }
}
