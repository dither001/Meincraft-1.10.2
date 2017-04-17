/*
*** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
*/

package com.salvestrom.w2theJungle.worldGen.structures.jungleTown;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;
import com.salvestrom.w2theJungle.worldGen.loot_tables.JungleLootTableRegistry;
import com.salvestrom.w2theJungle.worldGen.structures.blockPlacmentBridge;
import com.salvestrom.w2theJungle.worldGen.structures.fillWithBlocks;

// 3 and 11 are COBBLESTONE slabs (placed bottom of block and top/
// add half slab to temple rear entrance

public class jungleCityTemple extends WorldGenerator
{
	public blockPlacmentBridge bridge = new blockPlacmentBridge();

	public ResourceLocation fillchest = JungleLootTableRegistry.JUNGLE_CHESTS_TOWN;
	
	public fillWithBlocks fill = new fillWithBlocks();
	public String rota = "e";
	
	protected Block[] GetValidSpawnBlocks() {
		return new Block[] {
			Blocks.GRASS,
			Blocks.STONE,
			Blocks.DIRT,
			Blocks.WATER,
		};
	}
	
	//you know, im not sure this check works. there doesnt seem to be any mechanism for looping the check as
	//distancetoair increases...

	public boolean LocationIsValidSpawn(World world, int i, int j, int k) {
		
		
		//this inclusion of wallspawncount cock-blocks the spawning even when the other, similar, check is removed
		//however, it is required here to allow alttowns to spawn.
		//alternatively would need a true /false to determine if is full town orr alt town.
		if((this.biome == JungleBiomeRegistry.biomeJungleSwamp || this.biome == Biomes.PLAINS)
				//&& JungleSaveWorld.get(world).WallSpawnCount == 0
				) {
			j=j-8;
			return true;
		}
		
		int distanceToAir = 0;
		
		BlockPos pos = new BlockPos(i, j, k);
		Block checkID = world.getBlockState(pos).getBlock();
		//TODO this code differs from others, but then the checks below are all 1 block less than the other spawn checks. nothing is
		//changed overall

		if (checkID != Blocks.STONE || checkID != Blocks.GRASS || checkID != Blocks.DIRT || checkID != Blocks.WATER)
		{
			distanceToAir++;
			checkID = world.getBlockState(pos.add(0, distanceToAir, 0)).getBlock();
			}

		if (distanceToAir > 15)
		{
			return false;
			}
		
		j += distanceToAir - 1;
		
		pos = new BlockPos(i, j, k);

		Block blockID = world.getBlockState(pos.down(-1)).getBlock();
		Block blockIDAbove = world.getBlockState(pos).getBlock();
		Block blockIDBelow = world.getBlockState(pos.down(-2)).getBlock();
		
		for (Block x : GetValidSpawnBlocks())
		{
			if (blockIDAbove != Blocks.AIR)
			{
				return false;
				}
			if (blockID == x)
			{
				return true;
				}
			else if (blockID == Blocks.SNOW && blockIDBelow == x)
			{
				return true;
				}
			}
		return false;
		}
	
	public jungleCityTemple() {}
	
	public Biome biome;
	public boolean ruin;
	
	public boolean passAlong(World world, Random rng, Biome bio, int x, int y, int z, int l) {
		
		this.biome = bio;
		this.ruin = world.getBiome(new BlockPos(x+l+5, 0, z+5)) != JungleBiomeRegistry.biomeJungleSwamp;
		return this.generate(world, rng, x, y, z);
	}

