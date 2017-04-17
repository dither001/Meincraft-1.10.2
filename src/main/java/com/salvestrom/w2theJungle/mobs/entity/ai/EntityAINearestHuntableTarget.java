package com.salvestrom.w2theJungle.mobs.entity.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.EntitySelectors;

import com.google.common.base.Predicate;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanBase;

public class EntityAINearestHuntableTarget<T extends EntityLivingBase>  extends EntityAITarget
{
    private final Class<T> targetClass;
    private final int targetChance;
    /** Instance of EntityAINearestAttackableTargetSorter. */
    private final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
    /**
     * This filter is applied to the Entity search.  Only matching entities will be targetted.  (null -> no
     * restrictions)
     */
    protected final Predicate <? super T > targetEntitySelector;
    protected T targetEntity;
    private static final String __OBFID = "CL_00001620";

    public EntityAINearestHuntableTarget(EntityCreature par1EntityCreature, Class<T> par2Class, int par3, boolean par4)
    {
        this(par1EntityCreature, par2Class, par3, par4, false);
    }

    public EntityAINearestHuntableTarget(EntityCreature par1EntityCreature, Class<T> par2Class, int par3, boolean par4, boolean par5)
    {
        this(par1EntityCreature, par2Class, par3, par4, par5, (Predicate <? super T >)null);
    }

    public EntityAINearestHuntableTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5, final Predicate <? super T > targetSelector)
    {
        super(par1EntityCreature, par4, par5);
        this.targetClass = par2Class;
        this.targetChance = par3;
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(par1EntityCreature);
        this.setMutexBits(1);
        this.targetEntitySelector = new Predicate<T>()
        {
            public boolean apply(@Nullable T p_apply_1_)
            {
                return p_apply_1_ == null ? false : (targetSelector != null && !targetSelector.apply(p_apply_1_) ? false : (!EntitySelectors.NOT_SPECTATING.apply(p_apply_1_) ? false : EntityAINearestHuntableTarget.this.isSuitableTarget(p_apply_1_, false)));
            }
        };
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	float darkout = ((EntityLizardmanBase)this.taskOwner).getDarkOut();
    	
    	if(//!this.taskOwner.worldObj.isRemote && 
    			darkout <= 0.8 && this.taskOwner.getAttackTarget() == null) {
    		return false;
    	} else
    	    	
        if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0)
        {
            return false;
        }
        else
        {
            double d0 = this.getTargetDistance();
            List list = this.taskOwner.worldObj.getEntitiesWithinAABB(this.targetClass, this.taskOwner.getEntityBoundingBox().expand(d0, 4.0D, d0), this.targetEntitySelector);
            Collections.sort(list, this.theNearestAttackableTargetSorter);

            if (list.isEmpty())
            {
                return false;
            }
            else
            {
                this.targetEntity = (T)list.get(0);
                return true;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }

    public static class Sorter implements Comparator
        {
            private final Entity theEntity;
            private static final String __OBFID = "CL_00001622";

            public Sorter(Entity par1Entity)
            {
                this.theEntity = par1Entity;
            }

            public int compare(Entity par1Entity, Entity par2Entity)
            {
                double d0 = this.theEntity.getDistanceSqToEntity(par1Entity);
                double d1 = this.theEntity.getDistanceSqToEntity(par2Entity);
                return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
            }

            public int compare(Object par1Obj, Object par2Obj)
            {
                return this.compare((Entity)par1Obj, (Entity)par2Obj);
            }
        }

}
