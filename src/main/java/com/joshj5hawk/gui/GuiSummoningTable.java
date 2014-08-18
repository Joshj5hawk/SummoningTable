package com.joshj5hawk.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.joshj5hawk.container.ContainerSummoningTable;
import com.joshj5hawk.lib.Strings;
import com.joshj5hawk.tileentity.TileEntitySummoningTable;

public class GuiSummoningTable extends GuiContainer
{

	private ResourceLocation texutre = new ResourceLocation(Strings.modid + ":" + "textures/gui/summoningTable.png");
	private TileEntitySummoningTable summoningTable;
	
	public GuiSummoningTable(InventoryPlayer invPlayer, TileEntitySummoningTable tileEntityST) 
	{
		super(new ContainerSummoningTable(invPlayer, tileEntityST));
		summoningTable = tileEntityST;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	public void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String name = this.summoningTable.hasCustomInventoryName() ? this.summoningTable.getInventoryName() : I18n.format(this.summoningTable.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) - 70 / 2, 6, 4210752);
		this.fontRendererObj.drawString("Table", this.xSize / 2 - this.fontRendererObj.getStringWidth("Table") - 95 / 2, 15, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 63, this.ySize - 96 + 2, 4210752);
	}	

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texutre);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if(summoningTable.hasFuel())
		{
			int i1 = summoningTable.getFuelRemainingScaled(34);
			drawTexturedModalRect(guiLeft + 54, guiTop + 37 - i1, 227, 31 - i1, 12, i1);
		}
		
		int j1 = summoningTable.getSummoningProgressScaled(72);
		drawTexturedModalRect(guiLeft + 43, guiTop + 36, 176, 33, j1 + 1, 43);
	}
}
