package com.salvestrom.w2theJungle.worldGen.structures;

import java.util.Random;

import com.salvestrom.w2theJungle.blocks.mossySlab;
import com.salvestrom.w2theJungle.blocks.mossySlab.EnumType;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;
import com.salvestrom.w2theJungle.worldGen.loot_tables.JungleLootTableRegistry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;

public class lostTempleAdditionals {
	
	public blockPlacmentBridge bridge = new blockPlacmentBridge();
//TODO placeholder...
	//....
	public ResourceLocation fillchest = JungleLootTableRegistry.JUNGLE_CHESTS_TEMPLE;
	//	public WeightedRandomChestContent[] fillchest = new jungleLootTables().jungleTempleChestContents;
	public fillWithBlocks fill = new fillWithBlocks();
	public String rota = "e";
	public World world;
	
	public void setBlock(int x, int y, int z, Block block)
	{
		BlockPos pos = new BlockPos(x, y, z);
		
		this.world.setBlockState(pos, block.getDefaultState());
	}
	
	public void setBlock(int x, int y, int z, Block block, int i1, int i2)
	{
		BlockPos pos = new BlockPos(x, y, z);
		//TODO
		if(i1 == 0)
		{
			this.world.setBlockState(pos, block.getDefaultState(), i1);
		}
	}

	public void passAlong(World world, Random rand, int i, int j, int k, Biome bio) {
		
		this.world = world;
		
		Block block;
		
		//floor indent
		for(int a = 0; a <= 12; a++)
			for(int b = 0; b <= 8; b++) {
				if(a%6 == 0 && b%8 == 0) {
					
					int x = i + 9 + a;
					int y = j + 5;
					int z = k + 11 + b;

					block = Blocks.STONEBRICK;
					
					bridge.setBlock(world, x+1, y, z, block, 1, 2);
					bridge.setBlock(world, x, y, z-1, block, 1, 2);
					bridge.setBlock(world, x, y, z+1, block, 1, 2);
					bridge.setBlock(world, x-1, y, z, block, 1, 2);
					
					block = JungleBlocks.mossyslabSingle.getDefaultState().withProperty(mossySlab.VARIANT, EnumType.MOSSY_SMOOTH_SLAB).getBlock();  //smooth
					
					bridge.setBlock(world, x+1, y, z+1, block, 0, 2);
					bridge.setBlock(world, x-1, y, z-1, block, 0, 2);
					bridge.setBlock(world, x+1, y, z-1, block, 0, 2);
					bridge.setBlock(world, x-1, y, z+1, block, 0, 2);
				
					bridge.setBlock(world, x+2, y, z, block, 0, 2);
					bridge.setBlock(world, x-2, y, z, block, 0, 2);
					bridge.setBlock(world, x, y, z-2, block, 0, 2);
					bridge.setBlock(world, x, y, z+2, block, 0, 2);

					bridge.setBlock(world, x+2, y, z+1, block, 0, 2);
					bridge.setBlock(world, x-2, y, z-1, block, 0, 2);
					bridge.setBlock(world, x+1, y, z-2, block, 0, 2);
					bridge.setBlock(world, x-1, y, z+2, block, 0, 2);
					
					bridge.setBlock(world, x+2, y, z-1, block, 0, 2);
					bridge.setBlock(world, x-2, y, z+1, block, 0, 2);
					bridge.setBlock(world, x-1, y, z-2, block, 0, 2);
					bridge.setBlock(world, x+1, y, z+2, block, 0, 2);
					
					
				}
			}
		
		block = Blocks.AIR;
		
		//cave tunnel
		fill.fillWithRotation(world, i, j, k, 12, 12, 1, 1, 6, 6, block, "e");
		fill.fillWithRotation(world, i, j, k, 13, 13, 1, 1, 7, 7, block, "e");

		fill.fillWithRotation(world, i, j, k, 16, 16, 0, 0, 14, 16, block, "e");
		fill.fillWithRotation(world, i, j, k, 17, 17, 0, 0, 14, 16, block, "e");
		fill.fillWithRotation(world, i, j, k, 18, 18, 1, 1, 14, 14, block, "e");
		fill.fillWithRotation(world, i, j, k, 19, 19, 1, 1, 15, 16, block, "e");
		fill.fillWithRotation(world, i, j, k, 15, 15, 1, 1, 17, 17, block, "e");
		
		fill.fillWithRotation(world, i, j, k, 13, 13, 4, 4, 18, 22, Blocks.STONE, "e"); //stone, underhall under floor
				
		fill.fillWithRotation(world, i, j, k, 26, 26, 7, 7, 17, 17, block, "e"); //loneblock underhall
				
		fill.fillWithRotation(world, i, j, k, 37, 37, 2, 2, 18, 25, block, "e"); //first cavern.
		fill.fillWithRotation(world, i, j, k, 29, 29, 3, 3, 9, 13, block, "e"); //2nd cavern
		
		bridge.setBlock(world, i + 4, j + 13, k + 19, JungleBlocks.ancientMossyBlock); //moved from 20
		bridge.setBlock(world, i + 4, j + 14, k + 19, JungleBlocks.ancientMossyBlock);//moved from 20
		bridge.setBlock(world, i + 4, j + 15, k + 19, JungleBlocks.ancientMossyBlock);//moved from 20
		bridge.setBlock(world, i + 4, j + 16, k + 19, JungleBlocks.ancientMossyBlock);//moved from 20
		bridge.setBlock(world, i + 4, j + 13, k + 20, Blocks.AIR); //moved from 20
		bridge.setBlock(world, i + 4, j + 14, k + 20, Blocks.AIR);
		bridge.setBlock(world, i + 4, j + 15, k + 20, Blocks.AIR);
		bridge.setBlock(world, i + 4, j + 16, k + 20, Blocks.AIR);
		
		this.ruin(world, rand, i, j, k, bio);
		
	}

