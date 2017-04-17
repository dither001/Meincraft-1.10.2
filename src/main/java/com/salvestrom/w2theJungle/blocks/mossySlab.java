package com.salvestrom.w2theJungle.blocks;

import java.util.List;
import java.util.Random;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class mossySlab extends BlockSlab
{
//    public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");
    public static final PropertyEnum<mossySlab.EnumType> VARIANT = PropertyEnum.<mossySlab.EnumType>create("variant", mossySlab.EnumType.class);


	public mossySlab() {
		
		super(Material.ROCK);
		this.setHardness(2.0F);
		this.setResistance(10F);
		//this.fullblock = fullblock;
		this.setSoundType(SoundType.STONE);
        IBlockState iblockstate = this.blockState.getBaseState();
        //iblockstate = iblockstate.withProperty(VARIANT, mossySlab.EnumType.MOSSY_SLAB);

        if (!this.isDouble())
//        {            iblockstate = iblockstate.withProperty(SEAMLESS, Boolean.valueOf(false));        }        else
        {
        	this.setCreativeTab(w2theJungle.JungleModTab);
			this.setLightOpacity(0);
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

		this.setDefaultState(iblockstate.withProperty(VARIANT, mossySlab.EnumType.MOSSY_SLAB));
		}
	/*	
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if (this.isDouble())
        {
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
        else if (side != EnumFacing.UP && side != EnumFacing.DOWN && !super.shouldSideBeRendered(blockState, blockAccess, pos, side))
        {
            return false;
        }
        else if (false) // Forge: Additional logic breaks doesSideBlockRendering and is no longer useful.
        {
            IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
            boolean flag = isHalfSlab(iblockstate) && iblockstate.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP;
            boolean flag1 = isHalfSlab(blockState) && blockState.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP;
            return flag1 ? (side == EnumFacing.DOWN ? true : (side == EnumFacing.UP && super.shouldSideBeRendered(blockState, blockAccess, pos, side) ? true : !isHalfSlab(iblockstate) || !flag)) : (side == EnumFacing.UP ? true : (side == EnumFacing.DOWN && super.shouldSideBeRendered(blockState, blockAccess, pos, side) ? true : !isHalfSlab(iblockstate) || flag));
        }
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
	
	@SideOnly(Side.CLIENT)
	//@Override //this should be overriding, but it isnt...
    protected static boolean isHalfSlab(IBlockState state)
    {
		Block block = state.getBlock();
        return block == JungleBlocks.mossyslabSingle;// || block == JungleBlocks.mossyStoneSlabSingle;
    }
	*/
	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult trace, World wrld, BlockPos pos, EntityPlayer player)
	{ 
        return new ItemStack(JungleBlocks.mossyslabSingle, 1, ((mossySlab.EnumType)state.getValue(VARIANT)).getMetadata());

		//return new ItemStack(this.getItemDropped(state, new Random(), 0));
				/*this == JungleBlocks.mossyStoneSlabSingle ||
				this == JungleBlocks.mossyStoneSlabDouble
				? Item.getItemFromBlock(JungleBlocks.mossyStoneSlabSingle)
						: (this == JungleBlocks.mossyslabDouble
						? Item.getItemFromBlock(JungleBlocks.mossyslabSingle)
								: Item.getItemFromBlock(JungleBlocks.mossyslabSingle));
								*/
	}
	
    public int quantityDropped(Random p_149745_1_)
    {
        return this.isDouble() ? 2 : 1;
    }
/*
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		if(this == JungleBlocks.mossyslabSingle || this == JungleBlocks.mossyslabDouble) {
			this.blockIcon = register.registerIcon("thejungle:stoneMossy");
			} else if(this == JungleBlocks.mossyStoneSlabSingle || this == JungleBlocks.mossyStoneSlabDouble) {
				this.blockIcon = register.registerIcon("thejungle:mossySmoothStone");
			}				
	    }
	*/
    
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Blocks.STONE_SLAB, 1, ((mossySlab.EnumType)state.getValue(VARIANT)).getMetadata());
    }
    
	@Override
	public Item getItemDropped(IBlockState state, Random random, int b) {
		
        return Item.getItemFromBlock(JungleBlocks.mossyslabSingle);
/*
		return this == JungleBlocks.mossyStoneSlabSingle ||
				this == JungleBlocks.mossyStoneSlabDouble
				? Item.getItemFromBlock(JungleBlocks.mossyStoneSlabSingle)
						: (this == JungleBlocks.mossyslabDouble
						? Item.getItemFromBlock(JungleBlocks.mossyslabSingle)
								: Item.getItemFromBlock(JungleBlocks.mossyslabSingle));
								*/
				}
