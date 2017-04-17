package com.salvestrom.w2theJungle.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;

public class ceremonialObsidian extends ItemArmor {

	public ceremonialObsidian(ArmorMaterial parAM, int id, EntityEquipmentSlot slot)
	{
		super(parAM, id, slot);
	
		if (slot == EntityEquipmentSlot.CHEST)
		{
			this.setUnlocalizedName("cerechest");
			 //this.setTextureName("thejungle:ceremonialObChest");
			 this.setCreativeTab(w2theJungle.JungleModTab);}
		if (slot == EntityEquipmentSlot.HEAD)
		{
			this.setUnlocalizedName("cerehelmet");
//			 this.setTextureName("thejungle:ceremonialObHelm");
			 this.setCreativeTab(w2theJungle.JungleModTab);}		
		if (slot == EntityEquipmentSlot.FEET)
		{
			this.setUnlocalizedName("cereboots");
//			 this.setTextureName("thejungle:ceremonialObBoots");
			 this.setCreativeTab(w2theJungle.JungleModTab);}
		if (slot == EntityEquipmentSlot.LEGS)
		{
			this.setUnlocalizedName("cerelegs");
			// this.setTextureName("thejungle:ceremonialObLegs");
			this.setCreativeTab(w2theJungle.JungleModTab);
		}	
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot,	String type)
	{
		switch(slot)
		{
		case LEGS: return "thejungle:textures/armor/obsidianceremoniallegs.png";
		default: return "thejungle:textures/armor/obsidianceremonial.png";
		}
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase nttLiv, ItemStack itemStack, EntityEquipmentSlot slot, ModelBiped model)
	{		
		//armour type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots. 

		if(itemStack != null)
		{
			if (itemStack.getItem() instanceof ceremonialObsidian)
			{	
				//EntityEquipmentSlot type = ((ItemArmor)itemStack.getItem()).armorType;
				
				if (slot == EntityEquipmentSlot.CHEST || slot == EntityEquipmentSlot.FEET)
				{model = w2theJungle.proxy.getArmorModel(0);}
				else
				{model = w2theJungle.proxy.getArmorModel(1);}
			}
			
			if (model != null)
			{
				model.bipedHead.showModel = slot == EntityEquipmentSlot.HEAD;
				model.bipedHeadwear.showModel = slot == EntityEquipmentSlot.HEAD;
				model.bipedBody.showModel = slot == EntityEquipmentSlot.CHEST || slot == EntityEquipmentSlot.LEGS;
				model.bipedRightArm.showModel = slot == EntityEquipmentSlot.CHEST;
				model.bipedLeftArm.showModel = slot == EntityEquipmentSlot.CHEST;
				model.bipedRightLeg.showModel = slot == EntityEquipmentSlot.LEGS || slot == EntityEquipmentSlot.FEET;
				model.bipedLeftLeg.showModel = slot == EntityEquipmentSlot.LEGS || slot == EntityEquipmentSlot.FEET;
		
				model.isSneak = nttLiv.isSneaking();
				model.isRiding = nttLiv.isRiding();
				model.isChild = nttLiv.isChild();
				
				return model;
				}
			}
		
		return null;
		}
	
	
//	   public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player){return false;}
		
		}

	


