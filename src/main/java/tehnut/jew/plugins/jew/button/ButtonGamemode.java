package tehnut.jew.plugins.jew.button;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.GameType;
import tehnut.jew.plugins.jew.ButtonModeBase;
import tehnut.jew.plugins.jew.ButtonTextures;

import javax.annotation.Nullable;
import java.util.List;

public class ButtonGamemode extends ButtonModeBase {

	private final List<Mode> modes = ImmutableList.of(
			// SURVIVAL
			new Mode(ButtonTextures.BUTTON_DAY, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.setGameType(GameType.SURVIVAL);
					player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode.survival")));
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("gameMode.survival");
				}
			},
			// CREATIVE
			new Mode(ButtonTextures.BUTTON_EXIT, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.setGameType(GameType.CREATIVE);
					player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode.creative")));
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("gameMode.creative");
				}
			},
			// ADVENTURE
			new Mode(ButtonTextures.BUTTON_NIGHT, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.setGameType(GameType.ADVENTURE);
					player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode.adventure")));
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("gameMode.adventure");
				}
			},
			// SPECTATOR
			new Mode(ButtonTextures.BUTTON_EXIT, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.setGameType(GameType.SPECTATOR);
					player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode.spectator")));
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("gameMode.spectator");
				}
			}
	);

	public ButtonGamemode() {
		super("button_gamemode");

		setModes(modes);
		setServerRequired();
	}
}
