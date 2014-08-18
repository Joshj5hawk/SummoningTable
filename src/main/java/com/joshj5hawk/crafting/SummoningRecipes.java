package com.joshj5hawk.crafting;

import static com.joshj5hawk.item.ItemSummoningBook.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.joshj5hawk.handler.ConfigurationFile;
import com.joshj5hawk.main.SummoningTable;

public class SummoningRecipes
{
    private static class SummoningRecipe
    {
        private Item item1, item2;
        private ItemStack result;
        
        private SummoningRecipe(Item item1, Item item2, Class<? extends Entity> entityClass)
        {
            this.item1 = item1;
            this.item2 = item2;
            ItemStack result = new ItemStack(SummoningTable.itemSummoningBook);
            getTag(result).setString(ENTITY_KEY, entityClass.getName());
            getTag(result).setInteger(USES_KEY, ConfigurationFile.maxUses);
            this.result = result;
        }
        
        private ItemStack getResult()
        {
            return result.copy();
        }
    }
    
    private List<SummoningRecipe> recipes;
    
    public static final SummoningRecipes INSTANCE = new SummoningRecipes();
    
	private SummoningRecipes()
	{
		recipes = new ArrayList<SummoningRecipe>();
	}
	
	public ItemStack getSummoningResult(Item item, Item item2)
	{
	    for (SummoningRecipe recipe : recipes)
	    {
	        if (matchesEitherWay(recipe, item, item2))
	        {
	            return recipe.getResult();
	        }
	    }
	    return null;
	}
	
	/**
	 * Registers a new recipe using the two items given, resulting in a summoning book that spawns the specified entity
	 */
	public void registerRecipe(Item item1, Item item2, Class<? extends Entity> entityClass)
	{
	    recipes.add(new SummoningRecipe(item1, item2, entityClass));
	}
	
	/**
	 * Makes sure that the recipe matches regardless of the order of the items
	 */
	private boolean matchesEitherWay(SummoningRecipe recipe, Item item1, Item item2)
    {
	    return (recipe.item1 == item1 && recipe.item2 == item2) || (recipe.item2 == item1 && recipe.item1 == item2); 
    }

    //RECIPES HERE
	public void registerBaseRecipes()
	{	    
	    Item mat = null;
		if(ConfigurationFile.easyMode == true)
		{
		    mat = Items.diamond;
		}
		else
		{
		    mat = SummoningTable.itemSummoningCore;
		}
		
        ItemStack out = new ItemStack(SummoningTable.itemSummoningBook);
        out.stackTagCompound = new NBTTagCompound();
        out.stackTagCompound.setInteger(USES_KEY, 64);

        registerRecipe(mat, Items.leather, EntityCow.class);
        registerRecipe(mat, Items.porkchop, EntityPig.class);
        registerRecipe(mat, Item.getItemFromBlock(Blocks.wool), EntitySheep.class);

        // Here's the rest of the recipes, not converted to the new form yet.
        // Items.diamond should be replaced with mat
        // itemSummoningBook[EntityName] should be replaced with [EntityName].class
        
//			if(item == Items.diamond && item2 == Items.chicken || item == Items.chicken && item2 == Items.diamond)
//				return new ItemStack(SummoningTable.itemSummoningBookChicken);
//			if(item == Items.diamond && item2 == Items.mushroom_stew || item == Items.mushroom_stew && item2 == Items.diamond)
//				return new ItemStack(SummoningTable.itemSummoningBookMooshroom);
//			if(item == Items.diamond && item2 == Items.emerald || item == Items.emerald && item2 == Items.diamond)
//				return new ItemStack(SummoningTable.itemSummoningBookVillager);
//			if(item == Items.diamond && item2 == Items.snowball || item == Items.snowball && item2 == Items.diamond)
//				return new ItemStack(SummoningTable.itemSummoningBookSnowGolem);
//			if(item == Items.diamond && item2 == Items.apple || item == Items.apple && item2 == Items.diamond)
//				return new ItemStack(SummoningTable.itemSummoningBookBat);
//			if(item == Items.diamond && item2 == Items.saddle || item == Items.saddle && item2 == Items.diamond)
//				return new ItemStack(SummoningTable.itemSummoningBookHorse);
//			if(item == Items.diamond && item2 == Items.fish || item == Items.fish && item2 == Items.diamond)
//				return new ItemStack(SummoningTable.itemSummoningBookOcelot);
//			if(item == Items.diamond && item2 == Items.dye || item == Items.emerald && item2 == Items.diamond)
//				return new ItemStack(SummoningTable.itemSummoningBookSquid);
//			
//			
//			return null;
//		}
	}
}
