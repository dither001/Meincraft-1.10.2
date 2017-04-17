package com.salvestrom.w2theJungle.blocks;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class mossyStairs extends BlockStairs
{
	public mossyStairs(int i, Block mossyCobblestone)
	{	super(mossyCobblestone.getDefaultState());
		this.setUnlocalizedName("stoneMossyStairs");
		//this.setBlockTextureName("thejungle:stoneMossy");
		this.setCreativeTab(w2theJungle.JungleModTab);
        this.setLightOpacity(0);

	}
}