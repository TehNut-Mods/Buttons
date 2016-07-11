package tehnut.jew.plugins.jew.button.mode;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.WidgetTexture;
import tehnut.jew.api.button.IMode;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum GameModes implements IMode {
	SURVIVAL(WidgetTextures.BLANK, GameType.SURVIVAL),
	CREATIVE(WidgetTextures.BLANK, GameType.CREATIVE),
	ADVENTURE(WidgetTextures.BLANK, GameType.ADVENTURE),
	SPECTATOR(WidgetTextures.GLASSES, GameType.SPECTATOR),
	;

	private final WidgetTexture widgetTexture;
	private final GameType gameType;

	GameModes(WidgetTexture widgetTexture, GameType gameType) {
		this.widgetTexture = widgetTexture;
		this.gameType = gameType;
	}

	@Nonnull
	@Override
	public WidgetTexture getModeTexture() {
		return widgetTexture;
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return new TextComponentTranslation("gameMode." + gameType.getName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumActionResult onClientClick(int mouseX, int mouseY) {
		return EnumActionResult.SUCCESS;
	}

	@Override
	public void onServerClick(EntityPlayerMP player) {
		player.setGameType(gameType);
		player.addChatComponentMessage(new TextComponentTranslation("gameMode.changed", new TextComponentTranslation("gameMode." + gameType.getName())));
	}
}
