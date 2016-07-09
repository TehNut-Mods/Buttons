package tehnut.jew.plugins.jew.button;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tehnut.jew.plugins.jew.ButtonModeBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nullable;
import java.util.List;

public class ButtonHeal extends ButtonModeBase {

	private List<Mode> modes = ImmutableList.of(
			// HEALTH
			new Mode(WidgetTextures.HEART, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.setHealth(player.getMaxHealth());
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.heal.health.title");
				}
			},
			// FOOD
			new Mode(WidgetTextures.FOOD, this) {
				@Override
				public void onServerClick(EntityPlayerMP player) {
					player.getFoodStats().addStats(20, 2.0F);
				}

				@Nullable
				@Override
				public ITextComponent getTitle() {
					return new TextComponentTranslation("button.jew.heal.feed.title");
				}
			}
	);

	public ButtonHeal() {
		super("button_heal");

		setServerRequired();
		setModes(modes);
	}
}
