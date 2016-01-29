package me.crafter.mc.craftdotas.task;

import me.crafter.mc.craftdotas.data.GameFlow;
import me.crafter.mc.craftdotas.data.GameInfo;
import me.crafter.mc.craftdotas.data.HudAction;

public class GameTick implements Runnable{

	@Override
	public void run() {
		GameFlow.execute(GameInfo.getTick());
		HudAction.execute(GameInfo.getTick());
		
		
		
		
		
		
		GameInfo.tickAdd();
	}
	
}
