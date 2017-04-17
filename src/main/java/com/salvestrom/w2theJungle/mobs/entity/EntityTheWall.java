package com.salvestrom.w2theJungle.mobs.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleAchievements;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.items.bookScale;
import com.salvestrom.w2theJungle.mobs.entity.ai.EntityAISecondaryAttack;
import com.salvestrom.w2theJungle.worldGen.JungleSaveWorld;

public class EntityTheWall extends EntityMob implements IRangedAttackMob {

	public Class[] classlist = new Class[] {EntityPlayer.class, EntityZombie.class, EntitySkeleton.class, EntityWitch.class,
			EntityVillager.class, EntityIronGolem.class, EntityEnderman.class};	

	private static final DataParameter<BlockPos> ATTACKER_POS = EntityDataManager.<BlockPos>createKey(EntityTheWall.class, DataSerializers.BLOCK_POS);
	protected static final DataParameter<Integer> ATTACK_STATUS = EntityDataManager.<Integer>createKey(EntityTheWall.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> WALL_SPAWN_TYPE = EntityDataManager.<Byte>createKey(EntityTheWall.class, DataSerializers.BYTE);

    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

	
	public int smashTimer;
	protected int animationTimer;
	public int breakingtime;
	private int livingSoundTime;
	public int shiftAttackTimer;
	public int tremorTimer;
	//for locking out additional golem spawns.
	public int hcheck;
	public int healthtimer;

	public int effectTimer;

	private EntityLivingBase attacker; //seems of no use?

	public float dm = this.worldObj.getDifficulty().getDifficultyId();


	private int rangedTimer;


	public EntityTheWall(World par1World) {
		super(par1World);
		
		this.setSize(2.5f, 4.5f);
		
        this.experienceValue = 75; //withers = 50
        this.stepHeight = 1.0F;
		this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;

		this.entityCollisionReduction = 0.85f;

		//last nukmbeer is min attack distance. 1st is speed. 2nd is a timer, duplicated 20 is a timer.
		//within the task, the third number is a range check.
		
		this.tasks.addTask(0, new EntityAISecondaryAttack(this, .40, 40, 40, 5f));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.273D, true)); //so the attack parts dont need a class...
		
