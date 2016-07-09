package tehnut.jew.plugins.jew;

import tehnut.jew.api.IWidgetPlugin;
import tehnut.jew.api.IWidgetRegistry;
import tehnut.jew.api.WidgetPlugin;
import tehnut.jew.plugins.jew.button.*;

@WidgetPlugin
public class JEWPlugin extends IWidgetPlugin.Base {

	@Override
	public void register(IWidgetRegistry widgetRegistry) {
		widgetRegistry.addButton(new ButtonClose());
		widgetRegistry.addButton(new ButtonKillall());
		widgetRegistry.addButton(new ButtonHeal());
		widgetRegistry.addButton(new ButtonGamemode());
		widgetRegistry.addButton(new ButtonTime());
		widgetRegistry.addButton(new ButtonWeather());
	}
}
