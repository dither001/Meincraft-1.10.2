package com.salvestrom.w2theJungle.items.Render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.mobs.entity.EntityWeb;

//TODO  this file isnt even called...
@SideOnly(Side.CLIENT)
public class RenderThrownWeb extends Render<EntityWeb> {
	
    protected final Item item;
    private final RenderItem itemRenderer;
    
	public static final ResourceLocation thrownWeb = new ResourceLocation("thejungle:textures/items/thrownWeb.png");


    public RenderThrownWeb(RenderManager renderManagerIn, Item par1Item, RenderItem itemRendererIn)
    {
        super(renderManagerIn);
        this.item = par1Item;
        this.itemRenderer = itemRendererIn;
    }

    public void doRender(EntityWeb entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.itemRenderer.renderItem(this.getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    

    public ItemStack getStackToRender(Entity entityIn)
    {
        return new ItemStack(this.item);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityWeb par1Entity)
    {
        return thrownWeb;
    }
/*
    private void func_77026_a(Tessellator par1Tessellator)
    {
    	VertexBuffer vbuff = par1Tessellator.getBuffer();
    	
        float f = par2Icon.getMinU();
        float f1 = par2Icon.getMaxU();
        float f2 = par2Icon.getMinV();
        float f3 = par2Icon.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.Quads();
        vbuff.putNormal(0.0F, 1.0F, 0.0F);
        vbuff.pos((double)(0.0F - f5), (double)(0.0F - f6), 0.0D).tex((double)f, (double)f3);
        vbuff.pos((double)(f4 - f5), (double)(0.0F - f6), 0.0D).tex((double)f1, (double)f3);
        vbuff.pos((double)(f4 - f5), (double)(f4 - f6), 0.0D).tex((double)f1, (double)f2);
        vbuff.pos((double)(0.0F - f5), (double)(f4 - f6), 0.0D).tex((double)f, (double)f2);
        par1Tessellator.draw();
    }
    */
}