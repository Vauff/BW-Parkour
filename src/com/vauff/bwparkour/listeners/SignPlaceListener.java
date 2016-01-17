package com.vauff.bwparkour.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignPlaceListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSignChange(SignChangeEvent event)
	{
		Player player = event.getPlayer();

		if (event.getLine(0).equalsIgnoreCase("[Parkour]") && !player.hasPermission("bwparkour.place"))
		{
			System.out.println(1);
			event.getBlock().breakNaturally();
			player.sendMessage(ChatColor.DARK_RED + "You do not have access to place BW-Parkour signs.");
		}

	}
}