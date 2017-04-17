package com.salvestrom.w2theJungle.init;

import com.salvestrom.w2theJungle.items.bookScale;
import com.salvestrom.w2theJungle.lib.References;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JungleItemRenders
{
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers()
	{
		registerRender(JungleItems.bookScales);
	
		registerRenderWithMeta("east_scale", bookScale.eastScale.getMetadata(), JungleItems.bookScales);
		registerRenderWithMeta("west_scale", bookScale.westScale.getMetadata(), JungleItems.bookScales);
		registerRenderWithMeta("north_scale", bookScale.northScale.getMetadata(), JungleItems.bookScales);
		registerRenderWithMeta("south_scale", bookScale.southScale.getMetadata(), bookScale.southScale.getItem());
		
		registerRender(JungleItems.gemSapphire);
		
		registerRender(JungleItems.unadornedObChest);
		registerRender(JungleItems.unadornedObHelm);
		registerRender(JungleItems.unadornedObLegs);
		registerRender(JungleItems.unadornedObBoots);
		
		registerRender(JungleItems.eyeT);
		registerRender(JungleItems.rawCrabMeat);
		registerRender(JungleItems.obschest);
		registerRender(JungleItems.obshelmet);
		registerRender(JungleItems.obslegs);
		registerRender(JungleItems.obsboots);
		registerRender(JungleItems.stewC);
		registerRender(JungleItems.stoneDoorItem);

		registerRender(JungleItems.boneGrip);
		registerRender(JungleItems.boneGripBow);
		
		registerRender(JungleItems.clobberer);
		registerRender(JungleItems.infusedObsidian);
		
		registerRender(JungleItems.decorativeSeal);
		
		registerRender(JungleItems.carvedBone);
		registerRender(JungleItems.carvedBonehandle);
		registerRender(JungleItems.bonehandlesword);
		registerRender(JungleItems.mudBucket);
		
		registerRender(JungleItems.thrownWeb);

		}
	

	@SideOnly(Side.CLIENT)
	public static void registerRender(Item item)
	{
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemRender(Item item)
	{
		//Minecraft mc = Minecraft.getMinecraft();
		
		//RenderManager rm = mc.getRenderManager();
		
		//ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ItemRenderClobberer(rm, new ModelClobberer(), 0.5f), "inventory"));

	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderWithMeta(String string, int i, Item item)
	{
        ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(References.MODID + ":" + string, "inventory"));
	}


	
}
