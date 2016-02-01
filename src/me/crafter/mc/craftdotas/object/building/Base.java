package me.crafter.mc.craftdotas.object.building;

import org.bukkit.Location;

public class Base extends Building{

	public Base(int side_, int id_, double health_, double maxhealth_, double healthregen_, boolean destroyed_,
			String displayname_, Location[] locations_, boolean invulnerable_, int[] unlocks_, int damagescore_, int destroyscore_) {
		super(side_, id_, health_, maxhealth_, healthregen_, destroyed_, displayname_, locations_, invulnerable_, unlocks_, damagescore_, destroyscore_);
	}

	@Override
	public void tick(){
		super.tick();
		// Base building do nothing else
	}
	
}
