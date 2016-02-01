package me.crafter.mc.craftdotas.object.building;

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
	private String displayName;
	// Building min & max locations
	private Location[] locations;
	// Whether building is currently invulnerable
	private boolean invulnerable;
	// What building will become attackble if this building falls
	private int[] unlocks;
		
	public int getSide() {return side;}
	public int getId() {return id;}
	public double getHealth() {return health;}
	public double getMaxHealth() {return maxhealth;}
	public double getHealthRegen() {return healthregen;}
	public boolean isDestroyed() {return destroyed;}
	public String getDisplayName() {return displayName;}
	public Location[] getLocations() {return locations;}
	public boolean isInvulnerable() {return invulnerable;}
	public int[] getUnlocks() {return unlocks;}

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
