package me.crafter.mc.craftdotas.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

public class PlayerUtils {

	public static Player[] processKillerDier(Entity damagedraw, Entity damagerraw){
		Player damaged = null;
		Player damager = null;
		
		if (damagedraw instanceof Player){
			damaged = (Player) damagedraw;
		}
		
		if (damagerraw instanceof Player){
			damager = (Player) damagerraw;
		} else if (damagerraw instanceof Projectile){
			Projectile projectile = (Projectile) damagerraw;
			if (projectile.getShooter() != null && projectile.getShooter() instanceof Player){
				damager = (Player) projectile.getShooter();
			} // else check metadata
		}
		
		Player[] players = {damaged, damager};
		return players;
	}
	
}
