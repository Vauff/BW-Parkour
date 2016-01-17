package com.vauff.bwparkour.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vauff.bwparkour.core.Main;
import com.vauff.bwparkour.core.Util;

public class BWP implements CommandExecutor
{
	private Main main;

	public BWP(Main plugin)
	{
		main = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		if (commandlabel.equalsIgnoreCase("bwp"))
		{
			if (args.length > 0)
			{
				switch (args[0])
				{
				case "about":
					if (sender.hasPermission("bwparkour.about"))
					{
						sender.sendMessage(ChatColor.GREEN + "You are using BW-Parkour " + ChatColor.RED + "v" + Main.version + ChatColor.GREEN + " created by Vauff");
						sender.sendMessage(ChatColor.AQUA + "https://github.com/Vauff/BW-Parkour");
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}

					break;
				case "reload":
					if (sender.hasPermission("bwparkour.reload"))
					{
						main.reloadConfig();
						sender.sendMessage(ChatColor.AQUA + "BW-Parkour configuration file reloaded successfully!");
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have acccess to that command.");
					}

					break;
				case "help":
					if (sender.hasPermission("bwparkour.help"))
					{
						sender.sendMessage(ChatColor.GREEN + "*** BW-Parkour Help ***");
						sender.sendMessage(ChatColor.AQUA + "/bwp about: " + ChatColor.GOLD + "Shows you plugin information");
						sender.sendMessage(ChatColor.AQUA + "/bwp reload: " + ChatColor.GOLD + "Reloads the configuration file");
						sender.sendMessage(ChatColor.AQUA + "/bwp help: " + ChatColor.GOLD + "Gives you help with different command options");
						sender.sendMessage(ChatColor.AQUA + "/bwp exit: " + ChatColor.GOLD + "Allows you to leave a parkour arena");
						sender.sendMessage(ChatColor.AQUA + "/bwp enter: " + ChatColor.GOLD + "Allows you to enter a parkour arena");
						sender.sendMessage(ChatColor.AQUA + "/bwp tp: " + ChatColor.GOLD + "Shows you plugin information");

					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have acccess to that command.");
					}

					break;
				case "exit":
					if (sender.hasPermission("bwparkour.exit"))
					{
						Player player = (Player) sender;

						if (args.length > 1)
						{
							if (Util.doesArenaExist(args[1]))
							{
								if (Util.arenaData.containsKey(sender.getName()))
								{
									if (Util.arenaData.get(sender.getName()).equalsIgnoreCase(args[1]))
									{
										Util.arenaData.remove(sender.getName());
										player.teleport(Bukkit.getWorld(main.getConfig().getString("spawn-world-name")).getSpawnLocation());
										Util.checkpointData.remove(sender.getName());
										sender.sendMessage(ChatColor.GREEN + "You have exited the parkour arena " + args[1] + "! Teleporting you to spawn.");
									}
									else
									{
										sender.sendMessage(ChatColor.RED + "You are not in the parkour arena " + args[1] + ", you are in " + Util.arenaData.get(sender.getName()) + "!");
									}
								}
								else
								{
									sender.sendMessage(ChatColor.RED + "You are not in the parkour arena" + args[1] + "!");
								}
							}
							else
							{
								sender.sendMessage(ChatColor.RED + "There is not a parkour arena named " + args[1] + "!");
							}
						}
						else
						{
							if (Util.arenaData.containsKey(sender.getName()))
							{
								player.teleport(Bukkit.getWorld(main.getConfig().getString("spawn-world-name")).getSpawnLocation());
								Util.checkpointData.remove(sender.getName());
								sender.sendMessage(ChatColor.GREEN + "You have exited the parkour arena " + Util.arenaData.get(sender.getName()) + "! Teleporting you to spawn.");
								Util.arenaData.remove(sender.getName());
							}
							else
							{
								sender.sendMessage(ChatColor.RED + "You are not in a parkour arena!");
							}
						}
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have acccess to that command.");
					}

					break;
				case "enter":
					if (sender.hasPermission("bwparkour.enter"))
					{
						Player player = (Player) sender;

						if (args.length > 1)
						{
							if (Util.doesArenaExist(args[1]))
							{
								if (!Util.arenaData.containsKey(sender.getName()))
								{
									Util.arenaData.put(sender.getName(), args[1]);
									String[] coordinates = main.getConfig().getConfigurationSection("arenas").getConfigurationSection(args[1]).getString("coordinates").split(",");
									player.teleport(new Location(Bukkit.getWorld(main.getConfig().getConfigurationSection("arenas").getConfigurationSection(args[1]).getString("world-name")), Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));
									Util.checkpointData.remove(sender.getName());
									sender.sendMessage(ChatColor.GREEN + "You have entered the parkour arena named " + args[1] + "!");
								}

								else if (!Util.arenaData.get(sender.getName()).equalsIgnoreCase(args[1]))
								{
									sender.sendMessage(ChatColor.RED + "You can't enter this arena because you are already in the parkour arena " + Util.arenaData.get(sender.getName()) + "!");
								}
								else
								{
									sender.sendMessage(ChatColor.RED + "You are already in this parkour arena!");
								}
							}
							else
							{
								sender.sendMessage(ChatColor.RED + "There is not a parkour arena named " + args[1] + "!");
							}
						}
						else
						{
							player.sendMessage(ChatColor.RED + "You are missing a required argument! /bwp enter <arenaname>");
						}
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have acccess to that command.");
					}

					break;
				case "tp":
					if (sender.hasPermission("bwparkour.tp"))
					{
						Player player = (Player) sender;

						if (args.length > 1)
						{
							if (Util.doesArenaExist(args[1]))
							{
								if (Util.arenaData.containsKey(sender.getName()))
								{
									if (Util.arenaData.get(sender.getName()).equalsIgnoreCase(args[1]))
									{
										if (Util.checkpointData.containsKey(sender.getName()))
										{
											String[] coordinates = main.getConfig().getConfigurationSection("arenas").getConfigurationSection(args[1]).getConfigurationSection("checkpoints").getString(Util.checkpointData.get(player.getName())).split(",");
											player.teleport(new Location(Bukkit.getWorld(main.getConfig().getConfigurationSection("arenas").getConfigurationSection(args[1]).getString("world-name")), Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));
											sender.sendMessage(ChatColor.GREEN + "You have been teleported to your latest checkpoint named " + Util.checkpointData.get(sender.getName()) + "!");
										}
										else
										{
											sender.sendMessage(ChatColor.RED + "You do not currently have a checkpoint set on the arena " + args[1] + "!");
										}
									}
									else
									{
										sender.sendMessage(ChatColor.RED + "You are not in the parkour arena " + args[1] + ", you are in " + Util.arenaData.get(sender.getName()) + "!");
									}
								}
								else
								{
									sender.sendMessage(ChatColor.RED + "You are not in the parkour arena " + args[1] + "!");
								}
							}
							else
							{
								sender.sendMessage(ChatColor.RED + "There is not a parkour arena named " + args[1] + "!");
							}
						}
						else
						{
							if (Util.arenaData.containsKey(player.getName()))
							{
								if (Util.checkpointData.containsKey(sender.getName()))
								{
									String[] coordinates = main.getConfig().getConfigurationSection("arenas").getConfigurationSection(Util.arenaData.get(player.getName())).getConfigurationSection("checkpoints").getString(Util.checkpointData.get(player.getName())).split(",");
									player.teleport(new Location(Bukkit.getWorld(main.getConfig().getConfigurationSection("arenas").getConfigurationSection(Util.arenaData.get(player.getName())).getString("world-name")), Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));
									sender.sendMessage(ChatColor.GREEN + "You have been teleported to your latest checkpoint named " + Util.checkpointData.get(sender.getName()) + "!");
								}
								else
								{
									sender.sendMessage(ChatColor.RED + "You do not currently have a checkpoint set on the arena " + Util.arenaData.get(player.getName()) + "!");
								}
							}
							else
							{
								sender.sendMessage(ChatColor.RED + "You are not in a parkour arena!");
							}
						}
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have acccess to that command.");
					}

					break;
				default:
					sender.sendMessage(ChatColor.RED + args[0] + " wasn't recognized as a valid option, please see /bwp help");

					break;
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "Please give an option with /bwp! e.g. /bwp help");
			}
		}
		return true;
	}
}
