package tehnut.jew.api;

/**
 * The main class for a plugin. Everything communicated between a mod and JEW is through this class.
 * IWidgetPlugins must have the @WidgetPlugin annotation to get loaded by JEW.
 * This class must not import anything that could be missing at runtime (i.e. code from any other mod).
 */
public interface IWidgetPlugin {
	/**
	 * Register this mod plugin with the registry.
	 * Called just before the game launches.
	 * Will be called again if the config changes.
	 */
	void register(IWidgetRegistry widgetRegistry);

	class Base implements IWidgetPlugin {

		@Override
		public void register(IWidgetRegistry widgetRegistry) {

		}
	}
}
