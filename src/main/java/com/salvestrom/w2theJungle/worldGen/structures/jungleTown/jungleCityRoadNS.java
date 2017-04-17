/*
*** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
*/

package com.salvestrom.w2theJungle.worldGen.structures.jungleTown;
import java.util.Random;

import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.worldGen.structures.blockPlacmentBridge;
import com.salvestrom.w2theJungle.worldGen.structures.fillWithBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class jungleCityRoadNS extends WorldGenerator
{
	public blockPlacmentBridge bridge = new blockPlacmentBridge();

	protected Block[] GetValidSpawnBlocks() {
		return new Block[] {
			Blocks.STONE,
			Blocks.GRASS,
			Blocks.DIRT
		};
	}

	public boolean LocationIsValidSpawn(World world, int i, int j, int k)
	{
		int distanceToAir = 0;
	
		BlockPos pos = new BlockPos(i, j, k);
		Block checkID = world.getBlockState(pos).getBlock();

		if(checkID != Blocks.AIR)
		{
			distanceToAir++;
			checkID = world.getBlockState(pos.add(0, distanceToAir, 0)).getBlock();
		}

		if (distanceToAir > 3){
			return false;
		}
		j += distanceToAir-1; 

		Block blockID = world.getBlockState(pos).getBlock();
		Block blockIDAbove = world.getBlockState(pos.up()).getBlock();
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

	public jungleCityRoadNS() { }
	
	public fillWithBlocks fill = new fillWithBlocks();
	public String rota = "e";

	public boolean generate(World world, Random rand, int i, int j, int k) {
		//check that each corner is one of the valid spawn blocks
		//TODO why run this check? the plateau is flat and the height is fed from the main town code...??
		//oh, im not.... the return is blanked.
		if(!LocationIsValidSpawn(world, i+1, j, k+1) || !LocationIsValidSpawn(world, i+3, j, k+1)
				|| !LocationIsValidSpawn(world, i+3, j, k+5) || !LocationIsValidSpawn(world, i+1, j, k+6))
		{
//			return false;
		}
		
		fill.fillWithRotation(world, i, j, k, -1, -1, 0, 0, 0, 6, JungleBlocks.mossyslabSingle, rota);
		fill.fillWithRotation(world, i, j, k, 5, 5, 0, 0, 0, 6, JungleBlocks.mossyslabSingle, rota);

		bridge.setBlock(world, i + 0, j + 0, k + 0, JungleBlocks.mossyslabSingle);
		bridge.setBlock(world, i + 0, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 6, JungleBlocks.mossyslabSingle);
		bridge.setBlock(world, i + 1, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 0, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 0, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 0, k + 4, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 0, k + 5, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 3, Blocks.GLOWSTONE);
		bridge.setBlock(world, i + 2, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 5, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 0, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 0, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 0, k + 4, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 0, k + 5, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 0, JungleBlocks.mossyslabSingle);
		bridge.setBlock(world, i + 4, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 6, JungleBlocks.mossyslabSingle);

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