package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UnadornedObLegs extends Item {
	
	public UnadornedObLegs(int i)
	{
		this.setUnlocalizedName("unadornedObLegs");
		//this.setTextureName("thejungle:unadornedObLegs");
		this.setCreativeTab(w2theJungle.JungleModTab);
	}
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer playa, List<String> list, boolean par4)
	{list.add("They look your size, but having");
	 list.add("them anywhere near you makes");
	 list.add("you feel weak at the knees.");
	 
	}

}
