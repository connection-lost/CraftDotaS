package me.crafter.mc.craftdotas.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import me.crafter.mc.craftdotas.object.Team;
import net.md_5.bungee.api.ChatColor;

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
	
	public static boolean joinTeam(String playername, int teamid){
		Player player = Bukkit.getPlayerExact(playername);
		if (player == null || !player.isOnline()) return false;
		Team team = Team.getTeam(teamid);
		if (team == null) return false;
		team.addPlayer(player);
		player.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "你加入了队伍: " + ChatColor.RESET + team.getDisplayName());
		return true;
	}
	
}