	public void ruin(World world, Random rand, int i, int j, int k, Biome bio) {

		boolean tablet = bio instanceof BiomeJungle;
		
		if(!tablet) {

			int x = i + 25 - rand.nextInt(10);
			int y = j + 40;
			int z = k + 20 - rand.nextInt(10);
			
			int r = 20;
			
			for(int x2 = -r; x2 < r; x2++)
				for(int y2 = -r; y2 < r; y2++)
					for(int z2 = -r; z2 < r; z2++) {
						if((x2*x2) + (y2*y2) + (z2*z2) < r*r) {
						bridge.setBlock(world, x + x2, y + y2, z + z2, Blocks.AIR);
						}
					}
			
			//this loop is intended to drown the top lair of temples in water.
			//add additional code to prevent water on the inside by using < j+something
			for(int x2 = 0; x2 < 55; x2++)
				for(int z2 = 0; z2 < 30; z2++)
				{
					BlockPos pos = new BlockPos(i, 62, k);
					if(world.getBlockState(pos.add(x2, 0, z2)).getBlock() == Blocks.AIR)
					{
						bridge.setBlock(world, i+x2, 62, k+z2, Blocks.WATER);
					}
				}
			}
		
		this.generateChests(world, rand, i, j, k);
		this.spawnSpawners(world, rand, i, j, k);
		
	}

	public void spawnSpawners(World world, Random rand, int i, int j, int k) {
		
		int x; int y; int z; 
		
		TileEntityMobSpawner tems;
		//west pillars
		if(rand.nextInt(2)==0) {
			
			x = i + 9;
			y = j + 6;
			z = k + 11;
			
			bridge.setBlock(world, x, y, z, Blocks.MOB_SPAWNER, 0, 2);
			bridge.setBlock(world, x, y, z+8, JungleBlocks.ancientMossyBlock);
			tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(x, y, z));
			
			if (tems != null)
			{
            	tems.getSpawnerBaseLogic().setEntityName("thejungle.LizardmanStalker");
//				tems.func_145881_a().setEntityName("LizardmanStalker");
			}
			
		} else {
			
			x = i + 9;
			y = j + 6;
			z = k + 19;
			
			bridge.setBlock(world, x, y, z, Blocks.MOB_SPAWNER, 0, 2);
			bridge.setBlock(world, x, y, z-8, JungleBlocks.ancientMossyBlock);

			tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(x, y, z));

