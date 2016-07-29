package tehnut.buttons.api;

/**
 * The main class for a plugin. Everything communicated between a mod and JEW is through this class.
 * IWidgetPlugins must have the @WidgetPlugin annotation to get loaded by JEW.
 * This class must not import anything that could be missing at runtime (i.e. code from any other mod).
 */
public interface IWidgetPlugin {
    /**
     * Register this mod plugin with the registry.
     * Called during {@link net.minecraftforge.fml.common.event.FMLPostInitializationEvent}
     */
    void register(IWidgetRegistry widgetRegistry);

    /**
     * Base implementation. Used to avoid plugin breakages if new methods are added.
     */
    class Base implements IWidgetPlugin {

        @Override
        public void register(IWidgetRegistry widgetRegistry) {

        }
    }
}
