package com.salvestrom.w2theJungle;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.init.JungleAchievements;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.init.JungleItems;
import com.salvestrom.w2theJungle.items.bookScale;
import com.salvestrom.w2theJungle.mobs.entity.EntityGorrbat;
import com.salvestrom.w2theJungle.worldGen.LostWorldProvider;
import com.salvestrom.w2theJungle.worldGen.biome.JungleBiomeRegistry;

public class PlayerJungleEvent {
	
	Random rnd = new Random();
    public World thisworld;
	public EntityPlayerMP player;

	@SubscribeEvent
	public void setWorld(WorldEvent we) {
		this.thisworld = we.getWorld();
		}
	
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	public void loadWorld(WorldEvent.Load world) {
		//this method resets the worldgen file boolean "dun" so that fresh worlds can spawn temples.
		//older worlds will not pass the time check however, so existing temples will not rebuild.
		//w2theJungle.sapphireWGen.dun = false;
	}
	
	@SubscribeEvent
	public void playerSleepInLostWorld(PlayerWakeUpEvent psibe)
	{
		/*
		World wrld = psibe.getEntityPlayer().worldObj;
		
		if(wrld.provider instanceof LostWorldProvider)
		{
		
		psibe.getEntityPlayer().worldObj.provider.resetRainAndThunder();
		
        if (wrld.getGameRules().getBoolean("doDaylightCycle"))
        {
            long i = wrld.getWorldInfo().getWorldTime() + 24000L;
            wrld.getWorldInfo().setWorldTime(i - i % 24000L);
            System.out.println(wrld.getWorldInfo().getWorldTime() + 24000L);
        }
		}
        System.out.println(wrld.getWorldInfo().getWorldTime() + 24000L);
*/
		//return null;//psibe.getResultStatus();
	}
	
	//@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void exploringJungle(PlayerEvent.EnteringChunk ee) {

		//this suddenly works so much better now that it checks player coords
		if(ee.getEntity() != null)
		{
			if(ee.getEntity() instanceof EntityPlayerMP)
			{
				this.player = (EntityPlayerMP)ee.getEntity();
				
			
			int x = (int) this.player.posX;
			int z = (int) this.player.posZ;
			
			Biome bio = this.thisworld.getBiome(new BlockPos(x, 0, z));
			
			//locks out all this code once the player has the portal achievement. -
			//portal ach wont proc til temple ach, and so on back
			if(!(this.player).hasAchievement(JungleAchievements.takePortalLW))
			{
				if(this.thisworld.provider.getDimension() == w2theJungle.dimensionIdLost)
				{
					this.player.addStat(JungleAchievements.takePortalLW);
					}

				if(!(this.player).hasAchievement(JungleAchievements.jungleFound)) {
					if(bio != null && (bio instanceof BiomeJungle //|| bio.biomeID == BiomeJungle.jungle.biomeID + 128 
							|| bio == JungleBiomeRegistry.biomeJungleSwamp 
							|| bio == JungleBiomeRegistry.biomeJungleMountain
							)) {
						this.player.addStat(JungleAchievements.jungleFound);
						}
					}
				
				if(!(this.player).hasAchievement(JungleAchievements.villageFound)) {
					if((bio == JungleBiomeRegistry.biomeJungleSwamp || bio == JungleBiomeRegistry.biomeJungleMountain
							|| bio instanceof BiomeSwamp)
							&& localBlockCheck(JungleBlocks.altarAbstract)) {
						this.player.addStat(JungleAchievements.villageFound);
						}
					}

				if(!(this.player).hasAchievement(JungleAchievements.foundTemple)
						&& localBlockCheck(JungleBlocks.mossyCarved)) {
					this.player.addStat(JungleAchievements.foundTemple);
					}
				}
			
			if(!(this.player).hasAchievement(JungleAchievements.gorrbat)
					&& (bio == JungleBiomeRegistry.biomeJungleMountain || bio == Biomes.JUNGLE_HILLS))
			{
				List<Entity> list =	(this.player).worldObj.getEntitiesWithinAABBExcludingEntity(
						this.player,
						this.player.getEntityBoundingBox().expand(15,  15,  15));
				
				for(int i = 0; i < list.size(); ++i)
				if(list.get(i) instanceof EntityGorrbat)
				{
					this.player.addStat(JungleAchievements.gorrbat);
				}
					
			}
			
			}
		}
	}

	private boolean localBlockCheck(Block block)
	{		
		int xx = (int) this.player.posX;
		int yy = (int) this.player.posY;
		int zz = (int) this.player.posZ;
	
		for(int x = xx-8; x < xx+7; x++)
			for(int z = zz-8; z < zz+7; z++)
				for(int y = yy-2; y < yy + 13; y++)
					if(thisworld.getBlockState(new BlockPos(x, y, z)).getBlock() == block)
					{
						return true;
					}
		
		return false;
		}
	
	@SubscribeEvent
	public void jungleLoot(EntityItemPickupEvent ipe) { //PlayerUseItemEvent iuse this to read a book
		
		ItemStack grabbed = ipe.getItem().getEntityItem();
		
//		System.out.println(grabbed.getMaxDamage());
		
		if(ipe.getEntityPlayer() != null)
		{
			if(grabbed.getItem() == JungleItems.gemSapphire)
			{
				ipe.getEntityPlayer().addStat(JungleAchievements.harvestSapphire);
				}
			}
		}

	//@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void jungleLootExtra(RightClickItem pie) {
		
		if(pie.getEntityPlayer() != null)
		{
			//this.playerClient = (EntityClientPlayerMP)pie.entity;
			
			//if(this.playerClient != null)
			{
				ItemStack is = pie.getEntityPlayer().getHeldItemMainhand();

				if(is != null)
				{
					String reading = is.getDisplayName();
					
					if(reading.equals(bookScale.eastScale.getDisplayName()))
					{
						pie.getEntityPlayer().addStat(JungleAchievements.bookScaleI);
						}
					
					if(reading.equals(bookScale.westScale.getDisplayName()))
					{
						pie.getEntityPlayer().addStat(JungleAchievements.bookScaleII);
						}
					
					if(reading.equals(bookScale.northScale.getDisplayName()))
					{
						pie.getEntityPlayer().addStat(JungleAchievements.bookScaleIII);
						}
					
					if(reading.equals(bookScale.southScale.getDisplayName()))
					{
						pie.getEntityPlayer().addStat(JungleAchievements.bookScaleIV);
						}
					}
				}
			}
		}
	

		
		
	
	/*//now redundant, but useful
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void sapphireSilkTouch(BlockEvent.HarvestDropsEvent ntt) {
		
		if(ntt.block == w2theJungle.oreSapphire && EnchantmentHelper.getSilkTouchModifier(player)) {
			ntt.drops.clear();
			ntt.drops.add(new ItemStack(w2theJungle.gemSapphire));
			
		}
		
	} */
	
}

