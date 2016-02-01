package me.crafter.mc.craftdotas.action;

import java.util.HashMap;
import java.util.Map;

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
				break;
			case "SYSTEM_COMMAND": // Run command
				break;
			case "BATTLEFIELD_PLAYER_READY": // Teleport players to battlefield
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