	public boolean generate(World world, Random rand, int i, int j, int k) {

		//check that each corner is one of the valid spawn blocks
		//too frequent...
		/*
		if(world.provider.getDimension() == w2theJungle.dimensionIdLost)
		{
			j += 60;
		}
		*/
		
		if(!LocationIsValidSpawn(world, i+7, j, k+7)
				|| !LocationIsValidSpawn(world, i + 13, j, k+7)
				|| !LocationIsValidSpawn(world, i + 13, j, k + 13)
				|| !LocationIsValidSpawn(world, i+7, j, k + 13)
				){
			return false;
		}
		
		//prevents temple spawning in proximity to biome boundaries
		BlockPos pos = new BlockPos(i, j, k);
		if(world.getBiome(pos.add(10, 0, 10)) != this.biome
				|| world.getBiome(pos.add(10, 0, 30)) != this.biome
				|| world.getBiome(pos.add(10, 0, 20)) != this.biome
//				|| world.getBiomeGenForCoords(i+10, k+10) != biome
//				|| world.getBiomeGenForCoords(i+10, k+30) != biome
//				|| world.getBiomeGenForCoords(i+10, k-20) != biome
				)
		{
			return false;
		}
		
		if(world.getBiome(pos) instanceof BiomeSwamp)
		{
			j -= 1;
		}
		
		//air block to clear, allowing all air lines to be removed. placing first allows overwriting
		fill.fillWithRotation(world, i, j, k, 0, 20, 0, 20, 0, 20, Blocks.AIR, rota);
		
		bridge.setBlock(world, i + 0, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 7, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 0, j + 0, k + 10, JungleBlocks.mossyslabSingle, 0, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 0, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 13, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 14, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 15, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 17, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 18, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 0, j + 1, k + 0, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 0, j + 1, k + 1, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 0, j + 1, k + 7, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 0, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 0, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 0, j + 1, k + 13, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 0, j + 1, k + 19, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 0, j + 1, k + 20, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 7, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 10, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 13, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 14, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 15, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 17, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 18, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 1, j + 1, k + 0, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 1, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 1, k + 20, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 1, j + 2, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 2, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 2, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 3, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 3, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 1, j + 4, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 5, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 5, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 1, j + 6, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 12, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 15, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 2, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 2, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 10, JungleBlocks.mossystairs);
		bridge.setBlock(world, i + 2, j + 1, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 3, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 3, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 3, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 3, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 4, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 4, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 4, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 4, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 5, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 5, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 5, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 2, j + 6, k + 10, JungleBlocks.ancientMossyBlock);

		//fill.fillWithRotation(world, i, j, k, 2, 2, 7, 15, 0, 20, Blocks.AIR, rota);
		//fill.fillWithRotation(world, i, j, k, 3, 3, 8, 15, 0, 20, Blocks.AIR, rota);
		//fill.fillWithRotation(world, i, j, k, 4, 4, 9, 15, 0, 20, Blocks.AIR, rota);
		//fill.fillWithRotation(world, i, j, k, 5, 5, 8, 15, 0, 20, Blocks.AIR, rota);
		//fill.fillWithRotation(world, i, j, k, 6, 6, 8, 15, 0, 20, Blocks.AIR, rota);

		bridge.setBlock(world, i + 3, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 12, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 15, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 3, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 3, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 10, Blocks.GLOWSTONE);
		bridge.setBlock(world, i + 3, j + 1, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 5, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 8, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 11, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 12, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 2, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 2, k + 18, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 11, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 3, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 3, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 4, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 4, k + 8, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 4, k + 9, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 3, j + 4, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 4, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 4, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 5, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 5, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 5, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 5, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 5, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 6, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 6, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 3, j + 6, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 3, j + 7, k + 8, JungleBlocks.mossystairs);
		bridge.setBlock(world, i + 3, j + 7, k + 12, JungleBlocks.mossystairs);
		bridge.setBlock(world, i + 4, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 7, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 10, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 13, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 15, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 4, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 4, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 10, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 4, j + 1, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 3, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 2, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 2, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 2, k + 18, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 3, k + 3, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 3, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 3, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 3, k + 7, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 3, k + 8, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 3, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 3, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 3, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 3, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 3, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 3, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 3, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 4, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 9, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 4, j + 4, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 4, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 5, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 5, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 5, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 5, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 5, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 6, k + 8, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 4, j + 6, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 4, j + 6, k + 12, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 4, j + 7, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);

		generate2(world, rand, i, j, k);
		return true;
	}

