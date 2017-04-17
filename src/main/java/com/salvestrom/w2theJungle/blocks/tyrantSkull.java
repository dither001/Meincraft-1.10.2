package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.tileentity.TileEntityTyrantSkull;

public class tyrantSkull extends BlockContainer 
{

	public tyrantSkull(Material circuits) {
		super(circuits);
		
		//this.setHardness(1.0F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName("tyrantSkull");
		//this.setBlockTextureName("soul_sand");
		this.setCreativeTab(w2theJungle.JungleModTab);

        this.setTickRandomly(true);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));

	}
	
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return worldIn.isSideSolid(pos.down(), EnumFacing.UP);
	}
    
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
        return EnumBlockRenderType.INVISIBLE;
		}

	@Override
	public boolean isOpaqueCube(IBlockState state) {return false;}
	
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd)
    {
    	if(rnd.nextInt(1200) == 0) {
    		
    		world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_BLAZE_DEATH, SoundCategory.HOSTILE, 8.0F, 0.05F, true);
    		world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ENDERMEN_SCREAM, SoundCategory.HOSTILE, 8.0F, 0.05F, true);
    		world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.HOSTILE, 8.75F, 0.001F, true);
    	}	    	
    	
    }

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase e, ItemStack is)
	{
		int l = MathHelper.floor_double(e.rotationYaw * 16F / 360F + 0.5D);
		
		TileEntityTyrantSkull tsk = (TileEntityTyrantSkull) world.getTileEntity(pos);
		
		if(tsk != null)
		{
			tsk.setOrientation(l);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		Item item = Item.getItemFromBlock(JungleBlocks.tyrantSkull);
        return item == null ? null : new ItemStack(item, 1, this.damageDropped(state));
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(JungleBlocks.tyrantSkull);
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

/*	
	@Override
    public ArrayList<ItemStack> getDrops(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, int p_149749_6_, int fortune)
    {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        {
            if ((p_149749_6_ & 8) == 0)
            {
                ItemStack itemstack = new ItemStack(w2theJungle.tyrantSkull, 1, 0);
                TileEntityTyrantSkull tileentityskull = (TileEntityTyrantSkull)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

                if (tileentityskull == null) return ret;
                
                ret.add(itemstack);
            }
        }
        return ret;
        }
*/

	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityTyrantSkull();
	}
	
    @SideOnly(Side.CLIENT)
    public String getItemIconName()
    {
        return null; //this.getTextureName();
    }
}
