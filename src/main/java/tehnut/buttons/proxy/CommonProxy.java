package tehnut.buttons.proxy;

import com.google.common.base.Stopwatch;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tehnut.buttons.Buttons;
import tehnut.buttons.api.IWidgetPlugin;
import tehnut.buttons.impl.WidgetRegistry;
import tehnut.buttons.plugins.buttons.ButtonsPlugin;
import tehnut.buttons.util.AnnotationHelper;

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

    public void startup() {
        if (!started) {
            for (IWidgetPlugin plugin : plugins) {
                try {
                    Stopwatch stopwatch = Stopwatch.createStarted();
                    Buttons.LOGGER.info("Registering plugin: {}", plugin.getClass().getCanonicalName());
                    plugin.register(WidgetRegistry.INSTANCE);
                    Buttons.debug("Registered plugin {} in {}", plugin.getClass().getCanonicalName(), stopwatch.stop());
                } catch (Exception e) {
                    Buttons.LOGGER.error("Error loading plugin {}: {}", plugin.getClass().getCanonicalName(), e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }
        started = !started;
    }

    @Nullable
    private IWidgetPlugin getDefaultPlugin() {
        for (IWidgetPlugin widgetPlugin : plugins)
            if (widgetPlugin.getClass().equals(ButtonsPlugin.class))
                return widgetPlugin;

        return null;
    }
}
