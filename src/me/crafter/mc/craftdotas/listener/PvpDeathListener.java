package me.crafter.mc.craftdotas.listener;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.utils.PlayerUtils;

public class PvpDeathListener {

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
						// Score
						Game.addScore(damagerteam.getId(), damagedteam.getBounty().getScore());
						// Item
						List<ItemStack> itemstacks = damagedteam.getBounty().getItems();
						for (ItemStack itemstack : itemstacks){
							if (damagedteam.getBounty().isDropItemOnGround()){
								damaged.getWorld().dropItemNaturally(damaged.getLocation(), itemstack);
							} else {
								damager.getInventory().addItem(itemstack);
							}
						}
					}
				}
			}
		}
	}
	
}
