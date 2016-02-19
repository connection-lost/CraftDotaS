package me.crafter.mc.craftdotas.task;

import me.crafter.mc.craftdotas.action.GameFlow;
import me.crafter.mc.craftdotas.action.HudAction;
import me.crafter.mc.craftdotas.object.Game;

/* This is the master tick for the game.
 * All other game involved ticks are initiated here.
 */

public class GameTick implements Runnable{

	int tick = 0;
	
	@Override
	public void run() {
		// The following action does not require game to be running
		HudAction.execute(Game.getTick());
		// TEMP hologram need
//		for (int bid : Building.getBuildings().keySet()){
//			if (bid % 9 == (tick) % 9 && tick % 50 == 0){
//				Building building = Building.getBuildings().get(bid);
//				building.refreshHologram();
//			}
//		}
		// The following action requires game is running
		if (Game.isOn()){
			GameFlow.execute(Game.getTick());
			
			Game.tickAdd();
		}
		tick ++;
	}
}
