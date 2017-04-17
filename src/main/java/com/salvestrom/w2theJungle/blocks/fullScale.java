package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.tileentity.TileEntityFullScale;

public class fullScale extends BlockContainer {
	
    protected static final AxisAlignedBB DEFAULT = new AxisAlignedBB(0.06125f + 0.125f, 0.0f, 0.125f, 1f - 0.125f - 0.06125f, 0.5f, 0.875f);
	
	public fullScale(Material wood) {
		
		super(wood);
		//this.setHardness(1.5F);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName("fullScale");
		//this.setBlockTextureName("thejungle:fullScale");
		this.setCreativeTab(w2theJungle.JungleModTab);
	}
	
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return DEFAULT;
    }
	
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return worldIn.isSideSolid(pos.down(), EnumFacing.UP);
	}  

	public void onBlockDestroyedByExplosion(World wrldIn, BlockPos pos, Explosion exp)
	{
		this.dropBlockAsItem(wrldIn, pos, wrldIn.getBlockState(pos), 1);
	}		


	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.INVISIBLE;
		}

	@Override
	public boolean isOpaqueCube(IBlockState state) {return false;}

	@Override
	public boolean isFullCube(IBlockState state) {return false;}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase e, ItemStack is)
	{
		int l = MathHelper.floor_double(e.rotationYaw * 16F / 360F + 0.5D);
		TileEntityFullScale teas = (TileEntityFullScale)world.getTileEntity(pos);
		
		if(teas != null)
		{
			teas.setOrientation(l);
		}
	}
	//gaurunteed drop after explosion, etc
	@Override
    public void dropBlockAsItemWithChance(World wrld, BlockPos pos, IBlockState state, float f1, int i1)
    {
        if (!wrld.isRemote && wrld.getGameRules().getBoolean("doTileDrops"))
        {
        	ItemStack item = new ItemStack(this);
            spawnAsEntity(wrld, pos, item);
        }
     }

	@SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		Item item = Item.getItemFromBlock(JungleBlocks.fullScale);
        return item == null ? null : new ItemStack(item, 1, this.damageDropped(state));
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(JungleBlocks.fullScale);
	}


	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityFullScale();
	}



}
