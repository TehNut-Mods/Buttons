package tehnut.jew.api.button;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.WidgetTexture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IMode {

	@Nonnull
	WidgetTexture getModeTexture();

	@Nullable
	ITextComponent getTitle();

	@SideOnly(Side.CLIENT)
	EnumActionResult onClientClick(int mouseX, int mouseY);

	void onServerClick(EntityPlayerMP player);
}
