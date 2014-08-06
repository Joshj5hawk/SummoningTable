package com.joshj5hawk.handler;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class STFuelHandler implements IFuelHandler 
{

	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		return 0;
	}
	
}
