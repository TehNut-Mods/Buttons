package tehnut.buttons.plugins.buttons.button.mode;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.api.WidgetTexture;
import tehnut.buttons.api.button.utility.IMode;
import tehnut.buttons.plugins.buttons.WidgetTextures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public enum TimeModes implements IMode {
    DAWN(WidgetTextures.DAWN, 23000),
    DAY(WidgetTextures.DAY, 6000),
    DUSK(WidgetTextures.DUSK, 13000),
    NIGHT(WidgetTextures.NIGHT, 18000),;

    private final WidgetTexture widgetTexture;
    private final int time;

    TimeModes(WidgetTexture widgetTexture, int time) {
        this.widgetTexture = widgetTexture;
        this.time = time;
    }

    @Nonnull
    @Override
    public WidgetTexture getModeTexture() {
        return widgetTexture;
    }

    @Nullable
    @Override
    public List<? extends ITextComponent> getTooltip() {
        return Collections.singletonList(new TextComponentTranslation("button.butt.time." + name().toLowerCase(Locale.ENGLISH) + ".title"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumActionResult onClientClick(int mouseX, int mouseY) {
        return EnumActionResult.SUCCESS;
    }

    @Override
    public void onServerClick(EntityPlayerMP player) {
        for (WorldServer worldServer : FMLCommonHandler.instance().getMinecraftServerInstance().worldServers) {
            int ticksPerDay = 24000;
            long currentTime = worldServer.getWorldTime();
            long currentDayTime = currentTime % ticksPerDay;
            long targetDayTime = time % ticksPerDay;
            long diffTime = ((targetDayTime - currentDayTime) + ticksPerDay) % ticksPerDay;
            worldServer.setWorldTime(currentTime + diffTime);
        }
    }
}
