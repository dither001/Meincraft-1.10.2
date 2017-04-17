package com.salvestrom.w2theJungle.mobs.render.layers;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.mobs.entity.EntityLenny;
import com.salvestrom.w2theJungle.mobs.models.ModelZTR;
import com.salvestrom.w2theJungle.mobs.render.RenderLenny;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class LayerLennyEyeBeam<T extends EntityLenny> implements LayerRenderer<T> {

	protected final RenderLenny lentity;
	protected final ModelZTR model;
	protected final RenderManager renm;
    
	public static final ResourceLocation lennyEye = new ResourceLocation("thejungle:textures/mobs/lennyEyeBeam.png");

    public LayerLennyEyeBeam(RenderLenny livingEntityRendererIn, ModelZTR ztr, EntityLenny ntt, RenderManager rm)
    {
        (this.lentity) = livingEntityRendererIn;
        this.model = ztr;
        this.renm = rm;
    }
    
	@Override
	public void doRenderLayer(T ntt, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		int timer = ntt.getAnimationTimer();
        
		if(ntt.getAttType() == 3 && timer > 0)
        {
    		float rads = (float) (180F/Math.PI);
        	
            this.lentity.bindTexture(lennyEye);
            //the below is not required for the eyebeam, it was to compensate for the healingcrystals bobbing during enderdragon fight 
            float f0 = //(float) len.ticksExisted + partialTicks ;
            		(ntt.renderYawOffset/rads)+partialTicks;
            float f = MathHelper.sin(f0 * 0.2F) / 2.0F + 0.5F;
            f = (f * f + f) * 0.2F;
        
            //float xoffset = (float) (Math.sin(ntt.renderYawOffset/rads) * 3.14 );
    		//float zoffset = (float) (Math.cos(ntt.renderYawOffset/rads) * 3.14 );
            
			BlockPos pos = new BlockPos(ntt.getAttacker());// len.getAttackerPosX();
			
			float loc1; 
			float loc2;
			float loc3;

			loc1 = pos.getX();
			loc2 = pos.getY();
			loc3 = pos.getZ();

        	renderEyeBeam(ntt, ntt.posX, ntt.posY, ntt.posZ, partialTicks,
        			(loc1) - (ntt.posX ) + (ntt.prevPosX - ntt.posX) * (double)(1.0F - partialTicks),
        			(ntt.posY ) + (ntt.prevPosY - ntt.posY) * (double)(1.0F - partialTicks),
        			(loc3) - (ntt.posZ ) + (ntt.prevPosZ - ntt.posZ) * (double)(1.0F - partialTicks),
        			ntt.ticksExisted,
        			loc1,//-xoffset,
        			loc2,
        			loc3//-zoffset
        			);
        }
        //this is for showing bounding boxes in game 
        //this.func_85094_b(ntt, par2, par4, par6, rota, par9);
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void renderEyeBeam(EntityLenny len, double x, double y, double z, float partialTicks,
    		double lenX, double lenY, double lenZ, int ticksExisted,
    		double targetLocX, double targetLocY, double targetLocZ)
    {
		BlockPos pos = new BlockPos(len.getAttacker());// len.getAttackerPosX();

		float loc1 = pos.getX(); 
		float loc2 = pos.getY();
		float loc3 = pos.getZ();
/*
		loc1 = 41.5f;//pos.getX();
		loc2 = 39+1.5f;//pos.getY()+1.5f;
		loc3 = 0.5f;//pos.getZ();
*/		
		float rads = (float) (180F/Math.PI);

/*
        float hyp = MathHelper.sqrt_float(0.4f * 0.4f + 0.36f * 0.36f);
        /float opp2 = hyp * MathHelper.sin((42-this.model.head.rotateAngleZ*rads
        		)/rads);
        float adj1 = MathHelper.cos((42-this.model.head.rotateAngleZ*rads
        		)/rads) * hyp;
  */      
        //the furrther away lenny gets, the more inaccurate the line becomes, getting further and further from the mark.
        //as if the loc values are drifting.
        float xoffset = (float) (Math.sin(len.renderYawOffset/rads)*(3.15 + 0.4));
		float zoffset = (float) (Math.cos(len.renderYawOffset/rads)*(3.15 + 0.65));
		
		//loc1 -= 0.4;//+ opp2;
		//loc2 -= + adj1; 
		//loc3 += 0.65;
		
		//len.rotationYaw = len.ticksExisted;
		
		float xadd = (-xoffset);// + 0.4f);
		float zadd = zoffset;// + 0.65f;
		
		float f = (float) ((loc1) - (len.posX + (xadd))
				+ (len.prevPosX - len.posX)	* (double)(1.0F - partialTicks)
				);
		float f1 = (float) (loc2 - (len.posY + (5.25))
				+ (len.prevPosY - len.posY) * (double)(1.0F - partialTicks)
				);
		float f2 = (float) ((loc3) - (len.posZ + (zadd))
				+ (len.prevPosZ - len.posZ) * (double)(1.0F - partialTicks)
				);
		float f3 = MathHelper.sqrt_float((f  * f) + (f2 * f2));
        //f3 -= MathHelper.sqrt_float(xoffset * xoffset + zoffset * zoffset);
        float f4 = MathHelper.sqrt_float(f3 * f3 + f1 * f1);
        //f4 -= MathHelper.sqrt_float(xoffset * xoffset + zoffset * zoffset);

        float testa = (float)(-Math.atan2((double)f2, f));// - MathHelper.sqrt_float(xoffset * xoffset + zoffset * zoffset);
        float testb = (float)(-Math.atan2((double)f3, f1));
/*
        System.out.println(len.renderYawOffset);
        System.out.println(f);
        System.out.println(f2);
        System.out.println(f3);
*/
        
        //yaw = -2.8125 : f = 17.64; f2 = -11.133009 : f3 = 20
        // -113 : 4.21 : -8.659 : 9.63
        
        GlStateManager.pushMatrix();
        this.model.lowerbody.postRender(0.0625F);
        this.model.upperbody.postRender(0.0625F);
    	this.model.neck.postRender(0.0625F);
        this.model.head.postRender(0.0625F);

        GL11.glTranslatef((float)-0.40f, (float)-0.36, (float)-0.650f);

        GlStateManager.rotate(-this.model.lowerbody.rotateAngleX*rads, 1, 0, 0);
        GlStateManager.rotate(-this.model.lowerbody.rotateAngleY*rads, 0, 1, 0);
        //GlStateManager.rotate(-this.model.lowerbody.rotateAngleZ*rads, 1, 1, 0);

        GlStateManager.rotate(-this.model.upperbody.rotateAngleX*rads, 1, 0, 0);
        GlStateManager.rotate(-this.model.upperbody.rotateAngleY*rads, 0, 1, 0);
        //GlStateManager.rotate(-this.model.upperbody.rotateAngleZ*rads, 0, 0, 1);
        //GlStateManager.rotate(this.model.upperbody.rotateAngleZ*rads*2, 1, 0, 0);
        
    	GlStateManager.rotate(-this.model.neck.rotateAngleX*rads, 1, 0, 0);
        GlStateManager.rotate(-this.model.neck.rotateAngleY*rads, 0, 1, 0);
        //GlStateManager.rotate(-this.model.neck.rotateAngleZ*rads, 0, 0, 1);
        //GlStateManager.rotate(this.model.neck.rotateAngleZ*rads*2, 1, 0, 0);
            	
        GlStateManager.rotate(-this.model.head.rotateAngleX*rads, 1, 0, 0);
        GlStateManager.rotate(-this.model.head.rotateAngleY*rads, 0, 1, 0);
        //GlStateManager.rotate(-this.model.head.rotateAngleZ*rads, 0, 0, 1);
        //GlStateManager.rotate(-this.model.head.rotateAngleZ*rads, 0, 1, 0);
        //GlStateManager.rotate(this.model.head.rotateAngleZ*rads*2, 1, 0, 0);

        GlStateManager.rotate(-testa*rads + 90 - len.renderYawOffset - (0), 0, 1, 0);
        
        float test = (float)
        		((testb*rads)
        				//- ((this.model.head.rotateAngleZ
        				//+ this.model.neck.rotateAngleZ
        				//+ this.model.upperbody.rotateAngleZ) * rads)
        				+ 90);

        GlStateManager.rotate(test, 1, 0, 0);

//        GL11.glRotatef(this.model.eye.rotateAngleX*rads, (float)1, (float)0, (float)0);
//        GL11.glRotatef(this.model.eye.rotateAngleY*rads, (float)0, (float)1, (float)0);
//        GL11.glRotatef(this.model.eye.rotateAngleZ*rads, (float)0, (float)0, (float)1);

        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vbuff = tessellator.getBuffer();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableCull();
        GlStateManager.shadeModel(7425);
        
        //these two affect direction the animation moves, if you swap them below in addVertex.
        float f5 = 0.0F - ((float)ticksExisted + partialTicks) * 0.01F;
        float f6 = MathHelper.sqrt_float(f * f + f1 * f1 + f2 * f2) / 32.0F - ((float)ticksExisted + partialTicks) * 0.01F;
        vbuff.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);
        int i = 8;
        
        for (int j = 0; j <= i; ++j)
        {
        	float f7 = MathHelper.sin((float)(j % i) * ((float)Math.PI * 2.0F) / (float)i) * 0.75F;
        	float f8 = MathHelper.cos((float)(j % i) * ((float)Math.PI * 2.0F) / (float)i) * 0.75F;
        	float f9 = (float)(j % i) / (float)i;

        	vbuff.pos((double)(f7*0.1), (double)f8*0.1, (double)0.0).tex((double)f9, (double)f5).color(159, 222, 255, 255).endVertex();
        	vbuff.pos((double)(f7 * 0.051F), (double)((f8 * 0.051F)), (double)f4).tex((double)f9, (double)f6).color(0, 221, 255, 255).endVertex();
        }
        
        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.shadeModel(7424);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
	}

	
}
