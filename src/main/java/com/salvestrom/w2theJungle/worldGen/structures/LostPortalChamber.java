package com.salvestrom.w2theJungle.worldGen.structures;

import java.util.Random;

import com.salvestrom.w2theJungle.init.JungleBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LostPortalChamber extends WorldGenerator {
	
	public blockPlacmentBridge bridge = new blockPlacmentBridge();

	public fillWithBlocks fill = new fillWithBlocks();
	public String rota; // = "e";
	
	public void createPortalChamber(Entity e, WorldServer wsi, Random rng) {
		
		int x = (int) e.posX-3;
		int y = (int) e.posY-2;
		int z = (int) e.posZ-3;
		
		this.generate(wsi, rng, x, y, z);
	}
	
	//compiled game has the stonebrick all constructed facing north, vs south, when entering the  norther temple...
	//they all use AndMeta...

	public boolean generate(World world, Random r, int x, int y, int z) {
		
		if(x < -500) {//x < 0 && z < 0 && x < z 
			this.rota = "e";
			} else
				if(x > 500) {//x > 0
					this.rota = "w";
					} else
						if(z < -500) { //x < 0 && z < 0 && z < x
							this.rota = "s";
							} else
								if(z > 500) {
									this.rota = "n";
									} else {
										this.rota = "e";
									}

		fill.fillWithRotation(world, x, y, z, 0, 8, 1, 6, 0, 6, Blocks.AIR, rota);

		//base
		fill.fillWithRotationAndMeta(world, x, y, z, 0, 8, 0, 0, 1, 1, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotationAndMeta(world, x, y, z, 0, 8, 0, 0, 5, 5, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotationAndMeta(world, x, y, z, 0, 0, 0, 0, 2, 4, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotationAndMeta(world, x, y, z, 0, 8, 0, 0, 2, 4, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotation(world, x, y, z, 1, 7, 0, 0, 2, 4, Blocks.MOSSY_COBBLESTONE, rota);
		fill.fillWithRotation(world, x, y, z, 2, 6, 0, 0, 3, 3, JungleBlocks.mossyCarved, rota);
		fill.fillWithRotationAndMeta(world, x, y, z, 3, 5, 0, 0, 3, 3, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotation(world, x, y, z, 4, 4, 0, 0, 3, 3, Blocks.GLOWSTONE, rota);

		
		//ceiling
		fill.fillWithRotationAndMeta(world, x, y, z, 0, 8, 7, 7, 1, 1, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotationAndMeta(world, x, y, z, 0, 8, 7, 7, 5, 5, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotationAndMeta(world, x, y, z, 0, 0, 7, 7, 2, 4, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotationAndMeta(world, x, y, z, 0, 8, 7, 7, 2, 4, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotation(world, x, y, z, 1, 7, 7, 7, 2, 4, Blocks.MOSSY_COBBLESTONE, rota);
		fill.fillWithRotation(world, x, y, z, 2, 6, 7, 7, 3, 3, JungleBlocks.mossyCarved, rota);
		fill.fillWithRotationAndMeta(world, x, y, z, 3, 5, 7, 7, 3, 3, Blocks.STONEBRICK,  1, 2, rota);
		fill.fillWithRotation(world, x, y, z, 4, 4, 7, 7, 3, 3, Blocks.GLOWSTONE, rota);
		
		fill.fillWithRotation(world, x, y, z, 0, 8, 6, 6, 0, 0, Blocks.MOSSY_COBBLESTONE, rota);
		fill.fillWithRotation(world, x, y, z, 0, 8, 6, 6, 6, 6, Blocks.MOSSY_COBBLESTONE, rota);

		fill.fillWithRotation(world, x, y, z, 0, 8, 1, 1, 0, 0, Blocks.MOSSY_COBBLESTONE, rota);
		fill.fillWithRotation(world, x, y, z, 0, 8, 1, 1, 6, 6, Blocks.MOSSY_COBBLESTONE, rota);
		
		//pillars
		for(int c = 0; c <= 8; ++c) {
			if(c % 2 == 0) {

				fill.fillWithRotation(world, x, y, z, c, c, 1, 1, 1, 1, Blocks.MOSSY_COBBLESTONE, rota);
				fill.fillWithRotation(world, x, y, z, c, c, 1, 1, 5, 5, Blocks.MOSSY_COBBLESTONE, rota);
				fill.fillWithRotation(world, x, y, z, c, c, 6, 6, 1, 1, Blocks.MOSSY_COBBLESTONE, rota);
				fill.fillWithRotation(world, x, y, z, c, c, 6, 6, 5, 5, Blocks.MOSSY_COBBLESTONE, rota);

				fill.fillWithRotationAndMeta(world, x, y, z, c, c, 2, 3, 0, 0, Blocks.STONEBRICK, 1, 2, rota);
				fill.fillWithRotationAndMeta(world, x, y, z, c, c, 2, 3, 6, 6, Blocks.STONEBRICK, 1, 2, rota);
				fill.fillWithRotationAndMeta(world, x, y, z, c, c, 5, 5, 0, 0, Blocks.STONEBRICK, 1, 2, rota);
				fill.fillWithRotationAndMeta(world, x, y, z, c, c, 5, 5, 6, 6, Blocks.STONEBRICK, 1, 2, rota);
			}

			fill.fillWithRotation(world, x, y, z, 0, 8, 4, 4, 0, 0, Blocks.MOSSY_COBBLESTONE, rota);
			fill.fillWithRotation(world, x, y, z, 0, 8, 4, 4, 6, 6, Blocks.MOSSY_COBBLESTONE, rota);
			
			//backing#
			fill.fillWithRotation(world, x, y, z, -2, -2, 1, 1, 2, 4, Blocks.MOSSY_COBBLESTONE, rota);
			fill.fillWithRotation(world, x, y, z, -2, -2, 6, 6, 2, 4, Blocks.MOSSY_COBBLESTONE, rota);
			fill.fillWithRotation(world, x, y, z, -2, -2, 4, 4, 1, 5, Blocks.MOSSY_COBBLESTONE, rota);

			fill.fillWithRotationAndMeta(world, x, y, z, -2, -2, 2, 3, 1, 5, Blocks.STONEBRICK,  1, 2, rota);
			fill.fillWithRotationAndMeta(world, x, y, z, -2, -2, 5, 5, 1, 5, Blocks.STONEBRICK,  1, 2, rota);

			fill.fillWithRotation(world, x, y, z, -1, -1, 1, 1, 1, 5, Blocks.MOSSY_COBBLESTONE, rota);
			fill.fillWithRotation(world, x, y, z, -1, -1, 6, 6, 1, 5, Blocks.MOSSY_COBBLESTONE, rota);
			fill.fillWithRotation(world, x, y, z, -1, -1, 4, 4, 0, 6, Blocks.MOSSY_COBBLESTONE, rota);

			fill.fillWithRotationAndMeta(world, x, y, z, -1, -1, 2, 3, 0, 6, Blocks.STONEBRICK,  1, 2, rota);
			fill.fillWithRotationAndMeta(world, x, y, z, -1, -1, 5, 5, 0, 6, Blocks.STONEBRICK,  1, 2, rota);

			fill.fillWithRotationAndMeta(world, x, y, z, -1, -1, 0, 0, 2, 4, Blocks.STONEBRICK,  1, 2, rota);
			fill.fillWithRotationAndMeta(world, x, y, z, -1, -1, 7, 7, 2, 4, Blocks.STONEBRICK,  1, 2, rota);

			
			//portal
			fill.fillWithRotation(world, x, y, z, -1, -1, 1, 1, 2, 4, JungleBlocks.infusedObsidianBlock, rota);
			fill.fillWithRotation(world, x, y, z, -1, -1, 6, 6, 2, 4, JungleBlocks.infusedObsidianBlock, rota);
			fill.fillWithRotation(world, x, y, z, -1, -1, 2, 5, 1, 1, JungleBlocks.infusedObsidianBlock, rota);
			fill.fillWithRotation(world, x, y, z, -1, -1, 2, 5, 5, 5, JungleBlocks.infusedObsidianBlock, rota);
			
			fill.fillWithRotation(world, x, y, z, -1, -1, 2, 5, 2, 4, JungleBlocks.lostWorldPortal, rota);
					}

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
