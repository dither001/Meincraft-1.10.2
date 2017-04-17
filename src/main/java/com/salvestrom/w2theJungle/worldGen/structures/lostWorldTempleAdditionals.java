package com.salvestrom.w2theJungle.worldGen.structures;

import java.util.Random;

import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;
import com.salvestrom.w2theJungle.worldGen.loot_tables.JungleLootTableRegistry;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class lostWorldTempleAdditionals {
	
	public ResourceLocation fillchest = JungleLootTableRegistry.JUNGLE_CHESTS_LOST;
	public blockPlacmentBridge bridge = new blockPlacmentBridge();
	
	public void passAlong(World world, Random rand, int i, int j, int k) {
		
		//upper balcony room wall.
		bridge.setBlock(world, i + 14, j + 8, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 8, k + 20, JungleBlocks.ancientMossyBlock); 
		bridge.setBlock(world, i + 14, j + 9, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 9, k + 20, JungleBlocks.ancientMossyBlock); 
		bridge.setBlock(world, i + 14, j + 10, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 10, k + 19, JungleBlocks.ancientMossyBlock); 
		bridge.setBlock(world, i + 14, j + 10, k + 20, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 11, k + 18, JungleBlocks.ancientMossyBlock); 
		bridge.setBlock(world, i + 14, j + 11, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 11, k + 20, JungleBlocks.ancientMossyBlock); 

		
		this.generateChests(world, rand, i, j, k);
		this.spawnSpawners(world, rand, i, j, k);
		
	}

	private void spawnSpawners(World world, Random rand, int i, int j, int k) {
		
		this.placeSpawners(world, rand, i+5, j+2, k+25, i+24, j+2, k+25, "thejungle.Lizardman");
		this.placeSpawners(world, rand, i+20, j+8, k+14, i+15, j+3, k+12, "thejungle.LizardmanStalker");
		this.placeSpawners(world, rand, i+9, j+7, k+23, i+15, j+7, k+23, "thejungle.LizardmanWitchDoctor");
	
	}

	private void generateChests(World world, Random rand, int i, int j, int k) {

		this.placeChests(world, rand, i+5, j+2, k+27, 5, i+15, j+2, k+28, 2);
		this.placeChests(world, rand, i+16, j+8, k+18, 3, i+20, j+8, k+16, 4);
		this.placeChests(world, rand, i+15, j+3, k+20, 2, i+27, j+2, k+4, 3);
		
		//bridge.setBlock(world, i + 5, j + 2, k + 27, Blocks.chest, 1, 2); //first room, right corridor
		//bridge.setBlock(world, i + 15, j + 2, k + 28, Blocks.chest, 0, 2); //2nd room, left corridor.
		
//unused		bridge.setBlock(world, i + 15, j + 3, k + 14, Blocks.chest, 0, 2); // 2nd gallery room
//		bridge.setBlock(world, i + 15, j + 3, k + 20, Blocks.chest, 0, 2); // 1st "gallery" room
//		bridge.setBlock(world, i + 27, j + 2, k + 4, Blocks.chest, 2, 2); //lesser hall
		
//		bridge.setBlock(world, i + 16, j + 8, k + 18, Blocks.chest, 0, 2); //upper balcony
//		bridge.setBlock(world, i + 20, j + 8, k + 16, Blocks.chest, 0, 2); //upper floor, 1st chamber
		
	}
	
	public void placeChests(World world, Random rand, int i, int j, int k, int meta1, int i2, int j2, int k2, int meta2) {
		
		TileEntityChest tec;
		
		if(rand.nextInt(2)==0) {
			if(world.getBlockState(new BlockPos(i, j-1, k)).getBlock() != Blocks.AIR) {
				bridge.setBlock(world, i, j, k, Blocks.CHEST, meta1, 2);
			}
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i, j, k));
				
//				if(tec != null) {					WeightedRandomChestContent.generateChestContents(rand, fillchest, tec, 3);					}
				
			} else {
				if(world.getBlockState(new BlockPos(i2, j2 - 1, k2)).getBlock() != Blocks.AIR) {
					bridge.setBlock(world, i2, j2, k2, Blocks.CHEST, meta2, 2);
				}
					tec = (TileEntityChest)world.getTileEntity(new BlockPos(i2, j2, k2));
			}
		
		if(tec != null) {
			tec.setLootTable(fillchest, rand.nextLong());
			}
		}
	
	public void placeSpawners(World world, Random rand, int i, int j, int k, int i2, int j2, int k2, String ntt) {
		TileEntityMobSpawner tems;
		EntityStoneGolem stone = new EntityStoneGolem(world);
		
		if(rand.nextInt(2)==0) {
			bridge.setBlock(world, i, j, k, Blocks.MOB_SPAWNER, 0, 2);
			tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(i, j, k));
	        stone.setLocationAndAngles((double)i2 + 0.5D, (double)j2+0.5D, (double)k2 + 0.5D, 0.0F, 0.0F);

			} else {
				bridge.setBlock(world, i2, j2, k2, Blocks.MOB_SPAWNER, 0, 2);
				tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(i2, j2, k2));
		        stone.setLocationAndAngles((double)i + 0.5D, (double)j+0.5D, (double)k + 0.5D, 0.0F, 0.0F);
				}
		
		if (tems != null)
		{
			tems.getSpawnerBaseLogic().setEntityName(ntt);
			//tems.func_145881_a().setEntityName(ntt);
			world.spawnEntityInWorld(stone);
			}
	}
}