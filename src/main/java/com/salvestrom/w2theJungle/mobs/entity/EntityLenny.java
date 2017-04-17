package com.salvestrom.w2theJungle.mobs.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleAchievements;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.items.bookScale;
import com.salvestrom.w2theJungle.mobs.entity.ai.EntityAISecondaryAttack;

public class EntityLenny extends EntityMob implements IEntityMultiPart, IRangedAttackMob { //temp. interested to see if zombies spawn when lenny is attacked

	private static final DataParameter<Byte> LENNY_SPAWN_TYPE = EntityDataManager.<Byte>createKey(EntityLenny.class, DataSerializers.BYTE);
	private static final DataParameter<BlockPos> ATTACKER_POS = EntityDataManager.<BlockPos>createKey(EntityLenny.class, DataSerializers.BLOCK_POS);

    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

    //getLookVec - might be useful for eye beam. getClosestPlayer
	//spawn ancient skeletons when trampling... or something.
	
//lennys other hit boxes	
	public Class[] classlist = new Class[] {
			EntityLizardmanBase.class, EntityStoneGolem.class, EntityWitch.class,
			EntityVillager.class, EntityIronGolem.class, EntityEnderman.class, EntityMistSpider.class,
			};	

	public int animationTimer;
	private int effectTimer;
	public int tremorTimer;
	public int roarAnimationTimer;

	public int attackTimer;
	public int smashTimer;
	public int livingSoundTime;
	public int breakingtime;
	//reduce since they cant be attached to model parts
	private EntityDragonPart[] lennyNaughtyParts;
	public EntityDragonPart lennyNeck;
    public EntityDragonPart lennyTail4;
    public EntityDragonPart lennyTail5;
    
    public boolean slowed;
    public float minRange = 6f;
    public boolean isRoaring;
    public int roarTimer;
    public int attacktype; // = 3;
    public int add;

    public Entity attacker;
	//do not have counters count down. count up, compare against a fixed value, take action, reset. (reset timer after animation??
	    
    //setting a homeposition also requires the move to restriction entit file
	public EntityLenny(World p_i1745_1_) {
		super(p_i1745_1_);
		
		this.setSize(2.5F, 4F); //the collision box not model size. w/h+
        
		//this.minRange = 6.5f;
        
		this.stepHeight = 1.5F;
		this.isImmuneToFire = true;
        this.experienceValue = 150;
        this.ignoreFrustumCheck = true;
        this.entityCollisionReduction = 0.95F;

        this.setPathPriority(PathNodeType.WATER, -1.0F);
        ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
		

		this.lennyNaughtyParts = new EntityDragonPart[]	{				
				this.lennyNeck = new EntityDragonPart(this, "neck", 2.5F, 1.5F),
						this.lennyTail4 = new EntityDragonPart(this, "tailSeg4", 2.0F, 1.0F),
        				this.lennyTail5 = new EntityDragonPart(this, "tailSeg5", 2.0F, 1.0F),
        				};
		
       
	}
	
	public void entityInit()
	{
		super.entityInit();
		this.dataManager.register(LENNY_SPAWN_TYPE, Byte.valueOf((byte) 0));
		this.dataManager.register(ATTACKER_POS, new BlockPos(this));
	}
	
	public void initEntityAI()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAISecondaryAttack(this, 0.3d, 40, 40, this.minRange));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.33D, false));
		this.tasks.addTask(2, new EntityAIWander(this, 0.3D));
		
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIMoveTowardsTarget(this, 0.4D, 30.0F));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 0.50D));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, 0, true, false, IMob.MOB_SELECTOR));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityBat>(this, EntityBat.class, 0, true, false, IMob.MOB_SELECTOR));

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityLizardmanBase>(this, EntityLizardmanBase.class, 0, true, false, IMob.MOB_SELECTOR));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityStoneGolem>(this, EntityStoneGolem.class, 0, true, false, IMob.MOB_SELECTOR));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityWitch>(this, EntityWitch.class, 2, true, false, IMob.MOB_SELECTOR));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, 2, true, false, IMob.MOB_SELECTOR));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityIronGolem>(this, EntityIronGolem.class, 2, true, false, IMob.MOB_SELECTOR));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityEnderman>(this, EntityEnderman.class, 2, true, false, IMob.MOB_SELECTOR));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityMistSpider>(this, EntityMistSpider.class, 1, true, false, IMob.MOB_SELECTOR));
	}
	/* these 3 overwrites are from an mcforge post. the hit box works but does not show up under f3+b
	 * mob seems pinned in by its own hitbox. the box has no yaw(they never do, which is prolly why
	 * you can only set height/width to match.
	 */
