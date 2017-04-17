package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class carvedBonehandle extends Item
{
	public carvedBonehandle(int i)
	{
		this.setUnlocalizedName("carvedBonehandle");
		this.setCreativeTab(w2theJungle.JungleModTab);
		

	}
	
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer nttplaya, List<String> list, boolean par4)
	{list.add("Just what you");
	 list.add("always wanted.");
	}

}
