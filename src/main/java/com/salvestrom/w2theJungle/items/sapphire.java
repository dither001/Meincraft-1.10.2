package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;

public class sapphire extends Item {

	public sapphire(int i)
	{
		this.setUnlocalizedName("sapphire");
		//this.setTextureName("thejungle:sapphire");
		this.setCreativeTab(w2theJungle.JungleModTab);
	}
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer nttplaya, List<String> list, boolean par4)
	{list.add("The Saur-Ohn believe the souls");
	 list.add("of their ancestors dwell within");
	 list.add("these precious stones.");
	
	 IStak.setTagInfo("lore", new NBTTagString("testing"));
	
	}

}
