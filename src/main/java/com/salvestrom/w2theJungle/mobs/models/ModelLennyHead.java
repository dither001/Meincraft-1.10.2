package com.salvestrom.w2theJungle.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLennyHead extends ModelBase {
	
	ModelRenderer skullSnout;
    ModelRenderer skulljawlower;
    ModelRenderer skullHead;
    ModelRenderer skulljawUpper;
    ModelRenderer noseplate;
    
    ModelRenderer teethupper;
    ModelRenderer teethlower;
    
    public ModelLennyHead() {
    	
    	textureWidth = 256;
    	textureHeight = 256;

    skullHead = new ModelRenderer(this, 0, 206);
    skullHead.addBox(-7.5F, -10F, -12F, 15, 10, 12);
    skullHead.setRotationPoint(0F, -7F, 11F); //0 -21 -48
    skullHead.setTextureSize(256, 256);
    skullHead.mirror = true;
    setRotation(skullHead, 0F, 0F, 0F);
    skullSnout = new ModelRenderer(this, 183, 169);
    skullSnout.addBox(-4F, -5F, -11F, 8, 10, 16);
    skullSnout.setRotationPoint(0F, -3F, -15F); //0 -24 -63 //head 0 -21 -48
    skullSnout.setTextureSize(256, 256);
    skullSnout.mirror = true;
    setRotation(skullSnout, 0F, 0F, 0F);
    skulljawUpper = new ModelRenderer(this, 56, 210);
    skulljawUpper.addBox(-7.0F, -4F, -9F, 14, 8, 10);
    skulljawUpper.setRotationPoint(0F, 2F, -2F); //0 -19 -50
    skulljawUpper.setTextureSize(256, 256);
    skulljawUpper.mirror = true;
    setRotation(skulljawUpper, 0F, 0F, 0F);
    skulljawlower = new ModelRenderer(this, 183, 200);
    skulljawlower.addBox(-4.5F, -1F, -18F, 9, 5, 21);
    skulljawlower.setRotationPoint(0F, 1F, -5F); // 0 -18 -55
    skulljawlower.setTextureSize(256, 256);
    skulljawlower.mirror = true;
    setRotation(skulljawlower, 0F, 0F, 0F);
    noseplate = new ModelRenderer(this, 112, 205);
    noseplate.addBox(-2.5F, -3F, -8F, 5, 4, 10);
    noseplate.setRotationPoint(0F, -5F, -2F); //0 -28 -66
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
    
    this.skullHead.addChild(skulljawUpper);
    this.skullHead.addChild(skullSnout);
    this.skulljawUpper.addChild(skulljawlower);
    this.skullSnout.addChild(teethupper);
    this.skullSnout.addChild(noseplate);
    this.skulljawlower.addChild(teethlower);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.render(entity, f, f1, f2, f3, f4, f5);
      setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      skullHead.render(f5);
      
    }    
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float time, float motion, float age, float turn, float lookie, float unknown, Entity ntt)
    {
      super.setRotationAngles(time, motion, age, turn, lookie, unknown, ntt);
      
      float rads = (float) (180F/Math.PI);
      
      this.skullHead.rotateAngleX = -18/rads;
      this.skulljawUpper.rotateAngleX = -this.skullHead.rotateAngleX;
      this.noseplate.rotationPointY = -4.6F;
      this.noseplate.rotationPointZ = -2.6F;
      this.teethupper.rotationPointZ = 1F;
    }
    
    
}
