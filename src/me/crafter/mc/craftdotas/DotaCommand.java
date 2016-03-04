package me.crafter.mc.craftdotas;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

import me.crafter.mc.craftdotas.action.FileLoader;
import me.crafter.mc.craftdotas.action.GameFlow;
import me.crafter.mc.craftdotas.listener.InventoryListener;
import me.crafter.mc.craftdotas.listener.ModifyWorldListener;
import me.crafter.mc.craftdotas.listener.PlayerChatFormatListener;
import me.crafter.mc.craftdotas.listener.PlayerCommandChatRestrictListener;
import me.crafter.mc.craftdotas.listener.PreventBlockChangeListener;
import me.crafter.mc.craftdotas.listener.PvpDamageListener;
import me.crafter.mc.craftdotas.listener.PvpDeathListener;
import me.crafter.mc.craftdotas.listener.TowerDamageListener;
import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;
import me.crafter.mc.craftdotas.object.building.Building;
import me.crafter.mc.craftdotas.utils.PlayerUtils;
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
    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "载入地图 " + args[1]);
    		try {
				FileLoader.loadGame(CraftDotaS.plugin.getDataFolder(), args[1]);
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "载入成功");
	    		Game.ready();
	    		// Register listeners
	    		Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new ModifyWorldListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PlayerCommandChatRestrictListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PreventBlockChangeListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PvpDamageListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PvpDeathListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new TowerDamageListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChatFormatListener(), CraftDotaS.plugin);
			} catch (Exception e) {
				e.printStackTrace();
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.RED + "载入失败");
			}
    		break;
    	case "start":
    		if (Game.task == null){
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.RED + "游戏尚未载入");
    		} else {
    			Game.start();
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "游戏开始");
    		}
    		break;
    	case "stop":
    		if (Game.task == null){
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.RED + "游戏尚未载入");
    		} else {
    			Game.stop();
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "游戏停止");
    		}
    		break;
    	case "end":
    		Game.end();
    		for (int key : Building.getBuildings().keySet()){
    			Building building = Building.getBuildings().get(key);
    			building.clearHologram();
    		}
    		Game.removeAll();
    		Team.removeAll();
    		Building.removeAll();
    		GameFlow.removeAll();
    		HandlerList.unregisterAll(CraftDotaS.plugin);
    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "游戏关闭");
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
    	case "join":
    		if (args.length != 3){
    			showUsage(sender);
    		} else {
    			try {
        			PlayerUtils.joinTeam(args[1], Integer.parseInt(args[2]));
    			} catch (Exception ex){
    				showUsage(sender);
    			}
    		}
    		break;
    	default:
    		showUsage(sender);
    		break;
    	}
    	
    	return true;
    }
    
    public void showUsage(CommandSender sender){
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "CraftDotaS 插件指令说明");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota load <world> - 在指定世界加载配置");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota start - 开始游戏");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota stop - 暂停游戏");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota end - 关闭游戏");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota tick <tick> - 设置当前游戏tick时间");
    }

}
