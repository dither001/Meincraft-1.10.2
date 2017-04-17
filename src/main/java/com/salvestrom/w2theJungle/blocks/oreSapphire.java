package com.salvestrom.w2theJungle.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class oreSapphire extends Block {

	public oreSapphire(int i, Material p_i45394_1_)
	{
		super(p_i45394_1_);
		this.setUnlocalizedName("oreSapphire");
		//this.setBlockTextureName("thejungle:oreSapphire");
		this.setCreativeTab(w2theJungle.JungleModTab);
		this.setHardness(5.0F);
		this.setSoundType(SoundType.STONE);
		this.setResistance(6.0F);
		this.setHarvestLevel("pickaxe", 3);
		this.setLightLevel(0.4F);
		this.setTickRandomly(true);
		
	}
	public void addInformation(ItemStack IStak, EntityPlayer nttplaya, List<String> list, boolean par4)
	{list.add("This ore glows faintly.");}
	
    public void randomDisplayTick(IBlockState stateIn, World wrld, BlockPos pos, Random rnd)
    {
    	int x = pos.getX(); int y = pos.getY(); int z = pos.getZ();

        for (int l = 0; l < 3; ++l)
        {
            double d0 = (double)((float)x + rnd.nextFloat());
            double d1 = (double)((float)y + rnd.nextFloat());
            double d2 = (double)((float)z + rnd.nextFloat());
            //double d3 = 0.0D;            double d4 = 0.0D;            double d5 = 0.0D;
            int i1 = rnd.nextInt(2) * 2 - 1;
//            d3 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;
//            d4 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;
//            d5 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;

            if (wrld.getBlockState(pos.west()) != this && wrld.getBlockState(pos.east()) != this)
            {
                d0 = (double)x + 0.5D + 0.25D * (double)i1;
//                d3 = (double)(rnd.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                d2 = (double)z + 0.5D + 0.25D * (double)i1;
//                d5 = (double)(rnd.nextFloat() * 2.0F * (float)i1);
            }

            wrld.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, 0.12, 0.5, 0.95);// d3, d4, d5);
        }
    }
    
    //nonfunctioning. no exp at all.
    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
    	return 0; //this.getItemDropped(metadata, rng, fortune) != Item.getItemFromBlock(this) ? 5 : 0;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer nttp, BlockPos pos, IBlockState state, TileEntity te, ItemStack is)
    {
        //nttp.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
        nttp.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, pos, state, nttp) &&
        		EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, is) > 0)
        {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            ItemStack item = new ItemStack(JungleItems.gemSapphire);

            if (item != null)
            {
            	items.clear();
                items.add(item);
            }

            ForgeEventFactory.fireBlockHarvesting(items, world, pos, state, 0, 1.0f, true, nttp);
            for (ItemStack its : items)
            {
                spawnAsEntity(world, pos, its);
                this.dropXpOnBlockBreak(world, pos, 5);
            }
        }
        else
        {
            harvesters.set(nttp);
            int i1 = EnchantmentHelper.getLootingModifier(nttp);
            this.dropBlockAsItem(world, pos, state, i1);
            harvesters.set(null);
        }
    }

    @Override
    public void dropXpOnBlockBreak(World p_149657_1_, BlockPos pos, int p_149657_5_)
    {
        if (!p_149657_1_.isRemote)
        {
            while (p_149657_5_ > 0)
            {
                int i1 = EntityXPOrb.getXPSplit(p_149657_5_);
                p_149657_5_ -= i1;
                p_149657_1_.spawnEntityInWorld(new EntityXPOrb(p_149657_1_, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, i1));
            }
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {   	
        return Item.getItemFromBlock(this);
    }
}
