package me.crafter.mc.craftdotas.listener;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.crafter.mc.craftdotas.object.Game;

public class ModifyWorldListener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event){
		if (event.getBlock().getWorld() == Game.getWorld() && !(event.getPlayer().getGameMode() == GameMode.CREATIVE)){
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event){
		if (event.getBlock().getWorld() == Game.getWorld() && !(event.getPlayer().getGameMode() == GameMode.CREATIVE)){
			event.setCancelled(true);
		}
	}
	
	
}
