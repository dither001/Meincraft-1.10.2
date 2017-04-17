package com.salvestrom.w2theJungle.worldGen.structures;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.world.World;

//TODO  this whole file needs to be redone since rotations are not logically set up. 1.8 updates should clear things up.

public class fillWithBlocks

{
	
	public blockPlacmentBridge bridge = new blockPlacmentBridge();
	
	public Random rnd = new Random();
	
	/* meta rotation has some issues - sometimes the m value is a block subtype, sometimes its an orientation
	 * 
	 * is there way to pass over the unchanging x y z just once rather than everytime??
	*/
	
	public void placeBlockWithRota(World world, int x, int y, int z, int a, int b, int c, Block block, String s) {

		if(s == "n") {bridge.setBlock(world, x + c, y + b, z - a, block);}
		//default
		if(s == "e") {bridge.setBlock(world, x + a, y + b, z + c, block);}
		if(s == "s") {bridge.setBlock(world, x + c, y + b, z + a, block);}
		if(s == "w") {bridge.setBlock(world, x - a, y + b, z + c, block);}
		}
	
	public void placeBlockWithMetaAndRota(World world, int x, int y, int z, int a, int b, int c, Block block, int m, int m2, String s) {

		if(s == "n") {bridge.setBlock(world, x + c, y + b, z - a, block, m, m2);}
		//default
		if(s == "e") {bridge.setBlock(world, x + a, y + b, z + c, block, m, m2);}
		if(s == "s") {bridge.setBlock(world, x + c, y + b, z + a, block, m, m2);}
		if(s == "w") {bridge.setBlock(world, x - a, y + b, z + c, block, m, m2);}
		}

	
	public void fillRow(World world, int x, int y, int z, int min, int max, Block block, String s) {
		
		if(s == "x") {
			for(int a = min; a <= max; a++)
				bridge.setBlock(world, x + a, y, z, block);
		}
		if(s == "y") {
			for(int a = min; a <= max; a++)
				bridge.setBlock(world, x, y + a, z, block);
		}
		if(s == "z") {
			for(int a = min; a <= max; a++)
				bridge.setBlock(world, x, y, z + a, block);
		}
	}
	/*/this can be converted to do both square and cube since the third unused min/max is still passed as 0.
	 * infact. this could function for all 3 with no need to pass the string, or rather saving it for the 4 rotations.
	*/
	
	//e is the default build orientation, extending positive east and south.
	public void fillWithRotation(World world, int x, int y, int z, int min1, int max1, int min2, int max2, int min3, int max3, Block block, String s) {

		//what this actually creates would depend on what variables are modified or not
		//stairs and the like would need additional variables. "withRotationAndMetadata"
		//this is a step below bounding boxes.
		
		//I think this will work...
		//it may be necessary to flip the building both ways in order to accommodate metadata
		
		if(s == "n") {
			for(int a = min1; a <= max1; a++)
				for(int b = min2; b <= max2; b++)
					for(int c = min3; c <= max3; c++) {
						bridge.setBlock(world, x + c, y + b, z - a, block);
						}
			}
		//default
		if(s == "e") {
			for(int a = min1; a <= max1; a++)
				for(int b = min2; b <= max2; b++)
					for(int c = min3; c <= max3; c++) {
						bridge.setBlock(world, x + a, y + b, z + c, block);
						}
			}
		if(s == "s") {
			for(int a = min1; a <= max1; a++)
				for(int b = min2; b <= max2; b++)
					for(int c = min3; c <= max3; c++) {
						bridge.setBlock(world, x + c, y + b, z + a, block);
						}
			}
		if(s == "w") {
			for(int a = min1; a <= max1; a++)
				for(int b = min2; b <= max2; b++)
					for(int c = min3; c <= max3; c++) {
						bridge.setBlock(world, x - a, y + b, z + c, block);
						}
			}
	}

	public void fillWithRotationAndMeta(World world, int x, int y, int z, int min1, int max1, int min2, int max2, int min3, int max3, Block block, int m, int m2, String s) {

		//need to expand each switch to add to m1. m1 is block subtype and needs no adjusting. i think... see above also
		//m2 is a block update flag. m1 is metadata]
		//this may have limited use since wood stair/slabs have dozens of meta numbers in use
		
		//NB: buildings should be constructed facing east
		
		if(s == "n") {
			for(int a = min1; a <= max1; a++)
				for(int b = min2; b <= max2; b++)
					for(int c = min3; c <= max3; c++) {
						bridge.setBlock(world, x + c, y + b, z - a, block, m, m2);
						}
			}
		//default
		if(s == "e") {
			for(int a = min1; a <= max1; a++)
				for(int b = min2; b <= max2; b++)
					for(int c = min3; c <= max3; c++) {
						bridge.setBlock(world, x + a, y + b, z + c, block, m, m2);
						}
			}
		if(s == "s") {
			
			if(block instanceof BlockStairs) {
				m = (m + 2)%4; //TODO wont work for upside down blocks
				} //w > s and e > n : 0 > 3, 1 > 2 //w e n s 0 1 2 3
			// s > e and n > w. 3 > 1, 2 > 0
			
			for(int a = min1; a <= max1; a++)
				for(int b = min2; b <= max2; b++)
					for(int c = min3; c <= max3; c++) {
						bridge.setBlock(world, x + c, y + b, z + a, block, m, m2);
						}
			}
		
		if(s == "w") {
			for(int a = min1; a <= max1; a++)
				for(int b = min2; b <= max2; b++)
					for(int c = min3; c <= max3; c++) {
						bridge.setBlock(world, x - a, y + b, z + c, block, m, m2);
						}
			}
	}
	

	
}
