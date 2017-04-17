package com.salvestrom.w2theJungle.mobs.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.mobs.entity.EntityLenny;
import com.salvestrom.w2theJungle.mobs.models.ModelZTR;
import com.salvestrom.w2theJungle.mobs.render.layers.LayerLennyEyeBeam;

public class RenderLenny extends RenderLiving<EntityLenny> {

	public static final ResourceLocation lenny = new ResourceLocation("thejungle:textures/mobs/lennyTexture.png");
	public static final ResourceLocation lennyEye = new ResourceLocation("thejungle:textures/mobs/lennyEyeBeam.png");

	protected ModelZTR model;
	protected EntityLenny len;
	public RenderLenny(RenderManager rm, ModelZTR par1ModelBase, float par2)
	{
		super(rm, par1ModelBase, par2);
		this.model = par1ModelBase;
		this.addLayer(new LayerLennyEyeBeam<EntityLenny>(this, model, len, rm));

		}

	protected void preRenderCallback(EntityLenny nttL, float par2)
	{	
	    float reduc = 1.2F;
		GL11.glScalef(reduc, reduc, reduc);
		
        super.preRenderCallback(nttL, par2);
    }

	public void doRender(EntityLenny len, double x, double y, double z, float rota, float partialTicks)
	{
		super.doRender((EntityLenny)len, x, y, z, rota, partialTicks);
		this.len=len;
		}
    
