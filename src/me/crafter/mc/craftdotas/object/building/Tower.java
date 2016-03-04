package me.crafter.mc.craftdotas.object.building;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;

public class Tower extends Building{
	
	Random random = new Random();
	
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
	
	public void tickAttackCooldown(){
		if (attackcooldown <= 0){
			attackcooldown = 5 + random.nextInt(5);
		}
		attackcooldown --;
	}
	
	public void resetAttackCooldown(){
		attackcooldown = attackspeed;
	}
	
	public boolean isAttackReady(){
		return attackcooldown == 0;
	}
	
	@Override
	public void tick(){
		super.tick();
		if (isAttackReady()){
			if (attack()) resetAttackCooldown();
		}
		tickAttackCooldown();
	}
	
	private boolean attack(){
		boolean attacked = false;
		switch (getAttackMove()){
		case ARROW:
			break;
		case DAMAGE:
			break;
		case FIREBALL:
			break;
		case LASER:
			break;
		case ARROW_ALL:
			for (Player player : getNearbyPlayers(8)){
				if (Team.getPlayerTeam(player).getId() != getSide()){
					attacked = true;
					Location playerlocation = player.getLocation();
					playerlocation.getWorld().playSound(playerlocation, Sound.SHOOT_ARROW, 0.4F, 1F);
					playerlocation.getWorld().spigot().playEffect(playerlocation.add(0D, 4D, 0D), Effect.CRIT, 0, 0, 0.1F, 0.5F, 0.1F, 0.1F, 100, 32);
					Arrow arrow = playerlocation.getWorld().spawn(playerlocation.add(0D, 4D, 0D), Arrow.class);
					arrow.setVelocity(new Vector(0, -1.5D, 0));
					arrow.spigot().setDamage(damage);
					arrow.setCritical(true);
				}
			}
			break;
		case DAMAGE_ALL:
			for (Player player : getNearbyPlayers(8)){
				if (Team.getPlayerTeam(player).getId() != getSide()){
					attacked = true;
					Location playerlocation = player.getLocation();
					playerlocation.getWorld().spigot().playEffect(playerlocation.add(0D, 0.3D, 0D), Effect.POTION_BREAK, 0, 0, 0.1F, 0.5F, 0.1F, 0.1F, 100, 32);
					player.damage(damage, player); // is it ok?
				}
			}
			break;
		case FIREBALL_ALL:
			for (Player player : getNearbyPlayers(8)){
				if (Team.getPlayerTeam(player).getId() != getSide()){
					attacked = true;
					Location playerlocation = player.getLocation();
					playerlocation.getWorld().playSound(playerlocation, Sound.GHAST_FIREBALL, 0.4F, 1F);
					playerlocation.getWorld().spigot().playEffect(playerlocation.add(0D, 4D, 0D), Effect.FLAME, 0, 0, 0.1F, 0.5F, 0.1F, 0.1F, 100, 32);
					LargeFireball fireball = playerlocation.getWorld().spawn(playerlocation.add(0D, 4D, 0D), LargeFireball.class);
					fireball.setIsIncendiary(false);
					fireball.setYield(0);
					fireball.setVelocity(new Vector(0, -1.5D, 0));
				}
			}
			break;
		case LASER_ALL:
			break;
		}
		return attacked;
	}

}
