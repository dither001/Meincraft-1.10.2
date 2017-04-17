package com.salvestrom.w2theJungle.worldGen.structures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class blockPlacmentBridge {
	//0 = face west. 1 = east. 2 = n. 3 = s
	public void setBlock(World world, int x, int y, int z, Block block)
	{
		BlockPos pos = new BlockPos(x, y, z);
		
		if(block instanceof BlockStairs)
		{
			world.setBlockState(pos, block.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
		}
		else
		{
			world.setBlockState(pos, block.getDefaultState());
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setBlock(World world, int x, int y, int z, Block block, int i1, int i2)
	{
		BlockPos pos = new BlockPos(x, y, z);
		//TODO split this into seperate methods according to block type so i can search it more easily.
/*		if(block instanceof BlockStairs)
		{
			this.handleStairs(world, pos, block, i1, i2);
		}
		else
			if(block instanceof BlockLog)
			{
				this.handleLogs(world, pos, block, i1, i2);
			}
			else
				if(block.getDefaultState() == Blocks.STONEBRICK && i1 == 1)
				{
					world.setBlockState(pos, Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
				}
				else
				{
					world.setBlockState(pos, block.getDefaultState());
				}
*/		
		world.setBlockState(pos, block.getStateFromMeta(i1));
		}

	private void handleStairs(World world, BlockPos pos, Block block, int i1, int i2)
	{
		if(i1%4 == 3)
		{
			world.setBlockState(pos, block.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
		}
		else
			if(i1%4 == 2)
			{
				world.setBlockState(pos, block.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			}
			else
				if(i1%4 == 1)
				{
					world.setBlockState(pos, block.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
				}
				else
					if(i1%4 == 0)
					{
						world.setBlockState(pos, block.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
					}
		}
	
	private void handleLogs(World world, BlockPos pos, Block block, int i1, int i2)
	{
		//oak, spruce, birch, jungle? 0 1 2 3, 4 5 6 7, 8 9 10 11,
		//3 is upright.
		//7 is n/s jungle log
		//11 is w/e jungle log.	
		
		IBlockState iblockstate = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.byMetadata(i1%4));
		
		if(i1 <= 3)
		{
			world.setBlockState(pos, iblockstate.withProperty(BlockOldLog.LOG_AXIS, EnumAxis.Y));
		}
		else
			if(i1 <= 7)
			{
				world.setBlockState(pos, iblockstate.withProperty(BlockOldLog.LOG_AXIS, EnumAxis.X));
			}
			else
				if(i1 <= 11)
				{
					world.setBlockState(pos, iblockstate.withProperty(BlockOldLog.LOG_AXIS, EnumAxis.Z));
				}
		}
}