package com.vauff.bwparkour.core;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.Files;

import com.vauff.bwparkour.commands.BWPAbout;
import com.vauff.bwparkour.listeners.SignListener;

public class Main extends JavaPlugin
{
	public static String version = "0.1";

	public void onEnable()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(new SignListener(this), this);
		getCommand("bwpabout").setExecutor(new BWPAbout());

		if (getConfig().getInt("dont-ever-change-this") != 1)
		{
			ConsoleCommandSender console = getServer().getConsoleSender();
			File config = new File(getDataFolder(), "config.yml");
			File configbackup = new File(getDataFolder(), "config-backup.yml");

			console.sendMessage("[BW-Parkour]" + ChatColor.DARK_RED + " IMPORTANT:");
			console.sendMessage("[BW-Parkour]" + ChatColor.RED + " Your configuration has been reset due to an update that added new configuration options");
			console.sendMessage("[BW-Parkour]" + ChatColor.RED + " Your old configuration file is stored in config-backup.yml");
			console.sendMessage("[BW-Parkour]" + ChatColor.RED + " You can use this file as reference to get back your config to how it was before");

			try
			{
				Files.copy(config, configbackup);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			config.delete();
			saveDefaultConfig();
		}
	}
}