package com.salvestrom.w2theJungle.items.Render;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.mobs.models.ModelJungleAltar;
import com.salvestrom.w2theJungle.tileentity.TileEntityAltarAbstract;
import com.salvestrom.w2theJungle.tileentity.TileEntityAncientSkull;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderAltarAbstract extends TileEntitySpecialRenderer<TileEntityAltarAbstract> {
	private static final ResourceLocation texture = new ResourceLocation("thejungle:textures/blocks/altarAbstract.png");
	private ModelJungleAltar model;

	public RenderAltarAbstract() {
		this.model = new ModelJungleAltar();
			}

	@Override
	public void renderTileEntityAt(TileEntityAltarAbstract tntt, double x, double y, double z, float f, int i)
	{
		
		TileEntityAltarAbstract teas = (TileEntityAltarAbstract) tntt;
	    float x2 = (float) x;
	    float y2 = (float) y;
        float z2 = (float) z;
	    
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x2+0.5F, (float)y2+0.5F, (float)z2+0.5F);
		//GL11.glRotatef(180, 0F, 0, 1F);
		GL11.glRotatef(180, 1F, 0, 0F);
		
        //GL11.glRotatef((float) (teas.direction*22.5), 0.0F, 1.0F, 0.0F);

		
		this.bindTexture(texture);

		//GL11.glPushMatrix();
		this.model.render(null, teas.getWorld().getWorldTime(), 0f, 0f, 0f, 0f, 0.0625F);	

		//GL11.glPopMatrix();
		GL11.glPopMatrix();

	}


}
