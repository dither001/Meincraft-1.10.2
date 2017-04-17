package com.salvestrom.w2theJungle.mobs.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelObsidianArmour extends ModelBiped
{
  //fields
    ModelRenderer helmetpart2;
    ModelRenderer chinguard;
    ModelRenderer helmetdeco;
    ModelRenderer leftgauntlet;
    ModelRenderer rightshin;
    ModelRenderer rightshoulder;
    ModelRenderer rightgauntlet;
    ModelRenderer rearcloth;
    ModelRenderer chestdeco;
    ModelRenderer belt;
    ModelRenderer buckle;
    ModelRenderer leftshoulder;
    ModelRenderer sealshoulder;
    ModelRenderer ribbonshoulder;
    ModelRenderer sealleg;
    ModelRenderer ribbonleg;
    ModelRenderer frontcloth;
    ModelRenderer leftshin;
    ModelRenderer midrearfrill;
    ModelRenderer leftrearfrill;
    ModelRenderer rightrearfrill;
    ModelRenderer leftupperfrill;
    ModelRenderer rightupperfrill;
    ModelRenderer dangleright;
    ModelRenderer dangleleft;
    ModelRenderer midupperfrill;
    ModelRenderer armplateleft;
    ModelRenderer armplateright;
    
    ModelRenderer frontfrill;
    
  public ModelObsidianArmour(float f)
  {super (f, 0, 128, 128);
  //  textureWidth = 128;
  //  textureHeight = 128;

  
  frontfrill = new ModelRenderer(this, 92, 123);
  frontfrill.addBox(-1F, -4F, -1F, 2, 4, 1);
  frontfrill.setRotationPoint(0F, -10F, -5F);
  frontfrill.setTextureSize(64, 32);
  frontfrill.mirror = true;
  setRotation(frontfrill, 0.1396263F, 0F, 0F);
  
  
      helmetpart2 = new ModelRenderer(this, 0, 43);
      helmetpart2.addBox(-1F, -10F, -5.5F, 2, 10, 11);
      helmetpart2.setRotationPoint(0F, 0F, 0F);
      helmetpart2.setTextureSize(64, 32);
      helmetpart2.mirror = true;
      setRotation(helmetpart2, 0F, 0F, 0F);
      chinguard = new ModelRenderer(this, 0, 74);
      chinguard.addBox(-2F, -1F, -6F, 4, 2, 4);
      chinguard.setRotationPoint(0F, 0F, 0F);
      chinguard.setTextureSize(64, 32);
      chinguard.mirror = true;
      setRotation(chinguard, 0F, 0F, 0F);
      helmetdeco = new ModelRenderer(this, 0, 65);
      helmetdeco.addBox(-3F, -9F, -6F, 6, 4, 4);
      helmetdeco.setRotationPoint(0F, 0F, 0F);
      helmetdeco.setTextureSize(64, 32);
      helmetdeco.mirror = true;
      setRotation(helmetdeco, 0F, 0F, 0F);
      leftgauntlet = new ModelRenderer(this, 65, 53);
      leftgauntlet.addBox(-1F, 5F, -3F, 5, 6, 6);
      leftgauntlet.setRotationPoint(0F, 0F, 0F);
      leftgauntlet.setTextureSize(64, 32);
      leftgauntlet.mirror = true;
      setRotation(leftgauntlet, 0F, 0F, 0F);
      rightshin = new ModelRenderer(this, 75, 24);
      rightshin.addBox(-3F, 4F, -3F, 5, 8, 5);
      rightshin.setRotationPoint(0F, 0F, 0F);
      rightshin.setTextureSize(64, 32);
      rightshin.mirror = true;
      setRotation(rightshin, 0F, 0F, 0F);
      rightshoulder = new ModelRenderer(this, 40, 40);
      rightshoulder.addBox(-6, -4F, -3F, 6, 6, 6);
      rightshoulder.setRotationPoint(0F, 0F, 0F);
      rightshoulder.setTextureSize(64, 32);
      rightshoulder.mirror = true;
      setRotation(rightshoulder, 0F, 0F, 0F);
      rightgauntlet = new ModelRenderer(this, 65, 40);
      rightgauntlet.addBox(-4F, 5F, -3F, 5, 6, 6);
      rightgauntlet.setRotationPoint(0F, 0F, 0F);
      rightgauntlet.setTextureSize(64, 32);
      rightgauntlet.mirror = true;
      setRotation(rightgauntlet, 0F, 0F, 0F);
      rearcloth = new ModelRenderer(this, 41, 96);
      rearcloth.addBox(-3F, 0, 0F, 6, 12, 0);
      rearcloth.setRotationPoint(0F, 11F, 3.5F); //3.5>3.7
      rearcloth.setTextureSize(64, 32);
      rearcloth.mirror = true;
      setRotation(rearcloth, 0F, 0F, 0F);
      chestdeco = new ModelRenderer(this, 0, 105);
      chestdeco.addBox(-2F, 2F, -4F, 4, 4, 2);
      chestdeco.setRotationPoint(0F, 0F, 0F);
      chestdeco.setTextureSize(64, 32);
      chestdeco.mirror = true;
      setRotation(chestdeco, 0F, 0F, 0F);
      belt = new ModelRenderer(this, 28, 117);
      belt.addBox(-4F, 8F, -4F, 8, 3, 8);
      belt.setRotationPoint(0F, 0F, 0F);
      belt.setTextureSize(64, 32);
      belt.mirror = true;
      setRotation(belt, 0F, 0F, 0F);
      buckle = new ModelRenderer(this, 28, 109);
      buckle.addBox(-2F, 8F, -5F, 4, 3, 4);
      buckle.setRotationPoint(0F, 0F, 0F);
      buckle.setTextureSize(64, 32);
      buckle.mirror = true;
      setRotation(buckle, 0F, 0F, 0F);
      leftshoulder = new ModelRenderer(this, 40, 53);
      leftshoulder.addBox(0F, -4F, -3F, 6, 6, 6);
      leftshoulder.setRotationPoint(0F, 0F, 0F);
      leftshoulder.setTextureSize(64, 32);
      leftshoulder.mirror = true;
      setRotation(leftshoulder, 0F, 0F, 0F);
      sealshoulder = new ModelRenderer(this, 100, 60);
      sealshoulder.addBox(3.2F, 0F, -3.5F, 2, 2, 1);
      sealshoulder.setRotationPoint(0F, 0F, 0F);
      sealshoulder.setTextureSize(64, 32);
      sealshoulder.mirror = true;
      setRotation(sealshoulder, 0F, 0F, 0F);
      ribbonshoulder = new ModelRenderer(this, 114, 58);
      ribbonshoulder.addBox(1F, 0F, 0F, 2, 5, 0);
      ribbonshoulder.setRotationPoint(2.2F, 2F, -3.2F);
      ribbonshoulder.setTextureSize(64, 32);
      ribbonshoulder.mirror = true;
      setRotation(ribbonshoulder, 0F, 0F, 0F);
      sealleg = new ModelRenderer(this, 107, 60);
      sealleg.addBox(-3.4F, 5F, -3.5F, 2, 2, 1);
      sealleg.setRotationPoint(0F, 0F, 0F);
      sealleg.setTextureSize(64, 32);
      sealleg.mirror = true;
      setRotation(sealleg, 0F, 0F, 0F);
      ribbonleg = new ModelRenderer(this, 119, 59);
      ribbonleg.addBox(1F, 0F, 0F, 2, 4, 0);
      ribbonleg.setRotationPoint(-4.4F, 7F, -3.2F);
      ribbonleg.setTextureSize(64, 32);
      ribbonleg.mirror = true;
      setRotation(ribbonleg, 0F, 0F, 0F);
      frontcloth = new ModelRenderer(this, 28, 96);
      frontcloth.addBox(-3F, 0F, 0F, 6, 12, 0);
      frontcloth.setRotationPoint(0F, 11F, -4F); //4>4.2
      frontcloth.setTextureSize(64, 32);
      frontcloth.mirror = true;
      setRotation(frontcloth, 0F, 0F, 0F);
      leftshin = new ModelRenderer(this, 75, 10);
      leftshin.addBox(-2F, 4F, -3F, 5, 8, 5);
      leftshin.setRotationPoint(0F, 0F, 0F);
      leftshin.setTextureSize(64, 32);
      leftshin.mirror = true;
      setRotation(leftshin, 0F, 0F, 0F);
      midrearfrill = new ModelRenderer(this, 113, 85);
      midrearfrill.addBox(0F, -14F, 6F, 0, 12, 2);
      midrearfrill.setRotationPoint(0F, 0F, 0F);
      midrearfrill.setTextureSize(64, 32);
      midrearfrill.mirror = true;
      setRotation(midrearfrill, 0F, 0F, 0F);
      leftrearfrill = new ModelRenderer(this, 113, 71);
      leftrearfrill.addBox(1F, -12F, 0F, 1, 12, 2);
      leftrearfrill.setRotationPoint(-1F, 0F, 5F);
      leftrearfrill.setTextureSize(64, 32);
      leftrearfrill.mirror = true;
      setRotation(leftrearfrill, 0F, 0F, 0F);
      rightrearfrill = new ModelRenderer(this, 122, 71);
      rightrearfrill.addBox(-1F, -12F, 0F, 1, 12, 2);
      rightrearfrill.setRotationPoint(0F, 0F, 5F); //0> -12
      rightrearfrill.setTextureSize(64, 32);
      rightrearfrill.mirror = true;
      setRotation(rightrearfrill, 0F, 0F, 0F);
      leftupperfrill = new ModelRenderer(this, 79, 95);
      leftupperfrill.addBox(-1F, -4F, -6F, 1, 4, 12);
      leftupperfrill.setRotationPoint(1F, -10F, 0F);
      leftupperfrill.setTextureSize(64, 32);
      leftupperfrill.mirror = true;
      setRotation(leftupperfrill, 0F, 0F, 0F);
      rightupperfrill = new ModelRenderer(this, 79, 112);
      rightupperfrill.addBox(1F, -4F, -6F, 1, 4, 12);
      rightupperfrill.setRotationPoint(-2F, -10F, 0F);
      rightupperfrill.setTextureSize(64, 32);
      rightupperfrill.mirror = true;
      setRotation(rightupperfrill, 0F, 0F, 0F);
      dangleright = new ModelRenderer(this, 107, 71);
      dangleright.addBox(-0.6F, 0F, 0F, 1, 10, 1);
      dangleright.setRotationPoint(-0.6F, -0.5F, 5.5F);
      dangleright.setTextureSize(64, 32);
      dangleright.mirror = true;
      setRotation(dangleright, 0F, 0F, 0F);
      dangleleft = new ModelRenderer(this, 107, 71);
      dangleleft.addBox(-0.4F, 0F, 0F, 1, 10, 1);
      dangleleft.setRotationPoint(0.4F, -1F, 5F);
      dangleleft.setTextureSize(64, 32);
      setRotation(dangleleft, 0F, 0F, 0F);
      midupperfrill = new ModelRenderer(this, 105, 112);
      midupperfrill.addBox(0F, -5F, -5F, 0, 4, 12);
      midupperfrill.setRotationPoint(0F, -10F, 0F);
      midupperfrill.setTextureSize(64, 32);
      midupperfrill.mirror = true;
      setRotation(midupperfrill, 0F, 0F, 0F);
      
      armplateright = new ModelRenderer(this, 100, 23);
      armplateright.addBox(-3.5F, -2, -2, 0, 12, 4); 
      armplateright.setRotationPoint(0F, 0, 0);
      armplateright.setTextureSize(64, 32);
      armplateright.mirror = true;
            
      armplateleft = new ModelRenderer(this, 100, 23);
      armplateleft.addBox(3.5F, -2, -2, 0, 12, 4);
      armplateleft.setRotationPoint(0F, 0, 0);
      armplateleft.setTextureSize(64, 32);
      armplateleft.mirror = true;
      
      //headparts
      this.bipedHead.addChild(dangleright);
      this.bipedHead.addChild(dangleleft);
      
      this.bipedHead.addChild(helmetdeco);
      this.bipedHead.addChild(helmetpart2);
      this.bipedHead.addChild(chinguard);
      this.bipedHead.addChild(rightupperfrill);
      this.bipedHead.addChild(leftupperfrill);
      this.bipedHead.addChild(midupperfrill);
      this.bipedHead.addChild(leftrearfrill);
      this.bipedHead.addChild(rightrearfrill);
      this.bipedHead.addChild(midrearfrill);
      this.bipedHead.addChild(frontfrill);

      
      //bodyparts shift belt to legs <- doesnt work since legs only load with boots.
      this.bipedBody.addChild(chestdeco);
      
      this.bipedBody.addChild(belt);
      this.bipedBody.addChild(buckle);
        this.bipedBody.addChild(rearcloth);
          this.bipedBody.addChild(frontcloth);

      //arms
      this.bipedLeftArm.addChild(armplateleft);
      this.bipedLeftArm.addChild(leftgauntlet);
      this.bipedLeftArm.addChild(leftshoulder);
      this.bipedLeftArm.addChild(sealshoulder);
      this.sealshoulder.addChild(ribbonshoulder);
      
      this.bipedRightArm.addChild(armplateright);
      this.bipedRightArm.addChild(rightgauntlet);
      this.bipedRightArm.addChild(rightshoulder);
      
      //legs
      this.bipedLeftLeg.addChild(leftshin);
      this.bipedRightLeg.addChild(rightshin);
      this.bipedRightLeg.addChild(ribbonleg);
      this.bipedRightLeg.addChild(sealleg);
      }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);

  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  //lookie is pitch. turn is yaw.. (of head)
  
  public void setRotationAngles(float f, float motion, float time, float turn, float lookie, float unknown, Entity entity)
  {
	  super.setRotationAngles(f, motion, time, turn, lookie, unknown, entity);
	  
	  float rads = (float) (180F/Math.PI);
	  
	  float m1 = 1.1f;

	  this.frontcloth.rotateAngleX = super.bipedRightLeg.rotateAngleX * m1;
	  this.rearcloth.rotateAngleX = super.bipedLeftLeg.rotateAngleX * m1 + motion*0.5f;
	  
	  if (this.frontcloth.rotateAngleX > 0){this.frontcloth.rotateAngleX = super.bipedLeftLeg.rotateAngleX * m1;}
	  if (this.rearcloth.rotateAngleX < 0){this.rearcloth.rotateAngleX = super.bipedRightLeg.rotateAngleX * m1;}
	  
	  this.ribbonshoulder.rotateAngleZ = (float) -(motion * 0.5f);
	  this.ribbonshoulder.rotateAngleX = (float) (motion * 0.5f);
	  
	  this.ribbonleg.rotateAngleZ = (float) (motion * 0.5f);
	  this.ribbonleg.rotateAngleX = (float) (motion * 0.5f);
	  
	  this.rightupperfrill.rotateAngleZ = (float) (Math.sin(super.bipedLeftArm.rotateAngleX*0.75F)*(motion*0.33)
			 +(float)(Math.sin(-turn*0.005))) + (this.isRiding ? (float) (Math.sin(time*0.75f)*motion) : 0);
	  
	  this.leftupperfrill.rotateAngleZ = this.midupperfrill.rotateAngleZ = this.rightupperfrill.rotateAngleZ;
	  this.frontfrill.rotateAngleZ = this.rightrearfrill.rotateAngleY = this.leftrearfrill.rotateAngleY = this.rightupperfrill.rotateAngleZ;
	  
	  this.dangleleft.rotateAngleZ = -this.rightupperfrill.rotateAngleZ;
	  this.dangleleft.rotateAngleY = 0/rads;
	  this.dangleleft.rotateAngleX = (float)(Math.sin(time*0.5)*0.5f*motion) - lookie/rads; // +(float)(Math.sin(-lookie*0.5));

	  if(this.dangleleft.rotateAngleX < -45/rads) {
		  this.dangleleft.rotateAngleX = -45/rads;
		  }
	  
	  this.dangleright.rotateAngleZ = this.dangleleft.rotateAngleZ;
	  this.dangleright.rotateAngleX = this.dangleleft.rotateAngleX;
	  
	  if (this.isRiding) {
		this.rearcloth.rotateAngleX = (float) (85/rads + Math.sin(time*0.5)*0.85*motion);
		}
	}
}
 
 