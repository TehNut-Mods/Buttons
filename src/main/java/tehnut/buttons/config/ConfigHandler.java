package tehnut.buttons.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ConfigHandler {

    public static Configuration config;
    public static Set<String> categories = new HashSet<String>();

    private static boolean debugMode;

    public static boolean isDebugMode() {
        return debugMode;
    }

    private static boolean enableUtiltiyButtons;
    private static boolean enableSaveButtons;

    public static boolean enableUtilityButtons() {
        return enableUtiltiyButtons;
    }

    public static boolean enableSaveButtons() {
        return enableSaveButtons;
    }

    private static boolean hideUnusableButtons;

    public static boolean shouldHideUnusableButtons() {
        return hideUnusableButtons;
    }

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;
        category = "general";
        categories.add(category);
        debugMode = config.getBoolean("debugMode", category, false, "If true, more information will be logged to the console.");

        category = "modules";
        categories.add(category);
        enableUtiltiyButtons = config.getBoolean("enableUtilityButtons", category, true, "Enables utility buttons at the top left of the screen.");
        enableSaveButtons = config.getBoolean("enableSaveButtons", category, true, "Enables the inventory save buttons.");

        category = "modules.utilitybuttons";
        categories.add(category);
        hideUnusableButtons = config.getBoolean("hideUnusableButtons", category, false, "Hides buttons that require permissions that you don't have.");

        if (config.hasChanged())
            config.save();
    }
}
