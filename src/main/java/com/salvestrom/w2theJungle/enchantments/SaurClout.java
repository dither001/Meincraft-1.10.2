package com.salvestrom.w2theJungle.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class SaurClout extends Enchantment {
	
	public EntityLivingBase entitylb;

	public SaurClout(int id, Enchantment.Rarity rarity) {
		super(rarity, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		//super(id, rarity, EnumEnchantmentType.WEAPON);
		this.setName("saurclout");
	}
	
    public float calcDamageByCreature(int p_152376_1_, EnumCreatureAttribute p_152376_2_)
    {
    	//TODO prolly needs toning down from 3.5 to reflect 1.8 enchant nerfs
        return p_152376_2_ == EnumCreatureAttribute.valueOf("reptilian") ? (float)p_152376_1_ * 3.5F : p_152376_1_;
        //(still hurts nonlizards., but i think less so...?)
    }
	
	public int getMinEnchantability(int i) {
		return 0;
	}
	
	public int getMaxEnchantability(int i) {
		return 0;
	}

	public int getMaxLevel() {
		return 3;
	}
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return false;
    }
    
    public boolean isAllowedOnBooks()
    {
        return false;
    }
}
