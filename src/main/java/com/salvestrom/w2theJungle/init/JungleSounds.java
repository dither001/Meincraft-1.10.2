package com.salvestrom.w2theJungle.init;

import com.salvestrom.w2theJungle.lib.References;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class JungleSounds {

	public static SoundEvent RECORD_SURVIVORS;
	public static SoundEvent SAUROHN_DEATH;
	public static SoundEvent SAUROHN_HURT;
	public static SoundEvent SAUROHN_LAUGH;
	public static SoundEvent SAUROHN_MOCK;
	public static SoundEvent SAUROHN_TALK;
	
	
	public static void init() {
		RECORD_SURVIVORS = registerSound("records.eyeT");
		
		SAUROHN_DEATH = registerSound("saurohn.saurohnDeath");
		SAUROHN_HURT = registerSound("saurohn.saurohnHurt");
		SAUROHN_LAUGH = registerSound("saurohn.saurohnLaugh");
		SAUROHN_MOCK = registerSound("saurohn.saurohnMock");
		SAUROHN_TALK = registerSound("saurohn.saurohnTalk");
	}

	private static SoundEvent registerSound(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(References.MODID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}

	
}
