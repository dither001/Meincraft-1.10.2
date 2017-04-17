package com.salvestrom.w2theJungle.worldGen;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_CAVE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.CUSTOM;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.GLOWSTONE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.NETHER_LAVA;

import java.util.List;
import java.util.Random;

import com.salvestrom.w2theJungle.init.JungleBlocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class LostChunkProvider implements IChunkGenerator {
	
	private Random hellRNG;
    private NoiseGeneratorOctaves netherNoiseGen1;
    private NoiseGeneratorOctaves netherNoiseGen2;
    private NoiseGeneratorOctaves netherNoiseGen3;
    private NoiseGeneratorOctaves slowsandGravelNoiseGen;
    private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    private World worldObj;
    private double[] noiseField;

    //public MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();

    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] depthBuffer = new double[256];
    
    private MapGenBase netherCaveGenerator = new CavesLostWorld(); //duplicated hellcaves, changed netherrack to stone.
    private MapGenBase ravineGenerator = new RavineLostWorld();

    double[] noiseData1pnr;
    double[] noiseData2ar;
    double[] noiseData3br;
    double[] noiseData4;
    double[] noiseData5dr;
	private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

    public LostChunkProvider(World par1World, long par2, boolean features)
    {
        this.worldObj = par1World;
        this.hellRNG = new Random(par2);
        this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.hellRNG, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.hellRNG, 16);

        InitNoiseGensEvent.ContextHell noise = new InitNoiseGensEvent.ContextHell(netherNoiseGen1, netherNoiseGen2, netherNoiseGen3, slowsandGravelNoiseGen, netherrackExculsivityNoiseGen, scaleNoise, depthNoise);
        noise = TerrainGen.getModdedNoiseGenerators(par1World, this.hellRNG, noise);
        this.netherNoiseGen1 = noise.getLPerlin1();
        this.netherNoiseGen2 = noise.getLPerlin2();
        this.netherNoiseGen3 = noise.getPerlin();
        this.slowsandGravelNoiseGen = noise.getPerlin2();
        this.netherrackExculsivityNoiseGen = noise.getPerlin3();
        this.scaleNoise = noise.getScale();
        this.depthNoise = noise.getDepth();
        
        this.netherCaveGenerator = TerrainGen.getModdedMapGen(netherCaveGenerator, NETHER_CAVE);
        this.ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, RAVINE);
    }
    
	public void prepareHeights(int x, int z, ChunkPrimer primer)
    {
    	/* setting b2 was 17! Setting this to 34 added a final squashed "ground level",
    	 *  and left  the bedrock ceiling exposed.
    	 * 19 is lowest number while still exposing ceiling. quite laggy
    	 *  */
//        mH = ((((x*16*x*16) + (z*16*z*16))+1)/10000)*30;// if(mH > 2) {mH = 2;}

 //   	if((x*16 * x*16) + (z*16 *z*16) > 100*100) { // trying to make a hole in the map. failed. fatal crash
    	
        int i = 4;
        int j = 32; //main lake level. 
        int k = i + 1;
        int l = 17; //donot set below 17
        int i1 = i + 1;
        this.noiseField = this.getHeights(this.noiseField, x * i, 0, z * i, k, l, i1);

        for (int j1 = 0; j1 < i; ++j1)
        {
            for (int k1 = 0; k1 < i; ++k1)
            {
                for (int l1 = 0; l1 < 16; ++l1)
                {
                    double d0 = 0.125D;
                    double d1 = this.noiseField[((j1 + 0) * i1 + k1 + 0) * l + l1 + 0];
                    double d2 = this.noiseField[((j1 + 0) * i1 + k1 + 1) * l + l1 + 0];
                    double d3 = this.noiseField[((j1 + 1) * i1 + k1 + 0) * l + l1 + 0];
                    double d4 = this.noiseField[((j1 + 1) * i1 + k1 + 1) * l + l1 + 0];
                    double d5 = (this.noiseField[((j1 + 0) * i1 + k1 + 0) * l + l1 + 1] - d1) * d0;
                    double d6 = (this.noiseField[((j1 + 0) * i1 + k1 + 1) * l + l1 + 1] - d2) * d0;
                    double d7 = (this.noiseField[((j1 + 1) * i1 + k1 + 0) * l + l1 + 1] - d3) * d0;
                    double d8 = (this.noiseField[((j1 + 1) * i1 + k1 + 1) * l + l1 + 1] - d4) * d0;

                    for (int i2 = 0; i2 < 8; ++i2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int j2 = 0; j2 < 4; ++j2)
                        {
                            //int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | (k1 * 8 + l1);
//                            short short1 = 128;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 4; ++k2)
                            {
                                IBlockState block = null;

                                if (l1 * 8 + i2 < j) //this implies that k1*8+l1 is a height
                                {
                                    block = Blocks.WATER.getDefaultState();
                                }
                                
                                if (d15 > 0.0D)
                                {
                                    block = Blocks.STONE.getDefaultState();
                                }
                                
                                int newx = j2 + j1 * 4;
                                int newy = i2 + l1 * 8;
                                int newz = k2 + k1 * 4;
                                
                                primer.setBlockState(newx, newy, newz, block);
//                                j2 += short1;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }


	public void buildSurfaces(int x, int z, ChunkPrimer primer)
    {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.worldObj)) return;

        int seaLevel = 62;
        double d0 = 0.03125D;
        this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, x * 16, z * 16, 0, 16, 16, 1, d0, d0, 1.0D);
        this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, x * 16, 109, z * 16, 16, 1, 16, d0, 1.0D, d0);
        this.depthBuffer = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.depthBuffer, x * 16, z * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int xBlock = 0; xBlock < 16; ++xBlock) //this seems like a loop that checks each block in the chunk.
        {
            for (int zBlock = 0; zBlock < 16; ++zBlock)
            {
                boolean flag = this.slowsandNoise[xBlock + zBlock * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[xBlock + zBlock * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D;
                int i1 = (int)(this.depthBuffer[xBlock + zBlock * 16] / 3.0D + 3.0D + this.hellRNG.nextDouble() * 0.25D);
                int j1 = -1;
                IBlockState topBlock = Blocks.GRASS.getDefaultState();
                IBlockState fillerBlock = Blocks.DIRT.getDefaultState();

                for (int yLayer = 127; yLayer >= 0; --yLayer)
                {  	
                	if (yLayer < 127 - this.hellRNG.nextInt(5) && yLayer > 0 + this.hellRNG.nextInt(5))
                    {
                        IBlockState block2 = primer.getBlockState(xBlock, yLayer, zBlock);

                        if (block2 != null && block2.getMaterial() != Material.AIR)
                        {
                            if (block2.getBlock() == Blocks.STONE)
                            {
                                if (j1 == -1)
                                {
                                    if (i1 <= 0)
                                    {
                                        topBlock = Blocks.GRASS.getDefaultState();
                                        fillerBlock = Blocks.DIRT.getDefaultState();
                                    }
                                    else if (yLayer >= seaLevel - 4 && yLayer <= seaLevel + 1)
                                    {
                                        topBlock = Blocks.GRASS.getDefaultState();
                                        fillerBlock = Blocks.DIRT.getDefaultState();
                                        if (flag1)
                                        {
                                            topBlock = Blocks.GRAVEL.getDefaultState();
                                            fillerBlock = Blocks.STONE.getDefaultState();
                                        }

                                        if (flag)
                                        {
                                            topBlock = Blocks.MYCELIUM.getDefaultState();
                                            fillerBlock = Blocks.DIRT.getDefaultState();
                                        }
                                    }

                                    if (yLayer < seaLevel && (topBlock == null || topBlock.getMaterial() == Material.AIR))
                                    {
                                        topBlock = Blocks.WATER.getDefaultState();
                                    }

                                    j1 = i1;
                                    
                                    if (yLayer >= seaLevel - 1)
                                    {
                                        primer.setBlockState(xBlock, yLayer, zBlock, topBlock);
                                    }                                    
                                    else
                                    {
                                        primer.setBlockState(xBlock, yLayer, zBlock, fillerBlock);
                                    }
                                }
                                else if (j1 > 0)
                                {
                                    --j1;
                                    primer.setBlockState(xBlock, yLayer, zBlock, fillerBlock);
                                }
                            }
                        }
                        else
                        {
                            j1 = -1;
                        }
                    }
                    else                    
                    {
                      primer.setBlockState(xBlock, yLayer, zBlock, Blocks.BEDROCK.getDefaultState());
                    }
                     //if(yLayer >= 123){p_147418_3_[l1] = Blocks.air;}
                   }
                                
            }
        }

    }

	public Chunk provideChunk(int par1, int par2)
    {
        this.hellRNG.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        //Block[] ablock = new Block[32768];
        this.prepareHeights(par1, par2, chunkprimer);
        this.buildSurfaces(par1, par2, chunkprimer);
        this.netherCaveGenerator.generate(this.worldObj, par1, par2, chunkprimer);
        this.ravineGenerator.generate(this.worldObj, par1, par2, chunkprimer);

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, par1, par2);
        Biome[] abiome = this.worldObj.getBiomeProvider().getBiomes((Biome[])null, par1 * 16, par2 * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte)Biome.getIdForBiome(abiome[k]);
        }

        chunk.resetRelightChecks();
        return chunk;
    }

    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
    private double[] getHeights(double[] par1ArrayOfDouble, int modX, int modY, int modZ, int par5, int par6, int par7)
    {
        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }
    	
    	ChunkGeneratorEvent.InitNoiseField event = new ChunkGeneratorEvent.InitNoiseField(this, par1ArrayOfDouble, modX, modY, modZ, par5, par6, par7);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return event.getNoisefield();

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.noiseData4 = this.scaleNoise.generateNoiseOctaves(this.noiseData4, modX, modY, modZ, par5, 1, par7, 1.0D, 0.0D, 1.0D);
        this.noiseData5dr = this.depthNoise.generateNoiseOctaves(this.noiseData5dr, modX, modY, modZ, par5, 1, par7, 100.0D, 0.0D, 100.0D);
        this.noiseData1pnr = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1pnr, modX, modY, modZ, par5, par6, par7, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.noiseData2ar = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2ar, modX, modY, modZ, par5, par6, par7, d0, d1, d0);
        this.noiseData3br = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3br, modX, modY, modZ, par5, par6, par7, d0, d1, d0);
        int i = 0;
        double[] adouble1 = new double[par6];

        for (int j = 0; j < par6; ++j)
        {
            adouble1[j] = Math.cos((double)j * Math.PI * 6.0D / (double)par6) * 2.0D; //could hardly tell what affect this had!
            double d2 = (double)j;

            if (j > par6 / 2)
            {
                d2 = (double)(par6 - 1 - j);
            }

            if (d2 < 4.0D)
            {
                d2 = 4.0D - d2;
                adouble1[j] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (int l = 0; l < par5; ++l)
        {
            for (int i1 = 0; i1 < par7; ++i1)
            {
                double d3 = 0.0D; //(this.noiseData4[l1] + 256.0D) / 512.0D;

                for (int k = 0; k < par6; ++k)
                {
                    double d4 = 0.0D;
                    double d5 = adouble1[k];
                    double d6 = this.noiseData2ar[i] / 512.0D;
                    double d7 = this.noiseData3br[i] / 512.0D;
                    double d8 = (this.noiseData1pnr[i] / 10.0D + 1.0D) / 2.0D;

                    if (d8 < 0.0D)
                    {
                        d4 = d6;
                    }
                    else if (d8 > 1.0D)
                    {
                        d4 = d7;
                    }
                    else
                    {
                        d4 = d6 + (d7 - d6) * d8;
                    }

                    d4 = d4 - d5;

                    if (k > par6 - 4)
                    {
                        double d9 = (double)((float)(k - (par6 - 4)) / 3.0F);
                        d4 = d4 * (1.0D - d9) + -10.0D * d9;
                    }

                    if ((double)k < d3)
                    {
                        double d10 = (d3 - (double)k) / 4.0D;
                        d10 = MathHelper.clamp_double(d10, 0.0D, 1.0D);
                        d4 = d4 * (1.0D - d10) + -10.0D * d10;
                    }

                    par1ArrayOfDouble[i] = d4;
                    ++i;                
                
                
                /*
                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = this.noiseData5dr[l1] / 8000.0D;

                if (d5 < 0.0D)
                {
                    d5 = -d5;
                }

                d5 = d5 * 3.0D - 3.0D;

                if (d5 < 0.0D)
                {
                    d5 /= 2.0D;

                    if (d5 < -1.0D)
                    {
                        d5 = -1.0D;
                    }

                    d5 /= 1.4D;
                    d5 /= 2.0D;
                    d3 = 0.0D;
                }
                else
                {
                    if (d5 > 1.0D)
                    {
                        d5 = 1.0D;
                    }

                    d5 /= 6.0D;
                }

                d3 += 0.5D;
                d5 = d5 * (double)par6 / 16.0D;
                ++l1;
*/

                }
            }
        }

        return par1ArrayOfDouble;
    }

    @Override
    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, worldObj, hellRNG, x, z, false));

        int blockX = x * 16;
        int blockZ = z * 16;

        Biome biome = this.worldObj.getBiome(new BlockPos(blockX+16, 1, blockZ+16));

        int i1;
        int j1;
        int k1;
        int yLayer;

        boolean doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, CUSTOM);
        
        //for carving holes in the ceiling
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, CUSTOM);
           for(k1 = 0; k1 <=15; k1++){
           	for(i1=0; doGen && i1 <= 15; i1++){
           		for(yLayer=111; doGen && yLayer<=128; yLayer++){
           			if(this.worldObj.isAirBlock(new BlockPos(blockX+k1, yLayer-1, blockZ+i1))){
           				this.worldObj.setBlockState(new BlockPos(blockX+k1, yLayer, blockZ+i1), Blocks.AIR.getDefaultState());
           			}
           		}
           	}
           }
           
           
 /*          doGen = TerrainGen.populate(par1IChunkProvider, worldObj, hellRNG, x, z, false, CUSTOM);
           for (i1 = 0; doGen && i1 < 50; ++i1)
           {
               j1 = blockX + this.hellRNG.nextInt(16) + 8;
               yLayer = this.hellRNG.nextInt(100) + 30;
               k1 = blockZ + this.hellRNG.nextInt(16) + 8;
               
               if(hellRNG.nextInt(10) == 0) {
            	   new WorldGenBigTree(false).generate(worldObj, hellRNG, j1, yLayer, k1);
            	   } else if(hellRNG.nextInt(2) == 0) {
            		   new WorldGenShrub(3, 0).generate(worldObj, hellRNG, j1, yLayer, k1);
            		   } else if(hellRNG.nextInt(3) <= 1) {
            			   new WorldGenMegaJungle(false, 10, 20, 3, 3).generate(worldObj, hellRNG, j1, yLayer, k1);
            		   } else {
            			   new WorldGenTrees(false, 4 + hellRNG.nextInt(7), 3, 3, true).generate(worldObj, hellRNG, j1, yLayer, k1);
            		   }
        } */
        
        //next two added by me
        if (!(biome instanceof BiomeDesert) && this.hellRNG.nextInt(4) == 0
                && TerrainGen.populate(this, worldObj, hellRNG, x, z, false, LAKE))
            {
                k1 = blockX + this.hellRNG.nextInt(16) + 8;
                yLayer = this.hellRNG.nextInt(127); //was 255
                i1 = blockZ + this.hellRNG.nextInt(16) + 8;
                (new WorldGenLakes(Blocks.WATER)).generate(this.worldObj, this.hellRNG, new BlockPos(k1, yLayer, i1));
            }
        //short trees, taller, taller,tallest
        for (i1 = 0; doGen && i1 < 15; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(100) + 20;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenMegaJungle(false, 10, 15, JUNGLE_LOG, JUNGLE_LEAF)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, CUSTOM);
        for (i1 = 0; doGen && i1 < 15; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(100) + 20;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenMegaJungle(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAF)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, NETHER_LAVA);
        for (i1 = 0; doGen && i1 < 12; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(100) + 20;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenMegaJungle(false, 10, 25, JUNGLE_LOG, JUNGLE_LEAF)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, NETHER_LAVA);
        for (i1 = 0; doGen && i1 < 8; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(70) + 50;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenMegaJungle(false, 10, 30, JUNGLE_LOG, JUNGLE_LEAF)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        
        //ground cover
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, NETHER_LAVA);
        for (i1 = 0; doGen && i1 < 20; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(70) + 40;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenShrub(JUNGLE_LOG, OAK_LEAF)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, NETHER_LAVA);
        for (i1 = 0; doGen && i1 < 9; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(70) + 50;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenTallGrass(EnumType.FERN)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, NETHER_LAVA);
        for (i1 = 0; doGen && i1 < 9; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(70) + 50;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenTallGrass(EnumType.GRASS)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        //dead bush
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, NETHER_LAVA);
        for (i1 = 0; doGen && i1 < 2; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(30) + 30;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenTallGrass(EnumType.DEAD_BUSH)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, NETHER_LAVA);
        for (i1 = 0; doGen && i1 < 10; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(20) + 30;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenSwamp()).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }
        
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, LAVA);
        for (i1 = 0; doGen && i1 < 8; ++i1)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(120) + 4;
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
        //    (new WorldGenLiquids(Blocks.flowing_water)).generate(this.worldObj, this.hellRNG, j1, k1, yLayer);
        }

        int i2;
        i1 = this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1);
        //glowstone wont generate without being attached to netherrack
        doGen = TerrainGen.populate(this, worldObj, hellRNG, x, z, false, GLOWSTONE);
        for (j1 = 0; doGen && j1 < i1; ++j1)
        {
            k1 = blockX + this.hellRNG.nextInt(16) + 8;
            yLayer = this.hellRNG.nextInt(40) + 20;
            i2 = blockZ + this.hellRNG.nextInt(16) + 8;
//            (new WorldGenGlowStone1()).generate(this.worldObj, this.hellRNG, k1, yLayer, i2);
        }

        for (j1 = 0; doGen && j1 < 10; ++j1)
        {
            k1 = blockX + this.hellRNG.nextInt(16) + 8;
            yLayer = this.hellRNG.nextInt(128);
            i2 = blockZ + this.hellRNG.nextInt(16) + 8;
 //           (new WorldGenGlowStone2()).generate(this.worldObj, this.hellRNG, k1, yLayer, i2);
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldObj, hellRNG, new BlockPos(blockX, 0, blockZ)));

        doGen = TerrainGen.decorate(worldObj, hellRNG, new BlockPos(blockX, 0, blockZ), SHROOM);
        if (doGen && this.hellRNG.nextInt(1) == 0)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(125);
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenBush(Blocks.BROWN_MUSHROOM)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }

        if (doGen && this.hellRNG.nextInt(1) == 0)
        {
            j1 = blockX + this.hellRNG.nextInt(16) + 8;
            k1 = this.hellRNG.nextInt(128);
            yLayer = blockZ + this.hellRNG.nextInt(16) + 8;
            (new WorldGenBush(Blocks.RED_MUSHROOM)).generate(this.worldObj, this.hellRNG, new BlockPos(j1, k1, yLayer));
        }

        WorldGenMinable worldgenminable = new WorldGenMinable(JungleBlocks.oreSapphire.getDefaultState(), 13);
        int j2;

        doGen = TerrainGen.generateOre(worldObj, hellRNG, worldgenminable, new BlockPos(blockX, 0, blockZ), DIAMOND);
        for (k1 = 0; doGen && k1 < 16; ++k1)
        {
            yLayer = blockX + this.hellRNG.nextInt(16);
            i2 = this.hellRNG.nextInt(108) + 10;
            j2 = blockZ + this.hellRNG.nextInt(16);
            worldgenminable.generate(this.worldObj, this.hellRNG, new BlockPos(yLayer, i2, j2));
        }

        for (k1 = 0; k1 < 16; ++k1)
        {
            i1 = blockX + this.hellRNG.nextInt(16);
            i2 = this.hellRNG.nextInt(108) + 10;
           j2 = blockZ + this.hellRNG.nextInt(16);
        //    (new WorldGenLiquids(Blocks.flowing_water)).generate(this.worldObj, this.hellRNG, i1, i2, j2);
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj, hellRNG, new BlockPos(blockX, 0, blockZ)));
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(this, worldObj, hellRNG, x, z, false));

        BlockFalling.fallInstantly = false;
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, BlockPos pos)
    {
        if (par1EnumCreatureType == EnumCreatureType.MONSTER)
        {
             //if (this.genNetherBridge.hasStructureAt(par2, par3, par4))
            {
                //return this.genNetherBridge.getSpawnList();
            }

//            if (this.genNetherBridge.func_142038_b(par2, par3, par4) && this.worldObj.getBlock(par2, par3 - 1, par4) == Blocks.nether_brick)
            {
  //              return this.genNetherBridge.getSpawnList();
            }
        }

        Biome biomegenbase = this.worldObj.getBiome(pos);
        
        return biomegenbase.getSpawnableList(par1EnumCreatureType);
    }
    @Override
	public void recreateStructures(Chunk chunkIn, int x, int z)
    {
        //this.genNetherBridge.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
// inserting cave gen here seems to lead to blank areas on the map - narrow slices of missing trees and fortresses
        }

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName,
			BlockPos position) {
		return null;
	}



	
}