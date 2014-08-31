package com.joshj5hawk.proxies;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.joshj5hawk.main.SummoningTable;
import com.joshj5hawk.renderer.ItemRenderSummoningTable;
import com.joshj5hawk.renderer.RenderSummoningTable;
import com.joshj5hawk.tileentity.TileEntitySummoningTable;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void initSounds() 
	{
		
	}
	
	@Override
	public void initRenderers() 
	{
		//SummoningTable
		TileEntitySpecialRenderer render = new RenderSummoningTable();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySummoningTable.class, render);
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SummoningTable.blockSummoningTableIdle), new ItemRenderSummoningTable(render, new TileEntitySummoningTable()));
	
	}
}
