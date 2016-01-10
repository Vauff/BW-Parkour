package com.vauff.bwparkour.core;

public class Util
{
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
