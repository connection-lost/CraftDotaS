package me.crafter.mc.craftdotas.action;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.crafter.mc.craftdotas.object.Team;
import net.md_5.bungee.api.ChatColor;

public class GameFlow {

	private static Map<Integer, String[]> gameflows;
	
	public static void newGameFlow(int time, String action, String content){
		String[] actioncontent = {action, content};
		gameflows.put(time, actioncontent);
	}
	
	public static String[] getGameFlow(int time){
		return gameflows.get(time);
	}
	
	public static void execute(int time){
		String[] flow = gameflows.get(time);
		if (flow != null){
			switch (flow[0]){
			case "SYSTEM_ANNOUNCE": // Say message
				String message = ChatColor.translateAlternateColorCodes('&', flow[1]);
				Bukkit.broadcastMessage(message);
				break;
			case "SYSTEM_COMMAND": // Run command
				String command = flow[1];
				if (command.startsWith("/")){
					command = command.replaceFirst("/", "");
				}
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
				break;
			case "BATTLEFIELD_PLAYER_READY": // Teleport players to battlefield
				for (Player player : GameInfo.getWorld().getPlayers()){
					player.teleport(Team.getPlayerTeam(player).getSpawnLocation());
				}
				break;
			case "BATTLEFIELD_GATE_OPEN": // Open player base gate
				break;
			case "BATTLEFIELD_GATE_CLOSE": // Close player base gate
				break;
			case "BATTLEFIELD_SPAWN_NEUTRAL": // Spawn neutral creeps
				break;
			case "BATTLEFIELD_SPAWN_CREEP": // Spawn teamed creeps
				break;
			case "SYSTEM_END": // Game force end after a while
				break;
			case "SYSTEM_STOP": // Game terminate
				break;
			}
		}
	}
	
	public static void init(){
		gameflows = new HashMap<Integer, String[]>();
	}
	
	public static void clear(){
		gameflows = new HashMap<Integer, String[]>();
	}
	
}
