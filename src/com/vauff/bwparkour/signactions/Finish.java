package com.vauff.bwparkour.signactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.vauff.bwparkour.core.Main;
import com.vauff.bwparkour.core.Util;

public class Finish
{
	public static void finish(Player player, Main main, Sign sign)
	{
		String line3 = sign.getLine(2);

		if (Util.doesArenaExist(line3))
		{
			if (Util.arenadata.containsKey(player.getName()))
			{
				if (Util.arenadata.get(player.getName()).equalsIgnoreCase(line3))
				{
					int reward = main.getConfig().getConfigurationSection("arenas").getConfigurationSection(line3).getInt("reward");
					player.sendMessage(ChatColor.GREEN + "You have finished the parkour arena " + line3 + " and received $" + reward + "! Teleporting you to spawn.");
					Util.arenadata.remove(player.getName());
					player.teleport(Bukkit.getWorld(main.getConfig().getString("world-name")).getSpawnLocation());
					main.econ.depositPlayer(player, reward);
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You are not in the parkour arena " + line3 + ", you are in " + Util.arenadata.get(player.getName()) + "!");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You are not in a parkour arena!");
			}
		}
		else
		{
			if (line3.trim().length() > 0)
			{
				player.sendMessage(ChatColor.RED + "There is not a parkour arena named " + line3 + "!");
			}
			else
			{
				player.sendMessage(ChatColor.RED + "There is not a parkour arena name specified!");
			}
		}
	}
}
