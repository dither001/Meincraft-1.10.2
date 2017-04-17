package com.salvestrom.w2theJungle.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.items.bookScale;

public class JungleCrafting {
	
	public JungleCrafting() {}
	
	public static void loadRecipes()
	{		
		GameRegistry.addShapedRecipe(new ItemStack(JungleBlocks.infusedObsidianBlock, 1),
				new Object []
						{
								" i ", " s ", " o ",
								's', JungleItems.gemSapphire,
								'o', Blocks.OBSIDIAN,
								'i', Blocks.ICE
						});
		
		GameRegistry.addSmelting(JungleBlocks.infusedObsidianBlock, new ItemStack(JungleItems.infusedObsidian, 1, 0), 1.5f);
		
		GameRegistry.addRecipe(new ItemStack(JungleItems.unadornedObChest, 1), new Object []
				{"i i", "iii", "iii", 'i', JungleItems.infusedObsidian});
		GameRegistry.addRecipe(new ItemStack(JungleItems.unadornedObHelm, 1), new Object []
				{"iii", "i i", 'i', JungleItems.infusedObsidian});
		GameRegistry.addRecipe(new ItemStack(JungleItems.unadornedObLegs, 1), new Object []
				{"iii", "i i", "i i", 'i', JungleItems.infusedObsidian});
		GameRegistry.addRecipe(new ItemStack(JungleItems.unadornedObBoots, 1), new Object []
				{"i i", "i i", 'i', JungleItems.infusedObsidian});
		
		GameRegistry.addRecipe(new ItemStack(JungleItems.obshelmet, 1), new Object []
				{"rrr", "csc", " a ",
			'r', new ItemStack(Blocks.WOOL, 1, 14),
			's', JungleItems.gemSapphire,
			'c', JungleItems.carvedBone,
			'a', JungleItems.unadornedObHelm});

		GameRegistry.addRecipe(new ItemStack(JungleItems.obschest, 1), new Object []
				{"blk", "cac", "csc",
			'k', new ItemStack(JungleBlocks.ancientSkull, 1, 0),
			'b', bookScale.northScale,//new ItemStack(bookScale.northScale.getItem(), 1 , 369),
			'l', JungleItems.decorativeSeal,
			's', JungleItems.gemSapphire,
			'c', JungleItems.carvedBone,
			'a', JungleItems.unadornedObChest});

		GameRegistry.addRecipe(new ItemStack(JungleItems.obslegs, 1), new Object []
				{"ccc", "whw", "w w",
			'c', JungleItems.carvedBone,
			'w', new ItemStack(Blocks.WOOL, 1, 0),
			'h', JungleItems.unadornedObLegs});
		
		GameRegistry.addRecipe(new ItemStack(JungleItems.obsboots, 1), new Object []
				{"klk", " h ", "   ",
				'k', new ItemStack(JungleBlocks.ancientSkull, 1, 0),
				'l', JungleItems.decorativeSeal,
				'h', JungleItems.unadornedObBoots});
			
		GameRegistry.addRecipe(new ItemStack(JungleBlocks.jungleBrazier, 1), new Object []
				{"ini", "sms", "sms",
				'i', Items.IRON_INGOT,
				's', new ItemStack(Blocks.STONEBRICK, 1, 1),
				'm', Blocks.MOSSY_COBBLESTONE,
				'n', Blocks.NETHERRACK});
		
		GameRegistry.addRecipe(new ItemStack(JungleBlocks.jungleLadder, 3), new Object [] {
			"sps", "sps", "sps",
			's', Items.STRING,
			'p', Items.STICK
			});

		GameRegistry.addShapelessRecipe(new ItemStack(JungleItems.stewC, 1), new Object [] {
				JungleItems.rawCrabMeat, Items.CARROT, Items.POTATO, Items.BOWL
				});
		
		GameRegistry.addShapelessRecipe(new ItemStack(JungleBlocks.fullScale, 1), new Object [] {
			new ItemStack(bookScale.eastScale.getItem(), 1, 367),
			new ItemStack(bookScale.westScale.getItem(), 1, 368),
			new ItemStack(bookScale.northScale.getItem(), 1, 369),
			new ItemStack(bookScale.southScale.getItem(), 1, 370)
			});
		
		GameRegistry.addRecipe(new ItemStack(JungleItems.boneGrip, 1), new Object []
				{
			"cs "," k ","cs ", 'c', JungleItems.carvedBone, 's', Items.STRING, 'k', Items.STICK
			});
		
		GameRegistry.addRecipe(new ItemStack(JungleItems.boneGripBow, 1), new Object []
				{
			" ks", "b t", " ks", 's', Items.STRING, 'k', Items.STICK, 'b', JungleItems.boneGrip
			});
			
		GameRegistry.addShapelessRecipe(new ItemStack(JungleItems.decorativeSeal, 1), new Object []
					{Items.PAPER, Items.CLAY_BALL, new ItemStack(Items.DYE, 1, 1)});

		GameRegistry.addRecipe(new ItemStack(JungleItems.carvedBonehandle, 1),
				new Object []
						{"   ", "ccc", "scs", 'c', JungleItems.carvedBone, 's', Items.STRING});
			
		GameRegistry.addRecipe(new ItemStack(JungleItems.bonehandlesword, 1),
				new Object []
					{
						" s ", " s ", " c ",
						's', Blocks.COBBLESTONE,
						'c', JungleItems.carvedBonehandle
					});

		GameRegistry.addRecipe(new ItemStack(JungleItems.stoneDoorItem, 3),
				new Object []
					{
						"aa", "aa", "aa",
						'a', JungleBlocks.ancientMossyBlock
					});
	}
	
}
