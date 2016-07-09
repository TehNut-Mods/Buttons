package tehnut.jew.plugins.jew.button;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tehnut.jew.plugins.jew.ButtonBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nullable;

public class ButtonHeal extends ButtonBase {

	public ButtonHeal() {
		super(WidgetTextures.BUTTON_NIGHT, "button_heal");

		setServerRequired();
	}

	@Override
	public void onServerClick(EntityPlayerMP player) {
		player.setHealth(player.getMaxHealth());
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return new TextComponentTranslation("button.jew.heal.title");
	}
}
