package com.salvestrom.w2theJungle.worldGen.structures.jungleTown;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;
import com.salvestrom.w2theJungle.mobs.entity.EntityTheWall;
import com.salvestrom.w2theJungle.worldGen.JungleSaveWorld;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;
import com.salvestrom.w2theJungle.worldGen.structures.blockPlacmentBridge;

//see jungleTown for notes on variable meaning etc

public class jungleTownAlt extends WorldGenerator
{
	public blockPlacmentBridge bridge = new blockPlacmentBridge();

	public jungleCityTemple temple = new jungleCityTemple();
	public jungleCityBeacon townbeacon = new jungleCityBeacon();

	public boolean generate(World world, Random rng, int x, int y, int z)
	{
		y -= 1;
		y += rng.nextInt(2);

		int mrl = rng.nextInt(5) + 7;
		mrl /= (int)2;
		int amrl = mrl*6;
		x = x - (mrl*3);

		Biome bio = world.getBiome(new BlockPos(x, y, z));
		
		if(this.temple.passAlong(world, rng, bio, x, y, z, amrl)) {
			
			//add dirt base
			
			this.addBase(world, rng, bio, x, y, z, mrl, amrl);
			
			int az = z + 10 - 3 - 4;
			for(int l = 0; l < mrl; ++l)
			{
				int height = world.getHeight(new BlockPos(x + 10 + 9 + (l*8), 0, az)).getY();
				this.townbeacon.generate(world, rng, x + 10 + 9 + (l*8), height, az);
			}

			az = z + 10 + 4;
			for(int l = 0; l < mrl; ++l)
			{
				int height = world.getHeight(new BlockPos(x + 10 + 9 + (l*8), 0, az)).getY();
				this.townbeacon.generate(world, rng, x + 10 + 9 + (l*8), height, az);
			}
			
			EntityTheWall thewall = new EntityTheWall(world);

			if(JungleSaveWorld.get(world).WallSpawnCount == 0 && (bio instanceof BiomeSwamp || bio == JungleBiomeRegistry.biomeJungleSwamp )) {
				
				JungleSaveWorld.get(world).wallSpawn(new int []{(int)(x+40 + 5), (int)y, (int)(z + 12)});
				
				thewall.setLocationAndAngles((double)x+amrl + 10.5D, (double)y+2D, (double)z + 10D, 0.0F, 0.0F);
				thewall.setWallSpawnType(1);
				world.spawnEntityInWorld(thewall);
				JungleSaveWorld.get(world).wallSpawnCount(1);
				
			} else {
				
				EntityStoneGolem stoneg = new EntityStoneGolem(world);

				stoneg.setLocationAndAngles((double)x+amrl + 10D, (double)y, (double)z + 10D, 0.0F, 0.0F);
				world.spawnEntityInWorld(stoneg);
			}
			
			return true;
			}
		else
		{
			return false;
			}
		}

	private void addBase(World world, Random rng, Biome bio, int x, int y, int z, int mrl, int amrl)
	{
		if(!(bio == JungleBiomeRegistry.biomeJungleMountain))
		{
		
		for(int bl = -amrl-10; bl < amrl+10; bl++)
			for (int bw = (int) (-amrl/1.19); bw < amrl/1.19; bw++)
				for (int by = -5; by < 0; by++) {
					
					int picked = (int) (((bw*bw)+1)/(Math.sqrt((bw*bw)+(bl*bl))*0.9)*(by*1.025));//.05
					int also = (int) (((bl*bl)+1)/(Math.sqrt((bw*bw)+(bl*bl))*0.9)*(by*0.152)); //.2
											
					int ra = (int) ((amrl*0.9));
					int a = (int) (amrl*(1.027-(mrl/550))); 
					int b = (int) (amrl/(1.6+(mrl/200)));
					int c = (int) Math.sqrt((a*a)-(b*b));
					
					if ((Math.sqrt((c-bl)*(c-bl)+(bw*bw))
							+Math.sqrt((c+bl)*(c+bl)+(bw*bw)))
							<=
							a+a
							-(picked*0.0135)
							-(also*0.0135)
							+(Math.sin((bl*0.08))*bw*0.1)
							+(Math.sin((bl*0.17))*bw*0.085)
							+(Math.sin(45+bl*0.29)*bw*0.065)
							
							+(Math.sin(55+bw*0.09)*bl*0.14)
							+(Math.sin(40+bw*0.17)*bl*0.1)
							
							-(Math.sin(30+(by*0.2))*by*0.5)
							) {
						
						Block chk = world.getBlockState(new BlockPos(bl+15+x+ra/2, by+y, bw+z+10)).getBlock();
						
						if(chk == Blocks.WATER || chk == Blocks.FLOWING_WATER
								|| chk instanceof BlockBush
								|| chk instanceof BlockGrass
								|| chk instanceof BlockLog
								|| chk instanceof BlockLeaves
								|| chk == Blocks.AIR)
						{
							bridge.setBlock(world, bl+15+x+ra/2, by+y, bw+z+10, Blocks.DIRT, 0, 2);
						}
					}
				}
		}
		else
		{
			for(int i = 0; i <= 20; ++i)
				for(int j = -1; j >= -5; --j)
					for(int k = 0; k <= 20; ++k)
						
					{
						bridge.setBlock(world, x+i, y+j, z+k, JungleBlocks.mossyStone, 0, 2);
					}

			
		}
		}
	
	public boolean generate(World wrld, Random rnd, BlockPos pos) {

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		return this.generate(wrld, rnd, x, y, z);
	}
				
}
