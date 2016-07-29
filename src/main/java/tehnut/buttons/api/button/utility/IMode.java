package tehnut.buttons.api.button.utility;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.api.WidgetTexture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface IMode {

    @Nonnull
    WidgetTexture getModeTexture();

    @Nullable
    List<? extends ITextComponent> getTooltip();

    @SideOnly(Side.CLIENT)
    EnumActionResult onClientClick(int mouseX, int mouseY);

    void onServerClick(EntityPlayerMP player);
}
