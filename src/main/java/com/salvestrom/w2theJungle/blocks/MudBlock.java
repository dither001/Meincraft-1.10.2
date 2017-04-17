package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MudBlock extends BlockFluidClassic {
	/*
	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowingIcon;
	*/
	public MudBlock(Fluid fluid, Material material) {
		
		super(fluid, material); //w2theJungle.mudMaterial);

		this.setSoundType(SoundType.GROUND);
		this.setUnlocalizedName("mudBlock");
		//this.setCreativeTab(w2theJungle.JungleModTab);
		//this.setBlockTextureName("thejungle:mudBlockI");
        this.setTickRandomly(true);
        this.setResistance(12.0F);
        this.blockHardness = 100.0F;
        this.quantaPerBlock = 8;
        this.setLightOpacity(8);

		}
	
    @Deprecated
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	public int tickRate(World p_149738_1_) {   return  5 ;}
	
	@Override
    public void onFallenUpon(World world, BlockPos pos, Entity ntt, float f)
    {
    	int i = MathHelper.floor_double(ntt.posX);
		int j = MathHelper.floor_double(ntt.posY - 0.20000000298023224D - (double) ntt.getYOffset());
		int k = MathHelper.floor_double(ntt.posZ);
    	
    	Random rng = new Random();

    	if (!world.isRemote && f > 3.0F)
    	{
    		//world.playAuxSFX(2006, i, j+2, k, MathHelper.ceiling_float_int(f - 3.0F));
    		}
    	}
    
    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity ntt)
    {
    	super.onEntityWalk(world, pos, ntt);
    		//TODO retry movement effects and so on here...
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return hitIfLiquid && ((Integer)state.getValue(LEVEL)).intValue() == 0;
    }
    
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity ntt)
    {
    	ntt.motionX *= 0.660012D;
    	ntt.motionY *= 0.66D;
        ntt.motionZ *= 0.660012D;
    	/*
    	if(ntt instanceof EntityLivingBase)
    	{
    		EntityLivingBase nttl = (EntityLivingBase)ntt;
    		double d0;
        {
            d0 = nttl.posY;
            nttl.moveFlying(nttl.moveStrafing, nttl.moveForward, 0.04F);
            nttl.moveEntity(nttl.motionX, nttl.motionY, nttl.motionZ);
            nttl.motionX *= 0.800000011920929D;
            nttl.motionY *= 0.800000011920929D;
            nttl.motionZ *= 0.800000011920929D;
            nttl.motionY -= 0.02D;

            if (nttl.isCollidedHorizontally && nttl.isOffsetPositionInLiquid(nttl.motionX, nttl.motionY + 0.6000000238418579D - nttl.posY + d0, nttl.motionZ))
            {
                nttl.motionY = 0.30000001192092896D;
            }
        }
        
    	}
    	*/
    	int i = MathHelper.floor_double(ntt.posX);
		int j = MathHelper.floor_double(ntt.posY - 0.20000000298023224D - (double) ntt.getYOffset());
		int k = MathHelper.floor_double(ntt.posZ);
		    	
     	Random rng = new Random();

    	if (!world.isRemote && ntt.fallDistance > 3.0F)
    	{
    		world.playEvent(2006, new BlockPos(i, j+2, k), MathHelper.ceiling_float_int(ntt.fallDistance - 3.0F));
    		}
    }

    
    public boolean isSourceBlock(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }
