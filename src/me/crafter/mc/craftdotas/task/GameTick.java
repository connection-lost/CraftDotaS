package me.crafter.mc.craftdotas.task;

import me.crafter.mc.craftdotas.action.GameFlow;
import me.crafter.mc.craftdotas.action.GameInfo;
import me.crafter.mc.craftdotas.action.HudAction;

public class GameTick implements Runnable{

	@Override
	public void run() {
		GameFlow.execute(GameInfo.getTick());
		HudAction.execute(GameInfo.getTick());
		
		
		
		
		
		
		GameInfo.tickAdd();
	}
	
}
