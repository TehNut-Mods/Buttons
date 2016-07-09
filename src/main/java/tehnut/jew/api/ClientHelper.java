package tehnut.jew.api;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.jew.api.button.ButtonTexture;

@SideOnly(Side.CLIENT)
public class ClientHelper {

	public static void drawTexture(double zLevel, int x, int y, ButtonTexture buttonTexture) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double) x, (double) (y + buttonTexture.getTextureHeight()), zLevel).tex((double) ((float) (buttonTexture.getTextureX()) * f), (double) ((float) (buttonTexture.getTextureY() + buttonTexture.getTextureHeight()) * f1)).endVertex();
		vertexbuffer.pos((double) (x + buttonTexture.getTextureWidth()), (double) (y + buttonTexture.getTextureHeight()), zLevel).tex((double) ((float) (buttonTexture.getTextureX() + buttonTexture.getTextureWidth()) * f), (double) ((float) (buttonTexture.getTextureY() + buttonTexture.getTextureHeight()) * f1)).endVertex();
		vertexbuffer.pos((double) (x + buttonTexture.getTextureWidth()), (double) (y), zLevel).tex((double) ((float) (buttonTexture.getTextureX() + buttonTexture.getTextureWidth()) * f), (double) ((float) (buttonTexture.getTextureY()) * f1)).endVertex();
		vertexbuffer.pos((double) x, (double) (y), zLevel).tex((double) ((float) (buttonTexture.getTextureX()) * f), (double) ((float) (buttonTexture.getTextureY()) * f1)).endVertex();
		tessellator.draw();
	}

	public static boolean isMouseBetween(int mouseX, int mouseY, int x, int y, int width, int height) {
		int xSize = x + width;
		int ySize = y + height;
		return (mouseX >= x && mouseX <= xSize && mouseY >= y && mouseY <= ySize);
	}
}
