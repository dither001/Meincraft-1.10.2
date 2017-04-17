package com.salvestrom.w2theJungle.worldGen;

import com.salvestrom.w2theJungle.worldGen.layer.JungleGenLayerBiome;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class WorldTypeJungle extends WorldType {

	public WorldTypeJungle(int i, String s) {
		super(s);
		
	}
	
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, String string)
    {
        GenLayer ret = new JungleGenLayerBiome(200L, parentLayer, this, "jungle_mod");
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
    
    public void onGUICreateWorldPress()
    {
//    	System.out.println("loading");
    }


	
}
