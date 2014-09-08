package com.joshj5hawk.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.joshj5hawk.lib.Strings;
import com.joshj5hawk.main.SummoningTable;
import com.joshj5hawk.tileentity.TileEntitySummoningTable;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSummoningTable extends BlockContainer
{

	private Random rand;
	private final boolean isActive;
	private static boolean keepInventory = true;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public BlockSummoningTable(boolean blockState) 
	{
		super(Material.rock);
		rand = new Random();
		isActive = blockState;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
	}

	public int getRenderType()
	{
		return -1;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	/*@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(Strings.modid + ":iconTableSide");
		this.iconTop = iconRegister.registerIcon(Strings.modid + ":iconTableTop");
	}*/
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? this.iconTop : (side == 0 ? Blocks.planks.getBlockTextureFromSide(side) : this.blockIcon);
		//return meta == 0 && side == 3 ? this.iconTop : (side == meta ? this.iconTop : this.blockIcon);
	}
	
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) 
	{
		if(!world.isRemote)
		{
			Block b1 = world.getBlock(x, y, z - 1);
			Block b2 = world.getBlock(x, y, z + 1);
			Block b3 = world.getBlock(x -1, y, z);
			Block b4 = world.getBlock(x + 1, y, z);
			
			byte b0 = 3;
			
			if(b1.func_149730_j() && !b2.func_149730_j())
			{
				b0 = 3;
			}
			
			if(b2.func_149730_j() && !b1.func_149730_j())
			{
				b0 = 2;
			}
			
			if(b3.func_149730_j() && !b4.func_149730_j())
			{
				b0 = 5;
			}
			
			if(b4.func_149730_j() && !b3.func_149730_j())
			{
				b0 = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityPlayer, ItemStack is)
	{
		int l = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if(l  == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		
		if(l  == 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		
		if(l  == 2)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		
		if(l  == 3)
		{
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		
		if(is.hasDisplayName())
		{
			((TileEntitySummoningTable)world.getTileEntity(x, y, z)).setGuiDisplayName(is.getDisplayName());
		}
	}
		
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			return true;
		}
		else if(!player.isSneaking())
		{
			TileEntitySummoningTable entity = (TileEntitySummoningTable) world.getTileEntity(x, y, z);
			if(entity != null)
			{
				FMLNetworkHandler.openGui(player, SummoningTable.instance, SummoningTable.guiIDSummoningTable, world, x, y, z);
			}
			return true;
		}
		else
		{
			return false;
		}
	
	}
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) 
	{
		return new TileEntitySummoningTable();
	}

	public static void updateBlockState(boolean isSummoning, World world, int xCoord, int yCoord, int zCoord) 
	{
		int i = world.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity entity = world.getTileEntity(xCoord, yCoord, zCoord);
		
		keepInventory = true;
		
		if(isSummoning)
		{
			world.setBlock(xCoord, yCoord, zCoord, SummoningTable.blockSummoningTableActive);
		}
		else
		{
			world.setBlock(xCoord, yCoord, zCoord, SummoningTable.blockSummoningTableIdle);
		}
		keepInventory = false;
		world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		
		if(entity != null)
		{
			entity.validate();
			world.setTileEntity(xCoord, yCoord, zCoord, entity);
		}
	}
	public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata) 
	{
		if(!keepInventory) 
		{
			TileEntitySummoningTable tileentity = (TileEntitySummoningTable) world.getTileEntity(x, y, z);

			if(tileentity != null) 
			{
				for(int i = 0; i < tileentity.getSizeInventory(); i++) 
				{
					ItemStack itemstack = tileentity.getStackInSlot(i);

					if(itemstack != null) 
					{
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

						while(itemstack.stackSize > 0)
						{
							int j = this.rand.nextInt(21) + 10;

							if(j > itemstack.stackSize) 
							{
								j = itemstack.stackSize;
							}

							itemstack.stackSize -= j;

							EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

							if(itemstack.hasTagCompound())
							{
								item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
							}

							world.spawnEntityInWorld(item);
						}
					}
				}

				world.func_147453_f(x, y, z, oldblock);
			}
		}

		super.breakBlock(world, x, y, z, oldblock, oldMetadata);
	}
	
}
