package tehnut.jew.api.button;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.ClientHelper;
import tehnut.jew.api.WidgetTexture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The base class for a Button that will be displayed at the top left of the screen when container is open.
 */
public abstract class Button {

	private final WidgetTexture widgetTexture;
	@SideOnly(Side.CLIENT)
	protected final Minecraft minecraft = Minecraft.getMinecraft();
	private boolean serverRequired = false;

	public Button(WidgetTexture widgetTexture) {
		this.widgetTexture = widgetTexture;
	}

	/**
	 * Called when the player clicks on the button.
	 *
	 * {@link EnumActionResult#SUCCESS} will forward the call to the server if necessary.
	 * {@link EnumActionResult#FAIL} will not forward the call to the server.
	 * {@link EnumActionResult#PASS} will not forward the call to the server.
	 *
	 * @return the result of this call.
	 */
	@SideOnly(Side.CLIENT)
	public EnumActionResult onClientClick(int mouseX, int mouseY) {
		return EnumActionResult.SUCCESS;
	}

	/**
	 * Called when the player clicks on the button <i>if</i> {@link #serverRequired} is true.
	 *
	 * @param player - The player who clicked on the button.
	 */
	public void onServerClick(EntityPlayerMP player) {

	}

	/**
	 * Draws the {@link #widgetTexture} onto the button background.
	 *
	 * @param x - The x location to draw the texture at.
	 * @param y - The y location to draw the texture at.
	 */
	@SideOnly(Side.CLIENT)
	public void drawButton(int x, int y) {
		minecraft.renderEngine.bindTexture(getWidgetTexture().getTextureLocation());
		ClientHelper.drawTexture(1, x, y, getWidgetTexture());
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
	 * A message to print to console if {@link #isServerRequired()} is true and usage was successful.
	 *
	 * Used to inform server owners of people using server functionality.
	 *
	 * @param player - The player who has clicked on the button.
	 *
	 * @return a message to print to console.
	 */
	@Nonnull
	public ITextComponent getUseNotification(EntityPlayerMP player) {
		return new TextComponentString(String.format("%s used the %s button.", player.getDisplayNameString(), getButtonId()));
	}

	/**
	 * Marks this button as one which requires server interaction to function.
	 *
	 * e.g. Healing the player.
	 *
	 * @return self for chaining.
	 */
	protected final Button setServerRequired() {
		this.serverRequired = true;
		return this;
	}

	/**
	 * Queried when the client clicks on the button.
	 *
	 * @return whether this button requires server interaction.
	 */
	public final boolean isServerRequired() {
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

	public WidgetTexture getWidgetTexture() {
		return widgetTexture;
	}

	/**
	 * The unique ID for this button. Return the same ID as another button to override it. Overriding only works if you
	 * register <i>after</i> the button intended to be overridden.
	 *
	 * @return unique ID for this button.
	 */
	public abstract ResourceLocation getButtonId();
}
