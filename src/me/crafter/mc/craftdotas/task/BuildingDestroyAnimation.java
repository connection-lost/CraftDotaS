package me.crafter.mc.craftdotas.task;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitTask;

import me.crafter.mc.craftdotas.CraftDotaS;
import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.building.Building;

public class BuildingDestroyAnimation implements Runnable{

	private int tick = 0;
	private int taskid;
	private Building building;
	private static Random random = new Random();
	
	public BuildingDestroyAnimation(Building building_){
		building = building_;
	}
	
	public void feedId(int id){
		taskid = id;
	}
	
	@Override
	public void run() {
		if (tick < 100){
			Game.getWorld().spigot().playEffect(building.getLocationMid(), Effect.FLAME, 0, 0, 10F-tick/10F, 10F-tick/10F, 10F-tick/10F, 0.5F-tick/200F, 120-tick, 96);
			if (tick < 80 && tick % 4 == 0){
				Game.getWorld().playSound(building.getLocationMid(), Sound.AMBIENT_CAVE, 3.5F, tick/60F + 0.5F);
			}
		} else if (tick < 104){
			Game.getWorld().strikeLightningEffect(building.getLocationMid());
			Game.getWorld().spigot().playEffect(building.getLocationMid(), Effect.EXPLOSION_HUGE, 0, 0, 3F, 3F, 3F, 0, 5, 96);
		} else if (tick < 200){
			Game.getWorld().spigot().playEffect(building.getLocationMid(), Effect.FLAME, 0, 0, tick/10F-10F, tick/10F-10F, tick/10F-10F, tick/200F-0.2F, tick - 80, 96);
			if (random.nextInt(6) == 1){
				Game.getWorld().spigot().playEffect(building.getLocationMid(), Effect.EXPLOSION_HUGE, 0, 0, 3F, 3F, 3F, 0, 2, 96);
				Game.getWorld().playSound(building.getLocationMid(), Sound.ENTITY_GENERIC_EXPLODE, 3.5F, 0.6F + random.nextFloat()/2F);
			}
		} else if (tick < 600){
			if (random.nextInt(6) == 1){
				Game.getWorld().spigot().playEffect(building.getLocationMid(), Effect.EXPLOSION_HUGE, 0, 0, 3F, 3F, 3F, 0, 2, 96);
				Game.getWorld().playSound(building.getLocationMid(), Sound.ENTITY_GENERIC_EXPLODE, 3.5F, 0.6F + random.nextFloat()/2F);
			}
			Game.getWorld().spigot().playEffect(building.getLocationMid(), Effect.FLAME, 0, 0, 4F, 6F, 4F, 0, 8, 96);
		} else if (tick == 600) {
			cancel();
		}
		tick ++;
	}

	public void cancel(){
		Bukkit.getScheduler().cancelTask(taskid);
	}
	
	public static void play(Building building){
		BuildingDestroyAnimation task = new BuildingDestroyAnimation(building);
		BukkitTask btask = Bukkit.getScheduler().runTaskTimer(CraftDotaS.plugin, task, 1L, 1L);
		task.feedId(btask.getTaskId());
	}
	
}
