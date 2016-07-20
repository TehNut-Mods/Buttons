package tehnut.buttons.plugins.test;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import tehnut.buttons.plugins.buttons.ButtonBase;
import tehnut.buttons.plugins.buttons.WidgetTextures;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class ButtonTest extends ButtonBase {

	private int id;

	public ButtonTest(int i) {
		super(WidgetTextures.DAY, "button_test" + i);

		this.id = i;
	}

	@Nullable
	@Override
	public List<? extends ITextComponent> getTooltip() {
		return Collections.singletonList(new TextComponentString("Test button " + id));
	}
}
