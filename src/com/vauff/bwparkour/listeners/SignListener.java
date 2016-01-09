package com.vauff.bwparkour.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.vauff.bwparkour.core.Main;

public class SignListener implements Listener
{
	private Main main;

	public SignListener(Main plugin)
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

			if (line1.equalsIgnoreCase("[Parkour]"))
			{
				//to do
			}
		}
	}

}
