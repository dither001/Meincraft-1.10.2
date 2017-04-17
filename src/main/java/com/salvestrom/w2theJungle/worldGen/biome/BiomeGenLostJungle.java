package com.salvestrom.w2theJungle.worldGen.biome;

import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;

import com.salvestrom.w2theJungle.mobs.entity.EntityGorrbat;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanScuffler;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanStalker;
import com.salvestrom.w2theJungle.mobs.entity.EntityLizardmanWitchDoctor;
import com.salvestrom.w2theJungle.mobs.entity.EntityMistSpider;
import com.salvestrom.w2theJungle.mobs.entity.EntityStoneGolem;

public class BiomeGenLostJungle extends BiomeJungle {

	public BiomeGenLostJungle(int p_i45379_1_, boolean p_i45379_2_, BiomeProperties props) {
		super(false, props);
		
		this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        
        this.spawnableWaterCreatureList.add(new Biome.SpawnListEntry(EntitySquid.class, 15, 2, 4));
        
        this.spawnableCaveCreatureList.add(new Biome.SpawnListEntry(EntityBat.class, 20, 2, 8));
        this.spawnableCaveCreatureList.add(new Biome.SpawnListEntry(EntityCaveSpider.class, 5, 1, 3));
        
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityGorrbat.class, 50, 2, 5));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 15, 2, 5));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 30, 4, 4));
        //this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 20, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 50, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityOcelot.class, 5, 2, 5));

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanScuffler.class, 100, 2, 5));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanStalker.class, 100, 2, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLizardmanWitchDoctor.class, 75, 2, 3));

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMistSpider.class, 100, 3, 7));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityStoneGolem.class, 15, 1, 1));


        
	}

}
