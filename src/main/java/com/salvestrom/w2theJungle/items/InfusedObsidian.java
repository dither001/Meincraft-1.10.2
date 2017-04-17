package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusedObsidian extends Item {
	
	public InfusedObsidian(int i)
	{	
	this.setUnlocalizedName("infusedObsidian");
	this.setCreativeTab(w2theJungle.JungleModTab);
	}
	
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer nttplaya, List<String> list, boolean par4)
	{list.add("This material vibrates softly...");
	 list.add("You're not entirely sure it's a");
	 list.add("good idea to be making anything");
	 list.add("out of it...");
	}

}