/*
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int p_149691_2_)
    {
        return side != 0 && side != 1 ? this.flowingIcon : this.stillIcon;
    }

	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister)
	{
		this.stillIcon = iconregister.registerIcon("theJungle:mudBlockStill");
		this.flowingIcon = iconregister.registerIcon("theJungle:mudBlockFlowing");
	}

	/*nice colour. but method solved nothing.
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return 0x906C51;
    } */
	
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rnd)
    {
    	int l;
    	int x = pos.getX(); int y = pos.getY(); int z = pos.getZ();

            if (rnd.nextInt(10) == 0)
            {
                l = ((Integer)stateIn.getValue(LEVEL)).intValue();
                
                if (l <= 0 || l >= 8) //these are inverted in water file...
                {
                	int r = rnd.nextInt(10);
                	
                	if(r >= 7)
                	{
                		worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT,
                				(double)((float)x + rnd.nextFloat()), (double)((float)y + 0.5 + rnd.nextFloat()), (double)((float)z + rnd.nextFloat()),
                				0.510D, 0.3275D, 0.0970D);
                	}
                	else if(r <= 3)
                	{
                		worldIn.spawnParticle(EnumParticleTypes.SUSPENDED,
                				(double)((float)x + rnd.nextFloat()), (double)((float)y + rnd.nextFloat()), (double)((float)z + rnd.nextFloat()),
                				0.0D, 0.0D, 0.0D);
                		}
                	else
                	{
                		//	String s = "BLOCK_CRACK_" + Block.getIdFromBlock(Blocks.GRASS) + "_" + worldIn.getBlockMetadata(x, y, z);
                        worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK,
                        		(double)((float)x + rnd.nextFloat()), (double)((float)y + rnd.nextFloat() + 0.25), (double)((float)z + rnd.nextFloat()),
                        		0.0D, 0.0D, 0.0D,
                        		new int[] {Block.getStateId(stateIn)});
                	}
                }
            }

            for (l = 0; l < 0; ++l)
            {
                int i1 = rnd.nextInt(4);
                int j1 = x;
                int k1 = z;

                if (i1 == 0)
                {
                    j1 = x - 1;
                }

                if (i1 == 1)
                {
                    ++j1;
                }

                if (i1 == 2)
                {
                    k1 = z - 1;
                }

                if (i1 == 3)
                {
                    ++k1;
                }

                if (worldIn.getBlockState(new BlockPos(j1, y, k1)).getMaterial() == Material.AIR && (worldIn.getBlockState(new BlockPos(j1, y - 1, k1)).getMaterial().blocksMovement() || worldIn.getBlockState(new BlockPos(j1, y - 1, k1)).getMaterial().isLiquid()))
                {
                    float f = 0.0625F;
                    double d0 = (double)((float)x + rnd.nextFloat());
                    double d1 = (double)((float)y + rnd.nextFloat());
                    double d2 = (double)((float)z + rnd.nextFloat());

                    if (i1 == 0)
                    {
                        d0 = (double)((float)x - f);
                    }

                    if (i1 == 1)
                    {
                        d0 = (double)((float)(x + 1) + f);
                    }

                    if (i1 == 2)
                    {
                        d2 = (double)((float)z - f);
                    }

                    if (i1 == 3)
                    {
                        d2 = (double)((float)(z + 1) + f);
                    }

                    double d3 = 0.0D;
                    double d4 = 0.0D;

                    if (i1 == 0)
                    {
                        d3 = (double)(-f);
                    }

                    if (i1 == 1)
                    {
                        d3 = (double)f;
                    }

                    if (i1 == 2)
                    {
                        d4 = (double)(-f);
                    }

                    if (i1 == 3)
                    {
                        d4 = (double)f;
                    }

                    worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0 + worldIn.rand.nextFloat(), d1 + worldIn.rand.nextFloat(), d2 + worldIn.rand.nextFloat(),
                			0.01D, 0.8D, 0.075);
                    //world.spawnParticle("splash", d0, d1, d2, d3, 0.0D, d4);
                }
            }

    	
    	//using bubble makes for good thermal vent.
        double d0 = (double)((float)x + 0.5F);
        double d1 = (double)((float)y + 0.5F);
        double d2 = (double)((float)z + 0.5F);
        
        if (rnd.nextInt(64) == 0)
        {
            
            if (l > 0 && l < 8)
            {
                worldIn.playSound(null,
                		new BlockPos((double)((float)d0 + 0.5F), (double)((float)d1 + 0.5F), (double)((float)d2 + 0.5F)),
                		SoundEvents.BLOCK_WATER_AMBIENT,
                		SoundCategory.AMBIENT,
                		rnd.nextFloat() * 0.25F + 1.75F, 0.35F);
            }
        }
        	
        	if(rnd.nextInt(300) == 0)
        	{
        		//volumne then pitch
        		worldIn.playSound(x+rnd.nextFloat(), y+0.5f, z+rnd.nextFloat(),
        				SoundEvents.BLOCK_LAVA_POP, SoundCategory.AMBIENT,
        				0.5F + rnd.nextFloat() * 0.2F,
        				0.06125F + rnd.nextFloat() * 0.037515F, true);
        		
            	worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT,
            			d0 + worldIn.rand.nextFloat(),
            			d1 + 0.5d + worldIn.rand.nextFloat(),
            			d2 + worldIn.rand.nextFloat(),
            			0.75D, 0.30D, 0.0535);
        }
    }

	
}
