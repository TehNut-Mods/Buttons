package tehnut.jew.plugins.jew.button;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tehnut.jew.plugins.jew.ButtonBase;
import tehnut.jew.plugins.jew.ButtonTextures;

import javax.annotation.Nullable;

public class ButtonNight extends ButtonBase {

	public ButtonNight() {
		super(ButtonTextures.BUTTON_NIGHT, "button_night");

		setServerRequired();
	}

	@Override
	public void onServerClick(EntityPlayerMP player) {
		player.getEntityWorld().setWorldTime(13000);
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return new TextComponentTranslation("button.jew.night.title");
	}
}
