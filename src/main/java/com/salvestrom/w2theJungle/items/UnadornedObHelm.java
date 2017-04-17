package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UnadornedObHelm extends Item {
	
	public UnadornedObHelm(int i)
	{
		this.setUnlocalizedName("unadornedObHelm");
		//this.setTextureName("thejungle:unadornedObHelm");
		this.setCreativeTab(w2theJungle.JungleModTab);
	}
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer playa, List<String> list, boolean par4)
	{list.add("Holding it up to your head");
	 list.add("your hear an angry hissing.");
	 list.add("You are SO not putting it on!");
	 
	}
}
