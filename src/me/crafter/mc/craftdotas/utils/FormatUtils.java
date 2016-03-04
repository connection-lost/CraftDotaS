package me.crafter.mc.craftdotas.utils;

import me.crafter.mc.craftdotas.object.Game;
import net.md_5.bungee.api.ChatColor;

public class FormatUtils {
	
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

	public static String gameOn() {
		if (Game.isOn()){
			return ChatColor.GREEN + "";
		} else {
			return ChatColor.RED + "";
		}
	}

}
