package com.salvestrom.w2theJungle.blocks;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;

public class mossyStoneSteps extends BlockStairs {

	public mossyStoneSteps(int i, Block stonebrick, IBlockState state)
	{
		super(Block.getStateById(BlockStoneBrick.MOSSY_META));
		this.setUnlocalizedName("mossyStoneSteps");
		//this.setBlockTextureName("thejungle:mossySmoothStone");
		this.setCreativeTab(w2theJungle.JungleModTab);
		this.setLightOpacity(0);
	}

}

