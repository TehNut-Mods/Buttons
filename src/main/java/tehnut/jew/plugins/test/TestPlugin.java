package tehnut.jew.plugins.test;

import tehnut.jew.api.IWidgetPlugin;
import tehnut.jew.api.IWidgetRegistry;
import tehnut.jew.api.WidgetPlugin;

@WidgetPlugin
public class TestPlugin extends IWidgetPlugin.Base {

	@Override
	public void register(IWidgetRegistry widgetRegistry) {
//		for (int i = 0; i < 100; i++)
//			widgetRegistry.addButton(new ButtonTest(i));
	}
}
