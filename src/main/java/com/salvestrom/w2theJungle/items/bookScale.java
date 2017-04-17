package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleItems;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class bookScale extends ItemBook {
	
	// \" goes after first quotes and before last of each page string. \n will start a new line
	
	//change these titles to match achievements and reserve the book of scale for combined object.
	
	public static ItemStack eastScale;
	public static ItemStack westScale;
	public static ItemStack northScale;
	public static ItemStack southScale;
	
	public bookScale() 
    {
		 super();
	    	this.setUnlocalizedName("bookScale"); //Omnibusaurus Codex
	    	this.setContainerItem(this);
	    	this.setCreativeTab(w2theJungle.JungleModTab);
	    	this.setHasSubtypes(true);
    }
	
	static
	{		
		{
			NBTTagCompound tag = new NBTTagCompound();
	    		
		tag.setString("author", ("Salvestrom"));
		tag.setString("title", ("The Books of Scale: The Bringer"));//Lord of the Living"));
		//23 char or 29 spaces approx. or aaaabbbbaaaabbbbaaa!
		NBTTagList bookPages = new NBTTagList();
		tag.setTag("pages", bookPages);
	    bookPages.appendTag(new NBTTagString
	    		("\"Who really knows\n"
	    		+ " and who can swear\n"
	    		+ " How minecraft came,\n"
	    		+ " when or where!\n"
	    		+ "\n"
	    		+ "Even gods came\n"
	    		+ " after building day,\n"
	    		+ " Who really knows,\n"
	    		+ " who can truly say\n"
	    		+ "\n"
	    		+ "When and how\n"
	    		+ " did generation start?\n"
	    		+ " Did He do it?\n"
	    		+ " Was it Notch?\""));
	    bookPages.appendTag(new NBTTagString
	    		 ("\"Only He, up there,\n"
	    		+ " knows, maybe;\n"
	    		+ " Or perhaps,\n"
	    		+ " not even He...\n"
	    		+ "\n"
	    		+ "But we were first!!\n"
   		 		+ "\n"
	    		+ " — Dig Depa 10.129.1-7\n"
	    		+ "\n"
	    		+ "The ancient creation hymn of the Saur-Ohn.\n"
	    		+ "\n"
	    		+ "          -=-\""));
	    bookPages.appendTag(new NBTTagString
	    		 ("\"This seems more of a scrapbook"
	    		+ " than anything else. There are"
	    		+ " bits of leaf and bark and paper"
	    		+ " and things you wish were paper,"
	    		+ " covered in various scrawls, all packed together,"
	    		+ " loosely bound: a jumbled "
	    		+ " compilation of half-remembered"
	    		+ " wisdom and technologies\""));
	    bookPages.appendTag(new NBTTagString
	    	     ("\"from long ago.\n"
	    		+ "\n"
	    		+ "You study one piece of parchment that has"
	    		+ " been written in such a frenzy the text"
	    		+ " is quite illegible. Its contents are a"
	    		+ " mystery to you.\n"
	    		+ "\n"
	    		+ "On the reverse side you find"
	    		+ " what looks a lot like a recipie"
	    		+ " for stew: crab meat and \""));
	    	bookPages.appendTag(new NBTTagString
	    		 ("\"vegetables slowly boiled over night in a pot."
	    		+ " This was apparently a communal meal"
	    		+ " for the lizards: part of a restorative"
	    		+ " ceremony for conflict "
	    		+ "resolution.\n"
   		 		+ "\n"
	    		+ "          -=-\""));
	    	bookPages.appendTag(new NBTTagString
	    		 ("\"The remainder of this volumne"
   				+ " details many other aspects of"
   				+ " the saur-ohn daily routine. Hunting,"
   				+ " Farming and Ritual are covered at"
   				+ " length, much of it having a great deal"
   				+ " of emphasis on the Sun. The Lord of the Living."
   				+ " Often simply referred to as 'The Bringer'.\n"
	    		+ "\""));
	    	bookPages.appendTag(new NBTTagString
	    		 ("\"All saur-ohn settlements have an alter"
   		 		+ " dedicated to this Lord of the Living,"
	    		+ " such as the brazier outside this hut,"
	    		+ " kept constantly burning. Larger settlements"
	    		+ " had bigger, more impressive structures"
	    		+ " about which daily routine revolved.\""
	    		));
	    	bookPages.appendTag(new NBTTagString
	    		 ("\"Perhaps you should\n"
	    		 		+ " try finding such a\n"
	    				+ " place.\""
   				));
	    	
			eastScale = new ItemStack(Items.WRITTEN_BOOK);
			eastScale.setItemDamage(367);
	    	eastScale.setTagCompound(tag);

	}
	{
		NBTTagCompound wtag = new NBTTagCompound();
		
	    NBTTagList westPages = new NBTTagList();
	    wtag.setTag("pages", westPages);
		wtag.setString("author", ("Salvestrom"));
		wtag.setString("title", ("The Books of Scale: The Taker"));//Lord of the Damned"));
		//23 char or 29 spaces approx. or aaaabbbbaaaabbbbaaa!
	    westPages.appendTag(new NBTTagString
	    		 ("\"The first sheet "
	    		+ "in this compilation is "
	    		+ "an image of the moon, "
	    		+ "divided into unequal "
	    		+ "sections, each "
	    		+ "depicting a "
	    		+ "terrible punishment. "
	    		+ "It seems the Saur-"
	    		+ "ohn equated the "
	    		+ "celestial body with "
	    		+ "some sort of "
	    		+ "Netherworld. The ruler "
	    		+ "of this Selenic realm "
	    		+ "is referred to only by\""));
	    westPages.appendTag(new NBTTagString
	    		 ("\"his title: The Lord of "
	    		+ "the Damned. Scribbled "
	    		+ "in one corner is part "
	    		+ "of a hymn dedicated to "
	    		+ "this feared being:\n"
   		 		+ "\n"
   		 		+ "Fate, the moon,\n"
   		 		+ " monstrous, empty,\n"
   		 		+ " waxing, waning.\n"
   		 		+ "You spinning square,\n"
   		 		+ " so malevolent.\n"
   		 		+ "Well-being is vain\n"
   		 		+ " and always fades to\n"
   		 		+ " nothing.\""));
	    westPages.appendTag(new NBTTagString
	    		 ("\"Shadowed and veiled\n"
	    		+ " you plague me too.\n"
	    		+ "Now through this game\n"
	    		+ " I bring my bare back\n"
	    		+ " to your villainy.\n"
   		 		+ "\n"
   		 		+ "          -=-\n"
   		 		+ "\n"
   		 		+ "Several pages on you "
   		 		+ "finally come to "
   		 		+ "something of real "
   		 		+ "note: An image of a "
   		 		+ "gemstone you've never before\""));
	    westPages.appendTag(new NBTTagString
	    		 ("\"encountered. A "
	    		+ "Soul Sapphire. The "
	    		+ "Saur-Ohn believe "
	    		+ "that the spirits of the "
	    		+ "dead find "
	    		+ "their way into these "
	    		+ "stones, becoming "
	    		+ "permanently trapped "
	    		+ "within - serving as a "
	    		+ "source of great "
	    		+ "power if properly "
	    		+ "utilised. You read on, "
	    		+ "learning that the "
	    		+ "Priests of the "
	    		+ "Saur-Ohn had\""));
	    westPages.appendTag(new NBTTagString
	    		 ("\"actually created a "
	    		+ "ritual whereby they "
	    		+ "could coax out the "
	    		+ "spirits from their "
	    		+ "place of rest and into "
	    		+ "a block of obsidian, "
	    		+ "allowing the otherwise "
	    		+ "hardened material to "
	    		+ "be manipulated while "
	    		+ "becoming endowed with "
	    		+ "other properties. "
	    		+ "Apparantly ice was an "
	    		+ "important ingrediant "
	    		+ "in conducting this\""));
	    westPages.appendTag(new NBTTagString
  	    		 ("\"transition.\n"
  	      		+ "\n"
  	     		+ "One particular use the "
  	     		+ "saur-ohn put the "
  	     		+ "gems to was in "
  	     		+ "powering stone golems "
  	     		+ "to serve as night time "
  	     		+ "defenders. One piece of "
  	     		+ "text, written in "
  	     		+ "blood, tells of a "
  	     		+ "mighty golem they "
  	     		+ "constructed and named "
  	     		+ "'The Wall'. This \""));
	    westPages.appendTag(new NBTTagString
	    		 ("\"monolithic entity was "
	    		+ "not for defense, "
	    		+ "however. They refer to "
	    		+ "it as 'The Great "
	    		+ "Weapon', to be taken "
	    		+ "immediately to the "
	    		+ "Temple of Mahkin.\n"
  	      		+ "\n"
	    		+ "The page is clearly "
	    		+ "torn here and no more "
	    		+ "details are recorded "
	    		+ "of The Wall, upon whom "
	    		+ "they wished to unleash him,\""));
	    westPages.appendTag(new NBTTagString
	    		 ("\"or why the device "
   		 		+ "is still in the village.\n" 
  	      		+ "\n"
  	    		+ "There may be more "
  	    		+ "information at this "
  	    		+ "Temple of Mahkin, "
  	    		+ "assuming you can find "
  	    		+ "it.\n"
  	    		+ "\n"
   		 		+ "          -=-\""));
	    
	    westScale = new ItemStack(Items.WRITTEN_BOOK);
		westScale.setTagCompound(wtag);
	    westScale.setItemDamage(368);

	}
	{
		NBTTagCompound ntag = new NBTTagCompound();
		
		ntag.setString("author", ("Salvestrom"));
		ntag.setString("title", ("The Books of Scale: The Shield"));
		//23 char or 29 spaces approx. or aaaabbbbaaaabbbbaaa!
		NBTTagList northPages = new NBTTagList();
	    ntag.setTag("pages", northPages);
		northPages.appendTag(new NBTTagString
	    		 ("\"The Wailing Winter:\n"
	    	 	+ "\n"
	    		+ "All along the Beacon\n"
	    		+ " towers,\n"
	    		+ " Priests kept their\n"
	    		+ " view.\n"
	    		+ "\n"
	    		+ "While the summer came\n"
	    		+ " and went\n"
	    		+ " see how we shudder\n"
	    		+ " 'neath the moon.\""));
		northPages.appendTag(new NBTTagString
	    	 	 ("\"Outside in the Cold\n"
	    		+ " distance,\n"
	    		+ " Ocelots did growl.\n"
	    		+ "\n"
	    		+ "Two riders are\n"
	    		+ " approaching\n"
	    		+ " and the ender\n"
	    		+ " begin to howl.\n"
	    		+ "\n"
	    		+ "            -=-\""));
		northPages.appendTag(new NBTTagString
				 ("\"The Cold is the great "
				+ "enemy of the Saur'ohn, "
				+ "against which they "
				+ "fortify themselves. It "
				+ "would seem from the "
				+ "text, however, that "
				+ "this Cold, rather than "
				+ "a single entity, was "
				+ "actually a conflation "
				+ "of numerous foes, as "
				+ "well as more obvious "
				+ "environmental threats.\""));
		northPages.appendTag(new NBTTagString
				 ("\"According to this "
				+ "passage there are "
				+ "actually Four Great "
				+ "Temples of Mahkin. "
				+ "From these sites the "
				+ "saur'ohn gained access "
				+ "to the burning realm "
				+ "below, from which they "
				+ "mined netherrack, "
				+ "using it to create "
				+ "eternally burning "
				+ "fires for their tall "
				+ "beacons and communal "
				+ "flames.\""));
		northPages.appendTag(new NBTTagString
	    		 ("\"But this was not the "
	    		+ "building's original "
	    		+ "function. The book "
	    		+ "speaks of the temple "
	    		+ "not as a destination, "
	    		+ "but as a point of "
	    		+ "arrival... The "
	    		+ "saur'ohn, it seems, "
	    		+ "were not from this "
	    		+ "realm. And it would "
	    		+ "also appear to be the "
	    		+ "case that the "
	    		+ "lizardmen did not\""));
		northPages.appendTag(new NBTTagString
	    		 ("\"depart their homeland "
	    		+ "willingly. For they "
	    		+ "made many plans to "
	    		+ "return - all of them "
	    		+ "violent. The Wall was "
	    		+ "to have been the "
	    		+ "spearhead of one such "
	    		+ "effort. Evidently it "
	    		+ "failed.\n"
	    		+ "\n"
	    		+ "The book goes on to "
	    		+ "speak of a champion, "
	    		+ "one who would rise from - "
	    		+ "or be raised \""));
		northPages.appendTag(new NBTTagString
 	    		 ("\"up - among them. The "
 	    		+ "Priests worked to "
   				+ "create an armour  - 'The Aegis of the Tyrant' - "
   				+ "this Chosen One would "
   				+ "wear. Crafted from "
   				+ "infused obsidian, "
   				+ "decorated with the "
   				+ "bones of the damned, "
   				+ "blessed by invocation "
   				+ "and ceremony. So "
   				+ "armoured, the Hero "
   				+ "would venture back \""));
		northPages.appendTag(new NBTTagString
   				 ("\"into their homeworld "
   				+ "and face the terrible "
   				+ "creature who had "
   				+ "enslaved them, here "
				+ "recorded only once and "
				+ "by the epiphet 'The "
				+ "Tyrant'.\n"
	    		+ "\n"
				+ "Recreate this armour. "
				+ "Step through the "
				+ "portal into the lost "
				+ "homeworld of the "
				+ "Saur'ohn. Face this "
				+ "oppressor.\""));
		northPages.appendTag(new NBTTagString
				 ("\"            -=-\n"
				 		+ "\n"
				+ "You scribble down some notes"
				+ " about the saur'ohn design."
				+ " The headdress is decorated"
				+ " with a plume of red wool,"
	    		+ " some decorated bone and"
				+ " a soul-sapphire at the center"
				+ " of the forehead.\""));
		northPages.appendTag(new NBTTagString
				 ("\"            -=-\n"
				+ "\n"
				+ "The chest has a book and a skull"
				+ " placed at the shoulders,"
				+ " along with some sort of decorative seal,"
	    		+ " a soul-sapphire at the center"
				+ " and bits of bone all over.\""));
		northPages.appendTag(new NBTTagString
				 ("\"            -=-\n"
				+ "\n"
				+ "The legs are spruced up with"
				+ " bone and flowing robes, while"
				+ " the boots have skulls for"
				+ " kneeguards and a seal.\""));
		
		northScale = new ItemStack(Items.WRITTEN_BOOK); //cures drop issue with boss, but not stacking bug.
		northScale.setTagCompound(ntag);
	    northScale.setItemDamage(369);
	}

	{
		NBTTagCompound stag = new NBTTagCompound();

		NBTTagList southPages = new NBTTagList();

		stag.setTag("pages", southPages);
		stag.setString("author", ("Salvestrom"));
		stag.setString("title", ("The Books of Scale: The Sword"));

		southPages.appendTag(new NBTTagString
	    		 ("\"'War,' asks the author "
	    		+ "of a piece of inscribed "
	    		+ "bark, 'what is it good "
	    		+ "for?'\n"
	    		+ "\n"
	    		+ "With magic and metal "
	    		+ "the saur'ohn fought "
	    		+ "against their many "
	    		+ "enemies, in whatever "
	    		+ "form they took. But "
	    		+ "their most terrible "
	    		+ "conflict came when the "
	    		+ "saur'ohn turned on "
	    		+ "each other.\""));
		southPages.appendTag(new NBTTagString
	    		 ("\"There is no given cause "
	    		+ "for the civil war, "
	    		+ "referred to as 'The Scales of Power'. The fighting "
	    		+ "was bitter, scrappy, "
	    		+ "ceaseless. Skirmishing "
	    		+ "was ongoing between "
	    		+ "larger battles, where "
	    		+ "the full might of the "
	    		+ "feuding sides could be "
	    		+ "unleashed: Magi raising "
	    		+ "skeletal servants; "
	    		+ "Golems \""));
		southPages.appendTag(new NBTTagString
	    	  	 ("\"smashing "
	    		+ "through the massed "
	    	  	+ "ranks of Scufflers; "
	    		+ "Stalkers raining arrows "
	    		+ "upon them all.\n"
	    		+ "\n"
	    		+ "When the Witch "
	    		+ "Doctors saw the "
	    		+ "flaming star, both sides "
	    		+ "called it an omen, "
	    		+ "claiming it hearlded "
	    		+ "their imminent final "
	    		+ "victory.\""));
		southPages.appendTag(new NBTTagString
	    		 ("\"You hold a primitive "
	    		+ "image in your hand "
	    		+ "showing the impact of "
	    		+ "this meteor, many "
	    		+ "saur'ohn perishing in "
	    		+ "blue fire. Clearly "
	    		+ "noone won anything "
	    		+ "that day.\n"
	    		+ "\n"
	    		+ "Further on is "
	    		+ "recorded the "
	    		+ "discovery of a "
	    		+ "fragment of the "
	    		+ "meteor - a pulsing "));
		southPages.appendTag(new NBTTagString
	    		 ("\"blue rock. They "
	    		+ "worshipped this "
	    		+ "meteor fragment as a "
	    		+ "gift from the Lord of "
	    		+ "the Damned, "
	    		+ "well-pleased with "
	    		+ "the carnage they had "
	    		+ "visited on each other. "
	    		+ "They dubbed it 'The "
	    		+ "Eye of the Moon'.\n"
	    		+ "\n"
	    		+ "It isn't clear how it happened, "
	    		+ "but when the eye became \""));
		southPages.appendTag(new NBTTagString
	    	 	 ("\"embedded in the skull of the "
	    	 	+ "Tyrant they "
	    	 	+ "quickly began "
	    	 	+ "worshipping the "
	    	 	+ "creature as an Avatar "
	    	 	+ "of the Selenic "
	    	 	+ "Death-Master.\n"
	    	 	+ "\n"
	    	 	+ "A cult rose up around "
	    	 	+ "the beast, headed by the Magi "
	    	 	+ "who oversaw the construction of "
	    	 	+ "the temple in the impact \""));
		southPages.appendTag(new NBTTagString
	    	  	 ("\"crater. The witch doctors saw "
	    		+ "little choice but to "
	    		+ "flee, leading as many "
	    		+ "saur'ohn to the "
	    		+ "overworld as they "
	    		+ "could. The magi "
	    		+ "responded with "
	    		+ "the systematic "
	    		+ "destruction of these "
	    		+ "portals and any villages "
	    		+ "that did not submit wholly to the cults authority.\""));
		southPages.appendTag(new NBTTagString
	    		 ("\"Only the portal at "
	    		+ "the Tyrant's Temple "
	    		+ "was left active, but "
	    		+ "guarded by the "
	    		+ "creature itself.\n"
	    		+ "\n"
   		 		+ "           -=-\n"
	    		+ "\n"
	    		+ "You now have in your "
	    		+ "possesion the only "
	    		+ "legacies of the once "
	    		+ "great saur'ohn "
	    		+ "civillisation: the "
	    		+ "Books of Scale; the \""));
		southPages.appendTag(new NBTTagString
	    		 ("\"Aegis of the Tyrant "
	    		+ "and the Eye of the "
	    		+ "Moon.\n"
	    		+ "\n"
	    		+ "What calamity befell "
	    		+ "your own kind for "
	    		+ "there to be even less "
	    		+ "than these few scraps left "
	    		+ "behind? Maybe one day you'll "
	    		+ "find out. For now, rest and "
	    		+ "try not to dwell on the distant sound of drums.\""));

		southScale = new ItemStack(Items.WRITTEN_BOOK);
	    southScale.setItemDamage(370);
	    southScale.setTagCompound(stag);
	
	}
	}
	
	
	

	@SuppressWarnings({"rawtypes", "unchecked"})
    @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item i, CreativeTabs c, List<ItemStack> list) {
		list.add(new ItemStack(bookScale.eastScale.getItem(), 1, bookScale.eastScale.getMetadata()));
	 	list.add(new ItemStack(i, 1, bookScale.westScale.getMetadata()));
	 	list.add(bookScale.northScale);
	 	list.add(bookScale.southScale); 
	 	}
}
