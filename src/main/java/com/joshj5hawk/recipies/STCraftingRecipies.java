package com.joshj5hawk.recipies;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.joshj5hawk.crafting.SummoningRecipes;
import com.joshj5hawk.main.SummoningTable;

import cpw.mods.fml.common.registry.GameRegistry;

public class STCraftingRecipies 
{
	
	public static void mainRegistry()
	{
	    SummoningRecipes.INSTANCE.registerBaseRecipes();
		shapedRecipies();
		shapelessRecipies();
	}
	//GameRegistry.addRecipe(new ItemStack(RESULT), new Object[] {"CCC", "CCC", "CCC", 'C', VALUE OF C});
	public static void shapedRecipies()
	{
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemSummoningCore), new Object[] {"IDI", "DBD", "IDI", 'I', Items.iron_ingot, 'D', Items.diamond, 'B', Blocks.diamond_block});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemPassiveCraftingOrb), new Object[] {"GPG", "SDC", "GBG", 'G', Blocks.glass, 'P', Items.porkchop, 'S', Blocks.red_mushroom, 'C', Items.chicken, 'B', Items.beef, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemPassiveCraftingOrb), new Object[] {"GBG", "SDC", "GPG", 'G', Blocks.glass, 'P', Items.porkchop, 'S', Blocks.red_mushroom, 'C', Items.chicken, 'B', Items.beef, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemPassiveCraftingOrb), new Object[] {"GPG", "SDC", "GBG", 'G', Blocks.glass, 'P', Items.porkchop, 'S', Blocks.brown_mushroom, 'C', Items.chicken, 'B', Items.beef, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemPassiveCraftingOrb), new Object[] {"GBG", "SDC", "GPG", 'G', Blocks.glass, 'P', Items.porkchop, 'S', Blocks.brown_mushroom, 'C', Items.chicken, 'B', Items.beef, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemNeutralCraftingOrb), new Object[] {"GIG", "EDL", "GAG", 'G', Blocks.glass, 'I', Blocks.iron_block, 'E', Items.ender_pearl, 'D', Items.diamond, 'L', Items.lead, 'A', Items.gold_ingot});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemNeutralCraftingOrb), new Object[] {"GAG", "EDL", "GIG", 'G', Blocks.glass, 'I', Blocks.iron_block, 'E', Items.ender_pearl, 'D', Items.diamond, 'L', Items.lead, 'A', Items.gold_ingot});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemHostileCraftingOrb), new Object[] {"GFG", "BDE", "GSG", 'G', Blocks.glass, 'F', Items.rotten_flesh, 'B', Items.bone, 'E', Items.spider_eye, 'S', Items.gunpowder, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.itemHostileCraftingOrb), new Object[] {"GSG", "BDE", "GFG", 'G', Blocks.glass, 'F', Items.rotten_flesh, 'B', Items.bone, 'E', Items.spider_eye, 'S', Items.gunpowder, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(SummoningTable.blockSummoningTableIdle), new Object[] {"CCC", "PSH", "WNW", 'C', (new ItemStack(Blocks.carpet, 1, 14)), 'P', SummoningTable.itemPassiveCraftingOrb, 'S', SummoningTable.itemSummoningCore, 'H', SummoningTable.itemHostileCraftingOrb, 'W', Blocks.planks, 'N', SummoningTable.itemNeutralCraftingOrb});

	}
	
	public static void shapelessRecipies()
	{

	}
}
