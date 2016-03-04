package me.crafter.mc.craftdotas.action;

import org.bukkit.entity.Player;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.utils.FormatUtils;

public class HudAction {
	
	private static String actionbar = "-";
	private static int cooldown = 2;
	
	public HudAction(String actionbar_){
		actionbar = actionbar_;
	}

	public static String getActionBar(){return actionbar;}
	public static void setActionBar(String actionbar_){actionbar = actionbar_;}
	
	public static void execute(int tick){
		if (cooldown <= 0){
			for (Player player : Game.getWorld().getPlayers()){
				ActionBarAPI.sendActionBar(player, getMessage(getActionBar(), tick));
			}
			cooldown = 5;
		} else {
			cooldown --;
		}
		
	}

	public static String getDebugMessage(int tick){
		String message = "";
		message += FormatUtils.gameOn();
		message += "Time: " + FormatUtils.tickToMinuteSecond(tick) + "(" + tick + ")";
		return message;
	}

	public static String getMessage(String message, int tick){
		for (Team team : Team.getTeams()){
			message = message.replace("{teamdisplayname" + team.getId() + "}", team.getDisplayName());
			message = message.replace("{teamprefix" + team.getId() + "}", team.getPrefix());
			message = message.replace("{teamsuffix" + team.getId() + "}", team.getSuffix());
			message = message.replace("{teamscore" + team.getId() + "}", Game.getScore(team.getId()) + "");
		}
		message = message.replace("{time}", FormatUtils.tickToMinuteSecond(tick));
		return message;
	}
	
	
}
