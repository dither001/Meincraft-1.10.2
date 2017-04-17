package com.salvestrom.w2theJungle.mobs.entity;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.init.JungleItems;

public class EntityLizardmanWitchDoctor extends EntityLizardmanBase implements IRangedAttackMob {
	
	 private static final UUID field_110184_bp = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");

	 private static final AttributeModifier field_110185_bq = (new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0)).setSaved(false);
	 
	 public Class[] targetlist = new Class[] {
			 EntityZombie.class, EntitySkeleton.class, EntityWitch.class,
			 EntityEnderman.class, EntitySpider.class
			 };
		

	 private static final Item[] witchDrops = new Item[]
		{
		 Items.SPIDER_EYE,
		 Items.GLASS_BOTTLE,
		 Items.COOKED_CHICKEN,
		 JungleItems.decorativeSeal,
		 JungleItems.carvedBone
		 };

	 public int witchAttackTimer;
	 public int animationTimer;
	 
	public EntityLizardmanWitchDoctor(World par1World) {
		super(par1World);
		
		this.setSize(0.6f, 1.5f);
		
		this.tasks.addTask(1, new EntityAIAttackRanged(this, 0.35D, 40, 60, 12.0F)); //F is range
		
		for(int i=0; i < this.targetlist.length; ++i)
		{
	        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, targetlist[i], false));

		}
