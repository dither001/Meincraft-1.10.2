package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UnadornedObChest extends Item {
	
	public UnadornedObChest(int i)
	{
		this.setUnlocalizedName("unadornedObChest");
		//this.setTextureName("thejungle:unadornedObChest");
		this.setCreativeTab(w2theJungle.JungleModTab);
	}
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer playa, List<String> list, boolean par4)
	{list.add("Holding this piece to you");
	 list.add("makes your chest feel tight;");
	 list.add("breathing is a struggle.");
	 
	}

}
