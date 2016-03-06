package me.crafter.mc.craftdotas.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import me.crafter.mc.craftdotas.object.Bounty;
import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.SingleHologram;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.object.building.AttackMove;
import me.crafter.mc.craftdotas.object.building.Base;
import me.crafter.mc.craftdotas.object.building.Building;
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
		int towinscore = game.getInt("towinscore");
		new Game(world, start, end, towinscore);
		Bukkit.getLogger().info("[CraftDotaS] World: " + world.getName() + " " + start + "~" + end);
		Bukkit.getLogger().info("[CraftDotaS] To win: " + towinscore);
		for (Map<?, ?> flow : game.getMapList("flow")){
			int tick = (int) flow.get("tick");
			String action = (String) flow.get("action");
			String content = ChatColor.translateAlternateColorCodes('&', (String) flow.get("content"));
			Bukkit.getLogger().info("[CraftDotaS] Gameflow: " + tick + ": " + action + " (" + content + ")");
			GameFlow.newGameFlow(tick, action, content);
		}
		
		// Step 2 - Teams.yml
		Bukkit.getLogger().info("[CraftDotaS] Loading Teams.yml...");
		FileConfiguration teams = YamlConfiguration.loadConfiguration(new File(folder, "Teams.yml"));
		for (Map<?, ?> team : teams.getMapList("teams")){
			int id = (int) team.get("id");
			String displayname = ChatColor.translateAlternateColorCodes('&', (String) team.get("displayname"));
			String prefix = ChatColor.translateAlternateColorCodes('&', (String) team.get("prefix"));
			String suffix = ChatColor.translateAlternateColorCodes('&', (String) team.get("suffix"));
			String chatprefix = ChatColor.translateAlternateColorCodes('&', (String) team.get("chat_prefix"));
			String chatsuffix = ChatColor.translateAlternateColorCodes('&', (String) team.get("chat_suffix"));
			Location spawn = (Location) team.get("spawn");
			List<ItemStack> startingarmorarray = (List<ItemStack>) team.get("starting_armor");
			List<ItemStack> startingitemarray = (List<ItemStack>) team.get("starting_item");
			if (startingarmorarray != null && startingitemarray != null){
				ItemStack[] startingarmor = startingarmorarray.toArray(new ItemStack[startingarmorarray.size()]);
				ItemStack[] startingitem = startingitemarray.toArray(new ItemStack[startingitemarray.size()]);
				new Team(id, displayname, prefix, suffix, chatprefix, chatsuffix, spawn, startingarmor, startingitem);
				Bukkit.getLogger().info("[CraftDotaS] \t" + id + "\t" + displayname + "\tWith items");
			} else {
				new Team(id, displayname, prefix, suffix, chatprefix, chatsuffix, spawn);
				Bukkit.getLogger().info("[CraftDotaS] \t" + id + "\t" + displayname);
			}
			Game.setScore(id, 0);
			Bukkit.getLogger().info("[CraftDotaS] \t" + id + "\t" + displayname);
		}
		// Step 3 - Buildings.yml
		Bukkit.getLogger().info("[CraftDotaS] Loading Buildings.yml...");
		FileConfiguration buildings = YamlConfiguration.loadConfiguration(new File(folder, "Buildings.yml"));
		for (Map<?, ?> building : buildings.getMapList("buildings")){
			int id = (int) building.get("id");
			String type = (String) building.get("type");
			int side = (int) building.get("side");
			double health = (int) building.get("health");
			double maxhealth = (int) building.get("maxhealth");
			double healthregen = (int) building.get("healthregen");
			String displayname = ChatColor.translateAlternateColorCodes('&', (String) building.get("displayname"));
			List<Location> locationsp = (List<Location>) building.get("locations");
			Location[] locationspre = {locationsp.get(0), locationsp.get(1)};
			Location locationmin = new Location(locationspre[0].getWorld(), Math.min(locationspre[0].getX(), locationspre[1].getX()), Math.min(locationspre[0].getY(), locationspre[1].getY()), Math.min(locationspre[0].getZ(), locationspre[1].getZ()));
			Location locationmax = new Location(locationspre[0].getWorld(), Math.max(locationspre[0].getX(), locationspre[1].getX()), Math.max(locationspre[0].getY(), locationspre[1].getY()), Math.max(locationspre[0].getZ(), locationspre[1].getZ()));
			Location[] locations = {locationmin, locationmax};
			boolean invulnerable = (boolean) building.get("invulnerable");
			List<Integer> unlocksl = ((List<Integer>) building.get("unlocks")); 
			int[] unlocks = new int[unlocksl.size()];
		    for (int i = 0; i < unlocks.length; i++)
		    {
		        unlocks[i] = unlocksl.get(i).intValue();
		    }
		    Building resultbuilding = null;
		    switch (type){
		    case "decoration":
		    	resultbuilding = new Decoration(side, id, health, maxhealth, healthregen, health < 0.1, displayname, locations, invulnerable, unlocks, 0, 0);
		    	break;
		    case "base":
		    	resultbuilding = new Base(side, id, health, maxhealth, healthregen, health < 0.1, displayname, locations, invulnerable, unlocks, 0, 0);
		    	break;
		    case "tower":
				AttackMove attackmove = AttackMove.valueOf((String) building.get("attackmove"));
				double damage = (double) building.get("damage");
				int attackspeed = (int) building.get("attackspeed");
				resultbuilding = new Tower(side, id, health, maxhealth, healthregen, health < 0.1, displayname, locations, invulnerable, unlocks, 0, 0, attackmove, damage, attackspeed);
		    	break;
		    }
			Bukkit.getLogger().info("[CraftDotaS] \t" + id + "\t" + displayname + "\t" + health + "/" + maxhealth + "(+" + healthregen + ")");
		    if (building.get("holograms") != null){
		    	for (Object o : (List<?>)building.get("holograms")){
		    		Map<?, ?> hologram = (Map<?, ?>)o;
					int hologramid = (int) hologram.get("id");
					Location hologramlocation = (Location) hologram.get("location");
					List<String> hologramlines = (List<String>) hologram.get("lines");
					SingleHologram singlehologram = new SingleHologram(hologramid, hologramlocation, hologramlines, resultbuilding);
					resultbuilding.addHologram(singlehologram);
					Bukkit.getLogger().info("[CraftDotaS] \t" + hologramid + "\tHologram attached");
				}
		    }
		}
		
		// Step 4 - Players.yml
		
		// Step 5 - Neutrals.yml
		
		// Step 6 - Creeps.yml
		
		// Step 7 - Bounty.yml
		Bukkit.getLogger().info("[CraftDotaS] Loading Bounty.yml...");
		FileConfiguration bounties = YamlConfiguration.loadConfiguration(new File(folder, "Bounty.yml"));
		for (Map<?, ?> bounty : bounties.getMapList("bounties")){
			String type = (String) bounty.get("type");
			int id, score;
			boolean scale, dropitemonground;
			List<ItemStack> itemstacks;
			switch (type){
			case "buildingdestroy":
				id = (int) bounty.get("id");
				score = (int) bounty.get("score");
				itemstacks = (List<ItemStack>) bounty.get("items");
				Building.getBuildings().get(id).setKillBounty(new Bounty(score, itemstacks, false, false));
				Bukkit.getLogger().info("[CraftDotaS] Bounty " + Building.getBuildings().get(id).getDisplayName() + itemstacks.toString());
				break;
			case "buildingdamage":
				id = (int) bounty.get("id");
				score = (int) bounty.get("score");
				itemstacks = (List<ItemStack>) bounty.get("items");
				scale = (boolean) bounty.get("scale");
				Building.getBuildings().get(id).setKillBounty(new Bounty(score, itemstacks, false, false));
				break;
			case "playerkill":
				id = (int) bounty.get("id");
				score = (int) bounty.get("score");
				itemstacks = (List<ItemStack>) bounty.get("items");
				scale = (boolean) bounty.get("scale");
				dropitemonground = (boolean) bounty.get("dropitemonground");
				Team.getTeam(id).setBounty(new Bounty(score, itemstacks, dropitemonground, scale));
				Bukkit.getLogger().info("[CraftDotaS] Bounty " + Team.getTeam(id).getDisplayName() + itemstacks.toString());
				break;
			}
		}
		// Step 8 - HUD
		Bukkit.getLogger().info("[CraftDotaS] Loading Hud.yml...");
		FileConfiguration hud = YamlConfiguration.loadConfiguration(new File(folder, "Hud.yml"));
		String actionbar = ChatColor.translateAlternateColorCodes('&', hud.getString("action_bar"));
		new HudAction(actionbar);
		Bukkit.getLogger().info("[CraftDotaS] ActionBar: " + actionbar);
		// test
//		FileConfiguration itemtest = new YamlConfiguration();
//		for (Player p : Bukkit.getOnlinePlayers()){
//			itemtest.set(p.getName() + "_armor", p.getInventory().getArmorContents());
//			itemtest.set(p.getName() + "_item", p.getInventory().getContents());
//		}
//		itemtest.save(new File(folder, "ItemTest.yml"));
	}
	
}
