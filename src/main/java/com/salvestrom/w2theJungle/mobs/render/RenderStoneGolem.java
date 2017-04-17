package com.salvestrom.w2theJungle.mobs.render;

import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;
import com.salvestrom.w2theJungle.mobs.models.ModelStoneGolem;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderStoneGolem extends RenderLiving
{

	public static final ResourceLocation StoneGolem = new ResourceLocation("thejungle:textures/mobs/stonegolem.png");
	protected ModelStoneGolem model;
	public RenderStoneGolem(RenderManager renderManager, ModelBase par1ModelBase, float par2)
	{	super(renderManager, par1ModelBase, par2);
	model = ((ModelStoneGolem)mainModel);}

	public void renderStoneGolem(EntityStoneGolem ntt, double par2, double par4, double par6, float par8, float par9) {
		super.doRender(ntt, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1nttliving, double par2, double par4, double par6, float par8, float par9){
		renderStoneGolem((EntityStoneGolem) par1nttliving, par2, par4, par6, par8, par9);
	}

	public void doRender(Entity par1ntt, double par2, double par4, double par6, float par8, float par9) {
		renderStoneGolem((EntityStoneGolem)par1ntt, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity ntt) {
		return StoneGolem;
	}}
