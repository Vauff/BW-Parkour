package com.vauff.icparkour.signactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.vauff.icparkour.core.Main;
import com.vauff.icparkour.core.Util;

public class Finish
{
	public static void finish(Player player, Main main, Sign sign)
	{
		String line3 = sign.getLine(2);

		if (Util.doesArenaExist(line3))
		{
			if (Util.arenaData.containsKey(player.getName()))
			{
				if (Util.arenaData.get(player.getName()).equalsIgnoreCase(line3))
				{
					int reward = main.getConfig().getConfigurationSection("arenas").getConfigurationSection(line3).getInt("reward");
					Location spawn = Util.getCenter(Bukkit.getWorld(main.getConfig().getString("spawn-world-name")).getSpawnLocation());
					
					Util.arenaData.remove(player.getName());
					player.teleport(spawn);
					main.econ.depositPlayer(player, reward);
					Util.checkpointData.remove(player.getName());
					player.sendMessage(ChatColor.GREEN + "You have finished the parkour arena " + line3 + " and received $" + reward + "! Teleporting you to spawn.");
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
