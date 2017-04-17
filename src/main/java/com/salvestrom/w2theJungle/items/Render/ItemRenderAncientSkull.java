package com.salvestrom.w2theJungle.items.Render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.tileentity.TileEntityAncientSkull;
/*
public class ItemRenderAncientSkull implements IItemRenderer {

	TileEntitySpecialRenderer render;
	private TileEntity entity;
	
	public ItemRenderAncientSkull(TileEntitySpecialRenderer renderAS, TileEntityAncientSkull tntt) {
		this.render = renderAS;
		this.entity = tntt;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		float s = 2.5F;
		
		switch(type) {
		case ENTITY:
			
			GL11.glPushMatrix();
			GL11.glScalef(s, s, s);
			GL11.glRotatef(180, 0, 1, 0);

			GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);

			GL11.glPopMatrix();
			break;
		
		case INVENTORY:
			
			GL11.glPushMatrix();
			
			s = 1.5F;
			
			GL11.glScalef(s, s, s);
			GL11.glRotatef(270, 0, 1, 0);

			GL11.glTranslatef(-0.5F, -0.25F, -0.5F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);
			
			GL11.glPopMatrix();
			break;
			
		default:

			GL11.glPushMatrix();

			s = 2F;
			GL11.glScalef(s, s, s);
			//GL11.glRotatef(180, 0, 1, 0);
			GL11.glTranslatef(-0.25F, 0.0F, -0.25F);


			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);
			GL11.glPopMatrix();
			
			
			break;

		}
	}
	}
*/
