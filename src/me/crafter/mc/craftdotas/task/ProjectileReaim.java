package me.crafter.mc.craftdotas.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;

import me.crafter.mc.craftdotas.CraftDotaS;

public class ProjectileReaim implements Runnable{

	private LivingEntity target;
	private Projectile self;
	
	public ProjectileReaim(Projectile self_, LivingEntity target_){
		target = target_;
		self = self_;
	}

	@Override
	public void run() {
		if (target.isValid() && self.isValid() && !target.isDead() && !self.isOnGround()){
			double speed = self.getVelocity().length();
			self.setVelocity(target.getEyeLocation().subtract(self.getLocation()).toVector().normalize().multiply(speed));
		}
	}
	
	public static void registerReaim(Projectile projectile, LivingEntity who, int tick){
		Bukkit.getScheduler().runTaskLater(CraftDotaS.plugin, new ProjectileReaim(projectile, who), tick);
	}
	
}