	public boolean generate2(World world, Random rand, int i, int j, int k) {
		bridge.setBlock(world, i + 4, j + 7, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 4, j + 8, k + 8, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 4, j + 8, k + 12, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 0, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 0, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 15, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 5, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 5, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 5, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 2, k + 3, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 3, k + 3, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 3, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 3, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 3, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 8, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 4, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 13, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 4, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 4, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 5, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 10, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 5, j + 5, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 5, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 6, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 6, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 5, j + 6, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 6, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 5, j + 6, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 5, j + 7, k + 8, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 5, j + 7, k + 12, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 6, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 6, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 6, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 6, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 0, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 0, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 0, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 6, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 6, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 6, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 1, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 2, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 2, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 2, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 2, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 2, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 2, k + 18, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 3, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 3, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 3, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 3, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 3, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 4, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 4, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 4, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 4, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 5, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 5, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 5, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 5, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 5, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 5, k + 10, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 5, k + 11, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 5, k + 12, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 5, k + 13, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 6, j + 5, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 5, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 6, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 7, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 6, j + 7, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 7, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 7, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 7, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 7, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 7, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 7, j + 0, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 7, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 7, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 7, j + 0, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 0, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 7, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 7, j + 0, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 0, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 7, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 7, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 7, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 7, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 7, j + 1, k + 0, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 7, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 1, k + 3, JungleBlocks.ancientMossyBlock);

		
		bridge.setBlock(world, i + 7, j + 1, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 7, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 1, k + 20, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 7, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 3, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 4, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 7, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 4, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 5, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 5, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 5, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 5, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 5, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 5, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 6, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 6, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 6, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 6, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 6, k + 11, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 7, j + 6, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 6, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 6, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 7, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 7, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 7, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 7, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 7, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 7, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 7, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 8, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 8, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 9, k + 7, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 7, j + 9, k + 13, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 7, j + 10, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 10, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 7, j + 11, k + 7, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 7, j + 11, k + 13, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 7, j + 12, k + 7, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 7, j + 12, k + 13, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 7, j + 13, k + 7, JungleBlocks.mossystairs);
		bridge.setBlock(world, i + 7, j + 13, k + 13, Blocks.STONE_STAIRS);

		bridge.setBlock(world, i + 8, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 8, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 8, j + 0, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 7, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 8, j + 0, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 8, j + 0, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 12, Blocks.DIRT);
		bridge.setBlock(world, i + 8, j + 0, k + 13, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 14, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 15, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 8, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 8, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 8, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 8, j + 1, k + 0, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 1, k + 20, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 2, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 3, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 8, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 3, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 3, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 3, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 3, k + 19, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 8, j + 4, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 4, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 5, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 8, j + 5, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 5, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 5, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 5, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 5, k + 9, JungleBlocks.mossystairs, 7, 2);
		bridge.setBlock(world, i + 8, j + 5, k + 11, JungleBlocks.mossystairs, 6, 2);
		bridge.setBlock(world, i + 8, j + 5, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 5, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 5, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 8, j + 5, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 8, j + 5, k + 17, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 8, j + 6, k + 3, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 8, j + 6, k + 4, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 8, j + 6, k + 5, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 8, j + 6, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 6, k + 15, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 8, j + 6, k + 16, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 8, j + 6, k + 17, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 8, j + 7, k + 3, Blocks.STONE_STAIRS, 2, 2);
		bridge.setBlock(world, i + 8, j + 7, k + 4, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 8, j + 7, k + 5, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 8, j + 7, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 7, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 7, k + 9, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 8, j + 7, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 7, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 7, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 7, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 8, j + 7, k + 15, Blocks.STONE_STAIRS, 2, 2);
		bridge.setBlock(world, i + 8, j + 7, k + 16, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 8, j + 7, k + 17, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 8, j + 8, k + 4, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 8, j + 8, k + 8, JungleBlocks.jungleBrazier);
		bridge.setBlock(world, i + 8, j + 8, k + 9, Blocks.STONEBRICK, 1, 2); //here
		bridge.setBlock(world, i + 8, j + 8, k + 10, JungleBlocks.mossystairs, 0, 2);
		bridge.setBlock(world, i + 8, j + 8, k + 11, Blocks.STONEBRICK, 1, 2); //here
		bridge.setBlock(world, i + 8, j + 8, k + 12, JungleBlocks.jungleBrazier);
		bridge.setBlock(world, i + 8, j + 8, k + 16, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 8, j + 9, k + 9, JungleBlocks.mossyStoneSteps, 6, 2);
		bridge.setBlock(world, i + 8, j + 9, k + 10, JungleBlocks.altarAbstract);
		bridge.setBlock(world, i + 8, j + 9, k + 11, JungleBlocks.mossyStoneSteps, 7, 2);
		bridge.setBlock(world, i + 8, j + 13, k + 7, JungleBlocks.mossystairs, 5, 2);
		bridge.setBlock(world, i + 8, j + 13, k + 13, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 8, j + 14, k + 7, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 8, j + 14, k + 13, Blocks.STONE_SLAB, 3, 2);
		
		generate3(world, rand, i, j, k);
		return true;
	}

