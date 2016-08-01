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

    public static void writeSaveConfig() {
        try {
            if (!CONFIG_FILE.exists())
                CONFIG_FILE.createNewFile();

            String json = GSON.toJson(saveSlots);
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

            saveSlots = GSON.fromJson(new FileReader(CONFIG_FILE), String[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setSaveSlot(int slotNumber, @Nullable NBTTagCompound tagCompound) {
        saveSlots[slotNumber] = tagCompound == null ? "" : tagCompound.toString();
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
}
