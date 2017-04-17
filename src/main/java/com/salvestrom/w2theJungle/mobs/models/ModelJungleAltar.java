package com.salvestrom.w2theJungle.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJungleAltar extends ModelBase {
	
	ModelRenderer block;
	
	public ModelJungleAltar() {
		
		textureWidth = 64;
		textureHeight = 64;
		
		block = new ModelRenderer(this, 0, 0);
	    block.addBox(-7F, -7F, -7F, 14, 14, 14);
	    block.setRotationPoint(0f, 0F, 0F);
	    block.setTextureSize(64, 32);
	    block.mirror = true;
	    setRotation(block, 0F, 0F, 0F);
		
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    block.render(f5);
	  }
	  
	  public void renderModel(float f) {
		    block.render(f);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	    
	    
	  }
	  
	  public void setRotationAngles(float time, float motion, float age, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(time, motion, age, f3, f4, f5, entity);
	    
	    time /= 100;
	    
	    float amp = (float) (Math.sin(time*3.75)*1.25);
    
    	this.block.rotationPointX = (float) (Math.sin(time*1.125)*amp) * (1 - time*0.019F) / 10;
    	this.block.rotationPointY = -0.5F + (float) (Math.sin(time*3.125)*amp) * (1 - time*0.019F) / 10;
    	this.block.rotationPointZ = (float) (Math.sin(time*0.125)*amp) * (1 - time*0.019F) / 10;
	  }
}
