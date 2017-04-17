package com.salvestrom.w2theJungle.init;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.food.stewJungle;
import com.salvestrom.w2theJungle.items.InfusedObsidian;
import com.salvestrom.w2theJungle.items.ItemMudBucket;
import com.salvestrom.w2theJungle.items.ThrownWeb;
import com.salvestrom.w2theJungle.items.UnadornedObBoots;
import com.salvestrom.w2theJungle.items.UnadornedObChest;
import com.salvestrom.w2theJungle.items.UnadornedObHelm;
import com.salvestrom.w2theJungle.items.UnadornedObLegs;
import com.salvestrom.w2theJungle.items.boneGrip;
import com.salvestrom.w2theJungle.items.boneGripBow;
import com.salvestrom.w2theJungle.items.bookScale;
import com.salvestrom.w2theJungle.items.carvedBone;
import com.salvestrom.w2theJungle.items.carvedBonehandle;
import com.salvestrom.w2theJungle.items.ceremonialObsidian;
import com.salvestrom.w2theJungle.items.decorativeSeal;
import com.salvestrom.w2theJungle.items.eyeTyrant;
import com.salvestrom.w2theJungle.items.rawCrabMeat;
import com.salvestrom.w2theJungle.items.sapphire;
import com.salvestrom.w2theJungle.items.stoneDoorItem;
import com.salvestrom.w2theJungle.items.swordBonehandle;
import com.salvestrom.w2theJungle.items.theClobberer;

import akka.dispatch.sysmsg.EarliestFirstSystemMessageList;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemRecord;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class JungleItems
{
	public static Item bookScales;
	public static Item mudBucket;
	public static Item carvedBone;
	public static Item carvedBonehandle;
	public static Item decorativeSeal;
	
	public static Item clobberer;
	public static Item gemSapphire;
	public static Item infusedObsidian;
	public static Item rawCrabMeat;

	//Armour//
	//unique ids
	public static int obshelmId;
	public static int obschestId;
	public static int obslegsId;
	public static int obsbootsId;

	public static Item obschest;
	public static Item obslegs;
	public static Item obsboots;
	public static Item obshelmet;

	public static Item stewC;
	public static Item thrownWeb;
	
	//Weapons//
	public static Item bonehandlesword;
	
	public static ItemBow boneGripBow;
	public static Item boneGrip;

	
	//all four unadorned, unwearable obsidian armours
	public static Item unadornedObChest;
	public static Item unadornedObHelm;
	public static Item unadornedObLegs;
	public static Item unadornedObBoots;
	
	//public static ItemSlab stoneMossySlabOther;
	
	//public static ItemSlab stoneMossySlab;
	public static ItemDoor stoneDoorItem;

	public static ItemRecord eyeT;


	public static void init()
	{
		//items
		bookScales = new bookScale();
		mudBucket = new ItemMudBucket(JungleBlocks.mudBlock);
		carvedBone = new carvedBone(3680);
		carvedBonehandle = new carvedBonehandle(3681);
		decorativeSeal = new decorativeSeal(3683);
		
		obschest = new ceremonialObsidian(w2theJungle.InfObsidian, obschestId, EntityEquipmentSlot.CHEST);
		obslegs = new ceremonialObsidian(w2theJungle.InfObsidian, obslegsId, EntityEquipmentSlot.LEGS);
		obsboots = new ceremonialObsidian(w2theJungle.InfObsidian, obsbootsId, EntityEquipmentSlot.FEET);
		obshelmet = new ceremonialObsidian(w2theJungle.InfObsidian, obshelmId, EntityEquipmentSlot.HEAD);

		clobberer = new theClobberer(ToolMaterial.DIAMOND);
		gemSapphire = new sapphire(3672);
		infusedObsidian = new InfusedObsidian(3675);
		rawCrabMeat = new rawCrabMeat();
		eyeT = new eyeTyrant();
		
		stewC = new stewJungle(4, false);
		
		thrownWeb = new ThrownWeb(3691);
		
		bonehandlesword =  new swordBonehandle(3671, ToolMaterial.STONE);
		boneGripBow = new boneGripBow();
		boneGrip = new boneGrip(3682);

		unadornedObChest = new UnadornedObChest(3676);
		unadornedObHelm = new UnadornedObHelm(3677);
		unadornedObLegs = new UnadornedObLegs(3678);
		unadornedObBoots = new UnadornedObBoots(3679);
		
		stoneDoorItem = new stoneDoorItem(Material.ROCK, JungleBlocks.stoneDoor);
		
		//records
		
		registerItems();
    }
	
	public static void registerItems()
	{
		registerItem(bookScales, "bookScale");
		
		registerItem(gemSapphire, "sapphire");
		
		registerItem(unadornedObChest, "unadornedObChest");
		registerItem(unadornedObHelm, "unadornedObHelm");
		registerItem(unadornedObLegs, "unadornedObLegs");
		registerItem(unadornedObBoots, "unadornedObBoots");
		
		registerItem(eyeT, "eyeT");
		registerItem(rawCrabMeat, "rawCrabMeat");
		registerItem(obschest, "cerechest");
		registerItem(obshelmet, "cerehelmet");
		registerItem(obslegs, "cerelegs");
		registerItem(obsboots, "cereboots");
		registerItem(stewC, "stewJungle");
		registerItem(stoneDoorItem, "stoneDoorItem");

		registerItem(boneGrip, "boneGrip");
		registerItem(boneGripBow, "boneGripBow");
		
		registerItem(clobberer, "clobberer");
		registerItem(infusedObsidian, "infusedObsidian");
		
		registerItem(decorativeSeal, "decorativeSeal");
		
		registerItem(carvedBone, "carvedBone");
		registerItem(carvedBonehandle, "carvedBonehandle");
		registerItem(bonehandlesword, "swordBonehandle");
		registerItem(mudBucket, "mudBucket");
		
		registerItem(JungleItems.thrownWeb, "thrownWeb");

	}
	
	public static void registerItem(Item item, String string)
	{
		item.setRegistryName(string);
		GameRegistry.register(item);
		//System.out.println(item);

	}

}
