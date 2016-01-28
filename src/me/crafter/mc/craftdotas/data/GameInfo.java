package me.crafter.mc.craftdotas.data;

import java.util.List;
import java.util.Map;

import org.bukkit.World;

import me.crafter.mc.craftdotas.building.Building;

public class GameInfo {

	private static World world;
	private static List<Building> towers;
	private static int tick;
	private static Map<String, Integer> score;
	
	
	public GameInfo(){
		
	}


	//getters
	public static World getWorld() {
		return world;
	}

	public static List<Building> getTowers() {
		return towers;
	}
	
	public static int getTick(){
		return tick;
	}

	//setters
	public static void tickAdd(){
		tick ++;
	}
	
	public static void setTick(int newtick){
		tick = newtick;
	}
	
	
}
