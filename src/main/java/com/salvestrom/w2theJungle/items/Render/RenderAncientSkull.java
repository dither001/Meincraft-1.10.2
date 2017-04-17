package com.salvestrom.w2theJungle.items.Render;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.mobs.models.ModelBrazier;
import com.salvestrom.w2theJungle.tileentity.TileEntityAncientSkull;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderAncientSkull extends TileEntitySpecialRenderer<TileEntityAncientSkull> {
	
	private static final ResourceLocation texture = new ResourceLocation("thejungle:textures/blocks/ancientSkeleton.png");
	private ModelSkeletonHead model;
    public static RenderAncientSkull instance;


	public RenderAncientSkull() {
		this.model = new ModelSkeletonHead(0, 0, 64, 32);
			}
	
    public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super.setRendererDispatcher(rendererDispatcherIn);
        instance = this;
    }

	@Override
	public void renderTileEntityAt(TileEntityAncientSkull tntt, double x, double y, double z, float f, int i) {
		
		TileEntityAncientSkull teas = (TileEntityAncientSkull) tntt;
	    float x2 = (float) x;
	    float y2 = (float) y;
        float z2 = (float) z;
	    
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x2+0.5F, (float)y2+0.0F, (float)z2+0.5F);
		GL11.glRotatef(180, 0, 0, 1F);
        GL11.glRotatef((float) (teas.direction*22.5), 0.0F, 1.0F, 0.0F);

		
		this.bindTexture(texture);

		GL11.glPushMatrix();
		this.model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625F);	

		GL11.glPopMatrix();
		GL11.glPopMatrix();

	}

}