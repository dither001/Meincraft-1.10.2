package com.salvestrom.w2theJungle.worldGen.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;


public class MangoTreeExperiment extends WorldGenAbstractTree
{
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	
    public MangoTreeExperiment(boolean p_i45461_1_)
    {
        super(p_i45461_1_);
    }

    public boolean generate(World world, Random rng, int x, int y, int z)
    {
        int treeHeight = rng.nextInt(3) + rng.nextInt(2) + 9;
        boolean canSpawn = true;
        //heightBoost, to get the trunk base off the ground.
        int hB = rng.nextInt(3)+2+4; //plus 10 is for testing, but look good 
        
        if (y >= 1 && y + treeHeight + 1 <= 256)
        {
            int xMod;
            int zMod;
            //for loop spawnability check
            for (int yMod = y; yMod <= y + 1 + treeHeight; ++yMod)
            {
                byte b0 = 1;

                if (yMod == y)
                {
                    b0 = 0;
                }

                if (yMod >= y + 1 + treeHeight - 2)
                {
                    b0 = 2;
                }

                for (xMod = x - b0; xMod <= x + b0 && canSpawn; ++xMod)
                {
                    for (zMod = z - b0; zMod <= z + b0 && canSpawn; ++zMod)
                    {
                        if (yMod >= 0 && yMod < 256)
                        {
                            BlockPos pos = new BlockPos(xMod, yMod, zMod);

                            if (!this.isReplaceable(world, pos))
                            {
                                canSpawn = false;
                            }
                        }
                        else
                        {
                            canSpawn = false;
                        }
                    }
                }
            }

            if (!canSpawn)
            {
                return false;
            }
            else
            {	//growth and trunk generation
                BlockPos pos = new BlockPos(x, y, z);
            	Block block1 = world.getBlockState(pos.down()).getBlock();
                IBlockState state = world.getBlockState(pos);

                boolean isSoil = block1.canSustainPlant(state, world, pos.down(), EnumFacing.UP, (BlockSapling)Blocks.SAPLING);
                if (((isSoil && y < 256 - treeHeight - 1) && rng.nextInt(10)==0) || (block1 == Blocks.WATER && y < 256 - treeHeight - 1))
                {
                    this.onPlantGrow(world, x,     y - 1, z,     x, y, z);
                    this.onPlantGrow(world, x + 1, y - 1, z,     x, y, z);
                    this.onPlantGrow(world, x + 1, y - 1, z + 1, x, y, z);
                    this.onPlantGrow(world, x,     y - 1, z + 1, x, y, z);
                    EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rng);
                    xMod = y+hB + rng.nextInt(3) + 4;
                    zMod = 2 - rng.nextInt(3);
                    int newX = x;
                    int newZ = z;
                    int i2 = 0;
                    int heightCount;
                    int treeSlice; // = y + hB + heightCount;

                    for (heightCount = 0; heightCount < treeHeight; ++heightCount)
                    {
                        treeSlice = y + hB + heightCount;
                        //off setting upper part.
                        if (treeSlice >= xMod && zMod > 0 && rng.nextInt(2)==0)
                        {
                            newX += enumfacing.getFrontOffsetX(); //the array is 4 long. j3 selects a random one. from 0's, -1 and +1
                            newZ += enumfacing.getFrontOffsetZ();
                            --zMod;
                        }

                        BlockPos pos2 = new BlockPos(newX, treeSlice, newZ);
                        IBlockState state2 = world.getBlockState(pos);
                        Block block2 = state2.getBlock();

                        if (block2.isAir(state2, world, pos2) || block2.isLeaves(state2, world, pos2))
                        {
                       	
                            this.setBlockAndNotifyAdequately(world, pos2, JUNGLE_LOG);
                            this.setBlockAndNotifyAdequately(world, pos2.east(), JUNGLE_LOG);
                            this.setBlockAndNotifyAdequately(world, pos2.south(), JUNGLE_LOG);
                            this.setBlockAndNotifyAdequately(world, pos2.east().south(), JUNGLE_LOG);
                            i2 = treeSlice;
                        }
                    }
                    //leaf application.	hijacked variables
                    for (heightCount = -2; heightCount <= 0; ++heightCount) //x width
                    {
                        for (treeSlice = -3; treeSlice <= 0; ++treeSlice) //z width
                        {
                        	//lowest layer
                            if ((heightCount != -2 || treeSlice != 0))
                            {	
                            byte b1 = -1;
                            this.leafPlacement(world, newX + heightCount, i2 + b1, newZ + treeSlice);
                            this.leafPlacement(world, 1 + newX - heightCount, i2 + b1, newZ + treeSlice);
                            this.leafPlacement(world, newX + heightCount, i2 + b1, 1 + newZ - treeSlice);
                            this.leafPlacement(world, 1 + newX - heightCount, i2 + b1, 1 + newZ - treeSlice);
                            }

                            if ((heightCount > -2 || treeSlice > -3) && (heightCount != -2 || treeSlice != -3))
                            {
                            	//upperlayer
                                byte b2 = 1;
                                this.leafPlacement(world, newX + heightCount, i2 + b2, newZ + treeSlice);
                                this.leafPlacement(world, 1 + newX - heightCount, i2 + b2, newZ + treeSlice);
                                this.leafPlacement(world, newX + heightCount, i2 + b2, 1 + newZ - treeSlice);
                                this.leafPlacement(world, 1 + newX - heightCount, i2 + b2, 1 + newZ - treeSlice);
                            }
                        }
                    }

                    //highest layer, random chance to add final tuft
                    if (rng.nextBoolean())
                    {
                        this.leafPlacement(world, newX, i2 + 2, newZ);
                        this.leafPlacement(world, newX + 1, i2 + 2, newZ);
                        this.leafPlacement(world, newX + 1, i2 + 2, newZ + 1);
                        this.leafPlacement(world, newX, i2 + 2, newZ + 1);
                    }

                    //middle layer
                    int a = 3;
                    int b = 4;
                    int c = 5;
                    for (heightCount = -a; heightCount <= b; ++heightCount)
                    {
                        for (treeSlice = -b; treeSlice <= c; ++treeSlice)
                        {
                            if ((heightCount != -a || treeSlice != -b)
                            		&& (heightCount != -a || treeSlice != c)
                            		&& (heightCount != b || treeSlice != -b)
                            		&& (heightCount != b || treeSlice != c)
                            		&& (Math.abs(heightCount) < b || Math.abs(treeSlice) < c)
                            		)
                            {
                                this.leafPlacement(world, newX + heightCount, i2, newZ + treeSlice);
                            }
                        }
                    }
                    /*this is the root code, adapted from the branch code for canopy trees
                     * something is causing multiple roots to randomly appear away from the trunk.
                     * creating a seperate variable for the offset cured this, but left the roots shifting all over from 1
                     * log to the next.
                     */
                    {
                    int enough = 0;
                    while(enough <= 3) //this ensures the whole loop will start again if it fails to gen five legs
                    	
                    	for (int treeLegX = -1; treeLegX <= 2; treeLegX++) {
                    		for (int treeLegZ = -1; treeLegZ <= 2; treeLegZ++) {
                    			if(enough <= rng.nextInt(2)+3)// + rng.nextInt(2)) // this interrupts the loop as soon as enough reaches 4 to 5.
                    		
                        	//ignores the area directly under trunk, while giving each leg a 1-3 chance to spawn. 
                            if ((treeLegX < 0 || treeLegX > 1 || treeLegZ < 0 || treeLegZ > 1) && rng.nextInt(5) == 0)
                            {                    			

                            	int legLength = rng.nextInt(3) + 17; //randomised leg length. increase to match new height and ensure legs still hit lake bed.
                            	int legOffset = y+hB+rng.nextInt(2); //treeHeight-rng.nextInt(2)-1;

                                //arraySelect = rng.nextInt(4);
                                zMod = 3 - rng.nextInt(2); //2

                            	int offsetX = 0; int offsetZ = 0;
                                
                                for (int l2 = 0; l2 < legLength; ++l2)
                                {	
                                	// whys this blanked?? this.setBlockAndNotifyAdequately(world, x + treeLegX, legOffset - l2, z + treeLegZ, Blocks.log, 3);
                                	
                                	if(zMod >= 0 && rng.nextInt(2)==0 && l2 > 2+rng.nextInt(2)) //y+hB-3 > y+hB-l2-rng.nextInt(2) && <- this was supposed  to stop the issue but didnt work. 
                                    {
                                		offsetX += enumfacing.getFrontOffsetX();
                                		offsetZ += enumfacing.getFrontOffsetX();
                                        --zMod;
                                    }

                                	this.setBlockAndNotifyAdequately(world, new BlockPos(x + treeLegX+offsetX, legOffset - l2, z + treeLegZ+offsetZ), JUNGLE_LOG);
                                	
                                }
                            
                                ++enough;
                    
                                int ytree;
                                int xtree;
                                int ztree;
                                
                                for (ytree = y + hB; ytree <= y + hB + treeHeight; ++ytree)
                                {
                                    int j1 = ytree - (y + hB + treeHeight);
                                    int k1 = 2 - j1 / 2;

                                    for (xtree = x - k1; xtree <= x + k1; ++xtree)
                                    {
                                        for (ztree = z - k1; ztree <= z + k1; ++ztree)
                                        {
                                        	BlockPos pos3 = new BlockPos(xtree, ytree, ztree);
                                        	IBlockState state3 = world.getBlockState(pos3).getBlock().getDefaultState();
                                        	
                                            if (world.getBlockState(pos3).getBlock().isLeaves(state3, world, pos3))
                                            {
                                                if (rng.nextInt(5) == 0 && world.getBlockState(pos3.west()).getBlock() == Blocks.AIR)
                                                {
                                                    this.generateVines(world, xtree - 1, ytree, ztree, BlockVine.EAST);
                                                }

                                                if (rng.nextInt(5) == 0 && world.getBlockState(pos3.east()).getBlock() == Blocks.AIR)
                                                {
                                                    this.generateVines(world, xtree + 1, ytree, ztree, BlockVine.WEST);
                                                }

                                                if (rng.nextInt(5) == 0 && world.getBlockState(pos3.north()).getBlock() == Blocks.AIR)
                                                {
                                                    this.generateVines(world, xtree, ytree, ztree - 1, BlockVine.SOUTH);
                                                }

                                                if (rng.nextInt(5) == 0 && world.getBlockState(pos3.south()).getBlock() == Blocks.AIR)
                                                {
                                                    this.generateVines(world, xtree, ytree, ztree + 1, BlockVine.NORTH);
                                                }
                                            }
                                        }
                                    }
                                }
                                
                                
                            }
                                //end root log placement
                                
                              /*  int i3;

                                for (l2 = -1; l2 <= 1; ++l2)
                                {
                                    for (i3 = -1; i3 <= 1; ++i3)
                                    {
                                        this.func_150526_a(world, newX + treeLegX + l2, i2 - 0, newZ + treeLegZ + i3); //leaf gen for branches.tr
                                    }
                                }

                                for (l2 = -2; l2 <= 2; ++l2)
                                {
                                    for (i3 = -2; i3 <= 2; ++i3)
                                    {
                                        if (Math.abs(l2) != 2 || Math.abs(i3) != 2)
                                        {
                                            this.func_150526_a(world, newX + treeLegX + l2, i2 - 1, newZ + treeLegZ + i3);
                                        }
                                    }
                                } */
                            }
                        }
                    }
                    
                    
                    //put last touches here
                    

                    return true;
                }
                else
                {
                    return false;
                }
            } //growth/generation for loop ends here
        }
        else
        {
            return false;
        }
    }

    private void leafPlacement(World world, int x, int y, int z)
    {
    	BlockPos pos = new BlockPos(x, y, z);
    	IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block.isAir(state, world, pos))
        {
            this.setBlockAndNotifyAdequately(world, pos, JUNGLE_LEAF);
        }
    }

    //Just a helper macro
    private void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
    	BlockPos pos = new BlockPos(x, y, z);
    	IBlockState state = world.getBlockState(pos);
        state.getBlock().onPlantGrow(state, world, pos, new BlockPos(sourceX, sourceY, sourceZ));
    }
    
    private void generateVines(World world, int x, int y, int z, PropertyBool prop)
    {
    	BlockPos pos = new BlockPos(x, y, z);
    	IBlockState state = world.getBlockState(pos);
    	
        this.setBlockAndNotifyAdequately(world, pos, Blocks.VINE.getDefaultState().withProperty(prop, Boolean.valueOf(true)));
        int i1 = 4;

        while (true)
        {
            --y;

            if (!(state.getBlock() == Blocks.AIR) || i1 <= 0)
            {
                return;
            }

            this.setBlockAndNotifyAdequately(world, pos, Blocks.VINE.getDefaultState().withProperty(prop, Boolean.valueOf(true)));
            --i1;
        }
    }

	@Override
	public boolean generate(World world, Random rng, BlockPos pos) {
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		return this.generate(world, rng, x, y, z);
	}
}