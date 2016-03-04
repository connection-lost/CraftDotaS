package me.crafter.mc.craftdotas.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.crafter.mc.craftdotas.object.Game;

public class InventoryListener implements Listener {
	
	// no throw
	@EventHandler
	public void onPlayerTossItem(PlayerDropItemEvent event){
		if (event.getPlayer().getWorld() != Game.getWorld() || event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	// no change cap
	@EventHandler
	public void onPlayerChangeHelmet(InventoryClickEvent event){
		if (event.getWhoClicked().getWorld() != Game.getWorld() || event.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
		if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.LEATHER_HELMET){
			event.setCancelled(true);
		}
	}

}


