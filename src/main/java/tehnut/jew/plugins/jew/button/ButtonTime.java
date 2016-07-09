package tehnut.jew.plugins.jew.button;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tehnut.jew.plugins.jew.ButtonModeBase;
import tehnut.jew.plugins.jew.ButtonTextures;

import javax.annotation.Nullable;
import java.util.List;

public class ButtonTime extends ButtonModeBase {

	private final List<Mode> modes = ImmutableList.of(
			// DAY
			new Mode(ButtonTextures.BUTTON_DAY, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getEntityWorld().setWorldTime(1000);
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.time.day.title");
				}
			},
			// NIGHT
			new Mode(ButtonTextures.BUTTON_NIGHT, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getEntityWorld().setWorldTime(13000);
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.time.night.title");
				}
			}
	);

	public ButtonTime() {
		super("button_time");

		setModes(modes);
		setServerRequired();
	}
}