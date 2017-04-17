package com.salvestrom.w2theJungle.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJungleBook extends ModelBase {
	
	ModelRenderer mainBook;
	ModelRenderer seal;
	ModelRenderer ribbon;
	ModelRenderer seal2;
	ModelRenderer ribbon2;
	
    private float rads = 180F/(float)Math.PI;
	
	public ModelJungleBook() {
		
	    this.textureWidth = 64;
	    this.textureHeight = 64;
	    
	    mainBook = new ModelRenderer(this, 0, 0);
	    mainBook.addBox(-5F, -4F, -6f, 10, 8, 12);
	    mainBook.setRotationPoint(0F, 0F, 0F);
	    //mainBook.setTextureSize(64, 32);
	    //mainBook.mirror = true;
	    setRotation(mainBook, 0F, 0F, 0F);
	    
	    seal = new ModelRenderer(this, 0, 4);
	    seal.addBox(-1F, -1F, -0.5F, 2, 2, 1);
	    seal.setRotationPoint(-5.05F, -2F, -2F);
	    //seal.setTextureSize(64, 32);
	    setRotation(seal, 0F, 90/rads, 0F);
	    ribbon = new ModelRenderer(this, 0, 0);
	    ribbon.addBox(-1F, 0F, -0F, 2, 4, 0);
	    ribbon.setRotationPoint(0F, 0.75F, 0F);
	    //ribbon.setTextureSize(64, 32);
	    //ribbon.mirror = true;
	    setRotation(ribbon, 0F, 0F, 0F);

	    seal2 = new ModelRenderer(this, 0, 4);
	    seal2.addBox(-1F, -1F, -0.5F, 2, 2, 1);
	    seal2.setRotationPoint(-5.20F, -1.5F, -3.75F);
	    //seal2.setTextureSize(64, 32);
	    //seal.mirror = true;
	    setRotation(seal2, 25/rads, 90/rads, 0);
	    ribbon2 = new ModelRenderer(this, 0, 0);
	    ribbon2.addBox(-1F, 0F, -0F, 2, 4, 0);
	    ribbon2.setRotationPoint(0F, 0.75F, 0F);
	    //ribbon2.setTextureSize(64, 32);
	    //ribbon.mirror = true;
	    setRotation(ribbon2, 0F, 0F, 0F);
	    
	    this.mainBook.addChild(seal);
	    this.seal.addChild(ribbon);

	    this.mainBook.addChild(seal2);
	    this.seal2.addChild(ribbon2);
		
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    mainBook.render(f5);
	  }
	  
	  public void renderModel(float f) {
		    mainBook.render(f);

		  
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    
	    this.seal2.rotateAngleX = 90/rads;
	    this.seal2.rotateAngleY = 100/rads;
	    this.seal2.rotateAngleZ = 90/rads;
	  }

}
