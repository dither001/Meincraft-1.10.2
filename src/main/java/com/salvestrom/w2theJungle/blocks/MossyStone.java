package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MossyStone extends Block {

	public MossyStone(Material p_i45394_1_) {
		super(p_i45394_1_);
		

		this.setUnlocalizedName("mossyStone");
		//this.setBlockTextureName("thejungle:mossyStone_Base");
		this.setCreativeTab(w2theJungle.JungleModTab);
		this.setHardness(1.5F).setResistance(12.0F);
		this.setSoundType(SoundType.STONE);
	}
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.MOSSY_COBBLESTONE);
    }

}
