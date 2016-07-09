package tehnut.jew.api.button;

import net.minecraft.util.ResourceLocation;

/**
 * Button textures must exist on a SpriteSheet as with most GUI elements.
 *
 * This class is a utility for mapping a box on a sheet to a specific texture.
 */
public class ButtonTexture {

	private final ResourceLocation textureLocation;
	private final int textureX;
	private final int textureY;
	private final int textureWidth;
	private final int textureHeight;

	public ButtonTexture(ResourceLocation textureLocation, int textureX, int textureY, int textureWidth, int textureHeight) {
		this.textureLocation = textureLocation;
		this.textureX = textureX;
		this.textureY = textureY;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}

	/**
	 * @return the location of the texture file starting from assets.
	 */
	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}

	/**
	 * @return the X starting location of the wanted image.
	 */
	public int getTextureX() {
		return textureX;
	}

	/**
	 * @return the Y starting location of the wanted image.
	 */
	public int getTextureY() {
		return textureY;
	}

	/**
	 * @return how wide the wanted image is.
	 */
	public int getTextureWidth() {
		return textureWidth;
	}

	/**
	 * @return how tall the wanted image is.
	 */
	public int getTextureHeight() {
		return textureHeight;
	}

	@Override
	public String toString() {
		return getTextureLocation().toString() + "@" + "{" + getTextureX() + ", " + getTextureY() + "}->{" + getTextureWidth() + ", " + getTextureHeight() + "}";
	}
}
