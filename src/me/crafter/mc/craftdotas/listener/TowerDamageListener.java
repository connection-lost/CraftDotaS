package me.crafter.mc.craftdotas.listener;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.connorlinfoot.titleapi.TitleAPI;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.object.building.Building;
import net.md_5.bungee.api.ChatColor;

public class TowerDamageListener implements Listener {

	// Currently using normal region detection
	// Will try to use Metadata
	
	@EventHandler
	public void onPlayerHitTower(BlockBreakEvent event){
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		// Temp refuse to damage tower with torch
		switch (block.getType()){
		case TORCH:
		case LADDER:
			return;
		default:
			break;
		}
		
		if (block.getWorld() == Game.getWorld() && !(player.getGameMode() == GameMode.CREATIVE) && Game.getTick() > 0){
			for (int id : Building.getBuildings().keySet()){
				Building building = Building.getBuildings().get(id);
				Location locationmin = building.getLocations()[0];
				Location locationmax = building.getLocations()[1];
				Location location = block.getLocation();
				if (location.getX() >= locationmin.getX() && location.getX() <= locationmax.getX() && 
					location.getY() >= locationmin.getY() && location.getY() <= locationmax.getY() && 
					location.getZ() >= locationmin.getZ() && location.getZ() <= locationmax.getZ()){
					int buildingside = building.getSide();
					int playerside = Team.getPlayerTeam(player).getId();
					if (buildingside == playerside){
						// nothing?
					} else {
						// Handle building hp
						boolean hit = building.damage(1);
						if (hit){
							// Handle animation
							location.getWorld().spigot().playEffect(location, Effect.MAGIC_CRIT, 0, 0, 0.4F, 0.4F, 0.4F, 1F, 16, 16);
							location.getWorld().spigot().playEffect(location, Effect.CRIT, 0, 0, 0.4F, 0.4F, 0.4F, 1F, 16, 16);
							location.getWorld().spigot().playEffect(location, Effect.TILE_DUST, 0, 0, 0.8F, 0.8F, 0.8F, 0.65F, 16, 16);
							location.getWorld().playSound(location, Sound.EXPLODE, 0.4F, 1.25F);
							TitleAPI.sendTitle(player, 2, 30, 10, " ", building.getDisplayName() + ChatColor.RESET + " " + (int)building.getHealth() + "/" + (int)building.getMaxHealth());
							building.setLastDamager(player);
						}
					}	
					return;
				}	
			}
		}
	}
	
	
}
