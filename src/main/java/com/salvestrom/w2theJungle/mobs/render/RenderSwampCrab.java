package com.salvestrom.w2theJungle.mobs.render;

import com.salvestrom.w2theJungle.mobs.entity.EntitySwampCrab;
import com.salvestrom.w2theJungle.mobs.models.ModelSwampCrab;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderSwampCrab extends RenderLiving {
	
	public static final ResourceLocation SwampCrab = new ResourceLocation("thejungle:textures/mobs/swampcrab.png");
	protected ModelSwampCrab model;
	
	public RenderSwampCrab(RenderManager rm, ModelBase par1ModelBase, float par2) {
		super(rm, par1ModelBase, par2);
		model = ((ModelSwampCrab)mainModel);
		}
	
	public void renderSwampCrab(EntitySwampCrab ntt, double par2, double par4, double par6, float par8, float par9) {
		super.doRender(ntt, par2, par4, par6, par8, par9);
		}

	public void doRenderLiving(EntityLiving par1nttliving, double par2, double par4, double par6, float par8, float par9) {
		renderSwampCrab((EntitySwampCrab) par1nttliving, par2, par4, par6, par8, par9);
		}

	public void doRender(Entity par1ntt, double par2, double par4, double par6, float par8, float par9) {
		renderSwampCrab((EntitySwampCrab)par1ntt, par2, par4, par6, par8, par9);
		}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity ntt) {
		return SwampCrab;
		}
	
	protected void preRenderCallback(EntitySwampCrab nttLizSt, float par2) {
		
		float reduc = 1.25F;
		GL11.glScalef(reduc, reduc, reduc);
		}

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderCallback((EntitySwampCrab)par1EntityLivingBase, par2);
        super.preRenderCallback(par1EntityLivingBase, par2);
    }
	
	}