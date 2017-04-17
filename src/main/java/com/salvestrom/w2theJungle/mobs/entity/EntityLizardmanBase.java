package com.salvestrom.w2theJungle.mobs.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleSounds;
import com.salvestrom.w2theJungle.mobs.entity.ai.EntityAIPatrol;

public class EntityLizardmanBase extends EntityMob {

	public int livingSoundTime;
	public boolean isLaughing = false;
	public int laughTimer;
	public boolean darkout; 
	public float conserveEnergy;

	//private EntityAIPatrol lizardPatrol = new EntityAIPatrol(this, (double)(0.5 + conserveEnergy));

	public EntityLizardmanBase(World par1World) {
		super(par1World);

		//this used to be necessary for opening doors to work. 
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		//		this.tasks.addTask(1, new EntityAIClimbing(this));
		this.tasks.addTask(2, new EntityAIWander(this, (double)(0.3F)));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));

		this.tasks.addTask(5, new EntityAIMoveIndoors(this));
		this.tasks.addTask(6, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(7, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(9, new EntityAIPatrol(this, (double)(0.45)));// + conserveEnergy)));
		//TODO his target task will transfer aggro to similar mob classes, my code is broader,
		//but i could just tweak the below file.
		//in fact,, i think that all this does is transfer threat.]
		//Withoutt this mobs wont assist each other. ie my attackenttityfrom code does nothing...
		//actually it does. used it to pass aggro to golems. still...
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLenny.class, 0, false, true, IMob.MOB_SELECTOR));

	}
	
	protected static final DataParameter<Integer> HAS_AGGRO = EntityDataManager.<Integer>createKey(EntityLizardmanBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Float> CONSERVE_ENERGY = EntityDataManager.<Float>createKey(EntityLizardmanBase.class, DataSerializers.FLOAT);

	protected void entityInit()    {
		super.entityInit();
		//13 and 14
		this.dataManager.register(HAS_AGGRO, Integer.valueOf(0));
		this.dataManager.register(CONSERVE_ENERGY, Float.valueOf(0));
	}

	//this is here and blank to avoid interferring with other methods.
	//just use this and removee the moarloot method?
	protected void dropEquipment(boolean p_82160_1_, int p_82160_2_) {    }
	//TODO add abilityto move toward light sources during night.
	
	/* this is currently obsolete, since i used the new patrol ai to check against light
	 * but might work if i use the skeleton way, where these are defined at the start of
	 * the file and called later.
	 */
	public void coldBlooded() {

		this.tasks.removeTask(new EntityAIPatrol(this, (double)(0.45)));// + conserveEnergy)));

		if (this.worldObj.isDaytime()) {
			//System.out.println("added");
			this.tasks.addTask(9, new EntityAIPatrol(this, (double)(0.45)));// + conserveEnergy)));
		}
	}
	public int attackStatus;

	@SideOnly(Side.CLIENT)
	public int getHasTarget()	{
		return this.dataManager.get(HAS_AGGRO);
	}

	//server says no weapon, client thinks there is. added remote check to arming in scuffler file. cured?
	public void setHasTarget() {

		if(!this.worldObj.isRemote) {
			if(this.getAttackTarget() != null) {
				this.attackStatus = 2;
			} else if(this.laughTimer > 0) {
				this.attackStatus = 5;
			} else if(this.getHeldItemMainhand() != null) {
				this.attackStatus = 1;
			} else {
				this.attackStatus = 0;
			}
			
			if(this.attackStatus == 2)
			{
				this.setActiveHand(EnumHand.MAIN_HAND);
			}
			else
			{
				this.resetActiveHand();
			}

			this.dataManager.set(HAS_AGGRO, this.attackStatus);
		}
	}

	public void onLivingUpdate()
	{
		if(!this.worldObj.isRemote && this.ticksExisted%10 == 0)
		{
			this.setHasTarget();

			//System.out.println(this.attackStatus);
		}
		
		if(this.isLaughing) {
			this.laughTimer = 30;
			this.playSound(JungleSounds.SAUROHN_LAUGH, 0.5F, 1.05F);
			this.worldObj.setEntityState(this, (byte)31);
			this.livingSoundTime -= 100;
			this.isLaughing = false;
		}
		
		if(this.laughTimer > 0)
		{
			--this.laughTimer;
		}
		
		//this.getDarkOut();
		//this.coldBlooded();
		//this.setDead();
		
		if (this.livingSoundTime > 300 && this.hurtTime == 0) {

			this.worldObj.setEntityState(this, (byte)30);
/*
			if (this.rand.nextInt(2) == 1) {			
				this.playSound("thejungle:saurohn.saurohnTalk", 1.0F, 1.05F);
			} else {
				this.playSound("thejungle:saurohn.saurohnTalk", 1.0F, 1.05F);
			}
			*/
			this.playSound(JungleSounds.SAUROHN_TALK, 0.9F, 1.05F);
			this.livingSoundTime = 0 - this.rand.nextInt(200);
		} else	{
			++this.livingSoundTime;
		}
		
		if(this.hurtTime > 10)
		{
			this.worldObj.setEntityState(this, (byte)30);
			this.livingSoundTime += 50;
		}

		if(this.mouthAnimTimer > 0) {--this.mouthAnimTimer;}
		super.onLivingUpdate();

	}
	
    public boolean isPotionApplicable(PotionEffect p_70687_1_)
    {
        return p_70687_1_.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(p_70687_1_);
    }

	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
		
		Entity ntt = p_70097_1_.getEntity();

		if (this.isEntityInvulnerable(p_70097_1_))
		{
			return false;
		}
		else
			if(
					(ntt instanceof EntityLizardmanStalker || ntt instanceof EntityLizardmanWitchDoctor)
					&& this instanceof EntityLizardmanStalker)
		{
			return false;        	
		}
		else
			if(
					(ntt instanceof EntityLizardmanScuffler || ntt instanceof EntityLizardmanStalker)
					&& this instanceof EntityLizardmanScuffler)
		{
			return false;        	
		}
		else
			if(
					(ntt instanceof EntityLizardmanScuffler || ntt instanceof EntityLizardmanWitchDoctor)
					&& this instanceof EntityLizardmanWitchDoctor) 
		{
			return false;        	
		}
		else
		{
			//works for the golems, not for the lizards... (cause i was telling them to attack each other. kek)
			Entity entity = p_70097_1_.getEntity();

			List<Entity> mobs = worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(12, 3, 12));

			if(mobs != null) {
				for(int i  = 0; i < mobs.size(); ++i) {

					Entity mob = (Entity)mobs.get(i);
					if(mob instanceof EntityLizardmanBase) {
						
						EntityLizardmanBase liz = (EntityLizardmanBase)mob;
						
						if(liz.getAttackTarget()==null && !(entity instanceof EntityArrow))	{

							liz.setRevengeTarget((EntityLivingBase) entity);
						}
					}
					
					if(mob instanceof EntityStoneGolem) {
						
						EntityStoneGolem st = (EntityStoneGolem)mob;
						
						if(st.getAttackTarget()==null) {
							st.setRevengeTarget((EntityLivingBase) entity);
						}
					}
				}
			}

			return super.attackEntityFrom(p_70097_1_, p_70097_2_);
		}
	}
	
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

