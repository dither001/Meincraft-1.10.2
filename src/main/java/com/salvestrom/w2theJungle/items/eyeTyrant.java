package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleSounds;
import com.salvestrom.w2theJungle.lib.References;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class eyeTyrant extends ItemRecord {
	
	public eyeTyrant() {
		super("eyeT", JungleSounds.RECORD_SURVIVORS);
		
		this.setCreativeTab(w2theJungle.JungleModTab);
		//this.setTextureName("theJungle:eyeT");
		this.setUnlocalizedName("eyeT");
		}
	
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer playa, List<String> list, boolean par4)	{
		list.add("Most of the 'Eye' has disintergrated");
		list.add("leaving behind a square plate");
		list.add("decorated with a single, spiralling,");
		list.add("heavily notched groove..."); 
	}
	
    public ResourceLocation getRecordResource(String name)
    {
        return new ResourceLocation(References.MODID + ":records." + name);
    }

}
