package tehnut.jew.plugins.jew.button;

import com.google.common.base.Predicates;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tehnut.jew.plugins.jew.ButtonBase;
import tehnut.jew.plugins.jew.ButtonTextures;

import javax.annotation.Nullable;

public class ButtonKillall extends ButtonBase {

	public ButtonKillall() {
		super(ButtonTextures.BUTTON_EXIT, "button_killall");

		setServerRequired();
	}

	@Override
	public void onServerClick(EntityPlayerMP player) {
		for (EntityLiving living : player.getEntityWorld().getEntities(EntityLiving.class, Predicates.instanceOf(IMob.class)))
			living.setDead();
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return new TextComponentTranslation("button.jew.killall.title");
	}
}
