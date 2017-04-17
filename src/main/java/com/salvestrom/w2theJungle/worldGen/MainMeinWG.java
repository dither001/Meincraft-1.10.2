package com.salvestrom.w2theJungle.worldGen;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;
import com.salvestrom.w2theJungle.worldGen.structures.JungleFallenTree;
import com.salvestrom.w2theJungle.worldGen.structures.JungleFallenTree2;
import com.salvestrom.w2theJungle.worldGen.structures.LostWorldHeart;
import com.salvestrom.w2theJungle.worldGen.structures.jungleBeaconWG;
import com.salvestrom.w2theJungle.worldGen.structures.jungleFireWG;
import com.salvestrom.w2theJungle.worldGen.structures.lostTempleWG;
import com.salvestrom.w2theJungle.worldGen.structures.jungleTown.jungleCityBeacon;
import com.salvestrom.w2theJungle.worldGen.structures.jungleTown.jungleTownAlt;

public class MainMeinWG implements IWorldGenerator {
	
	/* check youtube playlist for good way to simplify this class.
	 */
	
	@Override
	public void generate(Random rng, int X, int Z, World world, IChunkGenerator chnkGen, IChunkProvider chnkProv){
		
		switch(world.provider.getDimension())
		{
		case -1:
			generateInNether(world, rng, X*16, Z*16);
			break;
		case 0:
			generateInOverworld(world, rng, X*16, Z*16);
			break;
		case 1:
			generateInEnd(world, rng, X*16, Z*16);
			break;
		}	
		if(world.provider.getDimension() == w2theJungle.dimensionIdLost)
		{
			generateInLostWorld(world, rng, X*16, Z*16);
		}
			
	}

	private void generateInEnd(World world, Random rnd, int x, int z) {}
	
	public boolean dun;
	public int tempRnd1;
	public int tempRnd2;
	//i couuld use these variables for everything so no structure is next to any other.?
	//however, this would make all buildings change place according to the way a map is revealled.
	//the game precreates coordinates for villages and temples. etc
	int plx;
	int plz;

