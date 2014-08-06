package com.joshj5hawk.handler;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;


public class STWorldGen implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider cG, IChunkProvider cP) 
	{
		switch(world.provider.dimensionId)
		{
			case 0:
				//Generate Overworld
				generateSurface(world, random, chunkX*16, chunkZ*16);
			case -1:
				//Generate Nether
				generateNether(world, random, chunkX*16, chunkZ*16);
			case 1:
				//Generate End
				generateEnd(world, random, chunkX*16, chunkZ*16);
		}
	}

	private void generateSurface(World world, Random random, int x, int z) 
	{
		//this.addOreSpawn(BLOCK, world, random, x, z, maxX, MaxZ, maxVeinSize, percentChanceToSpawn, minY, maxY);
	}

	private void generateNether(World world, Random random, int x, int z) 
	{
		
	}

	private void generateEnd(World world, Random random, int x, int z) 
	{
		
	}
	
	private void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int spawnChance, int minY, int maxY) 
	{
		for(int i = 0; i < spawnChance; i ++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
		}
	}
}
