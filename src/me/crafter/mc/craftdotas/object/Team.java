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
	private Bounty bounty;
	
	private static Map<Player, Team> playerteams = new HashMap<Player, Team>();
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
	public Bounty getBounty() {return bounty;}
	
	public static Team getTeam(int id){
		return teams.get(id);
	}
	
	public static Team getPlayerTeam(Player player) {
		Team team = playerteams.get(player);
		if (team == null){
			playerteams.put(player, teams.get(-1));
			return teams.get(-1);
		}
		return team;
	}
	
	public static List<Player> getMembersByTeamId(int teamid){
		List<Player> members = new ArrayList<Player>();
		for (Player player : playerteams.keySet()){
			if (playerteams.get(player).getId() == teamid) {
				members.add(player);
			}
		}
		return members;
	}
	
	public static List<Player> getMembersByTeam(Team team){
		List<Player> members = new ArrayList<Player>();
		for (Player player : playerteams.keySet()){
			if (playerteams.get(player) == team) {
				members.add(player);
			}
		}
		return members;
	}
	
	public void addPlayer(Player player){
		playerteams.put(player, this);
	}
	
	public void setBounty(Bounty bounty_) {bounty = bounty_;}

}
