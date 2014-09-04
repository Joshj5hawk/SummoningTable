package com.joshj5hawk.crafting;

import static com.joshj5hawk.item.ItemSummoningBook.ENTITY_KEY;
import static com.joshj5hawk.item.ItemSummoningBook.USES_KEY;
import static com.joshj5hawk.item.ItemSummoningBook.getTag;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
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
        private ItemStack item1, item2;
        private ItemStack result;
        
        private SummoningRecipe(ItemStack item1, ItemStack item2, Class<? extends Entity> entityClass)
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
    
    private static List<SummoningRecipe> recipes;
    
    public static final SummoningRecipes INSTANCE = new SummoningRecipes();
    
	private SummoningRecipes()
	{
		recipes = new ArrayList<SummoningRecipe>();
	}
	
	public ItemStack getSummoningResult(ItemStack item, ItemStack item2)
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
	
    public static List<ItemStack> getAllResults()
    {
        ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
        for (SummoningRecipe r : recipes)
        {
            outputs.add(r.getResult());
        }
        return outputs;
    }
    
	/**
	 * Registers a new recipe using the two items given, resulting in a summoning book that spawns the specified entity
	 */
	public void registerRecipe(ItemStack item1, ItemStack item2, Class<? extends Entity> entityClass)
	{
	    recipes.add(new SummoningRecipe(item1, item2, entityClass));
	}
	
	/**
	 * Makes sure that the recipe matches regardless of the order of the items
	 */
	private boolean matchesEitherWay(SummoningRecipe recipe, ItemStack item1, ItemStack item2)
    {
	    return (recipe.item1.isItemEqual(item1) && recipe.item2.isItemEqual(item2)) || (recipe.item2.isItemEqual(item1) && recipe.item1.isItemEqual(item2)); 
    }

    //RECIPES HERE
	public void registerBaseRecipes()
	{	    
	    ItemStack mat = null;
		if(ConfigurationFile.easyMode == true)
		{
		    mat = new ItemStack(Items.diamond);
		}
		else
		{
		    mat = new ItemStack(SummoningTable.itemSummoningCore);
		}
		
        ItemStack out = new ItemStack(SummoningTable.itemSummoningBook);
        out.stackTagCompound = new NBTTagCompound();
        out.stackTagCompound.setInteger(USES_KEY, 64);

        //Passive Mobs
        registerRecipe(mat, new ItemStack(Items.leather), EntityCow.class);
        registerRecipe(mat, new ItemStack(Items.porkchop), EntityPig.class);
        registerRecipe(mat, new ItemStack(Item.getItemFromBlock(Blocks.wool)), EntitySheep.class);
        registerRecipe(mat, new ItemStack(Items.chicken), EntityChicken.class);
        registerRecipe(mat, new ItemStack(Item.getItemFromBlock(Blocks.red_mushroom)), EntityMooshroom.class);
        registerRecipe(mat, new ItemStack(Item.getItemFromBlock(Blocks.brown_mushroom)), EntityMooshroom.class);
        registerRecipe(mat, new ItemStack(Items.emerald), EntityVillager.class);
        //registerRecipe(mat, Items.snowball, EntitySnowman.class);
        registerRecipe(mat, new ItemStack(Items.apple), EntityBat.class);
        registerRecipe(mat, new ItemStack(Items.saddle), EntityHorse.class);
        registerRecipe(mat, new ItemStack(Items.fish), EntityOcelot.class);
        registerRecipe(mat, new ItemStack(Items.dye, 1, 0), EntitySquid.class);
        
        //Passive Mobs
        registerRecipe(mat, new ItemStack(Items.ender_pearl), EntityEnderman.class);
        registerRecipe(mat, new ItemStack(Items.gold_nugget), EntityPigZombie.class);
        //registerRecipe(mat, Item.getItemFromBlock(Blocks.iron_block), EntityIronGolem.class);
        registerRecipe(mat, new ItemStack(Items.cooked_beef), EntityWolf.class);
        
        //HostileMobs
        registerRecipe(mat, new ItemStack(Items.gunpowder), EntityCreeper.class);
        registerRecipe(mat, new ItemStack(Items.rotten_flesh), EntityZombie.class);
        registerRecipe(mat, new ItemStack(Items.string), EntitySpider.class);
        registerRecipe(mat, new ItemStack(Items.fermented_spider_eye), EntityCaveSpider.class);
        registerRecipe(mat, new ItemStack(Items.bone), EntitySkeleton.class);
        registerRecipe(mat, new ItemStack(Item.getItemFromBlock(Blocks.stonebrick)), EntitySilverfish.class);
        registerRecipe(mat, new ItemStack(Items.slime_ball), EntitySlime.class);
        registerRecipe(mat, new ItemStack(Items.magma_cream), EntityMagmaCube.class);
        registerRecipe(mat, new ItemStack(Items.blaze_rod), EntityBlaze.class);
        registerRecipe(mat, new ItemStack(Items.ghast_tear), EntityGhast.class);
        registerRecipe(mat, new ItemStack(Items.nether_wart), EntityWitch.class);
	}
	
	public static boolean usedInCrafting(ItemStack itemstack)
	{
		for(SummoningRecipe r : recipes)
		{
			if(itemstack.getItem() == r.item1.getItem() || (itemstack.getItem() == r.item2.getItem()))
			{
				return true;
			}
		}
		return false;
	}
}
