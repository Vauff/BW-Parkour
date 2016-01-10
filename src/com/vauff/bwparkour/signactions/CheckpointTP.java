package com.vauff.bwparkour.signactions;

import org.bukkit.ChatColor;
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
			// do stuff
		}
		else
		{
			player.sendMessage(ChatColor.RED + "There is not a parkour arena named " + line3 + "!");
		}
	}
}
