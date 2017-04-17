package com.salvestrom.w2theJungle.blocks;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class mossyCarved extends Block {

	public mossyCarved(Material p_i45394_1_) {
		super(p_i45394_1_);
		
		this.setUnlocalizedName("mossyCarved");
		//this.setBlockTextureName("thejungle:mossyStone_carved");
		this.setCreativeTab(w2theJungle.JungleModTab);
		this.setHardness(3.0F).setResistance(15.0f);
		this.setSoundType(SoundType.STONE);

	}

}
