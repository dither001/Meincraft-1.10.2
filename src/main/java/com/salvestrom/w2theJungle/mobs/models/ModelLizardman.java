package com.salvestrom.w2theJungle.mobs.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanScuffler;

public class ModelLizardman extends ModelBase
{
  //fields
    ModelRenderer LeftPinky;
    ModelRenderer RightPinky;
    public ModelRenderer body;
    public ModelRenderer TailSeg1;
    public ModelRenderer TailSeg2;
    public ModelRenderer TailSeg3;
    public ModelRenderer head;
    ModelRenderer Snout;
    ModelRenderer LowerJaw;
    ModelRenderer ToothL;
    ModelRenderer ToothR;
    public ModelRenderer upperRarm;
    public ModelRenderer lowerRarm;
    ModelRenderer Rightthumb;
    public ModelRenderer Righthand;
    ModelRenderer rightleg;
    ModelRenderer LowerRLeg;
    ModelRenderer UpperRFoot;
    ModelRenderer rightfoot;
    ModelRenderer leftleg;
    ModelRenderer LowerLLeg;
    ModelRenderer UpperLfoot;
    ModelRenderer leftfoot;
    ModelRenderer upperLarm;
    ModelRenderer lowerLarm;
    ModelRenderer Lefthand;
    ModelRenderer Leftthumb;
    public ModelRenderer lowerbody;
    ModelRenderer rightBracer;
    ModelRenderer leftBracer;
   
  float rad = (float) (180/Math.PI);
  Random rng = new Random();
 
