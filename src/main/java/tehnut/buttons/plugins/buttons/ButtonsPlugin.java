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
        widgetRegistry.addUtilityButton(new ButtonClose());
        widgetRegistry.addUtilityButton(new ButtonConfig());
        widgetRegistry.addUtilityButton(new ButtonGamemode());
        widgetRegistry.addUtilityButton(new ButtonModeBase<TimeModes>(TimeModes.class, "button_time", true));
        widgetRegistry.addUtilityButton(new ButtonModeBase<HealModes>(HealModes.class, "button_heal", true));
        widgetRegistry.addUtilityButton(new ButtonModeBase<WeatherModes>(WeatherModes.class, "button_weather", true));
        widgetRegistry.addUtilityButton(new ButtonKillall());
        widgetRegistry.addUtilityButton(new ButtonDelete());
    }
}