	/*
	 * apparently, if you specify coords for a chunk beyond the los of a player it will run the chunk provider for
	 * that chunk, triggering this code for that chunk, as well.
	 */
	private void generateInOverworld(World world, Random rnd, int x, int z)
	{		
		Biome biome = world.getBiome(new BlockPos(x, 0, z));
/*
		if(world.getWorldTime()%20 == 0)
		{
			//System.out.println(JungleSaveWorld.get(world).WallSpawnCount);
			
			System.out.println(JungleSaveWorld.get(world).templeLocX);
			System.out.println();
			System.out.println(this.tempRnd1);
		}
		*/

		this.dun = JungleSaveWorld.get(world).dun;

		//better to allow spawning on old world. by seperating out the individual locations the game will not spawn
		//these buildings on an older world.
		//however, the prior way would only spawn them once new territory was revealled. besides, why start this mod on an existing worl??
		if(!this.dun)
		{
			this.tempRnd1 = (-6 + rnd.nextInt(12)) * 16;
			this.tempRnd2 = (rnd.nextInt(24) + 112) * 16;

			int [] ii = {this.tempRnd1, this.tempRnd2};
			
			JungleSaveWorld.get(world).jungleTemple(true);
			JungleSaveWorld.get(world).templeLoc(ii);
			}
		else
		{
			this.tempRnd1 = JungleSaveWorld.get(world).templeLocX;
			this.tempRnd2 = JungleSaveWorld.get(world).templeLocY;
		}	
		
		
		
		//does this try to spawn them instantly?? YES (old method thebuck, is no longer used. but then,
		//it didnt seemto speed up loading and slowed gen in temple vacinity?
		if(x == this.tempRnd1 && z == this.tempRnd2)
		{
			new lostTempleWG().generate(world, rnd, this.tempRnd1, 0, this.tempRnd2); //south
		}
		if(x == -this.tempRnd1 && z == -this.tempRnd2)
		{
			new lostTempleWG().generate(world, rnd, -this.tempRnd1, 0, -this.tempRnd2); //north
		}
		if(z == this.tempRnd1 && x == this.tempRnd2)
		{
			new lostTempleWG().generate(world, rnd, this.tempRnd2, 0, this.tempRnd1); //east
		}
		if(z == -this.tempRnd1 && x == -this.tempRnd2)
		{
			new lostTempleWG().generate(world, rnd, -this.tempRnd2, 0, -this.tempRnd1); //west
		}

		//if the world type is not the mod specific one, simple temples will spawn in swamps.
		if(biome instanceof BiomeSwamp && world.getWorldInfo().getTerrainType() != JungleBiomeRegistry.JUNGLETYPE
				&& rnd.nextInt(150) == 0) //already 1 in 70...
			//could add the wallspawncount here, preventing the line returning true unless wall is killed.
		{
			int blockX = x + rnd.nextInt(8) + 4;
			int blockZ = z + rnd.nextInt(8) + 4;
			int y = world.getHeight(new BlockPos(blockX, 0, blockZ)).getY();
			
			if(y < 69)
			{
				(new jungleTownAlt()).generate(world, rnd, blockX, y, blockZ);
			}
		}
		
		//opens seconday gen file.			
		if(biome == //BiomeGenBase.plains
				JungleBiomeRegistry.biomeJungleSwamp
				 && rnd.nextInt(20)==0) {

			new JungleSwampStructureGeneration(world, rnd, x, z);
			//this is effectively one roll per chunk. meaning 1 object per 10 chunks.
		}
	
		if((biome == JungleBiomeRegistry.biomeJungleMountain) && rnd.nextInt(80)==0)
		{			
			int blockX = x + rnd.nextInt(8) + 4;
			int blockZ = z + rnd.nextInt(8) + 4;
			int y = world.getHeight(new BlockPos(blockX, 0, blockZ)).getY();

			if(y > 75 && y < 110) {			
				(new jungleTownAlt()).generate(world, rnd, blockX, y, blockZ);
				}
			}
		
		if (biome instanceof BiomeJungle ||
				biome == JungleBiomeRegistry.biomeJungleMountain || biome == JungleBiomeRegistry.biomeJungleSwamp )
		{
			for (int i = 0; i <= 3; i++)
			{
				int chnkX = x + rnd.nextInt(16);
				int chnkY = rnd.nextInt(6) + 16;
				int chnkZ = z + rnd.nextInt(16);
				
				(new WorldGenMinable(JungleBlocks.oreSapphire.getDefaultState(), 3)).generate(world, rnd, new BlockPos(chnkX, chnkY, chnkZ));
				}
			}
			
		if((biome == Biomes.JUNGLE
				|| biome == Biomes.JUNGLE_HILLS
				|| biome == Biomes.MUTATED_JUNGLE
				|| biome == JungleBiomeRegistry.biomeJungleMountain)
				&& rnd.nextInt(7)==0)
		{
			int blockX = x + rnd.nextInt(16) + 8;
            int yLayer = rnd.nextInt(127)+53;
            int blockZ = z + rnd.nextInt(16) + 8;
            
            (new WorldGenLakes(JungleBlocks.mudBlock)).generate(world, rnd, new BlockPos(blockX, yLayer, blockZ));
            }
            
		//TODO create super random, that combines all possible options - eg, mud pool or water or hut or beacon.
		if(biome == JungleBiomeRegistry.biomeJungleSwamp)
		{
			for(int limit = 0; limit < 9; limit++)
			{				
				int blockX = x + rnd.nextInt(16);
				int yLayer = rnd.nextInt(45)+11;
				int blockZ = z + rnd.nextInt(16);
				
				(new WorldGenLakes(Blocks.WATER)).generate(world, rnd, new BlockPos(blockX, yLayer, blockZ));
				}
			}
		
		//fire/hut combo
		if (biome instanceof BiomeJungle || biome == JungleBiomeRegistry.biomeJungleMountain)
		{
			if (biome != JungleBiomeRegistry.biomeJungleSwamp && rnd.nextInt(10)==0)
			{
				int jFireLocX = x + rnd.nextInt(16);
				int jFireLocZ = z + rnd.nextInt(16);
				int jFireLocY = world.getHeight(new BlockPos(jFireLocX, 0, jFireLocZ)).getY();
				//TODO switch this back to a random so it has a chance to appear n mountain overhangs.

				if(jFireLocY < 85)
				{
					(new jungleFireWG()).generate(world, rnd, jFireLocX, jFireLocY,	jFireLocZ);
					}
				}
			}
		
		//using get height has greatly increased spawning. will need to add /random
		//using brackets isolates the variables allowing for repetition.
		if(biome == Biomes.JUNGLE_EDGE && rnd.nextInt(22) == 0)	{
			
			int jBeaconLocX = x + rnd.nextInt(16);
			int jBeaconLocZ = z + rnd.nextInt(16);
			int jBeaconLocY = world.getHeight(new BlockPos(jBeaconLocX, 0, jBeaconLocZ)).getY();

			(new jungleBeaconWG()).generate(world, rnd, jBeaconLocX, jBeaconLocY, jBeaconLocZ);
			}
	 
			if(biome == Biomes.JUNGLE_HILLS && rnd.nextInt(28) == 0)	{
				
				int jBeaconLocX = x + rnd.nextInt(16);
				int jBeaconLocZ = z + rnd.nextInt(16);
				int jBeaconLocY = world.getHeight(new BlockPos(jBeaconLocX, 0, jBeaconLocZ)).getY();
				
				(new jungleBeaconWG()).generate(world, rnd, jBeaconLocX, jBeaconLocY, jBeaconLocZ);
				}
			
			if(biome == Biomes.JUNGLE && rnd.nextInt(25) == 0)	{
				
				int jBeaconLocX = x + rnd.nextInt(16);
				int jBeaconLocZ = z + rnd.nextInt(16);
				int jBeaconLocY = world.getHeight(new BlockPos(jBeaconLocX, 0, jBeaconLocZ)).getY();
						
				(new jungleBeaconWG()).generate(world, rnd, jBeaconLocX, jBeaconLocY, jBeaconLocZ);
				}
			
			if(biome == JungleBiomeRegistry.biomeJungleMountain && rnd.nextInt(31) == 0)
			{				
				int jBeaconLocX = x + rnd.nextInt(16);
				int jBeaconLocZ = z + rnd.nextInt(16);
				int jBeaconLocY = world.getHeight(new BlockPos(jBeaconLocX, 0, jBeaconLocZ)).getY();

				
				if(jBeaconLocY < 105) {
					(new jungleBeaconWG()).generate(world, rnd, jBeaconLocX, jBeaconLocY, jBeaconLocZ);
					}
				}
	}

