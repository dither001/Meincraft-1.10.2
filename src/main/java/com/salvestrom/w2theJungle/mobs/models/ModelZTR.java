//the textures for the underside of ALL boxes need to be rotated 180
package com.salvestrom.w2theJungle.mobs.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

import com.salvestrom.w2theJungle.mobs.entity.EntityLenny;

public class ModelZTR extends ModelBase
{
//	public int k;
	
  //fields
	
	ModelRenderer skullSnout;
    ModelRenderer skulljawlower;
    ModelRenderer skullHead;
    ModelRenderer skulljawUpper;
	
    ModelRenderer snout;
    ModelRenderer noseplate;
    ModelRenderer jawUpper;
    ModelRenderer jawlower;
    ModelRenderer teethupper;
    ModelRenderer teethlower;
    public ModelRenderer head;
    public ModelRenderer neck;
    public ModelRenderer upperbody;
    public ModelRenderer lowerbody;
    ModelRenderer rearbody;
    ModelRenderer tailSeg1;
    ModelRenderer tailSeg2;
    ModelRenderer tailSeg3;
    ModelRenderer tailSeg4;
    public ModelRenderer tailSeg5;
    ModelRenderer upperlegR;
    ModelRenderer lowerlegR;
    ModelRenderer upperFootR;
    ModelRenderer lowerfootR;
    ModelRenderer upperlegL;
    ModelRenderer lowerlegL;
    ModelRenderer upperFootL;
    ModelRenderer lowerfootL;
    ModelRenderer shoulderR;
    ModelRenderer upperarmR;
    ModelRenderer lowerarmR;
    ModelRenderer handright;
    ModelRenderer shoulderL;
    ModelRenderer upperarmL;
    ModelRenderer lowerarmL;
    ModelRenderer handleft;
    ModelRenderer underneck;
    ModelRenderer underupperbody;
    ModelRenderer underrearbody;
    ModelRenderer undertailseg1;
    ModelRenderer undertailSeg2;
    public ModelRenderer eye;
  
