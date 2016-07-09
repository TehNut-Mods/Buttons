package tehnut.jew.plugins.jew.button;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tehnut.jew.plugins.jew.ButtonBase;
import tehnut.jew.plugins.jew.ButtonTextures;

import javax.annotation.Nullable;

public class ButtonDay extends ButtonBase {

	public ButtonDay() {
		super(ButtonTextures.BUTTON_DAY, "button_day");

		setServerRequired();
	}

	@Override
	public void onServerClick(EntityPlayer player) {
		player.getEntityWorld().setWorldTime(1000);
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return new TextComponentTranslation("button.jew.day.title");
	}
}
