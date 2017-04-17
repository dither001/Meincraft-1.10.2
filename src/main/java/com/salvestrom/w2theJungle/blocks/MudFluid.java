package com.salvestrom.w2theJungle.blocks;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;

public class MudFluid extends Fluid {
	
	public MudFluid(String fluidName) {
		super(fluidName,
				new ResourceLocation("thejungle:blocks/mudBlockStill"),
				new ResourceLocation("thejungle:blocks/mudBlockFlowing"));
		
		//this.setUnlocalizedName("mudFluid");
		this.setBlock(JungleBlocks.mudBlock);

		this.setEmptySound(SoundEvents.BLOCK_GRAVEL_PLACE);
		this.setFillSound(SoundEvents.BLOCK_GRAVEL_BREAK);
		
		this.viscosity = 4000;
		this.density = 4000;
		this.temperature = 295;
		this.luminosity = 0;
		}

	public int getColor()
    {
        return 0xFFFFFFFF;//0xdce0c064;
    }
    
}
