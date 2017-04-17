package com.salvestrom.w2theJungle.mobs.entity;

import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.mobs.entity.ai.EntityAINearestHuntableTarget;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLizardmanStalker extends EntityLizardmanBase implements IRangedAttackMob {
	
	public Class[] huntlist = new Class[] {
			EntityChicken.class, EntityCow.class, EntityPig.class, EntitySheep.class,
			EntitySwampCrab.class, EntitySpider.class			
			};
	
	public int attackTimer;

	public int animationTimer;

	public EntityLizardmanStalker(World par1World)	{
		super(par1World);
		
		this.setSize(0.5F, 1.5F);
		
		//TODO cant use this entitya class since its fixed for skeltons...

		this.tasks.addTask(1, new EntityAIAttackRanged(this, 0.35D, 20, 60, 20.0F)); //F is range
		this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.45D, 20.0F));

		//this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

		for(int i=0; i < this.huntlist.length; ++i)		{
			this.targetTasks.addTask(2, new EntityAINearestHuntableTarget(this, huntlist[i], 0, false));
			}

		this.setEquipmentBasedOnDifficulty(this.worldObj.getDifficultyForLocation(new BlockPos(this)));
	}
	
	 protected void applyEntityAttributes() {
		 super.applyEntityAttributes();
		 this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D); //20 default
		 }
	 
	 protected void entityInit()    {
			super.entityInit();
			//this.dataWatcher.addObject(14, Float.valueOf(0));
			}
	 
	    public int getItemInUseCount()
	    {
	    	int x = 72000 - 20 + this.animationTimer;
	    	return this.animationTimer > 0 ? x : 0;
	    }
	 
	 public int getTotalArmorValue() {
		 return 2;
	 }
	 
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	 {		 
		 if(this.worldObj != null)
		 {
			 if(!this.worldObj.isRemote)
			 {
				 if (this.rand.nextInt(3)==0)
				 {
					 this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(JungleItems.boneGripBow));
				 }
				 else
				 {
					 this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
				 }
			 }
		 }
	 }
	 
    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        float f = difficulty.getClampedAdditionalDifficulty();

        if (this.getHeldItemMainhand() != null && this.rand.nextFloat() < 0.85F * f)
        {
        	this.getHeldItemMainhand().addEnchantment(Enchantments.POWER, this.rand.nextInt(2)+1);
	        
        	if(this.rand.nextInt(3) > 0)
        	{
        		this.getHeldItemMainhand().addEnchantment(Enchantments.PUNCH, 1);
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


	 public boolean isAIEnabled(){return true;}

	public void onLivingUpdate() {
		
		if(this.animationTimer > 0) {--this.animationTimer;}
		if(this.attackTimer > 0) {--this.attackTimer;}

        //System.out.println(this.attackStatus);

		//this.isDead = true;
		super.onLivingUpdate();
		}

    public boolean attackEntityAsMob(Entity ntt) {
        return false; //stops archers from attemptinmg to engae in melee.
        //if this returns true they will always close to melee. add a distance based if statement? look at mistweavers
    }
    
    //need to setup an animation timer.
    
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float var2) {
		
		//check the skeleton file for remaining changes
        EntityArrow entityarrow = new EntityTippedArrow(this.worldObj, this);
        
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        entityarrow.setThrowableHeading(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
                
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, this.getHeldItemMainhand());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, this.getHeldItemMainhand());
        entityarrow.setDamage((double)(var2 * 2.2F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.getDifficulty().getDifficultyId() * 0.11F));

        if (i > 0)  {
        	entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        	}
        if (j > 0)  {
        	entityarrow.setKnockbackStrength(j);
        	}
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, this.getHeldItemMainhand()) > 0)
        {
        	entityarrow.setFire(160);
        }
        
        this.worldObj.setEntityState(this, (byte)15);
        
        this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
        this.attackTimer = 20;
	}
	
    @SideOnly(Side.CLIENT)
    public int getAnimTimer() {return this.animationTimer;}
	
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte par1)
    {
    	if (par1 == 15)
    	{
    		//this.attackStatus = 2;
    		this.animationTimer = 20;
    		}
    	else
    	{
    		//this.attackStatus = 1;
    		super.handleStatusUpdate(par1);
    		}
    	}
        
    //loot and sound follow...
    //the below currently takes no account of lootin on a weapon (par2).          
    protected void dropFewItems(boolean par1, int par2) {
    	
    	int chance = this.rand.nextInt(6);
    	switch (chance)
    	{
    	case 0:
    		break;
    		case 2:
    			this.dropItem(Items.MELON, 1);
    			break;
    		case 4:
    			if (par1){this.dropItem(JungleItems.carvedBone, 1);}
    			break;
    		default:
        		this.dropItem(Items.ARROW, 1);
    			break;
    			}
    	
    	this.moarLoot(par1, par2); //this creates a custom drop chance for the bow - 1 in 40 is too low.
    	//could bake together using a weighted drop chance.
    	
    	}
    //keep in mind theres already a drop equipment method.
    protected void moarLoot(boolean par1, int par2) {
    	if(par1 && this.rand.nextInt(20-par2) == 0) {
    		this.dropItem(this.getHeldItemMainhand().getItem(), 1);
    	}
    	
    }
    //this is blanked out because bow dropped by other method, skull was removed as potential loot
    //this method is actually defunct
  /*  protected void dropRareDrop(ItemStack stack, int par1)    {
    	this.getItemInUseCount();
    	
    	ResourceLocation location = properties.getKeys();
    	IItemPropertyGetter parameter = properties.getObject(location);
    	
    	stack.getItem().pre(stack, world, entity)cha(new ResourceLocation("pulling"));
    	}
 */
    /*
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getItemIcon(ItemStack p_70620_1_, int p_70620_2_)
    {
        IIcon iicon = super.getItemIcon(p_70620_1_, p_70620_2_);

        if(this.getHasTarget() == 2 && p_70620_1_.getItem() instanceof ItemBow)
           {
                int j = 0 + this.animationTimer;

                if (j >= 15)
                {
                    return ((ItemBow)p_70620_1_.getItem()).getIcon(p_70620_1_, 0);
                }

                if (j > 10)
                {
                	 return ((ItemBow)p_70620_1_.getItem()).getItemIconForUseDuration(0);
                }

                if (j > 5)
                {
                	return ((ItemBow)p_70620_1_.getItem()).getItemIconForUseDuration(1);

                }
                if (j >= 0) 
                {
                	return ((ItemBow)p_70620_1_.getItem()).getItemIconForUseDuration(2);
                }
           }
        else
            		//
        if(this.getHasTarget() != 2) {
            		((ItemBow)p_70620_1_.getItem()).getIcon(p_70620_1_, 0);
        }
         return iicon;
    }
    */
}



