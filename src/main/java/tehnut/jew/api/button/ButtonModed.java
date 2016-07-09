package tehnut.jew.api.button;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import tehnut.jew.api.ClientHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ButtonModed extends Button implements IModed {

	private int modeIndex = 0;

	public ButtonModed(ButtonTexture buttonTexture) {
		super(buttonTexture);
	}

	@Override
	public Mode getMode() {
		return getModes().get(modeIndex);
	}

	@Override
	public void cycleMode() {
		int newIndex = modeIndex + 1;
		if (newIndex >= getModes().size())
			newIndex = 0;

		modeIndex = newIndex;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public EnumActionResult onClientClick(int mouseX, int mouseY) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			cycleMode();
			return EnumActionResult.FAIL;
		}

		return getMode().onClientClick(mouseX, mouseY);
	}

	@Override
	public void onServerClick(EntityPlayerMP player) {
		getMode().onServerClick(player);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void drawButton(int x, int y) {
		minecraft.renderEngine.bindTexture(getMode().getModeTexture().getTextureLocation());
		ClientHelper.drawTexture(1, x, y, getMode().getModeTexture());
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return getMode().getTitle();
	}

	@Nonnull
	@Override
	public ITextComponent getUseNotification(EntityPlayerMP player) {
		return new TextComponentString(String.format("%s used the %s button with mode %d.", player.getDisplayNameString(), getButtonId(), modeIndex));
	}

	@Override
	public abstract List<Mode> getModes();

	@Override
	public abstract ResourceLocation getButtonId();
}
