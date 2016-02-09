package me.crafter.mc.craftdotas.object;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;

import me.crafter.mc.craftdotas.CraftDotaS;
import me.crafter.mc.craftdotas.task.GameTick;

public class Game {

	private static World world;
	private static Map<Integer, Integer> scores;
	private static int tick;
	private static boolean ison; // Whether game is running, it will turn off if game is finished
	private static int start;
	private static int end;
	private static int towinscore;
	public static BukkitTask task;
	
	public Game(World world_, int start_, int end_, int towinscore_){
		world = world_;
		tick = start_;
		start = start_;
		end = end_;
		towinscore = towinscore_;
		ison = false;
	}

	public static void ready(){
		if (task == null){
			task = Bukkit.getScheduler().runTaskTimer(CraftDotaS.plugin, new GameTick(), 1L, 1L);
		}
	}
	
	public static void start(){
		ison = true;
	}
	
	public static void stop(){
		ison = false;
	}
	
	public static void end(){
		if (task != null){
			task.cancel();
			task = null;
		}
	}

	//getters
	public static World getWorld() {return world;}
	public static int getTick(){return tick;}
	public static Map<Integer, Integer> getScores() {return scores;}
	public static int getScore(int side) {return scores.get(side);}
	public static boolean isOn() {return ison;}
	public static int getStart(){return start;}
	public static int getEnd(){return end;}
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
