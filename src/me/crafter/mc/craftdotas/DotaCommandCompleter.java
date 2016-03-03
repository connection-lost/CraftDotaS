package me.crafter.mc.craftdotas;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class DotaCommandCompleter implements TabCompleter {
	
	String[] arg1 = {"tick","start","load","stop","end","version"};

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		if (args.length == 1){
			return findFit(arg1, args[0]);
		}
		return null;
	}
	
	public List<String> findFit(String[] from, String input){
		List<String> result = new ArrayList<String>();
		for (String string : from){
			if (string.startsWith(input.toLowerCase())){
				result.add(string);
			}
		}
		return result;
	}

}
