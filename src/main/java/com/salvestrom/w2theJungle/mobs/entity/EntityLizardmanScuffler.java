package com.salvestrom.w2theJungle.mobs.entity;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.init.JungleSounds;

public class EntityLizardmanScuffler extends EntityLizardmanBase {
	
	public Class[] targetlist = new Class[] {EntityPlayer.class, EntitySwampCrab.class,	EntityVillager.class,
			EntityIronGolem.class, EntityEnderman.class, EntitySpider.class};
	//targets are check in this order. apiders will be ignored in favour of a distant crab
	
	public int attackTimer;
	public int animationTimer;
	public boolean isMocking;
	
	public EntityLizardmanScuffler(World par1World)
	{
		super(par1World);

		this.setSize(0.7F, 1.5F);

		this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.42D, true));
        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.35F));
		//set false - can see thru walls
		for (int i = 0; i < this.targetlist.length; ++i) {
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, targetlist[i], 0, false, false, IMob.MOB_SELECTOR));
			}
//		this.tasks.addTask(1, new EntityAIClimbing(this));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityStoneGolem.class, 6.0F));
        this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.42D, 20.0F));
		
		//this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		
		//this.setEquipmentBasedOnDifficulty(this.worldObj.getDifficultyForLocation(new BlockPos(this)));
	}
	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(27.0D); //20 default
	        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D); //zombie is 3. pigmen are 5.

	    }
	 
	 public boolean isAIEnabled(){return true;}
	 

	 private static final DataParameter<Byte> MOCKING = EntityDataManager.<Byte>createKey(EntityLizardmanScuffler.class, DataSerializers.BYTE);
	 
	 protected void entityInit()    {
			super.entityInit();
			this.dataManager.register(MOCKING, Byte.valueOf((byte)0));

			}
	 
	 @SideOnly(Side.CLIENT)
	 public boolean getIsMocking()
	 {
		 return this.isMocking;
//		 return this.dataWatcher.getWatchableObjectByte(15);
		 }

	 public int getTotalArmorValue() {
		 return 4;
	 }
	 
	 //for some reason this weapon vanishes after a reload...
	 //never seen it happen with stalkers. do they switch bows perhaps?
	 //not all scufflers hold their swords like they have an held item.
	 //the issue was that some saurohn only "seemed" to have a weapon, when they actually didnt.
	 @Override
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	 {		 
		 if(this.worldObj != null)
		 {
			 if(!this.worldObj.isRemote)
			 {
				 int r = this.rand.nextInt(5);
				 
				 if(r > 3)
				 {
					 this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(JungleItems.bonehandlesword));
				 }
				 else
				 
				 if(r > 2)
				 {
					 this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_AXE));
				 }
			 }
		 }
	 }
	 

	    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        float f = difficulty.getClampedAdditionalDifficulty();

        //f is a multiplier between 0-1 that takes into account difficulty and moon phase and  length of time in an area
	        //adjust this to allow more frequent enchanting.
	        //gorrbat egg... what aboiut it??

	        //the modifier here is so high that saurohn are almost always enchanted. was .95.
	        if (this.getHeldItemMainhand() != null && this.rand.nextFloat() < 0.85F * f)
	        {
	        	this.getHeldItemMainhand().addEnchantment(w2theJungle.saurclout, this.rand.nextInt(2)+1);
	        	
	        	if(this.rand.nextInt(3) > 0)
	        	{
	        		this.getHeldItemMainhand().addEnchantment(Enchantments.KNOCKBACK, this.rand.nextInt(2)+1);
	        	}
	        }

	        for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
	        {
	            if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
	            {
	                ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);

	                if (itemstack != null && this.rand.nextFloat() < 0.5F * f)
	                {
	                    EnchantmentHelper.addRandomEnchantment(this.rand, itemstack, (int)(5.0F + f * (float)this.rand.nextInt(18)), false);
	                }
	            }
	        }
	    }
	
	 public void onLivingUpdate()	{
		 super.onLivingUpdate();

		//this.isDead = true;
		if(this.attackTimer < 20) {++this.attackTimer;}
		if(this.animationTimer > 0)
		{
			--this.animationTimer;
			}
		
		
		if(!this.worldObj.isRemote)
		{
			if(this.attackStatus != 2 &&
					!this.isMocking && this.rand.nextInt(6400) == 0 && this.hurtTime == 0)
			{
				this.isMocking = true;
	        	this.worldObj.setEntityState(this, (byte)29);
				this.animationTimer = 80;
				this.playSound(JungleSounds.SAUROHN_MOCK, 1F, 1.0F);
				this.livingSoundTime -= 100;
				
				}
			else if(this.animationTimer == 0)
			{
				this.isMocking = false;
				}
			}
		
		if(this.isMocking)
		{
			this.moveForward = this.moveStrafing = 0f;
			
			if(this.animationTimer%25 == 0)
			{
	        	this.worldObj.setEntityState(this, (byte)30);

			}
			
			if(this.animationTimer == 15)
			{
            	List lt = this.worldObj.getEntitiesWithinAABB(EntityLizardmanBase.class, this.getEntityBoundingBox().expand(10.0D, 2.0D, 10.0D));
            	if(lt.size() > 0)
            	{
            		for(int i = 0; i < 5; ++i)
            		{            		
            			int j = this.rand.nextInt(lt.size());
                		
            			EntityLizardmanBase nttliz = (EntityLizardmanBase)lt.get(j);
            			
            			if(lt.get(j) != null && (nttliz.attackStatus != 2 || nttliz.attackStatus != 4))
            			{	
            				nttliz.isLaughing = true;
            				//nttliz.laughTimer = 20;
            				} 
            			}
            		}

			}
		}
		
		
		if(!this.worldObj.isRemote && this.ticksExisted%20 == 0)
		{
		//	System.out.println(this.livingSoundTime);
		}
		
	}
	 
	public void setHasTarget()
	{
		if(this.isMocking == true)
		{
			this.attackStatus = 4;
			this.dataManager.set(HAS_AGGRO, this.attackStatus);

		}
		else
		{
			super.setHasTarget();
			}
		}

