package com.salvestrom.w2theJungle.lib;

import com.salvestrom.w2theJungle.init.JungleBlockRenders;
import com.salvestrom.w2theJungle.init.JungleItemRenders;
import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.items.Render.RenderAltarAbstract;
import com.salvestrom.w2theJungle.items.Render.RenderFullScale;
import com.salvestrom.w2theJungle.items.Render.RenderTyrantSkull;
import com.salvestrom.w2theJungle.items.Render.RenderWallSkull;
import com.salvestrom.w2theJungle.mobs.entity.EntityGorrbat;
import com.salvestrom.w2theJungle.mobs.entity.EntityLenny;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanScuffler;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanStalker;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanWitchDoctor;
import com.salvestrom.w2theJungle.mobs.entity.EntityMistSpider;
import com.salvestrom.w2theJungle.mobs.entity.EntitySacrificialSkeleton;
import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;
import com.salvestrom.w2theJungle.mobs.entity.EntitySwampCrab;
import com.salvestrom.w2theJungle.mobs.entity.EntityTheWall;
import com.salvestrom.w2theJungle.mobs.entity.EntityWeb;
import com.salvestrom.w2theJungle.mobs.models.ModelGorrbat;
import com.salvestrom.w2theJungle.mobs.models.ModelLizardman;
import com.salvestrom.w2theJungle.mobs.models.ModelLizardmanStalker;
import com.salvestrom.w2theJungle.mobs.models.ModelLizardmanWitchDoctor;
import com.salvestrom.w2theJungle.mobs.models.ModelMistSpider;
import com.salvestrom.w2theJungle.mobs.models.ModelObsidianArmour;
import com.salvestrom.w2theJungle.mobs.models.ModelStoneGolem;
import com.salvestrom.w2theJungle.mobs.models.ModelSwampCrab;
import com.salvestrom.w2theJungle.mobs.models.ModelTheWall;
import com.salvestrom.w2theJungle.mobs.models.ModelZTR;
import com.salvestrom.w2theJungle.mobs.render.RenderGorrbat;
import com.salvestrom.w2theJungle.mobs.render.RenderLenny;
import com.salvestrom.w2theJungle.mobs.render.RenderLizardman;
import com.salvestrom.w2theJungle.mobs.render.RenderLizardmanStalker;
import com.salvestrom.w2theJungle.mobs.render.RenderLizardmanWitchDoctor;
import com.salvestrom.w2theJungle.mobs.render.RenderMistSpider;
import com.salvestrom.w2theJungle.mobs.render.RenderSacrificialSkeleton;
import com.salvestrom.w2theJungle.mobs.render.RenderStoneGolem;
import com.salvestrom.w2theJungle.mobs.render.RenderSwampCrab;
import com.salvestrom.w2theJungle.mobs.render.RenderTheWall;
import com.salvestrom.w2theJungle.tileentity.TileEntityAltarAbstract;
import com.salvestrom.w2theJungle.tileentity.TileEntityFullScale;
import com.salvestrom.w2theJungle.tileentity.TileEntityTyrantSkull;
import com.salvestrom.w2theJungle.tileentity.TileEntityWallSkull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ProxyClient extends ProxyCommon
{
	@Override
	public void preInitRenders()
	{		
		JungleBlockRenders.registerRenderers();
		JungleItemRenders.registerRenderers();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void registerRenderInformation()
	{
		Minecraft mc = Minecraft.getMinecraft();
		
		RenderManager rm = mc.getRenderManager();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityWeb.class, new RenderSnowball(rm, JungleItems.thrownWeb, mc.getRenderItem()));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityStoneGolem.class, new RenderStoneGolem(rm, new ModelStoneGolem(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTheWall.class, new RenderTheWall(rm, new ModelTheWall(), 1.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLenny.class, new RenderLenny(rm, new ModelZTR(), 1.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityLizardmanScuffler.class, new RenderLizardman(rm, new ModelLizardman(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLizardmanStalker.class, new RenderLizardmanStalker(rm, new ModelLizardmanStalker(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLizardmanWitchDoctor.class, new RenderLizardmanWitchDoctor(rm, new ModelLizardmanWitchDoctor(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySwampCrab.class, new RenderSwampCrab(rm, new ModelSwampCrab(), 1.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntitySacrificialSkeleton.class, new RenderSacrificialSkeleton(rm, new ModelSkeleton(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMistSpider.class, new RenderMistSpider(rm, new ModelMistSpider(), 1.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGorrbat.class, new RenderGorrbat(rm, new ModelGorrbat(), 1F));

		//ItemModelMesher mesh = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		//.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));

		//		GameRegistry.registerTileEntity(TileEntityCrafter.class, "Crafter");

		//TileEntitySpecialRenderer renderJB = new RenderJungleBrazier();
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJungleBrazier.class, renderJB);
		//mesh.register(Item.getItemFromBlock(JungleBlocks.jungleBrazier), 0,
		//		new ModelResourceLocation("jungleBrazier", "inventory"));
		//the itemrenderers are gone, replaced with jsons.
		//				new ItemRenderJungleBrazier(renderJB, new TileEntityJungleBrazier()));
		/*
		mesh.register(Item.getItemFromBlock(JungleBlocks.ancientSkull), 0,
				new ModelResourceLocation("ancientSkull", "inventory"));
//				new ItemRenderAncientSkull(renderAS, new TileEntityAncientSkull()));
		

		//mesh.register(Item.getItemFromBlock(JungleBlocks.tyrantSkull), 0,
		//		new ModelResourceLocation(JungleBlocks.tyrantSkull.getRegistryName(), "inventory"));

		
		//ModelLoader.setCustomStateMapper(JungleBlocks.tyrantSkull, (new StateMap.Builder()).withName(mossySlab.VARIANT).withSuffix("_slab").build());
		//GameRegistry.registerTileEntity(TileEntityTyrantSkull.class, "tyrantSkull");//JungleBlocks.tyrantSkull.getRegistryName());
		//TileEntitySpecialRenderer<TileEntityTyrantSkull> renderTS = new RenderTyrantSkull();
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTyrantSkull.class, renderTS);
		
		TileEntitySpecialRenderer renderWS = new RenderWallSkull();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWallSkull.class, renderWS);
		mesh.register(Item.getItemFromBlock(JungleBlocks.wallSkull), 0,
				new ModelResourceLocation("wallSkull", "inventory"));
		
		TileEntitySpecialRenderer renderFS = new RenderFullScale();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFullScale.class, renderFS);
		mesh.register(Item.getItemFromBlock(JungleBlocks.fullScale), 0,
				new ModelResourceLocation("fullScale", "inventory"));
		
		TileEntitySpecialRenderer renderAA = new RenderAltarAbstract();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltarAbstract.class, renderAA);
		mesh.register(Item.getItemFromBlock(JungleBlocks.altarAbstract), 0,
				new ModelResourceLocation("altarAbstract", "inventory"));
		*/
		//mesh.register(JungleItems.boneGripBow, 0, new RenderBoneBow(rm));
		//mesh.register(JungleItems.clobberer, 0, new ItemRenderClobberer(rm, new ModelClobberer(), 0.5F));

		//mesh.register(Item.getItemFromBlock(JungleBlocks.tyrantSkull), 0,
		//		new ModelResourceLocation(JungleBlocks.tyrantSkull.getRegistryName(), "inventory"));

		TileEntitySpecialRenderer renderAA = new RenderAltarAbstract();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltarAbstract.class, renderAA);
		
		TileEntitySpecialRenderer renderFS = new RenderFullScale();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFullScale.class, renderFS);
		
		//ModelLoader.setCustomStateMapper(JungleBlocks.tyrantSkull, (new StateMap.Builder()).withName(mossySlab.VARIANT).withSuffix("_slab").build());
		GameRegistry.registerTileEntity(TileEntityTyrantSkull.class, "tyrantSkull");//JungleBlocks.tyrantSkull.getRegistryName());
		TileEntitySpecialRenderer<TileEntityTyrantSkull> renderTS = new RenderTyrantSkull();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTyrantSkull.class, renderTS);
		
		GameRegistry.registerTileEntity(TileEntityWallSkull.class, "wallSkull");
		TileEntitySpecialRenderer renderWS = new RenderWallSkull();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWallSkull.class, renderWS);
		
		//TileEntitySpecialRenderer renderAS = new RenderAncientSkull();
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAncientSkull.class, renderAS);
		
		
		
	}
	
	
	public void registerTileEntitySpecialRenderer() {}

	private static final ModelObsidianArmour obschest = new ModelObsidianArmour(1.0F);
	private static final ModelObsidianArmour obslegs = new ModelObsidianArmour(0.5F);
	
	@Override
	public ModelBiped getArmorModel(int id)
	{
		switch (id) {
		case 0:
			return obschest;
		case 1:
			return obslegs;
			default:
			break;
		}
		
		return null;}
	
}
