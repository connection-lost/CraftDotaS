package me.crafter.mc.craftdotas;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.crafter.mc.craftdotas.listener.InventoryListener;

public class CraftDotaS extends JavaPlugin {

	public static CraftDotaS plugin;
	
	public void onEnable(){
		plugin = this;
    	getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    	Bukkit.getPluginCommand("dota").setExecutor(new DotaCommand());
    	Bukkit.getPluginCommand("dota").setTabCompleter(new DotaCommandCompleter());
    }
	
    public void onDisable(){
    	for (Hologram hologram : HologramsAPI.getHolograms(this)){
    		hologram.clearLines();
    		hologram.delete();
    		Bukkit.getLogger().info("Remove hologram " + hologram.toString());
    	}
    }
	
}
