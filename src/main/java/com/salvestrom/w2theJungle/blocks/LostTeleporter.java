package com.salvestrom.w2theJungle.blocks;

import java.util.Random;

import com.salvestrom.w2theJungle.w2theJungle;
import com.salvestrom.w2theJungle.init.JungleBlocks;
import com.salvestrom.w2theJungle.worldGen.structures.LostPortalChamber;
//import com.sun.xml.internal.ws.handler.HandlerProcessor.Direction;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class LostTeleporter extends Teleporter {

	private final WorldServer worldServerInstance;
	private final Random rng;
	private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);
	public LostTeleporter(WorldServer par1WorldServer)
	{
		super(par1WorldServer);

		this.worldServerInstance = par1WorldServer;
		this.rng = new Random(par1WorldServer.getSeed());
	}

	// lifed directly from teleporter class
	public void placeInPortal(Entity par1Entity, float f)
	{
		if (Math.abs(this.worldServerInstance.provider.getDimension()) != 1)// || this.worldServerInstance.provider.getDimension() != -1)
		{
			if(this.worldServerInstance.provider.getDimension() == w2theJungle.dimensionIdLost) {
				if (!this.placeInExistingPortal(par1Entity, f))
				{        			
					new LostPortalChamber().createPortalChamber(par1Entity, this.worldServerInstance, this.rng);
					this.placeInExistingPortal(par1Entity, f);
				}
			}
			else
				if(!this.placeInExistingPortal(par1Entity, f))
				{
					new LostPortalChamber().createPortalChamber(par1Entity, this.worldServerInstance, this.rng);
					this.placeInExistingPortal(par1Entity, f);
				}
		}
	}

	public boolean placeInExistingPortal(Entity entityIn, float rotyaw)
	{ 
		int i = 128;
		double d0 = -1.0D;
		int j = MathHelper.floor_double(entityIn.posX);
		int k = MathHelper.floor_double(entityIn.posZ);
		boolean flag = true;
		BlockPos blockpos = BlockPos.ORIGIN;
		long l = ChunkPos.asLong(j, k);

		if (this.destinationCoordinateCache.containsKey(l))
		{
			Teleporter.PortalPosition teleporter$portalposition = (Teleporter.PortalPosition)this.destinationCoordinateCache.get(l);
			d0 = 0.0D;
			blockpos = teleporter$portalposition;
			teleporter$portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
			flag = false;
		}
		else
		{
			BlockPos blockpos3 = new BlockPos(entityIn);

			for (int i1 = -128; i1 <= 128; ++i1)
			{
				BlockPos blockpos2;

				for (int j1 = -128; j1 <= 128; ++j1)
				{
					for (BlockPos blockpos1 = blockpos3.add(i1, this.worldServerInstance.getActualHeight() - 1 - blockpos3.getY(), j1); blockpos1.getY() >= 0; blockpos1 = blockpos2)
					{
						blockpos2 = blockpos1.down();

						if (this.worldServerInstance.getBlockState(blockpos1).getBlock() == JungleBlocks.lostWorldPortal)
						{
							while (this.worldServerInstance.getBlockState(blockpos2 = blockpos1.down()).getBlock() == JungleBlocks.lostWorldPortal)
							{
								blockpos1 = blockpos2;
							}

							double d1 = blockpos1.distanceSq(blockpos3);

							if (d0 < 0.0D || d1 < d0)
							{
								d0 = d1;
								blockpos = blockpos1;
							}
						}
					}
				}
			}
		}

		if (d0 >= 0.0D)
		{
			if (flag)
			{
				this.destinationCoordinateCache.put(l, new Teleporter.PortalPosition(blockpos, this.worldServerInstance.getTotalWorldTime()));
			}

			double d5 = (double)blockpos.getX() + 0.5D;
			double d6 = (double)blockpos.getY() + 0.5D;
			double d7 = (double)blockpos.getZ() + 0.5D;
			BlockPattern.PatternHelper blockpattern$patternhelper = JungleBlocks.lostWorldPortal.createPatternHelper(this.worldServerInstance, blockpos);
			boolean flag1 = blockpattern$patternhelper.getForwards().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE;
			double d2 = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? (double)blockpattern$patternhelper.getFrontTopLeft().getZ() : (double)blockpattern$patternhelper.getFrontTopLeft().getX();
			d6 = (double)(blockpattern$patternhelper
					.getFrontTopLeft()
					.getY() + 1)
					- entityIn
					.getLastPortalVec()
					.yCoord
					* (double)blockpattern$patternhelper
					.getHeight();

			if (flag1)
			{
				++d2;
			}

			if (blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X)
			{
				d7 = d2 + (1.0D - entityIn.getLastPortalVec().xCoord) * (double)blockpattern$patternhelper.getWidth() * (double)blockpattern$patternhelper.getForwards().rotateY().getAxisDirection().getOffset();
			}
			else
			{
				d5 = d2 + (1.0D - entityIn.getLastPortalVec().xCoord) * (double)blockpattern$patternhelper.getWidth() * (double)blockpattern$patternhelper.getForwards().rotateY().getAxisDirection().getOffset();
			}

			float f = 0.0F;
			float f1 = 0.0F;
			float f2 = 0.0F;
			float f3 = 0.0F;

			if (blockpattern$patternhelper.getForwards().getOpposite() == entityIn.getTeleportDirection())
			{
				f = 1.0F;
				f1 = 1.0F;
			}
			else if (blockpattern$patternhelper.getForwards().getOpposite() == entityIn.getTeleportDirection().getOpposite())
			{
				f = -1.0F;
				f1 = -1.0F;
			}
			else if (blockpattern$patternhelper.getForwards().getOpposite() == entityIn.getTeleportDirection().rotateY())
			{
				f2 = 1.0F;
				f3 = -1.0F;
			}
			else
			{
				f2 = -1.0F;
				f3 = 1.0F;
			}

			double d3 = entityIn.motionX;
			double d4 = entityIn.motionZ;
			entityIn.motionX = d3 * (double)f + d4 * (double)f3;
			entityIn.motionZ = d3 * (double)f2 + d4 * (double)f1;
			entityIn.rotationYaw = rotyaw - (float)(entityIn.getTeleportDirection().getOpposite().getHorizontalIndex() * 90) + (float)(blockpattern$patternhelper.getForwards().getHorizontalIndex() * 90);

			if (entityIn instanceof EntityPlayerMP)
			{
				((EntityPlayerMP)entityIn).connection.setPlayerLocation(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch);
			}
			else
			{
				entityIn.setLocationAndAngles(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch);
			}

			return true;
		}
		else
		{
			return false;
		}  

	}


	public boolean makePortal(Entity par1Entity)
	{ /*
		byte b0 = 16;
		double d0 = -1.0D;
		int x = MathHelper.floor_double(par1Entity.posX);
		int y = MathHelper.floor_double(par1Entity.posY);
		int z = MathHelper.floor_double(par1Entity.posZ);
		int l = x;
		int i1 = y;
		int j1 = z;
		int k1 = 0;
		int l1 = this.rng.nextInt(4);
		int i2;
		double d1;
		int k2;
		double d2;
		int i3;
		int j3;
		int k3;
		int l3;
		int i4;
		int j4;
		int k4;
		int l4;
		int i5;
		double d3;
		double d4;

		for (i2 = x - b0; i2 <= x + b0; ++i2)
		{
			d1 = (double)i2 + 0.5D - par1Entity.posX;

			for (k2 = z - b0; k2 <= z + b0; ++k2)
			{
				d2 = (double)k2 + 0.5D - par1Entity.posZ;
				label274:

					for (i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3)
					{
						if (this.worldServerInstance.isAirBlock(new BlockPos(i2, i3, k2)))
						{
							while (i3 > 0 && this.worldServerInstance.isAirBlock(new BlockPos(i2, i3 - 1, k2)))
							{
								--i3;
							}

							for (j3 = l1; j3 < l1 + 4; ++j3)
							{
								k3 = j3 % 2;
								l3 = 1 - k3;

								if (j3 % 4 >= 2)
								{
									k3 = -k3;
									l3 = -l3;
								}

								for (i4 = 0; i4 < 3; ++i4)
								{
									for (j4 = 0; j4 < 4; ++j4)
									{
										for (k4 = -1; k4 < 4; ++k4)
										{
											l4 = i2 + (j4 - 1) * k3 + i4 * l3;
											i5 = i3 + k4;
											int j5 = k2 + (j4 - 1) * l3 - i4 * k3;

											if (k4 < 0 && !this.worldServerInstance.getBlockState(l4, i5, j5).getMaterial().isSolid() || k4 >= 0 && !this.worldServerInstance.isAirBlock(new BlockPos(l4, i5, j5)))
											{
												continue label274;
											}
										}
									}
								}

								d3 = (double)i3 + 0.5D - par1Entity.posY;
								d4 = d1 * d1 + d3 * d3 + d2 * d2;

								if (d0 < 0.0D || d4 < d0)
								{
									d0 = d4;
									l = i2;
									i1 = i3;
									j1 = k2;
									k1 = j3 % 4;
								}
							}
						}
					}
			}
		}

		if (d0 < 0.0D)
		{
			for (i2 = x - b0; i2 <= x + b0; ++i2)
			{
				d1 = (double)i2 + 0.5D - par1Entity.posX;

				for (k2 = z - b0; k2 <= z + b0; ++k2)
				{
					d2 = (double)k2 + 0.5D - par1Entity.posZ;
					label222:

						for (i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3)
						{
							if (this.worldServerInstance.isAirBlock(new BlockPos(i2, i3, k2)))
							{
								while (i3 > 0 && this.worldServerInstance.isAirBlock(new BlockPos(i2, i3 - 1, k2)))
								{
									--i3;
								}

								for (j3 = l1; j3 < l1 + 2; ++j3)
								{
									k3 = j3 % 2;
									l3 = 1 - k3;

									for (i4 = 0; i4 < 4; ++i4)
									{
										for (j4 = -1; j4 < 4; ++j4)
										{
											k4 = i2 + (i4 - 1) * k3;
											l4 = i3 + j4;
											i5 = k2 + (i4 - 1) * l3;

											if (j4 < 0 && !this.worldServerInstance.getBlockState(new BlockPos(k4, l4, i5)).getMaterial().isSolid() || j4 >= 0 && !this.worldServerInstance.isAirBlock(new BlockPos(k4, l4, i5)))
											{
												continue label222;
											}
										}
									}

									d3 = (double)i3 + 0.5D - par1Entity.posY;
									d4 = d1 * d1 + d3 * d3 + d2 * d2;

									if (d0 < 0.0D || d4 < d0)
									{
										d0 = d4;
										l = i2;
										i1 = i3;
										j1 = k2;
										k1 = j3 % 2;
									}
								}
							}
						}
				}
			}
		}

		int k5 = l;
		int j2 = i1;
		k2 = j1;
		int l5 = k1 % 2;
		int l2 = 1 - l5;

		if (k1 % 4 >= 2)
		{
			l5 = -l5;
			l2 = -l2;
		}

		boolean flag;

		if (d0 < 0.0D)
		{
			if (i1 < 70)
			{
				i1 = 70;
			}

			if (i1 > this.worldServerInstance.getActualHeight() - 10)
			{
				i1 = this.worldServerInstance.getActualHeight() - 10;
			}

			j2 = i1;

			for (i3 = -1; i3 <= 1; ++i3)
			{
				for (j3 = 1; j3 < 3; ++j3)
				{
					for (k3 = -1; k3 < 3; ++k3)
					{
						l3 = k5 + (j3 - 1) * l5 + i3 * l2;
						i4 = j2 + k3;
						j4 = k2 + (j3 - 1) * l2 - i3 * l5;
						flag = k3 < 0;
						this.worldServerInstance.setBlock(l3, i4, j4, flag ? JungleBlocks.infusedObsidianBlock : Blocks.AIR);
					}
				}
			}
		}

		for (i3 = 0; i3 < 4; ++i3)
		{
			for (j3 = 0; j3 < 4; ++j3)
			{
				for (k3 = -1; k3 < 4; ++k3)
				{
					l3 = k5 + (j3 - 1) * l5;
					i4 = j2 + k3;
					j4 = k2 + (j3 - 1) * l2;
					flag = j3 == 0 || j3 == 3 || k3 == -1 || k3 == 3;
					this.worldServerInstance.setBlock(l3, i4, j4, (Block)(flag ? JungleBlocks.infusedObsidianBlock : JungleBlocks.lostWorldPortal), 0, 2);
				}
			}

			for (j3 = 0; j3 < 4; ++j3)
			{
				for (k3 = -1; k3 < 4; ++k3)
				{
					l3 = k5 + (j3 - 1) * l5;
					i4 = j2 + k3;
					j4 = k2 + (j3 - 1) * l2;
					this.worldServerInstance.notifyBlocksOfNeighborChange(l3, i4, j4, this.worldServerInstance.getBlockState(l3, i4, j4));
				}
			}
		}

*/
		return true;
		
	}


    public void removeStalePortalLocations(long worldTime)
    {
        if (worldTime % 100L == 0L)
        {
            long i = worldTime - 300L;
            ObjectIterator<Teleporter.PortalPosition> objectiterator = this.destinationCoordinateCache.values().iterator();

            while (objectiterator.hasNext())
            {
                Teleporter.PortalPosition teleporter$portalposition = (Teleporter.PortalPosition)objectiterator.next();

                if (teleporter$portalposition == null || teleporter$portalposition.lastUpdateTime < i)
                {
                    objectiterator.remove();
                }
            }
        }
    }

	public void createPortalChamber(Entity par1Entity) {
	}

	public class PortalPosition extends BlockPos
	{
		/** The worldtime at which this PortalPosition was last verified */
		public long lastUpdateTime;

		public PortalPosition(BlockPos pos, long lastUpdate)
		{
			super(pos.getX(), pos.getY(), pos.getZ());
			this.lastUpdateTime = lastUpdate;
		}
	}



}
