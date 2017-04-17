package com.salvestrom.w2theJungle.init;

import com.salvestrom.w2theJungle.blocks.mossySlab;
import com.salvestrom.w2theJungle.lib.References;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JungleBlockRenders
{
	@SideOnly(Side.CLIENT)
	public static void registerRenderers()
	{
		registerRender(JungleBlocks.altarAbstract);
		registerRender(JungleBlocks.ancientMossyBlock);
		registerRender(JungleBlocks.ancientSkull);
		
		registerRender(JungleBlocks.climbingWeb);
		registerRender(JungleBlocks.fullScale);
		registerRender(JungleBlocks.infusedObsidianBlock);
		
		registerRender(JungleBlocks.jungleBrazier);
		
		registerRender(JungleBlocks.jungleLadder);

		registerRender(JungleBlocks.lostWorldPortal);
		
		registerRender(JungleBlocks.mossyCarved);
		registerSlabRender("mossy_slab", mossySlab.EnumType.MOSSY_SLAB.getMetadata(), JungleBlocks.mossyslabSingle);
		registerSlabRender("mossy_smooth_slab", mossySlab.EnumType.MOSSY_SMOOTH_SLAB.getMetadata(), JungleBlocks.mossyslabSingle);

		registerRender(JungleBlocks.mossystairs);
		registerRender(JungleBlocks.mossyStone);
		registerRender(JungleBlocks.mossyStoneSteps);
		
		registerFluidRender(JungleBlocks.mudBlock);
		
		registerRender(JungleBlocks.oreSapphire);
		registerRender(JungleBlocks.tyrantSkull);
		registerRender(JungleBlocks.wallSkull);
		
		ModelLoader.setCustomStateMapper(JungleBlocks.stoneDoor, (new StateMap.Builder()).ignore(BlockDoor.POWERED).build());

		ModelLoader.setCustomStateMapper(JungleBlocks.mossyslabSingle, (new StateMap.Builder()).withName(mossySlab.VARIANT).withSuffix("_slab").build());
        ModelLoader.setCustomStateMapper(JungleBlocks.mossyslabDouble, (new StateMap.Builder()).withName(mossySlab.VARIANT).withSuffix("_double_slab").build());
	}


	@SideOnly(Side.CLIENT)
	public static void registerFluidRender(Block block)
	{
		Item fluidBlockItem = Item.getItemFromBlock(block);

        ModelBakery.registerItemVariants(fluidBlockItem);
        
        final ModelResourceLocation resloc = new ModelResourceLocation(block.getRegistryName(), "fluid");

		ModelLoader.setCustomMeshDefinition(fluidBlockItem, new ItemMeshDefinition()
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return resloc;
			}
		});
		
		ModelLoader.setCustomStateMapper(block, new StateMapperBase()
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_)
			{
				return resloc;
			}
		});
		
		//ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockFluidClassic.LEVEL).build());
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender(Block block)
	{
	//	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerDoorRender(Block block, Item item)
	{
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));

	}

	@SideOnly(Side.CLIENT)
	public static void registerSlabRender(String string, int i, Block block)
	{
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(References.MODID + ":" + string, "inventory"));
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), i, new ModelResourceLocation("thejungle:" + string, "inventory"));
	}


}
