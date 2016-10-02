package me.crafter.mc.craftdotas.action;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

//import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.object.building.Building;
import me.crafter.mc.craftdotas.utils.FormatUtils;
import me.crafter.mc.scoreboardapi.ScoreboardManager;
import me.crafter.mc.scoreboardapi.SingleScoreboard;
import net.md_5.bungee.api.ChatColor;

public class HudAction {
	
	private static String actionbar = null;
	private static int hudtick = 2;
	private static int updatefrequency = 20;
	private static String scoreboardtitle = null;
	private static List<String> scoreboardlines;
	private static SingleScoreboard singlescoreboard = null;
	private static BossBar bossbar = null;
	
	public HudAction(String actionbar_, String scoreboardtitle_, List<String> scoreboardlines_){
		actionbar = actionbar_;
		scoreboardtitle = scoreboardtitle_;
		scoreboardlines = scoreboardlines_;
		if (actionbar.equals("")) actionbar = null;
		if (scoreboardtitle.equals("")) scoreboardtitle = null;
		if (scoreboardtitle != null) initScoreboard();
		bossbar = Bukkit.createBossBar("CraftDotaS", BarColor.PURPLE, BarStyle.SOLID);
	}

	public static String getActionBar(){return actionbar;}
	public static String getScoreboardTitle(){return scoreboardtitle;}
	public static List<String> getScoreboardLines(){return scoreboardlines;}
	public static int getUpdateFrequency(){return updatefrequency;}
	
	public static void setActionBar(String actionbar_){actionbar = actionbar_;}
	public static void setUpdateFrequency(int updatefrequency_){updatefrequency = updatefrequency_;}
	public static void setScoreboardTitle(String scoreboardtitle_){scoreboardtitle = scoreboardtitle_;}
	public static void setScoreboardLines(List<String> scoreboardlines_){scoreboardlines = scoreboardlines_;}
	
	public static void initScoreboard(){
		singlescoreboard = ScoreboardManager.getNewScoreboard(ChatColor.GOLD + "CraftDotaS", getMessage(scoreboardlines, Game.getTick()));
	}
	
	public static void execute(int tick){
		if (hudtick % updatefrequency == 0){
			if (actionbar != null){
//				for (Player player : Game.getWorld().getPlayers()){
//					ActionBarAPI.sendActionBar(player, getMessage(getActionBar(), tick));
//				}
				bossbar.setTitle(getMessage(getActionBar(), tick));
				for (Player player : Bukkit.getOnlinePlayers()){
					if (player.getWorld() == Game.getWorld()){
						bossbar.addPlayer(player);
					} else {
						bossbar.removePlayer(player);
					}
				}
			}
			if (scoreboardtitle != null){
				singlescoreboard.changeLines(getMessage(scoreboardlines, tick));
				singlescoreboard.changeTitle(getMessage(scoreboardtitle, tick));
				for (Player player : Bukkit.getOnlinePlayers()){
					if (player.getWorld() == Game.getWorld()){
						if (!singlescoreboard.containsPlayer(player)){
							singlescoreboard.addPlayer(player);
						}
					} else {
						if (singlescoreboard.containsPlayer(player)) singlescoreboard.removePlayer(player);
					}
				}
				
			}
		}
		hudtick ++;
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
		for (Building building : Building.getBuildings()){
			message = message.replace("{buildingonechar" + building.getId() + "}", oneCharBuildingColor(building));
		}
		return message;
	}
	
	public static List<String> getMessage(List<String> messages, int tick){
		List<String> messages2 = new ArrayList<String>(messages);
		for (int i = 0; i < messages2.size(); i++){
			messages2.set(i, getMessage(messages2.get(i), tick));
		}
		return messages2;
	}
	
	public static String oneCharBuildingColor(Building building){
		if (building.isDestroyed()) return ChatColor.DARK_GRAY + "";
		if (building.isInvulnerable()) return ChatColor.GREEN + "";
		double percent = building.getHealth()/building.getMaxHealth();
		if (percent > 0.8) return ChatColor.GREEN + "";
		else if (percent > 0.6) return ChatColor.GOLD + "";
		else if (percent > 0.4) return ChatColor.YELLOW + "";
		else if (percent > 0.2) return ChatColor.RED + "";
		else return ChatColor.DARK_RED + "";
	}

	public static void removeAll() {
		if (scoreboardtitle != null) ScoreboardManager.removeScoreboard(ChatColor.GOLD + "CraftDotaS");
		if (actionbar != null) bossbar.removeAll();
	}
	
	
}
