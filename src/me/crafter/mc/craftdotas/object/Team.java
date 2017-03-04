package me.crafter.mc.craftdotas.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Team {

	private int id;
	private String displayname;
	private String prefix;
	private String suffix;
	private String chatprefix;
	private String chatsuffix;
	private Location spawnlocation;
	private Bounty bounty;
	private ItemStack[] startingarmor;
	private ItemStack[] startingitem;
	
	private static Map<String, Team> playerteams = new HashMap<String, Team>();
	private static Map<Integer, Team> teams = new HashMap<Integer, Team>();
	
	public Team(int id_, String displayname_, String prefix_, String suffix_, String chatprefix_, String chatsuffix_, Location spawnlocation_){
		id = id_;
		displayname = displayname_;
		prefix = prefix_;
		suffix = suffix_;
		chatprefix = chatprefix_;
		chatsuffix = chatsuffix_;
		spawnlocation = spawnlocation_;
		teams.put(id, this);
	}
	
	public Team(int id_, String displayname_, String prefix_, String suffix_, String chatprefix_, String chatsuffix_, Location spawnlocation_, ItemStack[] startingarmor_, ItemStack[] startingitem_){
		id = id_;
		displayname = displayname_;
		prefix = prefix_;
		suffix = suffix_;
		chatprefix = chatprefix_;
		chatsuffix = chatsuffix_;
		spawnlocation = spawnlocation_;
		teams.put(id, this);
		startingarmor = startingarmor_;
		startingitem = startingitem_;
	}
	
	public int getId() {return id;}
	public String getDisplayName() {return displayname;}
	public String getPrefix() {return prefix;}
	public String getSuffix() {return suffix;}
	public String getChatPrefix() {return chatprefix;}
	public String getChatSuffix() {return chatsuffix;}
	public Location getSpawnLocation() {return spawnlocation;}
	public Bounty getBounty() {return bounty;}
	
	public static Team getTeam(int id){
		return teams.get(id);
	}
	
	public static Team getPlayerTeam(Player player) {
		Team team = playerteams.get(player.getName());
		if (team == null){
			playerteams.put(player.getName(), teams.get(-1));
			return teams.get(-1);
		}
		return team;
	}
	
	public static List<String> getMembersByTeamId(int teamid){
		List<String> members = new ArrayList<String>();
		for (String playername : playerteams.keySet()){
			if (playerteams.get(playername).getId() == teamid) {
				members.add(playername);
			}
		}
		return members;
	}
	
	public static List<String> getMembersByTeam(Team team){
		List<String> members = new ArrayList<String>();
		for (String playername : playerteams.keySet()){
			if (playerteams.get(playername) == team) {
				members.add(playername);
			}
		}
		return members;
	}
	
	public static List<Team> getTeams(){
		List<Team> retteams = new ArrayList<Team>();
		for (int i : teams.keySet()){
			retteams.add(teams.get(i));
		}
		return retteams;
	}
	
	public void addPlayer(Player player){
		playerteams.put(player.getName(), this);
		player.getInventory().setContents(startingitem);
		player.getInventory().setArmorContents(startingarmor);
	}
	
	public void setBounty(Bounty bounty_) {bounty = bounty_;}
	
	public static void removeAll(){
		playerteams = new HashMap<String, Team>();
		teams = new HashMap<Integer, Team>();
	}

}
