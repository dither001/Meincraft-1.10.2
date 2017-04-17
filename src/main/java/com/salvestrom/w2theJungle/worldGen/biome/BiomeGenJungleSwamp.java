package com.salvestrom.w2theJungle.worldGen.biome;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanScuffler;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanStalker;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanWitchDoctor;
import com.salvestrom.w2theJungle.mobs.entity.EntityMistSpider;
import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;
import com.salvestrom.w2theJungle.mobs.entity.EntitySwampCrab;

public class BiomeGenJungleSwamp extends BiomeJungle {
	
	private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
	private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

	
	private boolean saywhat;

	public BiomeGenJungleSwamp(int p_i45379_1_, boolean p_i45379_2_, BiomeProperties biomeProperties) {
		super(false, biomeProperties);
		
		this.saywhat = p_i45379_2_;

		this.theBiomeDecorator.treesPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 12;
        this.theBiomeDecorator.waterlilyPerChunk = 4;

		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanWitchDoctor.class, 7, 1, 2));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanScuffler.class, 7, 3, 5));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanStalker.class, 7, 3, 5));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityStoneGolem.class, 1, 0, 1));
		
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMistSpider.class, 25, 2, 3));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySwampCrab.class, 31, 1, 2));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float f) {
		return 300000;
	}

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
    }
  
    public void decorate(World world, Random rng, BlockPos pos) {

    	int overall = rng.nextInt(30); 
		
        int x = pos.getX() + rng.nextInt(8) + 4; //8
        int z = pos.getZ() + rng.nextInt(8) + 4; //8
        int y = world.getHeight(new BlockPos(x, 1, z)).getY();
        
        BlockPos pos2 = new BlockPos(x, y, z);

 //       for(int limit = 0; limit < 6; limit++)
		{
	//		int yLayer = rng.nextInt(127)+53;
//			(new WorldGenLakes(Blocks.water)).generate(world, rng, x, yLayer, z);
		}
        
        
        if(overall > 10) {
            for(int limit = 0; limit < 6; limit++) {
            	x = pos.getX() + rng.nextInt(16);
            	z = pos.getZ() + rng.nextInt(16);
            	y = world.getHeight(new BlockPos(x, 0, z)).getY();
        	//int yLayer = rng.nextInt(127)+53;
            	(new WorldGenLakes(Blocks.WATER)).generate(world, rng, new BlockPos(x, y, z));
            }
        } else if(overall >= 9 && overall <= 10) {
        	(new WorldGenBlockBlob(JungleBlocks.mossyStone, rng.nextInt(4)+1)).generate(world, rng, pos2);
        } else if(overall < 4) {
            (new WorldGenBigMushroom()).generate(world, rng, pos2);
        } else if(overall == 5) {
        	new MangoTreeExperiment(false);
        } else if(overall == 8 || overall == 7) {
        	(new WorldGenLakes(JungleBlocks.mudBlock)).generate(world, rng, pos2); 
        } else {
            WorldGenAbstractTree worldgenabstracttree = this.genBigTreeChance(rng);
            //worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

            if (worldgenabstracttree.generate(world, rng, pos2))
            {
               // worldgenabstracttree.func_150524_b(world, rng, pos2);
            }
        }
        for(int lily = 0; lily < 5; ++lily)
        {
        	x = x + rng.nextInt(16);
        	z = z + rng.nextInt(16);
            y = world.getHeight(new BlockPos(x, 0, z)).getY();
            new WorldGenWaterlily().generate(world, rng, new BlockPos(x, y, z));
        }
        
        super.decorate(world, rng, pos);
        
    }
    
    public WorldGenAbstractTree genBigTreeChance(Random rng)
    {
        return (WorldGenAbstractTree)(rng.nextInt(20) == 0 ? new WorldGenMegaJungle(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAF) : (rng.nextInt(3) == 0 ? new WorldGenShrub(JUNGLE_LOG, OAK_LEAF) : (!this.saywhat && rng.nextInt(4) > 1 ? new MangoTreeExperiment(false) : new WorldGenSwamp())));
    }



}
