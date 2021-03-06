package com.vauff.icparkour.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.vauff.icparkour.core.Main;
import com.vauff.icparkour.signactions.*;

public class SignClickListener implements Listener
{
	private Main main;

	public SignClickListener(Main plugin)
	{
		main = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();

		if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) && ((event.getClickedBlock().getType() == Material.SIGN_POST) || (event.getClickedBlock().getType() == Material.WALL_SIGN)))
		{
			Sign sign = (Sign) event.getClickedBlock().getState();
			String line1 = sign.getLine(0);
			String line2 = sign.getLine(1);

			if (line1.equalsIgnoreCase("[Parkour]"))
			{
				if (line2.equalsIgnoreCase("Enter"))
				{
					if (player.hasPermission("icparkour.enter"))
					{
						Enter.enter(player, main, sign);
					}
					else
					{
						player.sendMessage(ChatColor.DARK_RED + "You do not have access to that action.");
					}
				}

				else if (line2.equalsIgnoreCase("Exit"))
				{
					if (player.hasPermission("icparkour.exit"))
					{
						Exit.exit(player, main, sign);
					}
					else
					{
						player.sendMessage(ChatColor.DARK_RED + "You do not have access to that action.");
					}
				}

				else if (line2.equalsIgnoreCase("Finish"))
				{
					if (player.hasPermission("icparkour.finish"))
					{
						Finish.finish(player, main, sign);
					}
					else
					{
						player.sendMessage(ChatColor.DARK_RED + "You do not have access to that action.");
					}
				}

				else if (line2.equalsIgnoreCase("Checkpoint"))
				{
					if (player.hasPermission("icparkour.checkpoint"))
					{
						Checkpoint.set(player, main, sign);
					}
					else
					{
						player.sendMessage(ChatColor.DARK_RED + "You do not have access to that action.");
					}
				}

				else if (line2.equalsIgnoreCase("CheckpointTP"))
				{
					if (player.hasPermission("icparkour.tp"))
					{
						CheckpointTP.tp(player, main, sign);
					}
					else
					{
						player.sendMessage(ChatColor.DARK_RED + "You do not have access to that action.");
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + line2 + " is not a valid option");
				}
			}
		}
	}

}
