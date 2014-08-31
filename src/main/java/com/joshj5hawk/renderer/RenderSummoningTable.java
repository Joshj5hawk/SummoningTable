package com.joshj5hawk.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import com.joshj5hawk.lib.Strings;
import com.joshj5hawk.model.ModelSummoningTable;

public class RenderSummoningTable extends TileEntitySpecialRenderer
{
	private static final ResourceLocation texture = new ResourceLocation(Strings.modid + ":" + "textures/models/summoningTableModel.png");
	private ModelSummoningTable model;

	public RenderSummoningTable()
	{
		this.model = new ModelSummoningTable();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) 
	{
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);
		this.bindTexture(texture);
		
		GL11.glPushMatrix();
			this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
