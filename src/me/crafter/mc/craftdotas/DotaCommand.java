package me.crafter.mc.craftdotas;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.crafter.mc.craftdotas.action.FileLoader;
import me.crafter.mc.craftdotas.action.GameFlow;
import me.crafter.mc.craftdotas.action.HudAction;
import me.crafter.mc.craftdotas.listener.*;
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
    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "�����ͼ " + args[1]);
    		try {
				FileLoader.loadGame(CraftDotaS.plugin.getDataFolder(), args[1]);
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "����ɹ�");
	    		Game.ready();
	    		// Register listeners
	    		Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new ModifyWorldListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PlayerCommandChatRestrictListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PreventBlockChangeListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PvpDeathListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new TowerDamageListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChatFormatListener(), CraftDotaS.plugin);
	    		Bukkit.getServer().getPluginManager().registerEvents(new PlayerTeleportBaseListener(), CraftDotaS.plugin);
			} catch (Exception e) {
				e.printStackTrace();
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.RED + "����ʧ��");
			}
    		break;
    	case "start":
    		if (Game.task == null){
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.RED + "��Ϸ��δ����");
    		} else {
    			Game.start();
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "��Ϸ��ʼ");
    		}
    		break;
    	case "stop":
    		if (Game.task == null){
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.RED + "��Ϸ��δ����");
    		} else {
    			Game.stop();
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "��Ϸֹͣ");
    		}
    		break;
    	case "end":
    		Game.end();
    		for (Building building : Building.getBuildings()){
    			building.clearHologram();
    		}
    		HudAction.removeAll();
    		Game.removeAll();
    		Team.removeAll();
    		Building.removeAll();
    		GameFlow.removeAll();
    		HandlerList.unregisterAll(CraftDotaS.plugin);
    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "��Ϸ�ر�");
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
    	case "unbreakable":
    		if (sender instanceof Player){
    			Player player = (Player)sender;
    			@SuppressWarnings("deprecation")
				ItemStack item = player.getItemInHand();
    			if (item != null){
    				ItemMeta itemmeta = item.getItemMeta();
    				if (itemmeta.spigot().isUnbreakable()){
    					itemmeta.spigot().setUnbreakable(false);
    					item.setItemMeta(itemmeta);
    		    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.YELLOW + "ȡ����Ʒ�;�����");
    				} else {
    					itemmeta.spigot().setUnbreakable(true);
    					item.setItemMeta(itemmeta);
    		    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.YELLOW + "������Ʒ�;�����");
    				}
    			} else {
    				
    			}
    		} else {
    			return false;
    		}
    		break;
    	default:
    		showUsage(sender);
    		break;
    	}
    	
    	return true;
    }
    
    public void showUsage(CommandSender sender){
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "CraftDotaS ���ָ��˵��");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota load <world> - ��ָ�������������");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota start - ��ʼ��Ϸ");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota stop - ��ͣ��Ϸ");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota end - �ر���Ϸ");
    	sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "/dota tick <tick> - ���õ�ǰ��Ϸtickʱ��");
    }

}
