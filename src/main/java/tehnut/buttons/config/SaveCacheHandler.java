package tehnut.buttons.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import tehnut.buttons.Buttons;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveCacheHandler {

    private static final File CONFIG_FILE = new File(Buttons.configDir, "InventorySlots.json");
    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();

    private static String[] saveSlots = {
        "", "", "", "", ""
    };
    private static String[] slotNames = {
        "", "", "", "", ""
    };

    public static void writeSaveConfig() {
        try {
            if (!CONFIG_FILE.exists())
                CONFIG_FILE.createNewFile();

            String json = GSON.toJson(new StringArrayPair(saveSlots, slotNames));
            FileWriter writer = new FileWriter(CONFIG_FILE);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readSaveConfig() {
        try {
            if (!CONFIG_FILE.exists())
                return;

            StringArrayPair arrayPair = GSON.fromJson(new FileReader(CONFIG_FILE), StringArrayPair.class);
            saveSlots = arrayPair.saveSlots;
            slotNames = arrayPair.slotNames;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setSaveSlot(int slotNumber, @Nullable NBTTagCompound tagCompound) {
        saveSlots[slotNumber] = tagCompound == null ? "" : tagCompound.toString();
        writeSaveConfig();
    }

    public static void setSlotName(int slotNumber, String slotName) {
        slotNames[slotNumber] = slotName;
        writeSaveConfig();
    }

    @Nullable
    public static NBTTagCompound getSaveSlot(int slotNumber) {
        try {
            return JsonToNBT.getTagFromJson(saveSlots[slotNumber]);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getSlotName(int slotNumber) {
        return slotNames[slotNumber];
    }

    private static class StringArrayPair {
        private String[] saveSlots;
        private String[] slotNames;

        public StringArrayPair(String[] saveSlots, String[] slotNames) {
            this.saveSlots = saveSlots;
            this.slotNames = slotNames;
        }
    }
}
