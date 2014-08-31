package com.joshj5hawk.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import com.joshj5hawk.crafting.SummoningRecipes;
import com.joshj5hawk.slot.SlotSummoningTable;
import com.joshj5hawk.tileentity.TileEntitySummoningTable;;

public class ContainerSummoningTable extends Container {

	private TileEntitySummoningTable summoning;
	private int dualCookTime;
	private int dualFuel;
	private int lastItemBurnTime;

	public ContainerSummoningTable(InventoryPlayer invPlayer, TileEntitySummoningTable teSummoningTable) {
		dualCookTime = 0;
		dualFuel = 0;
		lastItemBurnTime = 0;

		summoning = teSummoningTable;

		
		this.addSlotToContainer(new Slot(teSummoningTable, 0, 44, 63)); //Input A
		this.addSlotToContainer(new Slot(teSummoningTable, 1, 80, 36)); //Input B
		this.addSlotToContainer(new Slot(teSummoningTable, 2, 80, 7)); //Fuel
		this.addSlotToContainer(new SlotSummoningTable(invPlayer.player, teSummoningTable, 3, 116, 63)); //OUTPUT

		//Inventory
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		//HotBar
		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}

	public void addCraftingToCrafters (ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.summoning.dualCookTime);
		crafting.sendProgressBarUpdate(this, 1, this.summoning.dualFuel);
	}

	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int theSlot)
	{
		return null;
	}


	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return summoning.isUseableByPlayer(player);
	}
	
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting par1 = (ICrafting)this.crafters.get(i);
			
			if(this.dualCookTime != this.summoning.dualCookTime)
			{
				par1.sendProgressBarUpdate(this, 0, this.summoning.dualCookTime);
			}
			if(this.dualFuel != this.summoning.dualFuel)
			{
				par1.sendProgressBarUpdate(this, 1, this.summoning.dualFuel);
			}
		}
		
		this.dualCookTime = this.summoning.dualCookTime;
		this.dualFuel = this.summoning.dualFuel;
	}
	
	public void updateProgressBar(int i, int j)
	{
		if(i == 0)
		{
			summoning.dualCookTime = j;
		}
		if(i == 1)
		{
			summoning.dualFuel = j;
		}
	}
}