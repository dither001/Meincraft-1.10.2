package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;

public class lostWorldPortal extends BlockPortal {
	
	public lostWorldPortal(int i) {
		super();

		this.setUnlocalizedName("lostWorldPortal");
		this.setCreativeTab(w2theJungle.JungleModTab);
        this.setTickRandomly(true);
		this.setBlockUnbreakable();

	}
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if(!entity.isRiding() && !entity.isBeingRidden() && !world.isRemote && (entity instanceof EntityPlayerMP))
		{
			EntityPlayerMP player = (EntityPlayerMP)entity;
			FMLCommonHandler.instance().getMinecraftServerInstance();
			MinecraftServer server = player.getServer();

            player.setPortal(pos);
			
			if(player.timeUntilPortal > 0)
			{
				player.timeUntilPortal = 10; 
			}
			else if(player.dimension != w2theJungle.dimensionIdLost)
			{
				player.timeUntilPortal = 10;
				player.getServer().getPlayerList().transferPlayerToDimension(player, w2theJungle.dimensionIdLost, new LostTeleporter(server.worldServerForDimension(w2theJungle.dimensionIdLost)));
			}
			else
			{
				player.timeUntilPortal = 10;
				player.getServer().getPlayerList().transferPlayerToDimension(player, 0, new LostTeleporter(server.worldServerForDimension(0)));
			}
		}
		
	}
	// prev. tryToCreatePortal(activate it)
	@Override
    public boolean trySpawnPortal(World world, BlockPos pos)
	{
		byte b = 0;
		byte b1 = 0;
		Block isGood = JungleBlocks.infusedObsidianBlock;
		
		int x = pos.getX(); int z = pos.getZ();
		
		if(world.getBlockState(pos.west()).getBlock() == isGood || world.getBlockState(pos.east()).getBlock() == isGood){b = 1;}
		
		if(world.getBlockState(pos.north()).getBlock() == isGood || world.getBlockState(pos.south()).getBlock() == isGood){b1 = 1;}
		
		if(b == b1){return false;}
		
		else {
			if (world.isAirBlock(pos.add(-b, 0, -b1))) {
				x-=b;
				z-=b1;
				
			}

			BlockPos pos2 = new BlockPos(x, pos.getY(), z);
			
			for(int i = -1; i <= 2; i++)
			{
				for(int j = -1; j <= 3; j++)
				{
					boolean flag = (i == -1 || i == 2 || j == -1 || j == 3);
					
					if(i != -1 && i != 2 || j != -1 && j != 3)
					{
						Block check = world.getBlockState(pos2.add((b*i), j, (b1*i))).getBlock();
						boolean isAirBlock = world.isAirBlock(new BlockPos(x + b*i, j, z+ b1*i));
						
						if (flag)
						{
							if(check != isGood)
							{
								return false;
								}
							}
						else
							if(!isAirBlock && check != Blocks.FIRE)
							{//TODO create fire!
								return false;
							}
					
				}
			}
		}
			
			for(int l = 0; l<2; l++) {
				for(int l2 = 0; l2<3; l2++) {
					world.setBlockState(pos2.add(b*l, l2, b1*l), JungleBlocks.lostWorldPortal.getDefaultState());
				}
			}
			
			return true;
		}
	}
	
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)
        		&& worldIn.getBlockState(pos.down()).getBlock() == this
        		|| worldIn.getBlockState(pos.down()).getBlock() == JungleBlocks.infusedObsidianBlock;
    }
	
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	BlockPos posc = pos.down();
		IBlockState bstate = worldIn.getBlockState(posc);
		Block b = bstate.getBlock();
		
		if(b != JungleBlocks.infusedObsidianBlock && b != JungleBlocks.lostWorldPortal)
		{
			meta = 0;
			worldIn.setBlockToAir(pos);
		}
		else
		{
			meta = b.getMetaFromState(bstate);
			
			posc = pos.west();
			bstate = worldIn.getBlockState(posc);
			b = bstate.getBlock();
			
			if(b == JungleBlocks.lostWorldPortal || b == JungleBlocks.infusedObsidianBlock)
			{
		        worldIn.setBlockState(pos, this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X));
		        meta = 1;
			}
			else
			{
				posc = pos.east();
				bstate = worldIn.getBlockState(posc);
				b = bstate.getBlock();
				
				if(b == this || b == JungleBlocks.infusedObsidianBlock)
				{
					meta = 1;
			        worldIn.setBlockState(pos, this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X));
				}
				
				else
				{
					posc = pos.north();
					bstate = worldIn.getBlockState(posc);
					b = bstate.getBlock();
					
					if(b == JungleBlocks.lostWorldPortal || b == JungleBlocks.infusedObsidianBlock)
					{
				        worldIn.setBlockState(pos, JungleBlocks.lostWorldPortal.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z));
				        meta = 2;
					}
					else
					{
						posc = pos.south();
						bstate = worldIn.getBlockState(posc);
						b = bstate.getBlock();
						
						if(b == this || b == JungleBlocks.infusedObsidianBlock)
						{
							meta = 2;
					        worldIn.setBlockState(pos, JungleBlocks.lostWorldPortal.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z));
						}
					}
				}
			}
		}    	
		
		return this.getStateFromMeta(meta);
		
    }

	
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
	{
		BlockPos posc = pos.down();
		IBlockState bstate = worldIn.getBlockState(posc);
		Block b = bstate.getBlock();
		
		if(b != JungleBlocks.infusedObsidianBlock && b != JungleBlocks.lostWorldPortal)
		{
			worldIn.setBlockToAir(pos);
		}
		else
		{
			posc = pos.west();
			bstate = worldIn.getBlockState(posc);
			b = bstate.getBlock();
			
			if(b == JungleBlocks.lostWorldPortal || b == JungleBlocks.infusedObsidianBlock)
			{
		        worldIn.setBlockState(pos, this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X));
			}
			else
			{
				posc = pos.east();
				bstate = worldIn.getBlockState(posc);
				b = bstate.getBlock();
				
				if(b == this || b == JungleBlocks.infusedObsidianBlock)
				{
			        worldIn.setBlockState(pos, this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X));
				}
				
				else
				{
					posc = pos.north();
					bstate = worldIn.getBlockState(posc);
					b = bstate.getBlock();
					
					if(b == JungleBlocks.lostWorldPortal || b == JungleBlocks.infusedObsidianBlock)
					{
				        worldIn.setBlockState(pos, JungleBlocks.lostWorldPortal.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z));
					}
					else
					{
						posc = pos.south();
						bstate = worldIn.getBlockState(posc);
						b = bstate.getBlock();
						
						if(b == this || b == JungleBlocks.infusedObsidianBlock)
						{
					        worldIn.setBlockState(pos, JungleBlocks.lostWorldPortal.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z));
						}
					}
				}
			}
		}
	}
	
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    }
    
//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons(IIconRegister p_149651_1_)
//    {        this.blockIcon = p_149651_1_.registerIcon("thejungle:lostWorldPortal");    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }


    //investigate changing particle colour.
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(100) == 0)
        {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int l = 0; l < 3; ++l)
        {
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = rand.nextInt(2) * 2 - 1;
            d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;

            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this)
            {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)i1;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)i1;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }
            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, 0.2, 0.8, 0.2);// d3, d4, d5);
        }
    }
}
