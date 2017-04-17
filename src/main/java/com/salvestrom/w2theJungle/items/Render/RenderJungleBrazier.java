package com.salvestrom.w2theJungle.items.Render;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.mobs.models.ModelBrazier;
import com.salvestrom.w2theJungle.tileentity.TileEntityJungleBrazier;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderJungleBrazier extends TileEntitySpecialRenderer<TileEntityJungleBrazier>{

	private static final ResourceLocation texture = new ResourceLocation("thejungle:textures/blocks/jungleBrazier.png");
	private static final ResourceLocation texture2 = new ResourceLocation("thejungle:textures/blocks/jungleBrazierLit.png");
	private ModelBrazier model;
	
	public RenderJungleBrazier() {
		this.model = new ModelBrazier();
	}
	
	@Override
	public void renderTileEntityAt(TileEntityJungleBrazier tntt, double x, double y, double z, float f, int i) {
		
		TileEntityJungleBrazier tesr = (TileEntityJungleBrazier)tntt;
		
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x+0.5F, (float)y+1.5F, (float)z+0.5F);
		GL11.glRotatef(180, 0, 0, 1F);

		if(tesr.lit) {
		
			this.bindTexture(texture2);
		} else
		if (!tesr.lit){
			this.bindTexture(texture);
		}
		
		GL11.glPushMatrix();
		this.model.renderModel(0.0625F);	
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		
	}


}

