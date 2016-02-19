package me.crafter.mc.craftdotas.object;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Location;

import de.inventivegames.hologram.Hologram;
import de.inventivegames.hologram.HologramAPI;
import me.crafter.mc.craftdotas.object.building.Building;
import net.md_5.bungee.api.ChatColor;

public class SingleHologram {

	private int id;
	private Location location;
	private List<String> lines;
	private Object host;
	private List<Hologram> holograms = new ArrayList<Hologram>();
	
	public SingleHologram(int id_, Location location_, List<String> lines_, Object host_){
		id = id_;
		location = location_;
		lines = lines_;
		host = host_;
		create();
	}
	
	public int getId(){return id;}
	public Location getLocation(){return location;}
	public List<String> getLines(){return lines;}
	
	public void setHost(Object object){host = object;}
	
	public void create(){
		holograms = new ArrayList<Hologram>();
		if (host instanceof Building){
			Hologram hologrambase = HologramAPI.createHologram(location, placeholderBuilding(lines.get(0)));
			hologrambase.spawn();
			holograms.add(hologrambase);
			for (int i = 1; i < lines.size(); i++){
				Hologram hologramnew = hologrambase.addLineBelow(placeholderBuilding(lines.get(i)));
				//hologramnew.spawn(); does not need to spawn why = =
				holograms.add(hologramnew);
				hologrambase = hologramnew;
			}
		}
	}
	
	public void update(){
		if (host instanceof Building){
			int i = 0;
			for (Hologram hologram : holograms){
				hologram.setText(placeholderBuilding(lines.get(i)));
				i ++;
			}
		}
	}
	
	public void destroy(){
		holograms = new ArrayList<Hologram>();
		for (Hologram hologram : holograms){
			hologram.despawn();
		}
	}
	
	public String placeholderBuilding(String line){
		Building building = (Building) host;
		if (line.contains("%name%")){
			line = line.replace("%name%", building.getDisplayName());
		}
		if (line.contains("%hp%")){
			line = line.replace("%hp%", (int)building.getHealth() + "");
		}
		if (line.contains("%hpmax%")){
			line = line.replace("%hpmax%", (int)building.getMaxHealth() + "");
		}
		if (line.contains("%bar20%")){
			line = line.replace("%bar20%", getBarHealth(building, 20));
		}
		
		return line;
	}
	
	public String getBarHealth(Building building, int length){
		double hp = building.getHealth();
		double hpmax = building.getMaxHealth();
		String ret = "";
		if (building.isInvulnerable()){
			ret += ChatColor.WHITE;
			ret += StringUtils.repeat("¨~", length);
		} else if (building.isDestroyed()){
			ret += ChatColor.BLACK;
			ret += StringUtils.repeat("¨~", length);
		} else {
			double percent = hp / hpmax;
			if (percent > 0.6){
				ret += ChatColor.GREEN;
			} else if (percent > 0.3){
				ret += ChatColor.YELLOW;
			} else {
				ret += ChatColor.RED;
			}
			ret += StringUtils.repeat("¨~", (int)(percent*length));
			ret += ChatColor.GRAY + StringUtils.repeat("¨~", length-((int)(percent*length)));
		}
		
		return ret;
	}
	
}
