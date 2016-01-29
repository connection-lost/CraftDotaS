package me.crafter.mc.craftdotas.data;

import java.util.Map;

import org.bukkit.World;

import me.crafter.mc.craftdotas.building.Building;

public class GameInfo {

	private static World world;
	// <Side ID, Building>
	private static Map<Integer, Building> towers;
	// <Side ID, Points>
	private static Map<Integer, Integer> scores;
	private static int tick;
	
	public GameInfo(){
		
	}


	//getters
	public static World getWorld() {return world;}
	public static Map<Integer, Building> getTowers() {return towers;}
	public static int getTick(){return tick;}
	public static Map<Integer, Integer> getScores() {return scores;}
	public static int getScore(int side) {return scores.get(side);}

	//setters
	public static void tickAdd(){
		tick ++;
	}
	
	public static void setTick(int newtick){
		tick = newtick;
	}
	
	public static void setScore(int side, int score){
		scores.put(side, score);
	}
	
	public static void addScore(int side, int add){
		scores.put(side, scores.get(side) + add);
	}
	
	
}
