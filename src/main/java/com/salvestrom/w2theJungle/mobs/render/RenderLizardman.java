package com.salvestrom.w2theJungle.mobs.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanBase;
import com.salvestrom.w2theJungle.mobs.models.ModelLizardman;
import com.salvestrom.w2theJungle.mobs.render.layers.LayerSaurohnHeldItem;

public class RenderLizardman extends RenderLiving<EntityLizardmanBase>
{
	public static final ResourceLocation Lizardman = new ResourceLocation("thejungle:textures/mobs/lizardmanscuffler.png");
	public ModelLizardman model;
	
	public RenderLizardman(RenderManager rm, ModelLizardman par1ModelBase, float par2)
	{
		this(rm, par1ModelBase, par2, 1.0F);
		this.addLayer(new LayerSaurohnHeldItem(this));
	}
	
    public RenderLizardman(RenderManager renderManagerIn, ModelLizardman modelBipedIn, float shadowSize, float p_i46169_4_)
    {
        super(renderManagerIn, modelBipedIn, shadowSize);
        this.model = modelBipedIn; //(ModelLizardman) super.mainModel;
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityLizardmanBase ntt) {
		return Lizardman;
	}
	
	//@Override
    public ModelBase getMainModel()
    {
        return (ModelLizardman)this.model;
    }
    

	protected void preRenderCallback(EntityLizardmanBase nttLizSt, float par2) {
		
		float reduc = 1F;
		GL11.glScalef(reduc, reduc, reduc);
		}
    
	public void transformHeldFull3DItemLayer()
    {
        GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
    }

	/*
    
   //onItemRightClick (cool stuff) 
    protected void renderEquippedItems(EntityLizardmanScuffler par1EntityLiving, float par2)
    {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        super.renderEquippedItems((EntityLiving)par1EntityLiving, par2);
        ItemStack itemstack = par1EntityLiving.getHeldItemMainhand();
        ItemStack itemstack1 = par1EntityLiving.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        Item item;
        float f1;


        if (itemstack1 != null)
        {
            GL11.glPushMatrix();
            this.model.head.postRender(0.0625F);
            item = itemstack1.getItem();

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));

            if (item instanceof ItemBlock)
            {
                if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType()))
                {
                    f1 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f1, -f1, -f1);
                }

                this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack1, 0);
            }
            else if (item == Items.skull)
            {
                f1 = 1.0625F;
                GL11.glScalef(f1, -f1, -f1);
                GameProfile gameprofile = null;

                if (itemstack1.hasTagCompound())
                {
                    NBTTagCompound nbttagcompound = itemstack1.getTagCompound();

                    if (nbttagcompound.hasKey("SkullOwner", 10))
                    {
                        gameprofile = NBTUtil.func_152459_a(nbttagcompound.getCompoundTag("SkullOwner"));
                    }
                    else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(nbttagcompound.getString("SkullOwner")))
                    {
                        gameprofile = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
                    }
                }

                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack1.getItemDamage(), gameprofile);
            }

        GL11.glPopMatrix();
    }

        if (itemstack != null && itemstack.getItem() != null)
        {
            item = itemstack.getItem();
            GL11.glPushMatrix();

            if (this.model.isChild)
            {
                f1 = 0.5F;
                GL11.glTranslatef(0.0F, 0.625F, 1.0F); //0 0.625 0.
                GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F); //-20 -1 0 0
                GL11.glScalef(f1, f1, f1);
            }
            //daisy chaining so the held item inherits the movement of the child even when that movement is due to movement of parent piece
            this.model.lowerbody.postRender(0.0625F);
        //    GL11.glTranslatef(-0.1625F, 0.4375F, 0.0625F);
            this.model.body.postRender(0.0625F);
        //    GL11.glTranslatef(-0.1625F, 0.4375F, 0.0625F);
            this.model.upperRarm.postRender(0.0625F);
        //    GL11.glTranslatef(-0.1625F, 0.4375F, 0.0625F);
            this.model.lowerRarm.postRender(0.0625F);
        //    GL11.glTranslatef(-0.1625F, 0.4375F, 0.0625F);
            this.model.Righthand.postRender(0.0625F);
        //    GL11.glTranslatef(-0.1625F, 0.4375F, 0.0625F);
            //-0.0625 0.4375 0.0625. 1.4 shoved it into the ground - these are block values offsetting from the middle of the lowerbody.
            

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

            if (item instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType())))
            {
                f1 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f1 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f1, -f1, f1);
            }
            
            else if (item == Items.BOW || item == JungleItems.boneGripBow)
            {	//placing on back
                f1 = 0.625F; // 0.625
                GL11.glTranslatef(0.04F, 0.07F, 0.38125F); //0.0 0.125 0.3125 -- -0.5 -0.384125 -0.774
                GL11.glRotatef(5F, 0.0F, 1F, 0.0F); //-20 0 1 0 -- 65
                GL11.glScalef(f1, -f1, f1);
                GL11.glRotatef(-110.0F, 1F, 0.0F, 0.0F); //-100 1 0 0 -- 15
                GL11.glRotatef(70F, 0.0F, 1F, 0F); //45 0 1 0 -- 35
            }
            
            else if (item.isFull3D())
            {
                f1 = 0.625F;

                if (item.shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                this.transformHeldFull3DItemLayer();
                GL11.glScalef(f1, -f1, f1);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f1 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f1, f1, f1);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f2;
            float f3;
            int i;

            if (itemstack.getItem().requiresMultipleRenderPasses())
            {
                for (i = 0; i < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++i)
                {
                    int j = itemstack.getItem().getColorFromItemStack(itemstack, i);
                    f2 = (float)(j >> 16 & 255) / 255.0F;
                    f3 = (float)(j >> 8 & 255) / 255.0F;
                    float f4 = (float)(j & 255) / 255.0F;
                    GL11.glColor4f(f2, f3, f4, 1.0F);
                    this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack, i);
                }
            }
            else
            {
                i = itemstack.getItem().getColorFromItemStack(itemstack, 0);
                float f5 = (float)(i >> 16 & 255) / 255.0F;
                f2 = (float)(i >> 8 & 255) / 255.0F;
                f3 = (float)(i & 255) / 255.0F;
                GL11.glColor4f(f5, f2, f3, 1.0F);
                this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack, 0);
            }

            GL11.glPopMatrix();
        }
    }
    */
}
