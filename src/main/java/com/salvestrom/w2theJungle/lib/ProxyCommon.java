package com.salvestrom.w2theJungle.lib;

import com.salvestrom.w2theJungle.crafting.JungleCrafting;
import com.salvestrom.w2theJungle.init.JungleAchievements;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.init.JungleSounds;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyCommon
{
	public void registerRenderInformation(){}

	public ModelBiped getArmorModel(int id){return null;}

	public void preInit(FMLPreInitializationEvent event)
	{		
		JungleSounds.init();
		JungleBlocks.init();
		JungleItems.init();
		
		JungleAchievements.init();
		
		JungleCrafting.loadRecipes();
	}

	public void preInitRenders() {}

	
}
