package me.crafter.mc.craftdotas.object;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Location;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.crafter.mc.craftdotas.CraftDotaS;
import me.crafter.mc.craftdotas.object.building.Building;
import net.md_5.bungee.api.ChatColor;

public class SingleHologram {

	private int id;
	private Location location;
	private List<String> lines;
	private Object host;
	private Hologram hologram;
	
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
		if (host instanceof Building){
			hologram = HologramsAPI.createHologram(CraftDotaS.plugin, location);
			for (String line : lines){
				hologram.appendTextLine(placeholderBuilding(line));
			}
		}
	}
	
	public void update(){
		if (host instanceof Building){
			hologram.clearLines();
			for (String line : lines){
				hologram.appendTextLine(placeholderBuilding(line));
			}
		}
	}
	
	public void refresh(){
		if (host instanceof Building){
			hologram.clearLines();
			for (String line : lines){
				hologram.appendTextLine(placeholderBuilding(line));
			}
		}
	}
	
	public void destroy(){
		hologram.delete();
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