	public boolean generate3(World world, Random rand, int i, int j, int k) {
		
		bridge.setBlock(world, i + 9, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 0, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 0, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 0, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 15, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 9, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 1, k + 1, Blocks.STONE_STAIRS, 2, 2);
		bridge.setBlock(world, i + 9, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 1, k + 19, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 9, j + 2, k + 2, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 9, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 2, k + 18, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 9, j + 3, k + 3, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 9, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 3, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 9, j + 3, k + 17, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 9, j + 4, k + 4, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 9, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 4, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 9, j + 4, k + 16, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 9, j + 5, k + 5, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 9, j + 5, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 5, k + 8, JungleBlocks.mossystairs, 5, 2);
		bridge.setBlock(world, i + 9, j + 5, k + 12, JungleBlocks.mossystairs, 5, 2);
		bridge.setBlock(world, i + 9, j + 5, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 9, j + 5, k + 15, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 9, j + 6, k + 6, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 9, j + 6, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 6, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 6, k + 10, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 6, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 6, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 6, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 6, k + 14, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 9, j + 7, k + 7, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 9, j + 7, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 7, k + 9, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 9, j + 7, k + 10, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 9, j + 7, k + 11, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 9, j + 7, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 7, k + 13, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 9, j + 8, k + 8, JungleBlocks.mossyStoneSteps, 2, 2);
		bridge.setBlock(world, i + 9, j + 8, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 8, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 8, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 9, j + 8, k + 12, JungleBlocks.mossyStoneSteps, 3, 2);
		bridge.setBlock(world, i + 9, j + 14, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 9, j + 14, k + 13, Blocks.COBBLESTONE);
		
		bridge.setBlock(world, i + 10, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 10, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 10, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 0, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 0, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 0, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 12, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 15, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 10, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 10, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 10, j + 1, k + 1, Blocks.STONE_STAIRS, 2, 2);
		bridge.setBlock(world, i + 10, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 1, k + 19, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 10, j + 2, k + 2, Blocks.STONE_STAIRS, 2, 2);
		bridge.setBlock(world, i + 10, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 2, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 2, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 2, k + 18, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 10, j + 3, k + 3, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 10, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 3, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 3, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 10, j + 3, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 10, j + 3, k + 17, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 10, j + 4, k + 4, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 10, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 4, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 4, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 10, j + 4, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 10, j + 4, k + 16, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 10, j + 5, k + 5, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 10, j + 5, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 5, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 5, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 5, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 5, k + 15, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 10, j + 6, k + 6, Blocks.STONE_STAIRS, 2, 2);
		bridge.setBlock(world, i + 10, j + 6, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 6, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 10, j + 6, k + 10, Blocks.GLOWSTONE);
		bridge.setBlock(world, i + 10, j + 6, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 10, j + 6, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 6, k + 13, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 10, j + 6, k + 14, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 10, j + 7, k + 7, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 10, j + 7, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 7, k + 9, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 10, j + 7, k + 10, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 10, j + 7, k + 11, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 10, j + 7, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 7, k + 13, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 10, j + 8, k + 8, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 10, j + 8, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 8, k + 10, Blocks.STONEBRICK, 1, 2); //glowstone originally. temple top,
		bridge.setBlock(world, i + 10, j + 8, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 10, j + 8, k + 12, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 10, j + 14, k + 7, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 10, j + 14, k + 13, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 10, j + 15, k + 7, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 10, j + 15, k + 13, JungleBlocks.mossyslabSingle, 1, 2);
		
		bridge.setBlock(world, i + 11, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 0, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 15, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 11, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 1, k + 1, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 11, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 1, k + 3, JungleBlocks.ancientMossyBlock);

		bridge.setBlock(world, i + 11, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 1, k + 19, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 11, j + 2, k + 2, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 11, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 2, k + 18, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 11, j + 3, k + 3, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 11, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 3, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 11, j + 3, k + 17, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 11, j + 4, k + 4, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 11, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 4, k + 16, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 11, j + 5, k + 5, Blocks.STONE_STAIRS, 2, 2);
		bridge.setBlock(world, i + 11, j + 5, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 5, k + 8, JungleBlocks.mossystairs, 4, 2);
		bridge.setBlock(world, i + 11, j + 5, k + 12, JungleBlocks.mossystairs, 4, 2);
		bridge.setBlock(world, i + 11, j + 5, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 5, k + 15, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 11, j + 6, k + 6, Blocks.STONE_STAIRS, 2, 2);
		bridge.setBlock(world, i + 11, j + 6, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 6, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 6, k + 10, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 6, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 6, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 6, k + 13, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 11, j + 6, k + 14, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 11, j + 7, k + 7, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 11, j + 7, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 7, k + 9, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 11, j + 7, k + 10, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 11, j + 7, k + 11, Blocks.DIAMOND_BLOCK);
		bridge.setBlock(world, i + 11, j + 7, k + 12, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 11, j + 7, k + 13, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 11, j + 8, k + 8, JungleBlocks.mossyStoneSteps, 2, 2);
		bridge.setBlock(world, i + 11, j + 8, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 8, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 8, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 11, j + 8, k + 12, JungleBlocks.mossyStoneSteps, 3, 2);
		bridge.setBlock(world, i + 11, j + 14, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 11, j + 14, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 12, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 14, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 15, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 12, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 12, j + 1, k + 0, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 1, k + 20, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 2, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 3, k + 1, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 3, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 3, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 3, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 3, k + 19, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 4, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 4, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 5, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 5, k + 5, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 12, j + 5, k + 6, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 12, j + 5, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 5, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 5, k + 9, JungleBlocks.mossystairs, 7, 2);
		bridge.setBlock(world, i + 12, j + 5, k + 11, JungleBlocks.mossystairs, 6, 2);
		bridge.setBlock(world, i + 12, j + 5, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 5, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 5, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 5, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 5, k + 17, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 6, k + 3, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 6, k + 4, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 12, j + 6, k + 5, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 12, j + 6, k + 6, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 12, j + 6, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 6, k + 9, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 12, j + 6, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 6, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 6, k + 12, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 12, j + 6, k + 13, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 12, j + 6, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 6, k + 15, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 6, k + 16, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 12, j + 6, k + 17, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 7, k + 3, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 12, j + 7, k + 4, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 7, k + 5, Blocks.STONE_STAIRS, 3, 2);
		bridge.setBlock(world, i + 12, j + 7, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 7, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 7, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 7, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 7, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 7, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 7, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 12, j + 7, k + 15, JungleBlocks.mossystairs, 2, 2);
		bridge.setBlock(world, i + 12, j + 7, k + 16, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 12, j + 7, k + 17, JungleBlocks.mossystairs, 3, 2);
		bridge.setBlock(world, i + 12, j + 8, k + 4, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 12, j + 8, k + 8, JungleBlocks.jungleBrazier);
		bridge.setBlock(world, i + 12, j + 8, k + 9, JungleBlocks.mossyStoneSteps, 1, 2);
		bridge.setBlock(world, i + 12, j + 8, k + 10, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 12, j + 8, k + 11, JungleBlocks.mossyStoneSteps, 1, 2);
		bridge.setBlock(world, i + 12, j + 8, k + 12, JungleBlocks.jungleBrazier);
		bridge.setBlock(world, i + 12, j + 8, k + 16, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 12, j + 13, k + 7, JungleBlocks.mossystairs, 4, 2);
		bridge.setBlock(world, i + 12, j + 13, k + 13, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 12, j + 14, k + 7, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 12, j + 14, k + 13, JungleBlocks.mossyslabSingle, 1, 2);
		
		bridge.setBlock(world, i + 13, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 13, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 13, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 13, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 13, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 12, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 0, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 13, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 13, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 13, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 13, j + 1, k + 0, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 13, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 1, k + 3, JungleBlocks.ancientMossyBlock);

		bridge.setBlock(world, i + 13, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 1, k + 20, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 13, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 3, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 4, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 4, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 4, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 5, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 5, k + 6, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 5, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 5, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 5, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 5, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 5, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 5, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 5, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 6, k + 6, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 6, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 6, k + 9, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 6, k + 10, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 6, k + 11, Blocks.COBBLESTONE);

		generate4(world, rand, i, j, k);
		return true;
	}

