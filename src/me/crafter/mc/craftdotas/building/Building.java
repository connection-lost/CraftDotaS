package me.crafter.mc.craftdotas.building;

import org.bukkit.Location;

public class Building {

	// Which side is the building at. Default 0 = radiant, 1 = dire
	private int side;
	// Building specific id in tower file
	private int id;
	// Current Building health
	private double health;
	// Building max health
	private double maxhealth;
	// Regeneration of building per tick
	private double healthregen;
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
	public String getDisplayName() {return displayName;}
	public Location[] getLocations() {return locations;}
	public boolean isInvulnerable() {return invulnerable;}
	public int[] getUnlocks() {return unlocks;}

	public void setHealth(double newhealth) {health = newhealth;}
	public void setInvulnerable(boolean newinvulnerable) {invulnerable = newinvulnerable;}
	public boolean damage(double damage){
		health = Math.max(0, health - damage);
		if (health < 0.01) return true;
		else return false;
	}

}