//    	livingdata = super.onInitialSpawn(difficulty, livingdata);
        
    	this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        
        //this.addRandomArmor();
        //this.enchantEquipment();
        
        return livingdata;
    }

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.valueOf("reptilian");
	} 
	
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.3F, 0.25F);
    }
    
    protected SoundEvent getHurtSound()
    {
        return JungleSounds.SAUROHN_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return JungleSounds.SAUROHN_DEATH;
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
				this.worldObj.setSkylightSubtracted(10); //sets skylightsub
				l = this.worldObj.getLightFromNeighbors(pos);
				this.worldObj.setSkylightSubtracted(i1); //resets it to original value, saved as i1
			}
			return l >= this.rand.nextInt(10) + 6;	//is this right???
		}
	}
	//revery these changes so they only spawn in lit areas?? increase beacon spawns.?
    public float getBlockPathWeight(BlockPos pos)
    {
        return //this.worldObj.provider.dimensionId == w2theJungle.dimensionIdLost ? 0.0f : 
        		-0.45F + this.worldObj.getLightBrightness(pos);
    }

	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor_double(this.posZ);

		int j2 = (j);
		int j3 = (j-1);

		int ycheck = 50;

		if(this.worldObj.provider.getDimension() == w2theJungle.dimensionIdLost) {
			ycheck -= 10;
		}
		
		if (j2 >= ycheck
				&& super.getCanSpawnHere())
		{
			//ironically, lighting up the temple will make the spawners work.
			return true;			
		} else {
			return false;
		}
	}

	public int mouthAnimTimer;
	@SideOnly(Side.CLIENT)
	public int getLivingSoundTime(){return this.mouthAnimTimer;}
	@SideOnly(Side.CLIENT)
	public int getLaughTime(){return this.laughTimer;}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte par1)
	{
		if(par1 == 30)
		{
			if(this.hurtTime > 0)
			{
				this.mouthAnimTimer = 35;
			}
			else
			{
				this.mouthAnimTimer = 50;
			}		
		} else if (par1 == 31)
		{
			this.mouthAnimTimer = 50;
			this.laughTimer = 30;
		}
		else
		{
			super.handleStatusUpdate(par1);
		}
	}

	//@SideOnly(Side.CLIENT)
	public float getDarkOut() {
		//so the bug is all about this remote if statement. change it to true, or remove and scufflers will
		// pass thru, yet still, only 1 of 3 mobs was blocked by it

		/* so this only calculates light properly if server side, which puts me back at
		 * discovering why scufflers dont work... except now they do... except they do when its daylight
		 * at night, this code will not run...
		 * scufflers check this code far less often, too...
		 * 
		 * fixt**, but only with trickery-cunning
		 */
		
		
		int x = MathHelper.floor_double(this.posX);
		int y = MathHelper.floor_double(this.getEntityBoundingBox().minY);
		int z = MathHelper.floor_double(this.posZ);
		
		BlockPos pos = new BlockPos(x, y, z);
		
		float f = this.worldObj.getLight(pos.up());
		
	//if(!this.worldObj.isRemote){
		
		float m = this.worldObj.getLightFor(EnumSkyBlock.SKY, pos.up());
			float bl = this.worldObj.getLightFor(EnumSkyBlock.BLOCK, pos.up());
			float ss = this.worldObj.calculateSkylightSubtracted(1);
	
			if(bl >= m-ss) {
				this.conserveEnergy = (bl)/10;
			} else {
				this.conserveEnergy = (m-ss)/10;
			}
			
			if (this.conserveEnergy < 0.8F) {
				this.darkout = true;
			} else {
				this.darkout = false;
			}
			
			this.dataManager.set(CONSERVE_ENERGY, this.conserveEnergy);
		//}
		
		return this.dataManager.get(CONSERVE_ENERGY);
	}

}
/*
public Class[] targetlist = new Class[] {EntityPlayer.class, EntityZombie.class, EntitySkeleton.class, EntityWitch.class,
		EntityVillager.class, EntityIronGolem.class, EntityEnderman.class, EntityChicken.class, EntityCow.class, EntityPig.class};

public Class[] classlist = new Class[] {EntityPlayer.class, EntityZombie.class, EntitySkeleton.class, EntityWitch.class,
		EntityVillager.class, EntityIronGolem.class, EntityEnderman.class};

public Class[] huntlist = new Class[] {EntityChicken.class, EntityCow.class, EntityPig.class};
 */
/*		
for (int i = 0; i < this.classlist.length; ++i)
{
    this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, classlist[i], 0, false));
}
	//look over this file and see if you can make it register as a seperate list from classlist.
for(int i=0; i < this.huntlist.length; ++i)
{
    this.targetTasks.addTask(2, new EntityAINearestHuntableTarget(this, huntlist[i], 0, false));

}
 */	