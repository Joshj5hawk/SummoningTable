package com.joshj5hawk.main;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.joshj5hawk.block.BlockSummoningTable;
import com.joshj5hawk.handler.ConfigurationFile;
import com.joshj5hawk.handler.STFuelHandler;
import com.joshj5hawk.handler.STGUIHandler;
import com.joshj5hawk.item.ItemSummoningBookCow;
import com.joshj5hawk.lib.Strings;
import com.joshj5hawk.recipies.STCraftingRecipies;
import com.joshj5hawk.recipies.STSmeltingRecipies;
import com.joshj5hawk.tileentity.TileEntitySummoningTable;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Strings.modid, version = Strings.version)
public class SummoningTable 
{
	//Blocks
	public static Block blockSummoningTableIdle;
	public static Block blockSummoningTableActive;
	
	//Items
	public static Item itemSummoningBookCow;
	
	public static final int guiIDSummoningTable = 0;
	
	//Tabs
	public static CreativeTabs tabSummoningTable;
	
	@Instance(Strings.modid)
	public static SummoningTable instance;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent)
	{
		//Config
		ConfigurationFile.init(preEvent.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationFile());
		//CreativeTab
		tabSummoningTable = new CreativeTabs("tabSummoningTable")
		{
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem()
				{
					return Item.getItemFromBlock(SummoningTable.blockSummoningTableIdle);
				}
		};
		
		//Init
		//Blocks
		blockSummoningTableIdle = new BlockSummoningTable(false).setBlockName("blockSummoningTableIdle").setCreativeTab(tabSummoningTable).setHardness(3.5F);
		blockSummoningTableActive = new BlockSummoningTable(true).setBlockName("blockSummoningTableActive").setHardness(3.5F);
		
		//Items
		itemSummoningBookCow = new ItemSummoningBookCow().setUnlocalizedName("itemSummoningBookCow").setCreativeTab(tabSummoningTable);
		
		//Register
		//blocks
		GameRegistry.registerBlock(blockSummoningTableIdle, "blockSummoningTableIdle");
		GameRegistry.registerBlock(blockSummoningTableActive, "blockSummoningTableActive");
		
		//Items
		GameRegistry.registerItem(itemSummoningBookCow, "itemSummoningTableCow");
		
		//TileEntity
		GameRegistry.registerTileEntity(TileEntitySummoningTable.class, "summoningTable");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new STGUIHandler());
		//Crafting
		STCraftingRecipies.mainRegistry();
		STSmeltingRecipies.mainRegistry();
		//Fuel Handler
		GameRegistry.registerFuelHandler(new STFuelHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent)
	{
		
	}
	
}
