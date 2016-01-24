package com.vauff.bwparkour.signactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.vauff.bwparkour.core.Main;
import com.vauff.bwparkour.core.Util;

public class Enter
{
	public static void enter(Player player, Main main, Sign sign)
	{
		String line3 = sign.getLine(2);

		if (Util.doesArenaExist(line3))
		{
			if (!Util.arenaData.containsKey(player.getName()))
			{
				String[] coordinates = main.getConfig().getConfigurationSection("arenas").getConfigurationSection(line3).getString("coordinates").split(",");
				Location arena = Util.getCenter(new Location(Bukkit.getWorld(main.getConfig().getConfigurationSection("arenas").getConfigurationSection(line3).getString("world-name")), Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));
				
				if (main.getConfig().getBoolean("block-world"))
				{
					player.getInventory().clear();
					player.updateInventory();
				}

				Util.arenaData.put(player.getName(), line3);
				player.teleport(arena);
				Util.checkpointData.remove(player.getName());
				player.sendMessage(ChatColor.GREEN + "You have entered the parkour arena named " + line3 + "!");
			}

			else if (!Util.arenaData.get(player.getName()).equalsIgnoreCase(line3))
			{
				player.sendMessage(ChatColor.RED + "You can't enter this arena because you are already in the parkour arena " + Util.arenaData.get(player.getName()) + "!");
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You are already in this parkour arena!");
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
