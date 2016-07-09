package tehnut.jew.proxy;

import com.google.common.base.Stopwatch;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tehnut.jew.JustEnoughWidgets;
import tehnut.jew.api.IWidgetPlugin;
import tehnut.jew.impl.WidgetRegistry;
import tehnut.jew.plugins.jew.JEWPlugin;
import tehnut.jew.util.AnnotationHelper;

import javax.annotation.Nullable;
import java.util.List;

public class CommonProxy {

	private List<IWidgetPlugin> plugins;
	private boolean started = false;

	public void preInit(FMLPreInitializationEvent event) {
		plugins = AnnotationHelper.getPlugins(event.getAsmData());

		IWidgetPlugin defaultPlugin = getDefaultPlugin();
		if (defaultPlugin != null) {
			plugins.remove(defaultPlugin);
			plugins.add(0, defaultPlugin);
		}
	}

	public void init(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {

	}

	public void startup(FMLModIdMappingEvent event) {
		if (!started) {
			for (IWidgetPlugin plugin : plugins) {
				try {
					Stopwatch stopwatch = Stopwatch.createStarted();
					JustEnoughWidgets.LOGGER.info("Registering plugin: {}", plugin.getClass().getCanonicalName());
					plugin.register(WidgetRegistry.INSTANCE);
					JustEnoughWidgets.LOGGER.info("Registered plugin {} in {}", plugin.getClass().getCanonicalName(), stopwatch.stop());
				} catch (Exception e) {
					JustEnoughWidgets.LOGGER.error("Error loading plugin {}: {}", plugin.getClass().getCanonicalName(), e.getLocalizedMessage());
				}
			}
		}
		started = !started;
	}

	@Nullable
	private IWidgetPlugin getDefaultPlugin() {
		for (IWidgetPlugin widgetPlugin : plugins)
			if (widgetPlugin.getClass().equals(JEWPlugin.class))
				return widgetPlugin;

		return null;
	}
}
