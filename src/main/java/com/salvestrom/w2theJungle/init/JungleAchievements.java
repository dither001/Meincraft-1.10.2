package com.salvestrom.w2theJungle.init;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class JungleAchievements {

	public static Achievement jungleFound;
	public static Achievement gorrbat;
	public static Achievement bookScaleI;
	public static Achievement cookStew;
	public static Achievement villageFound;
	public static Achievement bookScaleII;
	public static Achievement slainWall;
	public static Achievement harvestSapphire;
	public static Achievement craftInfObs;
	public static Achievement foundTemple;
	public static Achievement bookScaleIII;
	public static Achievement craftArmourI;
	public static Achievement takePortalLW;
	public static Achievement slainTyrant;
	public static Achievement bookScaleIV;
	//public static Achievement craftBow;
	//public static Achievement lootFragment;
	//public static Achievement foundNewJBiomes;
	public static Achievement jungleBook;
		
	public static void init()
	{
		jungleFound = new Achievement("achievement.jungleFound", "junglefound", 0, 0, Blocks.LEAVES2, (Achievement)null).initIndependentStat();
		gorrbat = new Achievement("achievement.gorrbat", "gorrbat", -2, 0, Blocks.LEAVES2, jungleFound);
		bookScaleI = new Achievement("achievement.bookScaleI", "bookScaleI", 2, 0, Items.BOOK, jungleFound);
		cookStew = new Achievement("achievement.cookStew", "cookStew", 4, 0, JungleItems.stewC, bookScaleI);
		villageFound = new Achievement("achievement.villageFound", "villageFound", 2, 2, JungleBlocks.mossyCarved, bookScaleI);
		bookScaleII = new Achievement("achievement.bookScaleII", "bookScaleII", 4, 2, Items.BOOK, villageFound);
		slainWall = new Achievement("achievement.slainWall", "slainWall", 0, 2, JungleBlocks.wallSkull, villageFound);
		harvestSapphire = new Achievement("achievement.harvestSapphire", "harvestSapphire", 6, 2, JungleItems.gemSapphire, bookScaleII);
		craftInfObs = new Achievement("achievement.craftInfObs", "craftInfObs", 8, 2, JungleBlocks.infusedObsidianBlock, harvestSapphire);
		foundTemple = new Achievement("achievement.foundTemple", "foundTemple", 4, 4, JungleBlocks.mossystairs, bookScaleII);
		bookScaleIII = new Achievement("achievement.bookScaleIII", "bookScaleIII", 6, 4, Items.BOOK, foundTemple);
		craftArmourI = new Achievement("achievement.craftArmourI", "craftArmourI", 4, 6, JungleItems.obshelmet, bookScaleIII);
		takePortalLW = new Achievement("achievement.takePortalLW", "takePortalLW", 6, 6, JungleBlocks.infusedObsidianBlock, bookScaleIII);
		slainTyrant = new Achievement("achievement.slainTyrant", "slainTyrant", 4, 6, JungleBlocks.tyrantSkull, bookScaleIII);
		bookScaleIV = new Achievement("achievement.bookScaleIV", "bookScaleIV", 8, 6, Items.BOOK, takePortalLW);
		//Achievement craftBow;// = new Achievement("achievement.craftInfObs", "craftInfObs", 8, 6, infusedObsidianBlock, harvestSapphire).registerStat();
		//Achievement lootFragment;// = new Achievement("achievement.craftInfObs", "craftInfObs", 8, 2, infusedObsidianBlock, harvestSapphire).registerStat();
		//Achievement foundNewJBiomes;// = new Achievement("achievement.craftInfObs", "craftInfObs", 8, 2, infusedObsidianBlock, harvestSapphire).registerStat();
		jungleBook = new Achievement("achievement.jungleBook", "jungleBook", 10, 8, JungleBlocks.fullScale, bookScaleIV);

		registerAchievements();
		
	}

	private static void registerAchievements()
	{
		jungleFound.registerStat();
		gorrbat.registerStat();
		bookScaleI.registerStat();
		cookStew.registerStat();
		villageFound.registerStat();
		bookScaleII.registerStat();
		slainWall.registerStat();
		harvestSapphire.registerStat();
		craftInfObs.registerStat();
		foundTemple.registerStat();
		bookScaleIII.registerStat();
		craftArmourI.registerStat();
		takePortalLW.registerStat();
		slainTyrant.registerStat();
		bookScaleIV.registerStat();
		jungleBook.registerStat();
		
		AchievementPage.registerAchievementPage(new AchievementPage("Welcome To The Jungle",
				new Achievement[]
						{
								jungleFound,
								gorrbat,
								bookScaleI,
								cookStew,
								villageFound,
								bookScaleII, 
								slainWall,
								harvestSapphire, 
								craftInfObs, 
								foundTemple,
								bookScaleIII,
								craftArmourI,
								takePortalLW, 
								slainTyrant, 
								bookScaleIV, 
								jungleBook
								}));//, craftBow,    			lootFragment, foundNewJBiomes}));


		
		
	}

}
