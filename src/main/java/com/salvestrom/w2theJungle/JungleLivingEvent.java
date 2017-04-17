package com.salvestrom.w2theJungle;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import com.salvestrom.w2theJungle.items.ceremonialObsidian;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.scoreboard.IScoreCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JungleLivingEvent {

	Random rnd = new Random();
    public World thisworld;
	public EntityPlayerMP player;

	@SubscribeEvent
	public void setWorld(WorldEvent we) {
		this.thisworld = we.getWorld();
		}
	//0 is helmet, 1 chest, 2 legs, 3 boots
	@SubscribeEvent
	public void keepObsidianArmour(LivingDeathEvent lde)
	{
		//TODO redo this using the new version
		//also, does playerdropevent not work? i think the inventory has been lean out already...
		if(!this.thisworld.getGameRules().getBoolean("keepInventory")
				&& lde.getEntity() instanceof EntityPlayerMP)
		{
			lde.setCanceled(true);

			EntityPlayerMP ntt = (EntityPlayerMP)lde.getEntity();
			boolean flag = ntt.worldObj.getGameRules().getBoolean("showDeathMessages");
			ntt.connection.sendPacket(new SPacketCombatEvent(ntt.getCombatTracker(), SPacketCombatEvent.Event.ENTITY_DIED, flag));

	        if (flag)
	        {
	            Team team = ntt.getTeam();

	            if (team != null && team.getDeathMessageVisibility() != Team.EnumVisible.ALWAYS)
	            {
	                if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OTHER_TEAMS)
	                {
	                	ntt.mcServer.getPlayerList().sendMessageToAllTeamMembers(ntt, ntt.getCombatTracker().getDeathMessage());
	                }
	                else if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OWN_TEAM)
	                {
	                	ntt.mcServer.getPlayerList().sendMessageToTeamOrAllPlayers(ntt, ntt.getCombatTracker().getDeathMessage());
	                }
	            }
	            else
	            {
	            	ntt.mcServer.getPlayerList().sendChatMsg(ntt.getCombatTracker().getDeathMessage());
	            }
	        }

	        if(!ntt.isSpectator())
	        {
	        
	        ItemStack[] ais = ntt.inventory.armorInventory;
			
			for(int i = 0; i < ais.length; ++i)
			{
				if (ais[i] != null && (ais[i].getItem() instanceof ceremonialObsidian))
				{
					ais[i] = ais[i];
				}				
				else
				{
					ntt.dropItem(ais[i], true, false);
					ais[i] = null;
				}
			}

			ItemStack mis[] = ntt.inventory.mainInventory;

			for(int i = 0; i < mis.length; ++i)
			{
				
				if(mis[i] != null && (mis[i].getItem() instanceof ceremonialObsidian))
				{
					mis[i] = mis[i];
				}
				else 
				{
					ntt.dropItem(mis[i], true, false);
					ntt.inventory.mainInventory[i] = null;
				}
			}		
			
	        }

	        Collection<ScoreObjective> collection = this.thisworld.getScoreboard().getObjectivesFromCriteria(IScoreCriteria.DEATH_COUNT);
	        Iterator<ScoreObjective> iterator = collection.iterator();

	        while (iterator.hasNext())
	        {
	            ScoreObjective scoreobjective = (ScoreObjective)iterator.next();
	            Score score = ntt.getWorldScoreboard().getOrCreateScore(ntt.getName(), scoreobjective);
	            score.incrementScore();
	        }

	        EntityLivingBase entitylivingbase = lde.getEntityLiving().getAttackingEntity();

	        if (entitylivingbase != null)
	        {
	            EntityList.EntityEggInfo entitylist$entityegginfo = (EntityList.EntityEggInfo)EntityList.ENTITY_EGGS.get(EntityList.getEntityString(entitylivingbase));

	            if (entitylist$entityegginfo != null)
	            {
	                ntt.addStat(entitylist$entityegginfo.entityKilledByStat, 1);
	            }

	            //entitylivingbase.addToPlayerScore(ntt, ntt.scoreValue);
	            //not visible, but not even used
	        }

	        ntt.addStat(StatList.DEATHS, 1);
	        ntt.getCombatTracker().reset();
		}
	}
	
	@SubscribeEvent
	public void keepObsidianArmourII(PlayerEvent.Clone pec)
	{
		if(pec.isWasDeath())
		{
			pec.getEntityPlayer().inventory.copyInventory(pec.getOriginal().inventory);
		}
	}
	
	/*
	@SubscribeEvent
	public void keepObsidianArmour(PlayerDropsEvent pde)
	{
		
		if(pde.entityPlayer instanceof EntityPlayerMP)
		{
			//pde.setCanceled(true);
			for (EntityItem item : pde.drops)
			{
				if(item.getEntityItem() == new ItemStack(w2theJungle.obshelmet, 1))
				{
					pde.drops.remove(item);
					//pde.entityPlayer.inventory.setInventorySlotContents(1, item.getEntityItem());
					//pde.entityPlayer.captureDrops = true;
				}
				else
				{
					pde.entityPlayer.joinEntityItemWithWorld(item);
				}
			}
		}
	}*/
}
