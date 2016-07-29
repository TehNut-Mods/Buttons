package tehnut.buttons.plugins.buttons.button.mode;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.api.WidgetTexture;
import tehnut.buttons.api.button.utility.IMode;
import tehnut.buttons.plugins.buttons.WidgetTextures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public enum WeatherModes implements IMode {
    SUNNY(WidgetTextures.DAY) {
        @Nullable
        @Override
        public List<? extends ITextComponent> getTooltip() {
            return Collections.singletonList(new TextComponentTranslation("button.butt.weather.sun.title"));
        }

        @Override
        public void onServerClick(EntityPlayerMP player) {
            player.getEntityWorld().getWorldInfo().setCleanWeatherTime(Integer.MAX_VALUE);
            player.getEntityWorld().getWorldInfo().setRaining(false);
        }
    },
    RAINY(WidgetTextures.NIGHT) {
        @Nullable
        @Override
        public List<? extends ITextComponent> getTooltip() {
            return Collections.singletonList(new TextComponentTranslation("button.butt.weather.rain.title"));
        }

        @Override
        public void onServerClick(EntityPlayerMP player) {
            player.getEntityWorld().getWorldInfo().setCleanWeatherTime(0);
            player.getEntityWorld().getWorldInfo().setRaining(true);
        }
    };

    private final WidgetTexture widgetTexture;

    WeatherModes(WidgetTexture widgetTexture) {
        this.widgetTexture = widgetTexture;
    }

    @Nonnull
    @Override
    public WidgetTexture getModeTexture() {
        return widgetTexture;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumActionResult onClientClick(int mouseX, int mouseY) {
        return EnumActionResult.SUCCESS;
    }
}
