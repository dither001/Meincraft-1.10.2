package com.salvestrom.w2theJungle.worldGen;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LostWorldProvider extends WorldProvider {
	
    //public LostWorldProvider provider;
    
	@Override
	public DimensionType getDimensionType() {
		return w2theJungle.LOST_WORLD;
	}
    
	@Override
	public void createBiomeProvider()
	{
		this.biomeProvider = new BiomeProviderSingle(JungleBiomeRegistry.lostJungle);
		//this.setDimension(w2theJungle.dimensionIdLost);
        this.hasNoSky = false; //using reduces lag but makes dark. true to remove lag.false
        //this.provider = this;

	}

	@Override
	public IChunkGenerator createChunkGenerator(){
		return new LostChunkProvider(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
	}
	
  protected void generateLightBrightnessTable()   {
	  //f artificially inflates the brightness?
	  //serious green hue if negative -5. -2 is utter black..
	  //f will determine the light level when no other source is present.
	  //super.generateLightBrightnessTable();
	  float f = 0.15f;//0.15F;// + this.worldObj.getHeight()/200;
	  for (int i = 0; i <= 15; ++i)
	  {
		  float f1 = 1.0F - (float)i / 15F;
		  this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		  }
	//System.out.println(f);  	  
  }
  
  public double getMovementFactor()
  {
      return 2.0;
  }
	
	
	public String getDimensionName() {
		return "Lost World";
	}
	/*
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {	//creates whitish fog.
//        return this.worldObj.getWorldVec3Pool().getVecFromPool(0.60000000298023224D, 0.629999999329447746D, 0.629999999329447746D);
        return Vec3.createVectorHelper((double)0.60000000298023224D, (double)0.629999999329447746D, (double)0.629999999329447746D);
    }
    
    
    @SideOnly(Side.CLIENT)
    public double getVoidFogYFactor()
    {
        return 0.015;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2)
    {
        return true;
    }
    
    */

    public void resetRainAndThunder()
    {
        this.worldObj.getWorldInfo().setRainTime(0);
        this.worldObj.getWorldInfo().setRaining(false);
        this.worldObj.getWorldInfo().setThunderTime(0);
        this.worldObj.getWorldInfo().setThundering(false);
    }
	
    public boolean isSurfaceWorld()
    {
        return true;
    }
	
    public boolean canRespawnHere()
    {
        return true;  //required tru to prevent beds exploding.
    }

    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return 33F;
    }
    
    public String getWelcomeMessage()
    {
    	return "Entering the Lost World";
    }
    
    public String getDepartMessage()
    {
    	return "Leaving the Lost World";
    }
    /*
    //this didnt work...
    public ChunkCoordinates getEntrancePortalLocation() {return null;}//		new ChunkCoordinates(0, 90, 0);}
    
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return 0;
    }
        
    public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_)
    {
        return false;
    }
*/
    public BlockPos getRandomizedSpawnPoint()
    {
        BlockPos ret = this.worldObj.getSpawnPoint();
        return ret;
    }
    
    public BlockPos getSpawnPoint()
    {
     //   net.minecraft.world.storage.WorldInfo info = worldObj.getWorldInfo();
        return new BlockPos(0, 54, 0);//(info.getSpawnX(), info.getSpawnY(), info.getSpawnZ());
    }
    
    public void setSpawnPoint(BlockPos pos)
    {
        worldObj.getWorldInfo().setSpawn(new BlockPos(0, 54, 0));
    }
    
    public int getHeight()
    {
        return 128;
    }

    public int getActualHeight()
    {
        return 128;
    }
    
    public boolean isDaytime()
    {
        return worldObj.getSkylightSubtracted() < 4;
    }
    
    public void setWorldTime(long time)
    {
    	//System.out.println(time);
        this.worldObj.getWorldInfo().setWorldTime(time);
    }

    public long getWorldTime()
    {
        return this.worldObj.getWorldInfo().getWorldTime();
    }

    public void onWorldSave()
    {
    	    	//System.out.println(worldObj.getWorldInfo().getWorldTime());

    }

}
