package com.salvestrom.w2theJungle.mobs.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.mobs.entity.ai.EntityAIPatrol;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;

public class EntityGorrbat extends EntityAnimal {

	private static final DataParameter<Byte> CAN_FLY = EntityDataManager.<Byte>createKey(EntityGorrbat.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> CAN_JUMP = EntityDataManager.<Byte>createKey(EntityGorrbat.class, DataSerializers.BYTE);

    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity;
    private int aggroCooldown;
    public int prevAttackCounter;
    public int attackCounter;
    private int flightCd;
    public boolean takingOff;
    public int exhaustion;
    public EntityGorrbat parent;
    private int tally;
	int timedout;
	
	int livingSoundTime;
	public int mouthAnimTimer;
	
    public boolean landed;

	public EntityGorrbat(World p_i1738_1_) {
		super(p_i1738_1_);
		this.setSize(1.0F, 1.5F);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        
        //the numbers in movement ai are ultimately used as multipliers of the base movement speed set in attributes
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.SPECKLED_MELON, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIPatrol(this, 1.20D));
        this.tasks.addTask(5, new EntityAIWander(this, 1D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
       
        }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }
	
    protected void entityInit()
    {
        super.entityInit();
        
		this.dataManager.register(CAN_FLY, Byte.valueOf((byte)0));
        this.dataManager.register(CAN_JUMP, Byte.valueOf((byte)0));

    }
	
    public void onUpdate()
    {
    	//this.setDead();
        super.onUpdate();
        
        if(!this.isChild())
        {
        	this.parent = null;
        }
        
       // this.exhaustion = 0;
        
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(this.posZ);
        
        AxisAlignedBB axis = this.getEntityBoundingBox().expand(1, -1, 1).offset(0, -1, 0);
        
        //axis.offset(0, -1, 0);
        
        if(!this.landed)
        {
        	if(this.worldObj.getBlockState(this.getPosition().down()).getMaterial().isSolid())
        	{
        		this.landed = true;
        	}
        }
        else
        {
        	if(this.worldObj.getCollisionBoxes(this, axis).isEmpty()// && this.worldObj.getBlockState(this.getPosition()) != Blocks.AIR.getBlockState() 
        		|| this.fallDistance > 15)
        	{
        		this.landed = false;
        	}
        }
        		
        if(this.isChild())
        {
        	this.findMummy();
        	
        	if(this.parent != null)
        	{
        		this.landed = !this.parent.youCanFly();
        	}
        }
        
        
        if(!this.landed)
        {
        	if(this.ticksExisted%30 == 0)
        	{
            	this.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, .75f, 1.25f);
	    	}
        	//necessary to avoid loopss where entity flies to previous point,
        	//ttries to path onground, takes off ata n edge, then flies back tp prev loc.
        	this.getNavigator().clearPathEntity();
        	//this.posY = 80;
        	//this.takingOff = false;
        	if(this.ticksExisted%60 == 0 && this.exhaustion < 30)
        	{
        		++this.exhaustion;
        	}
        }
        else if(this.landed)
        {
        	//this.waypointX = this.posX;
    		//this.waypointY = this.posY;
    		//this.waypointZ = this.posZ;
    		
    		this.takingOff = false;
    		
        	if(this.ticksExisted%80 == 0 && this.exhaustion > 0)
        	{
        		--this.exhaustion;
        	}

        }
       
        if(!this.worldObj.isRemote)
        {

//        	(this.parent != null && this.parent.youCanFly()) || 
        	//using above makes the + motion and waypointy stuff constant.
        	
        if((this.parent != null && this.parent.youCanJump()) ||
        		(this.parent == null && ((this.flightCd < 50 && this.flightCd >= 5)
        				//|| (this.fleeingTick > 0 && this.fleeingTick < 20)
        				)
        				&& this.landed && this.exhaustion < 10))// && this.rand.nextInt(20) == 0))
        {
        
        	//this.setJumping(true);
        	this.motionY += 0.1 + (this.rand.nextFloat() * 0.035);

        	//this.waypointY = this.posY + 5;
        	this.waypointX = 0;//this.posX;
        	this.waypointZ = 0;//this.posZ;
        	//this.posY = 110;
        	this.takingOff = true;

        	if(this.flightCd%20 == 0) 
        	{
        		this.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.75f, 1.25f);

        	}
        } else {
        	//this.takingOff = false;
        }
        
        	if(this.flightCd == 0 && this.landed)
        	{
        		this.flightCd = this.rand.nextInt(200 + (this.exhaustion * 5)) + 200 + (this.exhaustion * 5);
        	}
        }

