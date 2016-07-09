package tehnut.jew.api.button;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.WidgetTexture;

import javax.annotation.Nullable;
import java.util.List;

public interface IModed {

	List<Mode> getModes();

	Mode getMode();

	void cycleMode();

	class Mode {

		private final WidgetTexture modeTexture;
		private final Button parent;

		public Mode(WidgetTexture modeTexture, Button parent) {
			this.modeTexture = modeTexture;
			this.parent = parent;
		}

		@SideOnly(Side.CLIENT)
		public EnumActionResult onClientClick(int mouseX, int mouseY) {
			return EnumActionResult.SUCCESS;
		}

		public void onServerClick(EntityPlayerMP player) {

		}

		@Nullable
		public ITextComponent getTitle() {
			return null;
		}

		public WidgetTexture getModeTexture() {
			return modeTexture;
		}

		public Button getParent() {
			return parent;
		}
	}
}
