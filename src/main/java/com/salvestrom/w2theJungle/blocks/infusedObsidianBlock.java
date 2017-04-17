package com.salvestrom.w2theJungle.blocks;

import com.salvestrom.w2theJungle.w2theJungle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class infusedObsidianBlock extends Block {

	public infusedObsidianBlock(int i, Material p_i45394_1_) {
		super(p_i45394_1_);
		
		this.setUnlocalizedName("infusedObsidianBlock");
		//this.setBlockTextureName("thejungle:infusedObsidianBlock");
		this.setCreativeTab(w2theJungle.JungleModTab);
		this.setHardness(35.0F);
		this.setResistance(2000.0F);
		this.setHarvestLevel("pickaxe", 3);
		this.setLightLevel(0.4F);
	}

}
