package tehnut.buttons.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

	public static Configuration config;

	private static boolean debugMode;
	public static boolean isDebugMode() { return debugMode; }

	public static void init(File file) {
		config = new Configuration(file);
		syncConfig();
	}

	public static void syncConfig() {
		String category;
		category = "general";
		debugMode = config.getBoolean("debugMode", category, false, "If true, more information will be logged to the console.");

		if (config.hasChanged())
			config.save();
	}
}
