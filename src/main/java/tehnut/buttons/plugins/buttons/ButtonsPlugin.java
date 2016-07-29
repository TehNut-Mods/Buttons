package tehnut.buttons.plugins.buttons;

import tehnut.buttons.api.IWidgetPlugin;
import tehnut.buttons.api.IWidgetRegistry;
import tehnut.buttons.plugins.buttons.button.*;
import tehnut.buttons.plugins.buttons.button.mode.HealModes;
import tehnut.buttons.plugins.buttons.button.mode.TimeModes;
import tehnut.buttons.plugins.buttons.button.mode.WeatherModes;

@tehnut.buttons.api.ButtonsPlugin
public class ButtonsPlugin extends IWidgetPlugin.Base {

    @Override
    public void register(IWidgetRegistry widgetRegistry) {
        widgetRegistry.addButton(new ButtonClose());
        widgetRegistry.addButton(new ButtonGamemode());
        widgetRegistry.addButton(new ButtonModeBase<TimeModes>(TimeModes.class, "button_time", true));
        widgetRegistry.addButton(new ButtonModeBase<HealModes>(HealModes.class, "button_heal", true));
        widgetRegistry.addButton(new ButtonModeBase<WeatherModes>(WeatherModes.class, "button_weather", true));
        widgetRegistry.addButton(new ButtonKillall());
        widgetRegistry.addButton(new ButtonDelete());
    }
}
