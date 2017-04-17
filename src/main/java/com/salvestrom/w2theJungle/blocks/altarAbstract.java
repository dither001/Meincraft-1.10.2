package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.tileentity.TileEntityAltarAbstract;

public class altarAbstract extends BlockContainer {
    protected static final AxisAlignedBB DEFAULT = new AxisAlignedBB(0.06125F, 0.06125F, 0.06125F, 0.93875F, 0.93875F, 0.93875F);

	public altarAbstract(Material rock) {
		super(rock);
		this.setHardness(1.0F).setResistance(12F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName("altarAbstract");
		//this.setBlockTextureName("thejungle:altarAbstract");
		this.setCreativeTab(w2theJungle.JungleModTab);
		

		}
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return DEFAULT;
    }
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
        return EnumBlockRenderType.INVISIBLE;
		}

	@Override
	public boolean isOpaqueCube(IBlockState state) {return false;}

	@Override
    public boolean isFullCube(IBlockState state)   {return false;}

	@SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		Item item = Item.getItemFromBlock(JungleBlocks.altarAbstract);
        return item == null ? null : new ItemStack(item, 1, this.damageDropped(state));
	}

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(JungleBlocks.altarAbstract);
	}


	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityAltarAbstract();
	}
}
