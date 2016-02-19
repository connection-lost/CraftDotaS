package me.crafter.mc.craftdotas.listener;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.crafter.mc.craftdotas.object.Game;

public class PreventBlockChangeListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerBreakBlock(BlockBreakEvent event){
		if (event.getBlock().getWorld() == Game.getWorld()){
			if (!(event.getPlayer().getGameMode() == GameMode.CREATIVE)){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(BlockPlaceEvent event){
		if (event.getBlock().getWorld() == Game.getWorld()){
			if (!(event.getPlayer().getGameMode() == GameMode.CREATIVE)){
				event.setCancelled(true);
			}
		}
	}
	
}
