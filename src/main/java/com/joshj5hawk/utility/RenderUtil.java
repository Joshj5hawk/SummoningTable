package com.joshj5hawk.utility;
import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;

public class RenderUtil 
{
	public static void render3DItem(EntityItem item, float partialTickTime, boolean rotate)
	{
		float rot = -(Minecraft.getMinecraft().theWorld.getTotalWorldTime() + partialTickTime)	% 360 * 2;
		
		glPushMatrix();
		glDepthMask(true);
		rotate &= Minecraft.getMinecraft().gameSettings.fancyGraphics;
		
		if(rotate)
		{
			glRotatef(rot, 0, 1, 0);
		}
		
		item.hoverStart = 0.0F;
		RenderManager.instance.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		
		glPopMatrix();
	}
}
