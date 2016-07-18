package tehnut.jew.api;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientHelper {

	public static void drawTexture(double zLevel, int x, int y, WidgetTexture widgetTexture) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double) x, (double) (y + widgetTexture.getTextureHeight()), zLevel).tex((double) ((float) (widgetTexture.getTextureX()) * f), (double) ((float) (widgetTexture.getTextureY() + widgetTexture.getTextureHeight()) * f1)).endVertex();
		vertexbuffer.pos((double) (x + widgetTexture.getTextureWidth()), (double) (y + widgetTexture.getTextureHeight()), zLevel).tex((double) ((float) (widgetTexture.getTextureX() + widgetTexture.getTextureWidth()) * f), (double) ((float) (widgetTexture.getTextureY() + widgetTexture.getTextureHeight()) * f1)).endVertex();
		vertexbuffer.pos((double) (x + widgetTexture.getTextureWidth()), (double) (y), zLevel).tex((double) ((float) (widgetTexture.getTextureX() + widgetTexture.getTextureWidth()) * f), (double) ((float) (widgetTexture.getTextureY()) * f1)).endVertex();
		vertexbuffer.pos((double) x, (double) (y), zLevel).tex((double) ((float) (widgetTexture.getTextureX()) * f), (double) ((float) (widgetTexture.getTextureY()) * f1)).endVertex();
		tessellator.draw();
	}

	public static boolean isMouseBetween(int mouseX, int mouseY, int x, int y, int width, int height) {
		int xSize = x + width;
		int ySize = y + height;
		return mouseX >= x && mouseX <= xSize && mouseY >= y && mouseY <= ySize;
	}
}
