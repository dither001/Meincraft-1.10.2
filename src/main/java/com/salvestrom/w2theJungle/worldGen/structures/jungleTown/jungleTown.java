package com.salvestrom.w2theJungle.worldGen.structures.jungleTown;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockVine;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;
import com.salvestrom.w2theJungle.mobs.entity.EntityTheWall;
import com.salvestrom.w2theJungle.worldGen.JungleSaveWorld;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;
import com.salvestrom.w2theJungle.worldGen.structures.blockPlacmentBridge;
import com.salvestrom.w2theJungle.worldGen.structures.jungleBeaconWG;
import com.salvestrom.w2theJungle.worldGen.structures.jungleFireWG;

//TODO	allow town to spawn in mountains at 80+. change spawning to detect floor height
//
//add additional biomee checks north and south of temple, specifically for ocean biomes

public class jungleTown extends WorldGenerator
{
	public blockPlacmentBridge bridge = new blockPlacmentBridge();
	
	//components unique to town
	public jungleCityTemple temple = new jungleCityTemple(); //tick
	public jungleCityRoadEW eroad = new jungleCityRoadEW(); //tick
	public jungleCityRoadNS nroad = new jungleCityRoadNS(); //t
	public jungleCityBeacon townbeacon = new jungleCityBeacon(); //t
	public jungleBaskingArea basking = new jungleBaskingArea(); //t
	public jungleCityLibrary nlibrary = new jungleCityLibrary(); //t
	public jungleCityLibrarySouth slibrary = new jungleCityLibrarySouth(); //south facing!
	public jungleHutLargeEast elargehut = new jungleHutLargeEast();
	public jungleHutLargeNorth nlargehut = new jungleHutLargeNorth(); //south of road, facing north
	public jungleHutLargeSouth slargehut = new jungleHutLargeSouth(); //north of road. //t
	public jungleHutMediumNorth nmediumhut = new jungleHutMediumNorth();
	public jungleHutMediumSouth smediumhut = new jungleHutMediumSouth();  //south facing, z is negative in file
	public jungleHutSmallNorth nsmallhut = new jungleHutSmallNorth(); //north facing
	public jungleHutSmallSouth ssmallhut = new jungleHutSmallSouth();
	
	//additional parts.
	public jungleFireWG jFire = new jungleFireWG(); //change this to allow huts a large chance to spawn.
	public jungleBeaconWG jbeacon = new jungleBeaconWG();

	public jungleTown() {} //called from worldgen
	
	//good spots: -1509 -400; -1200 500
	
