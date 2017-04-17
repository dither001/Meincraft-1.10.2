package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class jungleBraziers extends Block 
{
	public jungleBraziers(Material mat)
	{
		super(mat);
		
		this.setHardness(1.0F);
		this.setResistance(12f);
		this.setSoundType(SoundType.STONE);
		
		if(this.isLit())
		{
			this.setLightLevel(0.8F);
	        this.setTickRandomly(true);
		}
		else
		{
			this.setCreativeTab(w2theJungle.JungleModTab);
		}
	}
	
    public boolean isLit()
    {
		return false;
	}
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	    
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JungleBlocks.jungleBrazier);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(JungleBlocks.jungleBrazier);
    }

    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(JungleBlocks.jungleBrazier);
    }
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		Item item = Item.getItemFromBlock(JungleBlocks.jungleBrazier);
        return item == null ? null : new ItemStack(item, 1, this.damageDropped(state));
		}
    
	@Override
    public boolean onBlockActivated(World world, BlockPos pos,
    		IBlockState state, EntityPlayer playa, EnumHand hand, ItemStack is,
    		EnumFacing facing, float f1, float f2, float f3)
	{
    	if(is != null && is.getItem() == Items.FLINT_AND_STEEL) {
    		
            world.setBlockState(pos, JungleBlocks.jungleBrazierLit.getDefaultState());
            world.playSound(playa, pos.add(0.5D, 0.5D, 0.5D), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
            return true;
    	} else {
    		if(this.isLit()) {
    			world.playSound(playa, pos.add(0.5D, 0.5D, 0.5D), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.50F, world.rand.nextFloat() * 0.4F + 0.8F);
    			world.setBlockState(pos, JungleBlocks.jungleBrazier.getDefaultState());
    			}
    		return true;
    		}
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand)
    {
    	if(this.isLit())
    	{
    		int l = 0;
    		double d0 = (double)((float)pos.getX() + 0.5F);
    		double d1 = (double)((float)pos.getY() + 0.7F);
    		double d2 = (double)((float)pos.getZ() + 0.5F);
    		double d3 = 0.2199999988079071D;
    		double d4 = 0.27000001072883606D;
    		
    		if (rand.nextInt(24) == 0)
    		{
    			worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
    		}
    		if (l == 1)
    		{
    			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
    			worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
    		}
    		else
    			if (l == 2)
    			{
    				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
    				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
    			}
    			else
    				if (l == 3)
    				{
    					worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
    					worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
    				}
    				else
    					if (l == 4)
    					{
    						worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
    						worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
    					}
    					else
    					{
    						worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    						worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    					}
    		}
    	}

	public static class Lit extends jungleBraziers
	{
		public Lit(Material mat)
		{
			super(mat);
		}
		
		public boolean isLit()
		{
			return true;
		}
	}

	public static class Unlit extends jungleBraziers
	{
		public Unlit(Material mat)
		{
			super(mat);
		}
		
		public boolean isLit()
		{
			return false;
		}
	}

}
