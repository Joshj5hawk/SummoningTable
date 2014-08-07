package com.joshj5hawk.item;

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

public class ItemSummoningBookCow extends Item
{

	public ItemSummoningBookCow()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(63);
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
			
			Entity entity = spawnCreature(world, 92, (double)x + 0.5D, (double)y + d0, (double)z + 0.5D);
			
			if(entity != null)
			{
				if(entity instanceof EntityLivingBase && itemstack.hasDisplayName())
				{
					((EntityLiving)entity).setCustomNameTag(itemstack.getDisplayName());
				}
				
				if(!player.capabilities.isCreativeMode)
				{
					itemstack.damageItem(1, player);
				}
			}
		}
		return true;
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
