/*
*** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
*/

package com.salvestrom.w2theJungle.worldGen.structures.jungleTown;
import java.util.Random;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.worldGen.structures.blockPlacmentBridge;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.gen.feature.WorldGenerator;

public class jungleCityBeacon extends WorldGenerator
{
	public blockPlacmentBridge bridge = new blockPlacmentBridge();
	
	protected Block[] GetValidSpawnBlocks() {
		return new Block[] {
			Blocks.DIRT,
			Blocks.GRASS,
			Blocks.STONE,
			Blocks.WATER
		};
	}

	public boolean LocationIsValidSpawn(World world, int i, int j, int k){
		
		int distanceToAir = 0;
		BlockPos pos = new BlockPos(i, j, k);
		Block checkID = world.getBlockState(pos).getBlock();
		
		if(checkID != Blocks.AIR)
		{
			distanceToAir++;
			checkID = world.getBlockState(pos.add(0, distanceToAir, 0)).getBlock();
		}

		if (distanceToAir > 10)
		{
			return false;
		}
		j += distanceToAir-1; 

		Block blockID = world.getBlockState(pos).getBlock();
		Block blockIDAbove = world.getBlockState(pos.add(0, 1, 0)).getBlock();
		Block blockIDBelow = world.getBlockState(pos.down()).getBlock();
		for (Block x : GetValidSpawnBlocks()){
			if (blockIDAbove != Blocks.AIR)
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

	public jungleCityBeacon() { }

	public boolean generate(World world, Random rand, int i, int j, int k) {
		//check that each corner is one of the valid spawn blocks
		if(!LocationIsValidSpawn(world, i, j, k) || !LocationIsValidSpawn(world, i + 2, j, k) || !LocationIsValidSpawn(world, i + 2, j, k + 2) || !LocationIsValidSpawn(world, i, j, k + 2))
		{
			return false;
		}
		
		if(world.getBiome(new BlockPos(i, 0, k)) instanceof BiomeSwamp)
		{
			j -= 1;
		}
		
		if(world.provider.getDimension() == w2theJungle.dimensionIdLost)
		{
			j -= 2;
		}

		bridge.setBlock(world, i + 0, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 0, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 0, j + 1, k + 2, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 0, j + 2, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 2, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 2, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 3, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 0, j + 3, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 4, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 4, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 4, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 5, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 5, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 0, j + 5, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 6, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 6, k + 1, JungleBlocks.mossyStoneSteps);
		bridge.setBlock(world, i + 0, j + 6, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 7, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 7, k + 1, Blocks.STONE_STAIRS, 4, 2);
		bridge.setBlock(world, i + 0, j + 7, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 8, k + 0, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 0, j + 8, k + 1, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 0, j + 8, k + 2, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 0, j + 9, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 9, k + 1, Blocks.STONE_SLAB, 5, 2);
		bridge.setBlock(world, i + 0, j + 9, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 10, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 10, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 10, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 11, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 11, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 11, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 12, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 12, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 0, j + 12, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 2, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 2, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 2, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 3, k + 0, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 3, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 4, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 0, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 5, k + 2, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 1, j + 6, k + 0, JungleBlocks.mossyStoneSteps, 2, 2);
		bridge.setBlock(world, i + 1, j + 6, k + 1, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 6, k + 2, Blocks.STONE_BRICK_STAIRS, 3, 2);
		bridge.setBlock(world, i + 1, j + 7, k + 0, Blocks.STONE_STAIRS, 6, 2);
		bridge.setBlock(world, i + 1, j + 7, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 7, k + 2, Blocks.STONE_STAIRS, 7, 2);
		bridge.setBlock(world, i + 1, j + 8, k + 0, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 8, k + 1, Blocks.NETHERRACK);
		bridge.setBlock(world, i + 1, j + 8, k + 2, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 1, j + 9, k + 0, Blocks.STONE_SLAB, 5, 2);
		bridge.setBlock(world, i + 1, j + 9, k + 1, Blocks.FIRE, 15, 2);
		bridge.setBlock(world, i + 1, j + 9, k + 2, Blocks.STONE_SLAB, 5, 2);
		bridge.setBlock(world, i + 1, j + 10, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 10, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 10, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 11, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 11, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 11, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 12, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 12, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 1, j + 12, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 1, k + 0, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 2, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 2, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 2, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 3, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 3, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 4, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 4, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 4, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 5, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 5, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 2, j + 5, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 6, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 6, k + 1, JungleBlocks.mossyStoneSteps, 1, 2);
		bridge.setBlock(world, i + 2, j + 6, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 7, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 7, k + 1, Blocks.STONE_STAIRS, 5, 2);
		bridge.setBlock(world, i + 2, j + 7, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 8, k + 0, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 2, j + 8, k + 1, Blocks.STONEBRICK);
		bridge.setBlock(world, i + 2, j + 8, k + 2, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 2, j + 9, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 9, k + 1, Blocks.STONE_SLAB, 5, 2);
		bridge.setBlock(world, i + 2, j + 9, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 10, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 10, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 10, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 11, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 11, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 11, k + 2, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 12, k + 0, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 12, k + 1, Blocks.AIR);
		bridge.setBlock(world, i + 2, j + 12, k + 2, Blocks.AIR);

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