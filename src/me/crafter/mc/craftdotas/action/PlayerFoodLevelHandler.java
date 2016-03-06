package me.crafter.mc.craftdotas.action;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import me.crafter.mc.craftdotas.object.Game;

public class PlayerFoodLevelHandler {

	private static int tick = 0;
	
	public static void handleSprint(){
		for (Player p : Game.getWorld().getPlayers()){
			if (tick % 20 == p.getEntityId() % 20){
				if (p.isDead()) continue;
				if (p.isSprinting()){
					p.setFoodLevel(p.getFoodLevel() - 1);
				} else {
					if (p.getFoodLevel() >= 20){
						p.setHealth(Math.min(p.getHealth() + 1, p.getMaxHealth()));
					} else {
						if (p.hasPotionEffect(PotionEffectType.HUNGER)){
							p.setFoodLevel(Math.min(p.getFoodLevel() + 1, 20));
						} else {
							p.setFoodLevel(Math.min(p.getFoodLevel() + 2, 20));
						}
					}
				}
			}
		}
		tick ++;
	}
	
}