	public boolean generate4(World world, Random rand, int i, int j, int k) {
		bridge.setBlock(world, i + 13, j + 6, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 6, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 6, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 13, j + 7, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 7, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 7, k + 9, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 13, j + 7, k + 10, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 13, j + 7, k + 11, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 13, j + 7, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 7, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 8, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 8, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 9, k + 7, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 13, j + 9, k + 13, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 13, j + 10, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 10, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 13, j + 11, k + 7, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 13, j + 11, k + 13, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 13, j + 12, k + 7, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 13, j + 12, k + 13, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 13, j + 13, k + 7, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 13, j + 13, k + 13, JungleBlocks.mossystairs, 1, 2);
		
		bridge.setBlock(world, i + 14, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 14, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 14, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 14, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 0, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 0, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 0, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 0, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 14, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 14, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 14, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 2, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 2, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 2, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 2, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 2, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 2, k + 18, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 3, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 3, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 3, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 4, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 4, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 4, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 4, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 4, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 4, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 4, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 4, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 4, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 5, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 5, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 5, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 5, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 5, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 5, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 5, k + 11, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 5, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 5, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 5, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 5, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 6, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 6, k + 7, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 14, j + 6, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 6, k + 9, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 14, j + 6, k + 10, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 14, j + 6, k + 11, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 14, j + 6, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 6, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 6, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 7, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 14, j + 7, k + 14, JungleBlocks.ancientMossyBlock);
		
		bridge.setBlock(world, i + 15, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 15, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 15, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 15, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 15, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 15, j + 0, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 0, k + 14, Blocks.GRASS);
		bridge.setBlock(world, i + 15, j + 0, k + 15, Blocks.GRASS);
		bridge.setBlock(world, i + 15, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 15, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 15, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 15, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 15, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 1, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 2, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 2, k + 18, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 3, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 3, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 3, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 3, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 4, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 4, k + 5, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 4, k + 6, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 4, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 4, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 4, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 4, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 4, k + 11, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 4, k + 12, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 4, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 4, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 4, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 5, k + 5, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 5, k + 6, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 5, k + 7, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 5, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 5, k + 9, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 15, j + 5, k + 10, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 15, j + 5, k + 11, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 15, j + 5, k + 12, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 5, k + 13, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 5, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 5, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 6, k + 5, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 15, j + 6, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 15, j + 6, k + 12, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 15, j + 6, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 15, j + 7, k + 8, JungleBlocks.mossystairs);
		bridge.setBlock(world, i + 15, j + 7, k + 12, Blocks.STONE_STAIRS);
		
		bridge.setBlock(world, i + 16, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 7, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 13, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 15, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 16, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 16, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 9, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 3, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 3, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 3, k + 17, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 4, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 4, k + 5, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 4, k + 6, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 4, k + 7, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 4, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 4, k + 9, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 16, j + 4, k + 10, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 16, j + 4, k + 11, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 16, j + 4, k + 12, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 4, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 4, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 16, j + 4, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 4, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 5, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 5, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 16, j + 6, k + 8, JungleBlocks.mossyslabSingle, 14, 2);
		bridge.setBlock(world, i + 16, j + 6, k + 12, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 16, j + 7, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 16, j + 7, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 16, j + 8, k + 8, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 16, j + 8, k + 12, JungleBlocks.mossyslabSingle, 1, 2);
		
		bridge.setBlock(world, i + 17, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 17, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 17, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 12, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 15, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 18, Blocks.DIRT);
		bridge.setBlock(world, i + 17, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 17, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 17, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 9, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 10, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 7, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 2, k + 8, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 2, k + 9, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 2, k + 10, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 2, k + 11, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 2, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 2, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 3, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 3, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 3, k + 5, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 3, k + 6, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 3, k + 7, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 3, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 3, k + 9, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 17, j + 3, k + 10, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 17, j + 3, k + 11, JungleBlocks.mossystairs, 1, 2);
		bridge.setBlock(world, i + 17, j + 3, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 3, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 3, k + 14, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 3, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 3, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 3, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 4, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 4, k + 8, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 17, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 4, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 5, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 17, j + 5, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 17, j + 6, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 17, j + 6, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 17, j + 7, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 17, j + 7, k + 12, JungleBlocks.ancientMossyBlock);

		generate5(world, rand, i, j, k);
		return true;
	}

