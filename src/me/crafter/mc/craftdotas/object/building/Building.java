package me.crafter.mc.craftdotas.object.building;

import java.util.Map;

import org.bukkit.Location;

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
	// What building will become attackble if this building falls
	private int[] unlocks;
	// Static buildings
	private static Map<Integer, Building> buildings;
	// Points
	private int damagescore;
	private int destroyscore;

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
	public int getDamageScore() {return damagescore;}
	public int getDestroyScore() {return destroyscore;}

	public void setHealth(double newhealth) {health = newhealth;}
	public void setDestroyed(boolean newdestroyed) {destroyed = newdestroyed;}
	public void setInvulnerable(boolean newinvulnerable) {invulnerable = newinvulnerable;}
	
	public boolean damage(double damage){
		health = Math.max(0, health - damage);
		if (health < 0.01){
			destroyed = true;
			return true;
		}
		else return false;
	}
	
	public void tick(){
		// Base building class does nothing
	}

}