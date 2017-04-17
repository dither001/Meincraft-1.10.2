package com.salvestrom.w2theJungle.init;

import com.salvestrom.w2theJungle.blocks.ClimbingWeb;
import com.salvestrom.w2theJungle.blocks.JungleLadder;
import com.salvestrom.w2theJungle.blocks.MossyStone;
import com.salvestrom.w2theJungle.blocks.MudBlock;
import com.salvestrom.w2theJungle.blocks.MudFluid;
import com.salvestrom.w2theJungle.blocks.MudLiquid;
import com.salvestrom.w2theJungle.blocks.altarAbstract;
import com.salvestrom.w2theJungle.blocks.ancientSkull;
import com.salvestrom.w2theJungle.blocks.fullScale;
import com.salvestrom.w2theJungle.blocks.infusedObsidianBlock;
import com.salvestrom.w2theJungle.blocks.jungleBraziers;
import com.salvestrom.w2theJungle.blocks.lostWorldPortal;
import com.salvestrom.w2theJungle.blocks.mossyAncientBlock;
import com.salvestrom.w2theJungle.blocks.mossyCarved;
import com.salvestrom.w2theJungle.blocks.mossySlab;
import com.salvestrom.w2theJungle.blocks.mossyStairs;
import com.salvestrom.w2theJungle.blocks.mossyStoneSteps;
import com.salvestrom.w2theJungle.blocks.oreSapphire;
import com.salvestrom.w2theJungle.blocks.stoneDoor;
import com.salvestrom.w2theJungle.blocks.tyrantSkull;
import com.salvestrom.w2theJungle.blocks.wallSkull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class JungleBlocks
{
	//Blocks//
	public static BlockDoor stoneDoor;
	
	public static BlockStairs mossystairs;
	public static BlockStairs mossyStoneSteps;
	
	public static Block ancientMossyBlock;
	
	public static Block mossyCarved;
	
	//slab file
	public static BlockSlab mossyslabSingle;
	public static BlockSlab mossyslabDouble;
	
	//public static BlockSlab mossyStoneSlabSingle;
	//public static BlockSlab mossyStoneSlabDouble;

	public static Block oreSapphire;
	
	//lost world portal frame material.
	public static Block infusedObsidianBlock;
	//the portal block
	public static BlockPortal lostWorldPortal;
	
	public static Block jungleBrazier;
	public static Block jungleBrazierLit;
	public static Block ancientSkull;
	public static Block tyrantSkull;
	public static Block wallSkull;
	public static Block fullScale;
	public static Block altarAbstract;
	public static BlockLadder jungleLadder;
	public static BlockLadder climbingWeb;

	public static Block mossyStone;
	
	//mud
	public static Fluid mudFluid;
	public static Material mudMaterial;
	public static Block mudBlock;// = new Block(Material.SAND);
		
	public static void init()
	{
		/*
		*/
		altarAbstract = new altarAbstract(Material.CIRCUITS);
		ancientMossyBlock = new mossyAncientBlock(Material.ROCK); //very hard mossy cobblestone variant.
		ancientSkull = new ancientSkull(Material.CIRCUITS);

		climbingWeb = new ClimbingWeb();

		fullScale = new fullScale(Material.WOOD);
		
		infusedObsidianBlock = new infusedObsidianBlock(3685, Material.ROCK);

		jungleBrazier = new jungleBraziers.Unlit(Material.ROCK).setUnlocalizedName("jungleBrazier"); 
		jungleBrazierLit = new jungleBraziers.Lit(Material.ROCK).setUnlocalizedName("jungleBrazier");
		jungleLadder = new JungleLadder();

		lostWorldPortal	= new lostWorldPortal(3687);

		mossyCarved = new mossyCarved(Material.ROCK);
		mossyslabSingle = (BlockSlab) (new mossySlab.Half()).setUnlocalizedName("stoneMossySlab");//.setRegistryName("stoneMossySlab");
		mossyslabDouble = (BlockSlab) (new mossySlab.Double()).setUnlocalizedName("stoneMossySlab");//.setRegistryName("stoneMossySlab_double");
		mossystairs = new mossyStairs(3672, Blocks.MOSSY_COBBLESTONE);
		mossyStone = new MossyStone(Material.ROCK); //literal mossy stone.
		mossyStoneSteps = new mossyStoneSteps(3686, Blocks.STONEBRICK, Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY)); 
		
		oreSapphire = new oreSapphire(3674, Material.ROCK);
		
		stoneDoor = new stoneDoor(Material.ROCK);
		
		tyrantSkull = new tyrantSkull(Material.CIRCUITS);
		wallSkull = new wallSkull(Material.CIRCUITS);
		
		//mud
		mudFluid = new MudFluid("mudfluid");
		FluidRegistry.registerFluid(mudFluid);
		mudMaterial = new MudLiquid(MapColor.DIRT);
		mudBlock = new MudBlock(mudFluid, Material.WATER);
		
		registerBlocks();
    }
	
	public static void registerBlocks()
	{	
		registerBlock(altarAbstract, "altarAbstract");
		registerBlock(ancientMossyBlock, "ancientMossyBlock");
		registerBlock(ancientSkull, "ancientSkull");
		
		registerBlock(climbingWeb, "climbingWeb");
		registerBlock(fullScale, "fullScale");

		registerBlock(infusedObsidianBlock, "infusedObsidianBlock");
		
		registerBlock(jungleBrazier, "jungleBrazierUnlit");
		registerBlockWithoutItem(jungleBrazierLit, "jungleBrazierLit");
		registerBlock(jungleLadder, "jungleLadder");

		registerBlock(lostWorldPortal, "lostWorldPortal");
		
		registerBlock(mossyCarved, "mossyCarved");
		Item item2 = (ItemSlab) (new ItemSlab(JungleBlocks.mossyslabSingle, JungleBlocks.mossyslabSingle, JungleBlocks.mossyslabDouble)).setUnlocalizedName("mossySlab");
		registerBlockWithCustomItem(mossyslabSingle, item2, "mossy_slab"); //==blocks.stone_slab;
		registerBlockWithCustomItem(mossyslabDouble, null, "mossy_double_slab"); // = blocks.double stone slab

		registerBlock(mossystairs, "mossyStairs");
		registerBlock(mossyStone, "mossyStone");
		registerBlock(mossyStoneSteps, "mossyStoneSteps");

		registerBlock(oreSapphire, "oreSapphire");
		
		registerDoorBlock(stoneDoor, "stoneDoor");
		
		registerBlock(tyrantSkull, "tyrantSkull");
		registerBlock(wallSkull, "wallSkull");
		
		FluidRegistry.addBucketForFluid(mudFluid);

		//FluidContainerRegistry.registerFluidContainer(mudFluid, new ItemStack(JungleItems.mudBucket), new ItemStack(Items.BUCKET));
		
		//registerBlockWithoutItem(mudBlock, "mudBlock");
		registerBlock(mudBlock, "mudBlock");

				
		//ModelLoader.setCustomStateMapper(tyrantSkull, (new StateMap.Builder()).ignore(tyrantSkull.getDefaultState().getProperties().clear()).build());

		}	
	
	public static void registerBlock(Block block, String string)
	{
		block.setRegistryName(string);
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), block.getRegistryName());

	}

	public static void registerBlockWithoutItem(Block block, String string)
	{
		block.setRegistryName(string);
		GameRegistry.register(block);
	}
	
	public static void registerBlockWithCustomItem(Block block, Item item, String string)
	{
		block.setRegistryName(string);
		GameRegistry.register(block);
		if(item != null)
		{
			GameRegistry.register(item, block.getRegistryName());
		}
        
	}
	//to skip itemblock creation, since doors have a specific item. and need a custom state map.
	public static void registerDoorBlock(Block block, String string)
	{
		block.setRegistryName(string);
		GameRegistry.register(block);
	}

}
