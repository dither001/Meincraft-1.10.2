package com.salvestrom.w2theJungle.food;

import javax.annotation.Nullable;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleAchievements;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.world.World;

public class stewJungle extends ItemFood {

	public stewJungle(int i, boolean b) {
		super(i, b);
		
		this.setCreativeTab(w2theJungle.JungleModTab);
		//this.setTextureName("theJungle:stewJungle");
		this.setUnlocalizedName("stewJungle");
		this.setMaxStackSize(1);
		this.setPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 1), 1F);
		
	}
	
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }
	
    public void onCreated(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer playa) {
    	
    	if(playa instanceof EntityPlayerMP) {
    	EntityPlayerMP player = (EntityPlayerMP) playa;
    	
    	if(!player.hasAchievement(JungleAchievements.cookStew))
    	{
    		playa.addStat(JungleAchievements.cookStew);
    	}
    }}


}
