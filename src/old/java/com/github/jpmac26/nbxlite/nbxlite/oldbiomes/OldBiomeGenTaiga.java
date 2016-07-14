package com.github.jpmac26.nbxlite.nbxlite.oldbiomes;

import java.util.List;
import java.util.Random;

import com.github.jpmac26.nbxlite.nbxlite.spawners.SpawnListEntryBeta;
import com.github.jpmac26.nbxlite.nbxlite.spawners.SpawnListEntryBeta;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.WorldGenTaiga1;
import net.minecraft.src.WorldGenTaiga2;

public class OldBiomeGenTaiga extends OldBiomeGenBase
{

    public OldBiomeGenTaiga()
    {
        spawnableCreatureList.add(new SpawnListEntryBeta(net.minecraft.src.EntityWolf.class, 2));
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if(random.nextInt(3) == 0)
        {
            return new WorldGenTaiga1();
        } else
        {
            return new WorldGenTaiga2(false);
        }
    }
}