/*	
    protected void attackEntity(Entity par1Entity, float par2) {
    	
    	if (par2 < 2.0F
    			&& par1Entity.boundingBox.maxY > this.boundingBox.minY
    			&& par1Entity.boundingBox.minY < this.boundingBox.maxY) 	{
    		this.attackEntityAsMob(par1Entity);
    		}
    	}
    	*/
    
	boolean flag = false;

    public boolean attackEntityAsMob(Entity ntt) {
    	
    	if (this.getDistanceToEntity(this.getAttackTarget()) > 2.0F
        		&& this.getDistanceToEntity(this.getAttackTarget()) < 6.0F && this.rand.nextInt(10) == 0) {

        	if(this.onGround){

    		double d0 = ntt.posX - this.posX;
    		double d1 = ntt.posZ - this.posZ;
    		float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
    		this.motionX = d0 / (double)f2 * 0.5D * 0.600000011920929D + this.motionX * 0.20000000298023224D;
    		this.motionZ = d1 / (double)f2 * 0.5D * 0.600000011920929D + this.motionZ * 0.20000000298023224D;
    		this.motionY = 0.4000000059604645D;
    		}
        }


    	if(this.attackTimer >= 20
    			//&& this.getDistanceToEntity(this.getAttackTarget()) < 2.0F
    			) {
    		
        	this.worldObj.setEntityState(this, (byte)4);
        	float dmg = this.getHeldItemMainhand() != null ? 3.8f : 3.3f;
        	flag = ntt.attackEntityFrom(DamageSource.causeMobDamage(this), dmg);
        	        
        	if (flag) {
        		
        		this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 0.5F, 1.5F);
        		this.attackTimer = 0;
        	}
        }
        	return flag;
        }

    protected void dropFewItems(boolean par1, int par2) {
    	
    	int chance = this.rand.nextInt(6);
    	switch (chance)
    	{
    	case 0:
    		this.dropItem(Items.COOKED_CHICKEN, 1);
    		break;
    		case 2:
    			this.dropItem(Items.MELON, 1);
    			break;
    		case 4:
    			if (par1){this.dropItem(JungleItems.carvedBone, 1);}
    			break;
    		default:
    			break;
    			}
    	
    	if(this.getHeldItemMainhand() != null) {
    		this.moarLoot(par1, par2);
    	}
    }
    
    protected void moarLoot(boolean par1, int par2) {
    	
    	if(par1 && this.rand.nextInt(20-par2) == 0) {
    		this.dropItem(this.getHeldItemMainhand().getItem(), 1);
    	}
    	
    }

    protected void dropRareDrop(int par1)    {
    	this.entityDropItem((new ItemStack(JungleBlocks.ancientSkull, 1)), 0.0F);
    	}
    
    @SideOnly(Side.CLIENT)
    public int getAnimTimer() {return this.animationTimer;}

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte par1)
    {
    	
        if (par1 == 4)
        {
        	this.animationTimer = 20;
            this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 0.5F, 1.0F);
        }
        else if (par1 == 29)
		{
			this.mouthAnimTimer = 50;
			this.animationTimer = 80;
		}
        else
        {
            super.handleStatusUpdate(par1);
        }
    }
}

    
    //** climbing code begins **//
/*    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
    
    public boolean isOnLadder(){return this.isBesideClimbableBlock();}
   
/*    public DataWatcher getTrousers() {
    	return this.dataWatcher;
    }
    
    public boolean getHCollision() {
    	return (this.isCollidedHorizontally);
    }
*/    
/*    public boolean isBesideClimbableBlock(){return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;}
       
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote ||
        		this.worldObj.getBlock((int)posX+1, (int)posY, (int)posZ) != Blocks.wooden_door ||
        		this.worldObj.getBlock((int)posX, (int)posY, (int)posZ+1) != Blocks.wooden_door ||
        		this.worldObj.getBlock((int)posX, (int)posY-2, (int)posZ) != Blocks.wooden_door)
        {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }
    
    public void setBesideClimbableBlock(boolean par1)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
            b0 = (byte)(b0 | 1);
        }
        else
        {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
    }
*/    //climbing code ends.

    //loot and sound follow...
    //protected Item getDropItem(){}
