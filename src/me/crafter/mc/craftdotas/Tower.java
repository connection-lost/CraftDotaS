package me.crafter.mc.craftdotas;

import org.bukkit.Location;

public class Tower {

	private int side;
	private int id;
	private double health;
	private double maxhealth;
	private double damage;
	private int attackspeed;
	private int attackcooldown;
	private String displayName;
	private Location[] locations;
	private boolean invulnerable;
	private int[] unlocks;
	
	
	
	
	
	
	
	
	
	
	
	
	public int getSide() {return side;}
	public int getId() {return id;}
	public double getHealth() {return health;}
	public double getgetMaxHealth() {return maxhealth;}
	public double getdamage() {return damage;}
	public int getAttackSpeed() {return attackspeed;}
	public int getAttackCooldown() {return attackcooldown;}
	public String getDisplayName() {return displayName;}
	public Location[] getLocations() {return locations;}
	public boolean isInvulnerable() {return invulnerable;}
	public int[] getUnlocks() {return unlocks;}

	public void setHealth(double newhealth) {health = newhealth;}
	public void setAttackCooldown(int newattackcooldown) {attackcooldown = newattackcooldown;}
	public void setInvulnerable(boolean newinvulnerable) {invulnerable = newinvulnerable;}
	
}
