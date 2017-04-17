package com.salvestrom.w2theJungle.items;

import com.salvestrom.w2theJungle.w2theJungle;
import net.minecraft.item.ItemSword;

public class swordBonehandle extends ItemSword
{

	public swordBonehandle(int i, ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName("swordBonehandle");
		//this.setTextureName("thejungle:swordBoneStone");
		this.setCreativeTab(w2theJungle.JungleModTab);
	}

}
