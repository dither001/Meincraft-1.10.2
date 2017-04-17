package com.salvestrom.w2theJungle.items.Render;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.tileentity.TileEntityAltarAbstract;
/*
public class ItemRenderAltarAbstract extends RenderEntity {
	
	TileEntitySpecialRenderer render;
	private TileEntity entity;

	public ItemRenderAltarAbstract(TileEntitySpecialRenderer renderAA, TileEntityAltarAbstract teas) {
		this.render = renderAA;
		this.entity = teas;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderer type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderer type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderHeldItem(ItemCameraTransforms.TransformType type, ItemStack item, Object... data) {

		float s;
		
		switch(type) {
		case ENTITY:
			
			s = 2F;
			
			GL11.glPushMatrix();
			GL11.glScalef(s, s, s);
			GL11.glRotatef(180, 0, 1, 0);

			GL11.glTranslatef(-0.5F, -0.25F, -0.5F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);

			GL11.glPopMatrix();
			break;
		
		case INVENTORY:
			
			GL11.glPushMatrix();
			
			s = 1F;
			
			GL11.glScalef(s, s, s);
			GL11.glRotatef(90, 0, 1, 0);

			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);
			
			GL11.glPopMatrix();
			break;
			
		default:

			GL11.glPushMatrix();

			s = 1F;
			GL11.glScalef(s, s, s);
			//GL11.glRotatef(180, 0, 1, 0);
			GL11.glTranslatef(-0F, 0.0F, -0F);


			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);
			GL11.glPopMatrix();
			
			
			break;

		}

	}

}
*/