package com.salvestrom.w2theJungle.mobs.render.layers;

import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanScuffler;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanStalker;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanWitchDoctor;
import com.salvestrom.w2theJungle.mobs.models.ModelLizardman;
import com.salvestrom.w2theJungle.mobs.models.ModelLizardmanStalker;
import com.salvestrom.w2theJungle.mobs.models.ModelLizardmanWitchDoctor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;

public class LayerSaurohnHeldItem implements LayerRenderer<EntityLivingBase> {

	protected final RenderLivingBase<?> livingEntityRenderer;
    
    public LayerSaurohnHeldItem(RenderLivingBase<?> livingEntityRendererIn)
    {
        (this.livingEntityRenderer) = livingEntityRendererIn;
    }

    public void doRenderLayer(EntityLivingBase 
    		entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (itemstack != null || itemstack1 != null)
        {
            GlStateManager.pushMatrix();

            if (this.livingEntityRenderer.getMainModel().isChild)
            {
                float f = 0.5F;
                GlStateManager.translate(0.0F, 0.625F, 0.0F);
                GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
                GlStateManager.scale(f, f, f);
            }

            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }

    private void renderHeldItem(EntityLivingBase ntt, ItemStack istack, ItemCameraTransforms.TransformType p_188358_3_, EnumHandSide handSide)
    {
        if (istack != null)
        {
            GlStateManager.pushMatrix();

            if (ntt.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            
            {
            if(ntt instanceof EntityLizardmanScuffler)
            {            
            	ModelLizardman liz = ((ModelLizardman)this.livingEntityRenderer.getMainModel());


                liz.lowerbody.postRender(0.0625F);
                liz.body.postRender(0.0625F);
                liz.upperRarm.postRender(0.0625F);
                liz.lowerRarm.postRender(0.0625F);
            	liz.Righthand.postRender(0.0625F);

                GlStateManager.translate(0.025F, -0.3F, 0.165F); //l/r, f/b, d/-u

                GlStateManager.rotate(-5F, 0.0F, 0F, 1.0F);
                GlStateManager.rotate(-11F, 1F, 0.0F, 0.0F);
                GlStateManager.rotate(-0F, 0.0F, 1F, 0.0F);   
                
                GlStateManager.scale(0.8, 0.8, 0.8);
                     
            }
            else
                if(ntt instanceof EntityLizardmanStalker)
                {
                	 ModelLizardmanStalker liz =  ((ModelLizardmanStalker)this.livingEntityRenderer.getMainModel());
                	 
                     liz.lowerbody.postRender(0.0625F);
                     liz.body.postRender(0.0625F);
                     liz.upperRarm.postRender(0.0625F);
                     liz.lowerRarm.postRender(0.0625F);
                     liz.Righthand.postRender(0.0625F);
                }
                else
                	if(ntt instanceof EntityLizardmanWitchDoctor)
                    {
                    	 ModelLizardmanWitchDoctor liz =  ((ModelLizardmanWitchDoctor)this.livingEntityRenderer.getMainModel());
                    	 
                         liz.lowerbody.postRender(0.0625F);
                         liz.body.postRender(0.0625F);
                         liz.upperRarm.postRender(0.0625F);
                         liz.lowerRarm.postRender(0.0625F);
                         liz.Righthand.postRender(0.0625F);
                         
                         GlStateManager.translate(-0.335F, -0.0F, 0.35F); //r/-l, f/-b, d/-u

                         GlStateManager.rotate(-85F, 0.0F, 0F, 1.0F);
                         GlStateManager.rotate(-11F, 1F, 0.0F, 0.0F);
                         GlStateManager.rotate(-0F, 0.0F, 1F, 0.0F);
                         
                         GlStateManager.scale(0.8, 0.8, 0.8);

                    }
            
            if(istack.getItem() == Items.BOW || istack.getItem() == JungleItems.boneGripBow)
            {
            	EntityLizardmanStalker lizs = (EntityLizardmanStalker)ntt;
                //GlStateManager.translate(0.04F, 0.24F, -0.1F);
            	//GlStateManager.rotate(lizs.getHasTarget() == 2 ? 10F : 15F, 0.0F, 1F, 0F); //70 //45 0 1 0 -- 35
                //GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
                //GlStateManager.rotate(85F, 0.0F, 1F, 0.0F); //5 //-20 0 1 0 -- 65

                GlStateManager.translate(0.02346F, 0.034F, -0.2F); //0.04, 0.07, 0.38125 //0.0 0.125 0.3125 -- -0.5 -0.384125 -0.774
                //GlStateManager.rotate(-5F, 0.0F, 1F, 0.0F); //5 //-20 0 1 0 -- 65
                GlStateManager.rotate(30F, 0.0F, 0F, 1.0F); //5 //-20 0 1 0 -- 65
                GlStateManager.rotate(-90.0F, 1F, 0.0F, 0.0F); //110 //-100 1 0 0 -- 15
                GlStateManager.rotate(lizs.getHasTarget() == 2 ? 145F : 185F, 0.0F, 1F, 0F); 
                
            	//alter by first considering that the default positions are 90 further round than before.
            }
            else
            {
            	
            // Forge: moved this call down, fixes incorrect offset while sneaking.
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            boolean flag = handSide == EnumHandSide.LEFT;
            GlStateManager.translate(flag ? -0.0625F : 0.0625F, 0.125F, -0.625F);
            }
            boolean flag = handSide == EnumHandSide.LEFT;

//            GlStateManager.translate(0.04F, 0.04125F, -0.38625F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(ntt, istack, p_188358_3_, flag);
            GlStateManager.popMatrix();
        }}
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }

}
