package tehnut.jew.plugins.jew.button.mode;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.WidgetTexture;
import tehnut.jew.api.button.IMode;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nonnull;

public enum HealModes implements IMode {
	HEALTH(WidgetTextures.HEART) {
		@Override
		public ITextComponent getTitle() {
			return new TextComponentTranslation("button.jew.heal.health.title");
		}

		@Override
		public void onServerClick(EntityPlayerMP player) {
			player.setHealth(player.getMaxHealth());
		}
	},
	FEED(WidgetTextures.FOOD) {
		@Override
		public ITextComponent getTitle() {
			return new TextComponentTranslation("button.jew.heal.feed.title");
		}

		@Override
		public void onServerClick(EntityPlayerMP player) {
			player.getFoodStats().addStats(20, 2.0F);
		}
	},
	;

	private final WidgetTexture widgetTexture;

	HealModes(WidgetTexture widgetTexture) {
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
