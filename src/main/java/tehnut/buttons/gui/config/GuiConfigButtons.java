package tehnut.buttons.gui.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import tehnut.buttons.Buttons;
import tehnut.buttons.config.ConfigHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GuiConfigButtons extends GuiConfig {

    public GuiConfigButtons(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), Buttons.MODID, false, false, Buttons.NAME);
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        for (String category : ConfigHandler.categories)
            list.add(new ConfigElement(ConfigHandler.config.getCategory(category.toLowerCase(Locale.ENGLISH))));

        return list;
    }
}
