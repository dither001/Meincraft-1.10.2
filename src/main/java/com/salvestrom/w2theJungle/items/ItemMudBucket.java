package com.salvestrom.w2theJungle.items;

import java.util.List;

import com.salvestrom.w2theJungle.w2theJungle;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMudBucket extends ItemBucket {

	public ItemMudBucket(Block p_i45331_1_) {
		super(p_i45331_1_);
		
		this.setUnlocalizedName("mudBucket")
		.setContainerItem(Items.BUCKET).setCreativeTab(w2theJungle.JungleModTab);
		//.setTextureName("thejungle:mudBucket");
		}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack is, World wrld, EntityPlayer nttp, EnumHand hand)
	{
		wrld.playSound(nttp, new BlockPos(nttp), SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1, 1);
		return super.onItemRightClick(is, wrld, nttp, hand);
		
	}
	
	@Override
	public void addInformation(ItemStack IStak, EntityPlayer playa, List<String> list, boolean par4)
	{
		list.add("Fer the making o'");
		list.add("mud pies.");
	}

}
