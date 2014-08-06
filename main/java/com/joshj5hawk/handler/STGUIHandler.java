package com.joshj5hawk.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.joshj5hawk.container.ContainerSummoningTable;
import com.joshj5hawk.gui.GuiSummoningTable;
import com.joshj5hawk.main.SummoningTable;
import com.joshj5hawk.tileentity.TileEntitySummoningTable;

import cpw.mods.fml.common.network.IGuiHandler;

public class STGUIHandler implements IGuiHandler 
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null)
		{
			switch(ID)
			{
				case SummoningTable.guiIDSummoningTable:
					if(entity instanceof TileEntitySummoningTable)
					{
						return new ContainerSummoningTable(player.inventory, (TileEntitySummoningTable) entity);
					}
			}
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null)
		{
			switch(ID)
			{
				case SummoningTable.guiIDSummoningTable:
					if(entity instanceof TileEntitySummoningTable)
					{
						return new GuiSummoningTable(player.inventory, (TileEntitySummoningTable) entity);
					}
			}
		}
		
		return null;
		
	}

}