	public boolean generate5(World world, Random rand, int i, int j, int k) {

		bridge.setBlock(world, i + 18, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 18, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 18, j + 0, k + 2, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 3, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 4, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 5, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 6, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 7, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 8, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 9, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 10, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 11, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 12, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 13, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 14, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 15, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 16, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 17, Blocks.DIRT);
		bridge.setBlock(world, i + 18, j + 0, k + 18, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 18, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 18, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 18, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 9, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 18, j + 1, k + 10, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 18, j + 1, k + 11, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 18, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 15, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 4, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 18, j + 2, k + 5, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 18, j + 2, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 9, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 18, j + 2, k + 10, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 18, j + 2, k + 11, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 18, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 18, j + 2, k + 16, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 18, j + 2, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 2, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 3, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 3, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 18, j + 6, k + 8, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 18, j + 6, k + 12, Blocks.STONE_SLAB, 11, 2);
		bridge.setBlock(world, i + 18, j + 7, k + 8, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 18, j + 7, k + 12, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 18, j + 8, k + 8, Blocks.STONE_SLAB, 3, 2);
		bridge.setBlock(world, i + 18, j + 8, k + 12, Blocks.STONE_SLAB, 3, 2);
		
		bridge.setBlock(world, i + 19, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 7, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 10, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 13, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 14, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 15, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 17, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 18, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 19, j + 1, k + 0, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 19, j + 1, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 2, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 3, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 4, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 5, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 6, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 7, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 9, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 19, j + 1, k + 10, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 19, j + 1, k + 11, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 19, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 13, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 14, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 15, Blocks.COBBLESTONE);
		bridge.setBlock(world, i + 19, j + 1, k + 16, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 17, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 18, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 1, k + 20, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 19, j + 2, k + 1, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 2, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 2, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 2, k + 19, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 3, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 19, j + 3, k + 12, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 19, j + 4, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 4, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 19, j + 5, k + 8, Blocks.COBBLESTONE_WALL, 1, 2);
		bridge.setBlock(world, i + 19, j + 5, k + 12, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 19, j + 6, k + 8, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 19, j + 6, k + 12, Blocks.COBBLESTONE_WALL);
		bridge.setBlock(world, i + 19, j + 7, k + 8, Blocks.STONE_STAIRS, 1, 2);
		bridge.setBlock(world, i + 19, j + 7, k + 12, Blocks.STONE_STAIRS, 1, 2);
		
		bridge.setBlock(world, i + 20, j + 0, k + 0, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 1, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 2, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 3, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 4, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 5, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 6, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 7, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 8, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 9, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 10, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 11, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 12, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 13, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 14, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 15, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 16, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 17, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 18, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 19, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 0, k + 20, Blocks.STONEBRICK, 1, 2);
		bridge.setBlock(world, i + 20, j + 1, k + 0, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 20, j + 1, k + 1, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 20, j + 1, k + 7, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 20, j + 1, k + 8, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 20, j + 1, k + 12, JungleBlocks.ancientMossyBlock);
		bridge.setBlock(world, i + 20, j + 1, k + 13, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 20, j + 1, k + 19, JungleBlocks.mossyslabSingle, 1, 2);
		bridge.setBlock(world, i + 20, j + 1, k + 20, JungleBlocks.ancientMossyBlock);
		
		bridge.setBlock(world, i + 7, j + 3, k + 8, Blocks.TORCH, 2, 2);
		bridge.setBlock(world, i + 7, j + 3, k + 12, Blocks.TORCH, 2, 2);
		bridge.setBlock(world, i + 11, j + 3, k + 8, Blocks.TORCH, 2, 2); //west
		bridge.setBlock(world, i + 11, j + 3, k + 12, Blocks.TORCH, 2, 2);
		
		bridge.setBlock(world, i + 10, j + 2, k + 5, Blocks.TORCH, 3, 2);
		bridge.setBlock(world, i + 10, j + 2, k + 15, Blocks.TORCH, 4, 2);
				
		bridge.setBlock(world, i + 15, j + 2, k + 10, Blocks.TORCH, 2, 2); //above the door i think.
		
		

		spawnSpawner(world, rand, i, j, k);
		SpawnChest(world, rand, i, j, k);
		if(ruin) {
			
			int x = i + 15 - rand.nextInt(10);
			int y = j + 12 - rand.nextInt(5) ;
			int z = k + 15 - rand.nextInt(10);
			
			int r = 8; //radius of circle
			
			for(int x2 = -r; x2 < r; x2++)
				for(int y2 = -r; y2 < r; y2++)
					for(int z2 = -r; z2 < r; z2++) {
						if((x2*x2) + (y2*y2) + (z2*z2) < r*r)
						{
							bridge.setBlock(world, x + x2, y + y2, z + z2, Blocks.AIR);
							bridge.setBlock(world, i+10, j+1, k+10, JungleBlocks.altarAbstract);

						}
					}

			
			
		}
		//System.out.println("did it");
		return true;
	}

