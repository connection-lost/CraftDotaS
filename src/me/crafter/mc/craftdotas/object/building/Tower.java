package me.crafter.mc.craftdotas.object.building;

public class Tower extends Building{
	
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
