package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UnadornedObBoots extends Item {
	
	public UnadornedObBoots(int i)
	{
		this.setUnlocalizedName("unadornedObBoots");
		//this.setTextureName("thejungle:unadornedObBoots");
		this.setCreativeTab(w2theJungle.JungleModTab);
	}
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer playa, List<String> list, boolean par4)
	{list.add("You've worn out worse footwear");
	 list.add("but looking at these just makes");
	 list.add("your toes curl. Eesh.");
	 
	}	

}
