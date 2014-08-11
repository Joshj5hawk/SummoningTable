package com.joshj5hawk.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.joshj5hawk.handler.ConfigurationFile;
import com.joshj5hawk.main.SummoningTable;

public class ItemSummoningBook extends Item
{
	public ItemSummoningBook()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(63);
		this.setCreativeTab(SummoningTable.tabSummoningTable);
	}
	
	public void addInformation(List list)
	{
		list.add("Full Summons 64 Cows");
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int i, float f1, float f2, float f3)
	{
		if(world.isRemote)
		{
			return true;
		}
		else
		{
			Block block = world.getBlock(x, y, z);
			x += Facing.offsetsXForSide[i];
			y += Facing.offsetsYForSide[i];
			z += Facing.offsetsZForSide[i];
			double d0 = 0.0D;
			
			if(i == 1 && block.getRenderType() == 11)
			{
				d0 = 0.5D;
			}
			
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookCow.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 92, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Cow");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookPig.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 90, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Pig");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookSheep.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 91, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Sheep");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookChicken.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 93, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Chicken");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookMooshroom.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 96, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);

				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Mooshroom");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookVillager.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 120,(double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Villager");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookSnowGolem.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 97, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);

				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Snow Golem");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookBat.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 65, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Bat");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookHorse.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 100, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Horse");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookOcelot.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 98, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Ocelot");
				}
			}
			if(itemstack.getUnlocalizedName().equals(SummoningTable.itemSummoningBookSquid.getUnlocalizedName()))
			{
				Entity entity = spawnCreature(world, 99, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
				spawn(itemstack, entity, player);
				
				if(ConfigurationFile.debugMode == true)
				{
					System.out.println("Spawned Squid");
				}
			}
		}
		return true;
	}
	
	public static void spawn(ItemStack itemstack, Entity entity, EntityPlayer player)
	{
		if(entity != null)
			{
				if(entity instanceof EntityLivingBase && itemstack.hasDisplayName())
				{
					((EntityLiving)entity).setCustomNameTag(itemstack.getDisplayName());
				}
				
				if(!player.capabilities.isCreativeMode)
				{
					itemstack.damageItem(1, player);
					System.out.println("Damaged Item");
				}
			}
	}
	public static Entity spawnCreature(World world, int entityID, double x, double y, double z)
	{
		Entity entity = null;
		
		for (int j = 0; j < 1; ++j)
		{
			entity  = EntityList.createEntityByID(entityID, world);
			
			if(entity != null && entity instanceof EntityLivingBase)
			{
				EntityLiving entityLiving = (EntityLiving)entity;
				entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
				entityLiving.rotationYawHead = entityLiving.rotationYaw;
				entityLiving.renderYawOffset = entityLiving.rotationYaw;
				entityLiving.onSpawnWithEgg((IEntityLivingData)null);
				world.spawnEntityInWorld(entity);
				entityLiving.playLivingSound();
			}
		}
		return entity;
	}
}