	public boolean generate(World world, Random rng, int x, int y, int z) {

		int mrl = rng.nextInt(5) + 7; //number of main road sections
		int amrl = mrl*6; //block length of road
		int radd = rng.nextInt(3);

		BlockPos pos = new BlockPos(x, y, z);		
		Biome bio = world.getBiome(pos);
		x = x - (mrl*3);
		//sets the temple position several blocks back from the passed in 
		//coordinates, the original x is essentially the village mid-point
				
		
		if(world.getBiome(pos.add(+10, 0, 10)) == //BiomeGenBase.plains) {
			JungleBiomeRegistry.biomeJungleSwamp) {
			y = y+8;//70+radd;//y+9; //adjust in temple file validity check as well
		}
		
		if(this.temple.passAlong(world, rng, bio, x, y, z, amrl))
		{
			//create dirt base
			if(world.getBiome(pos.add(+10, 0, 10)) == //BiomeGenBase.plains) { 
					JungleBiomeRegistry.biomeJungleSwamp) {
			
			for(int bl = -amrl-10; bl < amrl; bl++)
				for (int bw = (int) (-amrl/1.2); bw < amrl/1.16; bw++) //1.19 and 1.15
					for (int by = -12-radd; by < 0; by++) {
						
						int picked = (int) (((bw*bw)+1)/(Math.sqrt((bw*bw)+(bl*bl))*0.9)*(by*by*1.025));//.05
						int also = (int) (((bl*bl)+1)/(Math.sqrt((bw*bw)+(bl*bl))*0.9)*(by*by*0.152)); //.2
												
						int ra = (int) ((amrl*0.9));
						int a = (int) (amrl*(1.027-(mrl/550))); //semi-major axis as well as half the value of the loop length minus foci distance 
						int b = (int) (amrl/(1.6+(mrl/200))); //semi-minor axis. was 1.8
						int c = (int) Math.sqrt((a*a)-(b*b)); //half foci distance
						
						//the below describes an oval
						if ((Math.sqrt((c-bl)*(c-bl)+(bw*bw))
								+ Math.sqrt((c+bl)*(c+bl)+(bw*bw)))
								<=
								//a distance check, comprised of doubling the semi major axis, then adding
								//and subtracting a host of values that create various curves
								((a+a)-((Math.pow(mrl, 3)/343) - 1) * 0.8) //when mrl is 7, result is 0.
								//- pick/also, splays outward
								-(picked*0.0135)/(by*by*.275) //.25 //.15 for both
								-(also*0.0135)/(by*by*.25)
								//both by added as tests
								//(by*1.1 looks really good. float higher?? not that bad, but issues with whatevers underneath)
								+(Math.sin((bl*0.08))*bw*0.1) //.12
								+(Math.sin((bl*0.17))*bw*0.085)
								+(Math.sin(45+bl*0.29)*bw*0.065)
								
								+(Math.sin(55+bw*0.09)*bl*0.14) //550 shunts main curve
								+(Math.sin(40+bw*0.17)*bl*0.1)
								//this line affects vertical curvature. reducing the value of by cause inward curving
								+(Math.sin(30+(12 - by) * 0.13)*(((144 - by*by)/by) *(0.11))) //.2 .5 //by/by or by*by produces good results.
								//-(Math.sin(30+(by*0.9))*by*2.8)
								) {
							
							Block chk = world.getBlockState(new BlockPos(bl+20+x+ra/2, by+y, bw+z+10)).getBlock();
							
							if(chk == Blocks.WATER || chk == Blocks.FLOWING_WATER
									|| chk instanceof BlockBush
									|| chk instanceof BlockGrass
									|| chk instanceof BlockLog
									|| chk instanceof BlockLeaves
									|| chk instanceof BlockVine
									|| chk == Blocks.AIR) {
								if(by > -3)
								{
									if(world.getBiome(new BlockPos(bl+20+x+ra/2, 0, bw+z+10)) instanceof BiomeDesert)
									{
										bridge.setBlock(world, bl+20+x+ra/2, by+y, bw+z+10, Blocks.SAND, 1, 2);
									}
									else
									{
										bridge.setBlock(world, bl+20+x+ra/2, by+y, bw+z+10, rng.nextInt(3) < 2 ? Blocks.GRASS : Blocks.DIRT, 1, 2);
									}
									}
								else
									if(by+y >= 68+radd) 
									{
										if(world.getBiome(new BlockPos(bl+20+x+ra/2, 0, bw+z+10)) instanceof BiomeDesert)
										{
											bridge.setBlock(world, bl+20+x+ra/2, by+y, bw+z+10, Blocks.SAND, 1, 2);
										}
										else
										{
											bridge.setBlock(world, bl+20+x+ra/2, by+y, bw+z+10, Blocks.DIRT, 1, 2);
										}
										}
									else
										if(by+y >= 61+radd)
										{
											bridge.setBlock(world, bl+20+x+ra/2, by+y, bw+z+10, Blocks.STONE);
											}
								}
							}
						}
			}
			
			//TODO could use some decoration code in here. the platteau will only get attention on the parts
			//that generate before the rest of the chunk, and then only if the town is triggred in the biome file
			//NOT the worldgen file.
			
			//generating main road
			for(int l = 0; l < mrl; ++l) {
				int xl = x + 20 + (6*l);
				int zl = z+8;
				
				this.biomeCheck(this.eroad, world, rng, xl, y, zl);

				//1st junction
				if(l == 2) {
					xl = x + 20 + (6*l);
					zl = z+8;
					
					int rnd = rng.nextInt(2);
					//TODO could be removed since the fire is als dependent on biome
					//making dependent on road as well seems redundant
					
					if(this.biomeCheck(nroad, world, rng, xl+1, y, zl+4)) {
						if(mrl > 8) {
							this.biomeCheck(jFire, world, rng, x+33, y, z+22);
							} else if(rnd == 0) {
								this.biomeCheck(jFire, world, rng, x+33, y, z+22);
							}
						}
					
					if(this.biomeCheck(nroad, world, rng, xl+1, y, zl-6)) {
						if(mrl > 8) {
							this.biomeCheck(jFire, world, rng, x+33, y, z-6); //x+33 = xl+1...! z-6 = zl-14. z+22 = zl+14
							} else if(rnd == 1) {
								this.biomeCheck(jFire, world, rng, x+33, y, z-6);
								}
						}
					} //end 1st junction

				//2nd section
				if(mrl < 7) {
					this.biomeCheck(nsmallhut, world, rng, x+20+18, y-1, z+20);
					this.biomeCheck(nsmallhut, world, rng, x+20+24, y-1, z+20);
					this.biomeCheck(ssmallhut, world, rng, x+20+18, y-1, z);
					this.biomeCheck(ssmallhut, world, rng, x+20+24, y-1, z);
					} else if(mrl > 9) {
						this.biomeCheck(smediumhut, world, rng, x+20+20, y-1, z+6);
						this.biomeCheck(nmediumhut, world, rng, x+20+20, y-1, z+14);
					} else if(rng.nextInt(2)==0) {
						this.biomeCheck(smediumhut, world, rng, x+20+20, y-1, z+6);
						this.biomeCheck(nsmallhut, world, rng, x+20+18, y-1, z+20);
						this.biomeCheck(nsmallhut, world, rng, x+20+24, y-1, z+20);
						} else {
							this.biomeCheck(nmediumhut, world, rng, x+20+20, y-1, z+14);
							this.biomeCheck(ssmallhut, world, rng, x+20+18, y-1, z);
							this.biomeCheck(ssmallhut, world, rng, x+20+24, y-1, z);
						}
							
				
				//2nd junction	
				if(l == 5) {
					xl = x + 20 + (6*l);
					zl = z+8;
					this.biomeCheck(this.nroad, world, rng, xl+1, y, zl+4);
					this.biomeCheck(this.nroad, world, rng, xl+1, y, zl+10);
					this.biomeCheck(this.nroad, world, rng, xl+1, y, zl-6);
					this.biomeCheck(this.nroad, world, rng, xl+1, y, zl-12);

					if(rng.nextInt(2)==0) {
						this.biomeCheck(this.slibrary, world, rng, xl-3, y, zl-12);
						this.biomeCheck(this.nlargehut, world, rng, xl-1, y-1, zl+16);
					} else {
						this.biomeCheck(this.nlibrary, world, rng, xl-3, y, zl+16);
						this.biomeCheck(this.slargehut, world, rng, xl-1, y-1, zl-12);
						}
					}
				//3rd section
				if(mrl >= 8) {
					this.biomeCheck(this.smediumhut, world, rng, x+58, y-1, z+6);
					this.biomeCheck(this.nmediumhut, world, rng, x+58, y-1, z+14);
					this.biomeCheck(this.townbeacon, world, rng, x+61, y, z-6);
					this.biomeCheck(this.townbeacon, world, rng, x+61, y, z+24);
				}
				
				//3rd junction
				if(l == 8) {
					xl = x + 20 + (6*l);
					zl = z+8;
					this.biomeCheck(this.nroad, world, rng, xl+1, y, zl+4);
					this.biomeCheck(this.nroad, world, rng, xl+1, y, zl-6);
				}
				//4th junction
				if(l == 11) {
					xl = x + 20 + (6*l);
					zl = z+8;
					this.biomeCheck(this.nroad, world, rng, xl+1, y, zl+4);
					this.biomeCheck(this.nroad, world, rng, xl+1, y, zl-6);
				}
				
				this.townbeacon.generate(world, rng, x+28, y, z+15);
				this.townbeacon.generate(world, rng, x+28, y, z+3);
			}	//for ends here	x + 20 * mrl	z-2 
			
			x=x+20;
			z=z+5;

			//generates basking area at end of road, regardless of length
			if(basking.generate(world, rng, x + amrl, y, z)) {
					
				EntityTheWall thewall = new EntityTheWall(world);

				if(JungleSaveWorld.get(world).WallSpawnCount == 0 ) {
					
					JungleSaveWorld.get(world).wallSpawn(new int []{(int)(x+amrl + 5), (int)y + 2, (int)(z + 5)});

					thewall.setLocationAndAngles((double)x+amrl + 5.5D, (double)y+2D, (double)z + 5.5D, 0.0F, 0.0F);
					thewall.setWallSpawnType(1);
					world.spawnEntityInWorld(thewall);
					//thewall.setHomeArea((int)(x+amrl + 5.5), (int)y+2, (int)(z + 5.5), 15);
					//try using the home position. keep it tethered to the basking area.
					
					JungleSaveWorld.get(world).wallSpawnCount(1);
				} else {
					
					EntityStoneGolem stoneg = new EntityStoneGolem(world);

					stoneg.setLocationAndAngles((double)x+amrl - 5.5D, (double)y+2D, (double)z + 5.5D, 0.0F, 0.0F);
					world.spawnEntityInWorld(stoneg);
					

				}

				if(mrl > 8) {
					this.jbeacon.generate(world, rng, x+amrl+3, y, z-7);
					this.jbeacon.generate(world, rng, x+amrl+3, y, z+13);
					} else {
						this.townbeacon.generate(world, rng, x+amrl+4, y, z-6);
						this.townbeacon.generate(world, rng, x+amrl+4, y, z+14);
						}
				}

			//a cobble base
			for(int i = x - 22; i < x+amrl + 12; ++i)
				for(int k = z - 7; k < z + 18; ++k)
				{
					
					BlockPos pos2 = new BlockPos(i, y, k);
					
					if(world.getBlockState(pos2.add(0, -2, 0)).getBlock() != Blocks.AIR)
					{
						if(world.getBiome(pos2) instanceof BiomeDesert)
						{
							bridge.setBlock(world, i, y-1, k, Blocks.SANDSTONE, 1, 2);
							}
						else
						{
							bridge.setBlock(world, i, y-1, k, Blocks.MOSSY_COBBLESTONE);
							}
						}
				}


		}

		return true;
				
	}
	
	public boolean biomeCheck(WorldGenerator wg, World world, Random rng, int x, int y, int z) {
		
		BlockPos pos = new BlockPos(x, y, z);
		Biome bio;
		if(wg == eroad) {
			bio = world.getBiome(pos.add(3, 0, 2));
			} else if(wg == nroad) {
				bio = world.getBiome(pos.add(2, 0, 3));
				} else {
					bio = world.getBiome(pos);
					}
				
		if(bio == //BiomeGenBase.plains) {
			JungleBiomeRegistry.biomeJungleSwamp)
		{
			wg.generate(world, rng, pos);
			return true;
		}
		else if(bio instanceof BiomeJungle) {
			wg.generate(world, rng, pos);
			return true;
		}
		return false;
	}
	
	public boolean generate(World wrld, Random rnd, BlockPos pos) {

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		return this.generate(wrld, rnd, x, y, z);
	}

}
