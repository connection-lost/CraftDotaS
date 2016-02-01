package me.crafter.mc.craftdotas.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Team {

	private int id;
	private String displayname;
	private String prefix;
	private String suffix;
	private Location spawnlocation;
	
	private static Map<Player, Integer> playerteams = new HashMap<Player, Integer>();
	private static Map<Integer, Team> teams = new HashMap<Integer, Team>();
	
	public Team(int id_, String displayname_, String prefix_, String suffix_, Location spawnlocation_){
		id = id_;
		displayname = displayname_;
		prefix = prefix_;
		suffix = suffix_;
		spawnlocation = spawnlocation_;
		teams.put(id, this);
	}
	
	public int getId() {return id;}
	public String getDisplayName() {return displayname;}
	public String getPrefix() {return prefix;}
	public String getSuffix() {return suffix;}
	public Location getSpawnLocation() {return spawnlocation;}
	
	public Team getTeam(int id){
		return teams.get(id);
	}
	
	public int getPlayerTeam(Player player) {return playerteams.get(player);}
	
	public List<Player> getMembers(int teamid){
		List<Player> members = new ArrayList<Player>();
		for (Player player : playerteams.keySet()){
			if (playerteams.get(player) == teamid) {
				members.add(player);
			}
		}
		return members;
	}
	
	
}
