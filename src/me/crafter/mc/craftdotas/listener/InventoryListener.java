package me.crafter.mc.craftdotas.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.crafter.mc.craftdotas.object.Game;

public class InventoryListener implements Listener {
	
	// no throw
	@EventHandler
	public void onPlayerTossItem(PlayerDropItemEvent event){
		if (event.getPlayer().getWorld() != Game.getWorld()) return;
		event.setCancelled(true);
	}

}