//	public AxisAlignedBB getBoundingBox() {		return this.getEntityBoundingBox();		}
	
//	public AxisAlignedBB getCollisionBox(Entity par1Entity) {		return this.getEntityBoundingBox();	}
	/*
	public void setPosition(double par1, double par2, double par3) {
		AxisAlignedBB b = this.getEntityBoundingBox();
		double boxSX = b.maxX - b.minX;
		double boxSY = b.maxY - b.minY;
		double boxSZ = b.maxZ - b.minZ;
		this.getEntityBoundingBox().setBounds(posX - boxSX/2D, posY, posZ - boxSZ/2D, posX + boxSX/2D, posY + boxSY, posZ + boxSZ/2D);
	}*/
	
	public void applyEntityAttributes()
	 {
		 super.applyEntityAttributes();

	     this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
	     this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	     this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
	}

	public int getTotalArmorValue()
	{
		return this.lowHealth() ? 6 : 9;
	}
	
    public boolean getCanSpawnHere()
    {
        return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && super.getCanSpawnHere() && (this.worldObj.provider.getDimension() == w2theJungle.dimensionIdLost);
    }
    
    protected void updateAITasks() {
    	super.updateAITasks();

		if(this.getLennySpawnType() == 1)
		{
			this.setHomePosAndDistance(new BlockPos(34, 40, 0), 15);
		}
		else
		{
			this.setHomePosAndDistance(new BlockPos(this.posX, this.posY, this.posZ), 30);
		}
		
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());

	}
    
	public void onLivingUpdate() {
		super.onLivingUpdate();

		//this.setNoAI(true);


		this.walkEffect(1);

		this.destroyBlocksInAABB(this.getEntityBoundingBox().expand(1, 0, 1), -1);
		//this.destroyBlocksInAABB(this.lennyNeck.getEntityBoundingBox().expand(0, 0, 0), 0);
		
		if (this.livingSoundTime > 200 + this.rand.nextInt(100)) {
			this.playSound(SoundEvents.ENTITY_BLAZE_AMBIENT, 0.8F, 0.01F);
			this.livingSoundTime = 0;
		} else {
			++this.livingSoundTime;
		}

		if(!this.worldObj.isRemote)
		{
		if (this.roarTimer < 0 && this.getAttackTarget() == null && !this.inWater && this.deathTime == 0) {
			
			this.playSound(SoundEvents.ENTITY_BLAZE_DEATH, 8.0F, 0.05F);
			this.playSound(SoundEvents.ENTITY_ENDERMEN_SCREAM, 8.0F, 0.05F);
	    	this.playSound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, 8.75F, 0.001F);
	        this.worldObj.setEntityState(this, (byte)11);
			this.roarTimer = 1200 + this.rand.nextInt(600);

			this.moveStrafing = this.moveForward = 0.0f;
			
			//this will only function out of combat... so lets have him stop to roar during combat?
			for(int i = 0; i < 2; ++i)
			{
				List<EntitySacrificialSkeleton> lt = this.worldObj.getEntitiesWithinAABB(EntitySacrificialSkeleton.class, this.getEntityBoundingBox().expand(10.0D, 0.0D, 10.0D));
				
				if(lt.size() < 8)
				{
					this.wakeTheDamned();
					}
				}
			}
		else
		{
			--this.roarTimer;
			}


		}
		
		//effect timer is solely used by tremble, to pace dmg, visuals and skel spawns.
		if (this.effectTimer > 0)
		{
			this.moveForward = this.moveStrafing = 0;
			this.smashEffect();
			
			if(this.attacktype == 4)
			{
				if(this.effectTimer%8 == 0)
				{
					int d = 8 - this.effectTimer/8;
					this.tremble(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(4, 0, 4)), d);
					this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 16.0F, 0.1F);
					}
				
				if(this.effectTimer%50 == 0)
				{
					List<Entity> mobs = worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(12, 3, 12));
					
					if(mobs != null)
					{
						for(int i  = 0; i < mobs.size(); ++i)
						{
							Entity mob = (Entity) mobs.get(i);
							
							if(mob instanceof EntityPlayer && !((EntityPlayer) mob).capabilities.isCreativeMode)
							{
								this.wakeTheDamned();
								}
							}
						}
					}
				}
			--this.effectTimer;
			}

		if (this.smashTimer > 0)		{--this.smashTimer;}
		if (this.attackTimer > 0)		{--this.attackTimer;}
		if (this.tremorTimer > 0)		{--this.tremorTimer;}
		if (this.animationTimer > 0)	{--this.animationTimer;}
		if (this.roarAnimationTimer > 0){--this.roarAnimationTimer;}
		
		if (this.slowed)
        {
            this.motionX *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
		else		
		if(this.isSwimming())
        {
            this.motionX *= 1.14800000011920929D;
 //           this.motionY *= 1D;
            this.motionZ *= 1.1400000011920929D;
        }
		else
		{
			this.motionX *=1;
			this.motionZ *= 1;
		}

		//TODO leaping out of mud aftr a time

		double mo = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		
		//i will need to use these things for all parts to approximate the location of the corresponding model component. yuck...
		float rads = (float) (180F/Math.PI);
		float x = (float) (Math.sin(this.renderYawOffset/rads) * 2.5);
		float z = (float) (Math.cos(this.renderYawOffset/rads) * 2.5);
		this.lennyTail5.onUpdate();
		this.lennyTail5.setLocationAndAngles(this.posX + x,
				this.posY+2.0 + (mo*20) + (this.roarAnimationTimer > 0 ? 1.25 : 0),
				this.posZ - z, 0, 0);
		
		x = (float) (Math.sin(this.renderYawOffset/rads) * 5.075);
		z = (float) (Math.cos(this.renderYawOffset/rads) * 5.075);
		this.lennyTail4.onUpdate();
		this.lennyTail4.setLocationAndAngles(this.posX + x, this.posY+1.50 + (mo*20) + (this.roarAnimationTimer > 0 ? 2. : 0), this.posZ - z, 0, 0);
		
		x = (float) (Math.sin(this.renderYawOffset/rads) * 3.5);
		z = (float) (Math.cos(this.renderYawOffset/rads) * 3.5);
		this.lennyNeck.onUpdate();
		this.lennyNeck.setLocationAndAngles(this.posX - x, this.posY+4F + (this.roarAnimationTimer > 0 ? -1 : 0), this.posZ + z, 0, 0);
		
    	AxisAlignedBB [] len = new AxisAlignedBB [] {
    			this.lennyTail4.getEntityBoundingBox(),
    			this.lennyTail5.getEntityBoundingBox()
    			};
    	
    	for(int l = 0; l < len.length; ++l) {
    		if (!this.worldObj.isRemote) {
    			this.tailWhip(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, len[l]), len[l]); //this.lennyTail5.getEntityBoundingBox()), this.lennyTail5.getEntityBoundingBox());
    			}
    		}
    	
    	if (!this.worldObj.isRemote) //this shouldnt be necessary??
        {
            this.slowed = this.worldObj.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getBlock() == JungleBlocks.mudBlock;
            
            //works every 7 sec atm. need to stop him eating them all.
            if (this.lowHealth() && this.ticksExisted%140 == 0 && this.deathTime == 0)
            {
            	List<EntitySacrificialSkeleton> lt = this.worldObj.getEntitiesWithinAABB(EntitySacrificialSkeleton.class, this.getEntityBoundingBox().expand(10.0D, 0.0D, 10.0D));
            	List<EntityPlayer> lt2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(30, 30, 30));
            	float additional = lt2.size() == 0 ? 1 : lt2.size() * 0.75f;

            	if(lt.size() > 0)
            	{
            		int j = this.rand.nextInt(lt.size());
            		
            		EntitySacrificialSkeleton nttss = (EntitySacrificialSkeleton)lt.get(j);
            		
            		float hmod = nttss.getHealth();
            		
            		if(lt.get(j) != null)
            		{
            			this.setHealth(this.getHealth() + (hmod*additional));
            			this.setRevengeTarget(nttss);
            			this.playSound(SoundEvents.ENTITY_SKELETON_DEATH, 1, 1);
            			nttss.setHealth(0);
            			}
            		}
            	}
            }
    	
    	//this.setDead();
        
    	if(this.ticksExisted%20 == 0 && !this.worldObj.isRemote)
    	{
    		if(this.getAttackTarget() != null)
    		{
    			//System.out.println(this.getDistanceToEntity(this.getAttackTarget()));
    			}
    		}
   		this.calculateHealth();
    }
	
	public void walkEffect(int l) {

		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.50000027790520E-7D
				&& this.rand.nextInt(l) == 0)
		{		
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double) this.getYOffset());
			int k = MathHelper.floor_double(this.posZ);
			
			Block block = this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock();
        	IBlockState iblock = this.worldObj.getBlockState(new BlockPos(i, j, k));

			if (block.getDefaultState().getMaterial() != Material.AIR)
			{				
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
	
	public void wakeTheDamned()
	{
		if(!this.worldObj.isRemote)
		{
			EntitySacrificialSkeleton aid = new EntitySacrificialSkeleton(this.worldObj);
			aid.setLocationAndAngles((double)this.posX-5+this.rand.nextInt(10), (double)this.posY+1, (double)this.posZ-5+this.rand.nextInt(10), 0.0F, 0.0F);
			aid.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(this)), (IEntityLivingData)null);

			this.worldObj.spawnEntityInWorld(aid);
			this.playSound(SoundEvents.ENTITY_SKELETON_HURT, 0.5f, 1);
			aid.spawnExplosionParticle();
			}
		}
	
    public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_)
    {
    	if(!this.worldObj.isRemote)
    	{
    		this.moveStrafing = this.moveForward = 0.0f;
    		}
    	}
    
    public boolean attackEntityAsMob(Entity ntt)
    {
    	List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(3, 0, 3));

    	this.attacker = ntt;
    	this.dataManager.set(ATTACKER_POS, new BlockPos(this.attacker));
    	
    	float dmg;
    	float dm = this.worldObj.getDifficulty().getDifficultyId();

    	if(this.getDistanceToEntity(ntt) < 6.5)
    	{
    		if(this.tremorTimer <= 0 && list != null)
    		{
    			this.worldObj.setEntityState(this, (byte)32);
    			this.attacktype = 4;
    			this.attackTimer = 60;
    			this.tremorTimer = 300; //15s. enough time to kill at least one of the prev skel spawns.
    			this.effectTimer = 60;
    			this.smashTimer += 100;
    			dmg = 1;
    			return false;
    		}
    		else
    			if (this.smashTimer == 0)
    			{
    				this.worldObj.setEntityState(this, (byte)33); //chomp
    				dmg = 15; //chomp vs armour =
    				this.attacktype = 1;
    			}
    			else
    			if(this.attackTimer == 0)
    			{
    				this.attacktype = 2;
    				dmg = 5;
    				this.worldObj.setEntityState(this, (byte)34); //claw
    			}
    			else
    			{
    				dmg = 1;
    				return false;
    			}
    		
    		dmg = dmg + 10 + (this.rand.nextInt((int)dmg/3)) + dm*0.11f + (float)this.rand.nextGaussian() * 0.5f;
    		boolean flag = ntt.attackEntityFrom(DamageSource.causeMobDamage(this), dmg * 0.9f);
    	

    		if(flag)
    		{
        		ntt.hurtResistantTime = 0;
				ntt.attackEntityFrom(DamageSource.generic, dmg * 0.1f);

    			if(attacktype == 1)
    			{
    				ntt.motionY += 0.4000000059604645D;
    				ntt.motionZ = -0.3000000059604645D;
    				ntt.motionX = -0.3000000059604645D;
    				
    				this.playSound(SoundEvents.ENTITY_BLAZE_DEATH, 1.0F, 0.25F);
    				this.playSound(SoundEvents.ENTITY_ENDERMEN_SCREAM, 1.0F, 0.25F);
    				this.smashTimer = (int) (100 + dmg*2.5);
    			}
    			else
    				if(this.attacktype == 2)
    				{
    					this.attackTimer = 30;
    					this.playSound(SoundEvents.ENTITY_ZOMBIE_AMBIENT, 1.7F, .5F);
    				}
    			}
    		else
    		{
    			flag = false;
    		}
    		return flag;
    		}
    	else
    	{
    		return false;
    	}
    }
        
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase nttlb, float rangeMod)
	{
        float f3 = this.getDistanceToEntity(nttlb);

        List<EntityLivingBase> moblist = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(3, 0, 3));
        Iterator<EntityLivingBase> it = moblist.iterator();
        while(it.hasNext())
        {
        	if(it.next() instanceof EntitySacrificialSkeleton)
        	{
        		it.remove();		
        		}
        	}
        //to allow lenny to respond to swarming mobs while targetting is fixed on a ranged mob
        if(this.tremorTimer <= 0 && moblist.size() > 3)
        {
			this.worldObj.setEntityState(this, (byte)32);
			this.attacktype = 4;
			this.attackTimer =  60;
			this.tremorTimer = 300;
			this.effectTimer = 60;
			this.smashTimer += 100;
        }
                
		if(f3 >= this.minRange && !this.lowHealth()) {

			this.attacktype = 3;
	    	
	    	this.worldObj.setEntityState(this, (byte)35);
	    	
	    	float a = 0.75f + (1-rangeMod) + ((float)(this.rand.nextGaussian() * 0.25D)
            		+ ((float)this.worldObj.getDifficulty().getDifficultyId() * 0.11f)) + (nttlb instanceof EntityBat ? 15 : 0);

	    	nttlb.attackEntityFrom(DamageSource.causeMobDamage(this), a);
	    	nttlb.hurtResistantTime = 0;
	    	nttlb.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this), a);

	    	this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 5.0F, .21F);
	    	
	    	this.attacker = nttlb;
	    	this.dataManager.set(ATTACKER_POS, new BlockPos(this.attacker));
	    	}
		else
		{
			this.attackEntityAsMob(nttlb);
		}
    }
	
    public void tremble(List<?> list, int d)
    {
		this.moveForward = this.moveStrafing = 0;

		Iterator<?> iterator = list.iterator();
		
        while (iterator.hasNext())
        {
        	Entity entity = (Entity)iterator.next();
        	
        	if (entity instanceof EntityLivingBase)
        	{
        		float dm = this.worldObj.getDifficulty().getDifficultyId();
        		float d2 = (2 * d) + (dm*1.5f) + 0.5f; //5.5 to 17.5 max
    	    	
        		entity.attackEntityFrom(DamageSource.causeMobDamage(this), d2*0.9f);
        		entity.hurtResistantTime = 0;
        		entity.attackEntityFrom(DamageSource.generic, d2 * 0.1f);
            }
    	}
    }
    
    private void tailWhip(List<?> p_70970_1_, AxisAlignedBB len) {
    	
        double d0 = (len.minX + len.maxX) / 2.0D;
        double d1 = (len.minZ + len.maxZ) / 2.0D;
        Iterator<?> iterator = p_70970_1_.iterator();
       
        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();
			if(entity instanceof EntityPlayerMP && !((EntityPlayerMP) entity).capabilities.isCreativeMode)
			{
				return;
			}
			else
			if (entity instanceof EntityLivingBase)
            {
                double d2 = entity.posX - d0;
                double d3 = entity.posZ - d1;
                double d4 = d2 * d2 + d3 * d3;
                entity.addVelocity(d2 / d4 * 1.20D, 0.20000000298023224D, d3 / d4 * 1.20D); //org 4.0D
        		this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 10.0F, 0.10F);
                entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.worldObj.getDifficulty().getDifficultyId()*1.5f);
                entity.hurtResistantTime = 0;
                entity.attackEntityFrom(DamageSource.generic, this.worldObj.getDifficulty().getDifficultyId()*1.5f);

            }
        }
    }
    
    private void smashEffect() {
    	
    	int i = MathHelper.floor_double(this.posX);
    	int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double) this.getYOffset());
    	int k = MathHelper.floor_double(this.posZ);

    	BlockPos pos = new BlockPos(i, j, k);
    	
    	int a = 5;
    	for(int x = -a; x < a; ++x)
    		for(int z = -a; z < a; ++z)
    		{
    			if(x*x + z*z < 25)
    			{
    				Block block = this.worldObj.getBlockState(pos.add(x, 0, z)).getBlock();
                	IBlockState iblock = this.worldObj.getBlockState(pos.add(x, 0, z));

    					if (block.getDefaultState().getMaterial() != Material.AIR)
    					{
    						double px = this.posX + x + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width;
    						double pz = this.posZ + z + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width;
    						
    						EnumParticleTypes st = EnumParticleTypes.BLOCK_CRACK;

    						this.worldObj.spawnParticle(st, px, j+1 + 0.1D, pz,
    								4.0D * ((double) this.rand.nextFloat() - 0.5D), 0.5D,
    								((double) this.rand.nextFloat() - 0.5D) * 4.0D,
                            		new int[] {Block.getStateId(iblock)});
    						}
    					}
    			}
    	}
    
	@SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte par1)
    {
		switch(par1)
		{
		case 11:
			this.roarAnimationTimer = 80;
			//System.out.println("roar");
			break;
			case 32:
				this.attacktype = 4;
				this.animationTimer = 40;
				this.effectTimer = 60;
				//System.out.println("stomp");
				break;
				case 33:
					this.attacktype = 1;
					this.animationTimer = 20;
					//System.out.println("chomp");
					break;
					case 34:
						this.attacktype = 2;
						this.animationTimer = 20;
						//System.out.println("claw");
						break;
						case 35:
							this.attacktype = 3;
							this.animationTimer = 20;
							//System.out.println("zap!");
							break;
							default:
								super.handleStatusUpdate(par1);
								}
		
        }


	public int getMaxSpawnedInChunk(){return 1;}
	
	//use this to allow lenny to walk thru low walls. called during onupdate
	private void destroyBlocksInAABB(AxisAlignedBB abba, int imod)
    {
		int waymaker = (int) ((float)this.breakingtime/5F * 10F);
		
        int minX = MathHelper.floor_double(abba.minX);
        int minY = MathHelper.floor_double(abba.minY);
        int minZ = MathHelper.floor_double(abba.minZ);
        int maxX = MathHelper.floor_double(abba.maxX);
        int maxY = MathHelper.floor_double(abba.maxY) + imod;
        int maxZ = MathHelper.floor_double(abba.maxZ);

        for (int x = minX; x <= maxX; ++x)
        {
        	for (int y = minY; y <= maxY; ++y)
        	{
        		for (int z = minZ; z <= maxZ; ++z)
        		{
        			BlockPos pos = new BlockPos(x, y, z);
        			IBlockState iblock = this.worldObj.getBlockState(pos);
        			Block block = iblock.getBlock();

                    if((block==Blocks.LEAVES || block==Blocks.WEB || block==Blocks.COBBLESTONE
                    		|| block.getExplosionResistance(this) < 6F)
                    		&& block != Blocks.AIR && block!=Blocks.DIRT)
                    {
                    	this.worldObj.sendBlockBreakProgress(this.getEntityId(), pos, waymaker);

                    	if(this.breakingtime > 5)
                    	{
                    		this.breakingtime = 0;
                    		SoundEvent se = block == Blocks.LEAVES ? SoundEvents.BLOCK_GRASS_BREAK :
                    			(block instanceof BlockStone ? SoundEvents.BLOCK_STONE_BREAK :
                    				SoundEvents.BLOCK_WOOD_BREAK); 
                    		this.playSound(se, 1.F, 0.7F);
                    		
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
                    		this.worldObj.setBlockToAir(new BlockPos(x, y, z));
                    		}
                    	else
                    	{
                    		++this.breakingtime;
                    		}
                    	}
                    }
                }
            }
        }
	
	public boolean canEntityDestroy(World worldObj, Block block, EntityLenny entityLenny)
	{
		return block == (Blocks.STONEBRICK);
	}

	@Override
	public World getWorld() {
        return this.worldObj;
	}

	@Override
	public boolean attackEntityFromPart(EntityDragonPart dpart, DamageSource dsource, float ddmg)
	{		
		if(dpart == this.lennyNeck)
		{
			ddmg *= 1.2;
			}
		
		if(dpart == this.lennyTail4)
		{
			ddmg *= 0.7;
			}
		
		if(dpart == this.lennyTail5)
		{
			ddmg *= 0.85;
			}
		
        this.func_82195_e(dsource, ddmg);
		return true;
		}

	//chaining part s dmg over to the main dmg method
	protected boolean func_82195_e(DamageSource p_82195_1_, float p_82195_2_)
    {
        return this.attackEntityFrom(p_82195_1_, p_82195_2_);
    }

	public Entity[] getParts()
    {
        return this.lennyNaughtyParts;
    }

	
	public void calculateHealth()
	{
    	float priorHealth = this.getMaxHealth();
    	List<EntityPlayer> lt2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(30, 30, 30));
    	int additional = lt2.size() == 0 ? 0 : (lt2.size()-1)*175;
    	
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(350+additional); //350
    	this.setHealth((this.getHealth()/priorHealth)*this.getMaxHealth());
    	
    	if(this.ticksExisted%40 == 0) {
    		this.heal(lt2.size() > 0 ? lt2.size() : 1);
    		}        	
	}
	
    public boolean isPotionApplicable(PotionEffect p_70687_1_)
    {
        return p_70687_1_.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(p_70687_1_);
    }
	
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
    	Entity ntt;

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

            if (this.lowHealth())
            {
                ntt = p_70097_1_.getSourceOfDamage();

                if (ntt instanceof EntityArrow || ntt instanceof EntityThrowable)
                {
                    return false;
                }
            }

            ntt = p_70097_1_.getEntity();
            
            if((ntt instanceof EntityPlayer && this.getDistanceToEntity(ntt) > 20) || ntt instanceof EntitySacrificialSkeleton )
            {
            	return false;
            }
            
        }
		
		//intended to pass aggro. only works on lenny being hit.
		//possible bug due to version age: skels continue shooting at empty air after target death.
		//
            
			List<Entity> mobs = worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(12, 3, 12));

			if(mobs != null) {
				for(int i  = 0; i < mobs.size(); ++i) {
					
					Entity mob = (Entity) mobs.get(i);
					ntt = p_70097_1_.getEntity();
					
					if(mob instanceof EntitySacrificialSkeleton)
					{						
						EntitySacrificialSkeleton ss = (EntitySacrificialSkeleton)mob;
						
						if(ss.getAttackTarget() == null && !(this.getAttackTarget() instanceof EntitySacrificialSkeleton))
						{
							if(ntt instanceof EntityLivingBase)
							/* arrow check required to avoid bug where game crashes/unloads with arrow inflight,
							 * then on reload arrow has no entity source and is the revenge target
							 */
							{
								ss.setRevengeTarget((EntityLivingBase)ntt);
								}
							}
						}
					}
				}
			
			return super.attackEntityFrom(p_70097_1_, p_70097_2_);
			
	}

	
    private boolean lowHealth() {
		return this.getHealth() < this.getMaxHealth()/2;
	}
    
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if(this.deathTime > 10 && this.deathTime < 20) {
        	
        	this.playSound(SoundEvents.ENTITY_BLAZE_DEATH, 5.0F, 0.05F);
			this.playSound(SoundEvents.ENTITY_ENDERMEN_SCREAM, 5.0F, 0.05F);
	    	this.playSound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, 5.75F, 0.001F);
	                	
        	double t0 = this.rand.nextGaussian() * 0.02D;
        	double t1 = this.rand.nextGaussian() * 0.02D;
        	double t2 = this.rand.nextGaussian() * 0.02D;
        	
           this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,
            		this.posX + (double)(this.rand.nextFloat() * this.width * 2.5F) - (double)this.width,
            		this.posY + (double)(this.rand.nextFloat() * this.height),
            		this.posZ + (double)(this.rand.nextFloat() * this.width * 2.5F) - (double)this.width,
            		t0, t1, t2);
           
           this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 1, 1F);
        }
        
        int i;
        
        if (this.deathTime >= 80 && this.deathTime%5 == 0)
        {
            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.canDropLoot() && this.worldObj.getGameRules().getBoolean("doMobLoot"))
            {
                i = (int)(this.getExperiencePoints(this.attackingPlayer)/3);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    
                    float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
                    float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
                    float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
                    
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX + f, this.posY + f1 + 2f, this.posZ + f2, j));
                }
            }
        }
        
        if(this.deathTime == 110)
        {
        	this.playSound(SoundEvents.ENTITY_BLAZE_AMBIENT, 4F, 0.01F);
            this.setDead();

            if (!this.worldObj.isRemote && this.canDropLoot() && this.worldObj.getGameRules().getBoolean("doMobLoot"))
            {
            	if(this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getMaterial().isSolid()
            			&& this.worldObj.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)) == Blocks.AIR)
            	{
            		this.worldObj.setBlockState(new BlockPos(this.posX, this.posY, this.posZ), JungleBlocks.tyrantSkull.getDefaultState());
            		}
            	else
            	{
            		this.entityDropItem((new ItemStack(JungleBlocks.tyrantSkull, 1)), 0.0F);
            		}
            	
            	
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            for (i = 0; i < 20; ++i)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            } 
        }
    }

	public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);
        
        if (!this.worldObj.isRemote)
        {
            Iterator<EntityPlayer> iterator = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(50.0D, 50.0D, 50.0D)).iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();
                entityplayer.addStat(JungleAchievements.slainTyrant);
            }
          
            this.entityDropItem((new ItemStack(JungleItems.eyeT, 1)), 0.0F);

            }
        }
	
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {    	
        new bookScale();
		ItemStack stack = bookScale.southScale;
        
        this.entityDropItem(stack, 0.0F);
            	
    	for(int i = 0; i < 9; ++i)
    	{
    		float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
    		float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
    		float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
    		
    		this.worldObj.spawnEntityInWorld(
    				new EntityItem(this.worldObj,
    						(double)(this.posX + f), (double)(this.posY + f1 + 1.5f), (double)(this.posZ + f2),
    						new ItemStack(Items.ROTTEN_FLESH, 1)));
    	}
    	
    	for(int i = 0; i < 7; ++i)
    	{
    		float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
    		float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
    		float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
    		
    		this.worldObj.spawnEntityInWorld(
    				new EntityItem(this.worldObj,
    						(double)(this.posX + f), (double)(this.posY + f1 + 1.5f), (double)(this.posZ + f2),
    						new ItemStack(JungleItems.carvedBone, 1)));

    	}
    }

    
	public BlockPos getAttacker()
	{
		return this.dataManager.get(ATTACKER_POS);
		}
	/*
	public float getAttackerPosY()
	{
		return this.dataWatcher.getWatchableObjectFloat(24);
		}
	
	public float getAttackerPosZ()
	{
		return this.dataWatcher.getWatchableObjectFloat(25);
		}
	*/
	@SideOnly(Side.CLIENT)
	public int getAnimationTimer() {return this.animationTimer;}
	
	@SideOnly(Side.CLIENT)
	public int getRoarAnimationTimer() {return this.roarAnimationTimer;}
	
	//@SideOnly(Side.CLIENT)
	//public boolean getRoaring() {return this.isRoaring;}
    public int getAttType(){return this.attacktype;}
    	
    	public boolean isAIEnabled(){return true;}
    	protected boolean canDespawn() {return false;}
    	public boolean isPushedByWater() {return false;}
    	
    	
    	public boolean isInWater()
    	    {
    	        return this.worldObj.getBlockState(new BlockPos(this.posX, this.posY+3, this.posZ)).getBlock() == Blocks.WATER;
    	    }
    	
        public boolean isSwimming() {
        	return this.inWater
        			&& (this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.WATER
        			|| (this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.FLOWING_WATER));
        	}
    	//erver side. need to use data watch or entity state to specify talking and set this timer.
    	public int getLivingSoundTime(){return this.livingSoundTime;}

    	public boolean isMovementCeased() {return this.roarAnimationTimer > 0 || this.animationTimer > 0;}

   	@Override
   	protected void playStepSound(BlockPos pos, Block blockIn)
   	{
   		if(!this.isSwimming())
   		{
   			this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 10.0F, 0.001F);
   		}
   	}
   	
   	public boolean isNonBoss()
   	{
   		return false;
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

   	public int getLennySpawnType()
   	{
   		return this.dataManager.get(LENNY_SPAWN_TYPE);
   	}

	    public void setLennySpawnType(int p_82201_1_)
	    {
	        this.dataManager.set(LENNY_SPAWN_TYPE, Byte.valueOf((byte)p_82201_1_));
	    }

	    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
	    {
	        super.readEntityFromNBT(p_70037_1_);

	        if (p_70037_1_.hasKey("LennySpawnType", 99))
	        {
	            byte b0 = p_70037_1_.getByte("LennySpawnType");
	            this.setLennySpawnType(b0);
	        }
	    }

	    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
	    {
	        super.writeEntityToNBT(p_70014_1_);
	        p_70014_1_.setByte("LennySpawnType", (byte)this.getLennySpawnType());
	    }

}
