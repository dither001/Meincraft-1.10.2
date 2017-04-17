package com.salvestrom.w2theJungle.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.mobs.entity.EntityWeb;

public class ThrownWeb extends Item {
	
    public ThrownWeb(int i)
    {
        this.maxStackSize = 16;
        this.setUnlocalizedName("thrownWeb");
        this.setCreativeTab(w2theJungle.JungleModTab);
        //this.setTextureName("thejungle:thrownWeb");
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player, EnumHand hand)
    {
    	if (!player.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        par2World.playSound(player, new BlockPos(player), SoundEvents.BLOCK_SNOW_STEP, SoundCategory.BLOCKS, 2.0F, 1.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
            EntityWeb nttweb = new EntityWeb(par2World, player);
            nttweb.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.6F, 1.0F);
            par2World.spawnEntityInWorld(nttweb);
        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, par1ItemStack);
    }
    /*    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
    this.itemIcon = iconRegister.registerIcon("thejungle:thrownWeb");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return par1 == 0 ? this.itemIcon : super.getIconFromDamage(par1);
    }
    */
}

