package com.salvestrom.w2theJungle.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWallHead extends ModelBase {
	
	//fields
	ModelRenderer headplate;
    ModelRenderer head;
    ModelRenderer neck;
    ModelRenderer jaw;
    ModelRenderer jawLfixture;
    ModelRenderer jawRfixture;
    ModelRenderer toothR;
    ModelRenderer toothL;
    
    public ModelWallHead()     {
    	
    	textureWidth = 256;
    	textureHeight = 256;
      
    	headplate = new ModelRenderer(this, 70, 0);
    	headplate.addBox(-7F, -4F, -7F, 14, 5, 15);
    	headplate.setRotationPoint(0F, -6F, -1F);
    	headplate.setTextureSize(256, 256);
    	headplate.mirror = true;
    	setRotation(headplate, 0F, 0F, 0F);
    	head = new ModelRenderer(this, 0, 65);
    	head.addBox(-6F, -11F, -6F, 12, 13, 12);
    	head.setRotationPoint(0F, 0F, 0F); //neck was 0 -28 -2 head: 0 -39 -2
    	head.setTextureSize(256, 256);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      neck = new ModelRenderer(this, 129, 34);
      neck.addBox(-5F, -12F, -5F, 10, 4, 10); //neck box height lowered from 12 to 4. upperbody:0 -36 0
      neck.setRotationPoint(0F, 8F, -1F); //lowered from 36 to 28 to accommodate above change.
      neck.setTextureSize(256, 256);
      neck.mirror = true;
      setRotation(neck, 0F, 0F, 0F);
      jaw = new ModelRenderer(this, 130, 0);
      jaw.addBox(-7F, -4F, -6F, 14, 6, 12);
      jaw.setRotationPoint(0F, 2F, -2F); //change to allow jaw to hinge
      jaw.setTextureSize(256, 256);
      jaw.mirror = true;
      setRotation(jaw, 0F, 0F, 0F);
      jawLfixture = new ModelRenderer(this, 57, 38);
      jawLfixture.addBox(-1F, -1F, -1F, 1, 4, 4);
      jawLfixture.setRotationPoint(-6F, -4F, 0F); //head: 0 -39 -2
      jawLfixture.setTextureSize(256, 256);
      jawLfixture.mirror = true;
      setRotation(jawLfixture, 0F, 0F, 0F);
      jawRfixture = new ModelRenderer(this, 57, 38);
      jawRfixture.addBox(-0.0F, -1F, -1F, 1, 4, 4);
      jawRfixture.setRotationPoint(6F, -4F, 0F); //was 5.5 but nt visible
      jawRfixture.setTextureSize(256, 256);
      jawRfixture.mirror = true;
      setRotation(jawRfixture, 0F, 0F, 0F);
      toothR = new ModelRenderer(this, 57, 33);
      toothR.addBox(-1F, -1F, -1F, 2, 3, 1);
      toothR.setRotationPoint(-4F, -5F, -4F);
      toothR.setTextureSize(256, 256);
      toothR.mirror = true;
      setRotation(toothR, 0F, 0F, 0F);
      toothL = new ModelRenderer(this, 57, 33);
      toothL.addBox(-1F, -1F, -1F, 2, 3, 1);
      toothL.setRotationPoint(4F, -5F, -4F);
      toothL.setTextureSize(256, 256);
      toothL.mirror = true;
      setRotation(toothL, 0F, 0F, 0F);

      this.head.addChild(headplate);
      this.head.addChild(jaw);
      this.head.addChild(jawLfixture);
      this.head.addChild(jawRfixture);
      this.jaw.addChild(toothL);
      this.jaw.addChild(toothR);
            
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)     {
    	super.render(entity, f, f1, f2, f3, f4, f5);
    	
    	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	head.render(f5);
    	}
    
    private void setRotation(ModelRenderer model, float x, float y, float z)    {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    	}

    public void setRotationAngles(float time, float motion, float age, float turn, float lookie, float unknown, Entity ntt)
    {
      super.setRotationAngles(time, motion, age, turn, lookie, unknown, ntt);
      
      float rads = (float) (180F/Math.PI);
     
    }


}


