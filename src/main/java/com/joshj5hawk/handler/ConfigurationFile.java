package com.joshj5hawk.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.joshj5hawk.lib.Strings;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationFile 
{
	public static Configuration config;
	public static boolean easyMode = true;
	public static boolean debugMode = false;
	public static boolean lowRes = false;
	
	public static void init(File configFile)
	{
		//Creates Config Files
		if(config == null)
		{
			config = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration()
	{
		easyMode = config.getBoolean("easyMode", Configuration.CATEGORY_GENERAL, false, "Easy recipes (iron, gold, diamond, emerald)?");
		debugMode = config.getBoolean("debugMode", Configuration.CATEGORY_GENERAL, false, "Print Certain info to console");
		lowRes = config.getBoolean("lowRes", Configuration.CATEGORY_GENERAL, false, "Low res, 16x16 textures");
		
		if(config.hasChanged())
		{
			config.save();
		}
	}
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Strings.modid))
		{
			loadConfiguration();
		}
	}
	
}
