package com.joshj5hawk.main;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.joshj5hawk.block.BlockSummoningTable;
import com.joshj5hawk.handler.ConfigurationFile;
import com.joshj5hawk.handler.STFuelHandler;
import com.joshj5hawk.handler.STGUIHandler;
import com.joshj5hawk.item.ItemSummoningBook;
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
	//Summoning Books
	public static Item itemSummoningBook;
	
	public static Item itemSummoningBookCow;
	public static Item itemSummoningBookPig;
	public static Item itemSummoningBookSheep;
	public static Item itemSummoningBookChicken;
	public static Item itemSummoningBookMooshroom;
	public static Item itemSummoningBookVillager;
	public static Item itemSummoningBookSnowGolem;
	public static Item itemSummoningBookBat;
	public static Item itemSummoningBookHorse;
	public static Item itemSummoningBookOcelot;
	public static Item itemSummoningBookSquid;
	//Crafting Orbs
	public static Item itemPassiveCraftingOrb;
	public static Item itemNeutralCraftingOrb;
	public static Item itemHostileCraftingOrb;
	public static Item itemSummoningCore;
	
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
		//Summoning Books
		itemSummoningBookCow = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookCow").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconCow16" : Strings.modid + ":iconCow64");
		itemSummoningBookPig = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookPig").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconPig16" : Strings.modid + ":iconPig64");
		itemSummoningBookSheep = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookSheep").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconSheep16" : Strings.modid + ":iconSheep64");
		itemSummoningBookChicken = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookChicken").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconChicken16" : Strings.modid + ":iconChicken64");
		itemSummoningBookMooshroom = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookMooshroom").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconMooshroom16" : Strings.modid + ":iconMooshroom64");
		itemSummoningBookVillager = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookVillager").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconVillager16" : Strings.modid + ":iconVillager64");
		itemSummoningBookSnowGolem = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookSnowGolem").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconSnowGolem16" : Strings.modid + ":iconSnowGolem64");
		itemSummoningBookBat = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookBat").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconBat16" : Strings.modid + ":iconBat64");
		itemSummoningBookHorse = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookHorse").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconHorse16" : Strings.modid + ":iconHorse64");
		itemSummoningBookOcelot = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookOcelot").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconOcelot16" : Strings.modid + ":iconOcelot64");
		itemSummoningBookSquid = new ItemSummoningBook().setUnlocalizedName("itemSummoningBookSquid").setTextureName(ConfigurationFile.lowRes ? Strings.modid + ":iconSquid16" : Strings.modid + ":iconSquid64");
		
		//Crafting Orbs
		itemPassiveCraftingOrb = new Item().setUnlocalizedName("itemPassiveCraftingOrb").setCreativeTab(tabSummoningTable).setTextureName(ConfigurationFile.lowRes ? Strings.modid + "iconPassiveOrb16" : Strings.modid + ":iconPassiveOrb64");
		itemNeutralCraftingOrb = new Item().setUnlocalizedName("itemNeutralCraftingOrb").setCreativeTab(tabSummoningTable).setTextureName(ConfigurationFile.lowRes ? Strings.modid + "iconNeutralOrb16" : Strings.modid + ":iconNeutralOrb64");
		itemHostileCraftingOrb = new Item().setUnlocalizedName("itemHostileCraftingOrb").setCreativeTab(tabSummoningTable).setTextureName(ConfigurationFile.lowRes ? Strings.modid + "iconHostileOrb16" : Strings.modid + ":iconHostileOrb64");
		itemSummoningCore = new Item().setUnlocalizedName("itemSummoningCore").setCreativeTab(tabSummoningTable).setTextureName(ConfigurationFile.lowRes ? Strings.modid + "iconSummoningCore16" : Strings.modid + ":iconSummoningCore64");
		
		//Register
		//blocks
		GameRegistry.registerBlock(blockSummoningTableIdle, "blockSummoningTableIdle");
		GameRegistry.registerBlock(blockSummoningTableActive, "blockSummoningTableActive");
		
		//Items
		GameRegistry.registerItem(itemSummoningBookCow, "itemSummoningBookCow");
		GameRegistry.registerItem(itemSummoningBookPig, "itemSummoningBookPig");
		GameRegistry.registerItem(itemSummoningBookSheep, "itemSummoningBookSheep");
		GameRegistry.registerItem(itemSummoningBookChicken, "itemSummoningBookChicken");
		GameRegistry.registerItem(itemSummoningBookMooshroom, "itemSummoningBookMooshroom");
		GameRegistry.registerItem(itemSummoningBookVillager, "itemSummoningBookVillager");
		GameRegistry.registerItem(itemSummoningBookSnowGolem, "itemSummoningBookSnwoGolem");
		GameRegistry.registerItem(itemSummoningBookBat, "itemSummoningBookBat");
		GameRegistry.registerItem(itemSummoningBookHorse, "itemSummoningBookHorse");
		GameRegistry.registerItem(itemSummoningBookOcelot, "itemSummoningBookOcelot");
		GameRegistry.registerItem(itemSummoningBookSquid, "itemSummoningBookSquid");
		
		GameRegistry.registerItem(itemPassiveCraftingOrb, "itemPassiveCraftingOrb");
		GameRegistry.registerItem(itemNeutralCraftingOrb, "itemNeutralCraftingOrb");
		GameRegistry.registerItem(itemHostileCraftingOrb, "itemHostileCraftingOrb");
		
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
