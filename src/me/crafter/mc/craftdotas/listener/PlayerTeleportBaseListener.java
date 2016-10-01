package me.crafter.mc.craftdotas.listener;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;

public class PlayerTeleportBaseListener implements Listener {

	@EventHandler
	public void onPlayerRightClickSign(PlayerInteractEvent event){
		if (event.getPlayer().getWorld() == Game.getWorld()){
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
				if (event.getClickedBlock().getType() == Material.WALL_SIGN){
					Sign sign = (Sign)event.getClickedBlock().getState();
					if (sign.getLine(0).equals("[Teleport Base]")){
						if (String.valueOf(Team.getPlayerTeam(event.getPlayer()).getId()).equals(sign.getLine(1))){
							event.getPlayer().teleport(Team.getPlayerTeam(event.getPlayer()).getSpawnLocation());
						}
					}
				}
			}
		}
	}
	
}
