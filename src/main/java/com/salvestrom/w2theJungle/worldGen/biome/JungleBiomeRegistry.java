package com.salvestrom.w2theJungle.worldGen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DENSE;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.JUNGLE;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import com.salvestrom.w2theJungle.worldGen.WorldTypeJungle;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class JungleBiomeRegistry {
	
	public static void mainClass() {
		initializeBiome();
	}
	
	public static Biome biomeJungleMountain;// = new BiomeGenJungleM(67, false);
	public static Biome biomeJungleSwamp;// = new BiomeGenJungleSwamp(68, false);
	public static Biome lostJungle;
	
	public static WorldType JUNGLETYPE;

	
	private static void initializeBiome() {
		
		//check biometemp. match jungle, see if that affects placement...
		//mount was 67. ids no longer required??? 68 for swamp, 69 for lost world.
    	biomeJungleMountain = new BiomeGenJungleM(67, false, 
    			(new Biome.BiomeProperties("Jungle Mountain")
    			.setBaseHeight(1.0F)
    			.setHeightVariation(0.6F)
    			.setTemperature(0.95F)
    			.setRainfall(0.9F)));
   	
    	biomeJungleSwamp = new BiomeGenJungleSwamp(68, false,
    			(new Biome.BiomeProperties("Mangled Swamp")
    			.setBaseHeight(-0.075F)
    			.setHeightVariation(0.055F)
    			.setWaterColor(5610504) //75 is blye. 80 is green...
    			.setTemperature(0.95F)
    			.setRainfall(0.9F)));//22 is jhills, 1 is plains(stopped working /shrug,
    	
    	//#004d00 	rgb(0, 77, 0 -=- 3559680 dec/hex 365100
    	
    	lostJungle = new BiomeGenLostJungle(69, false,
    			(new Biome.BiomeProperties("Lost World")
    			.setWaterColor(5610504) //was ...85. green.  45 is blue. could also update fog colour method to alter water colour by dimension.
    			.setTemperature(0.95F)
    			.setRainfall(0.9F)));
    	
		JUNGLETYPE = new WorldTypeJungle(3, "jungle");

    	
		registerBiome();

	}
	
	private static void registerBiome() {
		
		biomeJungleMountain.setRegistryName("Jungle Mountain");
		biomeJungleSwamp.setRegistryName("Mangled Swamp");
		lostJungle.setRegistryName("Lost World");

		GameRegistry.register(biomeJungleMountain);
		GameRegistry.register(biomeJungleSwamp);
		GameRegistry.register(lostJungle);

		//Biome.registerBiome(67, "Jungle Mountain", biomeJungleMountain);
		//BiomeManager.addBiome(BiomeType.WARM, JungleBiomeRegistry.biomeJungleMountain);
    	BiomeDictionary.registerBiomeType(biomeJungleMountain, HOT, WET, DENSE, JUNGLE, Type.MOUNTAIN);
    	//BiomeManager.warmBiomes.add(new BiomeEntry(biomeJungleMountain, 0));
    	BiomeDictionary.registerBiomeType(biomeJungleSwamp, HOT, WET, DENSE, Type.SWAMP, JUNGLE);
    	BiomeDictionary.registerBiomeType(lostJungle, JUNGLE);
    	//lost jungle should naturally not be part of overworld.
    	//BiomeManager.warmBiomes.add(new BiomeEntry(biomeJungleSwamp, 0)); //down from 10 to kill off spawning
	}
	
}
