package com.salvestrom.w2theJungle.mobs.entity;

import java.util.Calendar;

import javax.annotation.Nullable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.init.JungleItems;

public class EntitySacrificialSkeleton extends EntitySkeleton {
	
    private EntityAIAttackRangedBow aiRangedAttack = new EntityAIAttackRangedBow(this, 1.0D, 20, 15.0F);
    public EntitySacrificialSkeleton(World p_i1741_1_) {
		super(p_i1741_1_);
        this.tasks.addTask(4, this.aiRangedAttack);
    	this.isImmuneToFire = true;
	}
    
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
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
            int l = this.worldObj.getLightFromNeighbors(pos);

            if (this.worldObj.isThundering())
            {
                int j = this.worldObj.getSkylightSubtracted();
                this.worldObj.setSkylightSubtracted(10);
                l = this.worldObj.getLightFromNeighbors(pos);
                this.worldObj.setSkylightSubtracted(j);
            }
            
            //TODO this was probably forced true to prevent lennys summoned skels from not appearing.

            return true; //l <= this.rand.nextInt(13);
            
//            return l >= this.rand.nextInt(12) + 6;
        }
    }
	
    public void onLivingUpdate() {
    	
    	//this.setDead();
    	super.onLivingUpdate();
    }

    public void updateRidden()
    {
    	//this.renderYawOffset = 1.5f;
    	super.updateRidden();
    	
    }
    
    @Override    
    public boolean attackEntityFrom(DamageSource ds, float f0)
    {
    	if(ds == DamageSource.onFire)
    	{
    		return false;
    	}
    	//this should be covered by the entitymob version, but wasn't.
    	if(this.getRidingEntity() != null && ds.getEntity() == this.getRidingEntity()) 
    	{
    		return false;
    	}
    	
    	if(ds.getEntity() instanceof EntityLenny)
    	{
    		return false;
    	}
    
    	return super.attackEntityFrom(ds, f0);
    }
    
    public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_)
    {
    	if(this.getRidingEntity() != null && target == this.getRidingEntity())
    	{
    	}
    	
    	this.height = this.isRiding() ? 1.8f * 1.25f : 1.8f;
    	
        EntityArrow entityarrow = new EntityTippedArrow(this.worldObj, this);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        entityarrow.setThrowableHeading(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
        
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, this.getHeldItemMainhand());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, this.getHeldItemMainhand());
        entityarrow.setDamage((double)(p_82196_2_ * 3.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.getDifficulty().getDifficultyId() * 0.11F));

        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, this.getHeldItemMainhand()) > 0)
        {
            entityarrow.setFire(100);
        }

        this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }
    
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }
    
    protected Item getDropItem()
    {
        return Items.ARROW;
    }
    
    protected void dropFewItems(boolean p_70628_1_, int par2)
    {
        int j;
        int k;

        j = this.rand.nextInt(3 + par2);

        for (k = 0; k < j; ++k)
        {
        	this.dropItem(Items.ARROW, 1);
       	}

        j = this.rand.nextInt(1 + par2);

        for (k = 0; k < j; ++k)
        {
            this.dropItem(JungleItems.carvedBone, 1);
        }
        
	 	 if(this.rand.nextFloat() < 0.033F + (par2 * 0.01F))
	 	 {
	 		 this.dropRareDrop(par2);
	 	 }
    }

    protected void dropRareDrop(int p_70600_1_)
    {
    	this.entityDropItem(new ItemStack(JungleBlocks.ancientSkull, 1, 0), 0.0F);
    }
    
    
    public void setCombatTask()
    {
//        this.tasks.removeTask(this.aiAttackOnCollide);
        //this.tasks.removeTask(this.aiRangedAttack);
        //ItemStack itemstack = this.getHeldItem();

        //if (itemstack != null && (itemstack.getItem() == Items.bow || itemstack.getItem() == w2theJungle.boneGripBow))
        {
            //this.tasks.addTask(4, this.aiRangedAttack);
        }
//        else
        {
//            this.tasks.addTask(4, this.aiAttackOnCollide);
        }
    }
    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
//        super.addRandomArmor();
    	Item i = this.rand.nextInt(2) == 0 ? Items.BOW : JungleItems.boneGripBow;
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(i));
        
        if(this.rand.nextInt(5) == 0 && this.worldObj.provider.getDimension() == w2theJungle.dimensionIdLost)
        {
        	this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(JungleItems.obshelmet));
        }

    }
    

    
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
    	livingdata = super.onInitialSpawn(difficulty, livingdata);
    	//this.tasks.addTask(4, this.aiRangedAttack);
        this.setEquipmentBasedOnDifficulty(difficulty);
    	this.setEnchantmentBasedOnDifficulty(difficulty);
    	
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());

        if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }
    	
    	this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
        
    	return livingdata;
    }
}
