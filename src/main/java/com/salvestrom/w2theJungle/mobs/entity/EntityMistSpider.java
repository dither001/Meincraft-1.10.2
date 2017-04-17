package com.salvestrom.w2theJungle.mobs.entity;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.mobs.entity.ai.EntityAISecondaryAttack;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;

public class EntityMistSpider extends EntitySpider implements IRangedAttackMob {

	public int attackTimer;
	public float minRange;
	
	public EntityMistSpider(World par1World) {
		super(par1World);
		
		this.minRange = 7;

		this.setSize(2.0F, 1.3F); //2nd var. height was 1.4. adjusted to let riders shoot over spider head.
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.tasks.addTask(1, new EntityAISecondaryAttack(this, 0.45D, 40, 15.0F, this.minRange));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.52D, false));
        this.tasks.addTask(2, new EntityAIWander(this, 0.35D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.45D, 20.0F));
		
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityLivingBase>(this, EntityLivingBase.class, 10, true, false, IMob.MOB_SELECTOR));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true, false));

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		
        this.targetTasks.removeTask(new EntityAINearestAttackableTarget<EntitySacrificialSkeleton>(this, EntitySacrificialSkeleton.class, true, false));


	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D); //48
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.5D);
        //TODO as noted else where this dmg vale is the same dps as a zombie since the timer is 50% slower
        //adjusting to 6 would be closer to the nintended dmg. (50% higher than a zombie.
        //however, spiders are only 2, so 4'5 would give mist spiders 50% higher dps than them.

        //       this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.700000011920929D);
    }

    public boolean canAttackClass(Class p_70686_1_)
    {
    	//mount wont attack ANY of the riders class, not just the rider

    	if(this.getPassengers() != null)
    	{
    		return this.getPassengers().getClass() != p_70686_1_
    				&& EntityCreeper.class != p_70686_1_ && EntityGhast.class != p_70686_1_;// EntitySacrificialSkeleton.class != p_70686_1_;
			}
    	else
    	{
    		return EntityCreeper.class != p_70686_1_ && EntityGhast.class != p_70686_1_;
    	}        
    }
    
    public boolean attackEntityFrom(DamageSource ds, float f1)
    {
    	if(this.getPassengers() != null && this.getPassengers() instanceof EntitySacrificialSkeleton) {
			
			EntitySacrificialSkeleton ss = (EntitySacrificialSkeleton)this.getPassengers();
			
			if(this.getAttackTarget() != null)
			{
				ss.setAttackTarget(this.getAttackTarget());
				}
			//rider cant dmg mount
			if(ds.getEntity() == this.getPassengers())
			{
				return false;
			}
    	}
    	return super.attackEntityFrom(ds, f1);
    	}
    
    public boolean getCanSpawnHere()
    {
    	int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(this.posZ);
        
        int j2 = (j-1);
        int j3 = (j-2);
        Block block = this.worldObj.getBlockState(new BlockPos(i, j2, k)).getBlock();
        Block block2  = this.worldObj.getBlockState(new BlockPos(i, j3, k)).getBlock();
        Biome bio = this.worldObj.getBiome(new BlockPos(i, j, k));
    		
        boolean additional = (this.dimension == w2theJungle.dimensionIdLost || bio == JungleBiomeRegistry.biomeJungleSwamp)
        		&& MathHelper.floor_double(this.getEntityBoundingBox().minY) <=  50
        		;//&& this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty();
        //using super. prevents every spawn since the spiders larger than a block its very unlikely to find a valid location
    	if ((block == Blocks.STONE || block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.MOSSY_COBBLESTONE))// && this.isValidLightLevel())
    		{return additional && this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL;}
    	else if (block == Blocks.LEAVES && block2 == Blocks.GRASS)
    		{return additional && this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL;}
    	else
    	{return additional && super.getCanSpawnHere();}
    	}
    
    public float getBlockPathWeight(int p_70783_1_, int p_70783_2_, int p_70783_3_)
    {
        return 0.0F;
    }
    	
    public boolean isValidLightLevel()
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
				this.worldObj.setSkylightSubtracted(10); //sets skylightsub
				l = this.worldObj.getLightFromNeighbors(pos); //calculated using new skylighsub
				this.worldObj.setSkylightSubtracted(i1); //resets it to original value, saved as i1
			}
			
			return this.worldObj.provider.getDimension() == w2theJungle.dimensionIdLost ? true : l <= this.rand.nextInt(8);
		}
    }
	
    public boolean isAIEnabled(){return true;}
    
	public void onLivingUpdate() {
		
		//this.setDead();
		if(this.attackTimer > 0) {--this.attackTimer;}
		//this.isDead = true;
		
		super.onLivingUpdate();
		}
	
	//this can be adjust to position riders further forward or back.
	//TODO id saY YOure supposed to update in the riders class... nope
    public void updatePassenger(Entity passenger)
    {    	
    	float rads = (float) (180F/Math.PI);
		float x = (float) (Math.sin(this.renderYawOffset/rads) * .1251); //both .1251
		float z = (float) (Math.cos(this.renderYawOffset/rads) * .1251);
		
        if (this.isPassenger(passenger))
        {
        	passenger.setPosition(this.posX + x,
            		this.posY + this.getMountedYOffset() + passenger.getYOffset(),
            		this.posZ - z);
        }
    }
	
    public double getMountedYOffset()
    {
        return (double)this.height * 0.55D;//(b1 ? 0.55D : 0.5D);
    }
    
    
    public boolean attackEntityAsMob(Entity ntt)
    {
    	float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;
        
        if(this.getPassengers() != null && this.isPassenger(ntt)) 
        {
        	return false;
        }
        
        if(this.attackTimer == 0 && this.getDistanceToEntity(ntt) < this.minRange)
        {        
        boolean flag = ntt.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0)
            {
                ntt.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            this.attackTimer = 30;
        }
        
        return flag;
        }
        else if(this.getDistanceToEntity(ntt) >= this.minRange)
        { 
        	//this.attackEntityWithRangedAttack((EntityLivingBase) ntt, 1f);
        	return false;
        }
        
        return false;
        
    }
    //par2 is a range comparison, between mob distance and max attack range, limited to 0.1-1.0.
    //this then reduces arrow dmg over shorter distances.
    //my web however has a fixed dmg regardless of distance. fixable.
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2)
    {
    	//add here alterations so web shoots from head. look at lennys boxes code.
    	EntityWeb entityWeb = new EntityWeb(this.worldObj, this);
    	
    	float rads = (float) (180F/Math.PI);
		float x = (float) (Math.sin(this.renderYawOffset/rads) * .651);
		float z = (float) (Math.cos(this.renderYawOffset/rads) * .651);
		
        double d0 = par1EntityLivingBase.posX - this.posX - x; //supposedly - x
        double d1 = par1EntityLivingBase.posY + (double)par1EntityLivingBase.getEyeHeight() - entityWeb.posY;// - 1.5100000023841858D - entityWeb.posY;
        double d2 = par1EntityLivingBase.posZ - this.posZ + z;
        
        float range = (float)Math.sqrt((d0 * d0) + (d1 * d1) + (d2 * d2)); 
        
        //System.out.println(z);
        
        if(range >= this.minRange && this.attackTimer == 0) {
        	
        	float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F;
        	entityWeb.setLocationAndAngles(this.posX - x, this.posY + this.getEyeHeight()*.35, this.posZ + z, this.rotationYaw, this.rotationPitch);
        	entityWeb.setThrowableHeading(d0, d1 + (double)f1, d2, 1.1F, 14.0F);
        	this.playSound(SoundEvents.BLOCK_SNOW_STEP, 2.0F, 1.5F / (this.getRNG().nextFloat() * 1.0F + 0.8F));
        	this.worldObj.spawnEntityInWorld(entityWeb);
        	
        	this.attackTimer = 40;
        	//web dmg handled in entityweb.
        	} else {
        		//this.attackEntity(par1EntityLivingBase, range);
        		}
        }
    
    
    
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

        if (this.worldObj.provider.getDimension() == w2theJungle.dimensionIdLost &&
        		this.worldObj.rand.nextInt(100) == 0)
        {
        	EntitySacrificialSkeleton sacskel = new EntitySacrificialSkeleton(this.worldObj);
            sacskel.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            sacskel.onInitialSpawn(difficulty, (IEntityLivingData)null);
            this.worldObj.spawnEntityInWorld(sacskel);
            sacskel.startRiding(this);
        }
        return livingdata;
    }
}
