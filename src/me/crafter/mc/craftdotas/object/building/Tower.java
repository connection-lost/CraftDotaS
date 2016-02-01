package me.crafter.mc.craftdotas.object.building;

import org.bukkit.Location;

public class Tower extends Building{
	
	public Tower(int side_, int id_, double health_, double maxhealth_, double healthregen_, boolean destroyed_,
			String displayname_, Location[] locations_, boolean invulnerable_, int[] unlocks_, int damagescore_, int destroyscore_,
			AttackMove attackmove_, double damage_, int attackspeed_) {
		super(side_, id_, health_, maxhealth_, healthregen_, destroyed_, displayname_, locations_, invulnerable_, unlocks_, damagescore_, destroyscore_);
		attackmove = attackmove_;
		damage = damage_;
		attackspeed = attackspeed_;
		attackcooldown = 0;
	}

	// Building attack method
	private AttackMove attackmove;
	// Building dealing damage
	private double damage;
	// Building attack speed in ticks
	private int attackspeed;
	// Building remaining attack interval
	private int attackcooldown;
		
	public double getDamage() {return damage;}
	public AttackMove getAttackMove() {return attackmove;}
	public int getAttackSpeed() {return attackspeed;}
	public int getAttackCooldown() {return attackcooldown;}

	public void setAttackCooldown(int newattackcooldown) {attackcooldown = newattackcooldown;}
	
	public boolean tickAttackCooldown(){
		if (attackcooldown == 0){
			attackcooldown = attackspeed - 1;
			return true;
		} else {
			attackcooldown -= 1;
			return false;
		}
	}
	
	@Override
	public void tick(){
		super.tick();
		// TODO check and attack
	}

}
