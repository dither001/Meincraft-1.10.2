package com.salvestrom.w2theJungle.blocks;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClimbingWeb extends BlockLadder {

	public ClimbingWeb() {
        this.setHardness(0.4F);
        this.setSoundType(SoundType.SNOW);
        this.setUnlocalizedName("climbingWeb");
        //this.setBlockTextureName("thejungle:climbingWeb");
        this.setCreativeTab(w2theJungle.JungleModTab);
        }
	
	public void onEntityCollidedWithBlock(World wrld, BlockPos pos, IBlockState state, Entity ntt) 
	{
			
		if(wrld.isRemote)
		{
			if(!(ntt instanceof EntitySpider))
			{
				ntt.motionX *= 0.435;
				ntt.motionY *= 0.435;
				ntt.motionZ *= 0.435;
			}
			//this affects fall spped while caught in the web, but not climb rate.
			//however, when this is set high, touching the web will launch you into the air.
			}
		}
	}
