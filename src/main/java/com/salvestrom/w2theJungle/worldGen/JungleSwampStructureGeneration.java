package com.salvestrom.w2theJungle.worldGen;

import java.util.Random;

import com.salvestrom.w2theJungle.worldGen.structures.jungleFireWG;
import com.salvestrom.w2theJungle.worldGen.structures.jungleTown.jungleCityBeacon;
import com.salvestrom.w2theJungle.worldGen.structures.jungleTown.jungleTown;
import com.salvestrom.w2theJungle.worldGen.structures.jungleTown.jungleTownAlt;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JungleSwampStructureGeneration
{
	public JungleSwampStructureGeneration(World world, Random rnd, int x, int z)
	{
		
		int blockX = x + rnd.nextInt(8) + 4;
		int blockZ = z + rnd.nextInt(8) + 4;
		int y = world.getHeight(new BlockPos(blockX, 0, blockZ)).getY();
		
		//int[] a = {blockX, blockZ};
		
		if(y < 69 && rnd.nextInt(20) <= 10) //slightly over a half. 1/22.5 total. was 27 < 13
		{
			if(JungleSaveWorld.get(world).WallSpawnCount == 0) //alter to pass over chunks?
			{
				(new jungleTown()).generate(world, rnd, blockX, y, blockZ);
			}
			else
				if(rnd.nextInt(40) == 0)
			{
				(new jungleTownAlt()).generate(world, rnd, blockX, y, blockZ);
			}
		}
		else
			if(rnd.nextInt(5) < 3) //1/16
			{
				new jungleFireWG().generate(world, rnd, blockX, y, blockZ);
				}
			else if(rnd.nextInt(9) < 5)
			{
				new jungleCityBeacon().generate(world, rnd, blockX, y, blockZ);
				}
		}
	}
