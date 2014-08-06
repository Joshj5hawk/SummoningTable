package com.joshj5hawk.crafting;

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
		if(item == Items.diamond && item2 == Items.leather || item == Items.leather && item2 == Items.diamond)
			return new ItemStack(Items.spawn_egg, 1, 92);
		
		return null;
	}
}
