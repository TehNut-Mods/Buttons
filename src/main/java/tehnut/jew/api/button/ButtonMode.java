package tehnut.jew.api.button;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import tehnut.jew.api.ClientHelper;
import tehnut.jew.api.WidgetTexture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ButtonMode<T extends Enum<T> & IMode> extends Button {

	private final T[] modes;
	private T mode;

	public ButtonMode(WidgetTexture widgetTexture, Class<T> enumClass) {
		super(widgetTexture);

		this.modes = enumClass.getEnumConstants();
		setMode(modes[0]);
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
		return new TextComponentString(String.format("%s used the %s button with mode %s.", player.getDisplayNameString(), getButtonId(), mode.name()));
	}

	@Override
	public abstract ResourceLocation getButtonId();

	public void cycleMode() {
		setMode(modes[(mode.ordinal() + 1) % modes.length]);
	}

	public T getMode() {
		return mode;
	}

	public void setMode(T mode) {
		this.mode = mode;
	}

	public T[] getModes() {
		return modes;
	}
}
