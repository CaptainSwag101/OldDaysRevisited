package com.jpmac26.olddays.world.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.deco.OldGenBigTree;
import owg.deco.OldGenForest;
import owg.deco.OldGenTaiga1;
import owg.deco.OldGenTaiga2;
import owg.deco.OldGenTrees;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeBeta extends Biome
{
	private final int id;

    public BiomeBeta(Biome.BiomeProperties properties, int i)
    {
        super(properties);

		id = i;

		if(id == 0)
		{
			spawnableMonsterList.add(new Biome.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		}
	
		if(id == 1 || id == 2 || id == 3 || id == 6)
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
			spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		}
		
		if(id == 7)
		{
			spawnableCreatureList.clear();
			topBlock = Blocks.SAND.getDefaultState();
			fillerBlock = Blocks.SAND.getDefaultState();
		}
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
		if (id == 0)
		{
			if(rand.nextInt(3) == 0)
			{
				return new OldGenBigTree(2);
			} else
			{
				return new OldGenTrees(2);
			}
		}
		else if (id == 3) 
		{
			if(rand.nextInt(5) == 0)
			{
				return new OldGenForest();
			}
			if(rand.nextInt(3) == 0)
			{
				return new OldGenBigTree(2);
			} else
			{
				return new OldGenTrees(2);
			}
		}
		else if (id == 6)
		{
			if (rand.nextInt(3) == 0)
			{
				return new OldGenTaiga1();
			}
			else
			{
				return new OldGenTaiga2();
			}
		}	
		else
		{
			if (rand.nextInt(10) == 0)
			{
				return new OldGenBigTree(2);
			}
			else
			{
				return new OldGenTrees(2);
			}
		}	
    } 

    @SideOnly(Side.CLIENT)
    public int getGrassColor(BlockPos pos)
    {
		if( id == 6 || id == 9 )
		{
			return ColorizerGrass.getGrassColor(0.6F, 0.6F);
		}
		else if( id == 7 )
		{
			return ColorizerFoliage.getFoliageColor(0.8F, 0.2F);
		}
		else
		{
			double d = MathHelper.clamp(getFloatTemperature(pos), 0.0F, 1.0F);
			double d1 = MathHelper.clamp(this.getRainfall(), 0.0F, 1.0F);
			return ColorizerGrass.getGrassColor(d, d1);
		}
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColor(BlockPos pos)
    {
		if( id == 6 || id == 9 )
		{
			return ColorizerFoliage.getFoliageColor(0.6F, 0.6F);
		}
		else if( id == 7 )
		{
			return ColorizerFoliage.getFoliageColor(0.8F, 0.2F);
		}
		else
		{
			double d = MathHelper.clamp(getFloatTemperature(pos), 0.0F, 1.0F);
			double d1 = MathHelper.clamp(getRainfall(), 0.0F, 1.0F);
			return ColorizerFoliage.getFoliageColor(d, d1);
		}
    }
}