//		this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.45D, 20.0F));
		
	}
	
	 public int getTotalArmorValue() {
		 return 3;
	 }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
    //    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    private static final DataParameter<Byte> AGGRESSIVE = EntityDataManager.<Byte>createKey(EntityLizardmanScuffler.class, DataSerializers.BYTE);
    
	protected void entityInit()
    {
        super.entityInit();
		this.dataManager.register(AGGRESSIVE, Byte.valueOf((byte)0));
    }


    /**
     * Set whether this witch is aggressive at an entity.
     */
    public void setAggressive(boolean par1)
    {
        this.dataManager.set(AGGRESSIVE, Byte.valueOf((byte)(par1 ? 1 : 0)));
    }

    /**
     * Return whether this witch is aggressive at an entity.
     */
    public boolean getAggressive()
    {
        return this.dataManager.get(AGGRESSIVE) == 1;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    //public int getLivingSoundTime(){return this.livingSoundTime;}
	//public float getConserveEnergy() {return this.conserveEnergy;}

    public int useHealing;

	public void onLivingUpdate()
    {

		//this code is to stop the witch attacking while it applies its own buffs
        if (!this.worldObj.isRemote)
        {
            if (this.getAggressive())
            {
                if (this.witchAttackTimer-- <= 0)
                {
                    this.setAggressive(false);
                    ItemStack itemstack = this.getHeldItemMainhand();
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, (ItemStack)null);

                    if (itemstack != null && itemstack.getItem() == Items.POTIONITEM)
                    {
                    	List<PotionEffect> list = PotionUtils.getEffectsFromStack(itemstack);

                        if (list != null)
                        {
                            for (PotionEffect potioneffect : list)
                            {
                                this.addPotionEffect(new PotionEffect(potioneffect));
                            }
                        }
                    }

                    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(field_110185_bq);
                }
            }
            else
            {
                PotionType potiontype = null;

                if (this.rand.nextFloat() < 0.15F && this.isInsideOfMaterial(Material.WATER) && !this.isPotionActive(MobEffects.WATER_BREATHING))
                {
                    potiontype = PotionTypes.WATER_BREATHING;
                }
                else if (this.rand.nextFloat() < 0.15F && this.isBurning() && !this.isPotionActive(MobEffects.FIRE_RESISTANCE))
                {
                    potiontype = PotionTypes.FIRE_RESISTANCE;
                }
                else if (this.rand.nextFloat() < 0.5F && this.getHealth() < this.getMaxHealth() * 0.75 && this.useHealing == 0)
                {
                	potiontype = PotionTypes.HEALING;
                    this.useHealing = 200; //one pot per 10s?
                }
                else if (this.rand.nextFloat() < 0.25F && this.getAttackTarget() != null && !this.isPotionActive(MobEffects.SPEED) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0D)
                {
                	potiontype = PotionTypes.SWIFTNESS;
                }

                if (potiontype != null)
                {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), potiontype));
                    this.witchAttackTimer = this.getHeldItemMainhand().getMaxItemUseDuration();
                    this.setAggressive(true);
                    IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
                    iattributeinstance.removeModifier(field_110185_bq);
                    iattributeinstance.applyModifier(field_110185_bq);
                }
            }

        }

		if(this.animationTimer > 0) {--animationTimer;} 
        
		if(useHealing > 0) {--useHealing;}
		
        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte par1)
    {
    	if (par1 == 15)
        {
            for (int i = 0; i < this.rand.nextInt(35) + 10; ++i)
            {
                this.worldObj.spawnParticle(EnumParticleTypes.SPELL_WITCH, this.posX + this.rand.nextGaussian() * 0.12999999523162842D, this.getEntityBoundingBox().maxY + 0.5D + this.rand.nextGaussian() * 0.12999999523162842D, this.posZ + this.rand.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
            }
            this.animationTimer = 20;
        }
        else
        {
            super.handleStatusUpdate(par1);
        }
    }
    
    public int getAnimTimer() {return this.animationTimer;}


    /**
     * Reduces damage, depending on potions
     */
    protected float applyPotionDamageCalculations(DamageSource par1DamageSource, float par2)
    {
        par2 = super.applyPotionDamageCalculations(par1DamageSource, par2);

        if (par1DamageSource.getEntity() == this)//immune to its own potion.
        {
            par2 = 0.0F;
        }

        if (par1DamageSource.isMagicDamage())//heavy reduction from magic.
        {
            par2 = (float)((double)par2 * 0.15D);
        }

        return par2;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(3) + 1;

        for (int k = 0; k < j; ++k)
        {
            int l = this.rand.nextInt(3);
            Item item = witchDrops[this.rand.nextInt(witchDrops.length)];

            if (par2 > 0)
            {
                l += this.rand.nextInt(par2 + 1);
            }

            for (int i1 = 0; i1 < l; ++i1)
            {
                this.dropItem(item, 1);
            }
        }
        
	 	 if(this.rand.nextFloat() < 0.033F + (par2 * 0.01F))
	 	 {
	 		 this.dropRareDrop(par2);
	 	 }
    }
    
    protected void dropRareDrop(int i)
    {
    	switch (this.rand.nextInt(2))
        {
        case 0:
            this.entityDropItem(new ItemStack(JungleBlocks.ancientSkull, 1, 0), 0.0F);
            break;
        case 1:
            this.dropItem(JungleItems.gemSapphire, 1);
            break;
            }
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2)
    {
        if (!this.getAggressive())
        {

            double d0 = par1EntityLivingBase.posX + par1EntityLivingBase.motionX - this.posX;
            double d1 = par1EntityLivingBase.posY + (double)par1EntityLivingBase.getEyeHeight() - 1.100000023841858D - this.posY;
            double d2 = par1EntityLivingBase.posZ + par1EntityLivingBase.motionZ - this.posZ;
            float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);
            
            PotionType potiontype = null;


            if(f1 >= 8.0F && !par1EntityLivingBase.isPotionActive(MobEffects.SLOWNESS))
            {
            	potiontype = PotionTypes.SLOWNESS;
            }
            else if (f1 <= 3.0F && !par1EntityLivingBase.isPotionActive(MobEffects.WEAKNESS) && this.rand.nextFloat() < 0.25F)
            {
            	potiontype = PotionTypes.WEAKNESS;
            }
            else
            //insert to allow witchdoctors to actually damage undead && spiders
            if(par1EntityLivingBase instanceof EntityZombie || par1EntityLivingBase instanceof EntitySkeleton)
            {
            	potiontype = PotionTypes.HEALING; //health pot
            }
            else if(par1EntityLivingBase instanceof EntitySpider) 
            {
            	potiontype = PotionTypes.HARMING; //?? why am i setting this back to the initial potion??
            }
            else if(par1EntityLivingBase.getHealth() >= 8.0F && !par1EntityLivingBase.isPotionActive(MobEffects.POISON))
            {
            	potiontype = PotionTypes.POISON;
            } 
            
            //EntityPotion entitypotion = new EntityPotion(this.worldObj, this, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potiontype));
            EntityWeb nttweb = new EntityWeb(this.worldObj, this);
            nttweb.rotationPitch -= -20f;
            //entitypotion.rotationPitch -= -20.0F;

           // if (this.rand.nextFloat() < 7.5E-4F)
            {
                this.worldObj.setEntityState(this, (byte)15);
            }
            
//            System.out.println(entitypotion.getPotionDamage());
            if(potiontype != null)
            {
                EntityPotion entitypotion = new EntityPotion(this.worldObj, this, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potiontype));
                entitypotion.rotationPitch -= -20.0F;
                entitypotion.setThrowableHeading(d0, d1 + (double)(f1 * 0.2F), d2, 0.75F, 8.0F);
            	this.worldObj.spawnEntityInWorld(entitypotion);
            }
            else
            {
            	nttweb.setThrowableHeading(d0, d1 + (double)(f1 * 0.2F), d2, 0.75F, 8.0F);
            	this.worldObj.spawnEntityInWorld(nttweb);
            }
            
            //TODO could always alter this so default potion is replaced with a low damaging dart, or throwing knife.
        }
    }
}