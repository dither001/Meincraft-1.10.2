package com.salvestrom.w2theJungle.worldGen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class JungleSaveWorld extends WorldSavedData {
	
		private static final String IDENTIFIER = "Welcome";
		
		public int WallSpawnCount = 0;
		public int plZvillage = 0;
		public int plXvillage = 0;
		
		public int wallLocX = 0;
		public int wallLocY = 0;
		public int wallLocZ = 0;
		
		public float fogvar1;
		public float fogvar2;
		public float fogvar3;
		
		public boolean dun;
		public int templeLocX;
		public int templeLocY;

		
		public JungleSaveWorld() {
			super(IDENTIFIER);
		}
		
		public JungleSaveWorld(String identifier) {
			super(identifier);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
			this.WallSpawnCount = nbt.getInteger("WallCount");
			this.plXvillage = nbt.getInteger("plXvillage");
			this.plZvillage = nbt.getInteger("plZvillage");
			
			this.wallLocX = nbt.getInteger("wallLocX");
			this.wallLocY = nbt.getInteger("wallLocY");
			this.wallLocZ = nbt.getInteger("wallLocZ");

			this.fogvar1 = nbt.getFloat("fogvar1");
			this.fogvar2 = nbt.getFloat("fogvar2");
			this.fogvar3 = nbt.getFloat("fogvar3");
			
			this.dun = nbt.getBoolean("hasJungleTemple");
			this.templeLocX = nbt.getInteger("tLocX");
			this.templeLocY = nbt.getInteger("tlocZ");

			
			//System.out.println(fogvar2);
			
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

			//System.out.println(this.fogvar2);

			nbt.setInteger("WallCount", this.WallSpawnCount);
			nbt.setInteger("plXvillage", this.plXvillage);
			nbt.setInteger("plZvillage", this.plZvillage);
			
			nbt.setInteger("wallLocX", this.wallLocX);
			nbt.setInteger("wallLocY", this.wallLocY);
			nbt.setInteger("wallLocZ", this.wallLocZ);
			
			nbt.setFloat("fogvar1", this.fogvar1);
			nbt.setFloat("fogvar2", this.fogvar2);
			nbt.setFloat("fogvar3", this.fogvar3);
			
			nbt.setBoolean("hasJungleTemple", this.dun);
			nbt.setInteger("tLocX", this.templeLocX);
			nbt.setInteger("tlocZ", this.templeLocY);
			return nbt;
		}
		
		public void wallSpawnCount(int i) {
			this.WallSpawnCount = i;
			this.markDirty();
		}

		public void plvillage(int[] i) {
			this.plXvillage = i[0];
			this.plZvillage = i[1];
			this.markDirty();
		}
		
		public void wallSpawn(int[] i) {
			this.wallLocX = i[0];
			this.wallLocY = i[1];
			this.wallLocZ = i[2];
			this.markDirty();
		}
		
		public void fogValues(float[] f) {
			this.fogvar1 = f[0];
			this.fogvar2 = f[1];
			this.fogvar3 = f[2];
			this.markDirty();
		}
		
		public void jungleTemple(boolean b)
		{
			this.dun = b;
			this.markDirty();
		}

		public void templeLoc(int[] i) {
			this.templeLocX = i[0];
			this.templeLocY = i[1];
			this.markDirty();
		}

		
		
		public static JungleSaveWorld get(World world) {
			//according to a forge post this is wrong, missing a line and calling wrong thing.
			//ok, so per map storage is as below. i may be screwing stuff up slightly by not using per world since
			//each dimension has its own fog...
			//updated to perworldstorage.
			
			JungleSaveWorld data = (JungleSaveWorld)world.getPerWorldStorage().getOrLoadData(JungleSaveWorld.class, IDENTIFIER);
			if (data == null) {
				data = new JungleSaveWorld();
				world.getPerWorldStorage().setData(IDENTIFIER, data);
			}
			//System.out.println(data);
			return data;
		}
}
