package tehnut.jew.api.button;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.ClientHelper;

import javax.annotation.Nullable;

/**
 * The base class for a Button that will be displayed at the top left of the screen when container is open.
 */
public abstract class Button {

	private final ButtonTexture buttonTexture;
	@SideOnly(Side.CLIENT)
	protected final Minecraft minecraft = Minecraft.getMinecraft();
	private boolean serverRequired = false;

	public Button(ButtonTexture buttonTexture) {
		this.buttonTexture = buttonTexture;
	}

	/**
	 * Called when the player clicks on the button.
	 */
	@SideOnly(Side.CLIENT)
	public void onClientClick(int mouseX, int mouseY) {

	}

	/**
	 * Called when the player clicks on the button <i>if</i> {@link #serverRequired} is true.
	 *
	 * @param player - The player who clicked on the button.
	 */
	public void onServerClick(EntityPlayerMP player) {

	}

	/**
	 * Draws the {@link #buttonTexture} onto the button background.
	 *
	 * @param x - The x location to draw the texture at.
	 * @param y - The y location to draw the texture at.
	 */
	@SideOnly(Side.CLIENT)
	public void drawButton(int x, int y) {
		minecraft.renderEngine.bindTexture(buttonTexture.getTextureLocation());
		ClientHelper.drawTexture(1, x, y, buttonTexture);
	}

	/**
	 * Returns the title of the button. Used for a hovering tooltip.
	 *
	 * If null is returned, no tooltip will be drawn.
	 *
	 * @return an {@link ITextComponent} to draw as a tooltip.
	 */
	@Nullable
	public ITextComponent getTitle() {
		return null;
	}

	/**
	 * Marks this button as one which requires server interaction to function.
	 *
	 * e.g. Healing the player.
	 *
	 * @return self for chaining.
	 */
	protected Button setServerRequired() {
		this.serverRequired = true;
		return this;
	}

	/**
	 * @return whether this button requires server interaction.
	 */
	public boolean isServerRequired() {
		return serverRequired;
	}

	/**
	 * Queried when {@link #isServerRequired()} is true.
	 *
	 * If true, makes sure the player has at least a permission level of 2. This is the same level required for commands
	 * such as {@code /gamemode}, {@code /tp}, etc.
	 *
	 * @return whether this button requires elevated permissions
	 */
	public boolean requireElevatedPermissions() {
		return false;
	}

	public ButtonTexture getButtonTexture() {
		return buttonTexture;
	}

	/**
	 * The unique ID for this button. Return the same ID as another button to override it. Overriding only works if you
	 * register <i>after</i> the button intended to be overridden.
	 *
	 * @return unique ID for this button.
	 */
	public abstract ResourceLocation getButtonId();
}
