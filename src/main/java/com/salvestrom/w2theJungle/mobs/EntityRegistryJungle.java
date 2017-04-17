package com.salvestrom.w2theJungle.mobs;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.salvestrom.w2theJungle.w2theJungle;
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
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;

public class EntityRegistryJungle {
	
	public static void loadEntities() {
		EntityRegistryJungle.registerJungleEntities();
	}

	private static void registerJungleEntities() {
		
		//not sure about the ++i or the w2thejungle.class...
		
		int i = 167; //EntityRegistry.findGlobalUniqueEntityId();
		
		w2theJungle mod = w2theJungle.instance;

		
		EntityRegistry.registerModEntity(EntityWeb.class, "thrownWeb", i++, mod, 25, 10, true);

		//most mobs have a track range of 80 and update of 3. bosses are higher track range(160). most mobs are false, fast movers/frequent moves are true.
		EntityRegistry.registerModEntity(EntityGorrbat.class, "gorrbat", i++, mod, 80, 3, false, 0xeeeeee, 0xee22ee);
		
		EntityRegistry.registerModEntity(EntitySacrificialSkeleton.class, "SacSkel", i++, mod, 80, 3, false, 0xF9F7E7, 0x8e8966);
		EntityRegistry.registerModEntity(EntityLenny.class, "lenny", i++, mod, 160, 3, false);
		EntityRegistry.registerModEntity(EntityTheWall.class, "theWall", i++, mod, 160, 3, false);

		EntityRegistry.registerModEntity(EntityMistSpider.class, "MistSpider", i++, mod, 80, 3, false, 0x000000, 0xeeeeee);
		EntityRegistry.registerModEntity(EntityStoneGolem.class, "StoneGolem", i++, mod, 80, 3, false);
		EntityRegistry.registerModEntity(EntitySwampCrab.class, "SwampCrab", i++, mod, 80, 3, false, 0x004A7F, 0xFFF4DB);
		
		EntityRegistry.registerModEntity(EntityLizardmanScuffler.class, "Lizardman", i++, mod, 80, 3, false, 0x15b8cf, 0x1D7F8C);
		EntityRegistry.registerModEntity(EntityLizardmanStalker.class, "LizardmanStalker", i++, mod, 80, 3, false, 0x2bc451, 0x1D7F8C);
		EntityRegistry.registerModEntity(EntityLizardmanWitchDoctor.class, "LizardmanWitchDoctor", i++, mod, 80, 3, false, 0xee6633, 0x111111);
		
		EntityRegistryJungle.addJungleEntities();
	}

	private static void addJungleEntities() {
		
		EntityRegistry.addSpawn(EntityLizardmanScuffler.class, 30, 3, 5, EnumCreatureType.MONSTER,
				new Biome[] {Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS,
			JungleBiomeRegistry.biomeJungleMountain, JungleBiomeRegistry.biomeJungleSwamp});
		EntityRegistry.addSpawn(EntityLizardmanStalker.class, 40, 3, 5, EnumCreatureType.MONSTER,
				new Biome[] {Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS,
			JungleBiomeRegistry.biomeJungleMountain, JungleBiomeRegistry.biomeJungleSwamp});
		EntityRegistry.addSpawn(EntityLizardmanWitchDoctor.class, 25, 1, 2, EnumCreatureType.MONSTER,
				new Biome[] {Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS,
			JungleBiomeRegistry.biomeJungleMountain, JungleBiomeRegistry.biomeJungleSwamp});
		
		EntityRegistry.addSpawn(EntityMistSpider.class, 31, 2, 4, EnumCreatureType.MONSTER,
				new Biome[] {Biomes.JUNGLE, JungleBiomeRegistry.biomeJungleSwamp});
		EntityRegistry.addSpawn(EntityStoneGolem.class, 5, 0, 1, EnumCreatureType.MONSTER,
				new Biome[] {Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE,});
		EntityRegistry.addSpawn(EntityStoneGolem.class, 15, 1, 2, EnumCreatureType.MONSTER,
				new Biome[] {Biomes.JUNGLE_HILLS, JungleBiomeRegistry.biomeJungleMountain,});

		EntityRegistry.addSpawn(EntityGorrbat.class, 20, 3, 5, EnumCreatureType.MONSTER,
				new Biome[] {Biomes.JUNGLE_HILLS, JungleBiomeRegistry.biomeJungleMountain,});


		
	}

}
