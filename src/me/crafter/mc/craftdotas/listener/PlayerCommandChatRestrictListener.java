package me.crafter.mc.craftdotas.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.crafter.mc.craftdotas.object.Game;
import net.md_5.bungee.api.ChatColor;

public class PlayerCommandChatRestrictListener implements Listener {

	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent event){
		if (event.getPlayer().getWorld() != Game.getWorld()) return;
		if (event.getMessage().toLowerCase().startsWith("/kill") || event.getMessage().toLowerCase().contains(":kill")){
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "是谁的声音。。。不能逃！");
		}
	}
	
}
