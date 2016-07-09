package tehnut.jew.plugins.jew.button;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tehnut.jew.plugins.jew.ButtonModeBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nullable;
import java.util.List;

public class ButtonWeather extends ButtonModeBase {

	private List<Mode> modes = ImmutableList.of(
			// SUN
			new Mode(WidgetTextures.DAY, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getEntityWorld().getWorldInfo().setCleanWeatherTime(Integer.MAX_VALUE);
					player.getEntityWorld().getWorldInfo().setRaining(false);
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.weather.sun.title");
				}
			},
			// RAIN
			new Mode(WidgetTextures.NIGHT, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getEntityWorld().getWorldInfo().setCleanWeatherTime(0);
					player.getEntityWorld().getWorldInfo().setRaining(true);
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.weather.rain.title");
				}
			}
	);

	public ButtonWeather() {
		super("button_weather");

		setServerRequired();
		setModes(modes);
	}
}
