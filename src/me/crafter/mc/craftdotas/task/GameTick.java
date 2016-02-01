package me.crafter.mc.craftdotas.task;

import me.crafter.mc.craftdotas.action.GameFlow;
import me.crafter.mc.craftdotas.action.HudAction;
import me.crafter.mc.craftdotas.object.Game;

/* This is the master tick for the game.
 * All other game involved ticks are initiated here.
 */

public class GameTick implements Runnable{

	@Override
	public void run() {
		// The following action does not require game to be running
		HudAction.execute(Game.getTick());
		// The following action requires game is running
		if (Game.isOn()){
			GameFlow.execute(Game.getTick());
			
			Game.tickAdd();
		}
	}
}
