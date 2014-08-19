package com.joshj5hawk.slot;

import com.joshj5hawk.main.SummoningTable;
import com.joshj5hawk.tileentity.TileEntitySummoningTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSummoningTable extends Slot 
{
	public SlotSummoningTable(EntityPlayer player, IInventory iinventory, int i, int j, int k)
	{
		super(iinventory, i, j, k);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}

}
