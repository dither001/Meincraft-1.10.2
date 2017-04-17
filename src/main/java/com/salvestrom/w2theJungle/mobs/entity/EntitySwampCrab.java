package com.salvestrom.w2theJungle.mobs.entity;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleItems;

public class EntitySwampCrab extends EntityMob {
	
	protected int clawSnap;
	public int animationTimer;
	public int knockedOver;
    public int over;
	public int attackTimer;

//	no swimming functionality
	
	public EntitySwampCrab(World par1World) {
		super(par1World);
		
        this.setSize(2.250F, 1.80F);
        //this.height = 2.5f;
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.25D, false)); //so the attack parts dont need a class...
		this.tasks.addTask(2, new EntityAIWander(this, 0.2237D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIMoveTowardsTarget(this, 0.223D, 20.0F));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		
	}

	public boolean isAIEnabled() {
		return true;
	}
	
	public boolean canDespawn()
	{
		return false;
	}
	
	 public int getTotalArmorValue() {
		 int armour;
		 if(this.over > 0) {armour = 4;}
		 else {armour = 22;} //22 approx. 0.96 dmg with unenchanted diamond sword 25 is immune
		 return armour;
	 }	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(27.5D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.95d);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.4d);
        //this dmg/dps is higher than allowed by a 50%s swing time decrease due to
        //the fact crabs cant attack while on their backs.
    }
    
    protected boolean isMovementCeased() {
    	return this.over > 0 || this.animationTimer > 0;
    }

	public void onLivingUpdate() {

		if (this.clawSnap == 0 && !this.worldObj.isRemote) {
			
			this.worldObj.setEntityState(this, (byte)30);
			this.playSound(SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, 1.0F, 1.5F); //increasing loudness, ups range at which can be heard. x*16 approx.
			this.clawSnap = 10 + this.rand.nextInt(400);
			} else {
				--this.clawSnap;
				}
		
		if(this.animationTimer > 0) {--this.animationTimer;} 
		if(this.over > 0)
		{
			--this.over;
			this.motionX *= 0;
			this.motionY *= 0;
			this.motionZ *= 0;
		}
		if(this.attackTimer > 0) {--this.attackTimer;}
		

		if(this.knockedOver > 0) {--this.knockedOver;}
		
			super.onLivingUpdate();
		}
	
    public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
    	
    	if(!this.worldObj.isRemote) {
    		
    		if(p_70653_1_ instanceof EntityLivingBase) {
    			
    			ItemStack istack = ((EntityLivingBase) p_70653_1_).getHeldItemMainhand();  
    			
    			if(istack != null)
    			{    				
    		        int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, istack);

    		        double d = this.rand.nextDouble();
    		        
    		        if (d >= this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue() -
    		        		(i*0.31) //approx 2 in 3 chance with kb2 and 1 in 3 with kb1.
    	                    			&& this.over == 0)
    	                    {
    	                    	this.worldObj.setEntityState(this, (byte)29);
    	                    	//must adjust knocked over timer as well as over here.
    	                    	this.over = 60;
    	                }
    				}
    			}
    		}
    	}
    
    
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
    	if(this.attackTimer == 0
    			&& this.over <= 0
    			&& this.getDistanceToEntity(this.getAttackTarget()) < 3.0F
    			&& p_70652_1_.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY && p_70652_1_.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {

    		this.attackTimer = 30; //up dmg to compensate for lower swing?
    		this.worldObj.setEntityState(this, (byte)4);
    		boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag)
        {
            p_70652_1_.motionY += 0.1000000059604645D;
        }
		this.playSound(SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF, 1.0F, 1.5F); //increasing loudness, ups range at which can be heard. x*16 approx.
//        this.playSound("mob.spider.say", 1.0F, 2.0F);
        return flag;
    	} else {
    		return false;
    	}
    }

    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable(p_70097_1_))
        {
            return false;
        }
        else
        {
        	return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }
	
    @SideOnly(Side.CLIENT)
    public int getAttackTimer() {
		return this.attackTimer;
    }
    
	@SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte p_70103_1_)
    {
		if(p_70103_1_ == 4) {
			this.attackTimer = 30;
		}
		
        if (p_70103_1_ == 30)
        {
        	this.animationTimer = 10;
        }
        
        if(p_70103_1_ == 29) {
        		this.knockedOver = 60;
        	}
        else
        {
            super.handleStatusUpdate(p_70103_1_);
        }
    }

	//spawny stuff
    public float getBlockPathWeight(BlockPos pos)
    {
        return -0.25F + this.worldObj.getLightBrightness(new BlockPos(pos));
    }

    protected boolean isValidLightLevel()
    {
		BlockPos pos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
		
		if (this.worldObj.getLightFor(EnumSkyBlock.SKY, pos) > this.rand.nextInt(32))
		{
			return false;
			}
		else
		{
			int l;
			
			l = this.worldObj.getLightFromNeighbors(pos);
			
			if (this.worldObj.isThundering())
			{
				int i1 = this.worldObj.getSkylightSubtracted();
				this.worldObj.setSkylightSubtracted(10);
				l = this.worldObj.getLightFromNeighbors(pos);
				this.worldObj.setSkylightSubtracted(i1);
			}

            return l >= this.rand.nextInt(8) + 7;
        }
    }
    
	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor_double(this.posZ);

		int ycheck = 50;

		if(this.worldObj.provider.getDimension() == w2theJungle.dimensionIdLost) {
			ycheck -= 15;
		}
		
		if (j >= ycheck && super.getCanSpawnHere())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	 @SideOnly(Side.CLIENT)
	 public int getAnimTimer() {return this.animationTimer;}
	 
	 @SideOnly(Side.CLIENT)
	 public int getKnockedOver() {return this.knockedOver;}

	 protected Item getDropItem() {
		 return this.rand.nextInt(3) == 0 ? Items.FISH : JungleItems.rawCrabMeat;
	
	 }

	 protected void dropFewItems(boolean p_70628_1_, int i) {
	 	 this.dropItem(getDropItem(), this.rand.nextInt(2)+1);
	 	 
	 	 if(this.rand.nextFloat() < 0.025F + (i * 0.01F)) //2.5% + 0.1-0.3
	 	 {
	 		 this.dropRareDrop(i);
	 		 }
	    }
	 
	 protected void dropRareDrop(int i)
	 {
		 ItemStack is = new ItemStack(JungleItems.clobberer);
		 if(!is.isItemEnchanted())
		 {
			 is.addEnchantment(w2theJungle.saurclout, this.rand.nextInt(2)+1);
			 if(this.rand.nextInt(3) > 0)
			 {
				 is.addEnchantment(Enchantments.KNOCKBACK, this.rand.nextInt(2)+1);
				 }
			 }
		 
		 this.entityDropItem(is, 1);
		 }
	    
	    protected SoundEvent getHurtSound()
	    {
	        return SoundEvents.BLOCK_STONE_BREAK;
	    }

	    protected SoundEvent getDeathSound()
	    {
	        return SoundEvents.ENTITY_SHULKER_DEATH;
	    }
	 
	 protected void playStepSound(BlockPos pos, Block blockIn)
	 {
		 this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 1.15F, .85F);
	 }

}
