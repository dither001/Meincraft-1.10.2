package com.salvestrom.w2theJungle.blocks;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class mossyAncientBlock extends Block {

	public mossyAncientBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
		
		this.setUnlocalizedName("ancientMossyBlock");
		//this.setRegistryName("thejungle:stoneMossy_upper"); //TODO this is not correct
		this.setHardness(75F);
		this.setResistance(1500.0F);
		this.setSoundType(SoundType.STONE);
		this.setCreativeTab(w2theJungle.JungleModTab);
		
		
	}

}
