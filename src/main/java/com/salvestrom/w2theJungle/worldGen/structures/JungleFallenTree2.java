package com.salvestrom.w2theJungle.worldGen.structures;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class JungleFallenTree2 extends WorldGenerator {

	//oak, spruce, birch, jungle? 0 1 2 3
	//3 is upright.
	//7 is n/s jungle log
	//11 is w/e jungle log.
	
	
	public blockPlacmentBridge bridge = new blockPlacmentBridge();

	protected Block[] GetValidSpawnBlocks() {
		return new Block[]
				{
				Blocks.DIRT,
				Blocks.GRASS
				};
	}

	public boolean LocationIsValidSpawn(World world, int i, int j, int k)
	{
		BlockPos pos = new BlockPos(i, j, k);
		int distanceToAir = 0;
		Block checkID = world.getBlockState(pos).getBlock();

		if(checkID != Blocks.AIR)
		{
			distanceToAir++;
			checkID = world.getBlockState(pos.up(distanceToAir)).getBlock();
		}

		if (distanceToAir > 5)
		{
			return false;
		}
		
		j += distanceToAir; //this sets all subsequent j to be  1 below where ever it got to. 
		pos = new BlockPos(i, j, k);

		Block blockID = world.getBlockState(pos).getBlock();
		Block blockIDAbove = world.getBlockState(pos.up(2)).getBlock();
		Block blockIDFartherAbove = world.getBlockState(pos.up(+3)).getBlock();
		Block blockIDBelow = world.getBlockState(pos.down(-1)).getBlock();
		for (Block x : GetValidSpawnBlocks())
		{
			if (blockIDAbove != Blocks.AIR && blockIDFartherAbove != Blocks.AIR)
			{
				return false;
			}
			if (blockID == x)
			{
				return true;
			}
			else
				if (blockID == Blocks.SNOW && blockIDBelow == x)
				{
					return true;
				}
			}
		return false;
		}


	public JungleFallenTree2() {}

	public boolean generate(World world, Random rng, int x, int y, int z) {

		int check1;
		int check2;
		int check3;
		int total;
		
		if(LocationIsValidSpawn(world, x, y, z+1)) {check1 = 1;} else {check1 = 0;}
		if(LocationIsValidSpawn(world, x, y, z + 8)) {check2 = 1;} else {check2 = 0;}
		if(LocationIsValidSpawn(world, x, y, z + 15)) {check3 = 1;} else {check3 = 0;}
		total = check1 + check2 + check3;
		
		if(total != 2) 
		{
			return false;
		}

		y += 2;
		
		for(int fillx = 0; fillx <= 1; fillx++) {
			for(int filly = 0; filly <= 1; filly++) {
				for(int fillz = 0; fillz <= 16; fillz++) {
					bridge.setBlock(world, x + fillx, y + filly, z + fillz, Blocks.LOG, 11, 2);

				}
			}
		}
		
		int addmore = rng.nextInt(2);
		if(addmore == 1 && check3  == 0) {
			bridge.setBlock(world, x, y, z+17, Blocks.LOG, 11, 2);
			bridge.setBlock(world, x+1, y+1, z+16, Blocks.AIR);
			bridge.setBlock(world, x, y+1, z+0, Blocks.AIR);
			bridge.setBlock(world, x+1, y+1, z+0, Blocks.AIR);
			bridge.setBlock(world, x+1, y+1, z+1, Blocks.AIR);
			} else if(addmore == 0 && check3 == 0) {
			bridge.setBlock(world, x, y+1, z+17, Blocks.LOG, 11, 2);
			bridge.setBlock(world, x+1, y, z+16, Blocks.AIR);
			} else if(addmore == 1 && check1 == 0) {
			bridge.setBlock(world, x, y, z-1, Blocks.LOG, 11, 2);
			bridge.setBlock(world, x+1, y+1, z, Blocks.AIR);
			} else if(addmore == 0 && check1 == 0) {
			bridge.setBlock(world, x, y+1, z-1, Blocks.LOG, 11, 2);
			bridge.setBlock(world, x+1, y, z, Blocks.AIR);
			}
		int addbranch = rng.nextInt(2);
		if(addbranch == 0) {
			bridge.setBlock(world, x, y+2, z+11, Blocks.LOG, 3, 2);
			bridge.setBlock(world, x, y+3, z+11, Blocks.LOG, 3, 2);
		} else if(addbranch == 1) {
			bridge.setBlock(world, x+1, y+2, z+3, Blocks.LOG, 3, 2);
			bridge.setBlock(world, x+1, y+3, z+3, Blocks.LOG, 3, 2); //metadata 12 is an oak log with bark on all 6 sides!
		}
	
		return true;
	}	
	
	@Override
	public boolean generate(World wrld, Random rnd, BlockPos pos) {

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		return this.generate(wrld, rnd, x, y, z);
	}
	

}