	private void SpawnChest(World world, Random rand, int i, int j, int k) {
		
		if(!ruin) {
			
			this.placeChests(world, rand, i+5, j+1, k+16, i+11, j+1, k+16, 2);
			this.placeChests(world, rand, i+7, j+1, k+4, i+13, j+1, k+4, 3); //north pair
		}

		this.placeChests(world, rand, i+16, j+1, k+7, i+16, j+1, k+15, 4); //east pair		

	}

	private void spawnSpawner(World world, Random rand, int i, int j, int k) {
		this.placeSpawners(world, rand, i+10, j+1, k+6, "thejungle.Lizardman");
		this.placeSpawners(world, rand, i+10, j+1, k+14, "thejungle.LizardmanStalker");
		this.placeSpawners(world, rand, i+14, j+1, k+10, "thejungle.LizardmanWitchDoctor");

	}
	
	public void placeChests(World world, Random rand, int i, int j, int k, int i2, int j2, int k2, int facing)
	{		
		TileEntityChest tec;
		
		if(rand.nextInt(2)==0) {
			if(world.getBlockState(new BlockPos(i, j-1, k)).getBlock() != Blocks.AIR) {
				bridge.setBlock(world, i, j, k, Blocks.CHEST, facing, 2);
			}
				tec = (TileEntityChest)world.getTileEntity(new BlockPos(i, j, k));
				} else {
					if(world.getBlockState(new BlockPos(i2, j2 - 1, k2)).getBlock() != Blocks.AIR) {
						bridge.setBlock(world, i2, j2, k2, Blocks.CHEST, facing, 2);
						}
					tec = (TileEntityChest)world.getTileEntity(new BlockPos(i2, j2, k2));
					}
		if(tec != null) {
			tec.setLootTable(fillchest, rand.nextLong());
			}
		}

	public void placeSpawners(World world, Random rand, int i, int j, int k, String ntt)
	{
		TileEntityMobSpawner tems;
		
		bridge.setBlock(world, i, j, k, Blocks.MOB_SPAWNER, 0, 2);
		tems = (TileEntityMobSpawner)world.getTileEntity(new BlockPos(i, j, k));
		
		if (tems != null)
		{
			tems.getSpawnerBaseLogic().setEntityName(ntt);
		}
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