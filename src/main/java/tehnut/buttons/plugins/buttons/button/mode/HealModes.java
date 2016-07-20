package tehnut.buttons.plugins.buttons.button.mode;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.buttons.api.WidgetTexture;
import tehnut.buttons.api.button.utility.IMode;
import tehnut.buttons.plugins.buttons.WidgetTextures;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public enum HealModes implements IMode {
	HEALTH(WidgetTextures.HEART) {
		@Override
		public List<? extends ITextComponent> getTooltip() {
			return Collections.singletonList(new TextComponentTranslation("button.butt.heal.health.title"));
		}

		@Override
		public void onServerClick(EntityPlayerMP player) {
			player.setHealth(player.getMaxHealth());
		}
	},
	FEED(WidgetTextures.FOOD) {
		@Override
		public List<? extends ITextComponent> getTooltip() {
			return Collections.singletonList(new TextComponentTranslation("button.butt.heal.feed.title"));
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
