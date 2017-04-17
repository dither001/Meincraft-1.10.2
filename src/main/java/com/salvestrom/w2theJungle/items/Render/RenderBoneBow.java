package com.salvestrom.w2theJungle.items.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
/*
public class RenderBoneBow implements IItemRenderer {
	
		private RenderManager renderManager;
		private Minecraft mc;
		private TextureManager texturemanager;
	
	public RenderBoneBow(RenderManager rm) {
		this.renderManager = rm;
		this.mc = Minecraft.getMinecraft();
		this.texturemanager = this.mc.getTextureManager();
		}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
		}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
		}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		Entity entity = (Entity)data[1];
//		ItemRenderer irInstance = this.mc.entityRenderer.itemRenderer;
		GL11.glPopMatrix(); // prevents Forge from pre-translating the item
		//TODO check if this is identical to item.bow
		if(entity instanceof EntityOtherPlayerMP || entity instanceof EntityLiving) {
			
			if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
				this.renderItem(entity, item, 0);
				} else if(entity instanceof EntityOtherPlayerMP) {
					GL11.glPushMatrix();
					float f2 = 3F - (1F/3F);
					GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
					GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(f2, f2, f2);
					GL11.glTranslatef(-0.25F, -0.1875F, 0.1875F);
					
					float f3 = 0.625F;
					GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
					GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(f3, -f3, f3);
					GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
					this.renderItem(entity, item, 0);
					GL11.glPopMatrix();
				} 
// the following code was gutted to avoid double translation with the lizard render file and simplify them.
			if(entity instanceof EntityLiving) {
				GL11.glPushMatrix();
				float f2 = 3F - (1F/3F);
			//	GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
			//	GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
			//	GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
			//	GL11.glScalef(f2, f2, f2);
			//	GL11.glTranslatef(-0.25F, -0.1875F, 0.1875F);
				
				float f3 = 0.625F;
			//	GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-0.0F, 0.0F, 1.0F, 0.0F); //-20 0 1 0
			//	GL11.glScalef(f3, -f3, f3);
				GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F); //-100 1 0 0
				GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F); // 40 0 1 0
				this.renderItem(entity, item, 0);
				GL11.glPopMatrix();
			} 
			GL11.glPushMatrix(); // prevents GL Underflow errors
			}
		}
		
	//will changing this  affect saurohn?
	private void renderItem(Entity par1EntityLiving, ItemStack par2ItemStack, int par3) {
		{
//			EntityClientPlayerMP player = (EntityClientPlayerMP)par1EntityLiving;
//			if(par1EntityLiving instanceof EntityPlayer || par1EntityLiving instanceof EntityLiving) {
		
		float f;
		float f1;
		float f2;
		float f3;
		float f4;
		float f5;

		texturemanager.bindTexture(texturemanager.getResourceLocation(par2ItemStack.getItemSpriteNumber()));
		Tessellator tessellator = Tessellator.instance;
		
		if(par1EntityLiving instanceof EntityPlayer) {

			IIcon icon = ((EntityPlayer)par1EntityLiving).getItemIcon(par2ItemStack, par3);
		
			if (icon == null)
			{
			GL11.glPopMatrix();
			return;
			}
			
			f = icon.getMinU();
			f1 = icon.getMaxU();
			f2 = icon.getMinV();
			f3 = icon.getMaxV();
			f4 = 0.0F;
			f5 = 0.3F;
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glTranslatef(-f4, -f5, 0.0F);
			float f6 = 1.5F;
			GL11.glScalef(f6, f6, f6);
			GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
			ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
		}
		
		if(par1EntityLiving instanceof EntityLiving) {
			
			//texturemanager.bindTexture(texturemanager.getResourceLocation(par2ItemStack.getItemSpriteNumber()));
			
			IIcon iconelse = ((EntityLiving)par1EntityLiving).getItemIcon(par2ItemStack, par3);
			if (iconelse == null)
			{
			GL11.glPopMatrix();
			return;
			}

			f = iconelse.getMinU();
			f1 = iconelse.getMaxU();
			f2 = iconelse.getMinV();
			f3 = iconelse.getMaxV();
			f4 = 0.0F;
			f5 = 0.3F;
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glTranslatef(-f4, -f5, 0.0F);
			float f6 = 1.5F;
			GL11.glScalef(f6, f6, f6);
			GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
			ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iconelse.getIconWidth(), iconelse.getIconHeight(), 0.0625F);
			
		}
		
		if (par2ItemStack.hasEffect(par3)) {
			GL11.glDepthFunc(GL11.GL_EQUAL);
			GL11.glDisable(GL11.GL_LIGHTING);
			texturemanager.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
		
			float f7 = 0.76F;
			GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
		GL11.glMatrixMode(GL11.GL_TEXTURE);
		GL11.glPushMatrix();
		float f8 = 0.125F;
		GL11.glScalef(f8, f8, f8);
		float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
		GL11.glTranslatef(f9, 0.0F, 0.0F);
		GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
		ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glScalef(f8, f8, f8);
		f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
		GL11.glTranslatef(-f9, 0.0F, 0.0F);
		GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
		ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		}
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}
	}
}*/
		
		