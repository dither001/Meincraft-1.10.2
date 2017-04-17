package com.salvestrom.w2theJungle.mobs.render;

import com.salvestrom.w2theJungle.mobs.entity.EntityGorrbat;
import com.salvestrom.w2theJungle.mobs.models.ModelGorrbat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderGorrbat extends RenderLiving {

	public static final ResourceLocation gorrbat = new ResourceLocation("thejungle:textures/mobs/gorrbat.png");
	protected ModelGorrbat model;
	
	public RenderGorrbat(RenderManager rm, ModelBase par1ModelBase, float par2) {
		super(rm, par1ModelBase, par2);
		model = ((ModelGorrbat)mainModel);
		}

	public void rendergorrbat(EntityGorrbat ntt, double par2, double par4, double par6, float par8, float par9) {
		super.doRender(ntt, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1nttliving, double par2, double par4, double par6, float par8, float par9){
		rendergorrbat((EntityGorrbat)par1nttliving, par2, par4, par6, par8, par9);
	}

	public void doRender(EntityLiving par1ntt, double par2, double par4, double par6, float par8, float par9) {
		
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
        ItemStack itemstack = par1ntt.getHeldItemMainhand();
//        this.func_82420_a(par1ntt, itemstack);
        double d3 = par4 - (double)par1ntt.getYOffset();
		rendergorrbat((EntityGorrbat)par1ntt, par2, d3, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity ntt) {
		return gorrbat;
	}
	
    protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2) {
    	//this.renderEquippedItems((EntityGorrbat)par1EntityLiving, par2);
    	}
	

	protected void preRenderCallback(EntityGorrbat nttLizSt, float par2) {
		
		//float reduc = 1F;
		//GL11.glScalef(reduc, reduc, reduc);
		}

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderCallback((EntityGorrbat)par1EntityLivingBase, par2);
        super.preRenderCallback(par1EntityLivingBase, par2);
    }
    
    @Override
	public void transformHeldFull3DItemLayer()
    {
        GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
    }
    
}
