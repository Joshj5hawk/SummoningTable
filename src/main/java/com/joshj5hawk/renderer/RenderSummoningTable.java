package com.joshj5hawk.renderer;

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
	private static final ResourceLocation circleTexture = new ResourceLocation(Strings.modid + ":textures/others/summoningCircle.png");
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
		yRotationAngle += f * rotationSpeed;
		if(yRotationAngle <= 360)
		{
			yRotationAngle -= 360;
		}
		GL11.glPushMatrix();
        GL11.glTranslated((float)x + (0.0625F * 2.0F), (float)y + 0.6875F, (float)z + (0.0625F * 2.0F));
		GL11.glTranslatef(0.5F, 0, 0.5F);
		GL11.glPushMatrix();
		GL11.glRotatef(yRotationAngle, 0.0f, 1.0f, 0.0f);

		//Translates to location on model
		t.startDrawingQuads();
		this.bindTexture(circleTexture);
		t.addVertexWithUV(0, 0, 0, 0, 0);
		t.addVertexWithUV(0, 0, .75, 0, 1);
		t.addVertexWithUV(.75, 0, .75, 1, 1);
		t.addVertexWithUV(.75, 0, 0, 1, 0);
		
		t.addVertexWithUV(0, 0, 0, 0, 0);
		t.addVertexWithUV(.75, 0, 0, 1, 0);
		t.addVertexWithUV(.75, 0, .75, 1, 1);
		t.addVertexWithUV(0, 0, .75, 0, 1);
		t.draw();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);
		this.bindTexture(texture);
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
	}
	
	public void renderCircle(Entity entity, double x, double y, double z, float yaw, float pitch)
	{
		
	}
}
