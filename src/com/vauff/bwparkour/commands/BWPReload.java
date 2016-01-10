package com.vauff.bwparkour.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.vauff.bwparkour.core.Main;

public class BWPReload implements CommandExecutor
{
	private Main main;

	public BWPReload(Main plugin)
	{
		main = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		if (commandlabel.equalsIgnoreCase("bwpreload"))
		{
			if (sender.hasPermission("bwparkour.reload"))
			{
				main.reloadConfig();
				sender.sendMessage(ChatColor.AQUA + "BW-Parkour configuration file reloaded successfully!");
			}
			else
			{
				sender.sendMessage(ChatColor.DARK_RED + "You do not have acccess to that command.");
			}
		}
		return true;
	}
}