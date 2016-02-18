package me.crafter.mc.craftdotas.object;

import java.util.List;

import org.bukkit.Location;

import me.crafter.mc.craftdotas.object.building.Building;

public class SingleHologram {

	private int id;
	private Location location;
	private List<String> lines;
	private Object host;
	
	public SingleHologram(int id_, Location location_, List<String> lines_){
		id = id_;
		location = location_;
		lines = lines_;
	}
	
	public int getId(){return id;}
	public Location getLocation(){return location;}
	public List<String> getLines(){return lines;}
	
	public void setHost(Object object){host = object;}
	
	public void update(){
		if (host instanceof Building){
			// Handle building hologram
		}
	}
	
	public void destroy(){
		// remove hologram
	}
	
}
