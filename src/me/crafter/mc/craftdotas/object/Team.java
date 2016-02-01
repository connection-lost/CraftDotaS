package me.crafter.mc.craftdotas.object;

public class Team {

	private int id;
	private String displayname;
	private String prefix;
	private String suffix;
	
	public Team(int id_, String displayname_, String prefix_, String suffix_){
		id = id_;
		displayname = displayname_;
		prefix = prefix_;
		suffix = suffix_;
	}
	
	public int getId() {return id;}
	public String getDisplayName() {return displayname;}
	public String getPrefix() {return prefix;}
	public String getSuffix() {return suffix;}
}
