package me.crafter.mc.craftdotas.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.object.building.AttackMove;
import me.crafter.mc.craftdotas.object.building.Base;
import me.crafter.mc.craftdotas.object.building.Decoration;
import me.crafter.mc.craftdotas.object.building.Tower;
import net.md_5.bungee.api.ChatColor;

public class FileLoader {
	
	@SuppressWarnings("unchecked")
	public static void loadGame(File datafolder, String path) throws Exception{
		File folder = new File(datafolder.getPath() + "/" + path);
		
		// Step 1 - Game.yml
		Bukkit.getLogger().info("[CraftDotaS] Loading Game.yml...");
		FileConfiguration game = YamlConfiguration.loadConfiguration(new File(folder, "Game.yml"));
		World world = Bukkit.getWorld(game.getString("world"));
		int start = game.getInt("start");
		int end = game.getInt("end");
		int killscore = game.getInt("killscore");
		int deathscore = game.getInt("deathscore");
		int towinscore = game.getInt("towinscore");
		new Game(world, start, end, killscore, deathscore, towinscore);
		Bukkit.getLogger().info("[CraftDotaS] World: " + world.getName() + " " + start + "~" + end);
		Bukkit.getLogger().info("[CraftDotaS] ks/ds/ws: " + killscore + " / " + deathscore + " / " + towinscore);
		
		// Step 2 - Teams.yml
		Bukkit.getLogger().info("[CraftDotaS] Loading Teams.yml...");
		FileConfiguration teams = YamlConfiguration.loadConfiguration(new File(folder, "Teams.yml"));
		for (Map<?, ?> team : teams.getMapList("teams")){
			int id = (int) team.get("id");
			String displayname = ChatColor.translateAlternateColorCodes('&', (String) team.get("displayname"));
			String prefix = ChatColor.translateAlternateColorCodes('&', (String) team.get("prefix"));
			String suffix = ChatColor.translateAlternateColorCodes('&', (String) team.get("suffix"));
			List<Integer> spawnxyz = (List<Integer>) team.get("spawn");
			Location spawn;
			if (spawnxyz.size() == 3){
				spawn = new Location(world, spawnxyz.get(0), spawnxyz.get(1), spawnxyz.get(2));
			} else {
				spawn = new Location(world, spawnxyz.get(0), spawnxyz.get(1), spawnxyz.get(2), spawnxyz.get(3), spawnxyz.get(4));
			}
			new Team(id, displayname, prefix, suffix, spawn);
			Bukkit.getLogger().info("[CraftDotaS] \t" + id + "\t" + displayname);
		}
		
		// Step 3 - Building.yml
		Bukkit.getLogger().info("[CraftDotaS] Loading Buildings.yml...");
		FileConfiguration buildings = YamlConfiguration.loadConfiguration(new File(folder, "Buildings.yml"));
		for (Map<?, ?> building : buildings.getMapList("buildings")){
			int id = (int) building.get("id");
			String type = (String) building.get("type");
			int side = (int) building.get("id");
			double health = (int) building.get("health");
			double maxhealth = (int) building.get("maxhealth");
			double healthregen = (int) building.get("healthregen");
			String displayname = ChatColor.translateAlternateColorCodes('&', (String) building.get("displayname"));
			List<Location> locationsp = (List<Location>) building.get("locations");
			Location[] locations = {locationsp.get(0), locationsp.get(1)};
			boolean invulnerable = (boolean) building.get("invulnerable");
			List<Integer> unlocksl = ((List<Integer>) building.get("unlocks")); 
			int[] unlocks = new int[unlocksl.size()];
		    for (int i = 0; i < unlocks.length; i++)
		    {
		        unlocks[i] = unlocksl.get(i).intValue();
		    }
		    switch (type){
		    case "decoration":
		    	new Decoration(side, id, health, maxhealth, healthregen, health < 0.1, displayname, locations, invulnerable, unlocks, 0, 0);
		    	break;
		    case "base":
		    	new Base(side, id, health, maxhealth, healthregen, health < 0.1, displayname, locations, invulnerable, unlocks, 0, 0);
		    	break;
		    case "tower":
				AttackMove attackmove = AttackMove.valueOf((String) building.get("attackmove"));
				int damage = (int) building.get("damage");
				double attackspeed = (int) building.get("attackspeed");
				new Tower(side, id, health, maxhealth, healthregen, health < 0.1, displayname, locations, invulnerable, unlocks, 0, 0, attackmove, attackspeed, damage);
		    	break;
		    }
			Bukkit.getLogger().info("[CraftDotaS] \t" + id + "\t" + displayname + "\t" + health + "/" + maxhealth + "(+" + healthregen + ")");
		}
		
	}
	
}