			if (tems != null)
			{
            	tems.getSpawnerBaseLogic().setEntityName("thejungle.LizardmanStalker");
//				tems.func_145881_a().setEntityName("LizardmanStalker");
			}
		}
		
		//middle pillars
		if(rand.nextInt(2)==0) {
			
			x = i + 15;
			y= j + 6;
			z = k + 11;
			
			bridge.setBlock(world, x, y, z, Blocks.MOB_SPAWNER, 0, 2);
			bridge.setBlock(world, x, y, z+8, JungleBlocks.ancientMossyBlock);

			tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(x, y, z));
            
                if (tems != null)
                {
                	tems.getSpawnerBaseLogic().setEntityName("thejungle.LizardmanWitchDoctor");
//                    tems.func_145881_a().setEntityName("LizardmanWitchDoctor");
                }
            } else {
        		bridge.setBlock(world, i + 15, j + 6, k + 19, Blocks.MOB_SPAWNER, 0, 2);
    			bridge.setBlock(world, i+15, j+6, k+11, JungleBlocks.ancientMossyBlock);

                tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(i+15, j+6, k+19));

                if (tems != null)
                {
                	tems.getSpawnerBaseLogic().setEntityName("thejungle.LizardmanWitchDoctor");
//                	tems.func_145881_a().setEntityName("LizardmanWitchDoctor");
                }
            }	
		
		//east pillars
		if(rand.nextInt(2)==0) {
			bridge.setBlock(world, i+21, j+6, k+11, Blocks.MOB_SPAWNER, 0, 2);
			bridge.setBlock(world, i+21, j+6, k+19, JungleBlocks.ancientMossyBlock);
			tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(i+21, j+6, k+11));
			
			if (tems != null)
			{
            	tems.getSpawnerBaseLogic().setEntityName("thejungle.Lizardman");
//				tems.func_145881_a().setEntityName("Lizardman");
				}
			} else {
        		bridge.setBlock(world, i + 21, j + 6, k + 19, Blocks.MOB_SPAWNER, 0, 2);
    			bridge.setBlock(world, i+21, j+6, k+11, JungleBlocks.ancientMossyBlock);
                tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(i+21, j+6, k+19));

                if (tems != null)
                {
                	tems.getSpawnerBaseLogic().setEntityName("thejungle.Lizardman");
//                    tems.func_145881_a().setEntityName("Lizardman");
                }
            }
		
//		bridge.setBlock(world, i + 27, j + 18, k + 23, Blocks.lit_pumpkin, 2, 2); //sw tower. removed.
//		bridge.setBlock(world, i + 3, j + 18, k + 23, Blocks.lit_pumpkin); //nw tower at stairwell. remove
		
		if(rand.nextInt(2) == 0) {
			this.placeSpawners(world, rand, i+9, j+13, k+8, "thejungle.SacSkel");
			} else {
				this.placeSpawners(world, rand, i+9, j+13, k+16, "thejungle.SacSkel");
				}

		if(rand.nextInt(2) == 0) {
			this.placeSpawners(world, rand, i+4, j+13, k+18, "thejungle.SacSkel");
			} else {
				this.placeSpawners(world, rand, i+14, j+13, k+18, "thejungle.SacSkel");
				}

		EntityStoneGolem stone = new EntityStoneGolem(world);

		if(rand.nextInt(2) == 0) {
			stone.setLocationAndAngles((double)i+21+0.5D, (double)j+13+2D, (double)k+19+0.5D, 0.0F, 0.0F);
			} else {
				stone.setLocationAndAngles((double)i+15+0.5D, (double)j+18+2D, (double)k+19+0.5D, 0.0F, 0.0F);
				}
		world.spawnEntityInWorld(stone);
		
/*		bridge.setBlock(world, i + 9, j + 13, k + 16, Blocks.lit_pumpkin); //maze. spawn sac skels?
		bridge.setBlock(world, i + 10, j + 13, k + 18, Blocks.lit_pumpkin); //maze. dirt
		bridge.setBlock(world, i + 11, j + 13, k + 8, Blocks.lit_pumpkin); //maze. carved block
		bridge.setBlock(world, i + 14, j + 13, k + 18, Blocks.lit_pumpkin); //maze. first? dirt back */
		
