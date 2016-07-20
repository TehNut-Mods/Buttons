package tehnut.buttons.util;

import net.minecraftforge.fml.common.discovery.ASMDataTable;
import tehnut.buttons.Buttons;
import tehnut.buttons.api.IWidgetPlugin;
import tehnut.buttons.api.ButtonsPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnnotationHelper {

	public static List<IWidgetPlugin> getPlugins(ASMDataTable dataTable) {
		List<IWidgetPlugin> widgetPlugins = new ArrayList<IWidgetPlugin>();
		Set<ASMDataTable.ASMData> discoveredPlugins = dataTable.getAll(ButtonsPlugin.class.getCanonicalName());

		for (ASMDataTable.ASMData data : discoveredPlugins) {
			try {
				Class<?> asmClass = Class.forName(data.getClassName());
				Class<? extends IWidgetPlugin> pluginClass = asmClass.asSubclass(IWidgetPlugin.class);

				widgetPlugins.add(pluginClass.newInstance());
			} catch (Exception e) {
				Buttons.LOGGER.error("Error while discovering plugin for class {}: {}", data.getClassName(), e.getLocalizedMessage());
			}
		}

		return widgetPlugins;
	}
}
