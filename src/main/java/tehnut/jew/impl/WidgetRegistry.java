package tehnut.jew.impl;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import tehnut.jew.api.IWidgetRegistry;
import tehnut.jew.api.button.Button;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WidgetRegistry implements IWidgetRegistry {

	public static final WidgetRegistry INSTANCE = new WidgetRegistry();

	private WidgetRegistry() {
		// No-op
	}

	private final Map<ResourceLocation, Button> buttons = new LinkedHashMap<ResourceLocation, Button>();

	@Override
	public void addButton(Button button) {
		buttons.put(button.getButtonId(), button);
		System.out.println(button.getButtonId());
	}

	public Map<ResourceLocation, Button> getButtons() {
		return ImmutableMap.copyOf(buttons);
	}
}
