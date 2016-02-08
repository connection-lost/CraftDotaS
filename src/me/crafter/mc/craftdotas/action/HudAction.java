package me.crafter.mc.craftdotas.action;

import org.bukkit.entity.Player;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.crafter.mc.craftdotas.object.Game;
import net.md_5.bungee.api.ChatColor;

public class HudAction {
	
	
	public static void execute(int tick){
		for (Player player : Game.getWorld().getPlayers()){
			ActionBarAPI.sendActionBar(player, getDebugMessage(tick));
		}
	}

	public static String getDebugMessage(int tick){
		String message = "";
		if (Game.isOn()){
			message += ChatColor.GREEN;
		} else {
			message += ChatColor.RED;
		}
		message += "Time: " + tickToMinuteSecond(tick) + "(" + tick + ")";
		return message;
	}

	public static String tickToMinuteSecond(int tick){
		String time = "";
		if (tick < 0){
			tick -= 20;
		}
		if (tick < 0 && tick > -1200){
			time += "-";
		}
		time += (tick / 1200);
		time += ":";
		int second = ((tick / 20) % 60);
		if (second < 0){
			second = 0 - second;
		}
		if (second < 10){
			time += "0";
		}
		time += second;
		return time;
	}
	
}
