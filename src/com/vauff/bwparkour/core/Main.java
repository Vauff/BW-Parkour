package com.vauff.bwparkour.core;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.Files;

import com.vauff.bwparkour.commands.BWP;
import com.vauff.bwparkour.listeners.*;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin
{
	public static String version = "1.1-dev";
	public Economy econ;

	public void onEnable()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();
		new Util(this);
		getServer().getPluginManager().registerEvents(new CommandListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
		getServer().getPluginManager().registerEvents(new SignClickListener(this), this);
		getServer().getPluginManager().registerEvents(new SignChangeListener(), this);
		getCommand("bwp").setExecutor(new BWP(this));

		if (getConfig().getInt("dont-ever-change-this") != 1)
		{
			ConsoleCommandSender console = getServer().getConsoleSender();
			File config = new File(getDataFolder(), "config.yml");
			File configBackup = new File(getDataFolder(), "config-backup.yml");

			console.sendMessage("[BW-Parkour]" + ChatColor.DARK_RED + " IMPORTANT:");
			console.sendMessage("[BW-Parkour]" + ChatColor.RED + " Your configuration has been reset due to an update that added new configuration options");
			console.sendMessage("[BW-Parkour]" + ChatColor.RED + " Your old configuration file is stored in config-backup.yml");
			console.sendMessage("[BW-Parkour]" + ChatColor.RED + " You can use this file as reference to get back your config to how it was before");

			try
			{
				Files.copy(config, configBackup);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			config.delete();
			saveDefaultConfig();
		}

		setupEconomy();
	}

	private boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);

		if (economyProvider != null)
		{
			econ = economyProvider.getProvider();
		}

		return (econ != null);
	}
}
