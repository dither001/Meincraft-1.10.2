package com.salvestrom.w2theJungle.worldGen.loot_tables;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.salvestrom.w2theJungle.items.bookScale;

import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class JungleBookLootFunction extends LootFunction {

	private final int bookId;
	
	protected JungleBookLootFunction(LootCondition[] conditionsIn, int metaIn) {
		super(conditionsIn);

		this.bookId = metaIn;
		
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context)
	{
		//new bookScale();
		if(this.bookId == 367)
		{
			stack = bookScale.eastScale;
		}
		if(this.bookId == 368)
		{
			stack = bookScale.westScale;
		}
		if(this.bookId == 369)
		{
			stack = bookScale.northScale;
		}
		if(this.bookId == 370)
		{
			stack = bookScale.southScale;
		}
		
		
		
		return stack;
	}

    public static class Serializer extends LootFunction.Serializer<JungleBookLootFunction>
        {
            public Serializer()
            {
                super(new ResourceLocation("set_bookId"), JungleBookLootFunction.class);
            }

            public void serialize(JsonObject object, JungleBookLootFunction functionClazz, JsonSerializationContext serializationContext)
            {
                object.add("bookId", serializationContext.serialize(functionClazz.bookId));
            }

            public JungleBookLootFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootCondition[] conditionsIn)
            {
                return new JungleBookLootFunction(conditionsIn, (int)JsonUtils.deserializeClass(object, "bookId", deserializationContext, int.class));
            }
        }

}
