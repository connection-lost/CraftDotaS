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

import me.crafter.mc.craftdotas.object.Bounty;
import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.object.building.Building;

public class TowerDamageListener implements Listener {

	// Currently using normal region detection
	// Will try to use Metadata
	
	@EventHandler
	public void onPlayerHitTower(BlockBreakEvent event){
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if (block.getWorld() == Game.getWorld() && !(player.getGameMode() == GameMode.CREATIVE)){
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
							location.getWorld().spigot().playEffect(location, Effect.MAGIC_CRIT, 0, 0, 0.4F, 0.4F, 0.4F, 1F, 48, 16);
							location.getWorld().spigot().playEffect(location, Effect.CRIT, 0, 0, 0.4F, 0.4F, 0.4F, 1F, 32, 16);
							location.getWorld().spigot().playEffect(location, Effect.TILE_DUST, 0, 0, 0.8F, 0.8F, 0.8F, 0.65F, 32, 16);
							location.getWorld().playSound(location, Sound.EXPLODE, 0.4F, 1.45F);
						}
						
						// Handle bounty
//						Bounty bounty;
//						if (broken){
//							bounty = building.getKillBounty();
//						} else {
//							bounty = building.getDamageBounty();
//						}
//						bounty.award(player);
					}	
					return;
				}	
			}
		}
	}
	
	
}
