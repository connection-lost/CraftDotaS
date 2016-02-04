package me.crafter.mc.craftdotas;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.crafter.mc.craftdotas.action.FileLoader;
import me.crafter.mc.craftdotas.object.Game;
import net.md_5.bungee.api.ChatColor;

public class DotaCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args){
    	// Don't have to check dota because this class will only register one command
    	if (args.length < 1){
    		showUsage(sender);
    		return true;
    	}
    	switch (args[0]){
    	case "load":
    		if (args.length < 2){
    			sender.sendMessage(ChatColor.RED + "/dota load <name>");
    			break;
    		}
    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "Loading map " + args[1]);
    		try {
				FileLoader.loadGame(CraftDotaS.plugin.getDataFolder(), args[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break;
    	case "start":
    		// START GAME
    		break;
    	case "tick":
    		if (args.length < 2){
    			showUsage(sender);
    			return true;
    		}
    		try {
        		Game.setTick(Integer.parseInt(args[1]));
    		} catch (Exception ex){
    			showUsage(sender);
    			return true;
    		}
    		break;
    	default:
    		showUsage(sender);
    		break;
    	}
    	
    	return true;
    }
    
    public void showUsage(CommandSender sender){
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "CraftDotaS插件指令说明");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "/dota load <world> - 在指定世界加载配置");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "/dota start <world> - 在指定世界开始游戏");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "/dota tick <tick> - 设置当前游戏tick时间");
    }

}
