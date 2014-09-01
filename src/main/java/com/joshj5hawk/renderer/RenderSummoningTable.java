package com.joshj5hawk.renderer;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.joshj5hawk.handler.ConfigurationFile;
import com.joshj5hawk.lib.Strings;
import com.joshj5hawk.model.ModelSummoningTable;

public class RenderSummoningTable extends TileEntitySpecialRenderer
{
	Tessellator t = Tessellator.instance;
	
	private static final ResourceLocation texture = new ResourceLocation(Strings.modid + ":textures/models/summoningTableModel.png");
	private static final ResourceLocation circleTextureLarge = new ResourceLocation(Strings.modid + ":textures/others/summoningCircleLarge.png");
	private static final ResourceLocation circleTextureMedium = new ResourceLocation(Strings.modid + ":textures/others/summoningCircleMedium.png");
	private static final ResourceLocation circleTextureSmall = new ResourceLocation(Strings.modid + ":textures/others/summoningCircleSmall.png");
	private ModelSummoningTable model;

	public float yRotationAngle = 0.10F;
	public float rotationSpeed = ConfigurationFile.summoningRotationSpeed;
	
	public RenderSummoningTable()
	{
		this.model = new ModelSummoningTable();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) 
	{
		yRotationAngle += f * 3.0F;
		if(yRotationAngle <= 360)
		{
			yRotationAngle -= 360;
		}
		//Large Circle
		GL11.glPushMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glTranslatef((float)x + 0.5F, (float)y + .75F, (float)z + 0.5F);
		GL11.glRotatef(yRotationAngle, 0.0F, 1.0F, 0.0F);
		t.startDrawingQuads();
		this.bindTexture(circleTextureLarge);
		t.addVertexWithUV(-0.375, 0, -0.375, 0, 0);
		t.addVertexWithUV(-0.375, 0, 0.375, 0, 1);
		t.addVertexWithUV(0.375, 0, 0.375, 1, 1);
		t.addVertexWithUV(0.375, 0, -0.375, 1, 0);
		
		t.addVertexWithUV(0.375, 0, -0.375, 0, 0);
		t.addVertexWithUV(0.375, 0, 0.375, 1, 0);
		t.addVertexWithUV(-0.375, 0, 0.375, 1, 1);
		t.addVertexWithUV(-0.375, 0, -0.375, 0, 1);
		t.draw();
		GL11.glPopMatrix();
		//Medium Large Circle
		GL11.glPushMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glTranslatef((float)x + 0.5F, (float)y + .8F, (float)z + 0.5F);
		GL11.glRotatef(-yRotationAngle, 0.0F, 1.0F, 0.0F);
		t.startDrawingQuads();
		this.bindTexture(circleTextureLarge);
		t.addVertexWithUV(-0.25, 0, -0.25, 0, 0);
		t.addVertexWithUV(-0.25, 0, 0.25, 0, 1);
		t.addVertexWithUV(0.25, 0, 0.25, 1, 1);
		t.addVertexWithUV(0.25, 0, -0.25, 1, 0);
		
		t.addVertexWithUV(0.25, 0, -0.25, 0, 0);
		t.addVertexWithUV(0.25, 0, 0.25, 1, 0);
		t.addVertexWithUV(-0.25, 0, 0.25, 1, 1);
		t.addVertexWithUV(-0.25, 0, -0.25, 0, 1);
		t.draw();
		GL11.glPopMatrix();
		//Medium Circle
		GL11.glPushMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glTranslatef((float)x + 0.5F, (float)y + .9F, (float)z + 0.5F);
		GL11.glRotatef(yRotationAngle, 1.0F, 1.0F, 0.0F);
		t.startDrawingQuads();
		this.bindTexture(circleTextureLarge);
		t.addVertexWithUV(-0.175, 0, -0.175, 0, 0);
		t.addVertexWithUV(-0.175, 0, 0.175, 0, 1);
		t.addVertexWithUV(0.175, 0, 0.175, 1, 1);
		t.addVertexWithUV(0.175, 0, -0.175, 1, 0);
		
		t.addVertexWithUV(0.175, 0, -0.175, 0, 0);
		t.addVertexWithUV(0.175, 0, 0.175, 1, 0);
		t.addVertexWithUV(-0.175, 0, 0.175, 1, 1);
		t.addVertexWithUV(-0.175, 0, -0.175, 0, 1);
		t.draw();
		GL11.glPopMatrix();
		//Small Circle
		GL11.glPushMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glTranslatef((float)x + 0.5F, (float)y + .9F, (float)z + 0.5F);
		GL11.glRotatef(-yRotationAngle, 0.0F, 1.0F, 1.0F);
		t.startDrawingQuads();
		this.bindTexture(circleTextureLarge);
		t.addVertexWithUV(-0.075, 0, -0.075, 0, 0);
		t.addVertexWithUV(-0.075, 0, 0.075, 0, 1);
		t.addVertexWithUV(0.075, 0, 0.075, 1, 1);
		t.addVertexWithUV(0.075, 0, -0.075, 1, 0);
		
		t.addVertexWithUV(0.075, 0, -0.075, 0, 0);
		t.addVertexWithUV(0.075, 0, 0.075, 1, 0);
		t.addVertexWithUV(-0.075, 0, 0.075, 1, 1);
		t.addVertexWithUV(-0.075, 0, -0.075, 0, 1);
		t.draw();
		GL11.glPopMatrix();
		//Table
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);
		this.bindTexture(texture);
		this.model.renderModel(0.0625F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
	}
	
	public void renderCircle(Entity entity, double x, double y, double z, float yaw, float pitch)
	{
		
	}
}
