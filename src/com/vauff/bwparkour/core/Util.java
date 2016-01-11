package com.vauff.bwparkour.core;

import java.util.HashMap;

public class Util
{
	public static HashMap<String, String> arenadata = new HashMap();
	public static HashMap<String, String> checkpointdata = new HashMap();
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
}
