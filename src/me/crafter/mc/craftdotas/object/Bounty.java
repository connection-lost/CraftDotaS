package me.crafter.mc.craftdotas.object;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Bounty {

	private int score = 0;
	private List<ItemStack> items = new ArrayList<ItemStack>();
	private boolean dropitemonground = true;
	private boolean scale = false;

	private Bounty(){};
	
	public Bounty(int score_, List<ItemStack> items_, boolean dropitemonground_, boolean scale_){
		score = score_;
		items = items_;
		dropitemonground = dropitemonground_;
		scale = scale_;
	}
	
	public int getScore() {return score;}
	public boolean isScale() {return scale;}
	public boolean isDropItemOnGround() {return dropitemonground;}
	public List<ItemStack> getItems() {return items;}
	
	public Bounty withScore(int score_){
		score = score_;
		return this;
	}
	
	public Bounty withItem(ItemStack item){
		items.add(item);
		return this;
	}
	
	public Bounty withItems(List<ItemStack> items){
		items.addAll(items);
		return this;
	}
	
	public Bounty dropItemOnGround(boolean dropitemonground_){
		dropitemonground = dropitemonground_;
		return this;
	}
	
	public Bounty scale(boolean scale_){
		scale = scale_;
		return this;
	}
	
	public static Bounty start(){
		return new Bounty();
	}
	
	public void award(Player receiver, Location awardlocation){
		Team team = Team.getPlayerTeam(receiver);
		// Score
		Game.addScore(team.getId(), getScore());
		// Item
		List<ItemStack> itemstacks = getItems();
		for (ItemStack itemstack : itemstacks){
			if (isDropItemOnGround()){
				receiver.getWorld().dropItemNaturally(awardlocation, itemstack);
			} else {
				receiver.getInventory().addItem(itemstack);
			}
		}
	}
	
	public void award(Team receiver, Location awardlocation){
		Game.addScore(receiver.getId(), getScore());
		// Item
		List<ItemStack> itemstacks = getItems();
		for (ItemStack itemstack : itemstacks){
			if (isDropItemOnGround()){
				Game.getWorld().dropItemNaturally(awardlocation, itemstack);
			} else {
				for (Player player : Team.getMembersByTeam(receiver)){
					player.getInventory().addItem(itemstack);
				}
			}
		}
	}
	
}