  public ModelZTR()
  {
    textureWidth = 256;
    textureHeight = 256;
    
    //latterly added skull:
      skullHead = new ModelRenderer(this, 0, 206);
      //skullHead.mirror = true;
      skullHead.addBox(-7.5F, -10F, -12F, 15, 10, 12);
      skullHead.setRotationPoint(0F, 0F, 0F); //0 -21 -48
      skullHead.setTextureSize(256, 256);
      setRotation(skullHead, 0F, 0F, 0F);
      skullSnout = new ModelRenderer(this, 183, 169);
      skullSnout.addBox(-4F, -5F, -11F, 8, 10, 16);
      skullSnout.setRotationPoint(0F, 0F, 0F); //0 -24 -63 //head 0 -21 -48
      skullSnout.setTextureSize(256, 256);
      skullSnout.mirror = true;
      setRotation(skullSnout, 0F, 0F, 0F);
      skulljawUpper = new ModelRenderer(this, 56, 210);
      skulljawUpper.addBox(-7.0F, -4F, -9F, 14, 8, 10);
      skulljawUpper.setRotationPoint(0F, 0F, 0F); //0 -19 -50
      skulljawUpper.setTextureSize(256, 256);
      skulljawUpper.mirror = true;
      setRotation(skulljawUpper, 0F, 0F, 0F);
      skulljawlower = new ModelRenderer(this, 183, 200);
      skulljawlower.addBox(-4.5F, -1F, -18F, 9, 5, 21);
      skulljawlower.setRotationPoint(0F, 0F, 0F); // 0 -18 -55
      skulljawlower.setTextureSize(256, 256);
      skulljawlower.mirror = true;
      setRotation(skulljawlower, 0F, 0F, 0F);
      noseplate = new ModelRenderer(this, 112, 205);
      noseplate.addBox(-2.5F, -3F, -8F, 5, 4, 10);
      noseplate.setRotationPoint(0F, -4.6F, -2.6F); //0 -28 -66 //-4
      noseplate.setTextureSize(256, 256);
      noseplate.mirror = true;
      setRotation(noseplate, 0.1047197F, 0F, 0F); //0.1919862
      teethupper = new ModelRenderer(this, 112, 174);
      teethupper.addBox(-3F, -3F, -11F, 6, 4, 12);
      teethupper.setRotationPoint(0F, 6F, 1F); //org. 0 -19 -62
      teethupper.setTextureSize(256, 256);
      teethupper.mirror = true;
      setRotation(teethupper, 0.0523598F, 0F, 0F); //0.1396263
      teethlower = new ModelRenderer(this, 112, 191);
      teethlower.addBox(-3.5F, -1F, -18F, 7, 3, 10);
      teethlower.setRotationPoint(0F, -2F, 1F); //0 -20 -54
      teethlower.setTextureSize(256, 256);
      teethlower.mirror = true;
      setRotation(teethlower, 0F, 0F, 0F);
      
      head = new ModelRenderer(this, 0, 232);
      head.addBox(-8F, -10.5F, -12.5F, 16, 11, 13);
      head.setRotationPoint(0F, -0.5F, -14F); //0 -21 -48
      head.setTextureSize(256, 256);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      jawUpper = new ModelRenderer(this, 59, 236);
      jawUpper.addBox(-7.5F, -4.5F, -9.5F, 15, 9, 11);
      jawUpper.setRotationPoint(0F, 2F, -2F); //0 -19 -50
      jawUpper.setTextureSize(256, 256);
      jawUpper.mirror = true;
      setRotation(jawUpper, 0F, 0F, 0F);
      snout = new ModelRenderer(this, 112, 228);
      snout.addBox(-4.5F, -5.5F, -11.5F, 9, 11, 17);
      snout.setRotationPoint(0F, -3F, -15F); //0 -24 -63 //head 0 -21 -48
      snout.setTextureSize(256, 256);
      snout.mirror = true;
      setRotation(snout, 0.0872665F, 0F, 0F);
      jawlower = new ModelRenderer(this, 183, 228);
      jawlower.addBox(-5.F, -1.5F, -18.5F, 10, 6, 22);
      jawlower.setRotationPoint(0F, 1F, -5F); // 0 -18 -55
      jawlower.setTextureSize(256, 256);
      jawlower.mirror = true;
      setRotation(jawlower, 0F, 0F, 0F);
      
      
      neck = new ModelRenderer(this, 0, 174);
      neck.addBox(-6F, -10F, -16F, 12, 15, 16);
      neck.setRotationPoint(0F, 1F, -17F); //0 -21 -33 
      neck.setTextureSize(256, 256);
      neck.mirror = true;
      setRotation(neck, 0F, 0F, 0F);
      upperbody = new ModelRenderer(this, 0, 134);
      upperbody.addBox(-8F, -10F, -20F, 16, 19, 20);
      upperbody.setRotationPoint(0F, -3F, -11F); //0 -22 -14
      upperbody.setTextureSize(256, 256);
      upperbody.mirror = true;
      setRotation(upperbody, 0F, 0F, 0F);
      lowerbody = new ModelRenderer(this, 0, 77);
      lowerbody.mirror = false;
      lowerbody.addBox(-9F, -13F, -15F, 18, 26, 30);
      lowerbody.setRotationPoint(0F, -20F, 0F); //0 -20 0
      lowerbody.setTextureSize(256, 256);
      setRotation(lowerbody, 0F, 0F, 0F);
      rearbody = new ModelRenderer(this, 0, 35);
      rearbody.addBox(-7.5F, -10F, 0F, 15, 21, 20);
      rearbody.setRotationPoint(0F, -2F, 10F); //0 -22 10
      rearbody.setTextureSize(256, 256);
      rearbody.mirror = true;
      setRotation(rearbody, 0F, 0F, 0F);
      tailSeg1 = new ModelRenderer(this, 0, 0);
      tailSeg1.addBox(-6.5F, -8F, 0F, 13, 16, 18);
      tailSeg1.setRotationPoint(0F, -1F, 19F); //0 -23 29
      tailSeg1.setTextureSize(256, 256);
      tailSeg1.mirror = true;
      setRotation(tailSeg1, 0F, 0F, 0F);
      tailSeg2 = new ModelRenderer(this, 63, 2);
      tailSeg2.addBox(-5F, -6F, 0F, 10, 12, 20);
      tailSeg2.setRotationPoint(0F, -1F, 17F); //0 -24 46
      tailSeg2.setTextureSize(256, 256);
      tailSeg2.mirror = true;
      setRotation(tailSeg2, 0F, 0F, 0F);
      tailSeg3 = new ModelRenderer(this, 124, 4);
      tailSeg3.addBox(-4F, -5F, 0F, 8, 9, 21);
      tailSeg3.setRotationPoint(0F, 0F, 18F); //0 -24 64
      tailSeg3.setTextureSize(256, 256);
      tailSeg3.mirror = true;
      setRotation(tailSeg3, 0F, 0F, 0F);
      tailSeg4 = new ModelRenderer(this, 183, 5);
      tailSeg4.addBox(-3F, -4F, 0F, 6, 7, 22);
      tailSeg4.setRotationPoint(0F, -0.5F, 20F); //0 -24.5 84
      tailSeg4.setTextureSize(256, 256);
      tailSeg4.mirror = true;
      setRotation(tailSeg4, 0F, 0F, 0F);
      tailSeg5 = new ModelRenderer(this, 183, 35);
      tailSeg5.addBox(-2F, -3F, 0F, 4, 5, 22);
      tailSeg5.setRotationPoint(0F, -0.5F, 21F); //0 -25 105
      tailSeg5.setTextureSize(256, 256);
      tailSeg5.mirror = true;
      setRotation(tailSeg5, 0F, 0F, 0F);
      upperlegR = new ModelRenderer(this, 183, 63);
      upperlegR.mirror = true;
      upperlegR.addBox(-4F, -2F, -5F, 7, 24, 11);
      upperlegR.setRotationPoint(-11F, -2F, 3F); //-11 -22 3  lower b: 0 -20 0
      upperlegR.setTextureSize(256, 256);
      upperlegR.mirror = true;
      setRotation(upperlegR, -0.3490659F, 0F, 0F);
      lowerlegR = new ModelRenderer(this, 183, 99);
      lowerlegR.mirror = true;
      lowerlegR.addBox(-3.4F, -1F, -1F, 6, 16, 9);
      lowerlegR.setRotationPoint(0F, 21.5F, -3F); //0 18 -6 (-11 -3 -7.4)
      lowerlegR.setTextureSize(256, 256);
      lowerlegR.mirror = true;
      setRotation(lowerlegR, 0.6108652F, 0F, 0F);
      upperFootR = new ModelRenderer(this, 183, 125);
      upperFootR.mirror = true;
      upperFootR.addBox(-3.8F, -3F, -5F, 7, 18, 6);
      upperFootR.setRotationPoint(0F, 16F, 5.5F); //0 11 8      :-11 7 4
      upperFootR.setTextureSize(256, 256);
      upperFootR.mirror = true;
      setRotation(upperFootR, -0.2617994F, 0F, 0F);
      lowerfootR = new ModelRenderer(this, 183, 150); 
      lowerfootR.mirror = true;
      lowerfootR.addBox(-7.5F, 0F, -12F, 12, 4, 14);
      lowerfootR.setRotationPoint(0F, 13.25F, -1.25F); //-11 20 2
      lowerfootR.setTextureSize(256, 256);
      lowerfootR.mirror = true;
      setRotation(lowerfootR, 0F, 0F, 0F);
      upperlegL = new ModelRenderer(this, 183, 63);
      upperlegL.addBox(-3F, -2F, -5F, 7, 24, 11);
      upperlegL.setRotationPoint(11F, -2F, 3F); //11 -22 3 lower b: 0 -20 0
      upperlegL.setTextureSize(256, 256);
      upperlegL.mirror = true;
      setRotation(upperlegL, -0.3490659F, 0F, 0F);
      lowerlegL = new ModelRenderer(this, 183, 99);
      lowerlegL.addBox(-2.5F, -1F, -1F, 6, 16, 9);
      lowerlegL.setRotationPoint(0F, 21.5F, -3F); // 0 18 -6 (11 -4 -3)
      lowerlegL.setTextureSize(256, 256);
      lowerlegL.mirror = true;
      setRotation(lowerlegL, 0.6108652F, 0F, 0F);
      upperFootL = new ModelRenderer(this, 183, 125);
      upperFootL.addBox(-3.2F, -3F, -5F, 7, 18, 6);
      upperFootL.setRotationPoint(0F, 16F, 5.5F);//11 7 5  (11 7 7)
      upperFootL.setTextureSize(256, 256);
      upperFootL.mirror = true;
      setRotation(upperFootL, -0.2617994F, 0F, 0F);
      lowerfootL = new ModelRenderer(this, 183, 150);
      lowerfootL.addBox(-4.5F, 0F, -12F, 12, 4, 14);
      lowerfootL.setRotationPoint(0F, 13.25F, -1.25F);//11F, 20F, 2F (15 22 -1)
      lowerfootL.setTextureSize(256, 256);
      lowerfootL.mirror = true;
      setRotation(lowerfootL, 0F, 0F, 0F);
      shoulderR = new ModelRenderer(this, 166, 73);
      shoulderR.mirror = true;
      shoulderR.addBox(-2F, -2F, -2F, 4, 4, 4);
      shoulderR.setRotationPoint(-9F, 4F, -14F); //-9 -19 -28 ub: 0 -22 -14
      shoulderR.setTextureSize(256, 256);
      shoulderR.mirror = true;
      setRotation(shoulderR, 0F, 0F, 0F);
      upperarmR = new ModelRenderer(this, 168, 82);
      upperarmR.mirror = true;
      upperarmR.addBox(-1.533333F, -2F, -2F, 3, 6, 4);
      upperarmR.setRotationPoint(-0.5F, 4F, 0F); //-9.5 -16 -28
      upperarmR.setTextureSize(256, 256);
      upperarmR.mirror = true;
      setRotation(upperarmR, 0F, 0F, 0.0872665F);
      lowerarmR = new ModelRenderer(this, 168, 93);
      lowerarmR.mirror = true;
      lowerarmR.addBox(-2F, -2F, -2F, 3, 7, 4);
      lowerarmR.setRotationPoint(0F, 4F, 0F); //-9.5 -12 -28
      lowerarmR.setTextureSize(256, 256);
      lowerarmR.mirror = true;
      setRotation(lowerarmR, -0.3141593F, 0F, 0.0872665F);
      handright = new ModelRenderer(this, 168, 105);
      handright.mirror = true;
      handright.addBox(-2F, -1F, -2F, 3, 4, 4);
      handright.setRotationPoint(0F, 5F, 0F); //-9.5 -7 -29
      handright.setTextureSize(256, 256);
      handright.mirror = true;
      setRotation(handright, -0.3141593F, -0.0698132F, -0.2094395F);
      shoulderL = new ModelRenderer(this, 166, 73);
      shoulderL.addBox(-2F, -2F, -2F, 4, 4, 4);
      shoulderL.setRotationPoint(9F, 4F, -14F); //9 -20 -28 org. ub.: 0 -22 -14
      shoulderL.setTextureSize(256, 256);
      shoulderL.mirror = true;
      setRotation(shoulderL, 0F, 0F, 0F);
      upperarmL = new ModelRenderer(this, 168, 82);
      upperarmL.addBox(-1.533333F, -2F, -2F, 3, 6, 4);
      upperarmL.setRotationPoint(0.5F, 4F, 0F); //9.5 -16 -28
      upperarmL.setTextureSize(256, 256);
      upperarmL.mirror = true;
      setRotation(upperarmL, 0F, 0F, -0.0872665F);
      lowerarmL = new ModelRenderer(this, 168, 93);
      lowerarmL.addBox(-1F, -2F, -2F, 3, 7, 4);
      lowerarmL.setRotationPoint(0F, 4F, 0F); //9.5 -12 -28
      lowerarmL.setTextureSize(256, 256);
      lowerarmL.mirror = true;
      setRotation(lowerarmL, -0.3141593F, 0F, -0.0872665F);
      handleft = new ModelRenderer(this, 168, 105);
      handleft.addBox(-1F, -1F, -2F, 3, 4, 4);
      handleft.setRotationPoint(0F, 5F, 0); //9.5 -7 -29
      handleft.setTextureSize(256, 256);
      handleft.mirror = true;
      
      //understuff
      setRotation(handleft, -0.3141593F, 0.0698132F, 0.2094395F);
      underneck = new ModelRenderer(this, 57, 174);
      underneck.addBox(-5F, -3F, -15F, 10, 4, 17);
      underneck.setRotationPoint(0F, 6F, -6F); //0 -15 -35 neck: 0 -21 -37
      underneck.setTextureSize(256, 256);
      underneck.mirror = true;
      setRotation(underneck, -0.1745329F, 0F, 0F);
      underupperbody = new ModelRenderer(this, 73, 147);
      underupperbody.mirror = true;
      underupperbody.addBox(-6.5F, -3F, -10F, 13, 5, 21);
      underupperbody.setRotationPoint(0F, 9F, -9F); //0 -13 -23 ub: 0 -22 -24
      underupperbody.setTextureSize(256, 256);
      underupperbody.mirror = true;
      setRotation(underupperbody, -0.2617994F, 0F, 0F);
      underrearbody = new ModelRenderer(this, 71, 57);
      underrearbody.addBox(-6.48F, -3F, -10F, 13, 4, 15);
      underrearbody.setRotationPoint(0F, 11F, 13F); //0 -11 23 rear:0 -22 10 lb: 0 -20 10
      underrearbody.setTextureSize(256, 256);
      underrearbody.mirror = true;
      setRotation(underrearbody, 0.2094395F, 0F, 0F);
      undertailseg1 = new ModelRenderer(this, 71, 35);
      undertailseg1.addBox(-5.013333F, -3F, -1F, 10, 4, 16);
      undertailseg1.setRotationPoint(0F, 10F, 1F); //0 -13 30 tail: 0 -23 29
      undertailseg1.setTextureSize(256, 256);
      undertailseg1.mirror = true;
      setRotation(undertailseg1, 0.2094395F, 0F, 0F);
      undertailSeg2 = new ModelRenderer(this, 124, 35);
      undertailSeg2.addBox(-3.546667F, -2F, -2F, 7, 4, 14);
      undertailSeg2.setRotationPoint(0F, 6F, 1F);  //0 -18 47 tail: 0 -24 46
      undertailSeg2.setTextureSize(256, 256);
      undertailSeg2.mirror = true;
      setRotation(undertailSeg2, 0.1745329F, 0F, 0F);
      eye = new ModelRenderer(this, 48, 206);
      eye.addBox(-1F, -1F, -1F, 2, 2, 2);
      eye.setRotationPoint(-6F, -6F, -10F); // -6 -27 -58 head: 0 -21 -48
      eye.setTextureSize(256, 256);
      eye.mirror = true;
      setRotation(eye, 0F, 0F, 0F);
      
      this.skullSnout.addChild(teethupper);
      this.snout.addChild(skullSnout); this.skullSnout.addChild(noseplate);
      this.skulljawlower.addChild(teethlower);
      this.jawlower.addChild(skulljawlower);
      this.jawUpper.addChild(jawlower); this.jawUpper.addChild(skulljawUpper); 
      this.head.addChild(snout); this.head.addChild(skullHead);
      this.head.addChild(jawUpper);
      this.head.addChild(eye);
      this.neck.addChild(head);
      this.neck.addChild(underneck);
      this.upperbody.addChild(neck); this.upperbody.addChild(underupperbody);
      this.lowerbody.addChild(upperbody);
      this.lowerbody.addChild(upperlegL); this.upperlegL.addChild(lowerlegL); this.lowerlegL.addChild(upperFootL); this.upperFootL.addChild(lowerfootL);
      this.lowerbody.addChild(upperlegR); this.upperlegR.addChild(lowerlegR); this.lowerlegR.addChild(upperFootR); this.upperFootR.addChild(lowerfootR);
      this.lowerbody.addChild(rearbody); this.rearbody.addChild(underrearbody);
      
      this.rearbody.addChild(tailSeg1);
      this.tailSeg1.addChild(tailSeg2); this.tailSeg1.addChild(undertailseg1);
      this.tailSeg2.addChild(tailSeg3); this.tailSeg2.addChild(undertailSeg2);
      this.tailSeg3.addChild(tailSeg4);
      this.tailSeg4.addChild(tailSeg5);
      
      //arms
      this.upperbody.addChild(shoulderL);
      this.shoulderL.addChild(upperarmL); this.upperarmL.addChild(lowerarmL); this.lowerarmL.addChild(handleft);
      this.upperbody.addChild(shoulderR);
      this.shoulderR.addChild(upperarmR); this.upperarmR.addChild(lowerarmR); this.lowerarmR.addChild(handright);
      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);

