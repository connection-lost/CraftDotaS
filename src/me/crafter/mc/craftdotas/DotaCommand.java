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
    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.AQUA + "�����ͼ " + args[1]);
    		try {
				FileLoader.loadGame(CraftDotaS.plugin.getDataFolder(), args[1]);
	    		sender.sendMessage(ChatColor.GOLD + "[CraftDotaS] " + ChatColor.GREEN + "����ɹ�");
	    		Game.ready();
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
