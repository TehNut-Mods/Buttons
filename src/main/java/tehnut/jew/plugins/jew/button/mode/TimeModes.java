package tehnut.jew.plugins.jew.button.mode;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.WidgetTexture;
import tehnut.jew.api.button.IMode;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public enum  TimeModes implements IMode {
	DAWN(WidgetTextures.DAWN, 23000),
	DAY(WidgetTextures.DAY, 6000),
	DUSK(WidgetTextures.DUSK, 13000),
	NIGHT(WidgetTextures.NIGHT, 18000),
	;

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
	public ITextComponent getTitle() {
		return new TextComponentTranslation("button.jew.time." + name().toLowerCase(Locale.ENGLISH) + ".title");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumActionResult onClientClick(int mouseX, int mouseY) {
		return net.minecraft.util.EnumActionResult.SUCCESS;
	}

	@Override
	public void onServerClick(EntityPlayerMP player) {
		player.getEntityWorld().setWorldTime(time);
	}
}
