package tehnut.buttons.plugins.test;

import tehnut.buttons.api.IWidgetPlugin;
import tehnut.buttons.api.IWidgetRegistry;
import tehnut.buttons.api.ButtonsPlugin;

@ButtonsPlugin
public class TestPlugin extends IWidgetPlugin.Base {

    @Override
    public void register(IWidgetRegistry widgetRegistry) {
//		for (int i = 0; i < 100; i++)
//			widgetRegistry.addUtilityButton(new ButtonTest(i));
    }
}
