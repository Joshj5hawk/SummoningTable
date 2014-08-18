package com.joshj5hawk.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
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

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
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