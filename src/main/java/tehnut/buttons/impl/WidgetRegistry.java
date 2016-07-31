package tehnut.buttons.impl;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import tehnut.buttons.api.IWidgetRegistry;
import tehnut.buttons.api.button.utility.Button;

import java.util.LinkedHashMap;
import java.util.Map;

public class WidgetRegistry implements IWidgetRegistry {

    public static final WidgetRegistry INSTANCE = new WidgetRegistry();

    private WidgetRegistry() {
        // No-op
    }

    private final Map<ResourceLocation, Button> utilityButtons = new LinkedHashMap<ResourceLocation, Button>();

    @Override
    public void addUtilityButton(Button button) {
        utilityButtons.put(button.getButtonId(), button);
    }

    public Map<ResourceLocation, Button> getUtilityButtons() {
        return ImmutableMap.copyOf(utilityButtons);
    }
}
