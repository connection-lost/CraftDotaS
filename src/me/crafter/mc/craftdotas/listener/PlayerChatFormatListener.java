package me.crafter.mc.craftdotas.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.Team;

public class PlayerChatFormatListener implements Listener{

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		if (player.getWorld() == Game.getWorld()){
			Team team = Team.getPlayerTeam(player);
			event.setFormat(team.getChatPrefix() + "%s" + team.getChatSuffix() + ": %s");
		}
	}
	
}