  public ModelLizardman()
  {
    textureWidth = 64;
    textureHeight = 64;
    
   //rotation transformation is x'=x cos(ngl) - y sin (ngl)
   // and y' = y cos ngl + x sin ngl,  where ngl
   //is the rotation of the "child" in techne, NOT the relative dfference
    
      LeftPinky = new ModelRenderer(this, 57, 62);
      LeftPinky.addBox(0F, 0F, 0F, 1, 1, 1);
      LeftPinky.setRotationPoint(0F, 0F, 0.5F);
      LeftPinky.setTextureSize(64, 64);
      LeftPinky.mirror = true;
      setRotation(LeftPinky, 0F, -17/rad, 0F);
      RightPinky = new ModelRenderer(this, 57, 58);
      RightPinky.addBox(-1F, 0F, 0F, 1, 1, 1);
      RightPinky.setRotationPoint(0F, 0F, 0.5F);
      RightPinky.setTextureSize(64, 64);
      RightPinky.mirror = true;
      
      setRotation(RightPinky, 0F, 17/rad, 0F);
      TailSeg1 = new ModelRenderer(this, 0, 41);
      TailSeg1.addBox(-1F, 0F, 0F, 2, 3, 7);
      TailSeg1.setRotationPoint(0F, -0.5F, 1.3F);
      TailSeg1.setTextureSize(64, 64);
      TailSeg1.mirror = true;
      setRotation(TailSeg1, -3/rad, 0F, 0F);
      TailSeg2 = new ModelRenderer(this, 0, 32);
      TailSeg2.addBox(-1F, 0F, 0F, 2, 2, 7);
      TailSeg2.setRotationPoint(0F, -0.1F, 5.9F);
      TailSeg2.setTextureSize(64, 64);
      TailSeg2.mirror = true;
      setRotation(TailSeg2, 5/rad, 0F, 0F);
      TailSeg3 = new ModelRenderer(this, 0, 25);
      TailSeg3.addBox(-1F, 0F, 0F, 2, 1, 6);
      TailSeg3.setRotationPoint(0F, 0F, 6.7F);
      TailSeg3.setTextureSize(64, 64);
      TailSeg3.mirror = true;
      setRotation(TailSeg3, 6/rad, 0F, 0F);

      ToothL = new ModelRenderer(this, 43, 58);
      ToothL.addBox(0F, 0F, 0F, 1, 2, 1);
      ToothL.setRotationPoint(-2F, 1F, -2.8F);
      ToothL.setTextureSize(64, 64);
      ToothL.mirror = true;
      setRotation(ToothL, 0F, 0F, 0F);
      ToothR = new ModelRenderer(this, 43, 58);
      ToothR.addBox(0F, 0F, 0F, 1, 2, 1);
      ToothR.setRotationPoint(1F, 1F, -2.8F);
      ToothR.setTextureSize(64, 64);
      ToothR.mirror = true;
      setRotation(ToothR, 0F, 0F, 0F); 
      
      //adjust offset so rotation point is higher up the arm. but let arm stayy in same relative spot to body.
      //doing so has screwed up lower arm position..
      upperLarm = new ModelRenderer(this, 53, 27);
      upperLarm.addBox(0F, -1F, -1F, 2, 6, 3); //y -2
      upperLarm.setRotationPoint(4F, -4.32F, -0.78F); // y was - 3.32//-2.6 -2.2
      upperLarm.setTextureSize(64, 64);
      upperLarm.mirror = true;
      setRotation(upperLarm, -2/rad, 0.0F, -0.1927091F);
      lowerLarm = new ModelRenderer(this, 55, 37);
      lowerLarm.addBox(-1F, -1F, -1F, 2, 8, 2);
      lowerLarm.setRotationPoint(1.46F, 4.11F, 0.53F); // 2 2.6 1.8
      lowerLarm.setTextureSize(64, 64);
      lowerLarm.mirror = true;
      leftBracer = new ModelRenderer(this, 38, 0);
      leftBracer.addBox(-1.5F, 3.5F, -1.5F, 3, 4, 3);
      leftBracer.setRotationPoint(0F, 0F, 0F);
      leftBracer.setTextureSize(64, 64);
      leftBracer.mirror = true;
      setRotation(lowerLarm, -30/rad, 0.0297429F, -6/rad);
      Lefthand = new ModelRenderer(this, 55, 48);
      Lefthand.addBox(-1F, 0F, -1F, 2, 3, 2);
      Lefthand.setRotationPoint(0.0F, 6.8F, 0F); //2 6.4 -0.8 -17
      Lefthand.setTextureSize(64, 64);
      Lefthand.mirror = true;
      setRotation(Lefthand, 5/rad, 0, 17/rad); //y=40/rad
      Leftthumb = new ModelRenderer(this, 56, 54);
      Leftthumb.addBox(0.8F, -1F, -2.5F, 1, 1, 2);
      Leftthumb.setRotationPoint(-0.9F, 1F, 0F);
      Leftthumb.setTextureSize(64, 64);
      Leftthumb.mirror = true;
      setRotation(Leftthumb, 0.3569147F, 0.0F, 0F);
      upperRarm = new ModelRenderer(this, 53, 17);
      upperRarm.addBox(-2F, -1F, -1F, 2, 6, 3); //y was -2
      upperRarm.setRotationPoint(-4F, -4.32F, -0.78F); //y was -3.32
      upperRarm.setTextureSize(64, 64);
      upperRarm.mirror = true;
      setRotation(upperRarm, -2/rad, 0.0F, 0.1927091F);
      lowerRarm = new ModelRenderer(this, 46, 37);
      lowerRarm.addBox(-1F, -1F, -1F, 2, 8, 2);
      lowerRarm.setRotationPoint(-1.46F, 4.11F, 0.53F);
      lowerRarm.setTextureSize(64, 64);
      lowerRarm.mirror = true;
      setRotation(lowerRarm, -30/rad, 0.0297429F, 6/rad);
      rightBracer = new ModelRenderer(this, 25, 0);
      rightBracer.addBox(-1.5F, 3.5F, -1.5F, 3, 4, 3);
      rightBracer.setRotationPoint(0F, 0F, 0F);
      rightBracer.setTextureSize(64, 64);
      rightBracer.mirror = true;      
      Righthand = new ModelRenderer(this, 46, 48);
      Righthand.addBox(-1F, 0F, -1F, 2, 3, 2);
      Righthand.setRotationPoint(0.0F, 6.8F, -0.0F);
      Righthand.setTextureSize(64, 64);
      Righthand.mirror = true;
      setRotation(Righthand, 5/rad, 0, -17/rad); // y = -40/rad
      Rightthumb = new ModelRenderer(this, 47, 54);
      Rightthumb.addBox(-1.8F, -1F, -2.5F, 1, 1, 2);
      Rightthumb.setRotationPoint(0.9F, 1F, 0F);
      Rightthumb.setTextureSize(64, 64);
      Rightthumb.mirror = true;
      setRotation(Rightthumb, 0.3569147F, 0F, 0F);
      LowerJaw = new ModelRenderer(this, 46, 60);
      LowerJaw.addBox(-1F, 0F, -3F, 2, 1, 3);
      LowerJaw.setRotationPoint(0F, -0.41F, -6.8F);//-1.7 -6.6
      LowerJaw.setTextureSize(64, 64);
      LowerJaw.mirror = true;
      setRotation(LowerJaw, 0.4058472F + 11/rad, 0F, 0F);
      Snout = new ModelRenderer(this, 29, 58);
      Snout.addBox(-2F, -1F, -4F, 4, 2, 4);
      Snout.setRotationPoint(0F, -1.46F, -6.6F);// 2.7 6.2
      Snout.setTextureSize(64, 64);
      Snout.mirror = true;
      setRotation(Snout, (11/rad), 0F, 0F);
      head = new ModelRenderer(this, 0, 52);
      head.addBox(-3F, -3F, -7F, 6, 4, 8);
      head.setRotationPoint(0F, -4.92F, -1.08F);// -3.9 -3.2
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, (float) (-38/rad), 0F, 0F);
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-4F, -7F, -2F, 8, 7, 4);
      body.setRotationPoint(0F, -5.09F, 0.23F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, (13/rad), 0F, 0F);
      //here
      lowerbody = new ModelRenderer(this, 17, 16);
      lowerbody.addBox(-3.5F, -6F, -2F, 7, 9, 4);
      lowerbody.setRotationPoint(0F, 11F, 0F); //11.6 org.z = -2.8
      lowerbody.setTextureSize(64, 64);
      lowerbody.mirror = true;
      setRotation(lowerbody, 0.24434609F, 0F, 0F);
      rightleg = new ModelRenderer(this, 20, 30);
      rightleg.addBox(-3F, -2F, -2F, 3, 6, 3);
      rightleg.setRotationPoint(-1F, 3.15F, -1.40F); //-1 3.4 -0.6 ngl14
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, -(54/rad), 0F, -0.0F);
      LowerRLeg = new ModelRenderer(this, 21, 40);
      LowerRLeg.addBox(-2F, 0.0F, -0.3F, 3, 6, 2);
      LowerRLeg.setRotationPoint(-1.1F, 3.55F, -1.79F); //1.7 -3.6 ngl-38
      LowerRLeg.setTextureSize(64, 64);
      LowerRLeg.mirror = true;
      setRotation(LowerRLeg, 88/rad, 0F, 0F);
      UpperRFoot = new ModelRenderer(this, 21, 49);
      UpperRFoot.addBox(-2F, 0.06666667F, -1.253333F, 3, 5, 2);
      UpperRFoot.setRotationPoint(-0.1F, 5.5F, 2F); //2 5.5 ngl 50
      UpperRFoot.setTextureSize(64, 64);
      UpperRFoot.mirror = true;
      setRotation(UpperRFoot, -64/rad, 0F, 0F);
      rightfoot = new ModelRenderer(this, 0, 18);
      rightfoot.addBox(-2.8F, -1F, -3.5F, 4, 2, 4);
      rightfoot.setRotationPoint(0F, 5F, -0.5F);
      rightfoot.setTextureSize(64, 64);
      rightfoot.mirror = true;
      setRotation(rightfoot, 14/rad, 0F, 0F);
      leftleg = new ModelRenderer(this, 33, 30);
      leftleg.addBox(0F, -2F, -2F, 3, 6, 3);
      leftleg.setRotationPoint(1F, 3.15F, -1.4F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, -54/rad, 0F, 0F);
      LowerLLeg = new ModelRenderer(this, 34, 40);
      LowerLLeg.addBox(-1F, 0.0F, -0.3F, 3, 6, 2);
      LowerLLeg.setRotationPoint(1.1F, 3.55F, -1.79F);
      LowerLLeg.setTextureSize(64, 64);
      LowerLLeg.mirror = true;
      setRotation(LowerLLeg, 88/rad, 0F, 0F);
      UpperLfoot = new ModelRenderer(this, 34, 49);
      UpperLfoot.addBox(-1F, 0.06666667F, -1.253333F, 3, 5, 2);
      UpperLfoot.setRotationPoint(0.1F, 5.5F, 2F); //2 5.5 ngl 50
      UpperLfoot.setTextureSize(64, 64);
      UpperLfoot.mirror = true;
      setRotation(UpperLfoot, -64/rad, 0F, 0F);
      leftfoot = new ModelRenderer(this, 0, 12);
      leftfoot.addBox(-1.2F, -1F, -3.5F, 4, 2, 4);
      leftfoot.setRotationPoint(0F, 5F, -0.5F);
      leftfoot.setTextureSize(64, 64);
      leftfoot.mirror = true;
      setRotation(leftfoot, 14/rad, 0F, 0F); //org.y=-15/pi
      
      this.lowerbody.addChild(body);
      this.lowerbody.addChild(rightleg);
      this.lowerbody.addChild(leftleg);
      this.lowerbody.addChild(TailSeg1);
      this.TailSeg1.addChild(TailSeg2);
      this.TailSeg2.addChild(TailSeg3);
      
      this.rightleg.addChild(LowerRLeg);
      this.LowerRLeg.addChild(UpperRFoot);
      this.UpperRFoot.addChild(rightfoot);
      
      this.leftleg.addChild(LowerLLeg);
      this.LowerLLeg.addChild(UpperLfoot);
      this.UpperLfoot.addChild(leftfoot);      
      
      this.body.addChild(head);
      this.body.addChild(upperLarm);
      this.body.addChild(upperRarm);
            
      this.head.addChild(Snout);
      this.head.addChild(LowerJaw);
      this.Snout.addChild(ToothL);
      this.Snout.addChild(ToothR);
      
      this.upperRarm.addChild(lowerRarm);
      this.lowerRarm.addChild(Righthand);
      this.lowerRarm.addChild(rightBracer);
      this.Righthand.addChild(Rightthumb);
      this.Righthand.addChild(RightPinky);
      
      this.upperLarm.addChild(lowerLarm);
      this.lowerLarm.addChild(Lefthand);
      this.lowerLarm.addChild(leftBracer);
      this.Lefthand.addChild(Leftthumb);
      this.Lefthand.addChild(LeftPinky);
      }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    lowerbody.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  //multiply/divide within brackets to decrease/increase wavelength, and therefore frequency of repetition
  //multiply outside brackets to increase amplitude - the range of the movement
  // f seems to be time in motion, whereas f2 is time existed. idle animations do not function using f unless motion is occuring
  //minecraft does not like my use of the "weighted" method. it was all that was causing the arms to hover between locs...

  
  public void setRotationAngles(float time, float motion, float age, float f3, float f4, float f5, Entity ntt)  {
	  
	  EntityLizardmanScuffler nttLz = (EntityLizardmanScuffler)ntt;
	  boolean darkout = nttLz.getDarkOut() < 0.8f;
	  float cE = nttLz.getDarkOut(); //this call is only going thru during the day and quite infrequently compared to stalkers.
	  int t = nttLz.getLivingSoundTime();
	  float nttid = nttLz.getEntityId()/10000;
	  //System.out.println(age);
	  //System.out.println(nttLz.getEntityId());
	  
	  float r = 0.613306f;
	  float pi = (float) Math.PI;

	  //walking
	  this.rightleg.rotateAngleX = -(1.4F * MathHelper.sin(time * r) * motion) - 54/rad;
	  this.leftleg.rotateAngleX = 1.4F * MathHelper.sin(time * r) * motion - 54/rad;
	  this.LowerRLeg.rotateAngleX = -(0.5F * MathHelper.sin(0.5F + time * r) * motion) + 88/rad;
	  this.LowerLLeg.rotateAngleX = 0.5F * MathHelper.sin(0.5F + time * r) * motion + 88/rad;
	  this.UpperRFoot.rotateAngleX = 0.75F * MathHelper.sin(0.55F + time * r) * motion - 64/rad;
	  this.UpperLfoot.rotateAngleX = -0.75F * MathHelper.sin(0.55F + time * r) * motion - 64/rad;
	  this.rightfoot.rotateAngleX = 1.75F * MathHelper.sin(time * r + 0.3F) * motion + 14/rad;
	  this.leftfoot.rotateAngleX = -(1.75F * MathHelper.sin(time * r + 0.3F) * motion) + 14/rad;
	  
	  this.leftleg.rotateAngleZ = 0;
	  this.rightleg.rotateAngleZ = 0;
	  
	  
	  if (LowerRLeg.rotateAngleX > 95/rad){LowerRLeg.rotateAngleX = 95/rad;}
	  if (LowerLLeg.rotateAngleX > 95/rad){LowerLLeg.rotateAngleX = 95/rad;}	  
	  
	  if (leftfoot.rotateAngleX < 0/rad){leftfoot.rotateAngleX = 0/rad;}
	  if (rightfoot.rotateAngleX < 0/rad){rightfoot.rotateAngleX = 0/rad;}

	  //bob
	  this.lowerbody.rotationPointY = 11F - MathHelper.sin(time * (2 * r)) * 2F * motion;
	  this.lowerbody.rotateAngleZ = 0;	
	  
	  this.upperLarm.rotationPointY = this.upperRarm.rotationPointY = -4.32F;

	  
	  //tail swing
	 if (darkout) {
		  this.TailSeg1.rotateAngleY = (float) (MathHelper.sin((float) ((age/ 2/Math.PI))+nttid)) * 0.3f + ((1.5f - cE)/3);
		  this.TailSeg2.rotateAngleY = (float) (MathHelper.sin((float) (-1F + (age / 2/Math.PI)+nttid)) * 0.3 + ((1.5 - cE)/2.3));
		  this.TailSeg3.rotateAngleY = (float) (MathHelper.sin((float) (-1.6F + (age / 2/Math.PI)+nttid)) * 0.3 + ((1.5 - cE)/1.875));
		  }
	  else {//if(!darkout) {
		  this.TailSeg1.rotateAngleY = MathHelper.sin(nttid + (age/2/pi)) * 0.3f;
	  	  this.TailSeg2.rotateAngleY = (float) (MathHelper.sin((float) (-1F + (age / 2/Math.PI)+nttid)) * 0.3);
		  this.TailSeg3.rotateAngleY = (float) (MathHelper.sin((float) (-1.6F + (age / 2/Math.PI)+nttid)) * 0.3);
		  }
		  	  //System.out.println((float) (MathHelper.sin((float) (age/2/Math.PI))+nttLz.getEntityId()) * 0.3f);
	  
		  
	  //breathing
	  this.body.rotateAngleX = (float) (MathHelper.sin((float) (age / 2/Math.PI)+nttLz.getEntityId()) * 0.02 + 13/rad);
	 
	  //chat
	  this.LowerJaw.rotateAngleX = -MathHelper.sin((float) ((t/Math.PI) * 0.25F + 44/rad))*2.5f;// - motion/2; //was motion/2
	  if (LowerJaw.rotateAngleX < 5/rad){this.LowerJaw.rotateAngleX = 5/rad;}
	  if (LowerJaw.rotateAngleX > 45/rad){this.LowerJaw.rotateAngleX = 45/rad;}
	  
	  //using f4 on head angleX causes the head to rotate when the whole model turns.
	  this.head.rotateAngleX = (f3/ (270F/(float)Math.PI)) * 0.5F - 38/rad;
	  this.head.rotateAngleY = (f3 / (270F/(float)Math.PI)) * 0.5F;
	  this.head.rotateAngleZ = (f3 / (240F / (float)Math.PI)) * 0.5F;
	  
	  int swing = nttLz.getAnimTimer();
	  int aiming = nttLz.getHasTarget();
	  int lt = nttLz.getLaughTime();


	  //System.out.println(swing);
	  
	  //mocking/combat
	  if(aiming == 4)
	  {
		  this.lowerbody.rotationPointY = 11F - MathHelper.sin(time * (2 * r)) * 2F * motion;

		  this.upperRarm.rotateAngleX = this.upperLarm.rotateAngleX = - 95/rad;
		  this.lowerRarm.rotateAngleX = this.lowerLarm.rotateAngleX = - 15/rad;
		  this.upperRarm.rotateAngleY = this.upperLarm.rotateAngleY = 0;
		  
		  this.leftleg.rotateAngleZ = -((float) MathHelper.sin(swing*0.2F))*.165f;
		  this.rightleg.rotateAngleZ = -((float) MathHelper.sin(swing*0.2F))*.165f;
		  this.lowerbody.rotateAngleZ = ((float) MathHelper.sin(swing*0.2F))*.1f;
		  this.leftleg.rotateAngleX = ((float) MathHelper.sin(swing*0.2F))*.5f - 54/rad;
		  this.rightleg.rotateAngleX = -((float) MathHelper.sin(swing*0.2F))*.5f - 54/rad;

		 if (this.rightleg.rotateAngleX > -54/rad){this.rightleg.rotateAngleX = -54/rad;}
		 if (this.leftleg.rotateAngleX > -54/rad){this.leftleg.rotateAngleX = -54/rad;}
			  
		  this.Righthand.rotateAngleY = -50/rad;
		  this.Lefthand.rotateAngleY = 50/rad;
		  
		  	if(swing < 25)
		  	{
		  		this.upperLarm.rotationPointY = -4.32F - MathHelper.sin(swing * (2 * r)) * .5F;
		  		this.upperRarm.rotationPointY = -4.32F - MathHelper.sin(swing * (2 * r)) * .5F;
		  		this.head.rotateAngleX = MathHelper.sin(swing * (2 * r)) * .2F - 38/rad;
		  	}
		  
		}
	  else
		  if(aiming == 5)
	  {
			  
			  this.lowerLarm.rotateAngleX = -motion - 30/rad;
			  this.lowerRarm.rotateAngleX = -motion - 30/rad;

			  
	  		this.upperLarm.rotationPointY = -4.32F - MathHelper.sin(lt * (2 * r)) * .5F;
	  		this.upperRarm.rotationPointY = -4.32F - MathHelper.sin(lt * (2 * r)) * .5F;
	  		this.head.rotateAngleX = MathHelper.sin(lt * (2 * r)) * .2F - 38/rad;
	  		
	  		this.upperLarm.rotateAngleX = motion/1.5F - -2/rad;
	  		this.upperRarm.rotateAngleX = motion/1.5F -2/rad;
	  		}
		  else
			  if(aiming == 1)
			  {  
				  this.upperRarm.rotateAngleX = motion/1.5F -40/rad;
		  this.upperRarm.rotateAngleY = 0.325F;
		  this.lowerRarm.rotateAngleX = -30/rad;
		  
		  this.Righthand.rotateAngleY = -15/rad;
		  
		  this.upperLarm.rotateAngleX = motion/1.5F - -2/rad;
		  this.upperLarm.rotateAngleY = -0.6325F;
		  this.lowerLarm.rotateAngleX = -motion + -30/rad;
	  } else
		  if(aiming == 2)
		  { //aggro'd
			  this.upperLarm.rotateAngleX = motion/1.5F - -2/rad - 0.5F;
			  this.lowerLarm.rotateAngleX = -motion + -30/rad - 0.5F;
		  
		  this.Righthand.rotateAngleY = -5/rad;
		 
		  this.Lefthand.rotateAngleY = 65/rad;
		  this.Lefthand.rotateAngleX = 85/rad;
		  this.Lefthand.rotateAngleZ = 85/rad;
		  
		  
		  this.upperRarm.rotateAngleX = -90/rad;
		  this.upperRarm.rotateAngleY = 0.325F;
		  this.upperRarm.rotateAngleZ = 0.35F;
		  
		  this.lowerRarm.rotateAngleX = -35/rad;

		  if(swing > 0) {
			  this.upperRarm.rotateAngleX = -90/rad + ((float) MathHelper.sin(swing*0.1F))*1.2f;
			  this.upperRarm.rotateAngleY = 0.325F;// + swing/rad; //((float) MathHelper.sin(swing*0.25F));
			  this.upperRarm.rotateAngleZ = 0.35F;
			  this.upperLarm.rotateAngleZ = -0.35F;
			  this.upperLarm.rotateAngleY = -0.35F;
			  }
		  }
			  else
			  {
				  this.upperLarm.rotateAngleX = motion/1.5F - -2/rad;
				  this.lowerLarm.rotateAngleX = -motion - 30/rad;
				  this.lowerRarm.rotateAngleX = -motion - 30/rad;

				  this.upperRarm.rotateAngleX = motion/1.5F -2/rad;
				  this.upperRarm.rotateAngleY = this.upperLarm.rotateAngleY = 0; //this.weighted(this.upperRarm.rotateAngleY, 0.0F, 5);
				  this.upperRarm.rotateAngleZ = 0.1927091F;
				  this.upperLarm.rotateAngleZ = -0.1927091F;
			  
				  this.Righthand.rotateAngleY = -30/rad;
				  this.Lefthand.rotateAngleY = 30/rad;
				  this.Lefthand.rotateAngleX = 5/rad;
				  this.Lefthand.rotateAngleZ = 17/rad;
			  
				  this.leftleg.rotateAngleZ = this.rightleg.rotateAngleZ = 0;

//				  this.upperLarm.rotationPointY =			  				this.upperRarm.rotationPointY = -4.32F;
				  }	  
	  
	  super.setRotationAngles(time, motion, age, f3, f4, f5, ntt);

	 }

  public float weighted(float f1, float f2, int i) {	  
	  return ((f1*i) + f2)/(i+1);
	  }
}