	private void generateInNether(World world, Random rnd, int x, int z) {}

	public int chunkcount;
	
	private void generateInLostWorld(World world, Random rng, int x, int z) {
		
		
		/*-45-195. -80 to 85.
		 * below coordinates must be multiples of 16.
		 * this breaks it up into more processor friendly chunks, since
		 * the heart is technically a structure consisting of over 3 million blocks.
		 * the fact it only takes 9 seconds to load when generated as a single structure is pretty
		 * amazing, really.
		 */
		//System.out.println(z);

		if(x == -32 && z == -48) {
			new LostWorldHeart().partialBuild(world, rng, -45, -21, -80, -0);
		}
		if(x == -16 && z == -48) {
			if(new LostWorldHeart().partialBuild(world, rng, -20, -0, -80, -0)) {
				this.chunkcount +=1; //nw2 
			}
		}
		if(x == 144 && z == -32)		{
			new LostWorldHeart().partialBuild(world, rng, 86, 195, -80, -0);
		}
		if(x == 48 && z == -48)		{
			if(new LostWorldHeart().partialBuild(world, rng, 0, 85, -80, -0)) {
			this.chunkcount +=1; //ne2
			}
		}
		if(x == -32 && z == 48)		{
			new LostWorldHeart().partialBuild(world, rng, -45, -21, 0, 85);
		}
		if(x == -16 && z == 48)		{
			if(new LostWorldHeart().partialBuild(world, rng, -20, -0, 0, 85)) {
				this.chunkcount +=1; //sw2
			}
		}
		if(x == 144 && z == 48)		{
			new LostWorldHeart().partialBuild(world, rng, 96, 195, 0, 85);
		}
		if(x == 48 && z == 48)		{
			if(new LostWorldHeart().partialBuild(world, rng, 0, 95, 0, 85)) {
				this.chunkcount +=1; //se2
			}
		}

		//actual temple wont generate until the 4 sections in which it will appear have returned true.
		if(this.chunkcount == 4) //(x == 0 && z == 0) {
		{
			this.chunkcount = 0;
			(new LostWorldHeart()).generate(world, rng, -17, 39, -17);
			} //38 from 35
		
		if(rng.nextInt(2) == 0)	{
			int chnkX = x + rng.nextInt(16);
			int chnkY = ((rng.nextInt(80)) + 35);
			int chnkZ = z + rng.nextInt(16);
			(new JungleFallenTree()).generate(world, rng, chnkX, chnkY, chnkZ);
		}
		else {
			int chnkX = x + rng.nextInt(16);
			int chnkY = ((rng.nextInt(80)) + 35);
			int chnkZ = z + rng.nextInt(16);
			(new JungleFallenTree2()).generate(world, rng, chnkX, chnkY, chnkZ);
		}

		{
            int blockX = x + rng.nextInt(16) + 8;
            int yLayer = rng.nextInt(110); //was 255
            int blockZ = z + rng.nextInt(16) + 8;
            (new WorldGenLakes(JungleBlocks.mudBlock)).generate(world, rng, new BlockPos(blockX, yLayer, blockZ));
        }
		//lifted straight out of the biomggenjungle
		{
        int k = x + rng.nextInt(16) + 8;
        int l = z + rng.nextInt(16) + 8;
        int i1 = rng.nextInt(80)+ 50;
        (new WorldGenMelon()).generate(world, rng, new BlockPos(k, i1, l));
	
        //junglefire/hut combo
        
    	{
    		int jFireLocX = x + rng.nextInt(16);
    		int jFireLocZ = z + rng.nextInt(16);
    		int jFireLocY = world.getHeight(new BlockPos(jFireLocX, 0, jFireLocZ)).getY();

			
    		//System.out.println(jFireLocY);

    		int rnd = rng.nextInt(50);

    		if(jFireLocY < 110 && rnd > 35)
    		{
    			(new jungleFireWG()).generate(world, rng, jFireLocX, jFireLocY,	jFireLocZ);
    			}
    		else
    			if(rnd > 30)
    			{
    				(new jungleCityBeacon()).generate(world, rng, jFireLocX, jFireLocY, jFireLocZ);
    				}
    			else
    				if(rnd > 25)
    				{
    					(new jungleTownAlt()).generate(world, rng, jFireLocX, jFireLocY, jFireLocZ);
    					}
    		//System.out.println(rnd);
    		}
    	
        //removed to try and reduce lag.
        /*WorldGenVines worldgenvines = new WorldGenVines();

        for (l = 0; l < 50; ++l)
        
            i1 = x + rng.nextInt(16) + 8;
            short short1 = 128;
            int j1 = z + rng.nextInt(16) + 8;
            worldgenvines.generate(world, rng, i1, short1, j1);
            */
        }
		
	}

}

