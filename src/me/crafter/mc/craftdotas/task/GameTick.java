package me.crafter.mc.craftdotas.task;

import me.crafter.mc.craftdotas.action.GameFlow;
import me.crafter.mc.craftdotas.action.HudAction;
import me.crafter.mc.craftdotas.object.Game;

public class GameTick implements Runnable{

	@Override
	public void run() {
		GameFlow.execute(Game.getTick());
		HudAction.execute(Game.getTick());
		
		
		
		
		
		
		Game.tickAdd();
	}
	
}