        if(this.flightCd > 0)
        		{
        			--this.flightCd;
        		}
/*
        	if(this.fleeingTick > 0)
        	{
        	--this.fleeingTick;
        	}
*/
        	
        	if(this.courseChangeCooldown > 0)
        	{
        		--this.courseChangeCooldown;
        	}
        	
        	
        if(!this.worldObj.isRemote && this.ticksExisted%20 == 0)
        {
          //System.out.println(this.exhaustion);
		//System.out.println(this.flightCd);
       	//System.out.println((this.worldObj.getCollidingBoundingBoxes(this, axis).isEmpty()));
        }
        
                
		if (this.livingSoundTime > 150 + this.rand.nextInt(100)) {

			this.worldObj.setEntityState(this, (byte)30);

			this.playSound(SoundEvents.ENTITY_ZOMBIE_PIG_AMBIENT, 3F, 0.75F);
			
			this.livingSoundTime = 0;
			
		} else	{
			++this.livingSoundTime;
		}
		
		if(this.timedout > 0)		{	--this.timedout;		}
		if(this.mouthAnimTimer > 0) {	--this.mouthAnimTimer;	}
		
		if (!this.worldObj.isRemote)
        {
            byte b1 = this.dataManager.get(CAN_FLY);
            byte b0 = (byte)(this.landed ? 1 : 0);

            byte j1 = this.dataManager.get(CAN_JUMP);
            byte j2 = (byte)(this.takingOff ? 1 : 0);
            
            if(j1 != j2) 
            {
            	this.dataManager.set(CAN_JUMP, Byte.valueOf((byte)(this.takingOff ? 1 : 0)));
            }
            //this seems unnecessary, with some modding of the above...
            //although it does prevent the game continually restating the value.
            if (b1 != b0)
            {
                this.dataManager.set(CAN_FLY, Byte.valueOf((byte)(this.landed ? 1 : 0)));
            }
        }

    }
    
    private void peterPan()
    {    	
    	double d0 = this.waypointX - this.posX;
    	double d1 = this.waypointY - this.posY;
    	double d2 = this.waypointZ - this.posZ;
    	double d3 = d0 * d0 + d1 * d1 + d2 * d2;
    	
    	double d4 = d3;
    	
    	if(!this.worldObj.isRemote)
    	{
    		//System.out.println(waypointY);
        	//System.out.println(timedout);
    		if (((d3 < 1.0D || d3 > 8000.0D) && this.courseChangeCooldown <= 0) && this.timedout == 0 && this.rand.nextInt(60) == 0)
    		{
    			this.timedout = 150;
    			
    			int heightBase = this.worldObj.provider.getDimension() == w2theJungle.dimensionIdLost ? 90 : 128;
    			
    			this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 21.0F); //was 16
    			this.waypointY = (heightBase - (this.exhaustion*1.25f)) + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 21.0F);
    			//last part of y is establishing a number between -1 and 1 then multi by 21. creating a variance of -21 to 21 in height
    			//this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 21.0F) - (this.exhaustion/1.5f);// - 5;
    			this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 21.0F);
    			
    			}
    		
    		if(this.isChild())
    		{
    			this.findMummy();
    			
    			if(this.parent != null)
    			{    			
    			this.waypointX = this.parent.posX;
    			this.waypointY = this.parent.posY;
    			this.waypointZ = this.parent.posZ;
    			}
    		}
    		
    		if (d3 >= 1.0E-4F)
    		{
    			d3 = (double)MathHelper.sqrt_double(d3);
    			
                if (d3 < 1.0F)
                {
                    d3 = 1.0F;
                }
                
                //System.out.println((this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)));
                
                if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
                {
                	d3 = 0.03/d3; //(equivalent to speed).
                	
                	this.landed = false;
                
                	this.motionX += d0 * d3; //x difference / crow flies distance.
                	this.motionY += d1 * d3;
                	this.motionZ += d2 * d3;
                    
                	this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
                	}
                else
                {
                	this.waypointX = this.posX;
                	this.waypointY = this.posY;
                	this.waypointZ = this.posZ;
                	
                	this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
                	
                	if(d4 > 1.0D && d4 < 8000.0D)
                	{
                		//System.out.println(d4);
                		//this.courseChangeCooldown = (int) (d4*10);
                		}
                	}
                }
    		}
    	}
    
    private boolean isCourseTraversable(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_)
    {
    	if(p_70790_3_ > 140 - this.exhaustion || p_70790_3_ < 93 - this.exhaustion)
    	{
    		return false;
    	}
    	
    	if(!this.worldObj.isRemote)
    	{
    		Biome bio = this.worldObj.getBiome(new BlockPos(p_70790_1_, 0, p_70790_5_));

    	//System.out.println((bio instanceof BiomeGenJungleM));
    	
    	if(bio == JungleBiomeRegistry.biomeJungleMountain || bio == Biomes.JUNGLE_HILLS || bio == Biomes.MUTATED_JUNGLE)
    	{
    	
    	double d4 = (this.waypointX - this.posX) / p_70790_7_;
    	double d5 = (this.waypointY - this.posY) / p_70790_7_;
    	double d6 = (this.waypointZ - this.posZ) / p_70790_7_;
    	
    	AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
    	
    	for (int i = 1; (double)i < p_70790_7_; ++i)
    	{
    		axisalignedbb.offset(d4, d5, d6);
    		
    		if (this.worldObj.getCollisionBoxes(this, axisalignedbb).contains(Blocks.GRASS)
    				|| this.worldObj.getCollisionBoxes(this, axisalignedbb).contains(Blocks.LEAVES))
    		{
    			return true;
    		}
    		else 
    			if(!this.worldObj.getCollisionBoxes(this, axisalignedbb.expand(2, 1, 2)).isEmpty())
    			{
    				return false;
    			}
    		}
    	}
    	else
    	{
    		return true;
    	}
    	}
    	
    	return true;
    }
    
    
    public void findMummy()
    {
            List list = this.worldObj.getEntitiesWithinAABB(this.getClass(), this.getEntityBoundingBox().expand(8.0D, 4.0D, 8.0D));
            EntityGorrbat entityanimal = null;
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityGorrbat entityanimal1 = (EntityGorrbat)iterator.next();

                if (entityanimal1.getGrowingAge() >= 0)
                {
                    double d1 = this.getDistanceSqToEntity(entityanimal1);

                    if (d1 <= d0)
                    {
                        d0 = d1;
                        entityanimal = entityanimal1;
                    }
                }
            }

            if (entityanimal == null)
            {
            	this.parent = null;
                return;
            }
            else if (d0 < 9.0D)
            {
            	this.parent = null;
                return;
            }
            else
            {
            	this.parent = entityanimal;
                return;
            }
        }
    
    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
    {
    	if(!this.landed)
    	{
    		if (this.isInWater())
    		{
    			this.peterPan();
    			this.moveRelative(p_70612_1_, p_70612_2_, 0.02F); //TODO seems to be called movedRelative now
    			this.moveEntity(this.motionX, this.motionY, this.motionZ);
    			this.motionX *= 0.800000011920929D;
    			this.motionY *= 0.800000011920929D;
    			this.motionZ *= 0.800000011920929D;
  		/*	}
    		else if (this.handleLavaMovement()) //gone?
    		{
    			this.moveRelative(p_70612_1_, p_70612_2_, 0.02F);
    			this.moveEntity(this.motionX, this.motionY, this.motionZ);
    			this.motionX *= 0.5D;
    			this.motionY *= 0.5D;
    			this.motionZ *= 0.5D;
 */   			}
    		else
    		{
    			float f2 = 0.91F;
    			
    			if (this.onGround)
    			{
    				f2 = this.worldObj.getBlockState(new BlockPos(this.posX, this.getEntityBoundingBox().minY - 1, this.posZ)).getBlock().slipperiness * 0.91F;
    				}
    			
    			float f3 = 0.16277136F / (f2 * f2 * f2);
    			
    			//this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
    			this.moveRelative(p_70612_1_, p_70612_2_, this.onGround ? 0.15F * f3 : 0.03F);
    			f2 = 0.91F;
    			
    			if (this.onGround)
    			{
    				f2 = this.worldObj.getBlockState(new BlockPos(this.posX, this.getEntityBoundingBox().minY - 1, this.posZ)).getBlock().slipperiness * 0.91F;
    				}
    			
    			this.peterPan();
    			
    			this.moveEntity(this.motionX, this.motionY, this.motionZ);
    			this.motionX *= (double)f2;
    			this.motionY *= (double)f2;
    			this.motionZ *= (double)f2;
    			}
    		
    		this.prevLimbSwingAmount = this.limbSwingAmount;
    		double d1 = this.posX - this.prevPosX;
    		double d0 = this.posZ - this.prevPosZ;
    		float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

    		if (f4 > 1.0F)
    		{
    			f4 = 1.0F;
    			}
    		
    		this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
    		this.limbSwing += this.limbSwingAmount;
    		}
    	else
    	{
    		super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
    		}
    	}
    
	@SideOnly(Side.CLIENT)
	public int getLivingSoundTime(){return this.mouthAnimTimer;}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte par1)
	{
		if(par1 == 30) {
			this.mouthAnimTimer = 50;
		} else {
			super.handleStatusUpdate(par1);
		}
	}
	
    protected void fall(float p_70069_1_) {}
    
    protected void updateFallState(double p_70064_1_, boolean p_70064_3_) {}
	
    @SideOnly(Side.CLIENT)
    public boolean youCanFly()
    {
        return this.dataManager.get(CAN_FLY) == 0;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean youCanJump()
    {
        return this.dataManager.get(CAN_JUMP) == 1;
    }
    
    public boolean allowLeashing()
    {
        return false;
    }

    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(this.posZ);
        
        BlockPos pos = new BlockPos(i, j, k);
        
        return j > 85
        		//&& (this.worldObj.getBlock(i, j - 1, k) == Blocks.grass
        		//|| this.worldObj.getBlock(i, j - 1, k) == Blocks.leaves
        		//|| this.worldObj.getBlock(i, j - 1, k) == Blocks.leaves2)
        		&& this.worldObj.getLight(pos) > 5
        		&& this.getBlockPathWeight(pos) >= 0.0F
        		&& this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox())
        		&& this.worldObj.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty()
        		&& !this.worldObj.containsAnyLiquid(this.getEntityBoundingBox());
    }

    public float getBlockPathWeight(BlockPos pos)
    {
        return -0.25F + this.worldObj.getLightBrightness(pos);
    }
    
	public boolean isAIEnabled() {return true;}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return new EntityGorrbat(this.worldObj);
	} 
	
    public boolean isBreedingItem(ItemStack p_70877_1_)
    {
        return p_70877_1_.getItem() == Items.SPECKLED_MELON;
    }
	
    protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        return 1;
    }


}
