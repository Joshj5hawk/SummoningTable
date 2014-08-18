package com.joshj5hawk.slot;

import com.joshj5hawk.tileentity.TileEntitySummoningTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotSummoningTable extends Slot 
{
	public SlotSummoningTable(EntityPlayer player, IInventory iinventory, int i, int j, int k)
	{
		super(iinventory, i, j, k);
	}

}
