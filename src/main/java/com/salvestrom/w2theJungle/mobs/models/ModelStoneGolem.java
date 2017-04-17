package com.salvestrom.w2theJungle.mobs.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;

public class ModelStoneGolem extends ModelBase
{

//public static boolean smashed;
//public static boolean righthook;
//public static boolean uppercut;

//fields
    ModelRenderer head;
    ModelRenderer upperbody;
    ModelRenderer lowerbody;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer upperRleg;
    ModelRenderer upperLleg;
    ModelRenderer upperLarm;
    ModelRenderer lowerLarm;
    ModelRenderer LFist;
    ModelRenderer upperRarm;
    ModelRenderer lowerRarm;
    ModelRenderer RFist;
    ModelRenderer gemholder;
    ModelRenderer gem;

  
  public ModelStoneGolem()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      head = new ModelRenderer(this, 0, 17);
      head.addBox(-5F, -10F, -5F, 10, 10, 10);
      head.setRotationPoint(0F, -10F, -4F);
      head.setTextureSize(128, 128);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      upperbody = new ModelRenderer(this, 0, 103);
      upperbody.addBox(-15F, -12F, -6F, 30, 12, 12);
      upperbody.setRotationPoint(0F, -10F, 0F);
      upperbody.setTextureSize(128, 128);
      upperbody.mirror = true;
      setRotation(upperbody, 0F, 0F, 0F);
      lowerbody = new ModelRenderer(this, 0, 80);
      lowerbody.addBox(-11F, -10F, -6F, 22, 10, 12);
      lowerbody.setRotationPoint(0F, 2.0F, 0F);
      lowerbody.setTextureSize(128, 128);
      lowerbody.mirror = true;
      setRotation(lowerbody, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 41, 55);
      rightleg.addBox(-6F, 0F, -3F, 12, 12, 12);
      rightleg.setRotationPoint(0F, 10F, -3F);
      rightleg.setTextureSize(128, 128);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 41, 30);
      leftleg.addBox(-6F, 0F, -3F, 12, 12, 12);
      leftleg.setRotationPoint(0F, 10F, -3);
      leftleg.setTextureSize(128, 128);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      upperRleg = new ModelRenderer(this, 0, 59);
      upperRleg.addBox(-5F, 0F, -5F, 10, 10, 10);
      upperRleg.setRotationPoint(-8F, 0F, 0F);
      upperRleg.setTextureSize(128, 128);
      upperRleg.mirror = true;
      setRotation(upperRleg, 0F, 0F, 0F);
      upperLleg = new ModelRenderer(this, 0, 38);
      upperLleg.addBox(-5F, 0F, -5F, 10, 10, 10);
      upperLleg.setRotationPoint(8F, 0F, 0F);
      upperLleg.setTextureSize(128, 128);
      upperLleg.mirror = true;
      setRotation(upperLleg, 0F, 0F, 0F);
      upperLarm = new ModelRenderer(this, 96, 38);
      upperLarm.addBox(0F, -4F, -4F, 8, 12, 8);
      upperLarm.setRotationPoint(15F, -6F, -1F);
      upperLarm.setTextureSize(128, 128);
      upperLarm.mirror = true;
      setRotation(upperLarm, 0F, 0F, 0F);
      lowerLarm = new ModelRenderer(this, 88, 82);
      lowerLarm.addBox(-5F, 0F, -5F, 10, 12, 10);
      lowerLarm.setRotationPoint(4F, 8F, 0F);
      lowerLarm.setTextureSize(128, 128);
      lowerLarm.mirror = true;
      setRotation(lowerLarm, 0F, 0F, 0F);
      LFist = new ModelRenderer(this, 0, 0);
      LFist.addBox(-4F, -1F, -4F, 8, 2, 8);
      LFist.setRotationPoint(0F, 12F, 0);
      LFist.setTextureSize(128, 128);
      LFist.mirror = true;
      setRotation(LFist, 0F, 0F, 0F);
      upperRarm = new ModelRenderer(this, 96, 59);
      upperRarm.addBox(-8F, -4F, -4F, 8, 12, 8);
      upperRarm.setRotationPoint(-15F, -6F, -1F);
      upperRarm.setTextureSize(128, 128);
      upperRarm.mirror = true;
      setRotation(upperRarm, 0F, 0F, 0F);
      lowerRarm = new ModelRenderer(this, 88, 105);
      lowerRarm.addBox(-5F, 0F, -5F, 10, 12, 10);
      lowerRarm.setRotationPoint(-4F, 8F, 0F);
      lowerRarm.setTextureSize(128, 128);
      lowerRarm.mirror = true;
      setRotation(lowerRarm, 0F, 0F, 0F);
      RFist = new ModelRenderer(this, 33, 0);
      RFist.addBox(-4F, -1F, -4F, 8, 2, 8);
      RFist.setRotationPoint(0F, 12F, 0F);
      RFist.setTextureSize(128, 128);
      RFist.mirror = true;
      setRotation(RFist, 0F, 0F, 0F);
      gemholder = new ModelRenderer(this, 66, 0);
      gemholder.addBox(-1.5F, -1F, -2F, 3, 4, 1);
      gemholder.setRotationPoint(0.5F, -5F, -4.5F);
      gemholder.setTextureSize(128, 128);
      gemholder.mirror = true;
      setRotation(gemholder, 0F, 0F, 0F);
      gem = new ModelRenderer(this, 75, 0);
      gem.addBox(-1F, -2F, -1F, 2, 3, 1);
      gem.setRotationPoint(0F, 1.5F, -1.5F);
      gem.setTextureSize(128, 128);
      gem.mirror = true;
      setRotation(gem, 0F, 0F, 0F);
      
      
      this.lowerbody.addChild(upperbody);
      this.lowerbody.addChild(upperRleg);
      this.lowerbody.addChild(upperLleg);
      this.upperbody.addChild(gemholder);
      this.upperbody.addChild(head);
      this.gemholder.addChild(gem);
      this.upperbody.addChild(upperLarm);
      this.upperbody.addChild(upperRarm);
      this.upperLarm.addChild(lowerLarm);
      this.upperRarm.addChild(lowerRarm);
      this.lowerLarm.addChild(LFist);
      this.lowerRarm.addChild(RFist);
      this.upperLleg.addChild(leftleg);
      this.upperRleg.addChild(rightleg);
      
  }

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    lowerbody.render(f5);
    }

