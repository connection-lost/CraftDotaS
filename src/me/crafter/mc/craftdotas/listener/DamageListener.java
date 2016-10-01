package me.crafter.mc.craftdotas.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.utils.PlayerUtils;

public class DamageListener implements Listener {
	
	// Cancel friendly fire
	@EventHandler
	public void onPlayerTeamDamage(EntityDamageByEntityEvent event){
		if (event.getEntity() instanceof Player && event.getEntity().getWorld() == Game.getWorld()){
			Player[] players = PlayerUtils.processKillerDier(event.getEntity(), event.getDamager());
			Player damaged = players[0];
			Player damager = players[1];
			if (damaged != null && damager != null){
				if (Team.getPlayerTeam(damaged) == Team.getPlayerTeam(damager)){
					event.setCancelled(true);
				}
			}
		}
	}
	
	// Cancel fast regain health
	@EventHandler
	public void onPlayerRegainHealth(EntityRegainHealthEvent event){
		if (event.getEntity() instanceof Player && event.getEntity().getWorld() == Game.getWorld()){
			if (event.isFastRegen()) event.setCancelled(true);
		}
	}
	
	// Custom code for Rev-Craft
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDamageFood(EntityDamageEvent event){
		if (event.getEntity() instanceof Player && event.getEntity().getWorld() == Game.getWorld()){
			if (event.isCancelled()) return;
			Player player = (Player) event.getEntity();
			player.setFoodLevel(Math.max(player.getFoodLevel() - (int)event.getFinalDamage()/4, 6));
			player.removePotionEffect(PotionEffectType.HUNGER);
			player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Math.min(120, ((int)event.getFinalDamage()) * 10), 0, true, false));
		}
	}
	// End custom code for Rev-Craft
}
