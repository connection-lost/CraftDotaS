package me.crafter.mc.craftdotas.object.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.crafter.mc.craftdotas.object.Bounty;
import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.SingleHologram;
import me.crafter.mc.craftdotas.task.BuildingDestroyAnimation;
import net.md_5.bungee.api.ChatColor;

public class Building {

	// Which side is the building at. Default 0 = neutral, 1 = radiant, 2 = dire
	private int side;
	// Building specific id in tower file
	private int id;
	// Current Building health
	private double health;
	// Building max health
	private double maxhealth;
	// Regeneration of building per tick
	private double healthregen;
	// Whether this building is destroyed
	private boolean destroyed;
	// Building display name
	private String displayname;
	// Building min & max locations
	private Location[] locations;
	// Building center
	private Location locationmid;
	// Whether building is currently invulnerable
	private boolean invulnerable;
	// What building will become attackable if this building falls
	private int[] unlocks;
	// Static buildings
	private static Map<Integer, Building> buildings = new HashMap<Integer, Building>();
	// Points
	private Bounty damagebounty;
	private Bounty killbounty;
	// Hologram
	private List<SingleHologram> holograms = new ArrayList<SingleHologram>();
	// Center XZ
	private double[] centerxz;
	
	private Player lastdamager;

	public Building(int side_, int id_, double health_, double maxhealth_, double healthregen_, boolean destroyed_, 
			String displayname_, Location[] locations_, boolean invulnerable_, int[] unlocks_, int damagescore_, int destroyscore_){
		side = side_;
		id = id_;
		health = health_;
		maxhealth = maxhealth_;
		healthregen = healthregen_;
		destroyed = destroyed_;
		displayname = displayname_;
		locations = locations_;
		invulnerable = invulnerable_;
		unlocks = unlocks_;
		buildings.put(id, this);
		centerxz = new double[2];
		centerxz[0] = (locations[0].getX() + locations[1].getX()) / 2;
		centerxz[1] = (locations[0].getZ() + locations[1].getZ()) / 2;
		locationmid = new Location(locations[0].getWorld(), centerxz[0] + 0.5, (locations[0].getY() + locations[1].getY()) / 2, centerxz[1] + 0.5);
	}
		
	public int getSide() {return side;}
	public int getId() {return id;}
	public double getHealth() {return health;}
	public double getMaxHealth() {return maxhealth;}
	public double getHealthRegen() {return healthregen;}
	public boolean isDestroyed() {return destroyed;}
	public String getDisplayName() {return displayname;}
	public Location[] getLocations() {return locations;}
	public Location getLocationMid() {return locationmid;}
	public boolean isInvulnerable() {return invulnerable;}
	public int[] getUnlocks() {return unlocks;}
	public static Map<Integer, Building> getBuildings() {return buildings;}
	public Bounty getDamageBounty() {return damagebounty;}
	public Bounty getKillBounty() {return killbounty;}
	public Player getLastDamager() {return lastdamager;}
	
	public List<Player> getNearbyPlayers(double distance) {
		List<Player> nearby = new ArrayList<Player>();
		double distancesquared = distance * distance;
		for (Player player : Game.getWorld().getPlayers()){
			Location location = player.getLocation();
			double playerxdiff = location.getX() - centerxz[0];
			double playerzdiff = location.getZ() - centerxz[1];
			if (playerxdiff * playerxdiff + playerzdiff * playerzdiff < distancesquared){
				nearby.add(player);
			}
		}
		return nearby;
	}

	public void setHealth(double newhealth) {health = newhealth;}
	public void setDestroyed(boolean newdestroyed) {destroyed = newdestroyed;}
	public void setInvulnerable(boolean newinvulnerable) {invulnerable = newinvulnerable;}
	public void setDamageBounty(Bounty bounty) {damagebounty = bounty;}
	public void setKillBounty(Bounty bounty) {killbounty = bounty;}
	public void setLastDamager(Player player) {lastdamager = player;}
	
	public static void tickAll(){
		for (int bid : buildings.keySet()){
			buildings.get(bid).tick();
		}
	}
	
	public void addHologram(SingleHologram hologram){
		hologram.setHost(this);
		holograms.add(hologram);
		updateHologram();
	}
	public void clearHologram(){
		for (SingleHologram hologram : holograms){
			hologram.destroy();
		}
		holograms = new ArrayList<SingleHologram>();
	}
	public void updateHologram(){
		for (SingleHologram hologram : holograms){
			hologram.update();
		}
	}
	public void refreshHologram(){
		for (SingleHologram hologram : holograms){
			hologram.refresh();
		}
	}
	
	public boolean damage(double damage){
		if (isInvulnerable()) return false;
		health = Math.max(0, health - damage);
		for (SingleHologram singlehologram : holograms){
			singlehologram.update();
		}
		if (isDestroyed()){
			return false;
		} else if (health < 0.01){
			this.kill();
			health = 0D;
		}
		return true;
	}
	
	public void tick(){
		// Base building class does nothing
	}
	
	public void kill(){
		setDestroyed(true);
		for (int unlock : unlocks){
			if (buildings.containsKey(unlock)){
				buildings.get(unlock).setInvulnerable(false);
				buildings.get(unlock).updateHologram();
			}
		}
		updateHologram();
		Bukkit.broadcastMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.RESET + getDisplayName() + ChatColor.RED + " ±»´Ý»ÙÁË¡£¡£¡£");
		BuildingDestroyAnimation.play(this);
		if (getKillBounty() != null){
			getKillBounty().award(getLastDamager(), getLastDamager().getLocation());
		}
	}
	
	public static void removeAll(){
		buildings = new HashMap<Integer, Building>();
	}

}