		//TODO pretty big issue with targetting. if wall and other mobs are
		//getting wailed on by something other than their target
		//they cease attacking due to perma-hurt timer.
		for(int i = 0; i < this.classlist.length; i++)
		{
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, classlist[i], 0, true, false, IMob.MOB_SELECTOR));		
		}
		
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLenny.class, true, false));		
		
		this.tasks.addTask(5, new EntityAIWander(this, 0.237D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.275D, 30.0F));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, .50D));
		//this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}
	
	protected void entityInit()    {
		super.entityInit();
		//13 is spawn type. 23-25 target loc, 26 is attack type
		this.dataManager.register(WALL_SPAWN_TYPE, Byte.valueOf((byte) 0));
		this.dataManager.register(ATTACKER_POS, new BlockPos(this));
		//this.dataManager.register(24, Float.valueOf(0));
		//this.dataManager.register(25, Float.valueOf(0));
		this.dataManager.register(ATTACK_STATUS, Integer.valueOf(0));

	}

    protected void updateAITasks() {
    	super.updateAITasks();

    	int x = JungleSaveWorld.get(worldObj).wallLocX;
		int y = JungleSaveWorld.get(worldObj).wallLocY;
		int z = JungleSaveWorld.get(worldObj).wallLocZ;
		
		if(this.getWallSpawnType() == 1) {
			this.setHomePosAndDistance(new BlockPos(x, y, z), 18);
		}
		else
		{
			this.setHomePosAndDistance(new BlockPos(this.posX, this.posY, this.posZ), 18);
		}
		
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		
		//System.out.println(x);
		
    	
    }
	
	public boolean getCanSpawnHere() {		
		return super.getCanSpawnHere();		
		}
	
	public int getTotalArmorValue()
	{
		return !this.lowHealth() ? 7 : 5;
	}
	
	public boolean lowHealth()
	{
		return this.getHealth() < this.getMaxHealth()/2;
	}
	
	public boolean isMovementCeased()
	{
		return this.animationTimer > 0;
	}
	
	public void applyEntityAttributes()	 {		
		super.applyEntityAttributes();
	     this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.99D);
	     this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
	}
	
	public boolean isAIEnabled(){return true;}
	public boolean isEntityInsideOpaqueBlock(){return false;}
    public boolean canBreatheUnderwater(){return true;}
    public boolean handleWaterMovement(){this.inWater = false; return this.inWater;}
	protected boolean canDespawn() {return false;}
	
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		this.setAttackStatus();

		if(this.ticksExisted%20 == 0)
		{
//			System.out.println(this.getWallSpawnType());
		}

		
		if (this.effectTimer > 0)
		{
			this.moveForward = this.moveStrafing = 0;
			
			if(this.getAttackStatus() == 3)
			{
				this.smashEffect();
				if(this.effectTimer%8 == 0)
				{
					int d = 8 - this.effectTimer/8; // timer 56 = 1. timer 8 = 7.
					this.tremble(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(4, 0, 4)), d);
					this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 10.0F, -.001F);
					}
				}
			else if(this.getAttackStatus() == 4)
			{
				this.pullIn(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(10, 0, 10)));
				}
			--this.effectTimer;
			}
		
		if (this.smashTimer > 0) {--this.smashTimer;}
		if (this.rangedTimer > 0) {--this.rangedTimer;}
		
		if (this.tremorTimer > 0) {--this.tremorTimer;}

		if (this.animationTimer > 0) {--this.animationTimer;}
		if (this.shiftAttackTimer > 0) {--this.shiftAttackTimer;}

		if (this.livingSoundTime > 200 + this.rand.nextInt(100))
		{
			this.playSound(SoundEvents.ENTITY_BLAZE_AMBIENT, 0.4F, 0.01F);
			this.livingSoundTime = 0;
			}
		else
		{
			++this.livingSoundTime;
		}

		this.walkEffect(3);
		this.destroyBlocksInAABB(this.getEntityBoundingBox());
		//this.setDead();
		
	    // this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5D);
		
		this.calculateHealth();

		if(!this.worldObj.isRemote && this.healthtimer == 0)
		{
		
			if((this.getHealth() <= this.getMaxHealth() * 0.25 && this.hcheck == 2)
				|| (this.getHealth() <= this.getMaxHealth() * 0.5 && this.hcheck == 1)
				|| (this.getHealth() <= this.getMaxHealth() * 0.75 && this.hcheck == 0))
		{
			this.healthtimer = 100;
			
			this.hcheck += 1;
			
			EntityStoneGolem aid = new EntityStoneGolem(this.worldObj);
			aid.setLocationAndAngles((double)this.posX-5+this.rand.nextInt(10), (double)this.posY+1, (double)this.posZ-5+this.rand.nextInt(10), 0.0F, 0.0F);
			aid.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(this)), (IEntityLivingData)null);
			
			this.worldObj.spawnEntityInWorld(aid);
			aid.setAttackTarget(this.getAttackTarget());
			this.playSound(SoundEvents.BLOCK_STONE_BREAK, 0.5f, 1);
			aid.spawnExplosionParticle();
			}
		}
		
		if(this.healthtimer > 0)	{--this.healthtimer;}
		
		if((this.getHealth() >= this.getMaxHealth() * 0.25 && this.hcheck == 3)
				|| (this.getHealth() >= this.getMaxHealth() * 0.5 && this.hcheck == 2)
				|| (this.getHealth() >= this.getMaxHealth() * 0.75 && this.hcheck == 1))
		{
			this.hcheck -= 1;
			if(this.hcheck < 0)
			{ 
				this.hcheck = 0;
			}
		}
	}
	
	private void destroyBlocksInAABB(AxisAlignedBB abba)
    {
		int waymaker = (int) ((float)this.breakingtime/40F * 10F);
		
        int minX = MathHelper.floor_double(abba.minX);
        int minY = MathHelper.floor_double(abba.minY)-1;
        int minZ = MathHelper.floor_double(abba.minZ);
        int maxX = MathHelper.floor_double(abba.maxX);
        int maxY = MathHelper.floor_double(abba.maxY);
        int maxZ = MathHelper.floor_double(abba.maxZ)+1;

        for (int x = minX; x <= maxX; ++x)
        {
        	for (int y = minY; y <= maxY; ++y)
        	{
        		for (int z = minZ; z <= maxZ; ++z)
        		{
        			
                	BlockPos pos = new BlockPos(x, y, z);

                	IBlockState iblock = this.worldObj.getBlockState(pos);
                	Block block = iblock.getBlock();
        			
        			if (block==Blocks.LEAVES || block==Blocks.WEB)
        			{
        				this.worldObj.sendBlockBreakProgress(this.getEntityId(), pos, waymaker);
        				if(this.breakingtime > 40)
        				{
        					this.breakingtime = 0;
        					this.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.7F, 0.7F);
        					
        					for (int i = 0; i < 20; ++i)
                    		{
                    			double d0 = this.rand.nextGaussian() * 0.02D;
                    			double d1 = this.rand.nextGaussian() * 0.02D;
                    			double d2 = this.rand.nextGaussian() * 0.02D;
                    			double d3 = 10.0D;
                    			
                    			this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK,
                    					x + (double)(this.rand.nextFloat() * 2.0F) - 1,
                    					y + (double)(this.rand.nextFloat() * 2) - 1,
                    	            	z + (double)(this.rand.nextFloat() * 2) - 1,
                    	            	//d0, d1, d2);
                    	            	4.0D * ((double) this.rand.nextFloat() - 0.5D), 0.5D,
                						((double) this.rand.nextFloat() - 0.5D) * 4.0D,
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
	
	public void walkEffect(int l) {

		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.230000027790520E-7D
				&& this.rand.nextInt(l) == 0) {
		
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double) this.getYOffset() );
			int k = MathHelper.floor_double(this.posZ);
			
			IBlockState iblock = this.worldObj.getBlockState(new BlockPos(i, j, k));
        	Block block = iblock.getBlock();

			if (iblock.getMaterial() != Material.AIR) {
				
				this.worldObj.spawnParticle(
						EnumParticleTypes.BLOCK_CRACK,
						this.posX + ((double) this.rand.nextFloat() - 0.5D)	* (double) this.width,
						this.getEntityBoundingBox().minY + 0.1D,
						this.posZ + ((double) this.rand.nextFloat() - 0.5D)	* (double) this.width,
						4.0D * ((double) this.rand.nextFloat() - 0.5D), 0.5D,
						((double) this.rand.nextFloat() - 0.5D) * 4.0D,
                		new int[] {Block.getStateId(iblock)});
			}
		}
		
	}

	public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_)
	{
	    	if(!this.worldObj.isRemote)
	    	{
//	    		this.moveStrafing = this.moveForward = 0.0f;

	    		this.motionX *= 0.5f;
	    		this.motionY *= 0.5f;
	    		this.motionZ *= 0.5f;
	    	}
	}

	public void attackEntity(Entity ntt, float par2)
    {
        if (//par2 < 3f && 
        		ntt.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY && ntt.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY)
        {
            this.attackEntityAsMob(ntt);
        }
    }
	
	//null target = 0, range = 1, melee = 2, tremor = 3, yank = 4, whirl = 5

	public boolean attackEntityAsMob(Entity ntt)
	{
		float dmg;
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(3, 0, 3));

		if(this.shiftAttackTimer <= 0 && this.lowHealth())
		{
			dmg = 3;
			this.whirl(list);
			this.worldObj.setEntityState(this, (byte)30);
			this.dataManager.set(ATTACK_STATUS, 5);
			}
		else
			if(this.tremorTimer <= 0 && list != null && this.animationTimer == 0)
			{//&& !this.lowHealth() 
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
				this.moveForward = this.moveStrafing = 0;
			
				dmg = 2;
				
				this.worldObj.setEntityState(this, (byte)33);
				this.dataManager.set(ATTACK_STATUS, 3);
				}
			else
				if (this.smashTimer <= 0)
				{
					dmg = 5;
					this.worldObj.setEntityState(this, (byte)31);
			        this.dataManager.set(ATTACK_STATUS, 2);
					}
				else
				{
					dmg = 0; return false;
					}

		float xtra = (this.worldObj.getDifficulty().getDifficultyId() * 0.11f) + (float)(this.rand.nextGaussian() * 0.25);
		if(dmg > 0)
		{
			dmg = xtra + dmg + this.rand.nextInt((int)dmg-1);
		}
				
		boolean flag = ntt.attackEntityFrom(DamageSource.causeMobDamage(this), dmg * 0.9f);

		if (flag)
		{
			ntt.hurtResistantTime = 0;
			ntt.attackEntityFrom(DamageSource.generic, dmg * 0.1f + 1);
			
			if(this.getAttackStatus() == 5) //whirl
			{
				ntt.motionY += 0.2000000059604645D;
				ntt.motionZ += 0.4000000059604645D;
				ntt.motionX += 0.4000000059604645D;
				this.animationTimer = 20;
				this.shiftAttackTimer = 240;
				this.resetAttackTimers();
				
				}
			else if(this.getAttackStatus() == 2) //punch
				{
					int a = 60;
			        this.resetAttackTimers();
					this.animationTimer = 20;
					this.shiftAttackTimer += a;
					this.tremorTimer += a;
					this.smashTimer = a;
					this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 2.0F, 0.025F);
					}
			else
				if(this.getAttackStatus() == 3) //tremor
				{
					this.tremorTimer = 240;
					this.effectTimer = 60;
					this.smashTimer = 60;
					this.shiftAttackTimer = this.shiftAttackTimer < 80 ? 80 : this.shiftAttackTimer;
				}
		}
		else
		{
			//this.attackEntityWithRangedAttack((EntityLivingBase) ntt, 1);
		}
		
		return flag;
	    }
	
    public void attackEntityWithRangedAttack(EntityLivingBase nttlb, float rangeMod)
    {
    	float acc = 13 - this.worldObj.getDifficulty().getDifficultyId() * 4;
    	EntityArrow entityarrow = new EntityTippedArrow(this.worldObj, this);
    	EntityArrow entityarrow2 = new EntityTippedArrow(this.worldObj, this);
    	EntityArrow entityarrow3 = new EntityTippedArrow(this.worldObj, this);
    	//1.6 above is omph. lower it and arrows drop to the ground faster.
    	//final number is accuracy.

    	double d0 = nttlb.posX - this.posX;
        double d1 = nttlb.posY + (double)(nttlb.getEyeHeight() / 3) - entityarrow.posY;
        double d2 = nttlb.posZ - this.posZ;
        
        float range = (float)Math.sqrt((d0 * d0) + (d1 * d1) + (d2 * d2)); 
        
        EntityLivingBase nL = nttlb;
        
        List moblist = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(2, 0, 2));
        Iterator it = moblist.iterator();
        while(it.hasNext())
        {
        	if(it.next() instanceof EntityStoneGolem)
        	{
        		it.remove();		
        		}
        	}
        //to allow wall to respond to swarming mobs while targetting is fixed on a ranged mob
   		if(this.shiftAttackTimer <= 0 && moblist.size() > 3 && this.getAttackStatus() != 3)
   		{
   			this.whirl(moblist);
   			this.worldObj.setEntityState(this, (byte)30);
   			        	
			this.animationTimer = 20;
			this.shiftAttackTimer = 240;
	        this.dataManager.set(ATTACK_STATUS, 5);
			this.resetAttackTimers();
        }
        
        if(this.shiftAttackTimer <= 0
        		&& !this.lowHealth()
        		&& range > 10)
        {	
        	this.worldObj.setEntityState(this, (byte)32);
	        this.dataManager.set(ATTACK_STATUS, 4);
        	this.shiftAttackTimer = 240;
        	this.tremorTimer = this.tremorTimer < 80 ? 80 : this.tremorTimer;
        	this.resetAttackTimers();
        	this.animationTimer = 40;
        	this.effectTimer = 60;
        	}
        else

        if(this.rangedTimer <= 0 &&	range >= 5)
        {       
        	this.worldObj.setEntityState(this, (byte)34);
            this.dataManager.set(ATTACK_STATUS, 1);
        	
            
            double d = (double)(rangeMod * 7.0F) + this.rand.nextGaussian() * 0.25D
            		+ (double)((float)this.worldObj.getDifficulty().getDifficultyId() * 0.11)
            	; //20, 15 does 1.5 hearts per hit, through full original obs armour
            // 12 does 1 heart. 25 armour from obs.
            // about 4 hearts in iron armour.
            // diamond about the same as obs.
            
            float rads = (float) (180F/Math.PI);
    		float x = (float) -(Math.cos(this.renderYawOffset/rads));
    		float z = (float) -(Math.sin(this.renderYawOffset/rads));
    		
            double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

    		entityarrow.setDamage(d*1.52);
            entityarrow.setPosition(this.posX + (x*1.5), this.posY + this.getEyeHeight(), this.posZ + (z*1.5));
            entityarrow.setThrowableHeading(d0 - (x*3), d1 + d3 * 0.2, d2 - (z*3), 1.6f, acc);

            entityarrow2.setDamage(d*1.52);
        	entityarrow2.setPosition(this.posX + (x), this.posY + this.getEyeHeight()-0.5, this.posZ + (z));
            entityarrow2.setThrowableHeading(d0 - (x*1), d1 + d3 * 0.2, d2 - (z*1), 1.6f, acc);

            entityarrow3.setDamage(d*1.52);
        	entityarrow3.setPosition(this.posX + (x*.75), this.posY + this.getEyeHeight()+0.5, this.posZ + (z*.75));
            entityarrow3.setThrowableHeading(d0 - (x*0.2), d1 + d3 * 0.2, d2 - (z*.2), 1.6f, acc);


        	this.playSound(SoundEvents.BLOCK_PISTON_EXTEND, 0.6f, 0.6f);
        	this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        	this.resetAttackTimers();

        	{
            	this.worldObj.spawnEntityInWorld(entityarrow);
            	this.worldObj.spawnEntityInWorld(entityarrow2);
            	this.worldObj.spawnEntityInWorld(entityarrow3);
        	}
        }
        else
        {
//        	this.attackEntity(nttlb, range);
        }
        
    }
    
    public void tremble(List list, int d)
    {
		this.moveForward = this.moveStrafing = 0;
    	
    	Iterator iterator = list.iterator();
        
        while (iterator.hasNext())
        {
        	Entity entity = (Entity)iterator.next();
        	
        	if (entity instanceof EntityLivingBase && !(entity instanceof EntityStoneGolem))
        	{
        		float d2 = d + (this.dm*1.5f) - 0.5f;
        		//make the ability longer with less freuernt hits??
        		// this does 0.5 dmg on easy, 1.5 on normal and 2.5on hard. + 1-8. get out by 3 secs or take a big hit.
        		entity.attackEntityFrom(DamageSource.causeMobDamage(this), d2);
        		/*
        		System.out.println(d2); //final hit on normal is 8.5 thru unenchanted iron. test with diamond and enchants
        		System.out.println("tremble");
        		System.out.println(d);
*/
        	}
    	}
    }
    
    public void pullIn(List list)
    {
		this.moveForward = this.moveStrafing = 0;
    	
    	int j = list.size();

    	for(int l = 0; l < j; ++l)
    	{    	  
    		Entity entity = (Entity)list.get(l);
    	
           	if(entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
            {
            }
           	else if (entity instanceof EntityLivingBase && !(entity instanceof EntityStoneGolem) && this.getDistanceToEntity(entity) > 4)
           	{
            	double d0 = this.posX - entity.posX;
                double d1 = this.getEntityBoundingBox().minY + (double)(this.height / 3.0F) - this.posY;
                double d2 = this.posZ - entity.posZ;
                double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

                if (d3 >= 1.0E-7D)
                {
                    float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
                    float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
                    double d4 = d0 / d3;
                    double d5 = d2 / d3;
                    double d6 = Math.cos(this.effectTimer*1.5)*d3*0.1;
                    
                    if(this.worldObj.getBlockState(new BlockPos(
                    		(entity.posX + d4),
                    		(this.posY+0.51+d6),
                    		(entity.posZ + d5))).getMaterial() == Material.AIR)
                    {
                    	entity.setLocationAndAngles(entity.posX + d4, this.posY+0.51+d6, entity.posZ + d5, f2, f3);
                    }
                }
                if(this.effectTimer%15 == 0)
                {
                	entity.attackEntityFrom(DamageSource.causeIndirectDamage(this, this), this.dm); //0 1 2 3
            		this.playSound(SoundEvents.BLOCK_GRASS_BREAK, .8F, .5F);
                }
            }
    	}
    }

    
    public void whirl(List list) {
    	
    	double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
        double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
        Iterator iterator = list.iterator();
       
        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();

            if (entity instanceof EntityLivingBase)
            {
                double d2 = entity.posX - d0;
                double d3 = entity.posZ - d1;
                double d4 = d2 * d2 + d3 * d3;
                entity.addVelocity(d2 / d4 * 2.0D, 0.20000000298023224D, d3 / d4 * 2.0D); //org 4.0D
        		this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 10.0F, -10000F);
        		
        		float dm2 = (this.dm/2 * this.dm/2) + this.dm + 3; //6 normal, 10.5 on hard
            	entity.attackEntityFrom(DamageSource.causeMobDamage(this), dm2);
            	
            	//System.out.println(dm2);
            }
        }
    }
    
    private void smashEffect() {
    	
    	int i = MathHelper.floor_double(this.posX);
    	int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double) this.getYOffset());
    	int k = MathHelper.floor_double(this.posZ);
    	
    	//if(//this.worldObj.isRemote	&&    			this.effectTimer > 0)
    	{
    		int a = 4;
    		for(int x = -a; x < a; ++x)
    			for(int z = -a; z < a; ++z) {
    				
    				if(x*x + z*z < 16) {
    					
    					BlockPos blockpos = new BlockPos(i+x, j, k+z);
                        IBlockState iblock = this.worldObj.getBlockState(blockpos);
                        Block block = iblock.getBlock();
    					    					
    					if (iblock.getMaterial() != Material.AIR) {
    					
    					double px = this.posX + x + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width;
    					double pz = this.posZ + z + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width;
    					    					
    					this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, px, j+1 + 0.1D, pz,
    							4.0D * ((double) this.rand.nextFloat() - 0.5D), 0.5D,
    							((double) this.rand.nextFloat() - 0.5D) * 4.0D,
    	                		new int[] {Block.getStateId(iblock)});

    					}
    				}
    			}
    	}
    }


    public void resetAttackTimers() {
	   this.smashTimer = 60;
	   this.rangedTimer = 60;
   }
    
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
		if (this.isEntityInvulnerable(p_70097_1_))
		{
			return false;
		}
		else if (p_70097_1_ == DamageSource.drown)
        {
            return false;
        }
        else
        {
            Entity entity;

            if (this.lowHealth())
            {
                entity = p_70097_1_.getSourceOfDamage();

                if (entity instanceof EntityArrow || entity instanceof EntityThrowable)
                {
                    return false;
                }
            }
            
            entity = p_70097_1_.getEntity();

            //if (entity != null && !(entity instanceof EntityPlayer) && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getCreatureAttribute() == this.getCreatureAttribute())
            {
            //    return false;
            }

            if(entity instanceof EntityPlayer && this.getDistanceToEntity(entity) > 18)
            {
            	return false;
            }
            }
            	
		return super.attackEntityFrom(p_70097_1_, p_70097_2_);
	}
    
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if(this.deathTime%(this.rand.nextInt(7)+5) == 0)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY+3, this.posZ, 0.0D, 0.0D, 0.0D);
            this.playSound(SoundEvents.ENTITY_FIREWORK_BLAST, 0.5f, 1);
        	this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 10.0F, -0.1F);
        }
        else if(this.deathTime%(this.rand.nextInt(3)+10)==0)
        {
        	this.playSound(SoundEvents.BLOCK_STONE_BREAK, 6f, 0.2f);
        	
        	 if(!this.worldObj.isRemote && this.rand.nextInt(4) < 3)
        	 {
        		 float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
        		 float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
        		 float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
        		 
        		 this.worldObj.spawnEntityInWorld(
        				 new EntityItem(this.worldObj,
        						 (double)(this.posX + f), (double)(this.posY + f1 + 1f), (double)(this.posZ + f2),
        						 new ItemStack(Blocks.MOSSY_COBBLESTONE, 1)));
        	 }        	
        }

        int i;
        
        if (this.deathTime >= 80 && this.deathTime%5 == 0)
        {  
            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.canDropLoot() && this.worldObj.getGameRules().getBoolean("doMobLoot"))
            {
                i = (int)(this.getExperiencePoints(this.attackingPlayer) / 3);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }

                }
            }
        
        if (this.deathTime == 120)
        {
        	this.playSound(SoundEvents.ENTITY_BLAZE_AMBIENT, 4F, 0.01F);
            this.setDead();

            if (!this.worldObj.isRemote && this.canDropLoot() && this.worldObj.getGameRules().getBoolean("doMobLoot"))
            {
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    
                    float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
                    float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
                    float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
                    
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX + f, this.posY + f1 + 2f, this.posZ + f2, j));
                }
                
                if(this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getMaterial().isSolid())
            	{
            		this.worldObj.setBlockState(new BlockPos(this.posX, this.posY, this.posZ), JungleBlocks.wallSkull.getDefaultState());
            		}
            	else
            	{
            		this.entityDropItem((new ItemStack(JungleBlocks.wallSkull, 1)), 0.0F);
            		}
            }

            
            for (i = 0; i < 20; ++i)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
    }


    public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);
        
        if (!this.worldObj.isRemote)
        {
            Iterator<EntityPlayer> iterator = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(30.0D, 30.0D, 30.0D)).iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();
                entityplayer.addStat(JungleAchievements.slainWall);
            }
            
            new bookScale();
            ItemStack stack = bookScale.northScale;
            this.entityDropItem(stack, 0.0f);

            if(this.getWallSpawnType() == 1)
            {
            	JungleSaveWorld.get(worldObj).wallSpawnCount(0);
            	}
            }
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
    	for(int i = 0; i < 3; ++i)
    	{
    		float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
    		float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
    		float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
    		
    		this.worldObj.spawnEntityInWorld(
    				new EntityItem(this.worldObj,
    						(double)(this.posX + f), (double)(this.posY + f1 + 2.5f), (double)(this.posZ + f2),
    						new ItemStack(Blocks.MOSSY_COBBLESTONE, 1)));
    	}
    }
    
    
	@SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte par1)
    {
		switch(par1)
		{
		case 30:
			//System.out.println("whirled");
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY+3, this.posZ, 0.0D, 0.0D, 0.0D);
        	this.animationTimer = 20;
        	break;
        	case 32:
        		//    	    System.out.println("yanked");
        		this.animationTimer = 40;
        		this.effectTimer = 60;
        		break;
        		case 31:
        			//System.out.println("smashed");
        			this.animationTimer = 20;
        			break;
        			case 33:
        				//System.out.println("tremor");
        				this.animationTimer = 60;
        				this.effectTimer = 60;
        				break;
        				case 34:
        					//System.out.println("tank missle");
        					this.animationTimer = 10;
        					default:
        						super.handleStatusUpdate(par1);
        						}
		}
    
    //handle health value according to number of players in proximity (not counting a single player).
    //actual health is always maintained as a percentage of total when total changes.  	
    private void calculateHealth() {

		float priorHealth = this.getMaxHealth();
		
    	List lt2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(30, 30, 30));
    	
    	int additional = lt2.size() == 0 ? 0 : (lt2.size()-1)*150;
    	
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200+additional); //200
    	this.setHealth((this.getHealth()/priorHealth)*this.getMaxHealth());
    	
    	//add target check?? speed up out of combat?
    	//move to update??
    	if(this.ticksExisted%40 == 0)
    	{
    		float h = lt2.size() > 0 ? lt2.size() : 1;
    		this.heal(h);
    	}
    }
    
	public int attackStatus;

	public int getAttackStatus()	{
		return this.dataManager.get(ATTACK_STATUS);
	}

	public void setAttackStatus() {

		if(!this.worldObj.isRemote) {
			if(this.getAttackTarget() == null && this.animationTimer == 0) {
			this.dataManager.set(ATTACK_STATUS, 0);
			}
		}
	}
	
    protected SoundEvent getHurtSound()
    {
        return SoundEvents.BLOCK_STONE_BREAK;
    }
    
	 protected void playStepSound(BlockPos pos, Block blockIn)
	 {
		 this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 10.0F, -10F);
	 }
	 
     public boolean isNonBoss()
     {
         return false;
     }

	public BlockPos getAttackerPosX()
	{
		return this.dataManager.get(ATTACKER_POS);
	}
	/*
	public float getAttackerPosY() {
		return this.dataManager.get(24);
	}
	public float getAttackerPosZ() {
		return this.dataManager.get(25);
	}
*/
    
	 @SideOnly(Side.CLIENT)
	 public int getAnimationTimer()
	 {return this.animationTimer;}
	 
	    public int getWallSpawnType()
	    {
	        return this.dataManager.get(WALL_SPAWN_TYPE);
	    }

	    //only set by a wall spawned via village generation. all other walls are 0 by default.
	    public void setWallSpawnType(int p_82201_1_)
	    {
	        this.dataManager.set(WALL_SPAWN_TYPE, Byte.valueOf((byte)p_82201_1_));
//	        this.isImmuneToFire = p_82201_1_ == 1;
	    }

	    public void addTrackingPlayer(EntityPlayerMP player)
	    {
	        super.addTrackingPlayer(player);
	        this.bossInfo.addPlayer(player);
	    }

	    public void removeTrackingPlayer(EntityPlayerMP player)
	    {
	        super.removeTrackingPlayer(player);
	        this.bossInfo.removePlayer(player);
	    }
	    
	    
	    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
	    {
	        super.readEntityFromNBT(p_70037_1_);

	        if (p_70037_1_.hasKey("WallSpawnType", 99))
	        {
	            byte b0 = p_70037_1_.getByte("WallSpawnType");
	            this.setWallSpawnType(b0);
	        }
	    }

	    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
	    {
	        super.writeEntityToNBT(p_70014_1_);
	        p_70014_1_.setByte("WallSpawnType", (byte)this.getWallSpawnType());
	    }
}
