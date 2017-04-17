package com.salvestrom.w2theJungle.items.Render;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.mobs.models.ModelJungleBook;
import com.salvestrom.w2theJungle.tileentity.TileEntityFullScale;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderFullScale extends TileEntitySpecialRenderer<TileEntityFullScale> {
	private static final ResourceLocation texture = new ResourceLocation("thejungle:textures/blocks/fullScale.png");
	private ModelJungleBook model;

	public RenderFullScale() {
		this.model = new ModelJungleBook();
			}

	@Override
	public void renderTileEntityAt(TileEntityFullScale tntt, double x, double y, double z, float f, int i) {
		
		TileEntityFullScale teas = (TileEntityFullScale) tntt;
	    float x2 = (float) x;
	    float y2 = (float) y;
        float z2 = (float) z;
	    
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x2+0.5F, (float)y2+0.25F, (float)z2+0.5F);
		GL11.glRotatef(180, 0, 0, 1F);
        GL11.glRotatef((float) (teas.getOrientation()*22.5), 0.0F, 1.0F, 0.0F);

		
		this.bindTexture(texture);

		GL11.glPushMatrix();
		this.model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625F);	

		GL11.glPopMatrix();
		GL11.glPopMatrix();

	}


}
