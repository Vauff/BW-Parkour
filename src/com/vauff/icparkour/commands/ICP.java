package com.vauff.icparkour.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vauff.icparkour.core.Main;
import com.vauff.icparkour.core.Util;

public class ICP implements CommandExecutor
{
	private Main main;

	public ICP(Main plugin)
	{
		main = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		if (commandlabel.equalsIgnoreCase("icp"))
		{
			if (args.length > 0)
			{
				switch (args[0])
				{
				case "about":
					if (sender.hasPermission("icparkour.about"))
					{
						sender.sendMessage(ChatColor.GREEN + "You are using IC-Parkour " + ChatColor.RED + "v" + Main.version + ChatColor.GREEN + " created by Vauff");
						sender.sendMessage(ChatColor.AQUA + "https://github.com/Vauff/IC-Parkour");
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}

					break;
				case "reload":
					if (sender.hasPermission("icparkour.reload"))
					{
						main.reloadConfig();
						sender.sendMessage(ChatColor.AQUA + "IC-Parkour configuration file reloaded successfully!");
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}

					break;
				case "help":
					if (sender.hasPermission("icparkour.help"))
					{
						sender.sendMessage(ChatColor.GREEN + "*** IC-Parkour Help ***");
						sender.sendMessage(ChatColor.AQUA + "/icp about: " + ChatColor.GOLD + "Shows you plugin information");
						sender.sendMessage(ChatColor.AQUA + "/icp reload: " + ChatColor.GOLD + "Reloads the configuration file");
						sender.sendMessage(ChatColor.AQUA + "/icp help: " + ChatColor.GOLD + "Gives you help with different command options");
						sender.sendMessage(ChatColor.AQUA + "/icp exit: " + ChatColor.GOLD + "Allows you to leave a parkour arena");
						sender.sendMessage(ChatColor.AQUA + "/icp enter: " + ChatColor.GOLD + "Allows you to enter a parkour arena");
						sender.sendMessage(ChatColor.AQUA + "/icp tp: " + ChatColor.GOLD + "Lets you tp to your last checkpoint");

					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}

					break;
				case "exit":
					if (sender.hasPermission("icparkour.exit"))
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
										Location spawn = Util.getIntelligentSpawnLocation(Util.getCenter(Bukkit.getWorld(main.getConfig().getString("spawn-world-name")).getSpawnLocation()));
										Util.arenaData.remove(sender.getName());
										player.teleport(spawn);
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
								Location spawn = Util.getIntelligentSpawnLocation(Util.getCenter(Bukkit.getWorld(main.getConfig().getString("spawn-world-name")).getSpawnLocation()));
								player.teleport(spawn);
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
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}

					break;
				case "enter":
					if (sender.hasPermission("icparkour.enter"))
					{
						Player player = (Player) sender;

						if (args.length > 1)
						{
							if (Util.doesArenaExist(args[1]))
							{
								if (!Util.arenaData.containsKey(sender.getName()))
								{
									String[] coordinates = main.getConfig().getConfigurationSection("arenas").getConfigurationSection(args[1]).getString("coordinates").split(",");
									Location arena = Util.getCenter(new Location(Bukkit.getWorld(main.getConfig().getConfigurationSection("arenas").getConfigurationSection(args[1]).getString("world-name")), Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));
									Util.arenaData.put(sender.getName(), args[1]);
									player.teleport(arena);
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
							sender.sendMessage(ChatColor.RED + "You are missing a required argument! /icp enter <arenaname>");
						}
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}

					break;
				case "tp":
					if (sender.hasPermission("icparkour.tp"))
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
											Location arena = Util.getCenter(new Location(Bukkit.getWorld(main.getConfig().getConfigurationSection("arenas").getConfigurationSection(args[1]).getString("world-name")), Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));

											player.teleport(arena);
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
									Location arena = Util.getCenter(new Location(Bukkit.getWorld(main.getConfig().getConfigurationSection("arenas").getConfigurationSection(Util.arenaData.get(player.getName())).getString("world-name")), Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));

									player.teleport(arena);
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
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}

					break;
				case "remove":
					if (sender.hasPermission("icparkour.remove"))
					{
						if (args.length == 2)
						{
							sender.sendMessage(ChatColor.RED + "You are missing a required argument! /icp remove <arenaname>");
						}
						else
						{
							main.getConfig().getConfigurationSection("arenas").set(args[2], null);
							main.saveConfig();
							sender.sendMessage(ChatColor.GREEN + "The parkour arena " + args[2] + " was successfully removed!");
						}
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}

					break;
				case "add":
					break;
				case "edit":
					break;
				default:
					sender.sendMessage(ChatColor.RED + args[0] + " wasn't recognized as a valid option, please see /icp help");

					break;
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "Please give an option with /icp! e.g. /icp help");
			}
		}
		return true;
	}
}
