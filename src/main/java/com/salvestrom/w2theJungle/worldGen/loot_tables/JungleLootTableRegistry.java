package com.salvestrom.w2theJungle.worldGen.loot_tables;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class JungleLootTableRegistry extends LootTableList
{
    public static final ResourceLocation JUNGLE_CHESTS = register("chests/jungle_chest");
    public static final ResourceLocation JUNGLE_CHESTS_HUT = register("chests/jungle_chest_hut");
    public static final ResourceLocation JUNGLE_CHESTS_TOWN = register("chests/jungle_chest_town");
    public static final ResourceLocation JUNGLE_CHESTS_TOWN_LARGE_HUT = register("chests/jungle_chest_town_large_hut");
    public static final ResourceLocation JUNGLE_CHESTS_TOWN_LIBRARY = register("chests/jungle_chest_town_library");
    public static final ResourceLocation JUNGLE_CHESTS_TEMPLE = register("chests/jungle_chest_temple");
    public static final ResourceLocation JUNGLE_CHESTS_LOST = register("chests/jungle_chest_lost");

    private static ResourceLocation register(String sid)
    {
        return register(new ResourceLocation("thejungle", sid));
    }
}
