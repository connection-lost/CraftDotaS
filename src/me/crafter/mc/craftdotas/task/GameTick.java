package me.crafter.mc.craftdotas.task;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.crafter.mc.craftdotas.action.GameFlow;
import me.crafter.mc.craftdotas.action.HudAction;
import me.crafter.mc.craftdotas.action.PlayerFoodLevelHandler;
import me.crafter.mc.craftdotas.object.Game;
import me.crafter.mc.craftdotas.object.building.Building;

/* This is the master tick for the game.
 * All other game involved ticks are initiated here.
 */

public class GameTick implements Runnable{

	int tick = 0;
	
	@Override
	public void run() {
		// The following action does not require game to be running
		HudAction.execute(Game.getTick());
		PlayerFoodLevelHandler.handleSprint();
		// The following action requires game is running
		if (Game.isOn()){
			GameFlow.execute(Game.getTick());
			Building.tickAll();
			Game.tickAdd();
			
			// Rev Temp
			if (Game.getTick() > 0 && Game.getTick() % 600 == 0){
				for (Player p : Game.getWorld().getPlayers()){
					p.getInventory().addItem(new ItemStack(Material.EMERALD));
				}
			}
			
		}
		tick ++;
	}
}