    protected void renderEyeBeam(EntityLenny len, double x, double y, double z, float p9,
    		double lenX, double lenY, double lenZ, int ticksExisted,
    		double targetLocX, double targetLocY, double targetLocZ)
    {
		float rads = (float) (180F/Math.PI);
		float xoffset = (float) (Math.sin(len.renderYawOffset/rads)*4);
		float zoffset = (float) (Math.cos(len.renderYawOffset/rads)*4);

		float f = (float)lenX;//(targetLocX - lenX);
		float f1 = //(float)(lenY - targetLocY - 1.0D);// - ((this.model.lowerbody.rotationPointY+20)/16) );
//		System.out.println((this.model.lowerbody.rotationPointY + 20) / 16);
		(float)(targetLocY - 1.0D - lenY);// - (this.model.lowerbody.rotationPointY/16));
		float f2 = (float)lenZ;//(targetLocZ - lenZ);
        float f3 = MathHelper.sqrt_float(f * f + f2 * f2);
        float f4 = MathHelper.sqrt_float(f * f + f1 * f1 + f2 * f2);

       	float testa = (float)(-Math.atan2((double)f2, f));
        float testb = (float)(-Math.atan2((double)f3, f1));
        
        GlStateManager.pushMatrix();
/*
        this.model.lowerbody.postRender(0.0625F);
        this.model.upperbody.postRender(0.0625F);
    	this.model.neck.postRender(0.0625F);
        this.model.head.postRender(0.0625F);
        */

        GlStateManager.rotate((testa*rads) - 90, 0, 1, 0);
        GlStateManager.rotate((testb*rads) - 90, 1, 0, 0);
        GlStateManager.rotate((testb*rads) - 90, 0, 0, 1);
        /*
        GlStateManager.rotate(-this.model.lowerbody.rotateAngleX*rads, (float)1, (float)0, (float)0);
        GlStateManager.rotate(-this.model.lowerbody.rotateAngleY*rads, (float)0, (float)1, (float)0);
        GlStateManager.rotate(-this.model.lowerbody.rotateAngleZ*rads, (float)0, (float)0, (float)1);

        GlStateManager.rotate(-this.model.upperbody.rotateAngleX*rads, (float)1, (float)0, (float)0);
        GlStateManager.rotate(-this.model.upperbody.rotateAngleY*rads, (float)0, (float)1, (float)0);
        GlStateManager.rotate(-this.model.upperbody.rotateAngleZ*rads, (float)0, (float)0, (float)1);
        
    	GlStateManager.rotate(-this.model.neck.rotateAngleX*rads, (float)1, (float)0, (float)0);
        GlStateManager.rotate(-this.model.neck.rotateAngleY*rads, (float)0, (float)1, (float)0);
        GlStateManager.rotate(-this.model.neck.rotateAngleZ*rads, (float)0, (float)0, (float)1);
            	
        GlStateManager.rotate(-this.model.head.rotateAngleX*rads, (float)1, (float)0, (float)0);
        GlStateManager.rotate(-this.model.head.rotateAngleY*rads, (float)0, (float)1, (float)0);
        GlStateManager.rotate(-this.model.head.rotateAngleZ*rads, (float)0, (float)0, (float)1);
        //this.model.eye.postRender(0.0625F);
         * 
         */
        
        //GlStateManager.translate((float)-this.model.lowerbody.rotationPointX/16,
        //		(float)-this.model.lowerbody.rotationPointY/16,
        //		(float)-this.model.lowerbody.rotationPointZ/16);
        
        //GlStateManager.translate((float)0, (float)-(this.model.lowerbody.rotationPointY)/16, 0);
        
        //GlStateManager.translate((float)-this.model.upperbody.rotationPointX/16,
        //		(float)-this.model.upperbody.rotationPointY/16,
        //		(float)-this.model.upperbody.rotationPointZ/16);
    	GL11.glTranslatef((float)-0.40, (float)-0.36, (float)-0.650); //bake in these offsets to the f4-f6 equations?

        /*
        GlStateManager.rotate(-this.model.upperbody.rotateAngleX*rads, (float)1, (float)0, (float)0);
        GlStateManager.rotate(-this.model.upperbody.rotateAngleY*rads, (float)0, (float)1, (float)0);
        GlStateManager.rotate(-this.model.upperbody.rotateAngleZ*rads, (float)0, (float)0, (float)1);
        
        GlStateManager.rotate(-this.model.neck.rotateAngleX*rads, (float)1, (float)0, (float)0);
        GlStateManager.rotate(-this.model.neck.rotateAngleY*rads, (float)0, (float)1, (float)0);
        GlStateManager.rotate(-this.model.neck.rotateAngleZ*rads, (float)0, (float)0, (float)1);
        
        GlStateManager.rotate(-this.model.head.rotateAngleX*rads, (float)1, (float)0, (float)0);
        GlStateManager.rotate(-this.model.head.rotateAngleY*rads, (float)0, (float)1, (float)0);
        GlStateManager.rotate(-this.model.head.rotateAngleZ*rads, (float)0, (float)0, (float)1);
*/
//        GL11.glRotatef(this.model.eye.rotateAngleX*rads, (float)1, (float)0, (float)0);
//        GL11.glRotatef(this.model.eye.rotateAngleY*rads, (float)0, (float)1, (float)0);
//        GL11.glRotatef(this.model.eye.rotateAngleZ*rads, (float)0, (float)0, (float)1);

        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vbuff = tessellator.getBuffer();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableCull();
        GlStateManager.shadeModel(7425);
        
        //these two affect direction the animation moves, if you swap them below in addVertex.
        float f5 = 0.0F - ((float)ticksExisted + p9) * 0.01F;
        float f6 = MathHelper.sqrt_float(f * f + f1 * f1 + f2 * f2) / 32.0F - ((float)ticksExisted + p9) * 0.01F;
        vbuff.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);
        int i = 8;
        
