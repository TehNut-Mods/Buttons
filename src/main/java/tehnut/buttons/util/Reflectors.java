package tehnut.buttons.util;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.List;

public class Reflectors {

    public static final FieldReflector<GuiScreen, List<GuiButton>> _BUTTON_LIST = new FieldReflector<GuiScreen, List<GuiButton>>(GuiScreen.class, "buttonList", "field_146292_n");
    public static final FieldReflector<GuiContainer, Integer> _GUI_LEFT = new FieldReflector<GuiContainer, Integer>(GuiContainer.class, "guiLeft", "field_147003_i");

    public static class FieldReflector<T, R> {

        private final Class clazz;
        private final String[] names = new String[2];
        private final Field field;

        public FieldReflector(Class<T> clazz, String... names) {
            this.clazz = clazz;
            this.names[0] = names[0];
            this.names[1] = names[1];
            this.field = ReflectionHelper.findField(clazz, names);
            this.field.setAccessible(true);
        }

        @SuppressWarnings("unchecked")
        public R get(T type) {
            try {
                return (R) field.get(type);
            } catch (Exception e) {
                return null;
            }
        }

        public Class getClazz() {
            return clazz;
        }

        public String[] getNames() {
            return names;
        }
    }

}
