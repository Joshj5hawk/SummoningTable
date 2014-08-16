package com.joshj5hawk.crafting;

import com.joshj5hawk.handler.ConfigurationFile;
import com.joshj5hawk.main.SummoningTable;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
			if(item == Items.diamond && item2 == Items.leather || item == Items.leather && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookCow);
			if(item == Items.diamond && item2 == Items.porkchop || item == Items.porkchop && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookPig);
			if(item == Items.diamond && item2 == ItemBlock.getItemFromBlock(Blocks.wool) || item == ItemBlock.getItemFromBlock(Blocks.wool) && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookSheep);
			if(item == Items.diamond && item2 == Items.chicken || item == Items.chicken && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookChicken);
			if(item == Items.diamond && item2 == Items.mushroom_stew || item == Items.mushroom_stew && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookMooshroom);
			if(item == Items.diamond && item2 == Items.emerald || item == Items.emerald && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookVillager);
			if(item == Items.diamond && item2 == Items.snowball || item == Items.snowball && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookSnowGolem);
			if(item == Items.diamond && item2 == Items.apple || item == Items.apple && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookBat);
			if(item == Items.diamond && item2 == Items.saddle || item == Items.saddle && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookHorse);
			if(item == Items.diamond && item2 == Items.fish || item == Items.fish && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookOcelot);
			if(item == Items.diamond && item2 == Items.dye || item == Items.emerald && item2 == Items.diamond)
				return new ItemStack(SummoningTable.itemSummoningBookSquid);
			
			
			return null;
		}
		else
		{
			if(item == SummoningTable.itemSummoningCore && item2 == Items.leather || item == Items.leather && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookCow);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.porkchop || item == Items.porkchop && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookPig);
			if(item == SummoningTable.itemSummoningCore && item2 == ItemBlock.getItemFromBlock(Blocks.wool) || item == ItemBlock.getItemFromBlock(Blocks.wool) && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookSheep);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.chicken || item == Items.chicken && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookChicken);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.mushroom_stew || item == Items.mushroom_stew && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookMooshroom);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.emerald || item == Items.emerald && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookVillager);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.snowball || item == Items.snowball && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookSnowGolem);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.apple || item == Items.apple && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookBat);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.saddle || item == Items.saddle && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookHorse);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.fish || item == Items.fish && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookOcelot);
			if(item == SummoningTable.itemSummoningCore && item2 == Items.dye || item == Items.emerald && item2 == SummoningTable.itemSummoningCore)
				return new ItemStack(SummoningTable.itemSummoningBookSquid);
			
			return null;
		}
	}
}
