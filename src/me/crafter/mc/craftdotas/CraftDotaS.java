package me.crafter.mc.craftdotas;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftDotaS extends JavaPlugin {


	public void onEnable(){
    	getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }
	
	
    public void onDisable(){
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args){
    	
    	return true;
    }
	
}
