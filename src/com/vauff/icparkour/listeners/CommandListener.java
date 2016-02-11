package com.vauff.icparkour.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.vauff.icparkour.core.Main;
import com.vauff.icparkour.core.Util;

public class CommandListener implements Listener
{
	private Main main;

	public CommandListener(Main plugin)
	{
		main = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		if (main.getConfig().getBoolean("block-cmds"))
		{
			Player player = event.getPlayer();
			boolean isCmdBlocked = true;

			if (Util.arenaData.containsKey(player.getName()) && !player.hasPermission("icparkour.bypasscmdblock"))
			{
				for (String cmd : main.getConfig().getStringList("allowed-cmds"))
				{
					if (cmd.equalsIgnoreCase(event.getMessage().substring(1).split(" ")[0]))
					{
						isCmdBlocked = false;
					}
				}

				if (isCmdBlocked)
				{
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED + "You cannot use commands while in a parkour arena!");
				}
			}
		}
	}
}