    //head.render(f5);
    
    lowerbody.render(f5);
    
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public int k;
  public float tail;
  float jaw;
  float lookaby;

  // the variable "turn" represents the model turning. in other words, angles set with turn will cause body parts to rotate as the model changes direction.
  //lookie is pitch.
  //unknown is a constant passed over by the render. always 0.0625
  public void setRotationAngles(float time, float motion, float age, float turn, float lookie, float unknown, Entity ntt)
  {
    super.setRotationAngles(time, motion, age, turn, lookie, unknown, ntt);
    
    Random rng = new Random();
    float rads = (float) (180F/Math.PI);
    float modifier = 0.6F;
    EntityLenny len = (EntityLenny)ntt;
    int timea = len.getAnimationTimer();
    int timer = len.getRoarAnimationTimer();
    int attacktype = len.getAttType();
    
	float nttid = len.getEntityId()/10000;
	
	len.rotationYaw = len.ticksExisted;
    
	//System.out.println(timer);

    float neckX = this.neck.rotateAngleX;
    float ubody = this.upperbody.rotateAngleX;
    float uLLeg = this.upperlegL.rotateAngleX;
	float uRLeg = this.upperlegR.rotateAngleX;
	
    float tail1 = this.tailSeg1.rotateAngleX;
    float tail2 = this.tailSeg2.rotateAngleX;
    float tail3 = this.tailSeg3.rotateAngleX;
    float tail4 = this.tailSeg4.rotateAngleX;
	float tail5 = this.tailSeg5.rotateAngleX;
			
	int headdown = 4;
    
	//eyespin
		this.eye.rotateAngleX  = age/2.5f;
		this.eye.rotateAngleY  = age/2;
		this.eye.rotateAngleZ  = age/1.5f;
	
    this.lookaby = //(this.lookaby*2F + ((lookie+turn)/rads))/3F; //this is weighted, (old*2 + n) / 3
    		((lookie+turn)/rads);
    
	  //waddle
	  this.lowerbody.rotateAngleZ = 0 + motion/2f * 0.35F * (this.func_78172_a(time, 13.0F));
	  this.lowerbody.rotateAngleY = -motion/2f * 0.5315F * (this.func_78172_a(time, 13.0F));


    //looking + waddle compensation
  	this.head.rotateAngleY = 0.45F*(this.lookaby) - this.lowerbody.rotateAngleY/2;
  	//TODO blanked out:
	this.head.rotateAngleZ = 0;//(((this.head.rotateAngleZ*10))/11);// - this.lowerbody.rotateAngleZ/2;
    this.neck.rotateAngleY = 0.45F*(this.lookaby) - this.lowerbody.rotateAngleY;
	this.neck.rotateAngleZ = 0 - this.lowerbody.rotateAngleZ;

	this.head.rotateAngleX = (float) 14/rads-(motion/headdown) - (len.isSwimming() ? 10/rads : 0);// + this.lowerbody.rotateAngleX;
	this.neck.rotationPointZ = -17;
  	
	this.jaw = this.jawUpper.rotateAngleX;
	float lbody = this.lowerbody.rotateAngleX;
	
		this.jawUpper.rotateAngleX = 4.5f/rads - (float) (Math.sin((age/rads) * 5f)*.05f) - (motion/headdown)*0.25f;//this.weighted(this.jaw, -11/rads-this.upperbody.rotateAngleX+motion/5, 5);
		//TODO talking

		//breathing++
		this.upperbody.rotateAngleX = //this.weighted(ubody,
				(MathHelper.sin((float)(age / 2/Math.PI)+nttid) * 0.02f - 15/rads)+(motion/headdown) + (len.isSwimming() ? 10/rads : 0);
				//, 5);
		this.lowerbody.rotateAngleX = (float) //((this.lowerbody.rotateAngleX*10) +
				(-15/rads + (motion/headdown) + (len.isSwimming() ? 10/rads : 0));//)/11;
		this.neck.rotateAngleX = (float) 12/rads-(motion/headdown)
				- (len.isSwimming() ? 7/rads : 0);// - this.lowerbody.rotateAngleX;
		
	//bob
	this.lowerbody.rotationPointY = -20F + MathHelper.cos(time * (2F * modifier)) * 5F * motion;
	//this.upperbody.rotationPointY = -this.lowerbody.rotationPointY-20F;
	
	//rem: inside bracket is frequency. outside is amplitude.
	float walkiesU = (1.4F * MathHelper.cos(0.56F + time * modifier)) * 0.7F * (motion);
	float walkies = (1.4F * MathHelper.cos(time * modifier)) * 0.7F * (motion);
	//legs... subtraction of the lowerbody formula counters the lowerbody movement caused by breathing et al.
	this.upperlegL.rotateAngleX = -0.3490659F - walkiesU*0.7F - this.lowerbody.rotateAngleX;
	this.upperlegR.rotateAngleX = -0.3490659F + walkiesU*0.7F - this.lowerbody.rotateAngleX;
	this.lowerlegL.rotateAngleX = 0.6108652F + 0.3490659F - walkies*0.6329F;
	this.lowerlegR.rotateAngleX = 0.6108652F + 0.3490659F + walkies*0.6329F;
    this.upperFootL.rotateAngleX = -0.2617994F - 0.6108652F + walkies*0.59F;
    this.upperFootR.rotateAngleX = -0.2617994F - 0.6108652F - walkies*0.59F;
    
    this.lowerfootL.rotateAngleX = 0.2617994F - (walkies*2.653429F) + (len.isSwimming() ? 40/rads : 0);// + 0.1F;
    	if(this.lowerfootL.rotateAngleX < 0.26F) {
    		this.lowerfootL.rotateAngleX = 0.26F;
    	}
    
    	this.lowerfootR.rotateAngleX = 0.2617994F + (len.isSwimming() ? 40/rads : 0) + walkies*2.653429F;
    	if(this.lowerfootR.rotateAngleX < 0.260F) {
    		this.lowerfootR.rotateAngleX = 0.26F;
    		}
    	
    this.upperlegL.rotateAngleZ = walkies*0.12F + (this.lookaby/6 < 0 ? this.lookaby/6 : 0);
    this.upperlegL.rotateAngleY = this.upperlegL.rotateAngleZ * 0.75f;

    this.lowerfootL.rotateAngleZ = -this.upperlegL.rotateAngleZ * .2691f;
    this.lowerfootL.rotateAngleY = this.upperlegL.rotateAngleZ * 2.3f;

    this.upperlegR.rotateAngleZ = walkies*0.12F + (this.lookaby/6 > 0 ? this.lookaby/6 : 0);// + lookaby/5;
    this.upperlegR.rotateAngleY = this.upperlegR.rotateAngleZ * 0.75f;

    this.lowerfootR.rotateAngleZ = -this.upperlegR.rotateAngleZ * .2691f;
    this.lowerfootR.rotateAngleY = this.upperlegR.rotateAngleZ * 2.3f;


    //tailswing
    this.tail = //(this.tail*26 + 
    		(+0.23F * this.lookaby);///27; //this will fail given multiple lennys.
	this.rearbody.rotateAngleX = //this.weighted(this.rearbody.rotateAngleX, 
			this.lowerbody.rotateAngleX/2f;	//, 5);// swapped from negative to lower tail
    this.tailSeg1.rotateAngleX = //this.weighted(tail1, 
    		-this.rearbody.rotateAngleX * motion; //, 5);
    //the seemingly redundant + - stuff is to cancel out the tails up turn once lenny starts to walk.
    this.tailSeg2.rotateAngleX = //this.weighted(tail2,
    		this.tailSeg1.rotateAngleX+(5/rads)-(10/rads*motion);//, 5);
    this.tailSeg3.rotateAngleX = //this.weighted(tail3, 
    		this.tailSeg2.rotateAngleX;//, 5);
    this.tailSeg4.rotateAngleX = //this.weighted(tail4, 
    		this.tailSeg3.rotateAngleX;//, 5);
    this.tailSeg5.rotateAngleX = //this.weighted(tail5, 
    		this.tailSeg4.rotateAngleX;//, 5);
    
    this.rearbody.rotateAngleY = (float) (MathHelper.sin((float) (1 +(age / 2/Math.PI))+nttid) * 0.16) - this.tail;
	//actual swaying:
    float ta = 1.25f;
    this.tailSeg1.rotateAngleY = (float) (MathHelper.sin((float) (age / 2/Math.PI)+nttid) * 0.195 * ta) - this.tail - motion/5;
	  this.tailSeg2.rotateAngleY = (float) (MathHelper.sin((float) (-1F + (age / 2/Math.PI))+nttid) * 0.21 * ta) - this.tail - motion/5;
	  this.tailSeg3.rotateAngleY = (float) (MathHelper.sin((float) (-1.6F + (age / 2/Math.PI))+nttid) * 0.23 * ta) - this.tail - motion/5;
	  this.tailSeg4.rotateAngleY = (float) (MathHelper.sin((float) (-2F + (age / 2/Math.PI))+nttid) * 0.245 * ta) - this.tail - motion/5;
	  this.tailSeg5.rotateAngleY = (float) (MathHelper.sin((float) (-2.2F + (age / 2/Math.PI))+nttid) * 0.25 * ta) - this.tail - motion/5;
	  //oldtail = tail;
	  
	  //arm wiggle
	  this.shoulderL.rotateAngleZ = -this.tail - this.tailSeg1.rotateAngleY/2;
	  this.shoulderL.rotateAngleY = -this.tail - this.tailSeg1.rotateAngleY/2;
	  this.shoulderL.rotateAngleX = this.upperlegL.rotateAngleX/3;

	  this.shoulderR.rotateAngleZ = this.tail + this.tailSeg1.rotateAngleY/2;
	  this.shoulderR.rotateAngleY = this.tail + this.tailSeg1.rotateAngleY/2;
	  this.shoulderR.rotateAngleX = this.upperlegR.rotateAngleX/3;
	  
	  
	  //roaring
	  if(timer > 0) {

		  this.lowerbody.rotateAngleX = 8/rads;
			this.rearbody.rotateAngleX = this.lowerbody.rotateAngleX*0.9F;
			this.upperbody.rotateAngleX = (float) (MathHelper.sin((float) (age / 2/Math.PI)+nttid) * 0.02 - 10/rads);
		    this.tailSeg1.rotateAngleX = //this.weighted(this.tailSeg1.rotateAngleX, 
		    		this.rearbody.rotateAngleX;//, 5);
		    this.tailSeg2.rotateAngleX = //this.weighted(this.tailSeg2.rotateAngleX, 
		    		-this.tailSeg1.rotateAngleX;//-(5/rads);//, 5);
		    this.tailSeg3.rotateAngleX = //this.weighted(this.tailSeg3.rotateAngleX, 
		    		this.tailSeg2.rotateAngleX;//, 5);
		    this.tailSeg4.rotateAngleX = //this.weighted(this.tailSeg4.rotateAngleX, 
		    		this.tailSeg3.rotateAngleX;//, 5);
		    this.tailSeg5.rotateAngleX = //this.weighted(this.tailSeg5.rotateAngleX, 
		    		this.tailSeg4.rotateAngleX;// * 0.5f;//, 5);
		    
		    
		    this.jawUpper.rotateAngleX = ( + (27/rads - this.upperbody.rotateAngleX)) ;
			  
				this.head.rotateAngleX = (float) -12/rads;

			  this.head.rotateAngleZ = MathHelper.sin((float)(age/2/Math.PI*3F  ))* 0.1825F;
				this.neck.rotateAngleX = //this.weighted(neckX, 
						2/rads;//, 5);

			  this.neck.rotateAngleZ = this.head.rotateAngleZ/2;
			  this.upperbody.rotateAngleZ = this.head.rotateAngleZ/3F;
			  
				this.upperlegL.rotateAngleX = -0.3490659F - walkiesU*0.7F - this.lowerbody.rotateAngleX;
				this.upperlegR.rotateAngleX = -0.3490659F + walkiesU*0.7F - this.lowerbody.rotateAngleX;
			  
			  }
	  else if(attacktype == 3 && timea > 0)
	  {
		  this.head.rotateAngleY = //this.weighted(this.head.rotateAngleY, 
				  -35/rads;//, 5);
		  this.jawUpper.rotateAngleX = 4.5f/rads + timea*0.7f/rads;
		  }
	  else
		  if(attacktype == 1 && timea > 0)
		  {
					//chomping
					ubody = this.upperbody.rotateAngleX;
					this.upperbody.rotateAngleX = timea/rads/2.2f + //this.weighted(ubody, 
							(float) (MathHelper.sin((float) (age / 2/Math.PI)) * 0.02 + 35/rads)+(motion/headdown);//, 5);
					this.head.rotateAngleZ = //this.weighted(this.head.rotateAngleZ, 
							35/rads;//, 5);
					this.neck.rotateAngleZ = //this.weighted(this.neck.rotateAngleZ, 
							this.head.rotateAngleZ/2;//, 5);
				    this.jawUpper.rotateAngleX = MathHelper.sin((float)-(age/4/Math.PI*3F))* 0.1825F+(25/rads);
				}
				else if(attacktype == 2 && timea > 0) {
					this.upperarmR.rotateAngleZ = -timea/rads;
					this.upperarmL.rotateAngleZ = timea/rads;
				}
	  
	  
	  if(len.isSwimming()) {
		  
		  this.upperlegL.rotateAngleX = //this.weighted(uLLeg, 
				  (50/rads - this.lowerbody.rotateAngleX + motion/2);//, 5);
		  this.upperlegR.rotateAngleX = //this.weighted(uRLeg, 
				  (50/rads - this.lowerbody.rotateAngleX + motion/2);//, 5);
				}
	  
	  //TODO change weighted to do this, eg:
	  // pass in the calculation with time previous, ie t-1
	  // to calculate t. or t + 1 if the counter is descending.
	  // wont work with raw angles but should if applying a timer.
	  
	  int dt = len.deathTime;
	  
	  
//	  this.lowerbody.rotateAngleZ = 0;
	  
	  if(dt > 0)
	  {
		  //cancel breathing
		  this.upperbody.rotateAngleX = 0;
		  this.lowerbody.rotateAngleX = 0;
		  this.neck.rotateAngleX = 0;
		  
		  //spine curl.
		  
		  this.neck.rotateAngleX = 0 - (dt*.25f)/rads;
		  
		  this.head.rotateAngleX = this.neck.rotateAngleX;

		  this.rearbody.rotateAngleX = 
				  this.tailSeg1.rotateAngleX =
						  this.tailSeg2.rotateAngleX =
						  this.tailSeg3.rotateAngleX =
						  this.tailSeg4.rotateAngleX =
						  this.tailSeg5.rotateAngleX =
						  -this.neck.rotateAngleX;

		  this.neck.rotateAngleY = 0 - ((50-dt)*.9f)/rads;
		  if(this.neck.rotateAngleY > 2/rads) {
			  this.neck.rotateAngleY = 2/rads;
			  		  }
		  
		  this.head.rotateAngleY = this.neck.rotateAngleY;
		  this.rearbody.rotateAngleY = 
		  this.tailSeg1.rotateAngleY =
				  this.tailSeg2.rotateAngleY =
				  this.tailSeg3.rotateAngleY =
				  this.tailSeg4.rotateAngleY =
				  this.tailSeg5.rotateAngleY =
				  -this.neck.rotateAngleY;

		  this.jawUpper.rotateAngleX = 20/rads;
		  
		  this.lowerbody.rotateAngleZ = 0-((dt*5f)/rads);
		  if(this.lowerbody.rotateAngleZ < -90/rads) {
			  this.lowerbody.rotateAngleZ = -90/rads;
		  }
		  
		  this.upperlegL.rotateAngleZ = 20/rads;
		  this.shoulderR.rotateAngleZ = 0;
		  this.shoulderL.rotateAngleZ = 20/rads;
		  this.lowerfootL.rotateAngleX = 50/rads;
//		  System.out.println(this.lowerbody.rotateAngleZ);
		  
		  if(dt < 6) {
		  dt*=6;
		  this.lowerbody.rotationPointY = -20+dt;
		  } else {
			  this.lowerbody.rotationPointY = -20+36;
		  }
		  if(dt > 75) {
			  this.lowerbody.rotationPointY = -20+dt*.5f;
		  }
		  
		  
		  
		  
	  }

	 // this.lowerbody.rotateAngleY = len.ticksExisted/rads;

	 //TODO	does this function? yes it does.
//	  if(k <= 0) {for (k = rng.nextInt(120)+120; k > 0; k--);}
		  
		  
	  }
	
  //use this to separate actual animations
  public void setLivingAnimations(EntityLivingBase ntt, float time, float motion, float par4) {}
  
  //for weighting animations, smoothing transition between postures
  //fine as long as theres only a single instance of the mob in question, otherwise
  //animations will blend.
  public float weighted(float f1, float f2, int i) {
	  return ((f1*i) + f2)/(i+1);
  }

  private float func_78172_a(float par1, float par2)
  {
      return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }


}
