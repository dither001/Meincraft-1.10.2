package com.salvestrom.w2theJungle.items.Render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHandSide;

import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.tileentity.TileEntityTyrantSkull;
/*
public class ItemRenderTyrantSkull extends ItemRenderer {
	

	TileEntitySpecialRenderer render;
	private TileEntity entity;
	
	public ItemRenderTyrantSkull(TileEntitySpecialRenderer renderAS, TileEntityTyrantSkull tntt) {
		this.render = renderAS;
		this.entity = tntt;
		
        this.livingEntityRenderer = renderAS;

	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

    private void renderHeldItem(EntityLivingBase p_188358_1_, ItemStack p_188358_2_, ItemCameraTransforms.TransformType type, EnumHandSide handSide)
	{
		float s = 2.5F;
		
		switch(type) {
		case GROUND:
			
			GL11.glPushMatrix();
			GL11.glScalef(s, s, s);
			GL11.glRotatef(180, 0, 1, 0);

			GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);

			GL11.glPopMatrix();
			break;
		
		case GUI:
			
			GL11.glPushMatrix();
			
			s = 0.90F;
			
			GL11.glScalef(s, s, s);
			GL11.glRotatef(270, 0, 1, 0);

			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);
			
			GL11.glPopMatrix();
			break;
			
		default:

			GL11.glPushMatrix();

			s = 1F;
			GL11.glScalef(s, s, s);
			//GL11.glRotatef(180, 0, 1, 0);
			GL11.glTranslatef(-0.0F, 0.0F, -0.250F);


			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);
			GL11.glPopMatrix();
			
			
			break;

		}
	}

	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn,
			float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
        boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (itemstack != null || itemstack1 != null)
        {
            GlStateManager.pushMatrix();

            if (this.render.getMainModel().isChild)
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
	
	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

}
*/