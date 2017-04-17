package com.salvestrom.w2theJungle;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

import com.salvestrom.w2theJungle.blocks.MudBlock;
import com.salvestrom.w2theJungle.blocks.MudFluid;
import com.salvestrom.w2theJungle.blocks.MudLiquid;
import com.salvestrom.w2theJungle.crafting.JungleCrafting;
import com.salvestrom.w2theJungle.enchantments.SaurClout;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.items.mossySlabItem;
import com.salvestrom.w2theJungle.items.mossySlabItemOther;
import com.salvestrom.w2theJungle.lib.ProxyCommon;
import com.salvestrom.w2theJungle.lib.References;
import com.salvestrom.w2theJungle.mobs.EntityRegistryJungle;
import com.salvestrom.w2theJungle.mobs.entity.EntityWeb;
import com.salvestrom.w2theJungle.tileentity.TileEntityAltarAbstract;
import com.salvestrom.w2theJungle.tileentity.TileEntityAncientSkull;
import com.salvestrom.w2theJungle.tileentity.TileEntityFullScale;
import com.salvestrom.w2theJungle.tileentity.TileEntityJungleBrazier;
import com.salvestrom.w2theJungle.tileentity.TileEntityTyrantSkull;
import com.salvestrom.w2theJungle.tileentity.TileEntityWallSkull;
//import com.salvestrom.w2theJungle.worldGen.ActualTestStructure;
import com.salvestrom.w2theJungle.worldGen.LostWorldProvider;
import com.salvestrom.w2theJungle.worldGen.MainMeinWG;
import com.salvestrom.w2theJungle.worldGen.WorldTypeJungle;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;
//import com.salvestrom.w2theJungle.worldGen.TestStructure;
import com.salvestrom.w2theJungle.worldGen.loot_tables.JungleBookLootFunction;
import com.salvestrom.w2theJungle.worldGen.loot_tables.JungleBookLootFunction.Serializer;

@SuppressWarnings("deprecation")
@Mod(modid = References.MODID,
name = References.name,
version = References.VERSION)
public class w2theJungle
{
	@SidedProxy(clientSide = References.Client,
			serverSide = References.Common)

	public static ProxyCommon proxy;
	
    @Mod.Instance
    public static w2theJungle instance;

	public static int dimensionIdLost = -42;
	public static boolean keepSpecialItems;
	public static DimensionType LOST_WORLD;
	
	public static CreativeTabs JungleModTab = new JungleMod(CreativeTabs.getNextID(), "JungleMod" );

	public static Enchantment saurclout = new SaurClout(255, Rarity.RARE);
	public static EnumCreatureAttribute reptilian;


	//World Generation
	public static MainMeinWG sapphireWGen = new MainMeinWG();

	//ObsidianCeremonial
	public static ArmorMaterial InfObsidian = EnumHelper.addArmorMaterial("InfusedObsidian", "thejungle:obsidianceremonial", 49, new int [] {4, 10, 7, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0); //3 8 6 3 for diamond
	
	PlayerJungleEventMore pjeventmore = new PlayerJungleEventMore();
	PlayerJungleEvent pjevent = new PlayerJungleEvent();
	JungleFogEvent jfogevent = new JungleFogEvent();
	JungleLivingEvent jlivingevent = new JungleLivingEvent();
	

	
	public void configpreInit(FMLPreInitializationEvent event)
	{
		Configuration con = new Configuration(new File("config/thejungleMod.cfg"));
		
		con.load();
		dimensionIdLost = con.get("Settings", "Lost World ID", -42).getInt();
		keepSpecialItems = con.get("Settings", "Keep Special Items On Death", true).getBoolean();
		con.save();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.configpreInit(event);
		
//		AchievementPage.registerAchievementPage(new AchievementPage("Welcome To The Jungle",
//				new Achievement[]{jungleFound, gorrbat, bookScaleI, cookStew, villageFound,
//				bookScaleII, slainWall, harvestSapphire, craftInfObs, foundTemple,
//				bookScaleIII, craftArmourI, takePortalLW, slainTyrant, bookScaleIV, jungleBook}));//, craftBow,    			lootFragment, foundNewJBiomes}));

		
		
		proxy.preInit(event);

		LOST_WORLD = DimensionType.register("Lost", "_lost", w2theJungle.dimensionIdLost, LostWorldProvider.class, false);
		DimensionManager.registerDimension(w2theJungle.dimensionIdLost, w2theJungle.LOST_WORLD);

		JungleBiomeRegistry.mainClass();
		EntityRegistryJungle.loadEntities(); //entity ahead  of biome else crash
		GameRegistry.registerWorldGenerator(sapphireWGen, 1);
		saurclout.setRegistryName("saurclout");
		GameRegistry.register(saurclout);
		
		proxy.preInitRenders();
		
		LootFunctionManager.registerFunction(new JungleBookLootFunction.Serializer());
//		DimensionManager.registerProviderType(w2theJungle.dimensionIdLost, LostWorldProvider.class, false);
//		DimensionManager.registerDimension(w2theJungle.dimensionIdLost, DimensionType.w2theJungle.dimensionIdLost);
		
		//events
		MinecraftForge.EVENT_BUS.register(this.pjeventmore);
		MinecraftForge.EVENT_BUS.register(this.pjevent);
		MinecraftForge.EVENT_BUS.register(this.jfogevent);
		MinecraftForge.EVENT_BUS.register(this.jlivingevent);
	
		reptilian = EnumHelper.addCreatureAttribute("reptilian");
		//block


		
		//tile ents. these must he kept here or several textures will fail to apply.
		
		//GameRegistry.registerTileEntity(TileEntityJungleBrazier.class, "jungleBrazier");
		//GameRegistry.registerTileEntity(TileEntityJungleBrazier.class, "jungleBrazierLit");
		//GameRegistry.registerTileEntity(TileEntityAncientSkull.class, "ancientSkull");
		GameRegistry.registerTileEntity(TileEntityFullScale.class, "fullScale");
		GameRegistry.registerTileEntity(TileEntityAltarAbstract.class, "altarAbstract");
	}

	public w2theJungle() {}
	
	@EventHandler
	public void init(FMLInitializationEvent vnt)
	{
		proxy.registerRenderInformation();		


		//blocks



		//items

		//shifting this to after registering blocks cured crash on crafting of fullScale. only fullScale did this however.

		
		//tile entity registry with associated block. putting these here is causing the ingame item to have error'd  item text

		//achievements must be initialised after the items they refer to
		
		

		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		
	}

	public void registerBlock(Block block, String name) {
		GameRegistry.registerBlock(block, name);
	}

	public void registerItem(Item item, String name) {
		GameRegistry.registerItem(item, name);
	}
}
