package me.crafter.mc.craftdotas.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.crafter.mc.craftdotas.CraftDotaS;
import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.utils.PlayerUtils;

public class PvpDeathListener implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		if (event.getEntity().getWorld() == Game.getWorld()){
			if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent){
				EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent)event.getEntity().getLastDamageCause();
				Player[] players = PlayerUtils.processKillerDier(event2.getEntity(), event2.getDamager());
				Player damaged = players[0];
				Player damager = players[1];
				if (damaged != null && damager != null){
					Team damagedteam = Team.getPlayerTeam(damaged);
					Team damagerteam = Team.getPlayerTeam(damager);
					if (damagedteam != damagerteam){
						if (damagedteam.getBounty() != null){
							damagedteam.getBounty().award(damager, damaged.getLocation());
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent event){
		final Player player = event.getPlayer();
		if (player.getWorld() == Game.getWorld()){
			Bukkit.getScheduler().runTaskLater(CraftDotaS.plugin, new Runnable(){
				@Override
				public void run() {
					player.teleport(Team.getPlayerTeam(player).getSpawnLocation());
				}
			}, 1L);
		}
	}
	
}
