
package com.salvestrom.w2theJungle.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBrazier extends ModelBase
{
  //fields
    ModelRenderer BrazierSlab;
    ModelRenderer BrazierBase;
    ModelRenderer BrazierMain;
    ModelRenderer corner1;
    ModelRenderer corner2;
    ModelRenderer corner3;
    ModelRenderer corner4;
    ModelRenderer ironBase;
    ModelRenderer ironside1;
    ModelRenderer ironside2;
    ModelRenderer ironside3;
    ModelRenderer ironside4;
    ModelRenderer netherrackFuel;
  
  public ModelBrazier()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      BrazierSlab = new ModelRenderer(this, 0, 91);
      BrazierSlab.addBox(0F, 0F, 0F, 15, 3, 15);
      BrazierSlab.setRotationPoint(-7.52F, 12F, -7.52F);
      BrazierSlab.setTextureSize(128, 128);
      BrazierSlab.mirror = true;
      setRotation(BrazierSlab, 0F, 0F, 0F);
      BrazierBase = new ModelRenderer(this, 0, 110);
      BrazierBase.addBox(0F, 0F, 0F, 15, 3, 15);
      BrazierBase.setRotationPoint(-7.5F, 21F, -7.466667F);
      BrazierBase.setTextureSize(128, 128);
      BrazierBase.mirror = true;
      setRotation(BrazierBase, 0F, 0F, 0F);
      BrazierMain = new ModelRenderer(this, 0, 66);
      BrazierMain.addBox(0F, 0F, 0F, 14, 10, 14);
      BrazierMain.setRotationPoint(-7F, 13F, -7F);
      BrazierMain.setTextureSize(128, 128);
      BrazierMain.mirror = true;
      setRotation(BrazierMain, 0F, 0F, 0F);
      corner1 = new ModelRenderer(this, 27, 47);
      corner1.addBox(0F, 0F, 0F, 2, 16, 2);
      corner1.setRotationPoint(6F, 8F, -8F);
      corner1.setTextureSize(128, 128);
      corner1.mirror = true;
      setRotation(corner1, 0F, 0F, 0F);
      corner2 = new ModelRenderer(this, 18, 47);
      corner2.addBox(0F, 0F, 0F, 2, 16, 2);
      corner2.setRotationPoint(-8F, 8F, -8F);
      corner2.setTextureSize(128, 128);
      corner2.mirror = true;
      setRotation(corner2, 0F, 0F, 0F);
      corner3 = new ModelRenderer(this, 0, 47);
      corner3.addBox(0F, 0F, 0F, 2, 16, 2);
      corner3.setRotationPoint(-8F, 8F, 6F);
      corner3.setTextureSize(128, 128);
      corner3.mirror = true;
      setRotation(corner3, 0F, 0F, 0F);
      corner4 = new ModelRenderer(this, 9, 47);
      corner4.addBox(0F, 0F, 0F, 2, 16, 2);
      corner4.setRotationPoint(6F, 8F, 6F);
      corner4.setTextureSize(128, 128);
      corner4.mirror = true;
      setRotation(corner4, 0F, 0F, 0F);
      ironBase = new ModelRenderer(this, 0, 20);
      ironBase.addBox(0F, 0F, 0F, 11, 2, 11);
      ironBase.setRotationPoint(-5.5F, 10F, -5.5F);
      ironBase.setTextureSize(128, 128);
      ironBase.mirror = true;
      setRotation(ironBase, 0F, 0F, 0F);
      ironside1 = new ModelRenderer(this, 0, 0);
      ironside1.addBox(-5F, 0F, -6F, 5, 1, 12);
      ironside1.setRotationPoint(3F, 11F, 0F);
      ironside1.setTextureSize(128, 128);
      ironside1.mirror = true;
      setRotation(ironside1, 0F, -3.141593F, -0.6457718F); //here 180 -3.141593 37 0.6457718F 90 1.570796
      ironside2 = new ModelRenderer(this, 0, 0);
      ironside2.addBox(-5F, 0F, -6F, 5, 1, 12);
      ironside2.setRotationPoint(0F, 11F, -3F);
      ironside2.setTextureSize(128, 128);
      ironside2.mirror = true;
      setRotation(ironside2, 1.570796F, (-1.570796F - 0.6457718F), -1.570796F); //here (north?)
      ironside3 = new ModelRenderer(this, 0, 0);
      ironside3.addBox(-5F, 0F, -6F, 5, 1, 12);
      ironside3.setRotationPoint(-3F, 11F, 0F);
      ironside3.setTextureSize(128, 128);
      ironside3.mirror = true;
      setRotation(ironside3, 0F, 0F, 0.6457718F); //here (east) correct
      ironside4 = new ModelRenderer(this, 0, 0);
      ironside4.addBox(-5F, 0F, -6F, 5, 1, 12);
      ironside4.setRotationPoint(0F, 11F, 3F);
      ironside4.setTextureSize(128, 128);
      ironside4.mirror = true;
      setRotation(ironside4, 1.570796F, 0.9250245F, 1.570796F); //to here (south edge)
      netherrackFuel = new ModelRenderer(this, 0, 34);
      netherrackFuel.addBox(0F, 0F, 0F, 8, 4, 8);
      netherrackFuel.setRotationPoint(-4F, 8F, -4F);
      netherrackFuel.setTextureSize(128, 128);
      netherrackFuel.mirror = true;
      setRotation(netherrackFuel, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    BrazierSlab.render(f5);
    BrazierBase.render(f5);
    BrazierMain.render(f5);
    corner1.render(f5);
    corner2.render(f5);
    corner3.render(f5);
    corner4.render(f5);
    ironBase.render(f5);
    ironside1.render(f5);
    ironside2.render(f5);
    ironside3.render(f5);
    ironside4.render(f5);
    netherrackFuel.render(f5);
  }
  
  public void renderModel(float f) {
	
	    BrazierSlab.render(f);
	    BrazierBase.render(f);
	    BrazierMain.render(f);
	    corner1.render(f);
	    corner2.render(f);
	    corner3.render(f);
	    corner4.render(f);
	    ironBase.render(f);
	    ironside1.render(f);
	    ironside2.render(f);
	    ironside3.render(f);
	    ironside4.render(f);
	    netherrackFuel.render(f);
	  
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
  }

}
