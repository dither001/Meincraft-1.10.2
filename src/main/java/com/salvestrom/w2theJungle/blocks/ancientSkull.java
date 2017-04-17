package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ancientSkull extends Block {
	
    protected static final AxisAlignedBB DEFAULT = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
    public static final PropertyInteger ROTATION = PropertyInteger.create("rotations", 0, 15);

    
	public ancientSkull(Material mat) {
		super(mat);
	
		this.setHardness(1.0F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName("ancientSkull");
		this.setCreativeTab(w2theJungle.JungleModTab);
		
		//this.getDefaultState().withProperty(ancientSkull.ROTATION, Integer.valueOf(0));
		
	}
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return worldIn.isSideSolid(pos.down(), EnumFacing.UP);
	}
	
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return DEFAULT;
    }

 	
    @Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{return EnumBlockRenderType.MODEL;} //invisible = -1??

	@Override
    public boolean isOpaqueCube(IBlockState state) {return false;}

    public boolean isFullCube(IBlockState state)   {return false;}
    //above is the belows replacement??
//	@Override
//	public boolean renderAsNormalBlock() {return false;}

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase e)
    {
		int l = MathHelper.floor_double(e.rotationYaw * 16F / 360F + 0.5D);
		IBlockState state = worldIn.getBlockState(pos);
		state = this.getDefaultState().withProperty(ancientSkull.ROTATION, l & 15);

    	
        return state; //this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(NODROP, Boolean.valueOf(false));
    }
    
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase e, ItemStack is)
	{
		int l = MathHelper.floor_double(e.rotationYaw * 16F / 360F + 0.5D);
		state = this.getDefaultState().withProperty(ancientSkull.ROTATION, l & 15);

		//TileEntityAncientSkull teas = (TileEntityAncientSkull) world.getTileEntity(pos);
		//if(teas != null){teas.direction = l;}
	}

	@SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		Item item = Item.getItemFromBlock(JungleBlocks.ancientSkull);
        return item == null ? null : new ItemStack(item, 1, this.damageDropped(state));
	}

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(JungleBlocks.ancientSkull);
	}

    //@Override
	//public TileEntity createNewTileEntity(World var1, int var2)
    //{return new TileEntityAncientSkull();}
	
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(ROTATION, Integer.valueOf(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(ROTATION)).intValue();
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(ROTATION, Integer.valueOf(rot.rotate(((Integer)state.getValue(ROTATION)).intValue(), 16)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withProperty(ROTATION, Integer.valueOf(mirrorIn.mirrorRotation(((Integer)state.getValue(ROTATION)).intValue(), 16)));
    }
	
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ROTATION});
    }



}
