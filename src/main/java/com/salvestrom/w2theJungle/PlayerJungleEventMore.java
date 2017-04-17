package com.salvestrom.w2theJungle;

import com.salvestrom.w2theJungle.init.JungleAchievements;
import com.salvestrom.w2theJungle.init.JungleBlocks;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class PlayerJungleEventMore {
	
	@SubscribeEvent
	public void craftingJungle(ItemCraftedEvent ice) {
		
		if(ice.crafting.getItem() == Item.getItemFromBlock(JungleBlocks.fullScale))
		{
			ice.player.addStat(JungleAchievements.jungleBook);
			}		
	
	if(ice.crafting.getItem() == Item.getItemFromBlock(JungleBlocks.infusedObsidianBlock)) {

		ice.player.addStat(JungleAchievements.craftInfObs);
		}
	}
	
}
