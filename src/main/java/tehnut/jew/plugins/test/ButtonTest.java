package tehnut.jew.plugins.test;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import tehnut.jew.plugins.jew.ButtonBase;
import tehnut.jew.plugins.jew.WidgetTextures;

import javax.annotation.Nullable;

public class ButtonTest extends ButtonBase {

	private int id;

	public ButtonTest(int i) {
		super(WidgetTextures.DAY, "button_test" + i);

		this.id = i;
	}

	@Nullable
	@Override
	public ITextComponent getTitle() {
		return new TextComponentString("Test button " + id);
	}
}
