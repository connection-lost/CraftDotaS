package me.crafter.mc.craftdotas.object;

import java.util.Map;

import org.bukkit.World;

public class Game {

	private static World world;
	private static Map<Integer, Integer> scores;
	private static int tick;
	private static boolean ison; // Whether game is running, it will turn off if game is finished
	
	public Game(World world_, int tick_){
		world = world_;
		tick = tick_;
		ison = false;
	}

	public void start(){
		ison = true;
	}
	
	public void stop(){
		ison = false;
	}

	//getters
	public static World getWorld() {return world;}
	public static int getTick(){return tick;}
	public static Map<Integer, Integer> getScores() {return scores;}
	public static int getScore(int side) {return scores.get(side);}
	public static boolean isOn() {return ison;}

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
