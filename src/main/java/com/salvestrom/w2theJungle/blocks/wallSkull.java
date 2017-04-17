package com.salvestrom.w2theJungle.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.tileentity.TileEntityWallSkull;

public class wallSkull extends BlockContainer {

	public wallSkull(Material p_i45394_1_) {
		super(Material.CIRCUITS);
		
		this.setHardness(1.0F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName("wallSkull");
		//this.setBlockTextureName("thejungle:stoneMossy");
		this.setCreativeTab(w2theJungle.JungleModTab);

	}
	
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return worldIn.isSideSolid(pos.down(), EnumFacing.UP);
	}

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
	{return EnumBlockRenderType.INVISIBLE;}

	@Override
    public boolean isOpaqueCube(IBlockState state) {return false;}


	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase e, ItemStack is)
	{
		int l = MathHelper.floor_double(e.rotationYaw * 16F / 360F + 0.5D);
		TileEntityWallSkull tsk = (TileEntityWallSkull)world.getTileEntity(pos);
		if(tsk != null)
		{
			tsk.setOrientation(l);
		}
	}
	
	@Override
    public void dropBlockAsItemWithChance(World wrld, BlockPos pos, IBlockState state, float f1, int i1)
    {
        if (!wrld.isRemote && wrld.getGameRules().getBoolean("doTileDrops"))
        {
        	ItemStack item = new ItemStack(this);
            spawnAsEntity(wrld, pos, item);
        }
     }
	
	//TODO interesting that i blaNKED these in 1.7.10. do they have no effectt??
/*
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return Item.getItemFromBlock(w2theJungle.wallSkull);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(w2theJungle.wallSkull);
	}
*/

	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityWallSkull();
	}
}
