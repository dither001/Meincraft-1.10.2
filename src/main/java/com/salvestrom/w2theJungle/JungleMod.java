package com.salvestrom.w2theJungle;

import com.salvestrom.w2theJungle.init.JungleItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class JungleMod extends CreativeTabs {

	public JungleMod(int par1, String par2Str) {
		super(par1, par2Str);}

	@Override
	public Item getTabIconItem()
	{return JungleItems.gemSapphire;}

}
