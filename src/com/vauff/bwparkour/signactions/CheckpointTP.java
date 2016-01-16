package com.vauff.bwparkour.signactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.vauff.bwparkour.core.Main;
import com.vauff.bwparkour.core.Util;

public class CheckpointTP
{
	public static void tp(Player player, Main main, Sign sign)
	{
		String line3 = sign.getLine(2);

		if (Util.doesArenaExist(line3))
		{
			if (Util.arenaData.containsKey(player.getName()))
			{
				if (Util.arenaData.get(player.getName()).equalsIgnoreCase(line3))
				{
					if (Util.checkpointData.containsKey(player.getName()))
					{
						String[] coordinates = main.getConfig().getConfigurationSection("arenas").getConfigurationSection(line3).getConfigurationSection("checkpoints").getString(Util.checkpointData.get(player.getName())).split(",");
						player.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("world-name")), Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));
						player.sendMessage(ChatColor.GREEN + "You have been teleported to your latest checkpoint named " + Util.checkpointData.get(player.getName()) + "!");
					}
					else
					{
						player.sendMessage(ChatColor.RED + "You do not currently have a checkpoint set on the arena " + line3 + "!");
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You are not in the parkour arena " + line3 + ", you are in " + Util.arenaData.get(player.getName()) + "!");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You are not in the parkour arena " + line3 + "!");
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
