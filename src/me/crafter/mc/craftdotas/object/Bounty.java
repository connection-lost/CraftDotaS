package me.crafter.mc.craftdotas.object;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Bounty {

	private int score = 0;

	private List<ItemStack> items = new ArrayList<ItemStack>();
	private boolean dropitemonground = true;

	private boolean scale = false;

	private Bounty(){}
	
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
	
	public Bounty dropItemOnGround(boolean dropitemonground_){
		dropitemonground = dropitemonground_;
		return this;
	}
	
	public Bounty scale(boolean scale_){
		scale = scale_;
		return this;
	}
	
	public static Bounty build(){
		return new Bounty();
	}
	
	public void award(Player receiver){
		Team team = Team.getPlayerTeam(receiver);
		// Score
		Game.addScore(team.getId(), team.getBounty().getScore());
		// Item
		List<ItemStack> itemstacks = team.getBounty().getItems();
		for (ItemStack itemstack : itemstacks){
			if (team.getBounty().isDropItemOnGround()){
				receiver.getWorld().dropItemNaturally(receiver.getLocation(), itemstack);
			} else {
				receiver.getInventory().addItem(itemstack);
			}
		}
	}
	
}
