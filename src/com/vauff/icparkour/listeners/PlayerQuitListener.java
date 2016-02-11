package com.vauff.icparkour.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.vauff.icparkour.core.Util;

public class PlayerQuitListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		String playerName = event.getPlayer().getName();

		if (Util.arenaData.containsKey(playerName))
		{
			Util.arenaData.remove(playerName);
		}

		if (Util.checkpointData.containsKey(playerName))
		{
			Util.checkpointData.remove(playerName);
		}
	}
}
