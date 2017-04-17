package com.salvestrom.w2theJungle.items;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleItems;

public class boneGripBow extends ItemBow
{
	
    public boneGripBow()
    {
    	super();
        this.maxStackSize = 1;
        this.setMaxDamage(484);
    	//this.setTextureName("thejungle:boneGripBow");
    	this.setUnlocalizedName("boneGripBow");    
        this.setCreativeTab(w2theJungle.JungleModTab);
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    ItemStack itemstack = entityIn.getActiveItemStack();
                    return itemstack != null && itemstack.getItem() == JungleItems.boneGripBow ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 18.0F : 0.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });    }
    
    
    /*	@Override
	public void addInformation(ItemStack IStak, EntityPlayer nttplaya, List list, boolean par4)
	{list.add("");
	 list.add("");
	 list.add("");
	 list.add("");
	}
*/
    /*
    //@SideOnly(Side.CLIENT)
    private IIcon[] textureName = new IIcon[4];

    public void registerIcons(IIconRegister iconRegister)
    {
    	itemIcon = iconRegister.registerIcon("thejungle:boneGripBow");
        for (int t = 0; t < 3; t++)
        {this.textureName[t] = (IIcon) iconRegister.registerIcon("thejungle:boneGripBow_pull_" + t);}
    }
    //trying to speed up firing
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
    	if(player.getItemInUse() == null) return this.itemIcon;
    	
        int Pulling = stack.getMaxItemUseDuration() - useRemaining;
        
        if (Pulling >= 15) //18
        	{return textureName[2];}
        
        else if (Pulling > 10) //13
        	{return textureName[1];}
        
        else if (Pulling > 0)
        	{return textureName[0];}
        
        return itemIcon;
    }
    /*
    public IIcon getIcon2(ItemStack stack, int renderPass, EntityLivingBase player, int status, int useRemaining)
    {
    	if(status == 1) return this.itemIcon;
    	
        int Pulling = stack.getMaxItemUseDuration() - useRemaining;
        
        if (Pulling >= 15) //18
        	{return textureName[2];}
        
        else if (Pulling > 10) //13
        	{return textureName[1];}
        
        else if (Pulling >= 0)
        	{return textureName[0];}
        
        return this.itemIcon;
    }
    */
    /*
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int p_94599_1_)
    {
        return this.textureName[p_94599_1_];
    }
    */
    
    
    
}