        for (int j = 0; j <= i; ++j)
        {
        	float f7 = MathHelper.sin((float)(j % i) * ((float)Math.PI * 2.0F) / (float)i) * 0.75F;
        	float f8 = MathHelper.cos((float)(j % i) * ((float)Math.PI * 2.0F) / (float)i) * 0.75F;
        	float f9 = (float)(j % i) / (float)i;
        	
        	//vbuff.putColorRGB_F(10, 1, 1, (int) 0.15f);
        	vbuff.pos((double)(f7*0.1), (double)f8*0.1, (double)0.0).tex((double)f9, (double)f5).color(159, 222, 255, 255).endVertex();
        	vbuff.pos((double)(f7 * 0.051F), (double)((f8 * 0.051F)), (double)f4).tex((double)f9, (double)f6).color(0, 221, 255, 255).endVertex();
        }
        
        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.shadeModel(7424);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
	}
    
	public void rotateCorpse(EntityLenny nttb, float f1, float f2, float f3)
	{
        GL11.glRotatef(180.0F - f2, 0.0F, 1.0F, 0.0F);		
	}	

	@Override
	protected ResourceLocation getEntityTexture(EntityLenny ntt) {
		return lenny;
	}


	/*
	  	      protected void renderEyeBeam(EntityLenny ntt, double x, double p6, double p8,
	 		float p9,
    		double lenX, double lenY, double lenZ,
    		int ticksExisted,
    		double targetLocX, double targetLocY, double targetLoxZ)
    {
        //GL11.glColor3f(1.0F, 1.0F, 1.0F);
     
        EntityLenny len = (EntityLenny)ntt;

		//doubles are distance to the object relative to the player... (int the above doRender)
		BlockPos pos = new BlockPos(len.getAttacker());// len.getAttackerPosX();
		float loc1; 
		float loc2;
		float loc3;
		//x y and z of attacked entity stored and retrieved ia datawatcher.
		loc1 = 40;// pos.getX();
		loc2 = 43;//pos.getY()+3.5f;
		loc3 = 10;// pos.getZ();
		//float eyeY =  len.getEyeHeight();
		
		int timer = len.getAnimationTimer();
		float rads = (float) (180F/Math.PI);

		//if (timer > 0)
		{
			float xoffset = 0;//(float) (Math.sin(len.renderYawOffset/rads) * 4. );
			float zoffset = 0;//(float) (Math.cos(len.renderYawOffset/rads) * 4. );
			
            float f2 = //(float) len.ticksExisted + p9 ;
            		(len.renderYawOffset/rads)+p9;
            float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
            f3 = (f3 * f3 + f3) * 0.2F;
            float f4 = (float) (
            		loc1 
            		- 
            		(len.posX-xoffset + (len.prevPosX - len.posX-xoffset) * (double)(1.0F - p9))); //(len.prevPosX - len.posX)
            float f5 = (float)((double)(f3 + loc2 - 1.0D) - (len.posY + (len.prevPosY - len.posY) * (double)(1.0F - p9))); //- (len.prevPosY - len.posY)
            float f6 = (float)(
            		loc3
            		-
            		(len.posZ-zoffset + (len.prevPosZ - len.posZ-zoffset) * (double)(1.0F - p9))); //- (len.prevPosZ - len.posZ)
            
            /*
            System.out.println(f4);
            System.out.println();
            System.out.println(f5);
            System.out.println();
            System.out.println(f6);
			
            float f7 = MathHelper.sqrt_float(f4 * f4 + f6 * f6);
            //f8 is the length between the two mobs.
            float f8 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6);
            //f8 *= 0.9;
        	float testa = (float)-(Math.atan2((double)f6, f4)); //4 then 6
        	float testb = (float)-(Math.atan2((double)f7, f5));
        	//(float)(Math.asin((double)(f5/f8)));
            
        	GlStateManager.pushMatrix();
        	//daisy chaining model boxes to attach beam to a child box.
        	/*
        	this.model.lowerbody.postRender(0.0625F);
            this.model.upperbody.postRender(0.0625F);
        	this.model.neck.postRender(0.0625F);
        	this.model.head.postRender(0.0625F);
        	*/
        	//this.model.eye.postRender(0.0625F);
        	
        	//small off set to center on eye (counter eye rotation?? and remove...        	
        	//GlStateManager.translate((float)-0.40, (float)-0.36, (float)-0.650); //bake in these offsets to the f4-f6 equations?

        	//GL11.glRotatef(-this.model.eye.rotateAngleX*rads, (float)1, (float)0, (float)0);
        	//GL11.glRotatef(-this.model.eye.rotateAngleY*rads, (float)0, (float)1, (float)0);
        	//GL11.glRotatef(-this.model.eye.rotateAngleZ*rads, (float)0, (float)0, (float)1);

        	//the following are intended to cancel out certain animations such as breathing. why an undead dinosaur has a breathing animation is anyone's guess.
        	/*
        	GlStateManager.rotate(-this.model.lowerbody.rotateAngleX*rads, (float)1, (float)0, (float)0);
        	GlStateManager.rotate(-this.model.upperbody.rotateAngleX*rads, (float)1, (float)0, (float)0);
        	
        	GlStateManager.rotate(-this.model.neck.rotateAngleX*rads, (float)1, (float)0, (float)0);
        	GlStateManager.rotate(-this.model.neck.rotateAngleY*rads, (float)0, (float)1, (float)0);
        	
        	GlStateManager.rotate(-this.model.head.rotateAngleX*rads, (float)1, (float)0, (float)0);
        	GlStateManager.rotate(-this.model.head.rotateAngleY*rads, (float)0, (float)1, (float)0);
     
        	GlStateManager.rotate((testa*rads) + 180 - len.renderYawOffset, 0, 1, 0);
        	GlStateManager.rotate((testb*rads) + 180, 1, 0, 0);
        	            
       	    Tessellator tessellator = Tessellator.getInstance();
       	    VertexBuffer vbuff = tessellator.getBuffer();
       	    RenderHelper.disableStandardItemLighting();
            GlStateManager.disableCull();
            GlStateManager.shadeModel(7425);
            //these two affect direction the animation moves, if you swap them below in addVertex.
            float f9 = 0.0F - ((float)len.ticksExisted + p9) * 0.01F;
            float f10 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F - ((float)len.ticksExisted + p9) * 0.01F;
            
            vbuff.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);
            int b0 = 8;
            
            for (int i = 0; i <= b0; ++i)
            {
                float f11 = MathHelper.sin((float)(i % b0) * ((float)Math.PI * 2.0F) / (float)b0) * 0.75F;
                float f12 = MathHelper.cos((float)(i % b0) * ((float)Math.PI * 2.0F) / (float)b0) * 0.75F;
                float f13 = (float)(i % b0) / (float)b0;
            
                vbuff.putColorRGB_F(10, 1, 1, (int) 0.15f);
                //vbuff.getColorIndex(0x6DDEFF);
                vbuff.pos((double)(f11*0.1), (double)f12*0.1, (double)0.0).tex((double)f13, (double)f10).color(109, 222, 255, 255).endVertex();
                //vbuff.getColorIndex(0x00DDFF);
                vbuff.pos((double)(f11 * 0.051F), (double)((f12 * 0.051F)), (double)f8).tex((double)f13, (double)f9).color(0, 221, 255, 255).endVertex();
            }
            
            tessellator.draw();
            GlStateManager.enableCull();
            GlStateManager.shadeModel(7424);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.popMatrix();
		}
    }
	*/
    
    /*
    //this code was written by bewi and operated on by gummby8. it should draw bounding boxes for multiparts
    private void func_85094_b(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	
    	
        //GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        //GL11.glDisable(GL11.GL_LIGHTING);
        //GL11.glDisable(GL11.GL_CULL_FACE);
        //GL11.glDisable(GL11.GL_BLEND);
        GL11.glPushMatrix();
        Tessellator var10 = Tessellator.instance;
        var10.startDrawingQuads();
        var10.setColorRGBA(200, 200, 200, 32);
        
        
        Entity[] parts = par1Entity.getParts();
        if(parts != null)
        {
	        for(Entity part : parts)
	        {
	            var10.addVertex(part.boundingBox.minX, part.boundingBox.minY, part.boundingBox.minZ);
	            var10.addVertex(part.boundingBox.minX, part.boundingBox.maxY, part.boundingBox.minZ);
	            var10.addVertex(part.boundingBox.minX, part.boundingBox.maxY, part.boundingBox.maxZ);
	            var10.addVertex(part.boundingBox.minX, part.boundingBox.minY, part.boundingBox.maxZ);
	                 
	            var10.addVertex(part.boundingBox.minX, part.boundingBox.minY, part.boundingBox.minZ);
	            var10.addVertex(part.boundingBox.maxX, part.boundingBox.minY, part.boundingBox.minZ);
	            var10.addVertex(part.boundingBox.maxX, part.boundingBox.maxY, part.boundingBox.minZ);
	            var10.addVertex(part.boundingBox.minX, part.boundingBox.maxY, part.boundingBox.minZ);
	               
	            var10.addVertex(part.boundingBox.maxX, part.boundingBox.minY, part.boundingBox.minZ);
	            var10.addVertex(part.boundingBox.maxX, part.boundingBox.maxY, part.boundingBox.minZ);
	            var10.addVertex(part.boundingBox.maxX, part.boundingBox.maxY, part.boundingBox.maxZ);
	            var10.addVertex(part.boundingBox.maxX, part.boundingBox.minY, part.boundingBox.maxZ);
	                    
	            var10.addVertex(part.boundingBox.minX, part.boundingBox.minY, part.boundingBox.maxZ);
	            var10.addVertex(part.boundingBox.maxX, part.boundingBox.minY, part.boundingBox.maxZ);
	            var10.addVertex(part.boundingBox.maxX, part.boundingBox.maxY, part.boundingBox.maxZ);
	            var10.addVertex(part.boundingBox.minX, part.boundingBox.maxY, part.boundingBox.maxZ);
	        }
        } else {
        
        var10.addVertex(par1Entity.boundingBox.minX, par1Entity.boundingBox.minY, par1Entity.boundingBox.minZ);
        var10.addVertex(par1Entity.boundingBox.minX, par1Entity.boundingBox.maxY, par1Entity.boundingBox.minZ);
        var10.addVertex(par1Entity.boundingBox.minX, par1Entity.boundingBox.maxY, par1Entity.boundingBox.maxZ);
        var10.addVertex(par1Entity.boundingBox.minX, par1Entity.boundingBox.minY, par1Entity.boundingBox.maxZ);
                   
        var10.addVertex(par1Entity.boundingBox.minX, par1Entity.boundingBox.minY, par1Entity.boundingBox.minZ);
        var10.addVertex(par1Entity.boundingBox.maxX, par1Entity.boundingBox.minY, par1Entity.boundingBox.minZ);
        var10.addVertex(par1Entity.boundingBox.maxX, par1Entity.boundingBox.maxY, par1Entity.boundingBox.minZ);
        var10.addVertex(par1Entity.boundingBox.minX, par1Entity.boundingBox.maxY, par1Entity.boundingBox.minZ);
                       
        var10.addVertex(par1Entity.boundingBox.maxX, par1Entity.boundingBox.minY, par1Entity.boundingBox.minZ);
        var10.addVertex(par1Entity.boundingBox.maxX, par1Entity.boundingBox.maxY, par1Entity.boundingBox.minZ);
        var10.addVertex(par1Entity.boundingBox.maxX, par1Entity.boundingBox.maxY, par1Entity.boundingBox.maxZ);
        var10.addVertex(par1Entity.boundingBox.maxX, par1Entity.boundingBox.minY, par1Entity.boundingBox.maxZ);
                     
        var10.addVertex(par1Entity.boundingBox.minX, par1Entity.boundingBox.minY, par1Entity.boundingBox.maxZ);
        var10.addVertex(par1Entity.boundingBox.maxX, par1Entity.boundingBox.minY, par1Entity.boundingBox.maxZ);
        var10.addVertex(par1Entity.boundingBox.maxX, par1Entity.boundingBox.maxY, par1Entity.boundingBox.maxZ);
        var10.addVertex(par1Entity.boundingBox.minX, par1Entity.boundingBox.maxY, par1Entity.boundingBox.maxZ);
        
        }

        
        GL11.glTranslatef((float)(par2-par1Entity.posX), (float)(par4-par1Entity.posY), (float)(par6-par1Entity.posZ));
        var10.draw();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        //GL11.glEnable(GL11.GL_LIGHTING);
        //GL11.glEnable(GL11.GL_CULL_FACE);
        //GL11.glDisable(GL11.GL_BLEND);
        //GL11.glDepthMask(true);
    }
*/
}
