package com.salvestrom.w2theJungle.items;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemDoor;

public class stoneDoorItem extends ItemDoor {

	public stoneDoorItem(Material mat, Block block) {
		super(block);
		
		this.setUnlocalizedName("stoneDoorItem");
        this.maxStackSize=3;

		this.setCreativeTab(w2theJungle.JungleModTab);
	}
}