private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float time, float motion, float age, float par4, float par5, float par6, Entity ntt)
  {
	  time *= 2.5f; //omg he looks so cute shuffling like this... (2.51)
		
	  	  this.head.rotateAngleY = par4 / (240F / (float)Math.PI);
		  this.head.rotateAngleX = par5 / (240F / (float)Math.PI);
		  this.upperRleg.rotateAngleX = 1.5F * this.func_78172_a(time, 13.0F) * motion;
		  this.leftleg.rotateAngleX = 1.5F * this.func_78172_a(time, 13.0F) * motion;
		  this.upperLleg.rotateAngleX = -1.5F * this.func_78172_a(time, 13.0F) * motion;
		  this.rightleg.rotateAngleX = -1.5F * this.func_78172_a(time, 13.0F) * motion;
		//  this.upperLleg.rotateAngleY = 0.0F;
		//  this.upperRleg.rotateAngleY = 0.0F;
		  //wobble
		  //{if (motion > 0)
		  this.lowerbody.rotateAngleZ = motion/2 * 0.315F * (this.func_78172_a(time, 13.0F));
		  this.lowerbody.rotateAngleY = -motion/2 * 0.5315F * (this.func_78172_a(time, 13.0F));

		  //else {this.lowerbody.rotateAngleZ = 0;}}
		  //MathHelper.sin((float) (time * 2F/(180F/Math.PI))) * 0.3F * move;
		  
		  //"kneecap" conditionals
		  if (leftleg.rotateAngleX < 0){
			  this.leftleg.rotateAngleX = 0; }
		  
		  if (rightleg.rotateAngleX < 0){
			  this.rightleg.rotateAngleX = 0; }
			  
		  }
  
  public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float time, float speed, float par4)
  {
	  EntityStoneGolem entitystonegolem = (EntityStoneGolem)par1EntityLivingBase;
	  Random rng = new Random();
	  float rads = (float) (180/Math.PI);
	  int i = entitystonegolem.getAnimationTimer();
	  int j = entitystonegolem.getSmashTimer();
	  boolean smashed = entitystonegolem.getSmashed();
	  boolean uppercut = entitystonegolem.getUppercut();
	  boolean righthook = entitystonegolem.getRightHook();

	  //im not convinced the booleans are doing anything that couldnt be simplified with 3 seperate timers.
	  //try using age? par4 = age from previous method?
	  if (smashed && i > 0)
	  {
		  this.head.setRotationPoint(0,
				  -10 - (float) Math.sin(i*.1)*2.75f,
				  -6 + (float) Math.sin(i*.1)*1.75f);
		  this.head.rotateAngleY += -i * 0.011f; //head seems set by target direction
		  this.upperRarm.rotateAngleX = (float) (-(float)Math.PI / 2.5);
		  this.upperbody.rotateAngleY += -i*(.011f);
		  this.upperLarm.rotateAngleX = 0F;
		  }
	  else if (righthook && i > 0)
	  {
		  this.head.setRotationPoint(0, -8, -6);
		  this.upperLarm.rotateAngleX = 0F;
		  this.upperRarm.rotateAngleX = (float)  - Math.sin((i) * 0.1f) * 1.25f; //-40/rads;
		  this.upperbody.rotateAngleY = -MathHelper.sin(i * 0.25F - 0.5F) * 0.25F;
		  }
	  else if (uppercut && i > 0)
	  {
		  this.head.setRotationPoint(0, -8, -6);
		  this.upperRarm.rotateAngleX = 0F;
		  this.upperLarm.rotateAngleX = (float)  - Math.sin(i * 0.1f) * 2.5f;
		  //  this.upperbody.rotateAngleY = -MathHelper.sin(i * 0.25F - 0.5F) * 0.25F;
		  }
	  //default arm swings
	  else
	  {
		  time *= 2.5f;
		  this.upperRarm.rotateAngleX = (-0.2F - 1.5F * this.func_78172_a(time, 13.0F)) * speed;
		  this.upperLarm.rotateAngleX = (-0.2F + 1.5F * this.func_78172_a(time, 13.0F)) * speed;
		  this.head.setRotationPoint(0.0F, -10F, -4F);
		  this.upperbody.rotateAngleY = 0;
		  }
	  }
		  
		    	  
		  
		  
		private float func_78172_a(float par1, float par2)
		  {
		      return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
		  
		}
  
  
}
