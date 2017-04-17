package com.salvestrom.w2theJungle.mobs.entity;

import java.util.ArrayList;
import java.util.List;

import com.salvestrom.w2theJungle.init.JungleBlocks;

import net.minecraft.block.BlockLadder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWeb extends EntityThrowable
{
	
    public EntityWeb(World par1World)
    {
        super(par1World);
    }

    public EntityWeb(World par1World, EntityLivingBase par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
    }

    public EntityWeb(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    public void setHeadingFromThrower(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy)
    {
    	super.setHeadingFromThrower(entityThrower, rotationPitchIn, rotationYawIn, pitchOffset, velocity, inaccuracy);
    }
    //inserted here to manipulate accuracy setting.
    public void setThrowableHeading(double x, double y, double z, float vel, float acc)
    {
    	/*
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        entityarrow.setThrowableHeading(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
    	*/
    	float f = MathHelper.sqrt_double(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)acc;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)acc;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)acc;
        x = x * (double)vel;
        y = y * (double)vel;
        z = z * (double)vel;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
    }

    protected void onImpact(RayTraceResult result)
    {
    	float webbed;
    	
        if (result.entityHit != null)
        {
        	if (result.entityHit instanceof EntitySpider)
            {
                webbed = (float) (1 + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.getDifficulty().getDifficultyId() * 0.11F));
            }
        	else
        	{
        		webbed = (float) (3 + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.getDifficulty().getDifficultyId() * 0.11F));
        	}
                	
        	result.entityHit.attackEntityFrom(
        			DamageSource.causeThrownDamage(this, this.getThrower()), (float)webbed
        			);
        }
        else

        if (!this.worldObj.isRemote)
        	{
        		List<EnumFacing> side = this.checkAround();
        
        		int x = (int)this.posX;
        		int z = (int)this.posZ;
        		int y = (int)this.posY;

        		if(x < 0) { x-=1;}
        		if(z < 0) { z-=1;}

        		BlockPos pos = new BlockPos(x, y, z);
        		
        		if(side.size() > 0 &&
        				this.worldObj.getBlockState(pos).getBlock() == Blocks.AIR
        				//&&
        				//w2theJungle.jungleLadder.canPlaceBlockAt(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ)
        				)
        		{
        			//TODO find a way to randomise between available sides.
       			
        			this.worldObj.setBlockState(pos,
        					JungleBlocks.climbingWeb.getDefaultState().withProperty(BlockLadder.FACING,
        							side.get(this.rand.nextInt(side.size()))),
        							2);// (Integer)side.get(this.rand.nextInt(side.size())), 2);
        			}
        		}
     	

        for (int j = 0; j < 8; ++j)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
    public List<EnumFacing> checkAround()
    {
    	int x = (int)(this.posX - 0.0D);
    	int y = (int)(this.posY - 0.D);
    	int z = (int)(this.posZ - 0.D);
		
    	BlockPos pos = new BlockPos(x, y, z);
    	
    	List<EnumFacing> side = new ArrayList<EnumFacing>();// = new List<E>{} //i think a list is the best way forward.
    	
    	if(x < 0) { x-=1;}
		if(z < 0) { z-=1;}
    	
		if(this.worldObj.isSideSolid(pos.west(), EnumFacing.EAST))//isSideSolid(x - 1, y, z, EAST ))
    	{
			
            side.add(EnumFacing.EAST);
    		}
    	
    	if(this.worldObj.isSideSolid(pos.east(), EnumFacing.WEST))
    	{
    		side.add(EnumFacing.WEST);
    		
    	}

    	if(this.worldObj.isSideSolid(pos.north(), EnumFacing.SOUTH))
    	{
    		side.add(EnumFacing.SOUTH);
    	}
    	
    	if(this.worldObj.isSideSolid(pos.south(), EnumFacing.NORTH))
    	{
    		side.add(EnumFacing.NORTH);
    	}
		return side;
		
    }

}
