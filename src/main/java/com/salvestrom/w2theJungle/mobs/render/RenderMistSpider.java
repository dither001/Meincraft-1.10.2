package com.salvestrom.w2theJungle.mobs.render;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.mobs.entity.EntityMistSpider;
import com.salvestrom.w2theJungle.mobs.models.ModelMistSpider;
import com.salvestrom.w2theJungle.mobs.render.layers.LayerMistSpiderEyes;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class RenderMistSpider extends RenderLiving {
	
	public static final ResourceLocation MistWeaver = new ResourceLocation("thejungle:textures/mobs/mistSpider.png");
	public static final ResourceLocation mistspidereyes = new ResourceLocation("thejungle:textures/mobs/mistSpider_Eyes.png");

	protected ModelMistSpider model;

	public RenderMistSpider(RenderManager rm, ModelMistSpider par1ModelBase, float par2) {
		super(rm, par1ModelBase, par2);
		this.model = (ModelMistSpider) super.mainModel;
        this.addLayer(new LayerMistSpiderEyes(this));

	}

	public void rotateCorpse(EntityLivingBase nttb, float f1, float f2, float f3) {
        GL11.glRotatef(180.0F - f2, 0.0F, 1.0F, 0.0F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return MistWeaver;
	}

}
