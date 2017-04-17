package com.salvestrom.w2theJungle.mobs.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.mobs.entity.ai.EntityAIPatrol;

public class EntityStoneGolem extends EntityMob
{
	public Class[] classlist = new Class[]
			{
			EntityPlayer.class, EntityZombie.class, EntitySkeleton.class, EntityWitch.class,
			EntityVillager.class, EntityIronGolem.class, EntityEnderman.class
			};	
	
	protected int animationTimer;
	private int smashTimer;
	private int livingSoundTime;
	public int breakingtime;

	public boolean smashed; //=false;
	public boolean uppercut; //=false;
	public boolean righthook; //=false;
	
		public EntityStoneGolem(World par1World) {
		super(par1World);
		this.setSize(1.8F, 3.0F); //the collision box not model size
		this.stepHeight = 1.0F;
		this.isImmuneToFire = true;
        this.experienceValue = 25;
        //this.entityCollisionReduction = 0.4F;
		((PathNavigateGround)this.getNavigator()).setCanSwim(true);
		
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.35D, true)); //so the attack parts dont need a class...
		
		for(int i = 0; i < this.classlist.length; i++)
		{
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, classlist[i], 0, true, false, IMob.MOB_SELECTOR));		
		}
		
		this.tasks.addTask(2, new EntityAIPatrol(this, 0.3D));
		this.tasks.addTask(2, new EntityAIWander(this, 0.237D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIMoveTowardsTarget(this, 0.3D, 20.0F));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

	}
	public void applyEntityAttributes()
 {
	 super.applyEntityAttributes();
	 this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);//200
