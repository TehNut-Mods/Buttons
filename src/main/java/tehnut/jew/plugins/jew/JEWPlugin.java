package tehnut.jew.plugins.jew;

import tehnut.jew.api.IWidgetPlugin;
import tehnut.jew.api.IWidgetRegistry;
import tehnut.jew.api.WidgetPlugin;
import tehnut.jew.plugins.jew.button.*;
import tehnut.jew.plugins.jew.button.mode.GameModes;
import tehnut.jew.plugins.jew.button.mode.HealModes;
import tehnut.jew.plugins.jew.button.mode.TimeModes;
import tehnut.jew.plugins.jew.button.mode.WeatherModes;

@WidgetPlugin
public class JEWPlugin extends IWidgetPlugin.Base {

	@Override
	public void register(IWidgetRegistry widgetRegistry) {
		widgetRegistry.addButton(new ButtonClose());
		widgetRegistry.addButton(new ButtonModeBase<GameModes>(GameModes.class, "button_gamemode", true));
		widgetRegistry.addButton(new ButtonModeBase<TimeModes>(TimeModes.class, "button_time", true));
		widgetRegistry.addButton(new ButtonModeBase<HealModes>(HealModes.class, "button_heal", true));
		widgetRegistry.addButton(new ButtonModeBase<WeatherModes>(WeatherModes.class, "button_weather", true));
		widgetRegistry.addButton(new ButtonKillall());
	}
}
