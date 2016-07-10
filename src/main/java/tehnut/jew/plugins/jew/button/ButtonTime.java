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

public class ButtonTime extends ButtonModeBase<ButtonTime.ButtonTypes> {

	public ButtonTime() {
		super(ButtonTypes.class, "button_time");

		setServerRequired();
	}

	public enum ButtonTypes implements IMode {
		DAWN(WidgetTextures.DAWN) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("button.jew.time.dawn.title");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.getEntityWorld().setWorldTime(23000);
			}
		},
		DAY(WidgetTextures.DAY) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("button.jew.time.day.title");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.getEntityWorld().setWorldTime(6000);
			}
		},
		DUSK(WidgetTextures.DUSK) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("button.jew.time.dusk.title");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.getEntityWorld().setWorldTime(13000);
			}
		},
		NIGHT(WidgetTextures.NIGHT) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("button.jew.time.night.title");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.getEntityWorld().setWorldTime(18000);
			}
		},
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
			return net.minecraft.util.EnumActionResult.SUCCESS;
		}
	}
}
