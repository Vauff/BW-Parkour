package com.vauff.icparkour.signactions;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.vauff.icparkour.core.Main;
import com.vauff.icparkour.core.Util;

public class Checkpoint
{
	public static void set(Player player, Main main, Sign sign)
	{
		String line3 = sign.getLine(2);
		String line4 = sign.getLine(3);

		if (Util.doesArenaExist(line3))
		{
			if (Util.arenaData.containsKey(player.getName()))
			{
				if (Util.arenaData.get(player.getName()).equalsIgnoreCase(line3))
				{
					if (line4.trim().length() > 0)
					{
						if (Util.doesCheckPointExist(line3, line4))
						{
							if (Util.checkpointData.containsKey(player.getName()))
							{
								if (!Util.checkpointData.get(player.getName()).equalsIgnoreCase(line4))
								{
									Util.checkpointData.put(player.getName(), line4);
									player.sendMessage(ChatColor.GREEN + "Your latest checkpoint has been set to " + line4 + "!");
								}
								else
								{
									player.sendMessage(ChatColor.RED + "The checkpoint " + line4 + " is already the checkpoint you have set!");
								}
							}
							else
							{
								Util.checkpointData.put(player.getName(), line4);
								player.sendMessage(ChatColor.GREEN + "Your latest checkpoint has been set to " + line4 + "!");
							}
						}
						else
						{
							player.sendMessage(ChatColor.RED + "The checkpoint " + line4 + " does not exist for the arena " + line3 + "!");
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "There is not a checkpoint specified!");
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
