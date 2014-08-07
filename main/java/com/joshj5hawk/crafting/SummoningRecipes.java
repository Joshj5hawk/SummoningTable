package com.joshj5hawk.crafting;

import com.joshj5hawk.handler.ConfigurationFile;
import com.joshj5hawk.main.SummoningTable;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SummoningRecipes
{
	public SummoningRecipes()
	{
		
	}
	public static ItemStack getSummoningResult(Item item, Item item2)
	{
		return getOutput(item, item2);
	}
	
	//RECIPES HERE
	public static ItemStack getOutput(Item item, Item item2)
	{
		if(ConfigurationFile.easyMode == true)
		{
			if(item == Items.iron_ingot && item2 == Items.leather || item == Items.leather && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookCow, 1);
		
			return null;
		}
		else
		{
			if(item == Items.diamond && item2 == Items.leather || item == Items.leather && item2 == Items.iron_ingot)
			{
				return new ItemStack(SummoningTable.itemSummoningBookCow, 1, 32);
			}
			
			return null;
		}
	}
}
