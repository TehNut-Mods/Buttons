package tehnut.buttons.gui.config;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.Buttons;
import tehnut.buttons.config.ConfigHandler;
import tehnut.buttons.config.SaveCacheHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class GuiConfigButtons extends GuiConfig {

    private static ConfigCategory inventoryNameCat;

    public GuiConfigButtons(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), Buttons.MODID, false, false, Buttons.NAME);
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        for (String category : ConfigHandler.categories)
            list.add(new ConfigElement(ConfigHandler.config.getCategory(category.toLowerCase(Locale.ENGLISH))));

        inventoryNameCat = new ConfigCategory("Inventory Slot Names");
        for (int i = 0; i < 5; i++) {
            inventoryNameCat.put("Slot" + i, new Property("Slot " + (i + 1), SaveCacheHandler.getSlotName(i), Property.Type.STRING));
        }

        list.add(new ConfigElement(inventoryNameCat));

        return list;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 2000) {
            for (Map.Entry<String, Property> propertyEntry : inventoryNameCat.entrySet()) {
                int slotNumber = Integer.parseInt(propertyEntry.getKey().substring(4));
                String propertyValue = propertyEntry.getValue().getString();
                SaveCacheHandler.setSlotName(slotNumber, propertyValue);
            }
        }

        super.actionPerformed(button);
    }
}
