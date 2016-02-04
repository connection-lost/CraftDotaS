package me.crafter.mc.craftdotas.object;

import java.util.Map;

import org.bukkit.World;

public class Game {

	private static World world;
	private static Map<Integer, Integer> scores;
	private static int tick;
	private static boolean ison; // Whether game is running, it will turn off if game is finished
	private static int start;
	private static int end;
	private static int killscore;
	private static int deathscore;
	private static int towinscore;
	
	public Game(World world_, int start_, int end_, int killscore_, int deathscore_, int towinscore_){
		world = world_;
		tick = start_;
		start = start_;
		end = end_;
		killscore = killscore_;
		deathscore = deathscore_;
		towinscore = towinscore_;
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
	public static int getStart(){return start;}
	public static int getEnd(){return end;}
	public static int getKillScore(){return killscore;}
	public static int getDeathScore(){return deathscore;}
	public static int getToWinScore(){return towinscore;}

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
