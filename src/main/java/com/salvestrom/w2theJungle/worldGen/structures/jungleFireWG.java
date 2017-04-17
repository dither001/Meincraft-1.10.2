
	/*
	*** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
	*/

	//with some necessary tweaking by meh!


package com.salvestrom.w2theJungle.worldGen.structures;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.worldGen.loot_tables.JungleLootTableRegistry;
import com.salvestrom.w2theJungle.worldGen.structures.jungleTown.jungleHutLargeNorth;
import com.salvestrom.w2theJungle.worldGen.structures.jungleTown.jungleHutMediumNorth;


public class jungleFireWG extends WorldGenerator
{
	public blockPlacmentBridge bridge = new blockPlacmentBridge();
	
    public static final ResourceLocation fillme = JungleLootTableRegistry.JUNGLE_CHESTS_HUT;
    public static final ResourceLocation elsefillme = JungleLootTableRegistry.JUNGLE_CHESTS_TEMPLE;
    public int dim;
    
    //determining valid loc - try using leaves, logs here.
	public Block[] GetValidSpawnBlocks()
	{
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
			
			if (checkID != Blocks.STONE || checkID != Blocks.GRASS || checkID != Blocks.DIRT)
			{ //a test!. using block.air didnt work well and reveals why buildings can be one block to high - something to do with grass, perhaps?
				distanceToAir++;
				checkID = world.getBlockState(pos.up(distanceToAir)).getBlock();
			}

			if (distanceToAir > 15){
				return false;
			}
			j += distanceToAir-1;

			pos = new BlockPos(i, j, k);
			
			Block block = world.getBlockState(pos.down()).getBlock();
			Block blockAbove = world.getBlockState(pos).getBlock();
			Block blockBelow = world.getBlockState(pos.down(-2)).getBlock();
			Block blockAboveTop = world.getBlockState(pos.add(+2, +6, +2)).getBlock();
			Block blockMiddle = world.getBlockState(pos.add(2, 1, 2)).getBlock();
			
			for (Block x : GetValidSpawnBlocks()){
				if (blockAbove != Blocks.AIR)
					{return false;}
				else if (blockMiddle == Blocks.LOG && blockAboveTop == Blocks.LOG)
					{return false;}
				if (block == x)
					{return true;}
				else if (block == Blocks.SNOW && blockBelow == x)
					{return true;}
				else if (block == x && blockAbove == Blocks.LEAVES)
					{return true;}
				else if (block == x && blockMiddle == Blocks.LOG && blockAboveTop != Blocks.LOG)
					{return true;}
				
				
						
			}
			return false;
		}

		//specifics of the structure
		public jungleFireWG() {}

