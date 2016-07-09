package tehnut.jew.plugins.jew.button;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tehnut.jew.plugins.jew.ButtonModeBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nullable;
import java.util.List;

public class ButtonTime extends ButtonModeBase {

	private final List<Mode> modes = ImmutableList.of(
			// DAWN
			new Mode(WidgetTextures.DAWN, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getEntityWorld().setWorldTime(23000);
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.time.dawn.title");
				}
			},
			// DAY
			new Mode(WidgetTextures.DAY, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getEntityWorld().setWorldTime(6000);
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.time.day.title");
				}
			},
			// DUSK
			new Mode(WidgetTextures.DUSK, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getEntityWorld().setWorldTime(13000);
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.time.dusk.title");
				}
			},
			// NIGHT
			new Mode(WidgetTextures.NIGHT, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getEntityWorld().setWorldTime(18000);
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
