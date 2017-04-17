package com.salvestrom.w2theJungle.mobs.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanBase;

public class EntityAIPatrol extends EntityAIBase {

	 
	private EntityCreature entity;
	    private double xPosition;
	    private double yPosition;
	    private double zPosition;
	    private double speed;
	    private static final String __OBFID = "CL_00001608";

	    public EntityAIPatrol(EntityCreature par1EntityCreature, double par2)
	    {	
	        this.entity = par1EntityCreature;
	        this.speed = par2;
	        this.setMutexBits(1);
	    }

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute()
	    {
	      
	        {
	            Vec3d vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 15, 15); //was Vec3

	            
	            if (this.entity != null || vec3 == null || !this.entity.worldObj.isDaytime() || this.entity.worldObj.rand.nextInt(10) < 9 || (this.entity instanceof EntityLizardmanBase && ((EntityLizardmanBase)this.entity).getDarkOut() < 0.8F)
	            		)
	            {
	                return false;
	            }
	            else
	            {
	                this.xPosition = vec3.xCoord;
	                this.yPosition = vec3.yCoord;
	                this.zPosition = vec3.zCoord;
	                return true;
	            }
	        }
	    }

	    /**
	     * Returns whether an in-progress EntityAIBase should continue executing
	     */
	    public boolean continueExecuting()
	    {
	        return !this.entity.getNavigator().noPath();
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting()
	    {
	    	{
	    		this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
	    	}
	    }
	}