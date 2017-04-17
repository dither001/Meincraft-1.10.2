package com.salvestrom.w2theJungle;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.worldGen.JungleSaveWorld;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JungleFogEvent {
	
    public int checkdim;

	public World world;
	public Minecraft mc;
	public EntityLivingBase player;

	//this is fine once the games running but fails during initial load.
	public float old = 0.5f;// = JungleSaveWorld.get(world).fogvar1;
	public float oldc = 45;// = JungleSaveWorld.get(world).fogvar2;
	public float oldr = 120;// = JungleSaveWorld.get(world).fogvar3;
	
	@SubscribeEvent
	public void setWorld(WorldEvent.Load wel) {
		this.world = wel.getWorld();

		this.old = JungleSaveWorld.get(world).fogvar1;
		this.oldc = JungleSaveWorld.get(world).fogvar2;
		this.oldr = JungleSaveWorld.get(world).fogvar3;
	}
	
	@SubscribeEvent
	public void setPlayer(PlayerEvent pe) {
		//this.player = pe.getEntityPlayer();
	}
	
	@SubscribeEvent
	public void loadWorldFog(WorldEvent.Load wel)
	{
		//System.out.println(oldc);
	}
	
	@SubscribeEvent
	public void unloadWorldFog(WorldEvent.Unload weu)
	{
		float[] f = {this.old, this.oldc, this.oldr};
		JungleSaveWorld.get(world).fogValues(f);

		

		//System.out.println(JungleSaveWorld.get(world).fogvar2);
	}

	@SideOnly(Side.CLIENT) //why is this alone client side??
	@SubscribeEvent
	public void JungleFog(FogDensity e) {
		
		e.setCanceled(true);

		/*using gl_exp creates a gradual fogging using only density.
		 * linear doesnt use density and requires a start and end, however, a value must be provided or the game freaks out.
		 * note that gl_fog density has no effect. must use e.density/eden
		 * Each elseif block must initialise variables
		 * dimensions require tweaking. is this the int variable in setupFog?? 0 -1 1 and > 999?
		 * is the float camera angle?? is the int 999 for raining?
		 * must check potion effects infogs.
		 */
		int x = (int) (e.getEntity().posX);
		int z = (int) (e.getEntity().posZ);
		Biome bio = this.world.getBiome(new BlockPos(x, 0, z));
		this.checkdim = this.world.provider.getDimension();

		int fir = 400;
		int sec = 401;

		this.mc = Minecraft.getMinecraft();
		int seer = mc.gameSettings.renderDistanceChunks*16;
		float eden = this.old; //JungleSaveWorld.get(world).fogvar1;// = this.old; //start point for linear fog
		float cannon = this.oldc; //JungleSaveWorld.get(world).fogvar2;// = this.oldc; //density. (0-1 by default in linear.
		float far = this.oldr; //JungleSaveWorld.get(world).fogvar3;// = this.oldr; //end point for linear fog.
		float yen = (float) Math.abs(Math.pow(((e.getEntity().posY-53)/(255-53)),3));//(float) (e.entity.posY/10);
		
//		this.old = JungleSaveWorld.get(world).fogvar1;
//		this.oldc = JungleSaveWorld.get(world).fogvar2;
//		this.oldr = JungleSaveWorld.get(world).fogvar3;
		
		//liquid fog first, giving it priority
		if(e.getEntity() instanceof EntityLivingBase)
		{
			EntityLivingBase elb = (EntityLivingBase)e.getEntity();

		if(elb.isPotionActive(MobEffects.BLINDNESS))
		{			
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
			eden = 0f;
			cannon = 0f;
			far = 5.0F;

			int j = elb.getActivePotionEffect(MobEffects.BLINDNESS).getDuration();

			if (j < 20)
			{
				far = 5.0F + (seer - 5.0F) * (1.0F - (float)j / 20.0F);
				}
			}
		else
			if (e.getState().getBlock() == JungleBlocks.mudBlock)
			{
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
				
				cannon = this.oldc;
				far = this.oldr;

				if (elb.isPotionActive(MobEffects.WATER_BREATHING))
				{
					eden = 0.6f;
					}
				else
				{
					eden = 1.2F - (float)EnchantmentHelper.getRespirationModifier(elb) * 0.12F;
					}
				}
			else
				if (e.getState().getMaterial() == Material.WATER)
				{
					GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
					
					cannon = this.oldc;
					far = this.oldr;

					if (elb.isPotionActive(MobEffects.WATER_BREATHING))
					{
						eden = 0.05F;
						}
					else
					{
						eden = 0.1F - (float)EnchantmentHelper.getRespirationModifier(elb) * 0.03F;
						}
					}
				else
					if (e.getState().getMaterial() == Material.LAVA)	{

						GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
						
						cannon = this.oldc;
						far = this.oldr;
						eden = 2.0F;
					}
					else
						if(bio != null)
						{
							if(bio instanceof BiomeRiver)
							{
								//biome specific fog adjustments follow
								//rivers adopt the fog value of the previous biome.
								GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
								
								eden = this.old;
								cannon = oldc;
								far = oldr;
							}
							else
								if(bio == JungleBiomeRegistry.biomeJungleMountain)
								{
									GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
									
									float near;
									float farr;
									
									near = 0.09f;
									farr = 0.35f;
									
									if(this.world.getSunBrightness(1f) < 0.45)
									{
										near = 0.45f;
										farr = 0.7f;
									}
									
									eden = (float) Math.abs(Math.pow(((elb.posY-63)/(255-63)),4));//)/sec;
									cannon = (((this.oldc * fir) + ((seer) * near))/sec)-eden;
									far = (float) (((this.oldr * fir) +((seer) * farr))/sec)-eden;
																	
								}
								else
									if(bio == JungleBiomeRegistry.biomeJungleSwamp || bio instanceof BiomeSwamp)
									{	
										GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
										
										float near;
										float farr;
										
										near = 0f;
										farr = 30f;
										
										if(this.world.getSunBrightness(1f) < 0.45)
										{
											near = 0f;
											farr = 60f;
										}
										eden = 0.01f;
										cannon = (((this.oldc * fir) + (near))/sec)+yen;
										far = (float) (((this.oldr * fir)+(farr+elb.posY-63))/sec)+yen;
										
										if(far < 15)
										{
											far = 15;// (float) (((this.oldr * fir)+(15))/sec)+yen;
											}
										}
									else
										if(this.world.isRaining())
										//TODO this should prolly come first establishing
											//a modifier that is part of all the other statements. 
											//in part this is already in place. the wi variable "weather intensity"
											//its just this code wont apply to any of the above calculations.
										{	//weather fog
											float wi = this.world.getRainStrength(1.0F);
											
											GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
											
											eden = 0.25f;
											cannon = ((oldc*fir) + 0 )/sec;
											far = (float) ((oldr * fir)+(((seer/1.25f)/wi)))/sec;
											
											
											if(this.world.isThundering()) {
												far = (float) (((oldr * fir)+(((seer/2)/wi))))/sec;
												}
											//temp drop due to height, causes thickening of fog
											WorldClient wc = mc.theWorld;
											int temp = wc.getPrecipitationHeight(new BlockPos(elb.posX, 0, elb.posZ)).getY();
											float f9 = bio.getFloatTemperature(new BlockPos(elb.posX, elb.posY, elb.posZ));
											//the return on the bbelow is just the first variable...?
											if(wc.getBiomeProvider().getTemperatureAtHeight(f9, temp) <= 0.15F) {
												far = (float) (((oldr * fir)+(((seer/3)/wi))))/sec;
											} 
											
											if(bio instanceof BiomeDesert || bio instanceof BiomeMesa) {
												eden = ((old * fir) + 0.005F) / sec;
												cannon = ((oldc*fir) + 1)/sec;
												far = (float) (((oldr * fir)+(((25)/wi))))/sec;
												//this.sandstorm();
												//really bad attempt to add sandstorms while raining in other biomes.
												}
											}
										else
											if(this.checkdim == w2theJungle.dimensionIdLost)
											{												
												GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
												
												eden = 0.01f; // ((old * fir) + (float) Math.abs(Math.pow(((255 - 53)/(e.entity.posY-53)),3))) / sec;
												cannon = (((oldc * fir) + (0F))/sec)+yen*2;
												far = (float) ((oldr * fir)+20+elb.posY)/sec+yen*10;
												
												if(far < 15)
												{
													//far = (float) (((oldr * fir)+(15))/sec)+yen;
													}
												}
											else
												if(this.checkdim < 0) {
													
													GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
													
													eden = ((old * fir) + 0.001F) / sec;
													cannon = ((oldc * fir + 0F)/sec);
													far = (float) (((oldr * fir)+(seer* 0.8F))/sec);
													}
												else
													if(checkdim > 0) {
														
														GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
														
														eden = ((old * fir) + 0.001F) / sec;
														cannon = ((oldc * fir + seer*0.75F)/sec);
														far = (float) (((oldr * fir)+(seer))/sec);
														
													} else if(this.checkdim == 0) {
														
														GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
														
														eden = ((this.old * fir) + 0.001F) / sec;
														cannon = ((this.oldc * fir + seer*0.75F)/sec);
														far = (float) (((this.oldr * fir)+(seer))/sec);
														}
													else
													{
														eden = this.old;
														cannon = this.oldc;
														far = this.oldr;
													}
							} //closes bio != null loop
		
						else
						{
							eden = this.old;
							cannon = this.oldc;
							far = this.oldr;
							}
		}
		
	        if (GLContext.getCapabilities().GL_NV_fog_distance) {
	        	GL11.glFogi(34138, 34139);
	        	}

//	        GL11.glFogf(GL11.GL_FOG_START, 0.0F);

		GL11.glFogf(GL11.GL_FOG_START, cannon);
		GL11.glFogf(GL11.GL_FOG_END, far);
		
		
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT);
		
		e.setDensity(eden);
		
		this.old = eden;
		this.oldc = cannon;
		this.oldr = far;
		if(this.oldr > seer) { this.oldr = seer;}
		
/*
 * 		if(world.getTotalWorldTime()%20 == 0)
		{
			float[] f = {this.old, this.oldc, this.oldr};
			JungleSaveWorld.get(world).fogValues(f);

			//System.out.println(far);
			//System.out.println("old far:");
			//System.out.println(this.oldc);
			//System.out.println("current far:");
		}
*/
    	
	}
	
	public float oldred;
	public float oldgreen;	
	public float oldblue;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void fogColourEvent(FogColors e) {
		
		boolean light = this.world.getSunBrightness(1f) > 0.475;
				//this.world.isDaytime() && this.world.isRemote;

		float rc;
		float gc;
		float bc;
	
		float rc2;
		float gc2;
		float bc2;
		
		float scaler = 250;
		float scales = 251;
		
		if(e.getEntity() instanceof EntityLivingBase)
		{
			this.player = (EntityLivingBase)e.getEntity();

			float potdur = (float)EnchantmentHelper.getRespirationModifier(this.player) * 0.2F;
			
			Biome bio = this.world.getBiome(new BlockPos(this.player.posX, 0, this.player.posZ));
		
			//test this to see if mutations are now covered by the instanceof
			if((bio instanceof BiomeDesert || bio instanceof BiomeMesa
//	    		|| Biome.getIdForBiome(bio) == Biomes.DESERT.isMutation() + 128 || bio.biomeID == BiomeDesert.desertHills.biomeID + 128
//	    		|| bio.biomeID == BiomeMesa.mesa.biomeID + 128 || bio.biomeID == BiomeMesa.mesaPlateau.biomeID + 128 || bio.biomeID == Biomes.mesaPlateau_F.biomeID + 128  
	    		)
	    		&& light// this.world.isDaytime()
	    		)
	    	{
				//System.out.println("desert day");

	    		rc = this.calcFogColour(this.oldred, 0.71f); //((oldred*scaler) + 0.71F) / scales; //0.71F;
				gc = ((this.oldgreen*scaler) + 0.8F) / scales; //0.8F;
				bc = ((this.oldblue*scaler) + 0.8F) / scales; //0.8F;
	    	}			
			else if(checkdim == -1)
			{
		    	rc = this.calcFogColour(oldred, 0.10f); //((oldred*scaler) + 0.71F) / scales; //0.71F;
				gc = this.calcFogColour(oldgreen, 0.01F);
				bc = this.calcFogColour(oldblue, 0.01F);
			}
			else if(checkdim == 1)
			{
		    	rc = this.calcFogColour(oldred, 0.0752f); //((oldred*scaler) + 0.71F) / scales; //0.71F;
				gc = this.calcFogColour(oldgreen, 0.0521067f);
				bc = this.calcFogColour(oldblue, 0.07525f);
				}
			
			else if(light && this.checkdim == 0 && (!this.world.isRaining() || !this.world.isThundering()))
			{
				rc = (float) ((oldred*scaler) + 0.68f) / scales;
				gc = (float) ((oldgreen*scaler) + 0.8f) / scales;
				bc = (float) ((oldblue*scaler) + 1f) / scales;
				
				if(player.posY < 53)
				{
					//TODO could do with this scaling as an exponential of depth
					rc = (float) ((((this.oldred*scaler) + 0.7 - (1 - (this.player.posY/80)))) / scales);
					gc = (float) ((((this.oldgreen*scaler) + 0.7 - (1 - (this.player.posY/80)))) / scales);
					bc = (float) ((((this.oldblue*scaler) + 0.7 - (1 - (this.player.posY/80)))) / scales);
					}	
				
				}
			else if(!light)
			{	
				rc = this.calcFogColour(oldred, 0.04016818f); //((oldred*scaler) + 0.71F) / scales; //0.71F;
				gc = this.calcFogColour(oldgreen, 0.045189206f);
				bc = this.calcFogColour(oldblue, 0.08002f);
				}
			else
			{
				//this code triggers during the day whilst its raining.
				rc = (float) ((oldred*scaler) + e.getRed()) / scales;
				gc = (float) ((oldgreen*scaler) + e.getGreen()) / scales;
				bc = (float) ((oldblue*scaler) + e.getBlue()) / scales;
				
				if(player.posY < 53)
				{
					//TODO could do with this scaling as an exponential of depth
					rc = (float) ((((this.oldred*scaler) + 0.7 - (1 - (this.player.posY/80)))) / scales);
					gc = (float) ((((this.oldgreen*scaler) + 0.7 - (1 - (this.player.posY/80)))) / scales);
					bc = (float) ((((this.oldblue*scaler) + 0.7 - (1 - (this.player.posY/80)))) / scales);
					}	
				}
			
			e.setRed(rc);
			e.setGreen(gc);
			e.setBlue(bc);
			
			if (e.getState().getBlock() == JungleBlocks.mudBlock)
			{
				rc2 = 0.62F + potdur; //62 47 20
				gc2 = 0.47F + potdur;
				bc2 = 0.3F + potdur;
				}
			else if(e.getState().getMaterial() == Material.LAVA)
			{
				rc2 = 0.6f;
				gc2 = 0.1f;
				bc2 = 0f;
				}
			else if(e.getState().getMaterial() == Material.WATER)
			{
				rc2 = 0.02F + potdur;
				gc2 = 0.02F + potdur;
				bc2 = 0.2F + potdur;
				
				if(bio == JungleBiomeRegistry.biomeJungleSwamp || bio == JungleBiomeRegistry.lostJungle)
				{
					rc2 = 0.1F + potdur;
					gc2 = 0.34F + potdur;
					bc2 = 0.12F + potdur;
				}
			}
			else
			{
				rc2 = oldred;
				gc2 = oldgreen;
				bc2 = oldblue;
	    	}
			
			e.setRed(rc2);
			e.setGreen(gc2);
			e.setBlue(bc2);

			oldred = rc;
			oldgreen = gc;
			oldblue = bc;
		}
	}
	
	public float calcFogColour(float old, float goal) 
	{
		int scaler = 250;
		int scales = 251;
		
		return ((old*scaler) + goal) / scales;
		
		//return newer;
		
	}		
}
	
	
/*	
	float[] rainXCoords;
	float[] rainYCoords;
	int rendererUpdateCount;
	
	public void sandstorm() {
		
		//for(int p = 0; p < 20; p++) 
		{
		
		int i = MathHelper.floor_double(player.posX);
    	int j = MathHelper.floor_double(player.posY - 0.20000000298023224D - (double) player.yOffset);
    	int k = MathHelper.floor_double(player.posZ);

    	for(int x = -4; x < 4; ++x)
    		for(int z = -4; z < 4; ++z) {
    			
    			if(x*x + z*z < 16) {
    				
    				Block block = this.world.getBlock(i+x, j, k+z);
    				
    				//if (block.getMaterial() != Material.air)
    				{
    					
    					double px = this.player.posX + x + ((double) this.world.rand.nextFloat() - 0.5D) * (double) 3.0;
    					double pz = this.player.posZ + z + ((double) this.world.rand.nextFloat() - 0.5D) * (double) 3.0;
    					
    					String st = "blockcrack_" + Block.getIdFromBlock(Blocks.sand) + "_" + this.world.getBlockMetadata(i+x, j, k+z);
    					
    					this.world.spawnParticle(st, px, j+2 + 0.1D, pz,
    							4.0D * ((double) this.world.rand.nextFloat() - 0.5D), 15D,
    							((double) this.world.rand.nextFloat() - 0.5D) * 8.0D);
    					
    					}
    				}
    			}
		}
		
	}
	
}

		
		//TODO could try mass spawning particles relative to player, whipping up from ground.
		
		float p_78474_1_ = 1.0F;
		
		Random random = new Random();
		
		IRenderHandler renderer = null;
        if ((renderer = this.mc.theWorld.provider.getWeatherRenderer()) != null)
        {
            renderer.render(p_78474_1_, this.mc.theWorld, mc);
            return;
        }

        float f1 = this.mc.theWorld.getRainStrength(p_78474_1_);

        if (f1 > 0.0F)
        {
            //this.enableLightmap((double)p_78474_1_);

            if (rainXCoords == null)
            {
                rainXCoords = new float[1024];
                rainYCoords = new float[1024];

                for (int i = 0; i < 32; ++i)
                {
                    for (int j = 0; j < 32; ++j)
                    {
                        float f2 = (float)(j - 16);
                        float f3 = (float)(i - 16);
                        float f4 = MathHelper.sqrt_float(f2 * f2 + f3 * f3);
                        rainXCoords[i << 5 | j] = -f3 / f4;
                        rainYCoords[i << 5 | j] = f2 / f4;
                    }
                }
            }
		
		
		 EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
         WorldClient worldclient = this.mc.theWorld;
         int k2 = MathHelper.floor_double(entitylivingbase.posX);
         int l2 = MathHelper.floor_double(entitylivingbase.posY);
         int i3 = MathHelper.floor_double(entitylivingbase.posZ);
         Tessellator tessellator = Tessellator.instance;
         GL11.glDisable(GL11.GL_CULL_FACE);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GL11.glEnable(GL11.GL_BLEND);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
         double d0 = entitylivingbase.lastTickPosX + (entitylivingbase.posX - entitylivingbase.lastTickPosX) * (double)p_78474_1_;
         double d1 = entitylivingbase.lastTickPosY + (entitylivingbase.posY - entitylivingbase.lastTickPosY) * (double)p_78474_1_;
         double d2 = entitylivingbase.lastTickPosZ + (entitylivingbase.posZ - entitylivingbase.lastTickPosZ) * (double)p_78474_1_;
         int k = MathHelper.floor_double(d1);
         byte b0 = 5;

         if (this.mc.gameSettings.fancyGraphics)
         {
             b0 = 10;
         }
         
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

         for (int l = i3 - b0; l <= i3 + b0; ++l)
         {
             for (int i1 = k2 - b0; i1 <= k2 + b0; ++i1)
             {
                 int j1 = (l - i3 + 16) * 32 + i1 - k2 + 16;
                 float f6 = rainXCoords[j1] * 0.5F;
                 float f7 = rainYCoords[j1] * 0.5F;
                 BiomeGenBase biomegenbase = worldclient.getBiomeGenForCoords(i1, l);

                 if (biomegenbase.canSpawnLightningBolt() || biomegenbase.getEnableSnow())
                 {
                     int k1 = worldclient.getPrecipitationHeight(i1, l);
                     int l1 = l2 - b0;
                     int i2 = l2 + b0;

                     if (l1 < k1)
                     {
                         l1 = k1;
                     }

                     if (i2 < k1)
                     {
                         i2 = k1;
                     }

                     float f8 = 1.0F;
                     int j2 = k1;

                     if (k1 < k)
                     {
                         j2 = k;
                     }

                     if (l1 != i2)
                     {
                         random.setSeed((long)(i1 * i1 * 3121 + i1 * 45238971 ^ l * l * 418711 + l * 13761));
                         float f9 = biomegenbase.getFloatTemperature(i1, l1, l);
                         float f10;
                         double d4;
                     
                        
                         
                     	GL11.glPushMatrix();
                     	
                     	tessellator.draw();
                         
                         this.mc.getTextureManager().bindTexture(locationSnowPng);
                         tessellator.startDrawingQuads();



         float f5 = (float)rendererUpdateCount + p_78474_1_;

         
        f10 = ((float)(rendererUpdateCount & 511) + p_78474_1_) / 512.0F;
        float f16 = random.nextFloat() + f5 * 0.01F * (float)random.nextGaussian();
        float f11 = random.nextFloat() + f5 * (float)random.nextGaussian() * 0.001F;
        d4 = (double)((float)i1 + 0.5F) - player.posX;
        double d5 = (double)((float)l + 0.5F) - player.posZ;
        float f14 = MathHelper.sqrt_double(d4 * d4 + d5 * d5) / (float)b0;
        float f15 = 1.0F;
        //tessellator.setBrightness((worldclient.getLightBrightnessForSkyBlocks(i1, j2, l, 0) * 3 + 15728880) / 4);
        tessellator.setColorRGBA_F(f15, f15, f15, ((1.0F - f14 * f14) * 0.3F + 0.5F) * f1);
        tessellator.setTranslation(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
        tessellator.addVertexWithUV((double)((float)i1 - f6) + 0.5D, (double)l1, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8 + f16), (double)((float)l1 * f8 / 4.0F + f10 * f8 + f11));
        tessellator.addVertexWithUV((double)((float)i1 + f6) + 0.5D, (double)l1, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8 + f16), (double)((float)l1 * f8 / 4.0F + f10 * f8 + f11));
        tessellator.addVertexWithUV((double)((float)i1 + f6) + 0.5D, (double)i2, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8 + f16), (double)((float)i2 * f8 / 4.0F + f10 * f8 + f11));
        tessellator.addVertexWithUV((double)((float)i1 - f6) + 0.5D, (double)i2, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8 + f16), (double)((float)i2 * f8 / 4.0F + f10 * f8 + f11));
        tessellator.setTranslation(0.0D, 0.0D, 0.0D);

        GL11.glPopMatrix();
        
    ++rendererUpdateCount;
                     }
                 }
             }
         }
         
        }
	}
             


} */
