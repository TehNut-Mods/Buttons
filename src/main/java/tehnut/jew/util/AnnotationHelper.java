package tehnut.jew.util;

import net.minecraftforge.fml.common.discovery.ASMDataTable;
import tehnut.jew.JustEnoughWidgets;
import tehnut.jew.api.IWidgetPlugin;
import tehnut.jew.api.WidgetPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnnotationHelper {

	public static List<IWidgetPlugin> getPlugins(ASMDataTable dataTable) {
		List<IWidgetPlugin> widgetPlugins = new ArrayList<IWidgetPlugin>();
		Set<ASMDataTable.ASMData> discoveredPlugins = dataTable.getAll(WidgetPlugin.class.getCanonicalName());

		for (ASMDataTable.ASMData data : discoveredPlugins) {
			try {
				Class<?> asmClass = Class.forName(data.getClassName());
				Class<? extends IWidgetPlugin> pluginClass = asmClass.asSubclass(IWidgetPlugin.class);

				widgetPlugins.add(pluginClass.newInstance());
			} catch (Exception e) {
				JustEnoughWidgets.LOGGER.error("Error while discovering plugin for class {}: {}", data.getClassName(), e.getLocalizedMessage());
			}
		}

		return widgetPlugins;
	}
}