//		bridge.setBlock(world, i + 21, j + 13, k + 19, Blocks.lit_pumpkin, 1, 2); //first left downstairs. swap for golem
//		bridge.setBlock(world, i + 15, j + 18, k + 23, Blocks.lit_pumpkin); //south-cent tower

		}

	public void generateChests(World world, Random rand, int i, int j, int k) {
		
		TileEntityChest tec;
		
		//first pair. west balcony and nw corner		
		if(rand.nextInt(2)==0) {
			if(world.getBlockState(new BlockPos(i+2, j+18-1, k+10)).getBlock() != Blocks.AIR) {
				bridge.setBlock(world, i + 2, j + 18, k + 10, Blocks.CHEST, 3, 2); //west balcony. south facing
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i+2, j+18, k+10));

				if(tec != null) {
					tec.setLootTable(fillchest, rand.nextLong());
//					WeightedRandomChestContent.generateChestContents(rand, fillchest, tec, 3);
				}

			}
		}
		else {
			if(world.getBlockState(new BlockPos(i + 2, j + 18 - 1, k + 8)).getBlock() != Blocks.AIR) {

				bridge.setBlock(world, i + 2, j + 18, k + 8, Blocks.CHEST, 1, 2); //inside nw tower, east facing.
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i+2, j+18, k+8));

				if(tec != null)
				{
					tec.setLootTable(this.fillchest, rand.nextLong());
//					WeightedRandomChestContent.generateChestContents(rand, fillchest, tec, 3);
				}
			}
		}
		
		//second pair. unreachable since blocks cant be mined out...
		if(rand.nextInt(2)==0)
		{
			if(world.getBlockState(new BlockPos(i + 15, j + 32 - 1, k + 8)).getBlock() != Blocks.AIR)
			{
				bridge.setBlock(world, i + 15, j + 32, k + 8, Blocks.CHEST, 0, 2); //tower top. both face south...
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i+15, j+32, k+8));

				if(tec != null)
				{
					tec.setLootTable(fillchest, rand.nextLong());
				}
			}
		} else {
			if(world.getBlockState(new BlockPos(i + 15, j + 32 - 1, k + 22)).getBlock() != Blocks.AIR)
			{
				bridge.setBlock(world, i + 15, j + 32, k + 22, Blocks.CHEST, 0, 2); //tower top, south tower
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i+15, j+32, k+22)); //i had j here wrong, causing the crash. the null check should only be necessary if i make a mistake like that!

				if(tec != null) {
					tec.setLootTable(fillchest, rand.nextLong());
				}
			}
		}
		
		//third pair. rafters  and upper floor
		if(rand.nextInt(2)==0) {
			if(world.getBlockState(new BlockPos(i + 4, j + 30 - 1, k + 15)).getBlock() != Blocks.AIR) {
				bridge.setBlock(world, i + 4, j + 30, k + 15, Blocks.CHEST, 3, 2); //rafters. faces east
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i+4, j+30, k+15));

				if(tec != null) {
					tec.setLootTable(fillchest, rand.nextLong());
				}
			}
		} else {

			bridge.setBlock(world, i + 4, j + 30, k + 15, Blocks.AIR);
			
			if(world.getBlockState(new BlockPos(i + 27, j + 23 - 1, k + 24)).getBlock() != Blocks.AIR) {
				bridge.setBlock(world, i + 27, j + 23, k + 24, Blocks.CHEST, 0, 2); //upper southeast.
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i+27, j+23, k+24));

				if(tec != null) {
					tec.setLootTable(fillchest, rand.nextLong());
				}
			}
		}

		//fifth pair. cave double chest. and lowest chest
		if(rand.nextInt(2)==0) {
			if(world.getBlockState(new BlockPos(i + 12, j + 1 - 1, k + 14)).getBlock() != Blocks.AIR)
			{
				bridge.setBlock(world, i + 38, j + 3, k + 9, Blocks.AIR, 0, 2); //sitting man
				bridge.setBlock(world, i + 12, j + 1, k + 14, Blocks.CHEST, 0, 2); //hidden cave. also faces east
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i+12, j+1, k+14));

				if(tec != null) {
					tec.setLootTable(fillchest, rand.nextLong());
				}
			}
		} else {
			if(world.getBlockState(new BlockPos(i + 38, j + 3 - 1, k + 9)).getBlock() != Blocks.AIR)
			{
				bridge.setBlock(world, i + 38, j + 3, k + 9, Blocks.CHEST, 4, 2); //sitting man. east?
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i + 38, j + 3, k + 9));

				if(tec != null) {
					tec.setLootTable(fillchest, rand.nextLong());
				}
			}
		}
		
		//sixth pair
		if(rand.nextInt(2)==0) {
			if(world.getBlockState(new BlockPos(i + 22, j + 13 - 1, k + 15)).getBlock() != Blocks.AIR)
			{
				bridge.setBlock(world, i + 22, j + 13, k + 15, Blocks.CHEST, 5, 2); //behind stairs
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i + 22, j + 13, k + 15));

				if(tec != null) {
					tec.setLootTable(fillchest, rand.nextLong());
				}
			}
		} else {
			if(world.getBlockState(new BlockPos(i + 12, j + 1 - 1, k + 15)).getBlock() != Blocks.AIR)
			{
				bridge.setBlock(world, i + 12, j + 1, k + 15, Blocks.CHEST, 5, 2); //forms double chest in hidden cave
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i + 12, j + 1, k + 15));

				if(tec != null) {
					tec.setLootTable(fillchest, rand.nextLong());
				}
			}
		}
	}
	
	public void placeSpawners(World world, Random rand, int i, int j, int k, String ntt) {

		//TileEntityMobSpawner tems;

		bridge.setBlock(world, i, j, k, Blocks.MOB_SPAWNER, 0, 2);
		TileEntity tems = world.getTileEntity(new BlockPos(i, j, k));
		
		if (tems instanceof TileEntityMobSpawner)
		{
			((TileEntityMobSpawner)tems).getSpawnerBaseLogic().setEntityName(ntt);
//			tems.func_145881_a().setEntityName(ntt);
			}
	}
}
