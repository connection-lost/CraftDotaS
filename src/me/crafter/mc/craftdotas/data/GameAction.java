package me.crafter.mc.craftdotas.data;

public enum GameAction { // NOT USED

	SYSTEM_ANNOUNCE, // Say message
	SYSTEM_COMMAND, // Run command
	BATTLEFIELD_PLAYER_READY, // Teleport players to battlefield
	BATTLEFIELD_GATE_OPEN, // Open player base gate
	BATTLEFIELD_GATE_CLOSE, // Close player base gate
	BATTLEFIELD_SPAWN_NEUTRAL, // Spawn neutral creeps
	BATTLEFIELD_SPAWN_CREEP, // Spawn teamed creeps
	SYSTEM_END, // Game force end after a while
	SYSTEM_STOP; // Game terminate
	
}
