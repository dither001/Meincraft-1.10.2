package com.salvestrom.w2theJungle.worldGen.biome;

import java.util.Random;

import net.minecraft.block.BlockTallGrass.EnumType;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import com.salvestrom.w2theJungle.mobs.entity.EntityGorrbat;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanScuffler;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanStalker;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanWitchDoctor;
import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;

public class BiomeGenJungleM extends BiomeJungle {
	
	public BiomeGenJungleM(int i, boolean par2, Biome.BiomeProperties properties) {
		super(false, properties);
	
		/*plateau = 1.5 0.025. mutate = +0.1 +0.2. midhills 1.0, 0.5
		 * midhills extending jungle was really good.
		 * midhills extending hills "ok". some nice bald mountains. same for ext.hills + mutation added to root height/var of plateau
		 * ext.jungle, plateau height plus mutation figures, kinda meh, just like the normal plateaus
		 * ext.jungle, midHills+ mutation hardcoded. - its actually a bit too much. kek.
		 * should be doable to hijack stone spawning into here. also, emerald will currently spawn in this version
		*/
		
        if (par2)
        {
            this.theBiomeDecorator.treesPerChunk = 2;
        }
        else
        {
            this.theBiomeDecorator.treesPerChunk = 50;
        }

        this.theBiomeDecorator.grassPerChunk = 25;
        this.theBiomeDecorator.flowersPerChunk = 4;

        if (!par2)
        {
            this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        }

        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanWitchDoctor.class, 7, 1, 2));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanScuffler.class, 7, 3, 5));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanStalker.class, 7, 3, 5));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityStoneGolem.class, 1, 0, 1));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGorrbat.class, 10, 3, 7));
    }
	
    public static int getIdForBiome(Biome biome)
    {
        return REGISTRY.getIDForObject(biome);
    }

	/*
	@Override
    public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? this.BIG_TREE_FEATURE : (p_150567_1_.nextInt(2) == 0 ? new WorldGenShrub(Blocks.) : (!this.field_150614_aC && p_150567_1_.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, 3, 3) : new WorldGenTrees(false, 4 + p_150567_1_.nextInt(7), 3, 3, true))));
    }
    */

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(EnumType.FERN) : new WorldGenTallGrass(EnumType.GRASS);
    }

    @Override
    public void genTerrainBlocks(World p_150560_1_, Random p_150560_2_, ChunkPrimer chunkPrimerIn, int p_150560_5_, int p_150560_6_, double p_150560_7_)
    {
    	this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        if (p_150560_7_ > 3D) // 3 seems to cut out almost all bare stone (this number seems to have no effect without reload.
        {
        	this.topBlock = Blocks.STONE.getDefaultState();
        	this.fillerBlock = Blocks.STONE.getDefaultState();
        	}
        
        this.generateBiomeTerrain(p_150560_1_, p_150560_2_, chunkPrimerIn, p_150560_5_, p_150560_6_, p_150560_7_);
        
    }
    
    @Override
    public void decorate(World par1World, Random par2Random, BlockPos pos)
    {
        super.decorate(par1World, par2Random, pos);
        
        int k = pos.getX() + par2Random.nextInt(16) + 8;
        int l = pos.getZ() + par2Random.nextInt(16) + 8;

        BlockPos pos2 = new BlockPos(k, 0, l);
        
        int i1 = par2Random.nextInt(par1World.getHeight(pos2).getY() * 2);
        
        new WorldGenMelon().generate(par1World, par2Random, pos.add(0, i1, 0));

        WorldGenVines worldgenvines = new WorldGenVines();

        for (l = 0; l < 50; ++l)
        {
            i1 = pos.getX() + par2Random.nextInt(16) + 8;
            short short1 = 128;
            int j1 = pos.getZ() + par2Random.nextInt(16) + 8;
        
            pos2 = new BlockPos(i1, short1, j1);
            
            worldgenvines.generate(par1World, par2Random, pos2);
        }
    }
}
