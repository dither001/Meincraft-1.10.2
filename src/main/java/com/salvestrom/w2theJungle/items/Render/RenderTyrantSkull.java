package com.salvestrom.w2theJungle.items.Render;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.mobs.models.ModelLennyHead;
import com.salvestrom.w2theJungle.tileentity.TileEntityTyrantSkull;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTyrantSkull extends TileEntitySpecialRenderer<TileEntityTyrantSkull> {
		
		private static final ResourceLocation texture = new ResourceLocation("thejungle:textures/mobs/lennyTexture.png");
		private ModelLennyHead model;

		public RenderTyrantSkull() {
			this.model = new ModelLennyHead();
				}

		@Override
		public void renderTileEntityAt(TileEntityTyrantSkull tntt, double x, double y, double z, float f, int i) {
			
			//if(tntt.getBlockType().onNeighborChange(getWorld(), tntt.getPos(), tntt.getPos().east()))
			{
				
			}
//this doesnt work. it will cause the block to only appear once every second, which is giggle worthy, but not helpful			
//			if(this.getWorld().getWorldTime()%20 == 0)
			{
			
			TileEntityTyrantSkull tsk = (TileEntityTyrantSkull) tntt;
		    float x2 = (float) x;
		    float y2 = (float) y;
	        float z2 = (float) z;
		    
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x2+0.5F, (float)y2+0.0F, (float)z2+0.5F);
			GL11.glRotatef(180, 0, 0, 1F);
	        GL11.glRotatef((float) (tsk.getOrientation()*22.5), 0.0F, 1.0F, 0.0F);

			this.bindTexture(texture);

			GL11.glPushMatrix();
			this.model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625F);	

			GL11.glPopMatrix();
			GL11.glPopMatrix();

		}
		}

	}
