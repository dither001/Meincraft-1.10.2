package com.salvestrom.w2theJungle.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTyrantSkull extends TileEntity {
	
public int direction;

	public int getOrientation()
	{
		return this.direction;
	}

	public void setOrientation(int i)
	{
		this.direction = i;
		this.markDirty();
	}
	
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);

        par1.setInteger("direction", direction);
		return par1;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);
        
        if(par1.hasKey("direction"))
        {
        	this.direction = par1.getInteger("direction");
        }
        else
        {
        	
        }

    }
    
    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }
    
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        writeToNBT(var1);
        return new SPacketUpdateTileEntity(this.pos, 1, var1);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }
}