/*	
	@Override
	protected ItemStack createStackedBlock(IBlockState state)
	{
		return this == JungleBlocks.mossyslabSingle ? new ItemStack(JungleBlocks.mossyslabSingle, 2, 0 & 7)
			: new ItemStack(JungleBlocks.mossyStoneSlabSingle, 2, 0 & 7);
	}
*/
    
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, mossySlab.EnumType.byMetadata(meta & 7));

        if (!this.isDouble())
//        {iblockstate = iblockstate.withProperty(SEAMLESS, Boolean.valueOf((meta & 8) != 0));        }        else
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }
    
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((mossySlab.EnumType)state.getValue(VARIANT)).getMetadata();

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

	@Override
	public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName() + "." + mossySlab.EnumType.byMetadata(meta).getUnlocalizedName();
	}
    
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
        return mossySlab.EnumType.byMetadata(stack.getMetadata() & 7);
	}

	@Override
	public IProperty<?> getVariantProperty() {
        return VARIANT;
	}

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        if (itemIn != Item.getItemFromBlock(JungleBlocks.mossyslabDouble))
        {
            for (mossySlab.EnumType blockstoneslab$enumtype : mossySlab.EnumType.values())
            {
            	list.add(new ItemStack(itemIn, 1, blockstoneslab$enumtype.getMetadata()));
            }
        }
    }

    public int damageDropped(IBlockState state)
    {
        return ((mossySlab.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    protected final BlockStateContainer createBlockState() {
        if (this.isDouble()) {
            return new BlockStateContainer(this, new IProperty[] {VARIANT});
        } else {
            return new BlockStateContainer(
                this,
                new IProperty[] {HALF, VARIANT});
        }
    }
    
    public MapColor getMapColor(IBlockState state)
    {
        return ((mossySlab.EnumType)state.getValue(VARIANT)).getMapColor();
    }
    
	@Override
	public boolean isDouble() {
		return false;
	}
	
    public static class Double extends mossySlab
    {
        public boolean isDouble()
        {
            return true;
        }
    }

    public static class Half extends mossySlab
    {
        public boolean isDouble()
        {
            return false;
        }
    }

	
    public static enum EnumType implements IStringSerializable
    {
        MOSSY_SLAB(0, "mossy", MapColor.STONE),
        MOSSY_SMOOTH_SLAB(1, "mossy_smooth", MapColor.STONE);


        private static final mossySlab.EnumType[] META_LOOKUP = new mossySlab.EnumType[values().length];
        private final int meta;
        private final String name;
        private final MapColor mapColor;

        private EnumType(int p_i46391_3_, String p_i46391_4_, MapColor p_i46391_5_)
        {
            this.meta = p_i46391_3_;
            this.name = p_i46391_4_;
            this.mapColor = p_i46391_5_;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static mossySlab.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.name;
        }

        static
        {
            for (mossySlab.EnumType blockstoneslabnew$enumtype : values())
            {
                META_LOOKUP[blockstoneslabnew$enumtype.getMetadata()] = blockstoneslabnew$enumtype;
            }
        }
    }
}

