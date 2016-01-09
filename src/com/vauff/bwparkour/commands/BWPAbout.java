package com.vauff.bwparkour.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.vauff.bwparkour.core.Main;

public class BWPAbout implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		if (commandlabel.equalsIgnoreCase("bwpabout"))
		{
			if (sender.hasPermission("bwparkour.about"))
			{
				sender.sendMessage(ChatColor.GREEN + "You are using BW-Parkour " + ChatColor.RED + "v" + Main.version + ChatColor.GREEN + " created by Vauff");
				sender.sendMessage(ChatColor.AQUA + "https://github.com/Vauff/BW-Parkour");
			}
			else
			{
				sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
			}
		}
		return true;
	}
}