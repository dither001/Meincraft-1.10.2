package com.salvestrom.w2theJungle.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

public class MudLiquid extends MaterialLiquid {

	public MudLiquid(MapColor p_i2116_1_) {
		super(p_i2116_1_);
		
		this.setReplaceable();
        this.setNoPushMobility();
	}
	
    public boolean isLiquid()
    {
        return true;
    }

    public boolean blocksMovement()
    {
        return false;
    }

    public boolean isSolid()
    {
        return false;
    }
}
