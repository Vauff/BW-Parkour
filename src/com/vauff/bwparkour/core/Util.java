package com.vauff.bwparkour.core;

import java.util.HashMap;

public class Util
{
	public static HashMap<String, String> arenadata = new HashMap<String, String>();
	public static HashMap<String, String> checkpointdata = new HashMap<String, String>();
	private static Main main;

	public Util(Main plugin)
	{
		main = plugin;
	}

	public static boolean doesArenaExist(String arenaname)
	{
		boolean arenafound = false;

		for (String configarenaname : main.getConfig().getConfigurationSection("arenas").getKeys(false))
		{
			if (arenaname.equalsIgnoreCase(configarenaname))
			{
				arenafound = true;
			}
		}

		return arenafound;
	}

	public static boolean doesCheckPointExist(String arenaname, String checkpointname)
	{
		boolean checkpointfound = false;
		
		for (String configcheckpointname : main.getConfig().getConfigurationSection("arenas").getConfigurationSection(arenaname).getConfigurationSection("checkpoints").getKeys(false))
		{
			if (checkpointname.equalsIgnoreCase(configcheckpointname))
			{
				checkpointfound = true;
			}
		}
		
		return checkpointfound;
	}
}
