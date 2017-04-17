package com.salvestrom.w2theJungle.mobs.render;

import java.util.List;
import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import scala.collection.parallel.immutable.ParVector.ParVectorIterator;

import com.salvestrom.w2theJungle.mobs.entity.EntityTheWall;
import com.salvestrom.w2theJungle.mobs.models.ModelTheWall;
import com.salvestrom.w2theJungle.mobs.render.layers.LayerLennyEyeBeam;
import com.salvestrom.w2theJungle.mobs.render.layers.LayerTheWallVine;

public class RenderTheWall extends RenderLiving<EntityTheWall> {

	public static final ResourceLocation theWall = new ResourceLocation("thejungle:textures/mobs/theWallTexture.png");
	public static final ResourceLocation yank = new ResourceLocation("thejungle:textures/mobs/vineyank.png");
	
	protected ModelTheWall model;
	
		
	public RenderTheWall(RenderManager rm, ModelBase par1ModelBase, float par2)
	{
		super(rm, par1ModelBase, par2);
		this.model = ((ModelTheWall)this.mainModel);
		this.addLayer(new LayerTheWallVine<EntityTheWall>(this, this.model));

	}


	public void doRender(EntityTheWall ntt, double par2, double par4, double par6, float par8, float par9) {
        //BossStatus.setBossStatus((EntityTheWall)par1ntt, false);
		super.doRender((EntityTheWall)ntt, par2, par4, par6, par8, par9);
	}
	
    protected void renderGraspingVines(EntityTheWall wall, float par2)
    {
        //GL11.glColor3f(1.0F, 1.0F, 1.0F);
      
//        EntityTheWall wall = (EntityTheWall)par1EntityLiving;

        
		float loc1;
		float loc2;
		float loc3;
		
		int timer = wall.effectTimer;
		int status = wall.getAttackStatus();
		float rads = (float) (180F/Math.PI);
		
        //System.out.println(status);
		List list = wall.worldObj.getEntitiesWithinAABBExcludingEntity(wall, wall.getEntityBoundingBox().expand(10, 0, 10));
		
		//for(int l = 0; l < list.size(); ++l)
		{
			
		//Entity entity = (Entity)list.get(l);
		
		//if(entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
        {
        }
        //else
		
		//if(entity instanceof EntityLivingBase && wall.getDistanceToEntity(entity) > 3)
		{

			loc1 = 29;//(float)entity.posX;
			loc2 = 79;//(float)entity.posY;
			loc3 = -23;//(float)entity.posZ;
		//if (timer == 0)
		{
	        this.bindTexture(yank);

			float x = (float) (Math.sin(wall.renderYawOffset/rads));
			float z = (float) (Math.cos(wall.renderYawOffset/rads));
			
            float f2 = (float) (wall.renderYawOffset/rads)+par2;
            float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
            f3 = (f3 * f3 + f3) * 0.2F;
            float f4 = (float) (loc1 - (wall.posX) - (wall.prevPosX - wall.posX) * (double)(1.0F - par2)); //(len.prevPosX - len.posX)
            float f5 = (float)((double)loc2 + .5D - (wall.posY) - (wall.prevPosY - wall.posY) * (double)(1.0F - par2)); //- (len.prevPosY - len.posY)
            float f6 = (float)((loc3 - wall.posZ) -  (wall.prevPosZ - wall.posZ) * (double)(1.0F - par2)); //- (len.prevPosZ - len.posZ)
            float f7 = MathHelper.sqrt_float(f4 * f4 + f6 * f6);
            //f8 is the length between the two mobs.
            float f8 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6);

        	float testa = (float)-(Math.atan2((double)f4, f6));
        	float testb = (float)(Math.asin((double)(f5/f8)));

        	GlStateManager.pushMatrix();
			//System.out.println(testa*rads);

        	//daisy chaining model boxes to attach beam to a child box. //set so that vine emerges from chest?
        	this.model.lowerbody.postRender(0.0625F);
            this.model.upperbody.postRender(0.0625F);

            //offset to abdomen
        	GL11.glTranslatef((float)0, (float)(+1.5), (float)0);

        	//adjustments to ignore all body movement.
        	GL11.glRotatef(-this.model.lowerbody.rotateAngleX*rads, (float)1, (float)0, (float)0);
        	GL11.glRotatef(-this.model.lowerbody.rotateAngleX*rads, (float)0, (float)1, (float)0);
            GL11.glRotatef(-this.model.upperbody.rotateAngleX*rads, (float)1, (float)0, (float)0);
            GL11.glRotatef(-this.model.upperbody.rotateAngleY*rads, (float)0, (float)1, (float)0);

        	GL11.glRotatef((testa*rads)+180, 0, 1, 0);
        	GL11.glRotatef(testb*rads, 1, 0, 0);
        	            
       	    Tessellator tessellator = Tessellator.getInstance();
       	    VertexBuffer vbuff = tessellator.getBuffer();
            RenderHelper.disableStandardItemLighting(); //was blank. still used, still interferes??
            GlStateManager.disableCull();
            GlStateManager.shadeModel(7425);
            //these two affect direction the animation moves, if you swap them below in addVertex.
            //latst multiplier affects animation speed
            float f9 = 0.0F - ((float)wall.ticksExisted + par2) * 0.003F;
            float f10 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F
            		- ((float)wall.ticksExisted + par2) * 0.003F;
            
            vbuff.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);
            byte b0 = 8; //seems to affect duration
            
            for (int i = 0; i <= b0; ++i)
            {
                float f11 = MathHelper.sin((float)(i % b0) * (f8/b0) * (float)Math.PI * 2.0F / (float)b0) * 2.75F * 4;// * timer/10;
                float f12 = MathHelper.cos((float)(i % b0) * (f8/b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F * 4;// * timer/10;
                float f13 = (float)(i % b0) * 1.0F / (float)b0;
               
                //-13583605
                
//                vbuff.getColorIndex(wall.worldObj.getBiomeGenForCoords(new BlockPos(wall.posX, wall.posY, wall.posZ)).getFoliageColorAtPos(new BlockPos(wall.posX, wall.posY, wall.posZ)));
                vbuff.pos((double)(f11*0.1), (double)f12*0.1, (double)0.0).tex((double)f13, (double)f10).color(10, 230, 120, 255).endVertex();
//                vbuff.getColorIndex(wall.worldObj.getBiomeGenForCoords(new BlockPos(loc1, loc2, loc3)).getFoliageColorAtPos(new BlockPos(wall.posX, wall.posY, wall.posZ)));
                vbuff.pos((double)(f11 * 0.51F), (double)((f12 * 0.51F)), (double)f8).tex((double)f13, (double)f9).color(10, 230, 30, 255).endVertex();
            }

            tessellator.draw();
            GlStateManager.enableCull();
            GlStateManager.shadeModel(7424);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.popMatrix();
		}
    }
		}
    }
    
	public void rotateCorpse(EntityTheWall nttb, float f1, float f2, float f3)
	{
		EntityTheWall nttl = (EntityTheWall)nttb;
		//this actually seems to work... well, it recognises the boolean, actual result is useless//
		boolean chk = nttl.isMovementCeased();
		
		//if(!chk)
		{
			GL11.glRotatef(180.0F - f2, 0.0F, 1.0F, 0.0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTheWall ntt) {
		return theWall;
	}
	



}
