package com.salvestrom.w2theJungle.blocks;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;

public class JungleLadder extends BlockLadder {

	public JungleLadder() {
		
		super();

        this.setHardness(0.4F);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName("jungleLadder");
        this.setCreativeTab(w2theJungle.JungleModTab);
        }
}
