/*
*** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
*/

package com.salvestrom.w2theJungle.worldGen.structures;
import java.util.Random;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class jungleBeaconWG extends WorldGenerator
{
	public blockPlacmentBridge bridge = new blockPlacmentBridge();
	
	protected Block[] GetValidSpawnBlocks() {
		return new Block[] {
			Blocks.DIRT,
			Blocks.GRASS,
			Blocks.STONE
		};
	}

	public boolean LocationIsValidSpawn(World world, int i, int j, int k)
	{
		int distanceToAir = 0;
		BlockPos pos = new BlockPos(i, j, k);
		Block checkID = world.getBlockState(pos).getBlock();

		if(world.provider.getDimension() == w2theJungle.dimensionIdLost)
		{
			return true;
		}
		
		if(checkID != Blocks.AIR){ //must use if not while to carry over the distance to air. no idea why.
			distanceToAir++;
			checkID = world.getBlockState(pos.add(0, distanceToAir, 0)).getBlock();
		}

		if (distanceToAir > 30){
			return false;
		}
		j += distanceToAir-1; //this sets all subsequent j to be  1 below where ever it got to. 

		pos = new BlockPos(i, j, k);
		
		Block blockID = world.getBlockState(pos).getBlock();
		Block blockIDAbove = world.getBlockState(pos.add(0, 1, 0)).getBlock();
		Block blockIDBelow = world.getBlockState(pos.down()).getBlock();
		for (Block x : GetValidSpawnBlocks()){
			if (blockIDAbove != Blocks.AIR)
			{
				return false;
			}
			if (blockID == x){
				return true;
			}else if (blockID == Blocks.SNOW && blockIDBelow == x){
				return true;
			}
		}
		return false;
	}

	public jungleBeaconWG() { }

	public boolean generate(World world, Random rand, int i, int j, int k) {
		//check that each corner is one of the valid spawn blocks
		//if(!LocationIsValidSpawn(world, i, j, k) || !LocationIsValidSpawn(world, i + 2, j, k) || !LocationIsValidSpawn(world, i + 2, j, k + 2) || !LocationIsValidSpawn(world, i, j, k + 2))
		if(!LocationIsValidSpawn(world, i+2, j, k+2))
		{
			return false;
		}
		
		bridge.setBlock(world, i + 0, j + 25, k + 2, Blocks.STONE_STAIRS, 4, 2);
		bridge.setBlock(world, i + 0, j + 26, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 3, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 3, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 4, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 5, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 6, k + 1, Blocks.STONE_SLAB, 3, 2);
		bridge.setBlock(world, i + 1, j + 6, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 6, k + 3, Blocks.STONE_SLAB, 3, 2);
		bridge.setBlock(world, i + 1, j + 7, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 8, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 9, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 10, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 11, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 12, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 13, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 14, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 15, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 16, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 17, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 18, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 19, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 20, k + 2, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 1, j + 21, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 22, k + 2, Blocks.STONE_STAIRS);
		bridge.setBlock(world, i + 1, j + 23, k + 2, Blocks.STONE_STAIRS, 4, 2);
		bridge.setBlock(world, i + 1, j + 24, k + 1, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 1, j + 24, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 24, k + 3, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 1, j + 25, k + 1, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 1, j + 25, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 25, k + 3, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 1, j + 26, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 26, k + 2, Blocks.NETHERRACK);
		bridge.setBlock(world, i + 1, j + 26, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 27, k + 1, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 27, k + 2, Blocks.FIRE, 15, 2);
		bridge.setBlock(world, i + 1, j + 27, k + 3, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 28, k + 1, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 28, k + 3, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 29, k + 1, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 29, k + 3, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 30, k + 1, Blocks.STONE_BRICK_STAIRS, 2, 2);
		bridge.setBlock(world, i + 1, j + 30, k + 3, Blocks.STONE_BRICK_STAIRS);
		bridge.setBlock(world, i + 2, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 2, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 2, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 3, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 3, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 4, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 4, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 4, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 5, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 5, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 5, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 6, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 6, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 6, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 7, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 7, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 7, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 8, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 8, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 8, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 9, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 9, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 9, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 10, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 10, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 10, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 11, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 11, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 11, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 12, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 12, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 12, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 13, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 13, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 13, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 14, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 14, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 14, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 15, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 15, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 15, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 16, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 16, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 16, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 17, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 17, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 17, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 18, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 18, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 18, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 19, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 19, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 19, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 20, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 20, k + 2, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 20, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 21, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 21, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 21, k + 3, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 22, k + 1, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 2, j + 22, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 22, k + 3, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 2, j + 23, k + 1, Blocks.STONE_STAIRS, 6, 2);
		bridge.setBlock(world, i + 2, j + 23, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 23, k + 3, Blocks.STONE_STAIRS, 7, 2);
		bridge.setBlock(world, i + 2, j + 24, k + 1, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 24, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 24, k + 3, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 25, k + 0, Blocks.STONE_STAIRS, 6, 2);
		bridge.setBlock(world, i + 2, j + 25, k + 1, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 25, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 25, k + 3, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 25, k + 4, Blocks.STONE_STAIRS, 7, 2);
		bridge.setBlock(world, i + 2, j + 26, k + 0, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 26, k + 1, Blocks.NETHERRACK);
		bridge.setBlock(world, i + 2, j + 26, k + 2, Blocks.NETHERRACK);
		bridge.setBlock(world, i + 2, j + 26, k + 3, Blocks.NETHERRACK);
		bridge.setBlock(world, i + 2, j + 26, k + 4, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 27, k + 1, Blocks.FIRE, 15, 2);
		bridge.setBlock(world, i + 2, j + 27, k + 2, Blocks.FIRE, 15, 2);
		bridge.setBlock(world, i + 2, j + 27, k + 3, Blocks.FIRE, 15, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 3, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 3, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 4, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 4, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 4, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 5, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 5, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 5, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 6, k + 1, Blocks.STONE_SLAB, 3, 2);
		bridge.setBlock(world, i + 3, j + 6, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 6, k + 3, Blocks.STONE_SLAB, 3, 2);
		bridge.setBlock(world, i + 3, j + 7, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 8, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 9, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 10, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 11, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 12, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 13, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 14, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 15, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 16, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 17, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 18, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 19, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 20, k + 2, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 21, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 22, k + 2, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 3, j + 23, k + 2, Blocks.STONE_STAIRS, 5, 2);
		bridge.setBlock(world, i + 3, j + 24, k + 1, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 3, j + 24, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 3, j + 24, k + 3, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 3, j + 25, k + 1, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 3, j + 25, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 3, j + 25, k + 3, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 3, j + 26, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 26, k + 2, Blocks.NETHERRACK);
		bridge.setBlock(world, i + 3, j + 26, k + 3, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 27, k + 1, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 3, j + 27, k + 2, Blocks.FIRE, 15, 2);
		bridge.setBlock(world, i + 3, j + 27, k + 3, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 3, j + 28, k + 1, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 3, j + 28, k + 3, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 3, j + 29, k + 1, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 29, k + 3, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 30, k + 1, Blocks.STONE_BRICK_STAIRS, 1, 2);
		bridge.setBlock(world, i + 3, j + 30, k + 3, Blocks.STONE_BRICK_STAIRS, 3, 2);
		bridge.setBlock(world, i + 4, j + 25, k + 2, Blocks.STONE_STAIRS, 5, 2);
		bridge.setBlock(world, i + 4, j + 26, k + 2, Blocks.STONEBRICK);

		int lower = world.provider.getDimension() == w2theJungle.dimensionIdLost ? -12 : -4;
		
		for(int fi = 1; fi <= 3; fi++) {
			for(int fj = -1; fj > lower; fj--) {
				for(int fk = 1; fk <= 3; fk++) {
					Block check = world.getBlockState(new BlockPos(i + fi, j + fj, k + fk)).getBlock();
							if(check != Blocks.STONE) {
								bridge.setBlock(world, i + fi, j + fj, k + fk, Blocks.MOSSY_COBBLESTONE);
							}
				}
			}
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