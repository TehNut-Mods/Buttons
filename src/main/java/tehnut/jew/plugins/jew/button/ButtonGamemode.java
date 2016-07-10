package tehnut.jew.plugins.jew.button;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.WidgetTexture;
import tehnut.jew.api.button.IMode;
import tehnut.jew.plugins.jew.ButtonModeBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ButtonGamemode extends ButtonModeBase<ButtonGamemode.ButtonTypes> {

	public ButtonGamemode() {
		super(ButtonTypes.class, "button_gamemode");

		setServerRequired();
	}

	public enum ButtonTypes implements IMode {
		SURVIVAL(WidgetTextures.DAWN) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("gameMode.survival");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.setGameType(GameType.SURVIVAL);
				player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode.survival")));
			}
		},
		CREATIVE(WidgetTextures.DAY) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("gameMode.creative");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.setGameType(GameType.CREATIVE);
				player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode.creative")));
			}
		},
		ADVENTURE(WidgetTextures.DUSK) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("gameMode.adventure");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.setGameType(GameType.ADVENTURE);
				player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode.adventure")));
			}
		},
		SPECTATOR(WidgetTextures.NIGHT) {
			@Nullable
			@Override
			public ITextComponent getTitle() {
				return new TextComponentTranslation("gameMode.spectator");
			}

			@Override
			public void onServerClick(EntityPlayerMP player) {
				player.setGameType(GameType.SPECTATOR);
				player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode.spectator")));
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
			return EnumActionResult.SUCCESS;
		}
	}
}
