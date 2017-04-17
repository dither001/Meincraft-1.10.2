package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class carvedBone extends Item {

	public carvedBone(int i)
	{
		this.setUnlocalizedName("carvedBone");
		this.setCreativeTab(w2theJungle.JungleModTab);
}
	
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer nttplaya, List<String> list, boolean par4)
	{list.add("Let's hope this");
	 list.add("was once a cow...");
	}

}
