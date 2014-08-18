package com.joshj5hawk.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.joshj5hawk.crafting.SummoningRecipes;
import com.joshj5hawk.handler.ConfigurationFile;
import com.joshj5hawk.lib.Strings;
import com.joshj5hawk.main.SummoningTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSummoningBook extends Item
{
    @SideOnly(Side.CLIENT)
    private IIcon base;
    @SideOnly(Side.CLIENT)
    private IIcon overlay;
    @SideOnly(Side.CLIENT)
    private IIcon pages;
    
	public ItemSummoningBook()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(SummoningTable.tabSummoningTable);
	}
	
	// NBT keys
	public static final String USES_KEY = "usesLeft";
	public static final String ENTITY_KEY = "entityKey";
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean held)
    {
        list.add(StatCollector.translateToLocal("st.tooltip.spawns") + ": " + EnumChatFormatting.YELLOW + getSimpleEntityName(getTag(stack).getString(ENTITY_KEY)));
        list.add(StatCollector.translateToLocal("st.tooltip.usesLeft") + ": " + EnumChatFormatting.WHITE +  getTag(stack).getInteger(USES_KEY));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        list.addAll(SummoningRecipes.INSTANCE.getAllResults());
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
			double yOffset = 0.0D;
			
			if(i == 1 && block.getRenderType() == 11)
			{
				yOffset = 0.5D;
			}
			
			// creates an entity from its simple name
			Entity entity = EntityList.createEntityByName(getSimpleEntityName(getTag(itemstack).getString(ENTITY_KEY)), world);
			
			// put it where we want and make it face forward
			entity.setLocationAndAngles(x + 0.5, y + yOffset, z + 0.5, 0, 0);
			
			// put it in the world
			spawn(itemstack, entity, player);
		}
		return true;
	}
	
	public static void spawn(ItemStack itemstack, Entity entity, EntityPlayer player)
	{
		if(entity != null)
        {
            if (entity instanceof EntityLivingBase && itemstack.hasDisplayName())
            {
                ((EntityLiving) entity).setCustomNameTag(itemstack.getDisplayName());
            }

            player.worldObj.spawnEntityInWorld(entity);

            if (!player.capabilities.isCreativeMode)
            {
                int currentUses = getTag(itemstack).getInteger(USES_KEY);
                int nextUses = currentUses - 1;
                
                if (nextUses <= 0)
                {
                    // delete the item from the inv when out of uses
                    player.inventory.decrStackSize(player.inventory.currentItem, 1);
                }
                else
                {
                    // decrement uses left
                    getTag(itemstack).setInteger(USES_KEY, nextUses);
                }
            }
        }
	}
	
	public static NBTTagCompound getTag(ItemStack stack)
	{
	    if (stack.getTagCompound() == null)
	    {
	        stack.stackTagCompound = new NBTTagCompound();
	        stack.stackTagCompound.setInteger(USES_KEY, ConfigurationFile.maxUses);
	        stack.stackTagCompound.setString(ENTITY_KEY, EntityPig.class.getName());
	    }
	    return stack.stackTagCompound;
	}
	
	public static String getSimpleEntityName(String className)
	{
	    try
        {
            return (String) EntityList.classToStringMapping.get(Class.forName(className));
        }
        catch (ClassNotFoundException e)
        {
            return "ERROR"; // this *shouldn't* happen, but that's what they all say.
        }
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
	    // register all 3 textures
	    String suffix = ConfigurationFile.lowRes ? "16" : "64";
	    base = register.registerIcon(Strings.modid + ":" + "book_base" + suffix);
	    overlay = register.registerIcon(Strings.modid + ":" + "book_overlay" + suffix);
	    pages = register.registerIcon(Strings.modid + ":" + "book_pages" + suffix);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
	    return true;
	}
	
	@Override
	public int getRenderPasses(int metadata)
	{
	    return 3;
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
	    // if first pass, the base, second pass is the overlay, and finally the (uncolored) pages of the book
	    return pass == 0 ? base : pass == 1 ? overlay : pages;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
	    if (pass == 2)
	    {
	        return 0xFFFFFF;
	    }
	    else
	    {
	        // this is a mess
	        EntityEggInfo info = (EntityEggInfo) EntityList.entityEggs.get(EntityList.getEntityID(EntityList.createEntityByName(getSimpleEntityName(getTag(stack).getString(ENTITY_KEY)), Minecraft.getMinecraft().theWorld)));
	        return pass == 0 ? info.primaryColor : info.secondaryColor;
	    }
	}
}