//     this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.27D);
    // this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
     this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.9D);
     this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(12.0D);
}
	
	public int getTotalArmorValue() {
		return 4;
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
			return l >= this.rand.nextInt(7);
		}
    }
	
  //removed .get can spawnhere method.
    
	 //bunch of golem-y type settings. is AI Enabled is set per final entityile.
    //check the entity ccode again. im sure theres a check/boolean that lets you flip the ai on/off. i wanted it for the trolls.
	public boolean isAIEnabled(){return true;}
	public boolean isEntityInsideOpaqueBlock(){return false;}
    public boolean canBreatheUnderwater(){return true;}
    public boolean handleWaterMovement(){this.inWater = false; return this.inWater;}
	protected boolean canDespawn() {return false;}
	
	// bunch of timers to control attacking and animation
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		if (this.animationTimer > 0) {
			--this.animationTimer;
		}
		if (this.smashTimer > 0) {
			--this.smashTimer;
		}

		this.walkEffect(5);

		if (this.livingSoundTime > 200) {
			this.playSound(SoundEvents.ENTITY_BLAZE_AMBIENT, 0.4F, 0.01F);
			this.livingSoundTime = 0;
		} else {
			++this.livingSoundTime;
		}

		this.destroyBlocksInAABB(this.getEntityBoundingBox());
		}

	public void walkEffect(int l) {

		if(MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) > 2.50000027790520E-7D)
		if(this.rand.nextInt(l) == 0)
		{		
			//System.out.println(MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ));
			
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double) this.getYOffset());
			int k = MathHelper.floor_double(this.posZ);
			
			IBlockState iblock = this.worldObj.getBlockState(new BlockPos(i, j, k));

			if (iblock.getMaterial() != Material.AIR)
			{	
                this.worldObj.spawnParticle(
                		EnumParticleTypes.BLOCK_CRACK,
                		this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width,
                		this.getEntityBoundingBox().minY + 0.1D,
                		this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width,
                		4.0D * ((double)this.rand.nextFloat() - 0.5D),
                		0.5D,
                		((double)this.rand.nextFloat() - 0.5D) * 4.0D,
                		new int[] {Block.getStateId(iblock)});
			}
		}		
	}
	
	private void destroyBlocksInAABB(AxisAlignedBB abba)
    {
		int waymaker = (int) ((float)this.breakingtime/240F * 10F); //why not just divide by 24? :P
		
		abba = abba.expand(0.5, 0, 0.5);
		
        int minX = MathHelper.floor_double(abba.minX);
        int minY = MathHelper.floor_double(abba.minY)-1;
        int minZ = MathHelper.floor_double(abba.minZ);
        int maxX = MathHelper.floor_double(abba.maxX);
        int maxY = MathHelper.floor_double(abba.maxY);
        int maxZ = MathHelper.floor_double(abba.maxZ);

        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z)
                {
                	BlockPos pos = new BlockPos(x, y, z);
        			IBlockState iblock = this.worldObj.getBlockState(pos);
                    Block block = iblock.getBlock();

                    if (block==Blocks.LEAVES || block== Blocks.WEB)
                    {
                    	this.worldObj.sendBlockBreakProgress(this.getEntityId(), pos, waymaker);
                    	if(this.breakingtime > 240)
                    	{
                    			this.breakingtime = 0;
                    			this.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.7F, 0.7F);
                    			
                          		for (int i = 0; i < 20; ++i)
                        		{
                        			//double d0 = this.rand.nextGaussian() * 0.02D;
                        			//double d1 = this.rand.nextGaussian() * 0.02D;
                        			//double d2 = this.rand.nextGaussian() * 0.02D;
                        			//double d3 = 10.0D;
                        			
                        			EnumParticleTypes part = EnumParticleTypes.BLOCK_DUST;
                        			
                        			this.worldObj.spawnParticle(part,
                        					x + (double)(this.rand.nextFloat() * 2.0F) - 1,
                        					y + (double)(this.rand.nextFloat()),
                        	            	z + (double)(this.rand.nextFloat() * 2) - 1,
                        	            	.2502D * ((double) this.rand.nextFloat() - 0.5D),
                        	            	0.25D,
                    						((double) this.rand.nextFloat() - 0.5D) * .2502D,
                                    		new int[] {Block.getStateId(iblock)});
                        			}
                    			
                    			this.worldObj.setBlockToAir(pos);
                    		}
                    		else
                    		{
                    			++this.breakingtime;
                    			}
                    		}
                    }
                }
        }

        return;
    }
	        
   protected int decreaseAirSupply(int par1) {return 5;}    
   
   //this is essentially a boolean, checking if an attack can be made.
   //i can find no indication that any mob actually uses this code...
    protected void attackEntity(Entity par1Entity, float par2)
    {
    }
       
	public boolean attackEntityAsMob(Entity par1Entity)
	    {
		//for smashing stuff!! it should be possible to alter the type and dmg of attack...
	      if (this.smashTimer == 0 && this.getDistanceToEntity(par1Entity) < 3)
	       {    	  
	    	  int rocky = this.rand.nextInt(6);
	    	  int t;
	    	  float d;
	    	  switch (rocky) {
	    	  case 0:
		    	  this.worldObj.setEntityState(this, (byte)30); //smash
		    	  t = 40;
		    	  d = 10;
		    	  break;
	    	  case 1:
		    	  this.worldObj.setEntityState(this, (byte)32); //uppercut 32
		    	  t = 30;
		    	  d = 7.5f;
	    		  break;
	    	  case 2:
		    	  this.worldObj.setEntityState(this, (byte)32); //uppercut
		    	  t = 30;
		    	  d = 7.5f;
	    		  break;
	    	  default:
	    		  this.worldObj.setEntityState(this, (byte)31); //righthook 31
	    		  t = 20;
		    	  d = 5f;
	    		  break;
		    	  }
	    	  
	    	  float dm = this.worldObj.getDifficulty().getDifficultyId();
	    	  //dmg is 5.5-6.5 for hook. 8-9 for upper and 10.5-11.5 for smash
	    	  boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (d + dm/2 + (float)this.rand.nextGaussian() * 0.5f));
	    	  if (flag)
	    		  {
	    		  par1Entity.motionY += 0.1000000059604645D;
	    		  par1Entity.motionZ += 0.1000000059604645D;
	    		  par1Entity.motionX += 0.1000000059604645D;
	    		  }
	    	  
	    	  this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 0.25F);
	    	  this.smashTimer = t;
	    
	    	  return flag;
	    	  } else {
	    		  return false;
	    		  }   
	      }

	public boolean attackEntityFrom(DamageSource dmg, float f)
	{
		if(super.attackEntityFrom(dmg, f))
		{
			if(dmg.getEntity() instanceof EntityTheWall)
			{
				return false;
				//this doesnt fully work because the wall does "crushing" dmg which has no source...
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	
	//byte 4 is attacking, 16 is zombies reverting, 11 is holding rose, 2 is hurt, 3 is dead.
	//15 is todo with witches throwing stuff. 12, 13, 14 are all villager related. mating hearts, angry, happy rep.
	// 6 and 7 are horse related, yet nothing in the class uses  the setentitystate commands. tameable class also uses these
	//10 is for sheep that are sheared - the sheep class does not set it anywhere.
	//18 is animal breeding. 8 is wolves shaking off after swimming//
	//
	@SideOnly(Side.CLIENT)
	    public void handleStatusUpdate(byte par1)
	    {

	        switch(par1){
	        case 30:
	        	smashed = true;
	        	uppercut = false;
	        	righthook = false;
	        	this.animationTimer = 20;
	        break;
	        case 32:
	        	uppercut = true;
	        	righthook = false;
	        	smashed = false;
	        	this.animationTimer = 20;
	    	   	break;
	        case 31:
	        	righthook = true;
	        	uppercut = false;
	        	smashed = false;
	        	this.animationTimer = 20;
	        	break;
	        default:

		    	super.handleStatusUpdate(par1);
	        	 }
	        }
	
	
    public int getMaxSpawnedInChunk(){return 1;}
	 
	 @SideOnly(Side.CLIENT)
	 public int getSmashTimer()
	 {return this.smashTimer;}
	 
	 @SideOnly(Side.CLIENT)
	 public int getAnimationTimer()
	 {return this.animationTimer;}

	 @SideOnly(Side.CLIENT)
	 public boolean getSmashed(){return this.smashed;}
	 @SideOnly(Side.CLIENT)
	 public boolean getUppercut(){return this.uppercut;}
	 @SideOnly(Side.CLIENT)
	 public boolean getRightHook(){return this.righthook;}
	 
	 
//	public boolean isMovementCeased() {if (this.hasAttacked = true); {return true;}}

	    protected SoundEvent getHurtSound()
	    {
	        return SoundEvents.BLOCK_STONE_BREAK;
	    }

	    protected SoundEvent getDeathSound()
	    {
	        return SoundEvents.ENTITY_BLAZE_DEATH;
	    }
	 
	 protected void playStepSound(BlockPos pos, Block blockIn)
	 {
		 this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 4.0F, 1.0F);
	 }
}
