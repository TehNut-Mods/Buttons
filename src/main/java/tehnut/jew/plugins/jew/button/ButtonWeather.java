package tehnut.jew.plugins.jew.button;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.WidgetTexture;
import tehnut.jew.api.button.IMode;
import tehnut.jew.plugins.jew.ButtonModeBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ButtonWeather extends ButtonModeBase<ButtonWeather.ButtonTypes> {

	public ButtonWeather() {
		super(ButtonTypes.class, "button_weather");

		setServerRequired();
	}

	public enum ButtonTypes implements IMode {
		SUNNY(WidgetTextures.DAY) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("button.jew.weather.sun.title");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.getEntityWorld().getWorldInfo().setCleanWeatherTime(Integer.MAX_VALUE);
				player.getEntityWorld().getWorldInfo().setRaining(false);
			}
		},
		RAINY(WidgetTextures.NIGHT) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("button.jew.weather.rain.title");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.getEntityWorld().getWorldInfo().setCleanWeatherTime(0);
				player.getEntityWorld().getWorldInfo().setRaining(true);
			}
		}
		;

		private final WidgetTexture widgetTexture;

		ButtonTypes(WidgetTexture widgetTexture) {
			this.widgetTexture = widgetTexture;
		}

		@Nonnull
		@Override
		public WidgetTexture getModeTexture() {
			return widgetTexture;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public EnumActionResult onClientClick(int mouseX, int mouseY) {
			return EnumActionResult.SUCCESS;
		}
	}
}
