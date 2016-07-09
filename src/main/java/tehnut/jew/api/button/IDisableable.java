package tehnut.jew.api.button;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Marks a {@link Button} as disable-able.
 *
 * Disable-able buttons can be right clicked to toggle.
 */
public interface IDisableable {

	void onDisableClient(int mouseX, int mouseY);

	void onDisableServer(EntityPlayer player);
}
