package me.crafter.mc.craftdotas.object.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.crafter.mc.craftdotas.object.Bounty;
import me.crafter.mc.craftdotas.object.SingleHologram;
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
	}
		
	public int getSide() {return side;}
	public int getId() {return id;}
	public double getHealth() {return health;}
	public double getMaxHealth() {return maxhealth;}
	public double getHealthRegen() {return healthregen;}
	public boolean isDestroyed() {return destroyed;}
	public String getDisplayName() {return displayname;}
	public Location[] getLocations() {return locations;}
	public boolean isInvulnerable() {return invulnerable;}
	public int[] getUnlocks() {return unlocks;}
	public static Map<Integer, Building> getBuildings() {return buildings;}
	public Bounty getDamageBounty() {return damagebounty;}
	public Bounty getKillBounty() {return killbounty;}

	public void setHealth(double newhealth) {health = newhealth;}
	public void setDestroyed(boolean newdestroyed) {destroyed = newdestroyed;}
	public void setInvulnerable(boolean newinvulnerable) {invulnerable = newinvulnerable;}
	public void setDamageBounty(Bounty bounty) {damagebounty = bounty;}
	public void setKillBounty(Bounty bounty) {killbounty = bounty;}
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
			}
		}
		Bukkit.broadcastMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.RESET + getDisplayName() + ChatColor.RED + " ±»´Ý»ÙÁË¡£¡£¡£");
		// TODO Handle animations and such
	}

}
