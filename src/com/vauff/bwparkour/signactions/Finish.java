package com.vauff.bwparkour.signactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.vauff.bwparkour.core.Main;
import com.vauff.bwparkour.core.Util;

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

					if (main.getConfig().getBoolean("block-world"))
					{
						player.getInventory().setItem(4, new ItemStack(Material.WATCH, 1));
						player.updateInventory();
					}

					Util.arenaData.remove(player.getName());
					player.teleport(Bukkit.getWorld(main.getConfig().getString("spawn-world-name")).getSpawnLocation());
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
