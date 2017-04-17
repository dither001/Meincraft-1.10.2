package com.salvestrom.w2theJungle.items.Render;

import com.salvestrom.w2theJungle.mobs.models.ModelWallHead;
import com.salvestrom.w2theJungle.tileentity.TileEntityWallSkull;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderWallSkull extends TileEntitySpecialRenderer<TileEntityWallSkull> {

	private static final ResourceLocation texture = new ResourceLocation("thejungle:textures/mobs/theWallTexture.png");
	private ModelWallHead model;

	public RenderWallSkull() {
		this.model = new ModelWallHead();
		}

	@Override
	public void renderTileEntityAt(TileEntityWallSkull tntt, double x, double y, double z, float f, int i) {
		
		TileEntityWallSkull wsk = (TileEntityWallSkull)tntt;
	    float x2 = (float) x;
	    float y2 = (float) y;
        float z2 = (float) z;
	    
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x2+0.5F, (float)y2+0.250F, (float)z2+0.5F);
		GL11.glRotatef(180, 0, 0, 1F);
        GL11.glRotatef((float) (wsk.direction*22.5), 0.0F, 1.0F, 0.0F);
		
		this.bindTexture(texture);

		GL11.glPushMatrix();
		this.model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625F);	

		GL11.glPopMatrix();
		GL11.glPopMatrix();

	}

}