		public boolean generate(World world, Random rand, int i, int j, int k)
		{			
			if(!LocationIsValidSpawn(world, i+2, j, k) || !LocationIsValidSpawn(world, i+2, j, k+4))			
			{
				return false;
			}

			//adding a base to correct some of the floating spawns
			for (int v = 0; v < 5; ++v)
				for (int v2 = 0; v2 < 5; ++ v2)
				{
					//Block fill = world.getBlock(i+v, j-1, k+v2);
					//if (fill == Blocks.AIR || fill == Blocks.tallgrass || fill == Blocks.leaves)
						{
						bridge.setBlock(world, i + v, j - 1, k + v2, Blocks.MOSSY_COBBLESTONE);
						}
				}					
			
			dim = world.provider.getDimension();
			
			Block air = Blocks.AIR;
			
			bridge.setBlock(world, i + 0, j + 0, k + 0, Blocks.STONE_SLAB, 3, 2);
			bridge.setBlock(world, i + 0, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 0, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 0, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 0, j + 0, k + 4, Blocks.STONE_SLAB, 3, 2);
		
			for(int fill=0; fill <= 4; fill++)
			{
				bridge.setBlock(world, i + 0, j + 1, k + fill, air);
				bridge.setBlock(world, i + 0, j + 2, k + fill, air);
				bridge.setBlock(world, i + 0, j + 3, k + fill, air);
			}
			
			bridge.setBlock(world, i + 1, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 1, j + 0, k + 1, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 1, j + 0, k + 2, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 1, j + 0, k + 3, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 1, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 1, j + 1, k + 0, air);
			bridge.setBlock(world, i + 1, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 1, j + 1, k + 2, JungleBlocks.mossystairs);
			bridge.setBlock(world, i + 1, j + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 1, j + 1, k + 4, air);
			bridge.setBlock(world, i + 1, j + 2, k + 0, air);
			bridge.setBlock(world, i + 1, j + 2, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
			bridge.setBlock(world, i + 1, j + 2, k + 2, air);
			bridge.setBlock(world, i + 1, j + 2, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
			bridge.setBlock(world, i + 1, j + 2, k + 4, air);
			bridge.setBlock(world, i + 1, j + 3, k + 0, air);
			bridge.setBlock(world, i + 1, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 1, j + 3, k + 2, JungleBlocks.mossystairs, 4, 2);
			bridge.setBlock(world, i + 1, j + 3, k + 3, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 1, j + 3, k + 4, air);
			bridge.setBlock(world, i + 2, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 2, j + 0, k + 1, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 2, j + 0, k + 2, Blocks.NETHERRACK);
			bridge.setBlock(world, i + 2, j + 0, k + 3, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 2, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 2, j + 1, k + 0, air);
			bridge.setBlock(world, i + 2, j + 1, k + 1, JungleBlocks.mossystairs, 2, 2);
			bridge.setBlock(world, i + 2, j + 1, k + 2, Blocks.FIRE, 15, 2);
			bridge.setBlock(world, i + 2, j + 1, k + 3, JungleBlocks.mossystairs, 3, 2);
			bridge.setBlock(world, i + 2, j + 1, k + 4, air);
			bridge.setBlock(world, i + 2, j + 2, k + 0, air);
			bridge.setBlock(world, i + 2, j + 2, k + 1, air);
			bridge.setBlock(world, i + 2, j + 2, k + 2, air);
			bridge.setBlock(world, i + 2, j + 2, k + 3, air);
			bridge.setBlock(world, i + 2, j + 2, k + 4, air);
			bridge.setBlock(world, i + 2, j + 3, k + 0, air);
			bridge.setBlock(world, i + 2, j + 3, k + 1, JungleBlocks.mossystairs, 6, 2);
			bridge.setBlock(world, i + 2, j + 3, k + 2, air);
			bridge.setBlock(world, i + 2, j + 3, k + 3, JungleBlocks.mossystairs, 7, 2);
			bridge.setBlock(world, i + 2, j + 3, k + 4, air);
			bridge.setBlock(world, i + 3, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 3, j + 0, k + 1, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 3, j + 0, k + 2, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 3, j + 0, k + 3, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 3, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 3, j + 1, k + 0, air);
			bridge.setBlock(world, i + 3, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 3, j + 1, k + 2, JungleBlocks.mossystairs, 1, 2);
			bridge.setBlock(world, i + 3, j + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 3, j + 1, k + 4, air);
			bridge.setBlock(world, i + 3, j + 2, k + 0, air);
			bridge.setBlock(world, i + 3, j + 2, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
			bridge.setBlock(world, i + 3, j + 2, k + 2, air);
			bridge.setBlock(world, i + 3, j + 2, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
			bridge.setBlock(world, i + 3, j + 2, k + 4, air);
			bridge.setBlock(world, i + 3, j + 3, k + 0, air);
			bridge.setBlock(world, i + 3, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 3, j + 3, k + 2, JungleBlocks.mossystairs, 5, 2);
			bridge.setBlock(world, i + 3, j + 3, k + 3, Blocks.MOSSY_COBBLESTONE);
			bridge.setBlock(world, i + 3, j + 3, k + 4, air);
			bridge.setBlock(world, i + 4, j + 0, k + 0, JungleBlocks.mossyslabSingle, 3, 2);
			bridge.setBlock(world, i + 4, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 4, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 4, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
			bridge.setBlock(world, i + 4, j + 0, k + 4, JungleBlocks.mossyslabSingle, 3, 2); //pick-block on this returns a different item to the creative tab...
			
			for(int fill=0; fill <= 4; fill++) {
				bridge.setBlock(world, i + 4, j + 1, k + fill, air);
				bridge.setBlock(world, i + 4, j + 2, k + fill, air);
				bridge.setBlock(world, i + 4, j + 3, k + fill, air);
				}

			int small = rand.nextInt(5);
			if(small == 0) {
				} else if(small >= 3) {
					jungleHutSmallWG(world, rand, i, j, k);
				} else {
					if(jungleHutSmallWG(world, rand, i, j, k)) {
						this.ruin(world, rand, i-1, j-1, k-10, 4);
						}
					}
			
			int large = rand.nextInt(5);
			
			if(large == 0) {
				} else if(large >= 3) {
					jungleHutLargeEWG(world, rand, i, j, k);
					} else {
						if(jungleHutLargeEWG(world, rand, i, j, k)) {
							this.ruin(world, rand, i-9, j-1, k-2, 4);
							}
						}
			//this never seems to work...
			int larger = rand.nextInt(7);
			if(larger == 0) {
				new jungleHutLargeNorth().generate(world, rand, i-2, j-1, k+8);
			} else if(larger == 2 || larger == 3) {
				if(new jungleHutLargeNorth().generate(world, rand, i-2, j-1, k+8)) {
					this.ruin(world, rand, i, j+3, k+10, 5);
					this.ruin(world, rand, i+3, j+3, k+12, 5);
					}
			} else if(larger > 7) {
				if(new jungleHutMediumNorth().generate(world, rand, i-2, j-1, k+8)) {
					this.ruin(world, rand, i-1, j, k+10, 4);
					this.ruin(world, rand, i+3, j, k+10, 4);
					}
				} else {
					new jungleHutMediumNorth().generate(world, rand, i-2, j-1, k+8);
			}
			
			return true;
		}

		private void ruin(World world, Random rand, int i, int j, int k, int radius) {
				
			int x = i + 5 - rand.nextInt(5);
			int y = j + 6;
			int z = k + 5 - rand.nextInt(5);
			
			int r = radius;
			
			for(int x2 = -r; x2 < r; x2++)
				for(int y2 = -r; y2 < r; y2++)
					for(int z2 = -r; z2 < r; z2++) {
						if((x2*x2) + (y2*y2) + (z2*z2) < r*r) {
						bridge.setBlock(world, x + x2, y + y2, z + z2, Blocks.AIR);
						}
					}			
		}

			public boolean jungleHutSmallWG(World world, Random rand, int i, int j, int k)		{
				
				
				//without a spawn check the north hut blocks the road in a village
				// also. reinstate a check for air 2 blocks below the mid point
				
				int x = i - 1;
				int y = j - 1;
//				int z = k - 10;
				k = k-10;
				//check that each corner is one of the valid spawn blocks
				//if(!LocationIsValidSpawn(world, x, y+2, k) || !LocationIsValidSpawn(world, x + 6, y+2, k) || !LocationIsValidSpawn(world, x + 6, y+2, k + 6) || !LocationIsValidSpawn(world, x, y+2, k + 6))
				//if(!LocationIsValidSpawn(world, x+3, y+2, k+3))	{//	return false;}
				//stops one particular iteration in swamp village
				if(world.getBlockState(new BlockPos(x+2, y+1, k+2)).getBlock() == Blocks.MOSSY_COBBLESTONE
						|| world.getBlockState(new BlockPos(x+3, y-4, k+3)).getBlock() == Blocks.AIR)
				{
					return false;					
				}
				
				for(int fill = 2; fill <= 4; fill++)
					for(int fill2 = 1; fill2 <= 4; fill2++)
						for(int fill3 = 2; fill3 <= 4; fill3++)
						{
							bridge.setBlock(world, x+fill, y+fill2, k+fill3, Blocks.AIR);
						}
				
				new fillWithBlocks().fillWithRotation(world, x, y, k, 0, 6, -4, 0, 0, 6, Blocks.DIRT, "e");
	
				bridge.setBlock(world, x + 0, y + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 0, y + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 0, y + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 0, y + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 0, y + 1, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 0, y + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 0, y + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 0, y + 4, k + 3, Blocks.LOG, 7, 2);
				bridge.setBlock(world, x + 1, y + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 1, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 2, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 2, k + 3, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 2, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 2, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 3, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
				bridge.setBlock(world, x + 1, y + 3, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 3, k + 3, JungleBlocks.mossystairs);
				bridge.setBlock(world, x + 1, y + 3, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 3, k + 5, Blocks.COBBLESTONE_WALL, 1, 2);
				bridge.setBlock(world, x + 1, y + 4, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
				bridge.setBlock(world, x + 1, y + 4, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 4, k + 3, Blocks.LOG, 7, 2);
				bridge.setBlock(world, x + 1, y + 4, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 4, k + 5, Blocks.COBBLESTONE_WALL, 1, 2);
				bridge.setBlock(world, x + 1, y + 5, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 1, y + 5, k + 2, JungleBlocks.mossystairs, 4, 2);
				bridge.setBlock(world, x + 1, y + 5, k + 3, JungleBlocks.mossystairs, 4, 2);
				bridge.setBlock(world, x + 1, y + 5, k + 4, JungleBlocks.mossystairs, 4, 2);
				bridge.setBlock(world, x + 1, y + 5, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 2, y + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 2, y + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
			
			if(rand.nextInt(2) == 0) {
				
				bridge.setBlock(world, x + 3, y + 1, k + 2, Blocks.CHEST, 3, 2); //3 is now south facing

				TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(new BlockPos(x+3, y+1, k+2));
					
				if (tileentitychest != null)
				{
					if(dim == 0)
					{
                        ((TileEntityChest)tileentitychest).setLootTable(fillme, rand.nextLong());

//						WeightedRandomChestContent.generateChestContents(rand, fillme, tileentitychest, 3);
					}
					else if(dim == w2theJungle.dimensionIdLost)
					{
                        ((TileEntityChest)tileentitychest).setLootTable(elsefillme, rand.nextLong());
					}
				}
			}
			
				bridge.setBlock(world, x + 2, y + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 2, y + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 2, y + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 2, y + 2, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 2, y + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 2, y + 3, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 2, y + 4, k + 0, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 2, y + 4, k + 1, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 2, y + 4, k + 2, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 2, y + 4, k + 3, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 2, y + 4, k + 4, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 2, y + 4, k + 5, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 2, y + 4, k + 6, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 2, y + 5, k + 1, JungleBlocks.mossystairs, 6, 2);
				bridge.setBlock(world, x + 2, y + 5, k + 2, Blocks.GRASS);
				bridge.setBlock(world, x + 2, y + 5, k + 3, Blocks.GRASS);
				bridge.setBlock(world, x + 2, y + 5, k + 4, Blocks.GRASS);
				bridge.setBlock(world, x + 2, y + 5, k + 5, JungleBlocks.mossystairs, 7, 2);
				bridge.setBlock(world, x + 3, y + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 3, y + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 3, y + 1, k + 4, Blocks.STONE_SLAB, 3, 2);
				bridge.setBlock(world, x + 3, y + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 3, y + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 3, y + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 3, y + 3, k + 1, JungleBlocks.mossystairs, 2, 2);
				bridge.setBlock(world, x + 3, y + 4, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 3, y + 4, k + 3, Blocks.LOG, 7, 2);
				bridge.setBlock(world, x + 3, y + 4, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 3, y + 5, k + 1, JungleBlocks.mossystairs, 6, 2);
				bridge.setBlock(world, x + 3, y + 5, k + 2, Blocks.GRASS);
				bridge.setBlock(world, x + 3, y + 5, k + 3, Blocks.GRASS);
				bridge.setBlock(world, x + 3, y + 5, k + 4, Blocks.GRASS);
				bridge.setBlock(world, x + 3, y + 5, k + 5, JungleBlocks.mossystairs, 7, 2);
				bridge.setBlock(world, x + 4, y + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 4, y + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 4, y + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 4, y + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 4, y + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 4, y + 2, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 4, y + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 4, y + 3, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 4, y + 4, k + 0, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 4, y + 4, k + 1, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 4, y + 4, k + 2, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 4, y + 4, k + 3, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 4, y + 4, k + 4, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 4, y + 4, k + 5, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 4, y + 4, k + 6, Blocks.LOG, 11, 2);
				bridge.setBlock(world, x + 4, y + 5, k + 1, JungleBlocks.mossystairs, 6, 2);
				bridge.setBlock(world, x + 4, y + 5, k + 2, Blocks.GRASS);
				bridge.setBlock(world, x + 4, y + 5, k + 3, Blocks.GRASS);
				bridge.setBlock(world, x + 4, y + 5, k + 4, Blocks.GRASS);
				bridge.setBlock(world, x + 4, y + 5, k + 5, JungleBlocks.mossystairs, 7, 2);
				bridge.setBlock(world, x + 5, y + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 1, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 2, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 2, k + 3, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 2, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 2, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 3, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
				bridge.setBlock(world, x + 5, y + 3, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 3, k + 3, JungleBlocks.mossystairs, 1, 2);
				bridge.setBlock(world, x + 5, y + 3, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 3, k + 5, Blocks.COBBLESTONE_WALL, 1, 2);
				bridge.setBlock(world, x + 5, y + 4, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
				bridge.setBlock(world, x + 5, y + 4, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 4, k + 3, Blocks.LOG, 7, 2);
				bridge.setBlock(world, x + 5, y + 4, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 4, k + 5, Blocks.COBBLESTONE_WALL, 1, 2);
				bridge.setBlock(world, x + 5, y + 5, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 5, y + 5, k + 2, JungleBlocks.mossystairs, 5, 2);
				bridge.setBlock(world, x + 5, y + 5, k + 3, JungleBlocks.mossystairs, 5, 2);
				bridge.setBlock(world, x + 5, y + 5, k + 4, JungleBlocks.mossystairs, 5, 2);
				bridge.setBlock(world, x + 5, y + 5, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 6, y + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 6, y + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 6, y + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 6, y + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 6, y + 1, k + 4, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 6, y + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 6, y + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
				bridge.setBlock(world, x + 6, y + 4, k + 3, Blocks.LOG, 7, 2);
				bridge.setBlock(world, x + 3, y + 2, k + 2, Blocks.TORCH, 3, 2);
				bridge.setBlock(world, x + 3, y + 2, k + 5, Blocks.JUNGLE_DOOR, 3, 2);
				bridge.setBlock(world, x + 3, y + 3, k + 5, Blocks.JUNGLE_DOOR, 8, 2);

				return true;
			}	
		
		
		public boolean jungleHutLargeEWG(World world, Random rand, int i, int j, int k)
		{
			i = i-10;
			j = j-1;
			k = k-2; 

		//check that each corner is one of the valid spawn blocks
		//if(!LocationIsValidSpawn(world, i, j, k) || !LocationIsValidSpawn(world, i + 6, j, k) || !LocationIsValidSpawn(world, i + 6, j, k + 8) || !LocationIsValidSpawn(world, i, j, k + 8))
			if(world.getBlockState(new BlockPos(i+3, j-5, k+4)).getBlock() == Blocks.AIR) {
				return false;
				}

			for(int fill = 2; fill <= 4; fill++)
				for(int fill2 = 1; fill2 <= 4; fill2++)
					for(int fill3 = 2; fill3 <= 6; fill3++)
					{
						bridge.setBlock(world, i+fill, j+fill2, k+fill3, Blocks.AIR);
					}
			
			new fillWithBlocks().fillWithRotation(world, i, j, k, 0, 6, -4, -1, 0, 8, Blocks.DIRT, "e");

			
		bridge.setBlock(world, i + 0, j + 0, k + 0, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 0, k + 1, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 0, j + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 0, j + 1, k + 1, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 0, j + 1, k + 2, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 0, j + 1, k + 3, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 0, j + 1, k + 4, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 0, j + 1, k + 5, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 0, j + 1, k + 6, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 0, j + 1, k + 7, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 0, j + 1, k + 8, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 0, j + 4, k + 2, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 0, j + 4, k + 4, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 0, j + 4, k + 6, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 0, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 0, k + 1, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 1, j + 1, k + 0, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 1, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 4, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 1, k + 8, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 1, j + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 4, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 5, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 6, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 2, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 3, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 3, k + 2, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 1, j + 3, k + 3, JungleBlocks.mossystairs);
		bridge.setBlock(world, i + 1, j + 3, k + 4, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 1, j + 3, k + 5, JungleBlocks.mossystairs);
		bridge.setBlock(world, i + 1, j + 3, k + 6, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 1, j + 3, k + 7, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 4, k + 2, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 4, k + 4, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 5, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 4, k + 6, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 5, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 1, j + 5, k + 2, JungleBlocks.mossystairs, 4, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 3, JungleBlocks.mossystairs, 4, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 4, JungleBlocks.mossystairs, 4, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 5, JungleBlocks.mossystairs, 4, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 6, JungleBlocks.mossystairs, 4, 2);
		bridge.setBlock(world, i + 1, j + 5, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 0, k + 0, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 1, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 1, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 1, k + 8, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 2, j + 2, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 2, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 3, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 3, k + 7, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 2, j + 4, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 4, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 4, k + 2, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 2, j + 4, k + 4, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 2, j + 4, k + 5, Blocks.LOG, 11, 2);
		bridge.setBlock(world, i + 2, j + 4, k + 6, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 2, j + 4, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 2, j + 5, k + 0, JungleBlocks.mossyslabSingle, 3, 2);
		bridge.setBlock(world, i + 2, j + 5, k + 1, JungleBlocks.mossystairs, 6, 2);
		bridge.setBlock(world, i + 2, j + 5, k + 2, Blocks.GRASS);
		bridge.setBlock(world, i + 2, j + 5, k + 3, Blocks.GRASS);
		bridge.setBlock(world, i + 2, j + 5, k + 4, Blocks.GRASS);
		bridge.setBlock(world, i + 2, j + 5, k + 5, Blocks.GRASS);
		bridge.setBlock(world, i + 2, j + 5, k + 6, Blocks.GRASS);
		bridge.setBlock(world, i + 2, j + 5, k + 7, JungleBlocks.mossystairs, 7, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 0, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 1, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 1, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 1, k + 8, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 3, j + 2, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 3, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 3, k + 7, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 3, j + 4, k + 0, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 3, j + 4, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 4, k + 2, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 3, j + 4, k + 4, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 3, j + 4, k + 6, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 3, j + 4, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 5, k + 1, JungleBlocks.mossystairs, 6, 2);
		bridge.setBlock(world, i + 3, j + 5, k + 2, Blocks.GRASS);
		bridge.setBlock(world, i + 3, j + 5, k + 3, Blocks.GRASS);
		bridge.setBlock(world, i + 3, j + 5, k + 4, Blocks.GRASS);
		bridge.setBlock(world, i + 3, j + 5, k + 5, Blocks.GRASS);
		bridge.setBlock(world, i + 3, j + 5, k + 6, Blocks.GRASS);
		bridge.setBlock(world, i + 3, j + 5, k + 7, JungleBlocks.mossystairs, 7, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 0, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 1, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 1, k + 3, JungleBlocks.mossyslabSingle, 3, 2);
		bridge.setBlock(world, i + 4, j + 1, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 1, k + 8, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 4, j + 2, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 2, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 3, k + 0, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 4, j + 3, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 3, k + 7, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 4, j + 4, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 4, k + 2, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 4, j + 4, k + 3, Blocks.LOG, 11, 2);
		bridge.setBlock(world, i + 4, j + 4, k + 4, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 4, j + 4, k + 6, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 4, j + 4, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 5, k + 1, JungleBlocks.mossystairs, 6, 2);
		bridge.setBlock(world, i + 4, j + 5, k + 2, Blocks.GRASS);
		bridge.setBlock(world, i + 4, j + 5, k + 3, Blocks.GRASS);
		bridge.setBlock(world, i + 4, j + 5, k + 4, Blocks.GRASS);
		bridge.setBlock(world, i + 4, j + 5, k + 5, Blocks.GRASS);
		bridge.setBlock(world, i + 4, j + 5, k + 6, Blocks.GRASS);
		bridge.setBlock(world, i + 4, j + 5, k + 7, JungleBlocks.mossystairs, 7, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 0, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 1, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 1, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 1, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 1, k + 4, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 1, k + 5, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 1, k + 6, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 1, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 1, k + 8, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 5, j + 2, k + 0, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 5, j + 2, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 2, k + 2, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 2, k + 4, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 2, k + 5, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 5, j + 2, k + 6, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 2, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 3, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 5, j + 3, k + 2, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 5, j + 3, k + 4, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 5, j + 3, k + 6, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 5, j + 3, k + 7, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 5, j + 4, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 4, k + 2, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 5, j + 4, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 4, k + 4, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 5, j + 4, k + 5, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 4, k + 6, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 5, j + 4, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 5, k + 1, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 5, k + 2, JungleBlocks.mossystairs, 5, 2);
		bridge.setBlock(world, i + 5, j + 5, k + 3, JungleBlocks.mossystairs, 5, 2);
		bridge.setBlock(world, i + 5, j + 5, k + 4, JungleBlocks.mossystairs, 5, 2);
		bridge.setBlock(world, i + 5, j + 5, k + 5, JungleBlocks.mossystairs, 5, 2);
		bridge.setBlock(world, i + 5, j + 5, k + 6, JungleBlocks.mossystairs, 5, 2);
		bridge.setBlock(world, i + 5, j + 5, k + 7, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 0, k + 0, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 1, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 1, k + 0, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 1, k + 1, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 6, j + 1, k + 2, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 6, j + 1, k + 3, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 1, k + 4, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 6, j + 1, k + 5, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 6, j + 1, k + 6, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 6, j + 1, k + 7, Blocks.STONEBRICK, 1, 2 );
		bridge.setBlock(world, i + 6, j + 1, k + 8, Blocks.MOSSY_COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 4, k + 2, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 6, j + 4, k + 4, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 6, j + 4, k + 6, Blocks.LOG, 7, 2);
		bridge.setBlock(world, i + 3, j + 2, k + 6, Blocks.TORCH, 4, 2); // e w s n 1 2 3 4 up 5.
		bridge.setBlock(world, i + 5, j + 2, k + 3, Blocks.JUNGLE_DOOR, 2, 2);
		bridge.setBlock(world, i + 5, j + 3, k + 3, Blocks.JUNGLE_DOOR, 8, 2);
		
		if(rand.nextInt(2) == 0)
		{
			bridge.setBlock(world, i + 4, j + 1, k + 6, Blocks.CHEST, 4, 2); // at 4 this chest faces west. 0 also faces west. 1 faces south.
			
			TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(new BlockPos(i+4, j+1, k+6));
			
			if (tileentitychest != null)
			{
				if(dim == 0)
				{
                    ((TileEntityChest)tileentitychest).setLootTable(fillme, rand.nextLong());

					//WeightedRandomChestContent.generateChestContents(rand, fillme, tileentitychest, 3);
				}
				else if(dim == w2theJungle.dimensionIdLost)
				{
                    ((TileEntityChest)tileentitychest).setLootTable(elsefillme, rand.nextLong());
				}
			}
		}

		return true;
		}
		
	@Override
	public boolean generate(World wrld, Random rnd, BlockPos pos)
	{

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
	
		return this.generate(wrld, rnd, x, y, z);
	}
}
